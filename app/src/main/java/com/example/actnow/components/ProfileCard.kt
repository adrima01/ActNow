@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.actnow.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.border
import androidx.navigation.NavController
import com.example.actnow.viewmodels.ProfileViewModel



@Composable
fun ProfileCard(navController: NavController, viewModel: ProfileViewModel) {

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = viewModel.imageResId),
                    contentDescription = "profile picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column (
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = viewModel.fullName,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "Membre depuis ${viewModel.formattedDate}",
                        fontSize = 14.sp,
                    )
                    Row (
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Card (
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Blue.copy(alpha = 0.6f)
                            )
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(8.dp),
                                text = viewModel.niveauActuel.titre,
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                        Card (
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Blue.copy(alpha = 0.6f)
                            )
                        ){
                            Text(
                                modifier = Modifier
                                    .padding(8.dp),
                                text = "${viewModel.totalHeures}h de bénévolat",
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    StatCard(
                        title = "Mission(s) complétée(s)",
                        value = viewModel.missionsCompletees,
                        modifier = Modifier.weight(1f),
                        icon = Icons.Filled.Done,
                        iconDescription = "Done Icon",
                        color = Color.Green,
                        onClick = {
                            navController.navigate("last_missions")
                        }
                    )
                    StatCard(
                        title = "Missions à venir",
                        value = viewModel.count,
                        modifier = Modifier.weight(1f),
                        icon = Icons.Filled.DateRange,
                        iconDescription = "Date Icon",
                        color = Color.Magenta,
                        onClick = {
                            navController.navigate("next_missions")
                        }
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text("Progression vers ${viewModel.niveauSuivant?.titre}", fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { viewModel.pourcentage },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp),
                    color = Color.Blue.copy(alpha = 0.6f),
                    trackColor = Color.LightGray,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text("${( viewModel.pourcentage * 100).toInt()} %", fontSize = 12.sp)
            }
        }
    }
}
