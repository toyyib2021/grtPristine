package com.stevdza.san.testapp.screen.auth


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stevdza.san.testapp.ui.theme.RoyalHealht
import com.stevdza.san.testapp.util.isInternetAvailable
import io.realm.kotlin.exceptions.RealmException
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import kotlinx.coroutines.launch
import com.stevdza.san.testApp.R
import com.stevdza.san.testapp.ui.Constants.APP_ID


@Composable
fun SignUpAndSignIn(navToPinCreation:()-> Unit, navToGoogleAuthScreen:()-> Unit){
    Column {

        val authVm: AuthenticationViewModel= viewModel()
        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        var registrationState by remember { mutableStateOf("") }
        var snackbarMessage by remember { mutableStateOf("") }
        val loadingSnackbarMessage by remember { mutableStateOf("") }
        var snackbarVisible by remember { mutableStateOf(false) }
        val loadingSnackbarVisible by remember { mutableStateOf(false) }

        var signUpEmail by remember { mutableStateOf("") }
        var signUpPassword by remember { mutableStateOf("") }
        var signUpConfirmPassword by remember { mutableStateOf("") }

        var signInEmail by remember { mutableStateOf("") }
        var signInPassword by remember { mutableStateOf("") }
//        var signUpConfirmPassword by remember { mutableStateOf("") }

        val emailPattern = Regex("""^[A-Za-z0-9+_.-]+@(.+)\..+$""")


        if(snackbarVisible){
            Snackbar(
                modifier=Modifier.padding(horizontal = 15.dp),
                backgroundColor = RoyalHealht,
                contentColor = Color.White,
                action = {
                    Text(modifier = Modifier.clickable {
                        snackbarVisible=false
                    }, text = "Dismiss", color = Color.White, fontSize = 10.sp)
                }) {
                Text(text = snackbarMessage, color = Color.White, fontSize = 12.sp )
            }
        }
        if(loadingSnackbarVisible){
            Snackbar(
                modifier=Modifier.padding(horizontal = 15.dp),
                backgroundColor = RoyalHealht,
                contentColor = Color.White,
                action = {
//                    Text(modifier = Modifier.clickable {
//                        snackbarVisible=false
//                    }, text = "Dismiss", color = Color.White, fontSize = 10.sp)
                }) {
                Text(text = loadingSnackbarMessage, color = Color.White, fontSize = 12.sp )
            }
        }


        if (registrationState== "SignIn"){
            SignUpComponent(
                onSignUpClick = {
                    if (isInternetAvailable(context)){
                        if (signInEmail.isNotEmpty() && signInPassword.isNotEmpty() ){
                                snackbarMessage = "Loading....."
                                snackbarVisible=true
                                authVm.emailPasswordLogin(
                                    email = signInEmail,
                                    password = signInPassword,
                                    onSuccess = {
                                        snackbarMessage = "Sign In Successful"
                                        snackbarVisible=false
                                        authVm.setLoading(false)
                                        navToPinCreation()
                                    },
                                    onError = {
                                        snackbarMessage = "Email Or Password Not Correct"
//                                        snackbarMessage = it.message.toString()
                                        snackbarVisible=true
                                    }
                                )

                        } else{
                            snackbarMessage = "Fill the empty fields"
                            snackbarVisible=true
                        }

                    }else{
                        snackbarMessage = "Internet Connected Not Available"
                        snackbarVisible=true
                    }
                },
                onGoogleSignUpClick = {navToGoogleAuthScreen()},
                onAlreadyHaveAccountClick = { registrationState=""},
                signUpEmail=signInEmail,
                onSignUpEmailChange={signInEmail=it},
                signUpPassword=signInPassword,
                onSignUpPasswordChange={signInPassword=it},
                signUpConfirmPassword=signInPassword,
                onSignUpConfirmPChange={signInPassword = it },
                registrationState=registrationState,
                alreadyHaveAccountText = "Sign Up for an Account"
            )

        }else{
            SignUpComponent(
                onSignUpClick = {
                    if (isInternetAvailable(context)){
                        if (signUpEmail.isNotEmpty() && signUpPassword.isNotEmpty() && signUpConfirmPassword.isNotEmpty() ){
                            val isEmailValid = emailPattern.matches(signUpEmail)
                            if (isEmailValid){
                                if (signUpPassword.length >= 7){
                                    if (signUpPassword ==  signUpConfirmPassword){
                                        snackbarMessage = "Loading....."
                                        snackbarVisible=true
                                        authVm.emailPasswordAuth(
                                            email = signUpEmail,
                                            password = signUpConfirmPassword,
                                            onSuccess = {
                                                snackbarMessage = "Sign Up Successful"
                                                snackbarVisible=false
                                                authVm.setLoading(false)
                                                navToPinCreation()
                                            },
                                            onError = {
                                                snackbarMessage = "Something went wrong"
//                                        snackbarMessage = it.message.toString()
                                                snackbarVisible=false
                                            }
                                        )

                                    }else{
                                        snackbarMessage = "password does not march"
                                        snackbarVisible=true
                                    }
                                }else{
                                    snackbarMessage = "password Should be more than 7 Words or number"
                                    snackbarVisible=true
                                }
                            }else{
                                snackbarMessage = "input a valid email"
                                snackbarVisible=true
                            }

                        }else{
                            snackbarMessage = "Fill the empty fields"
                            snackbarVisible=true
                        }

                    }else{
                        snackbarMessage = "Internet Connected Not Available"
                        snackbarVisible=true
                    }
                },
                onGoogleSignUpClick = {navToGoogleAuthScreen()},
                onAlreadyHaveAccountClick = { registrationState="SignIn"},
                signUpEmail=signUpEmail,
                onSignUpEmailChange={signUpEmail=it},
                signUpPassword=signUpPassword,
                onSignUpPasswordChange={signUpPassword=it},
                signUpConfirmPassword=signUpConfirmPassword,
                onSignUpConfirmPChange={signUpConfirmPassword=it},
                registrationState=registrationState,
                alreadyHaveAccountText = "Already Have Account Click Here"
            )
        }
    }
}




@Composable
fun SignUpComponent(
    onSignUpClick:()->Unit,
    onGoogleSignUpClick:()->Unit,
    onAlreadyHaveAccountClick:()->Unit,
    signUpEmail: String,
    onSignUpEmailChange:(String)->Unit,
    signUpPassword: String,
    onSignUpPasswordChange:(String)->Unit,
    signUpConfirmPassword: String,
    onSignUpConfirmPChange:(String)->Unit,
    registrationState: String,
    alreadyHaveAccountText: String
){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var passwordVisibility by remember { mutableStateOf(true) }
        var confirmPasswordVisibility by remember { mutableStateOf(true) }
        LazyColumn {

                item {
                    Column(
                        modifier = Modifier.padding(10.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Column(modifier=Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(painter = painterResource(id = R.drawable.grt_pristine_logo) , contentDescription = "empty_image")
                        }
                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(modifier=Modifier.padding(start = 20.dp) ,text = "Email", color = RoyalHealht)
                        OutlinedTextField(modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                            value = signUpEmail, onValueChange = { onSignUpEmailChange(it) } ,
                            colors = androidx.compose.material.TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = RoyalHealht, cursorColor = RoyalHealht,
                                backgroundColor = Color.White,  unfocusedIndicatorColor = RoyalHealht
                            )
                        )

                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(modifier=Modifier.padding(start = 20.dp) ,text = "Password", color = RoyalHealht)
                        OutlinedTextField(modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                            value = signUpPassword, onValueChange = { onSignUpPasswordChange(it) },
                            colors = androidx.compose.material.TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = RoyalHealht, cursorColor = RoyalHealht,
                                backgroundColor = Color.White,  unfocusedIndicatorColor = RoyalHealht
                            ),
                            trailingIcon = {
                                IconButton(onClick = {
                                    passwordVisibility = !passwordVisibility
                                }) {
                                    Icon(
                                        imageVector = if (passwordVisibility) Icons.Filled.VisibilityOff else Icons.Default.Visibility,
                                        contentDescription = "",
                                        tint = Color.Black
                                    )
                                }
                            },
                            visualTransformation = if (passwordVisibility) PasswordVisualTransformation() else VisualTransformation.None,
                        )
                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(modifier = Modifier.padding(start = 20.dp) ,text = "Confirm Password", color = RoyalHealht)
                        if (registrationState == ""){
                            OutlinedTextField(modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                                value = signUpConfirmPassword, onValueChange = { onSignUpConfirmPChange(it) } ,
                                colors = androidx.compose.material.TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = RoyalHealht, cursorColor = RoyalHealht,
                                    backgroundColor = Color.White, unfocusedIndicatorColor = RoyalHealht
                                ),
                                trailingIcon = {
                                    IconButton(onClick = {
                                        confirmPasswordVisibility = !confirmPasswordVisibility
                                    }) {
                                        Icon(
                                            imageVector = if (confirmPasswordVisibility) Icons.Filled.VisibilityOff else Icons.Default.Visibility,
                                            contentDescription = "",
                                            tint = Color.Black
                                        )
                                    }
                                },
                                visualTransformation = if (confirmPasswordVisibility) PasswordVisualTransformation() else VisualTransformation.None,
                            )
                        }

                        Spacer(modifier = Modifier.padding(10.dp))
                        OutlinedButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            onClick = { onSignUpClick() }) {
                            Text(modifier = Modifier.padding(10.dp), text = "Sign Up", color = RoyalHealht)
                        }
                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(
                            text = "Sign Up With Google",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onGoogleSignUpClick() },
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.padding(20.dp))
                        Text(
                            text = alreadyHaveAccountText,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onAlreadyHaveAccountClick() },
                            textAlign = TextAlign.Center,
                            fontSize = 12.sp,
                            color = RoyalHealht
                        )
                    }

                }


        }
    }
}


@Composable
fun SignInComponent(
    onSinginClick:()->Unit,
    onGoogleSignUpClick:()->Unit,
    doNotHaveAccountClick:()->Unit,

){
    var email by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var confirmPasswordVisibility by remember { mutableStateOf(true) }
        LazyColumn {

                item {
                    Column(
                        modifier = Modifier.padding(10.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(painter = painterResource(id = R.drawable.grt_pristine_logo) , contentDescription = "empty_image")
                        }
                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(modifier=Modifier.padding(start = 20.dp) ,text = "Email", color = RoyalHealht)
                        OutlinedTextField(modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                            value = email, onValueChange = { email = it } ,
                            colors = androidx.compose.material.TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = RoyalHealht, cursorColor = RoyalHealht,
                                backgroundColor = Color.White, unfocusedIndicatorColor = RoyalHealht
                            ),
                            )
                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(modifier=Modifier.padding(start = 20.dp) ,text = "Password", color = RoyalHealht)
                        OutlinedTextField(modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                            value = email, onValueChange = { email = it },
                            colors = androidx.compose.material.TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = RoyalHealht, cursorColor = RoyalHealht,
                                backgroundColor = Color.White, unfocusedIndicatorColor = RoyalHealht
                            ),
                            trailingIcon = {
                                IconButton(onClick = {
                                    confirmPasswordVisibility = !confirmPasswordVisibility
                                }) {
                                    Icon(
                                        imageVector = if (confirmPasswordVisibility) Icons.Filled.VisibilityOff else Icons.Default.Visibility,
                                        contentDescription = "",
                                        tint = Color.Black
                                    )
                                }
                            },
                            visualTransformation = if (confirmPasswordVisibility) PasswordVisualTransformation() else VisualTransformation.None,

                            )
                        Spacer(modifier = Modifier.padding(10.dp))

                        OutlinedButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            onClick = { onSinginClick() },
                            colors = ButtonDefaults.buttonColors(
                                contentColor = RoyalHealht,
                                backgroundColor = Color.White

                            )
                        ) {
                            Text(modifier = Modifier.padding(10.dp), text = "Sign In")
                        }
                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(
                            text = "Sign Up With Google",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onGoogleSignUpClick() },
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.padding(20.dp))
                        Text(
                            text = "Do not have an account click here",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { doNotHaveAccountClick() },
                            textAlign = TextAlign.Center,
                            fontSize = 12.sp,
                            color = RoyalHealht
                        )
                    }

                }

        }
    }
}






