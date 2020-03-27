package com.andriivanov.trainstestapp.repository

import android.content.Context
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import com.andriivanov.trainstestapp.R
import com.andriivanov.trainstestapp.TIME_FORMAT
import com.andriivanov.trainstestapp.TrainServices
import com.andriivanov.trainstestapp.data.ArrayOfObjTrainMovements
import com.andriivanov.trainstestapp.data.ObjTrainMovements
import com.andriivanov.trainstestapp.data.TrainStations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS"
)
class TrainRepository @Inject
constructor(
    private var trainServices: TrainServices,
    private val context: Context

) : Observable {
    var trainMovements: MutableLiveData<ArrayOfObjTrainMovements> = MutableLiveData()

    fun getTrainRoute(origin:TrainStations, destination:TrainStations) {
        CoroutineScope(Dispatchers.Default).launch {
            withContext(Main) {
                trainMovements.value =
                    fetchRouteFromServer(origin, destination)
            }
        }

    }

    private fun doesTrainGoToDestination(
        trainString: String,
        destinationEnum: TrainStations,
        direction: String
    ): Boolean {
        try {
            val trainEnum = TrainStations.valueOf(trainString)
            if ((trainEnum <= destinationEnum) and (direction == context.getString(R.string.southbound))) return true
            if ((trainEnum >= destinationEnum) and (direction == context.getString(R.string.northbound))) return true
        } catch (e: IllegalArgumentException) {
            return false
        }
        return false
    }

    private suspend fun fetchRouteFromServer(
        originEnum: TrainStations,
        destinationEnum: TrainStations
    ): ArrayOfObjTrainMovements {
        var direction = context.getString(R.string.northbound)
        if (originEnum > destinationEnum) {
            direction = context.getString(R.string.southbound)
        }
        var presentableTrainRoute: MutableList<ObjTrainMovements?> = mutableListOf()

        //get all passing trains at origin
        val trainsAtOrigin = trainServices.getStationByName(originEnum.toString())
        val mostRecentTrainToBray = trainsAtOrigin.objStationData?.find { it.Direction.equals(direction) }
        //get the movement of the most recent train
        if (mostRecentTrainToBray != null) {
            val recentTrainMovementToBray = trainServices.getTrainMovementsOfTrain(
                mostRecentTrainToBray.Traincode,
                mostRecentTrainToBray.Traindate
            )
            presentableTrainRoute = mutableListOf(recentTrainMovementToBray.objTrainMovements.find {
                it?.LocationFullName.equals(originEnum.toString())
            })
        }

        //get trains at Bray since it's the transfer station
        val trainsAtBray = trainServices.getStationByName(context.getString(R.string.bray))
        val trainToDestination = trainsAtBray.objStationData?.find {
            it.Direction.equals(direction) and
                    !it.Destination.equals(context.getString(R.string.bray)) and
                    isTrainEligible(it.Expdepart, 12) and
                    doesTrainGoToDestination(it.Destination.toString(), destinationEnum, direction)
        }
        if (trainToDestination != null) {
            val recentTrainMovementToDestination = trainServices.getTrainMovementsOfTrain(
                trainToDestination.Traincode,
                trainToDestination.Traindate
            )
            presentableTrainRoute.addAll(recentTrainMovementToDestination.objTrainMovements.filter {
                isTrainEligible(it?.ExpectedDeparture, 0)
            })

        }

        return ArrayOfObjTrainMovements(objTrainMovements = presentableTrainRoute)
    }

    private fun isTrainEligible(time: String?, delayMinutes: Int): Boolean {
        val calendar: Calendar = Calendar.getInstance()
        calendar.add(Calendar.MINUTE, delayMinutes)
        val formatter = SimpleDateFormat(TIME_FORMAT, Locale.UK)
        val currentDate: String = formatter.format(calendar.time)
        val currentTimeWithDelay = formatter.parse(currentDate)
        val trainDeparture = formatter.parse(time)
        if (currentTimeWithDelay <= trainDeparture) {
            return true
        }
        return false
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
