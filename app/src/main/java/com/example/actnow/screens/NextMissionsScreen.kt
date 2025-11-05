package com.example.actnow.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.actnow.components.MissionCard
import com.example.actnow.missionData
import com.russhwolf.settings.Settings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NextMissionsScreen(navController: NavController) {

    val settings = Settings()

    val participatedMissions = missionData.missions.filter { mission ->
        settings.getBooleanOrNull(mission.id.toString()) == true
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Retour")
        }

        Text(
            text = "Vos missions à venir",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(participatedMissions) { mission ->
                MissionCard(
                    mission = mission,
                    onClick = { },
                    isParticipating = true
                )
            }
        }
    }
}