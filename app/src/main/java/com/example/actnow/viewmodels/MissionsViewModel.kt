package com.example.actnow.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.example.actnow.missionData
import com.example.actnow.SingleMissionDto
import com.russhwolf.settings.Settings
import java.util.Calendar

class MissionViewModel : ViewModel() {
    val settings = Settings()

    var searchQuery by mutableStateOf(TextFieldValue(""))

    var dateTimeSortAscending by mutableStateOf(true)

    var missions by mutableStateOf(missionData.missions)

    fun onSearchQueryChange(newValue: TextFieldValue) {
        searchQuery = newValue
    }

    fun toggleSortOrder() {
        dateTimeSortAscending = !dateTimeSortAscending
    }

    fun getFilteredAndSortedMissions(): List<SingleMissionDto> {
        val filtered = missions.filter { mission ->
            mission.titre.lowercase().contains(searchQuery.text.lowercase())
        }

        val sorted = filtered.sortedWith(compareBy { mission ->
            val calendar = Calendar.getInstance().apply {
                time = mission.date
                set(Calendar.HOUR_OF_DAY, mission.heure.hours)
                set(Calendar.MINUTE, mission.heure.minutes)
            }
            calendar.time
        })

        return if (dateTimeSortAscending) sorted else sorted.reversed()
    }

    fun isParticipating(missionId: String): Boolean {
        return settings.getBooleanOrNull(missionId) ?: false
    }

    var currentMission by mutableStateOf<SingleMissionDto?>(null)

    var isParticipating by mutableStateOf(false)

    var participants by mutableStateOf<List<String>>(emptyList())


    fun loadCurrentMission(mission: SingleMissionDto) {
        currentMission = mission
        isParticipating = settings.getBooleanOrNull(mission.id) ?: false
        participants = mission.participantsImages.toList()
        if (isParticipating) {
            val newList = participants.toMutableList()
            newList.add("profile_monsieur")
            participants = newList
        }
    }

    fun addOrRemoveUserPicture(participate : Boolean){
        if (participate) {
            val newList = participants.toMutableList()
            newList.add("profile_monsieur")
            participants = newList
        } else {
            val newList = participants.toMutableList()
            newList.remove("profile_monsieur")
            participants = newList
        }
    }

}