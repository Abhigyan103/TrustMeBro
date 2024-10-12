package com.abhigyan.trustmebro.ui.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.abhigyan.trustmebro.calculator.types.Transaction
import java.text.NumberFormat

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TransactionItem(transaction: Transaction, onClick: () -> Unit, onHold: () -> Unit, modifier: Modifier = Modifier){
    val formattedCurrencyAmount = NumberFormat.getCurrencyInstance().format(transaction.totalCost)
    ListItem(modifier = modifier
        .padding(8.dp)
        .shadow(5.dp, shape = RoundedCornerShape(16.dp))
        .clip(RoundedCornerShape(16.dp))
        .combinedClickable(onClick = { onClick() }, onLongClick = { onHold() }),
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