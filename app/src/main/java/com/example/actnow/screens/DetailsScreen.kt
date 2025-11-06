package com.example.actnow.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.actnow.components.ParticiperButton



@Composable
fun DetailsScreen(mission: SingleMissionDto, navController: NavHostController) {
    val context = LocalContext.current
    val imageResId = context.resources.getIdentifier(
        mission.imageName,
        "drawable",
        context.packageName
    )
    val participantsDrawableIds = mission.participantsImages.map { imageName ->
        context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }

    Box(modifier = Modifier.fillMaxSize()) {

        IconButton(
            onClick = { navController.popBackStack() }
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Retour")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Nom de l'association
            Text(
                text = mission.association.nom,
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF1F2937)
            )
            Text(
                text = mission.lieu,
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF1F2937)
            )
            Text(
                text = mission.date.formatAsDdMmYyyy(),
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
                    .height(200.dp)
                    .clip(RoundedCornerShape(24.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nom de la mission
            Text(mission.titre, style = MaterialTheme.typography.titleMedium)
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    mission.description,
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
                participantsDrawableIds.forEachIndexed { index, resId ->
                    AsyncImage(
                        model = resId,
                        contentDescription = "Participant ${index + 1}",
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(18.dp))
                            .border(2.dp, Color.White, RoundedCornerShape(18.dp))
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text("${mission.participantsImages.size} /${mission.nombreParticipants} Participant(s)")
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

            Spacer(modifier = Modifier.height(120.dp)) // espace pour le bouton
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            ParticiperButton(
                missionId = mission.id,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                onParticipationChange = { /* rafraîchir si nécessaire */ }
            )
        }
    }
}
