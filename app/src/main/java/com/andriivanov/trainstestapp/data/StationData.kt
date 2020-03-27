package com.andriivanov.trainstestapp.data

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "ArrayOfObjStationData")
data class ArrayOfObjStationData(
    @JacksonXmlProperty(localName = "objStationData")
    @JacksonXmlElementWrapper(useWrapping = false)
    var objStationData: Array<ObjStationData>?
)

@JacksonXmlRootElement(localName = "ArrayOfObjStation")
data class ArrayOfObjStation(
    @JacksonXmlProperty(localName = "objStation")
    @JacksonXmlElementWrapper(useWrapping = false)
    var objStation: Array<ObjStation>
)

data class ObjStation(
    @JacksonXmlProperty(localName = "StationDesc")
    var StationDesc: String?,
    @JacksonXmlProperty(localName = "StationAlias")
    val StationAlias: String?,
    @JacksonXmlProperty(localName = "StationLatitude")
    val StationLatitude: String,
    @JacksonXmlProperty(localName = "StationCode")
    val StationCode: String,
    @JacksonXmlProperty(localName = "StationLongitude")
    val StationLongitude: String,
    @JacksonXmlProperty(localName = "StationId")
    val StationId: String?

)

data class ObjStationData(
    @JacksonXmlProperty(localName = "Origin")
    val Origin: String?,
    @JacksonXmlProperty(localName = "Status")
    val Status: String?,
    @JacksonXmlProperty(localName = "Destination")
    val Destination: String?,
    @JacksonXmlProperty(localName = "Origintime")
    val Origintime: String?,
    @JacksonXmlProperty(localName = "Late")
    val Late: String?,
    @JacksonXmlProperty(localName = "Destinationtime")
    val Destinationtime: String?,
    @JacksonXmlProperty(localName = "Traintype")
    val Traintype: String?,
    @JacksonXmlProperty(localName = "Duein")
    val Duein: String?,
    @JacksonXmlProperty(localName = "Direction")
    val Direction: String?,
    @JacksonXmlProperty(localName = "Locationtype")
    val Locationtype: String?,
    @JacksonXmlProperty(localName = "Expdepart")
    val Expdepart: String?,
    @JacksonXmlProperty(localName = "Traincode")
    val Traincode: String?,
    @JacksonXmlProperty(localName = "Traindate")
    val Traindate: String?,
    @JacksonXmlProperty(localName = "Stationfullname")
    val Stationfullname: String?,
    @JacksonXmlProperty(localName = "Querytime")
    val Querytime: String?,
    @JacksonXmlProperty(localName = "Schdepart")
    val Schdepart: String?,
    @JacksonXmlProperty(localName = "Stationcode")
    val Stationcode: String?,
    @JacksonXmlProperty(localName = "Servertime")
    val Servertime: String?,
    @JacksonXmlProperty(localName = "Exparrival")
    val Exparrival: String?,
    @JacksonXmlProperty(localName = "Scharrival")
    val Scharrival: String?,
    @JacksonXmlProperty(localName = "Lastlocation")
    val Lastlocation: String?
)

