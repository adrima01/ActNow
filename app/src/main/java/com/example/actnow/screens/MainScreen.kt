package com.example.actnow.screens

import androidx.compose.foundation.Image
import com.example.actnow.R
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.actnow.missionData
import com.example.actnow.viewmodels.MissionViewModel
import com.example.actnow.viewmodels.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {

    val startDestination = Destination.MISSION
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier.size(40.dp),
                    )
                }
            )
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
                        label = { Text(destination.label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Blue.copy(alpha = 0.8f),
                            selectedTextColor = Color.Blue.copy(alpha = 0.8f),
                            unselectedIconColor = Color.Blue.copy(alpha = 0.5f),
                            unselectedTextColor = Color.Blue.copy(alpha = 0.5f)
                        )
                    )
                }
            }
        }
    ) { contentPadding ->
        val missionViewModel: MissionViewModel = viewModel()
        val profileViewModel: ProfileViewModel = viewModel()
        NavHost(
            navController = navController,
            startDestination = "missions",
            modifier = Modifier.padding(contentPadding)
        ) {
            composable("missions") {
                MissionScreen(navController, missionViewModel)
            }

            composable("map") {
                MapScreenWrapper(navController = navController, viewModel = missionViewModel)
            }

            composable("profile") {
                ProfileScreen(navController, profileViewModel)
            }

            composable("next_Missions") {
                NextMissionsScreen(navController, missionViewModel)
            }

            composable("last_Missions") {
                LastMissionsScreen(navController, profileViewModel)
            }

            composable("details/{missionId}") { backStackEntry ->
                val missionId = backStackEntry.arguments?.getString("missionId")
                val mission = missionData.missions.find { it.id == missionId }
                val missionViewModel: MissionViewModel = viewModel()
                if (mission != null) {
                    DetailsScreen(mission = mission, navController = navController, missionViewModel)
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
