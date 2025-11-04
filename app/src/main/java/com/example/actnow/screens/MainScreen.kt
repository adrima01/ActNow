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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var selectedItem by remember { mutableStateOf(0) }

    val items = listOf(
        NavItem("Missions", Icons.Filled.CalendarToday),
        NavItem("Carte", Icons.Filled.Map),
        NavItem("Profile", Icons.Filled.AccountCircle)
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("ActNow") })
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedItem) {
                0 -> MissionScreen()
                1 -> MapScreen()
                2 -> ProfileScreen()
            }
        }
    }
}

data class NavItem(val title: String, val icon: ImageVector)
