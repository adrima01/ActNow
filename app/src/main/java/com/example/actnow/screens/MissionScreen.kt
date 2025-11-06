package com.example.actnow.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.actnow.missionData
import com.example.actnow.SingleMissionDto
import com.example.actnow.components.MissionCard
import com.example.actnow.components.MissionSearchBar
import com.russhwolf.settings.Settings
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MissionScreen(navController : NavHostController) {
    val settings = Settings()
    var dateTimeSortAscending by remember { mutableStateOf(true) }
    var searchValue by remember { mutableStateOf(TextFieldValue("")) }

    val filteredMissions = missionData.missions.filter { mission ->
        mission.titre.lowercase().contains(searchValue.text.lowercase())
    }
    val filteredMissionsSorted = filteredMissions.sortedWith(compareBy { mission ->
        val calendar = Calendar.getInstance().apply {
            time = mission.date
            set(Calendar.HOUR_OF_DAY, mission.heure.hours)
            set(Calendar.MINUTE, mission.heure.minutes)
        }
        calendar.time
    })

    val missionsToShow = if (dateTimeSortAscending) filteredMissionsSorted
    else filteredMissionsSorted.reversed()

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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MissionSearchBar(
                value = searchValue,
                onValueChange = { searchValue = it },
                modifier = Modifier.weight(1f)
            )

            Row(
                verticalAlignment =
                    Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { dateTimeSortAscending = !dateTimeSortAscending }
                    .padding(2.dp)
            ) {
                Icon(
                    imageVector = if (dateTimeSortAscending) Icons.Default.ArrowUpward else Icons.Default.ArrowDownward,
                    contentDescription = "Trier par date"
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = "Date",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

        }



        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(missionsToShow) { mission ->
                val participe = settings.getBooleanOrNull(mission.id.toString()) ?: false
                MissionCard(
                    mission = mission,
                    onClick = { navController.navigate("details/${mission.id}") },
                    isParticipating = participe
                )
            }
        }
    }
}
