package com.example.actnow.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.actnow.viewmodels.MissionViewModel

@Composable
fun ParticipateButton(
    missionId: String,
    modifier: Modifier = Modifier,
    viewModel: MissionViewModel
) {

    Button(
        onClick = {
            viewModel.isParticipating = !viewModel.isParticipating
            viewModel.settings.putBoolean(missionId, viewModel.isParticipating)
            viewModel.addOrRemoveUserPicture(viewModel.isParticipating)
        },
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (viewModel.isParticipating) Color.Red else Color(0xFF4CAF50),
            contentColor = Color.White
        )
    ) {
        Text(if (viewModel.isParticipating) "Annuler" else "Participer")
    }
}
