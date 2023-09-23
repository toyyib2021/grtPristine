package com.stevdza.san.testapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.stevdza.san.testapp.screen.account.Account
import com.stevdza.san.testapp.screen.account.StatementForMembers
import com.stevdza.san.testapp.screen.home.Dashboard
import com.stevdza.san.testapp.screen.memberDetails.MemberDetailScreen
import com.stevdza.san.testapp.screen.profileDetails.ProfileScreen

import com.stevdza.san.testapp.ui.Constants.DASHBOARD_GRAPH_ROUTE

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.homeRoute(
    navController: NavHostController
) {

    navigation(
        startDestination = Screen.Home.route,
        route = DASHBOARD_GRAPH_ROUTE
    ) {

        composable(
            route = Screen.Home.route
        ) {

            Dashboard(
                navToProfileScreen = { navController.navigate(Screen.MemberDetail.passArgument2(it)) },
                navToAccountScreen = { navController.navigate(Screen.Account.route) },
                onSwitchAccountClick = {navController.navigate(Screen.PinLogin.route)}
            )
        }

        composable(
            route = Screen.ProfileDetails.route,
            arguments = listOf(
                navArgument(NAVKEY) {
                    type = NavType.StringType
                })
        ) {
            val memberId = it.arguments?.getString(NAVKEY) ?: ""
            ProfileScreen(onBackClick = { navController.navigate(Screen.MemberDetail.passArgument2(memberId)) }, memberId = memberId)
        }

        composable(route = Screen.Account.route){
            Account(onBackArrowClick = {navController.navigate(Screen.Home.route)})
        }

        composable(
            route = Screen.Statement.route,
            arguments = listOf(
                navArgument(NAVKEY) {
                    type = NavType.StringType
                })
        ){
            val memberId = it.arguments?.getString(NAVKEY) ?: ""
            StatementForMembers(
                memberId = memberId,
                onBackClick = { navController.navigate(Screen.Home.route) }
            )
        }


        composable(
            route= Screen.MemberDetail.route,
            arguments = listOf(
                navArgument(NAVKEY) {
                    type = NavType.StringType
                })
        ){
            val memberId = it.arguments?.getString(NAVKEY) ?: ""
            MemberDetailScreen(
                memberId = memberId,
                onBackClick = { navController.navigate(Screen.Home.route) },
                navToStatement = {
                    navController.navigate(
                        Screen.Statement.memberStatementID(it)
                    )},
                navToProfileScreen = {
                    navController.navigate(Screen.ProfileDetails.memberProfileID(it))
                }
            )
        }
    }


}