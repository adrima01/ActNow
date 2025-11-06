package com.example.actnow.screens

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Map
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.actnow.missionData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {

    val startDestination = Destination.MISSION
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("ActNow") })
        },
        bottomBar = {
            NavigationBar {
                Destination.entries.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedDestination == index,
                        onClick = {
                            navController.navigate(route = destination.route)
                            selectedDestination = index
                        },
                        icon = {
                            Icon(
                                destination.icon,
                                contentDescription = destination.contentDescription
                            )
                        },
                        label = { Text(destination.label) }
                    )
                }
            }
        }
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = "missions",
            modifier = Modifier.padding(contentPadding)
        ) {
            composable("missions") {
                MissionScreen(navController)
            }

            composable("map") {
                MapScreen()
            }

            composable("profile") {
                ProfileScreen(navController)
            }

            composable("next_Missions") {
                NextMissionsScreen(navController)
            }

            composable("last_Missions") {
                LastMissionsScreen(navController)
            }
            composable("details/{missionId}") { backStackEntry ->
                val missionId = backStackEntry.arguments?.getString("missionId")
                val mission = missionData.missions.find { it.id == missionId }
                if (mission != null) {
                    DetailsScreen(mission = mission, navController = navController)
                }
            }

        }
    }
}

enum class Destination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    MISSION("missions", "Missions", Icons.Filled.CalendarToday, "Missions"),
    MAP("map", "Carte", Icons.Filled.Map, "Carte"),
    PROFILE("profile", "Profile", Icons.Filled.AccountCircle, "Profile"),
}
