package com.example.actnow.screens

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
import com.example.actnow.components.ParticiperButton


@Composable
fun DetailsScreen(mission: SingleMissionDto, onBack: () -> Unit) {

    val context = LocalContext.current
    val imageResId = context.resources.getIdentifier(
        mission.imageName,
        "drawable",
        context.packageName
    )
    val participantsDrawableIds = mission.participantsImages.map { imageName ->
        context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            IconButton(onClick = { onBack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Retour"
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(mission.titre, style = MaterialTheme.typography.headlineMedium)
                            Spacer(Modifier.height(4.dp))
                            Text(" ${mission.lieu}")
                            Text(" ${mission.date.formatAsDdMmYyyy()} à ${mission.heure}")
                        }
                        AsyncImage(
                            model = imageResId,
                            contentDescription = mission.titre,
                            modifier = Modifier
                                .size(130.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    // Description
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            mission.description,
                            modifier = Modifier.padding(12.dp)
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    // Participants
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        participantsDrawableIds.forEach { resId ->
                            AsyncImage(
                                model = resId,
                                contentDescription = "Participant",
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(RoundedCornerShape(18.dp))
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            "0/${mission.nombreParticipants} Participant(s)"
                        )
                    }


                    Spacer(Modifier.height(16.dp))

                    // Récompenses
                    Column {
                        Text("Récompenses :", style = MaterialTheme.typography.titleMedium)

                        if (mission.recompenses.isNotEmpty()) {
                            mission.recompenses.forEach { recompense ->
                                Text("• $recompense", style = MaterialTheme.typography.bodyMedium)
                            }
                        } else {
                            Text(
                                "Aucune récompense spécifiée",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                    Spacer(Modifier.height(80.dp))


                }

            }

        }
        ParticiperButton(
            missionId = mission.id,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            onParticipationChange = { /*rafraichir?*/ }
        )

    }
}
