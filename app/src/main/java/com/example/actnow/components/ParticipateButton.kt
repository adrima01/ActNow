package com.example.actnow.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ParticiperButton(modifier: Modifier = Modifier) {
    var participe by remember { mutableStateOf(false) }

    Button(
        onClick = { participe = !participe },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (participe) Color.Red else Color(0xFF4CAF50),
            contentColor = Color.White
        )
    ) {
        Text(if (participe) "Annuler" else "Participer")
    }
}
