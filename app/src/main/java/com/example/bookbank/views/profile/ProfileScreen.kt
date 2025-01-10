package com.example.bookbank.views.profile

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookbank.R
import com.example.bookbank.ui.theme.appColor
import com.example.bookbank.ui.theme.blueColor
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.interBold
import com.example.bookbank.ui.theme.interRegular
import com.example.bookbank.ui.theme.lightAppColor
import com.example.bookbank.util.Dimens.MediumPadding1
import com.example.bookbank.util.Dimens.MediumPadding2
import com.example.bookbank.util.Dimens.MediumPadding3
import com.example.bookbank.util.Dimens.SmallPadding
import com.example.bookbank.util.Dimens.XSmallPadding
import com.example.bookbank.util.Dimens.XXSmallPadding
import com.example.bookbank.views.common.CustomButton

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(lightAppColor)
    ) {

        Column {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
                    .padding(SmallPadding)
            ) {

                Image(
                    painter = painterResource(R.drawable.placeholder),
                    contentDescription = null,
                    modifier = Modifier.size(150.dp)
                )

                Spacer(Modifier.height(XXSmallPadding))

                Text(
                    "Tayyab Ayub", style = TextStyle(
                        fontSize = 18.sp, color = buttonColor, fontFamily = interBold
                    )
                )

                Spacer(Modifier.height(XSmallPadding))

                CustomButton(
                    text = "Edit Profile",
                    color = Color.Transparent,
                    textSize = 16,
                    textColor = buttonColor,
                    isLoading = false,
                    radius = 7,
                    height = 40,
                    modifier = Modifier.fillMaxWidth(0.4f).border(1.dp, buttonColor, RoundedCornerShape(7.dp))
                ) {

                }

            }

            Spacer(Modifier.height(MediumPadding3))

            Column(
                modifier = Modifier.fillMaxSize()
                    .background(Color.White, RoundedCornerShape(topEnd = 40.dp, topStart = 40.dp))
            ) {

                LazyColumn(modifier.padding(MediumPadding3)) {
                    item{
                        Text(
                            "Father Name", style = TextStyle(
                                fontSize = 18.sp, color = buttonColor, fontFamily = interBold
                            )
                        )

                        Spacer(modifier.height(XSmallPadding))

                        Text(
                            "Talha ahmed", style = TextStyle(
                                fontSize = 17.sp, color = blueColor, fontFamily = interBold
                            )
                        )

                        Spacer(modifier.height(SmallPadding))

                        Text(
                            "CNIC", style = TextStyle(
                                fontSize = 18.sp, color = buttonColor, fontFamily = interBold
                            )
                        )

                        Spacer(modifier.height(XSmallPadding))

                        Text(
                            "42000-7025211103", style = TextStyle(
                                fontSize = 17.sp, color = blueColor, fontFamily = interBold
                            )
                        )

                        Spacer(modifier.height(SmallPadding))

                        Text(
                            "Address", style = TextStyle(
                                fontSize = 18.sp, color = buttonColor, fontFamily = interBold
                            )
                        )

                        Spacer(modifier.height(XSmallPadding))

                        Text(
                            "FB Area block 18", style = TextStyle(
                                fontSize = 17.sp, color = blueColor, fontFamily = interBold
                            )
                        )

                        Spacer(modifier.height(SmallPadding))
                        Text(
                            "Email", style = TextStyle(
                                fontSize = 18.sp, color = buttonColor, fontFamily = interBold
                            )
                        )

                        Spacer(modifier.height(XSmallPadding))

                        Text(
                            "TayyabAyyub@gmail.com", style = TextStyle(
                                fontSize = 17.sp, color = blueColor, fontFamily = interBold
                            )
                        )

                        Spacer(modifier.height(SmallPadding))
                        Text(
                            "Mobile", style = TextStyle(
                                fontSize = 18.sp, color = buttonColor, fontFamily = interBold
                            )
                        )

                        Spacer(modifier.height(XSmallPadding))

                        Text(
                            "0332-09249312", style = TextStyle(
                                fontSize = 17.sp, color = blueColor, fontFamily = interBold
                            )
                        )
                        
                    }
                }

            }
        }

    }

}