package com.abhigyan.trustmebro.ui.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar(modifier: Modifier = Modifier, title: String, onBack: (()->Unit)?=null)  {
    TopAppBar(title = { Text(text = title, fontWeight = FontWeight.Bold) }, navigationIcon = {
        if(onBack!=null){
            IconButton(onClick = { onBack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Sharp.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    })
}