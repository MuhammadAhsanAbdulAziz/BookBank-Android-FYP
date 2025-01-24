package com.example.bookbank.views.donate

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.bookbank.util.Dimens.MediumPadding1
import com.example.bookbank.util.Dimens.SmallPadding
import com.example.bookbank.viewmodels.UtilViewModel
import com.example.bookbank.views.common.CategoriesTab
import com.example.bookbank.views.donate.common.DonateBooks
import com.example.bookbank.views.donate.common.DonateMoney

@Composable
fun DonateScreen(modifier: Modifier = Modifier,utilViewModel: UtilViewModel) {
    val tabs = listOf("Books","Money")
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
                DonateBooks(utilViewModel = utilViewModel)
            } else {
                DonateMoney()
            }


        }
    }
}