package com.andriivanov.trainstestapp


import com.andriivanov.trainstestapp.data.ArrayOfObjStation
import com.andriivanov.trainstestapp.data.ArrayOfObjStationData
import com.andriivanov.trainstestapp.data.ArrayOfObjTrainMovements
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path


interface TrainServices {

    @GET("realtime/realtime.asmx/getAllStationsXML")
    suspend fun getAllStations(): ArrayOfObjStation

    @GET("realtime/realtime.asmx/getStationDataByNameXML")
    suspend fun getStationByName(@Query ("StationDesc")station: String): ArrayOfObjStationData

    @GET("realtime/realtime.asmx/getTrainMovementsXML")
    suspend fun getTrainMovementsOfTrain(@Query("TrainID")trainID:String?,@Query("TrainDate")trainDate:String?): ArrayOfObjTrainMovements


}
