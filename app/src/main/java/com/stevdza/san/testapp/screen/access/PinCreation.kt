package com.stevdza.san.testapp.screen.access


import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stevdza.san.testapp.ui.theme.RoyalHealht
import com.stevdza.san.testApp.R
import com.stevdza.san.testapp.dataMain.dataStore.FinOrSec
import com.stevdza.san.testapp.screen.HomeViewModel
import com.stevdza.san.testapp.util.isInternetAvailable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun PinCreation(navToHome: ()-> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var newSectaryAccessKey by remember { mutableStateOf("") }
    var defaultSectaryPin by remember { mutableStateOf("") }
    var confirmSectaryAccessKey by remember { mutableStateOf("") }

    var newAccountantAccessKey by remember { mutableStateOf("") }
    var defaultAccountantPin by remember { mutableStateOf("") }
    var confirmAccountantAccessKey by remember { mutableStateOf("") }


//    var dbPin by remember { mutableStateOf("12345") }
    var snackbarMessage by remember { mutableStateOf("") }
    var snackbarVisible by remember { mutableStateOf(false) }
    var secOrAcctState by remember { mutableStateOf(false) }

    val finOrSec = FinOrSec(context)
    val getFinOrSecKey = finOrSec.getKey.collectAsState(initial = "")

    val homeVM : HomeViewModel = viewModel()
//    val na = homeVM.age.value

//    Log.e(ContentValues.TAG, "data: $data", )
    LaunchedEffect(key1 = snackbarVisible){
        if (snackbarVisible){
            delay(3000)
            snackbarVisible = false
        }
    }

    var adminResetState by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = adminResetState ){
        if(homeVM.adminData.value.isNotEmpty()){
            homeVM.getAdminWIthID()
        }
    }


    val admin = homeVM.admin.value
    Log.e(TAG, "finPin: ${homeVM.finPin.value}", )
    Log.e(TAG, "secPin: ${homeVM.secPin.value}", )
    Log.e(TAG, "adminFee: ${homeVM.adminFee.value}", )
    Log.e(TAG, "shareUnitValue: ${homeVM.shareUnitValue.value}", )




    Log.e(TAG, "getFinOrSecKey: ${getFinOrSecKey.value}", )
    if (!secOrAcctState){
        admin?.let {
            homeVM.finPin.value = it.finPin
//            homeVM.secPin.value = it.secPin
            homeVM.adminFee.value = it.adminFee
            homeVM.shareUnitValue.value = it.sharesUnitValue
        }
        Column {
            if(snackbarVisible){
                Snackbar(
                    modifier=Modifier.padding(horizontal = 15.dp),
                    backgroundColor = RoyalHealht,
                    contentColor = Color.White,
                    action = {
                        Text(modifier = Modifier.clickable {
//                                onDismissClick()
                            snackbarVisible=false
                        }, text = "Dismiss", color = Color.White, fontSize = 10.sp)
                    }) {
                    Text(text = snackbarMessage, color = Color.White, fontSize = 12.sp )
                }
            }
            SecretaryAndAccountPinCreationComponent(
                onSectaryClick = {
                    if (isInternetAvailable(context)){
                        if (newSectaryAccessKey.length >= 6){
                            homeVM.updateAdmin(
                                onError = {
                                    snackbarMessage = "Update Not Successful Try again"
                                    snackbarVisible=true
                                },
                                onSuccess = {
                                    adminResetState = !adminResetState
                                    navToHome()
                                }
                            )
                            scope.launch {
                                finOrSec.saveKey("sec")
                            }
                        }
                        else{
                            snackbarMessage = "Access code can't be more that 6 digits"
                            snackbarVisible=true
                        }

                    }else{
                        snackbarMessage = "Internet Connected Not Available"
                        snackbarVisible=true
                    }
                },
                newSectaryAccessKey = newSectaryAccessKey,
                onNewSectaryAccessKeyChange = {
                    newSectaryAccessKey=it.filter { it.isDigit() }
                    homeVM.secPin.value = it
                },
                defaultSectaryPin = defaultSectaryPin,
                onDefaultSectaryPinChangeChange = {defaultSectaryPin=it.filter { it.isDigit() }},
                confirmSectaryAccessKey = confirmSectaryAccessKey,
                onConfirmSectaryAccessKeyChange = {confirmSectaryAccessKey=it.filter { it.isDigit() }},
                errorMsgForEmptyTextField ={
                    snackbarMessage = "Fill the empty fields"
                    snackbarVisible=true },
                newPinAndOldPinNotDesame ={
                    snackbarMessage = "new pin and confirm pin does not march"
                    snackbarVisible=true
                },
                dbPin = admin?.secPin ?: "",
                wrongDefualtPin = {
                    snackbarMessage = "you entered a wrong previous pin"
                    snackbarVisible=true
                },
                secOrAcctState = secOrAcctState,
                onAccountClick = { secOrAcctState = true},
                onSecertaryClick = { secOrAcctState=false},
                title = "Reset Secretary Passcode"
            )
        }
    }else{
        Column {
            admin?.let {
//                homeVM.finPin.value = it.finPin
                homeVM.secPin.value = it.secPin
                homeVM.adminFee.value = it.adminFee
                homeVM.shareUnitValue.value = it.sharesUnitValue
            }
            if(snackbarVisible){
                Snackbar(
                    modifier=Modifier.padding(horizontal = 15.dp),
                    backgroundColor = RoyalHealht,
                    contentColor = Color.White,
                    action = {
                        Text(modifier = Modifier.clickable {
//                                onDismissClick()
                            snackbarVisible=false
                        }, text = "Dismiss", color = Color.White, fontSize = 10.sp)
                    }) {
                    Text(text = snackbarMessage, color = Color.White, fontSize = 12.sp )
                }
            }
            SecretaryAndAccountPinCreationComponent(
                onSectaryClick = {
                    // update confirm passcode here to db before going to home screen
                    if (isInternetAvailable(context)){
                        if (newSectaryAccessKey.length >= 6){

                            homeVM.updateAdmin(
                                onError = {
                                    snackbarMessage = "Update Not Successful Try again"
                                    snackbarVisible=true
                                },
                                onSuccess = {
                                    adminResetState = !adminResetState
                                    navToHome()
                                }
                            )
                            scope.launch {
                                finOrSec.saveKey("fin")
                            }
                        }
                        else{
                            snackbarMessage = "Access code can't be more that 6 digits"
                            snackbarVisible=true
                        }

                    }else{
                        snackbarMessage = "Internet Connected Not Available"
                        snackbarVisible=true
                    }
                },
                newSectaryAccessKey = newAccountantAccessKey,
                onNewSectaryAccessKeyChange = {
                    homeVM.finPin.value = it
                    newAccountantAccessKey=it.filter { it.isDigit() }},
                defaultSectaryPin = defaultAccountantPin,
                onDefaultSectaryPinChangeChange = {defaultAccountantPin=it.filter { it.isDigit() }},
                confirmSectaryAccessKey = confirmAccountantAccessKey,
                onConfirmSectaryAccessKeyChange = {confirmAccountantAccessKey=it.filter { it.isDigit() }},
                errorMsgForEmptyTextField ={
                    snackbarMessage = "Fill the empty fields"
                    snackbarVisible=true },
                newPinAndOldPinNotDesame ={
                    snackbarMessage = "new pin and confirm pin does not march"
                    snackbarVisible=true
                },
//                    title = "Change access login pin for secretary",
                dbPin = admin?.finPin ?: "",
                wrongDefualtPin = {
                    snackbarMessage = "you entered a wrong previous pin"
                    snackbarVisible=true
                },
                secOrAcctState = secOrAcctState,
                onAccountClick = {secOrAcctState = true},
                onSecertaryClick = {secOrAcctState=false},
                title = "Reset Accountant Passcode"
            )
        }
    }








}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SecretaryAndAccountPinCreationComponent(
    onSectaryClick: () -> Unit,
    newSectaryAccessKey: String,
    onNewSectaryAccessKeyChange:(String)->Unit,
    defaultSectaryPin: String,
    onDefaultSectaryPinChangeChange:(String)->Unit,
    confirmSectaryAccessKey: String,
    onConfirmSectaryAccessKeyChange: (String)->Unit,
    errorMsgForEmptyTextField: ()-> Unit,
    newPinAndOldPinNotDesame: ()-> Unit,
    title: String,
    wrongDefualtPin:()->Unit,
    dbPin: String,
    secOrAcctState: Boolean,
    onAccountClick:()->Unit,
    onSecertaryClick:()->Unit,

) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var defaultPasscodeVisibility by remember { mutableStateOf(true) }
        var newPasscodeVisibility by remember { mutableStateOf(true) }
        var confirmPasscodeVisibility by remember { mutableStateOf(true) }
        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(4f)
            .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.grt_pristine_logo) ,
                contentDescription = "empty_image",
            )
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(5f)
        ) {
            Text(modifier = Modifier.padding(20.dp), text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold,
            )


            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                value = defaultSectaryPin,
                onValueChange = { onDefaultSectaryPinChangeChange(it) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = {
                    Text(
                        text = "Enter your Default Passcode Here") },
                        label = {
                            Text(text = "Default Passcode", color = RoyalHealht)
                        },
                        colors = androidx.compose.material.TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = RoyalHealht, cursorColor = RoyalHealht,
                            backgroundColor = Color.White
                        ),
                        trailingIcon = {
                            IconButton(onClick = {
                                defaultPasscodeVisibility = !defaultPasscodeVisibility
                            }) {
                                Icon(
                                    imageVector = if (defaultPasscodeVisibility) Icons.Filled.VisibilityOff else Icons.Default.Visibility,
                                    contentDescription = "",
                                    tint = Color.Black
                                )
                            }
                        },
                        visualTransformation = if (defaultPasscodeVisibility) PasswordVisualTransformation() else VisualTransformation.None,

                        )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp))
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        value = newSectaryAccessKey,
                        onValueChange = { onNewSectaryAccessKeyChange(it) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        placeholder = {
                            Text(text = "Enter your New Passcode Here")
                        },
                        label = {
                            Text(text = "New Passcode", color = RoyalHealht)
                        },
                        colors = androidx.compose.material.TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = RoyalHealht, cursorColor = RoyalHealht,
                            backgroundColor = Color.White
                        ),
                        trailingIcon = {
                            IconButton(onClick = {
                                newPasscodeVisibility = !newPasscodeVisibility
                            }) {
                                Icon(
                                    imageVector = if (newPasscodeVisibility) Icons.Filled.VisibilityOff else Icons.Default.Visibility,
                                    contentDescription = "",
                                    tint = Color.Black
                                )
                            }
                        },
                        visualTransformation = if (newPasscodeVisibility) PasswordVisualTransformation() else VisualTransformation.None,
                    )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp))
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        value = confirmSectaryAccessKey,
                        onValueChange = { onConfirmSectaryAccessKeyChange(it) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        placeholder = {
                            Text(text = "Enter your New Passcode Here", )
                        },
                        label = {
                            Text(text = "Confirm New Passcode", color = RoyalHealht)
                        },
                        colors = androidx.compose.material.TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = RoyalHealht, cursorColor = RoyalHealht,
                            backgroundColor = Color.White
                        ),
                        trailingIcon = {
                            IconButton(onClick = {
                                confirmPasscodeVisibility = !confirmPasscodeVisibility
                            }) {
                                Icon(
                                    imageVector = if (confirmPasscodeVisibility) Icons.Filled.VisibilityOff else Icons.Default.Visibility,
                                    contentDescription = "",
                                    tint = Color.Black
                                )
                            }
                        },
                        visualTransformation = if (confirmPasscodeVisibility) PasswordVisualTransformation() else VisualTransformation.None,
                    )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp))
                    Button( modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                        onClick = {
                            if (defaultSectaryPin.isNotEmpty() && newSectaryAccessKey.isNotEmpty() && confirmSectaryAccessKey.isNotEmpty()){
                                if (defaultSectaryPin == dbPin){
                                    if (newSectaryAccessKey == confirmSectaryAccessKey){
                                        onSectaryClick()
                                    }
                                    else{ newPinAndOldPinNotDesame() }
                                }else{
                                    wrongDefualtPin()
                                }

                            }else{
                                errorMsgForEmptyTextField()
                            } },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White,
                            contentColor = RoyalHealht
                        ),
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(7.dp)
                                .fillMaxWidth(),
                            text = "Login", fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .weight(1f)
        ) {
            Button(modifier = Modifier
                .fillMaxWidth()
                .weight(4f),
                onClick = { onSecertaryClick() },
                colors = ButtonDefaults.buttonColors(
                    contentColor = if(secOrAcctState){
                        RoyalHealht
                    }else{
                        Color.White
                    },
                    backgroundColor = if(secOrAcctState){
                        Color.White
                    }else{
                        RoyalHealht
                    }
                )
            ) {
                Text(text = "Secretary")
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(modifier = Modifier
                .fillMaxWidth()
                .weight(4f),
                onClick = { onAccountClick() },
                colors = ButtonDefaults.buttonColors(
                    contentColor = if(secOrAcctState){
                        Color.White
                    }else{
                        RoyalHealht
                     },
                    backgroundColor = if(secOrAcctState){
                        RoyalHealht
                    }else{
                        Color.White
                    }
                )
            ) {
                Text(text = "Account")
            }
        }
    }
}

