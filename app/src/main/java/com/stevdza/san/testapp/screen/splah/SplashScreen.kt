package com.stevdza.san.testapp.screen.splah

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.stevdza.san.testApp.R
import com.stevdza.san.testapp.navigation.Screen
import com.stevdza.san.testapp.ui.Constants.APP_ID
import io.realm.kotlin.mongodb.App


@Composable
fun Splash(navController: NavHostController,
) {

    val context = LocalContext.current
//    val finOrSec = FinOrSec(context)
//    val getFinOrSecKey = finOrSec.getKey.collectAsState(initial = "")
    val getFinOrSecKey by remember { mutableStateOf("") }


    val degrees = remember { Animatable(0f) }

    val app: App = App.create(APP_ID) // Replace this with your App ID

    LaunchedEffect(key1 = true) {
        degrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
        navController.popBackStack()
        if (
//            getFinOrSecKey == ""
            app.currentUser == null
        ) {
            navController.navigate(Screen.SignUp.route)
        } else {
            navController.navigate(Screen.PinLogin.route)
        }
//        Log.e(TAG, "Splash: ${getFinOrSecKey}", )
    }

    SplashUI(degrees = degrees.value)
}

@Composable
fun SplashUI(degrees: Float) {
    val modifier = if (isSystemInDarkTheme()) {
        Modifier.background(Color.Black)
    } else {
        Modifier.background(Color.White)
    }
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.rotate(degrees = degrees),
            painter = painterResource(id = R.drawable.empty_image),
            contentDescription = "o"

        )
    }

}
