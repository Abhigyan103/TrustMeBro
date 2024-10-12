package com.abhigyan.trustmebro.ui.utils

import DecimalVisualTransformation
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Currency

@Composable
fun CurrencyInput(modifier: Modifier= Modifier,onValueChanged: (Double)->Unit) {
    var amount by remember {
        mutableStateOf("")
    }
    OutlinedTextField(value = amount, onValueChange = {it->
        if (it.matches("^\\d*(\\.\\d{0,2})?$".toRegex())) {
            amount = it  // Only allow up to 2 decimal places
        }
        var amountNum =0.0
        if(amount!="") {
            if (amount.last()!='.'){
                amountNum = amount.toDouble()
            }else {
                amountNum=amount.subSequence(0,amount.length-2).toString().toDouble()
            }
        }
        onValueChanged(amountNum)
    }, shape = RoundedCornerShape(16.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal
        ),
        singleLine = true,
        leadingIcon = {
            Text(text = Currency.getInstance("INR").symbol.trim())
        },
        visualTransformation = DecimalVisualTransformation(),
        placeholder = { Text(text = "0.0")},
        modifier = modifier
    )
}