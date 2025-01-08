package com.example.bookbank.views.request

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.bookbank.util.Dimens.MediumPadding1
import com.example.bookbank.util.Dimens.SmallPadding
import com.example.bookbank.views.common.CategoriesTab
import com.example.bookbank.views.donate.common.DonateMoney
import com.example.bookbank.views.request.common.RequestBooks
import com.example.bookbank.views.request.common.ReturnBooks

@Composable
fun RequestScreen(modifier: Modifier = Modifier) {
    val tabs = listOf("Requests","Return")
    var selectedTab by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(SmallPadding)
    ) {

        Column {


            CategoriesTab(tabs){
                selectedTab = it
            }

            Spacer(Modifier.height(MediumPadding1))

            if(selectedTab == 0) {
                RequestBooks()
            } else {
                ReturnBooks()
            }


        }
    }
}