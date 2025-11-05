package com.example.actnow.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.russhwolf.settings.Settings

@Composable
fun ParticiperButton(
    missionId: String,
    modifier: Modifier = Modifier,
    onParticipationChange: (Boolean) -> Unit
) {
    val settings = Settings()
    var participe by remember { mutableStateOf(settings.getBooleanOrNull(missionId.toString()) ?: false) }

    Button(
        onClick = {
            participe = !participe
            settings.putBoolean(missionId.toString(), participe)
            onParticipationChange(participe) // 🔄 callback vers MissionScreen
        },
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (participe) Color.Red else Color(0xFF4CAF50),
            contentColor = Color.White
        )
    ) {
        Text(if (participe) "Annuler" else "Participer")
    }
}
