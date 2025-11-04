package com.example.actnow.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.actnow.missionData
import com.example.actnow.SingleMissionDto
import com.example.actnow.components.MissionCard
import com.example.actnow.components.MissionSearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MissionScreen() {
    // État pour la barre de recherche
    var searchValue by remember { mutableStateOf(TextFieldValue("")) }

    // État pour la mission sélectionnée
    var selectedMission by remember { mutableStateOf<SingleMissionDto?>(null) }

    // Filtrage des missions
    val filteredMissions = missionData.missions.filter { mission ->
        mission.titre.lowercase().contains(searchValue.text.lowercase())
    }

    // Si une mission est sélectionnée, afficher DetailsScreen
    if (selectedMission != null) {
        DetailsScreen(
            mission = selectedMission!!,
            onBack = { selectedMission = null } // permet de revenir à la liste
        )
        return
    }

    // Liste des missions
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Missions",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        // Barre de recherche
        MissionSearchBar(
            value = searchValue,
            onValueChange = { searchValue = it }
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(filteredMissions) { mission ->
                MissionCard(mission = mission, onClick = {
                    selectedMission = mission // afficher le détail
                })
            }
        }
    }
}
