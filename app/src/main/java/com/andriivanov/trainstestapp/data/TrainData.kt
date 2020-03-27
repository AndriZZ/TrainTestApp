package com.andriivanov.trainstestapp.data

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement


@JacksonXmlRootElement(localName = "ArrayOfObjTrainMovements")
data class ArrayOfObjTrainMovements(
    @JacksonXmlProperty(localName = "objTrainMovements")
    @JacksonXmlElementWrapper(useWrapping = false)
    val objTrainMovements: List<ObjTrainMovements?>
)

data class ObjTrainMovements(
    @JacksonXmlProperty(localName = "ExpectedDeparture")
    val ExpectedDeparture: String?,
    @JacksonXmlProperty(localName = "TrainCode")
    val TrainCode: String?,
    @JacksonXmlProperty(localName = "TrainDate")
    val TrainDate: String?,
    @JacksonXmlProperty(localName = "ExpectedArrival")
    val ExpectedArrival: String?,
    @JacksonXmlProperty(localName = "LocationCode")
    val LocationCode: String?,
    @JacksonXmlProperty(localName = "Arrival")
    val Arrival: String?,
    @JacksonXmlProperty(localName = "StopType")
    val StopType: String?,
    @JacksonXmlProperty(localName = "ScheduledArrival")
    val ScheduledArrival: String?,
    @JacksonXmlProperty(localName = "TrainOrigin")
    val TrainOrigin: String?,
    @JacksonXmlProperty(localName = "ScheduledDeparture")
    val ScheduledDeparture: String?,
    @JacksonXmlProperty(localName = "Departure")
    val Departure: String?,
    @JacksonXmlProperty(localName = "AutoArrival")
    val AutoArrival: String?,
    @JacksonXmlProperty(localName = "AutoDepart")
    val AutoDepart: String?,
    @JacksonXmlProperty(localName = "TrainDestination")
    val TrainDestination: String?,
    @JacksonXmlProperty(localName = "LocationFullName")
    val LocationFullName: String?,
    @JacksonXmlProperty(localName = "LocationType")
    val LocationType: String?,
    @JacksonXmlProperty(localName = "LocationOrder")
    val LocationOrder: String?
)
