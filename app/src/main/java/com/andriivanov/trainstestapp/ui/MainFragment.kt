package com.andriivanov.trainstestapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andriivanov.trainstestapp.R
import com.andriivanov.trainstestapp.adapter.StationsAdapter
import com.andriivanov.trainstestapp.data.TrainStations
import com.andriivanov.trainstestapp.viewmodel.MainViewModel
import dagger.android.support.DaggerFragment
import com.andriivanov.trainstestapp.databinding.MainFragmentBinding
import javax.inject.Inject

class MainFragment : DaggerFragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private lateinit var viewAdapter: StationsAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.arklwToSkill.setOnClickListener {
            viewModel.getRoute(TrainStations.Arklow,TrainStations.Shankill)
        }
        binding.skillToAkrlw.setOnClickListener {
            viewModel.getRoute(TrainStations.Shankill,TrainStations.Arklow)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.fetchData()
        viewModel.trainStationsLiveData.observe(viewLifecycleOwner, Observer {
            viewAdapter = StationsAdapter(viewModel.trainStationsLiveData)
            viewManager = LinearLayoutManager(context)
            recyclerView = binding.root.findViewById(R.id.train_recycler_view)
            recyclerView.adapter = viewAdapter
            recyclerView.layoutManager = viewManager
        })

    }

}
