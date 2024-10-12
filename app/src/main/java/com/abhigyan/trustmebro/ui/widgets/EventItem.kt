package com.abhigyan.trustmebro.ui.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhigyan.trustmebro.calculator.types.Event
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EventItem(event: Event, onClick: ()-> Unit,onHold: ()-> Unit, modifier: Modifier = Modifier){
    ListItem(modifier = modifier
        .padding(8.dp)
        .shadow(5.dp, shape = RoundedCornerShape(16.dp))
        .clip(RoundedCornerShape(16.dp))
        .combinedClickable(onClick = { onClick() }, onLongClick = { onHold() }),
        colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.primary),
        headlineContent = { Text(text = event.name, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimary) },
        supportingContent = { Text(text = event.date, color = MaterialTheme.colorScheme.primaryContainer) },
        trailingContent = {
            Box(modifier = Modifier
                .shadow(8.dp, shape = RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(32.dp))
                .background(color = MaterialTheme.colorScheme.background)
                .height(40.dp)
                .width(60.dp)
                , contentAlignment = Alignment.Center){
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Text(
                        text = event.people.size.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(imageVector = Icons.Default.Person, contentDescription = null)
                }
            }
        })
}