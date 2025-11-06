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
import com.example.actnow.components.MissionCard
import com.example.actnow.components.MissionSearchBar
import com.example.actnow.viewmodels.MissionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MissionScreen(navController : NavHostController, viewModel: MissionViewModel) {

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
                value = TextFieldValue(viewModel.searchQuery),
                onValueChange = { viewModel.onSearchQueryChange(it.text) },
                modifier = Modifier.weight(1f)
            )

            Row(
                verticalAlignment =
                    Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { viewModel.toggleSortOrder() }
                    .padding(2.dp)
            ) {
                Icon(
                    imageVector = if (viewModel.dateTimeSortAscending) Icons.Default.ArrowUpward else Icons.Default.ArrowDownward,
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
            items(viewModel.getFilteredAndSortedMissions()) { mission ->
                val participe = viewModel.isParticipating(mission.id)
                MissionCard(
                    mission = mission,
                    onClick = { navController.navigate("details/${mission.id}") },
                    isParticipating = participe
                )
            }
        }
    }
}
