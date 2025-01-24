package com.example.bookbank.views.profile

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bookbank.R
import com.example.bookbank.ui.theme.blueColor
import com.example.bookbank.ui.theme.buttonColor
import com.example.bookbank.ui.theme.interBold
import com.example.bookbank.ui.theme.lightAppColor
import com.example.bookbank.util.Dimens.MediumPadding3
import com.example.bookbank.util.Dimens.SmallPadding
import com.example.bookbank.util.Dimens.XSmallPadding
import com.example.bookbank.util.Dimens.XXSmallPadding
import com.example.bookbank.util.navgraph.Route
import com.example.bookbank.viewmodels.MainViewModel
import com.example.bookbank.views.common.CustomButton

@Composable
fun ProfileScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
    modifier: Modifier = Modifier
) {

    var isLoading by remember { mutableStateOf(false) }

    val userData by mainViewModel.readUserData().collectAsState(initial = null)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(lightAppColor)
    ) {

        Column {
            if (userData != null){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(SmallPadding)
                ) {

                    Image(
                        painter = painterResource(R.drawable.placeholder),
                        contentDescription = null,
                        modifier = Modifier.size(150.dp)
                    )

                    Spacer(Modifier.height(XXSmallPadding))

                    Text(
                        userData!!.name, style = TextStyle(
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
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .border(1.dp, buttonColor, RoundedCornerShape(7.dp))
                    ) {
                        navController.navigate(Route.UpdateProfileScreen.route)
                    }

                }

                Spacer(Modifier.height(MediumPadding3))

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White, RoundedCornerShape(topEnd = 40.dp, topStart = 40.dp))
                ) {

                    LazyColumn(modifier.padding(MediumPadding3)) {
                        item {
                            Text(
                                "Father Name", style = TextStyle(
                                    fontSize = 18.sp, color = buttonColor, fontFamily = interBold
                                )
                            )

                            Spacer(modifier.height(XSmallPadding))

                            Text(
                                userData!!.father_name, style = TextStyle(
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
                                userData!!.student_cnic, style = TextStyle(
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
                                userData!!.address, style = TextStyle(
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
                                userData!!.email, style = TextStyle(
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
                                userData!!.mobile, style = TextStyle(
                                    fontSize = 17.sp, color = blueColor, fontFamily = interBold
                                )
                            )

                            Spacer(modifier.height(SmallPadding))

                            CustomButton(
                                text = "Log out",
                                color = buttonColor,
                                textSize = 15,
                                textColor = Color.White,
                                isLoading = isLoading,
                                radius = 8,
                                height = 50,
                                modifier = Modifier
                            ) {
                                isLoading = true
                                mainViewModel.saveUserData(null)
                                mainViewModel.saveUserToken("")
                                isLoading = false
                            }

                        }
                    }

                }
            } else {
                CircularProgressIndicator(color = buttonColor)
            }
        }

    }

}