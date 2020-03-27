package com.andriivanov.trainstestapp.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andriivanov.trainstestapp.data.ArrayOfObjTrainMovements
import com.andriivanov.trainstestapp.data.TrainStations
import com.andriivanov.trainstestapp.repository.TrainRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private var trainRepository: TrainRepository
) : ViewModel(),Observable {
    @Bindable
    var trainStationsLiveData: MutableLiveData<ArrayOfObjTrainMovements> = MutableLiveData()
    fun fetchData() {
        trainRepository.trainMovements.observeForever {
            trainStationsLiveData.value = it
        }
    }

    fun getRoute(origin:TrainStations, destination:TrainStations){
        trainRepository.getTrainRoute(origin,destination)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
