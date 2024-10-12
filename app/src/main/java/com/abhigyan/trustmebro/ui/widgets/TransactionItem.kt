package com.abhigyan.trustmebro.ui.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhigyan.trustmebro.calculator.types.Transaction
import kotlinx.coroutines.delay
import java.text.NumberFormat

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TransactionItem(transaction: Transaction, onDelete: () -> Unit, modifier: Modifier = Modifier, animationDuration: Int = 500,){
    val formattedCurrencyAmount = NumberFormat.getCurrencyInstance().format(transaction.totalCost)
    var isRemoved by remember {
        mutableStateOf(false)
    }
    var dropdownExpanded by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = isRemoved) {
        if(isRemoved){
            dropdownExpanded=false
            delay(animationDuration.toLong())
            onDelete()
        }
    }
    Box(modifier = modifier) {
        DropdownMenu(
            expanded = dropdownExpanded, onDismissRequest = { dropdownExpanded = false },
            offset = DpOffset(250.dp, (-50).dp)
        ) {
            DropdownMenuItem(text = { Text("Delete") }, leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = "Delete transaction",
                    tint = Color(0xFFEE4B2B)
                )
            }, onClick = {
//                newEvent = newEvent.apply {
//                    deleteTransaction(i)
//                }.copy()
//                val mutDropdownList = dropdownExpandedList.toMutableList()
//                mutDropdownList.removeAt(i)
//                dropdownExpandedList = mutDropdownList
                isRemoved=true
            })
        }
        AnimatedVisibility(visible = !isRemoved, exit = shrinkVertically(animationSpec = tween(durationMillis = animationDuration), shrinkTowards = Alignment.Top)+ fadeOut()) {
            ListItem(modifier = Modifier
                .padding(8.dp)
                .shadow(5.dp, shape = RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
                .combinedClickable(onClick = {
                    /* TODO */
                }, onLongClick = { dropdownExpanded = true }),
                colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                headlineContent = { Text(text = transaction.name, color = MaterialTheme.colorScheme.onPrimaryContainer) },
                supportingContent = {
                    Text(text = "Total Payers: ${transaction.payers.size}; Total Payees: ${transaction.payees.size}", color = MaterialTheme.colorScheme.onPrimaryContainer)
                },
                trailingContent = {
                    Box(modifier = Modifier
                        .shadow(7.dp, shape = RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(32.dp))
                        .background(color = MaterialTheme.colorScheme.background)
                        , contentAlignment = Alignment.Center){
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(8.dp,8.dp)) {
                            Text(
                                text = formattedCurrencyAmount,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }
                })
        }
    }
}