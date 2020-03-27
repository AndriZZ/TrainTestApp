package com.andriivanov.trainstestapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.andriivanov.trainstestapp.R
import com.andriivanov.trainstestapp.data.ArrayOfObjTrainMovements
import kotlinx.android.synthetic.main.station_layout.view.*


class StationsAdapter(
    private var trainList: MutableLiveData<ArrayOfObjTrainMovements>
) :
    RecyclerView.Adapter<StationsAdapter.TrainsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.station_layout, parent, false) as View

        return TrainsViewHolder(view)
    }

    override fun getItemCount() = trainList.value!!.objTrainMovements.size

    override fun onBindViewHolder(holder: TrainsViewHolder, position: Int) {
        holder.currentLocation.text =
            trainList.value?.objTrainMovements?.get(position)?.LocationFullName ?: ""
        holder.arrivalTime.text = "Arrival time: ${trainList.value?.objTrainMovements?.get(position)?.ExpectedArrival ?: ""}"
        holder.originToDestination.text =
            "From ${trainList.value?.objTrainMovements?.get(position)?.TrainOrigin} to ${trainList.value?.objTrainMovements?.get(position)?.TrainDestination}"
        holder.departureTime.text = "Departure time: ${trainList.value?.objTrainMovements?.get(position)?.ExpectedDeparture}"
    }

    class TrainsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val currentLocation = view.current_location
        val originToDestination = view.from_to
        val arrivalTime = view.arrival_time
        val departureTime = view.departure_time
    }
}
