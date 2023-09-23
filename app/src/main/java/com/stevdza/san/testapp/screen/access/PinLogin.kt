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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import com.stevdza.san.testApp.R
import com.stevdza.san.testapp.dataMain.dataStore.FinOrSec
import com.stevdza.san.testapp.screen.HomeViewModel
import com.stevdza.san.testapp.ui.theme.RoyalHealht
import kotlinx.coroutines.launch

@Composable
fun PinLoginSecAndFin(navToDashboard:()-> Unit){
    var accountAccessKey by remember { mutableStateOf("") }
    var sectaryAccessKey by remember { mutableStateOf("") }
    var snackbarMessage by remember { mutableStateOf("") }
    var snackbarVisible by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val homeVM : HomeViewModel = viewModel()

    val admin = homeVM.admin.value

//    LaunchedEffect(key1 = true ){
//        if(homeVM.adminData.value.isNotEmpty()){
//            homeVM.getAdminWIthID()
//        }
//    }
    if(homeVM.adminData.value.isNotEmpty()){
        homeVM.getAdminWIthID()
    }

    admin?.let {
        homeVM.finPin.value = it.finPin
        homeVM.secPin.value = it.secPin

    }

    val context = LocalContext.current
    val finOrSec = FinOrSec(context)
    val getFinOrSecKey = finOrSec.getKey.collectAsState(initial = "")
    Log.e(TAG, "finPin: ${homeVM.finPin.value}", )
    Log.e(TAG, "secPin: ${homeVM.secPin.value}", )

    Column(modifier = Modifier.fillMaxSize()) {

        PinLoginComponent(
            onAccountClick = {
                if (accountAccessKey == homeVM.finPin.value){
                    scope.launch {
                        finOrSec.saveKey("fin")
                    }
                    navToDashboard()
                }else{
                    snackbarMessage ="Wrong Access Key"
                    snackbarVisible=true
                } 
                             },
            onSectaryClick = {
                if (sectaryAccessKey == homeVM.secPin.value){
                    scope.launch {
                        finOrSec.saveKey("sec")
                    }
                    navToDashboard()
                }else{
                    snackbarMessage ="Wrong Access Key"
                    snackbarVisible=true
                }
                             },
            onAccountAccessKeyChange = { accountAccessKey = it.filter { it.isDigit() } },
            accountAccessKey = accountAccessKey,
            onSectaryAccessKeyChange = { sectaryAccessKey=it.filter { it.isDigit() } },
            sectaryAccessKey = sectaryAccessKey ,
            errorMsgForEmptyTextField = {
                snackbarMessage ="Input Field is Empty"
                snackbarVisible=true
                                        },
            snackbarVisible = snackbarVisible,
            onDismissClick = {snackbarVisible=false},
            snackbarMessage = snackbarMessage
        )
    }

}

@Composable
fun PinLoginComponent(
    onSectaryClick:()->Unit,
    onAccountClick:()->Unit,
    onAccountAccessKeyChange:(String)->Unit,
    accountAccessKey: String,
    onSectaryAccessKeyChange:(String)->Unit,
    sectaryAccessKey: String,
    errorMsgForEmptyTextField: ()-> Unit,
    snackbarVisible: Boolean,
    snackbarMessage: String,
    onDismissClick:()->Unit,
){
    Column(
        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(snackbarVisible){
            Snackbar(
                modifier=Modifier.padding(horizontal = 15.dp),
                backgroundColor = RoyalHealht,
                contentColor = Color.White,
                action = {
                    Text(modifier = Modifier.clickable {
                        onDismissClick()
//                        snackbarVisible=false
                    }, text = "Dismiss", color = Color.White, fontSize = 10.sp)
                }) {
                Text(text = snackbarMessage, color = Color.White, fontSize = 12.sp )
            }
        }
        LazyColumn {
            item{
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp))

                    Text(text = "Log out", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Right)
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp))

                    Image(
                        painter = painterResource(id = R.drawable.grt_pristine_logo) ,
                        contentDescription = "empty_image",
                    )
                }
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp))
            }

            item{
                SectaryOrAccountSectionScreen(
                    onSectaryClick = { onSectaryClick()},
                    onAccountClick = { onAccountClick() },
                    onAccountAccessKeyChange = { onAccountAccessKeyChange(it) },
                    accountAccessKey = accountAccessKey,
                    onSectaryAccessKeyChange = { onSectaryAccessKeyChange(it) },
                    sectaryAccessKey =sectaryAccessKey ,
                    errorMsgForEmptyTextField = errorMsgForEmptyTextField
                )
            }
        }
    }
}


@Composable
private fun SectaryOrAccountSectionScreen(
    onSectaryClick: () -> Unit,
    onAccountClick:()->Unit,
    onAccountAccessKeyChange:(String)->Unit,
    accountAccessKey: String,
    onSectaryAccessKeyChange:(String)->Unit,
    sectaryAccessKey: String,
    errorMsgForEmptyTextField: ()-> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var accountantExpand by remember { mutableStateOf(false) }
        var sectaryExpand by remember { mutableStateOf(false) }
        var accountPasscodeVisibility by remember { mutableStateOf(true) }
        var secretaryCodeVisibility by remember { mutableStateOf(true) }
        Text(
            text = "What would you like to login as?",
            fontSize = 14.sp, fontWeight = FontWeight.Bold
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
        Card {
            Column {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .clickable { sectaryExpand = !sectaryExpand }
                    .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(modifier = Modifier.padding(20.dp), text = "Secretary", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = RoyalHealht)
                    if (sectaryExpand){
                        Icon(modifier = Modifier.padding(20.dp), imageVector = Icons.Default.ArrowDropUp, contentDescription ="ArrowDropUp" )
                    }else{
                        Icon(modifier = Modifier.padding(20.dp), imageVector = Icons.Default.ArrowDropDown, contentDescription = "ArrowDropDown")
                    }
                }

                if (sectaryExpand){
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        value = sectaryAccessKey,
                        onValueChange = { onSectaryAccessKeyChange(it) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        trailingIcon = {
                            IconButton(onClick = {
                                secretaryCodeVisibility = !secretaryCodeVisibility
                            }) {
                                Icon(
                                    imageVector = if (secretaryCodeVisibility) Icons.Filled.VisibilityOff else Icons.Default.Visibility,
                                    contentDescription = "",
                                    tint = Color.Black
                                )
                            }
                        },
                        visualTransformation = if (secretaryCodeVisibility) PasswordVisualTransformation() else VisualTransformation.None,

                        )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp))
                    Button( modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                        onClick = {
                            if (sectaryAccessKey.isNotEmpty()){
                                onSectaryClick()
                            }else{
                                errorMsgForEmptyTextField()
                            } },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            backgroundColor = RoyalHealht
                        ),
                        ) {
                        Text(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            text = "Login", fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }


        }


        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp))

        Card {
            Column {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .clickable { accountantExpand = !accountantExpand }
                    .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(modifier = Modifier.padding(20.dp), text = "Accountant", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = RoyalHealht)
                    if (accountantExpand){
                        Icon(modifier = Modifier.padding(20.dp), imageVector = Icons.Default.ArrowDropUp, contentDescription ="ArrowDropUp" )
                    }else{
                        Icon(modifier = Modifier.padding(20.dp), imageVector = Icons.Default.ArrowDropDown, contentDescription = "ArrowDropDown")
                    }
                }

                if (accountantExpand){
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        value = accountAccessKey,
                        onValueChange = { onAccountAccessKeyChange(it) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        trailingIcon = {
                            IconButton(onClick = {
                                accountPasscodeVisibility = !accountPasscodeVisibility
                            }) {
                                Icon(
                                    imageVector = if (accountPasscodeVisibility) Icons.Filled.VisibilityOff else Icons.Default.Visibility,
                                    contentDescription = "",
                                    tint = Color.Black
                                )
                            }
                        },
                        visualTransformation = if (accountPasscodeVisibility) PasswordVisualTransformation() else VisualTransformation.None,

                        )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp))
                    Button(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth(),
                        onClick = {
                            if (accountAccessKey.isNotEmpty()){
                                onAccountClick()
                            }else{
                                errorMsgForEmptyTextField()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            backgroundColor = RoyalHealht
                        ),

                        ) {
                        Text(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            text = "Login", fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

        }


    }
}