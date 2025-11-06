package com.example.actnow.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.actnow.SingleBadgeDto

@Composable
fun BadgeCard(modifier: Modifier, locked : Boolean, badge : SingleBadgeDto) {
    val colorMatrix = ColorMatrix().apply {
        setToSaturation(0.5f)
    }
    Card(
        modifier = modifier
            .padding(top = 10.dp)
            .height(120.dp)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = if (locked) CardDefaults.cardColors(containerColor = Color.LightGray.copy(alpha = 0.5f)) else CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = badge.image),
                contentDescription = "profile picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                colorFilter = if (locked) ColorFilter.colorMatrix(colorMatrix) else null
            )

            Column(
                modifier = Modifier
                    .padding(12.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = badge.titre,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                if (locked) {
                    Text(
                        text = badge.objectif,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Start
                    )
                }else {
                    Text(
                        text = badge.description,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Start
                    )
                }

            }
        }
    }
}
