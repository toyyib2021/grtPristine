package com.stevdza.san.testapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.stevdza.san.testapp.ui.Constants.AUTH_GRAPH_ROUTE
import com.stevdza.san.testapp.ui.Constants.ROOT_GRAPH_ROUTE

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AUTH_GRAPH_ROUTE,
        route = ROOT_GRAPH_ROUTE
    ) {
        authRoute(navController)
        homeRoute(navController)
    }
}



