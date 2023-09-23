package com.stevdza.san.testapp.screen.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.stevdza.san.testapp.dataMain.GrtPayment
import com.stevdza.san.testapp.dataMain.GrtWithdrawal
import com.stevdza.san.testApp.R
import com.stevdza.san.testapp.dataMain.GrtProfile
import com.stevdza.san.testapp.screen.component.PaymentUINotEditable
import com.stevdza.san.testapp.screen.component.WithdrawalUiNotEditable

@Composable
fun GRTAccounts(
    modifier: Modifier=Modifier,
    payment: List<GrtPayment>,
    withdraw: List<GrtWithdrawal>,
    onRefreshSlide: () -> Unit,
    swipeRefreshState: SwipeRefreshState,
    totalWithdrawAdmin: String,
    totalPaymentAdmin: String,
    yearState: Boolean,
    yearTextfield: String,
    yearText: String,
    onYearTextFieldChange:(String)->Unit,
    onDoneClick:()->Unit,
    onYearTextClick:()->Unit,
    selectedMonth: String,
    monthList: List<String>,
    onDropDownMenuClick: (String)->Unit,
    paymentAndWithdrawalState:String,
    grtProfile: List<GrtProfile>

){
    Column(modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SwipeRefresh(
            modifier = Modifier.weight(8.5f),
            state = swipeRefreshState,
            onRefresh = { onRefreshSlide()},
        ) {
            when(paymentAndWithdrawalState){
                "Payment"->{
                    if (payment.isNotEmpty()){
                        LazyColumn(modifier = Modifier.fillMaxWidth() ){
                            items(items = payment) {grtPayment->

                                val memberId =  grtProfile.find { it._id.toHexString() == grtPayment.memberId }
                                PaymentUINotEditable(
                                    payment = grtPayment,
                                    memberId = memberId?.name ?: ""
                                )
                            }
                        }
                    }else{
                        Image(
                            modifier = Modifier
                                .fillMaxSize(),
                            painter = painterResource(id = R.drawable.empty_list),
                            contentDescription = "empty_list",
                            contentScale = ContentScale.Fit
                        )
                    }
                }
                "Withdraw"->{
                    if (withdraw.isNotEmpty()){
                        LazyColumn(modifier = Modifier.fillMaxWidth() ){
                            items(items = withdraw) {grtWithdraw->
                                val memberId =  grtProfile.find { it._id.toHexString() == grtWithdraw.memberId }
                                WithdrawalUiNotEditable(
                                    withdraw = grtWithdraw,
                                    memberId = memberId?.name ?: "",
                                    purposeOfWithdraw = grtWithdraw.purposeOfWithdraw,

                                )
                            }
                        }

                    }else{
                        Image(
                            modifier = Modifier
                                .fillMaxSize(),
                            painter = painterResource(id = R.drawable.empty_list),
                            contentDescription = "empty_list",
                            contentScale = ContentScale.Fit
                        )
                    }
                }
            }
        }
        Column(modifier = Modifier.weight(1.5f)) {
//            var yearState by remember { mutableStateOf(false) }
            if (!yearState){
                when(paymentAndWithdrawalState){
                    "Payment"->{
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = "Total $totalPaymentAdmin",
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
                    }
                    "Withdraw"->{
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = "Total $totalWithdrawAdmin",
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
                    }
                }

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically
            ) {
//                var yearTextfield by remember { mutableStateOf("2023") }
//                var yearText by remember { mutableStateOf("") }
//                yearText=yearTextfield

                if (yearState){
                    TextField(
                        value = yearTextfield,
                        onValueChange = {onYearTextFieldChange(it)},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        maxLines = 1,
                        trailingIcon = {
                            Icon(modifier = Modifier.clickable { onDoneClick() }, imageVector = Icons.Default.Done, contentDescription = "Done")
                        }
                    )
                }else{
                    Button(modifier = Modifier.weight(5f), onClick = { onYearTextClick() }, colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                        Text(modifier = Modifier.padding(5.dp), text = yearText, fontSize = 16.sp)
                    }
                }


                var expanded by remember { mutableStateOf(false) }
//               var monthHide by remember { mutableStateOf(false) }
//                var selectedMonth by remember { mutableStateOf("Select") }
//                val monthList = listOf<String>(
//                    "Select", "January", "February", "March", "April", "May", "June", "July",
//                    "August", "September", "October", "November", "December"
//                )
                Row(
                    modifier = Modifier
                        .padding(5.dp)
                        .clickable { expanded = true }
                        .weight(5f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                        Text(text = selectedMonth, fontSize = 16.sp)
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "ArrowDropDown"
                        )
                        DropdownMenu(
                            modifier=Modifier.fillMaxWidth(),
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            monthList.forEach { Month ->
                                DropdownMenuItem(
                                    text = {
                                        androidx.compose.material3.Text(
                                            modifier=Modifier.fillMaxWidth(),
                                            text = Month,
                                            textAlign = TextAlign.Center
                                        )
                                    },
                                    onClick = {
                                        onDropDownMenuClick(Month)
                                        expanded = false
                                    })
                            }
                        }
                    }
            }
        }


    }
}