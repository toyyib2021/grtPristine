package com.stevdza.san.testapp.navigation


const val NAVKEY = "order_iD"
sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object SignUp : Screen("sign_up_screen")
    object SignIn : Screen("sign_in_screen")
    object Authentication : Screen(route = "authentication_screen")
    object PinLogin : Screen("pin_login_screen")
    object Home : Screen(route = "home_screen")
    object Account : Screen(route = "account_screen")


    object ProfileDetails : Screen("profile_details_screen/{$NAVKEY}"){
        fun memberProfileID(key: String): String{
            return "profile_details_screen/$key"
        }
    }

    object Statement : Screen("statement_screen/{$NAVKEY}"){
        fun memberStatementID(key: String): String{
            return "statement_screen/$key"
        }
    }

    object PinCreation : Screen("pin_creation_screen/{$NAVKEY}"){
        fun secOrFinKey(key: String): String{
            return "pin_creation_screen/$key"
        }
    }


    object MemberDetail : Screen("order_detail_screen/{$NAVKEY}"){
        fun passArgument2(orderId: String): String{
            return "order_detail_screen/$orderId"
        }
    }

    object PlaceOrder : Screen("order_place_screen/{$NAVKEY}"){
        fun passArgument(order_iD: String): String{
            return "order_place_screen/$order_iD"
        }
    }



}