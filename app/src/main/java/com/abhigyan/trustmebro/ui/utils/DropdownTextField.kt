package com.abhigyan.trustmebro.ui.utils

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropdownTextField(modifier: Modifier=Modifier,options: List<T>,initialValue: T?, onSelected: (T)-> Unit) {
    var expandDropdown by remember {
        mutableStateOf(false)
    }
    var itemSelected by remember {
        mutableStateOf(initialValue)
    }

    ExposedDropdownMenuBox(expanded = expandDropdown, onExpandedChange = { expandDropdown=it }, modifier = modifier) {
        OutlinedTextField(value = itemSelected.toString(),
            onValueChange = {},
            readOnly = true,
            textStyle = MaterialTheme.typography.bodyMedium,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandDropdown) },
            colors = TextFieldDefaults.colors(),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(expanded = expandDropdown, onDismissRequest = { expandDropdown=false}) {
            options.forEach { it->
                DropdownMenuItem(text = { Text(text = it.toString()) }, onClick = {
                    itemSelected=it
                    expandDropdown=false
                    onSelected(it)
                })
            }
        }
    }
}