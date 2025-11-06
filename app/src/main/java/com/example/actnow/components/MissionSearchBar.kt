package com.example.actnow.components
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextField
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MissionSearchBar(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = {
            Text(
                text = "Rechercher une mission",
                color = Color.White
            ) },
        singleLine = true,
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8F),
            unfocusedContainerColor = Color.Blue.copy(alpha = 0.3f),
            focusedPlaceholderColor = Color.Gray,
            unfocusedPlaceholderColor = Color.Gray,
            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
            focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
            focusedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
            focusedTrailingIconColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedTrailingIconColor = MaterialTheme.colorScheme.onPrimary,
        ),
        leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = "Rechercher") },
        trailingIcon = {
            if (value.text.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Effacer",
                    modifier = Modifier.clickable { onValueChange(TextFieldValue("")) }
                )
            }
        }
    )
}
