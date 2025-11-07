package com.example.actnow.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import com.example.actnow.SingleMissionDto
import com.example.actnow.components.formatAsDdMmYyyy
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.actnow.components.ParticipateButton
import com.example.actnow.viewmodels.MissionViewModel




@Composable
fun DetailsScreen(mission: SingleMissionDto, navController: NavHostController, viewModel: MissionViewModel) {
    if (viewModel.currentMission == null) {
        val mission = viewModel.missions.first { it.id == mission.id }
        viewModel.loadCurrentMission(mission)
    }

    val mission = viewModel.currentMission ?: return
    val context = LocalContext.current
    val imageResId = context.resources.getIdentifier(
        mission.imageName,
        "drawable",
        context.packageName
    )


    Box(modifier = Modifier.fillMaxSize()) {



        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Nom de l'association
            Text(
                text = mission.titre,
                style = MaterialTheme.typography.headlineLarge,
                color = Color(0xFF1F2937)
            )
            Text(
                text = "Association: " + mission.association.nom,
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF1F2937)
            )
            Text(
                text = "Lieu: " + mission.lieu,
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF1F2937)
            )
            Text(
                text = "Date: " + mission.date.formatAsDdMmYyyy(),
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF1F2937)
            )



            Spacer(modifier = Modifier.height(8.dp))

            // Image mission
            AsyncImage(
                model = imageResId,
                contentDescription = mission.titre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(24.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nom de la mission
            Text(" Description: ", style = MaterialTheme.typography.titleSmall)
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    mission.description, style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(12.dp),
                    color = Color(0xFF4B5563)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Participants info
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                viewModel.participants.forEach { image ->
                    val id = context.resources.getIdentifier(image, "drawable", context.packageName)
                    AsyncImage(
                        model = id,
                        contentDescription = "Participant",
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(18.dp))
                            .border(2.dp, Color.White, RoundedCornerShape(18.dp))
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text("${viewModel.participants.count()}/${viewModel.currentMission?.nombreParticipants} Participant(s)")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Récompenses
            Column {
                Text("Récompenses :", style = MaterialTheme.typography.titleMedium)
                if (mission.recompenses.isNotEmpty()) {
                    mission.recompenses.forEach { recompense ->
                        Text("• $recompense", style = MaterialTheme.typography.bodyMedium)
                    }
                } else {
                    Text("Aucune récompense spécifiée", style = MaterialTheme.typography.bodyMedium)
                }
            }

            Spacer(modifier = Modifier.height(120.dp))
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            ParticipateButton(
                missionId = mission.id,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                viewModel
            )
        }
    }
}
