package com.example.bookbank.views.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookbank.ui.theme.buttonColor

@Composable
fun CategoriesTab(items:List<String>,onClick:(Int)->Unit) {
    val scrollState = rememberScrollState()
    var selectedIndex by remember { mutableIntStateOf(0) }

    Row(
        modifier = Modifier
            .horizontalScroll(scrollState)
            .fillMaxWidth()
            .wrapContentWidth()
    ) {
        // List of tabs

        items.forEachIndexed {index, label ->
            Button(
                onClick = {
                    selectedIndex = index
                    onClick(index)
                          },
                modifier = Modifier.padding(end = 8.dp),
                border = BorderStroke(1.dp, if (index == selectedIndex) buttonColor else Color.Gray),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = label,
                    fontSize = 16.sp,
                    color = if (index == selectedIndex) buttonColor else Color.Gray
                )
            }
        }
    }
}
