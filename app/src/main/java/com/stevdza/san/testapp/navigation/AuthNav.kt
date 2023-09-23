package com.stevdza.san.testapp.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.stevdza.san.testapp.screen.access.PinCreation
import com.stevdza.san.testapp.screen.access.PinLoginSecAndFin
import com.stevdza.san.testapp.screen.auth.AuthenticationScreen
import com.stevdza.san.testapp.screen.auth.AuthenticationViewModel
import com.stevdza.san.testapp.screen.auth.SignUpAndSignIn
import com.stevdza.san.testapp.screen.splah.Splash
import com.stevdza.san.testapp.ui.Constants.AUTH_GRAPH_ROUTE
import com.stevdza.san.testapp.ui.Constants.DASHBOARD_GRAPH_ROUTE
import com.stevdzasan.messagebar.rememberMessageBarState
import com.stevdzasan.onetap.rememberOneTapSignInState


@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.authRoute(
    navController: NavHostController
) {


    navigation(
        startDestination = Screen.Splash.route,
        route = AUTH_GRAPH_ROUTE
    ){


        composable(route = Screen.Splash.route) {
            Splash(navController = navController)
        }

        composable(route = Screen.Authentication.route){
            val viewModel: AuthenticationViewModel = viewModel()
            val authenticated by viewModel.authenticated
            val loadingState by viewModel.loadingState
            val oneTapState = rememberOneTapSignInState()
            val messageBarState = rememberMessageBarState()

            AuthenticationScreen(
                authenticated = authenticated,
                loadingState = loadingState,
                oneTapState = oneTapState,
                messageBarState = messageBarState,
                onButtonClicked = {
                    oneTapState.open()
                    viewModel.setLoading(true)
                },
                onSuccessfulSignIn = { tokenId ->
                    viewModel.signInWithMongoAtlas(
                        tokenId = tokenId,
                        onSuccess = {
                            messageBarState.addSuccess("Successfully Authenticated!")
                            viewModel.setLoading(false)
                        },
                        onError = {
                            messageBarState.addError(it)
                            viewModel.setLoading(false)
                        }
                    )
                },
                onDialogDismissed = { message ->
                    messageBarState.addError(Exception(message))
                    viewModel.setLoading(false)
                },
                navigateToHome = { navController.navigate(Screen.PinCreation.route) }
            )
        }

        composable(route = Screen.SignUp.route) {
            SignUpAndSignIn(
                navToPinCreation = {navController.navigate(Screen.PinCreation.route)},
                navToGoogleAuthScreen = {navController.navigate(Screen.Authentication.route)}
            )
        }

        composable(
            route= Screen.PinCreation.route,
        ){
            PinCreation(navToHome = {navController.navigate(DASHBOARD_GRAPH_ROUTE)})

        }

        composable(route = Screen.PinLogin.route) {
            PinLoginSecAndFin (
                navToDashboard = {navController.navigate(DASHBOARD_GRAPH_ROUTE)}
            )
        }

//            // Login
//        composable(route = NavigationRoutes.Unauthenticated.Login.route) {
//            LoginScreen(
//                onNavigateToRegistration = {
//                    navController.navigate(route = NavigationRoutes.Unauthenticated.Registration.route) },
//                onNavigateToForgotPassword = {},
//                onNavigateToAuthenticatedRoute = {
//                    navController.navigate(route = Screen.Activation.route) {
//                        popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
//                            inclusive = true
//                        }
//                    }
//                }
//            )
//        }
//        // Registration
//        composable(route = NavigationRoutes.Unauthenticated.Registration.route) {
//            RegistrationScreen(
//                onNavigateBack = {
//                        navController.navigateUp()
//                },
//                onNavigateToAuthenticatedRoute = {
//                        navController.navigate(route = Screen.PinCreation.route) {
//                            popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
//                                inclusive = true
//                            }
//                        }
//                },
//                navigateToGoogleOnTapSignIn = {navController.navigate(Screen.Authentication.route)}
//            )
//        }
//
//
//        composable(route = Screen.PinCreation.route){
//            PinCreationScreen(
//                navToDashBoard = { navController.navigate((DASHBOARD_GRAPH_ROUTE)) }
//            )
//        }
//
//        composable(route = Screen.PinLogin.route){
//            PinScreen(
//                navPinCreationScreen = { navController.navigate(Screen.PinCreation.route) },
//                navDashboardScreen = {navController.navigate(DASHBOARD_GRAPH_ROUTE)}
//            )
//        }

    }
}