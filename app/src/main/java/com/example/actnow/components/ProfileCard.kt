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
import com.example.actnow.R
import androidx.compose.foundation.border
import com.example.actnow.utilisateur
import java.text.SimpleDateFormat
import java.util.Locale



@Composable
fun ProfileCard() {
    val sdf = SimpleDateFormat("MMMM yyyy", Locale.FRENCH)
    val formattedDate = sdf.format(utilisateur.date)

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, shape = RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_monsieur),
                    contentDescription = "profile picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = utilisateur.prenom + " " + utilisateur.nom,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = "Membre depuis $formattedDate",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                    Row {
                        Card (
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .padding(6.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Blue.copy(alpha = 0.6f)
                            )
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(8.dp),
                                text = "Super-Bénévole",
                                color = Color.White
                            )
                        }
                        Card (
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .padding(6.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Blue.copy(alpha = 0.6f)
                            )
                        ){
                            Text(
                                modifier = Modifier
                                    .padding(8.dp),
                                text = "${utilisateur.heures}h de bénévolat",
                                color = Color.White
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    StatCard(
                        title = "Mission(s) complétée(s)",
                        value = utilisateur.missionsCompletees,
                        modifier = Modifier.weight(1f),
                        icon = Icons.Filled.Done,
                        iconDescription = "Done Icon",
                        color = Color.Green
                    )
                    StatCard(
                        title = "Missions à venir",
                        value = utilisateur.missionsAVenir,
                        modifier = Modifier.weight(1f),
                        icon = Icons.Filled.DateRange,
                        iconDescription = "Date Icon",
                        color = Color.Magenta
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ){
                Text("Progression vers Méga-Bénévole", fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { 0.75f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp),
                    color = Color.Blue,
                    trackColor = Color.LightGray,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text("75 %", fontSize = 12.sp)
            }
        }
    }
}