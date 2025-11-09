package com.example.actnow.viewmodels


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.actnow.missionData
import org.osmdroid.util.GeoPoint

class MapViewModel() : ViewModel() {

    var missions by mutableStateOf(missionData.missions)

    var missionGeoPoints by mutableStateOf<Map<String, GeoPoint>>(emptyMap())

    var lastId by mutableStateOf<String>("")

    fun generateMissionGeoPoints() {
        val map = mutableMapOf<String, GeoPoint>()
        for (mission in missions) {
                map[mission.id] = GeoPoint(mission.adresse.lat, mission.adresse.long)
        }
        missionGeoPoints = map
    }



    init {
        generateMissionGeoPoints()
    }

}