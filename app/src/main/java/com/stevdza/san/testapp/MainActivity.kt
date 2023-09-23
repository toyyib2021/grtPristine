package com.stevdza.san.testapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import com.stevdza.san.testapp.navigation.SetupNavGraph
import com.stevdza.san.testapp.ui.theme.MongoDemoTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MongoDemoTheme {

                    val navController = rememberNavController()
                    SetupNavGraph(
                        navController = navController
                    )
            }
        }
    }
}

