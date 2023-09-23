package com.stevdza.san.testapp.screen.memberDetails

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPayment(
    modifier: Modifier=Modifier,
    selectedModeOfPayment: String, modeOfPaymentList: List<String>, modeOfPaymentHide: Boolean,
    modeOfPayment: String, onDrowdownModeOfPaymentMenuClick:(String)-> Unit, onEditModeOfPayment:()->Unit,

    amountHide: Boolean, amount: String, amountPaidText: String, onAmountChange: (String)-> Unit,
    onEditAmountClick:()->Unit, onDoneAmountClick:()->Unit,

    onAddClick:()->Unit,

    purposeOfPaymentHide: Boolean, selectedPurposeOfPayment: String, purposeOfPaymentList: List<String>,
    purposeOfPayment: String, onEditPurposeOfPayment: ()-> Unit, onDrowdownPurposeOfPaymentMenuClick:(String)->Unit,

    monthPledgePaymentHide: Boolean, selectedMonthPledgePayment: String, monthPledgePaymentList: List<String>,
    monthPledgePayment: String, onEditMonthPledge: ()-> Unit, onDrowdownMonthPledgePaymentMenuClick:(String)->Unit,

    dateOfPaymenthHide: Boolean, onDatePick: ()-> Unit, onEditDateClick: ()-> Unit, selectedDate: String, datePickerState: DatePickerState,

    paymentReftHide: Boolean, paymentRef: String, paymentRefText: String,
    onEditPaymentRefClick:()->Unit, onPaymentRefChange:(String)-> Unit, onDonePaymentRefClick:()->Unit,

    objectId: String, onUpdatePaymentClick:()->Unit
){

    Column(modifier.fillMaxWidth()) {
        Column(modifier = Modifier.weight(9f)) {

            LazyColumn{
                item {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        var expanded by remember { mutableStateOf(false) }
                        if (modeOfPaymentHide){
                            Row(
                                modifier = Modifier
                                    .padding(5.dp)
                                    .clickable { expanded = true }
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text( text = selectedModeOfPayment)
                                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "ArrowDropDown")
                                DropdownMenu(
                                    modifier=Modifier.fillMaxWidth(),
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false }
                                ) {
                                    modeOfPaymentList.forEach { modeOfPayment ->
                                        DropdownMenuItem(
                                            text = {
                                                Text(
                                                    modifier=Modifier.fillMaxWidth(),
                                                    text = modeOfPayment,
                                                    textAlign = TextAlign.Center
                                                )
                                            },
                                            onClick = {
                                            onDrowdownModeOfPaymentMenuClick(modeOfPayment)
                                            expanded = false
                                        })
                                    }
                                }
                            }
                        }
                        else {
                            Row(modifier = Modifier
                                .padding(vertical = 20.dp)
                                .fillMaxWidth()) {
                                Icon(
                                    modifier = Modifier.weight(1f),
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = "PlayArrow"
                                )

                                Column(
                                    modifier = Modifier.weight(7f),
                                ) {
                                    Text(text = "Mode of Payment", fontSize = 12.sp)
                                    Text(text = modeOfPayment, fontSize = 14.sp)
                                }

                                Icon(
                                    modifier = Modifier
                                        .weight(2f)
                                        .clickable { onEditModeOfPayment() },
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit"
                                )
                            }
                        }

                        Divider(modifier = Modifier.fillMaxWidth(), thickness = 3.dp)
                    }

                }
                item {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        var expanded by remember { mutableStateOf(false) }
                        if (purposeOfPaymentHide){
                            Row(
                                modifier = Modifier
                                    .padding(5.dp)
                                    .clickable { expanded = true }
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text( text = selectedPurposeOfPayment)
                                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "ArrowDropDown")
                                DropdownMenu(
                                    modifier=Modifier.fillMaxWidth(),
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false }
                                ) {
                                    purposeOfPaymentList.forEach { purposeOfPayment ->
                                        DropdownMenuItem(
                                            text = {
                                                Text(
                                                    modifier=Modifier.fillMaxWidth(),
                                                    text = purposeOfPayment,
                                                    textAlign = TextAlign.Center
                                                )
                                            },
                                            onClick = {
                                                onDrowdownPurposeOfPaymentMenuClick(purposeOfPayment)
                                                expanded = false
                                            })
                                    }
                                }
                            }
                        }else {
                            Row(modifier = Modifier
                                .padding(vertical = 20.dp)
                                .fillMaxWidth()) {
                                Icon(
                                    modifier = Modifier.weight(1f),
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = "PlayArrow"
                                )

                                Column(
                                    modifier = Modifier.weight(7f),
                                ) {
                                    Text(text = "Purpose of Payment", fontSize = 12.sp)
                                    Text(text = purposeOfPayment, fontSize = 14.sp)
                                }

                                Icon(
                                    modifier = Modifier
                                        .weight(2f)
                                        .clickable { onEditPurposeOfPayment() },
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit"
                                )
                            }
                        }

                        Divider(modifier = Modifier.fillMaxWidth(), thickness = 3.dp)
                    }

                }

                if (selectedPurposeOfPayment == "Pledge"){
                    item {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            var expanded by remember { mutableStateOf(false) }
                            if (monthPledgePaymentHide){
                                Row(
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .clickable { expanded = true }
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text( text = selectedMonthPledgePayment)
                                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "ArrowDropDown")
                                    DropdownMenu(
                                        modifier=Modifier.fillMaxWidth(),
                                        expanded = expanded,
                                        onDismissRequest = { expanded = false }
                                    ) {
                                        monthPledgePaymentList.forEach { monthPledgePayment ->
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        modifier=Modifier.fillMaxWidth(),
                                                        text = monthPledgePayment,
                                                        textAlign = TextAlign.Center
                                                    )
                                                },
                                                onClick = {
                                                    onDrowdownMonthPledgePaymentMenuClick(monthPledgePayment)
                                                    expanded = false
                                                })
                                        }
                                    }
                                }
                            }else {
                                Row(modifier = Modifier
                                    .padding(vertical = 20.dp)
                                    .fillMaxWidth()) {
                                    Icon(
                                        modifier = Modifier.weight(1f),
                                        imageVector = Icons.Default.PlayArrow,
                                        contentDescription = "PlayArrow"
                                    )

                                    Column(
                                        modifier = Modifier.weight(7f),
                                    ) {
                                        Text(text = "Pledge Month Payment", fontSize = 12.sp)
                                        Text(text = monthPledgePayment, fontSize = 14.sp)
                                    }

                                    Icon(
                                        modifier = Modifier
                                            .weight(2f)
                                            .clickable { onEditMonthPledge() },
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = "Edit"
                                    )
                                }
                            }

                            Divider(modifier = Modifier.fillMaxWidth(), thickness = 3.dp)
                        }

                    }
                }

                if (selectedPurposeOfPayment == "Admin"){
                    item {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            var expanded by remember { mutableStateOf(false) }
                            if (monthPledgePaymentHide){
                                Row(
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .clickable { expanded = true }
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text( text = selectedMonthPledgePayment)
                                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "ArrowDropDown")
                                    DropdownMenu(
                                        modifier=Modifier.fillMaxWidth(),
                                        expanded = expanded,
                                        onDismissRequest = { expanded = false }
                                    ) {
                                        monthPledgePaymentList.forEach { monthPledgePayment ->
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        modifier=Modifier.fillMaxWidth(),
                                                        text = monthPledgePayment,
                                                        textAlign = TextAlign.Center
                                                    )
                                                },
                                                onClick = {
                                                    onDrowdownMonthPledgePaymentMenuClick(monthPledgePayment)
                                                    expanded = false
                                                })
                                        }
                                    }
                                }
                            }else {
                                Row(modifier = Modifier
                                    .padding(vertical = 20.dp)
                                    .fillMaxWidth()) {
                                    Icon(
                                        modifier = Modifier.weight(1f),
                                        imageVector = Icons.Default.PlayArrow,
                                        contentDescription = "PlayArrow"
                                    )

                                    Column(
                                        modifier = Modifier.weight(7f),
                                    ) {
                                        Text(text = "Admin Month Payment", fontSize = 12.sp)
                                        Text(text = monthPledgePayment, fontSize = 14.sp)
                                    }

                                    Icon(
                                        modifier = Modifier
                                            .weight(2f)
                                            .clickable { onEditMonthPledge() },
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = "Edit"
                                    )
                                }
                            }

                            Divider(modifier = Modifier.fillMaxWidth(), thickness = 3.dp)
                        }

                    } }

                item {
                    InformationItem(
                        hide = amountHide,
                        value = amount,
                        textValue = amountPaidText,
                        onEditClick = { onEditAmountClick() },
                        onValueChange = { onAmountChange(it) },
                        onDOneClick = {onDoneAmountClick()},
                        icon = Icons.Default.LocationOn,
                        title = "Amount",
                    )
                }
                item {
                    InformationItem(
                        hide = paymentReftHide,
                        value = paymentRef,
                        textValue = paymentRefText,
                        onEditClick = { onEditPaymentRefClick() },
                        onValueChange = { onPaymentRefChange(it) },
                        onDOneClick = {onDonePaymentRefClick()},
                        icon = Icons.Default.LocationOn,
                        title = "Payment Reference",
                    )
                }
                item {
                    // Date of Payment
                     if (dateOfPaymenthHide) {
                            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                val calendar = Calendar.getInstance()
                                calendar.set(2023, 6, 14) // add year, month (Jan), date
                                DatePicker(
                                    state = datePickerState,
                                    showModeToggle = false
                                )
                                Button(onClick = { onDatePick() }) {
                                    Text(text = "Done")
                                }
                            }
                        }
                     else {

                         Row(modifier = Modifier
                             .padding(vertical = 20.dp)
                             .fillMaxWidth()) {
                             Icon(
                                 modifier = Modifier.weight(1f),
                                 imageVector = Icons.Default.DateRange,
                                 contentDescription = "DateRange"
                             )
                             Column(
                                 modifier = Modifier.weight(7f),
                             ) {
                                 Text(text = "Date Of Payment", fontSize = 12.sp)
                                 Text(text = selectedDate, fontSize = 14.sp)
                             }
                             Icon(
                                 modifier = Modifier
                                     .weight(2f)
                                     .clickable { onEditDateClick() },
                                 imageVector = Icons.Default.Edit,
                                 contentDescription = "Edit"
                             )
                         }

                         Divider(modifier = Modifier.fillMaxWidth(), thickness = 3.dp)
                     }
                }

            }


        }

        if (amountHide) {
            Log.e(ContentValues.TAG, "amountHide: $amountHide",)
        }else if (modeOfPaymentHide){
            Log.e(ContentValues.TAG, "modeOfPaymentHide: $modeOfPaymentHide",)
        }else if (purposeOfPaymentHide){
            Log.e(ContentValues.TAG, "purposeOfPaymentHide: $purposeOfPaymentHide",)
        }else if (paymentReftHide){
            Log.e(ContentValues.TAG, "paymentReftHide: $paymentReftHide",)
        }
        else if (dateOfPaymenthHide){
            Log.e(ContentValues.TAG, "dateOfPaymenthHide: $dateOfPaymenthHide",)
        }
        else{
            if (objectId.isNotEmpty()){
                Row(modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(end = 20.dp),
                    horizontalArrangement = Arrangement.End) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onUpdatePaymentClick() }) {
                        Text(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            text = "Update Payment"
                        )
                    }
                }
            }else{
                Row(modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(end = 20.dp),
                    horizontalArrangement = Arrangement.End) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onAddClick() }) {
                        Text(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            text = "Add Payment"
                        )

                    }
                }
            }

        }

    }

}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWithdraw(
    modifier: Modifier=Modifier,
    amountWithdrawHide: Boolean, amountWithdraw: String, amountWithdrawText: String, onAmountWithdrawChange: (String)-> Unit, onEditWithdrawAmountClick:()->Unit,
    onDoneWithdrawAmountClick:()->Unit, onWithdrawAddClick:()->Unit,

    purposeOfWithdrawHide: Boolean, selectedPurposeOfWithdraw: String, purposeOfWithdrawList: List<String>,
    purposeOfWithdraw: String, onEditPurposeOfWithdraw: ()-> Unit, onDrowdownPurposeOfWithdrawMenuClick:(String)->Unit,

    dateOfWithdrawthHide: Boolean, onDateWithdrawPick: ()-> Unit, onEditWithdrawDateClick: ()-> Unit, selectedWithdrawDate: String,
    WithdrawDatePickerState: DatePickerState,

    chequeNoHide:Boolean, chequeNo: String, chequeNoText: String, onEditChequeNoClick:()->Unit,
    onChequeNoChange:(String)->Unit, onDoneChequeNoClick:()->Unit,

    voucherNoHide: Boolean, voucherNo: String, voucherNoText: String, onEditVoucherNoClick:()->Unit,
    onvoucherNoChange:(String)->Unit, onDonevoucherNoClick:()->Unit,

    accountCodeHide: Boolean, accountCode:String, accountCodeText: String, onEditAccountCodeClick:()->Unit,
    onAccountCodeChange:(String)->Unit, onDoneAccountCodeClick:()-> Unit,

    withdrawalObjectId: String, onUpdateWithdrawClick:()->Unit

){
    Column(modifier.fillMaxWidth()) {
        Column(modifier = Modifier.weight(9f)) {

            LazyColumn{

                item {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        var expanded by remember { mutableStateOf(false) }
                        if (purposeOfWithdrawHide){
                            Row(
                                modifier = Modifier
                                    .padding(5.dp)
                                    .clickable { expanded = true }
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text( text = selectedPurposeOfWithdraw)
                                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "ArrowDropDown")
                                DropdownMenu(
                                    modifier=Modifier.fillMaxWidth(),
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false }
                                ) {
                                    purposeOfWithdrawList.forEach { purposeOfWithdraw ->
                                        DropdownMenuItem(
                                            text = {
                                                Text(
                                                    modifier=Modifier.fillMaxWidth(),
                                                    text = purposeOfWithdraw,
                                                    textAlign = TextAlign.Center
                                                )
                                            },
                                            onClick = {
                                                onDrowdownPurposeOfWithdrawMenuClick(purposeOfWithdraw)
                                                expanded = false
                                            })
                                    }
                                }
                            }
                        }else {
                            Row(modifier = Modifier
                                .padding(vertical = 20.dp)
                                .fillMaxWidth()) {
                                Icon(
                                    modifier = Modifier.weight(1f),
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = "PlayArrow"
                                )

                                Column(
                                    modifier = Modifier.weight(7f),
                                ) {
                                    Text(text = "Account of Withdraw", fontSize = 12.sp)
                                    Text(text = purposeOfWithdraw, fontSize = 14.sp)
                                }

                                Icon(
                                    modifier = Modifier
                                        .weight(2f)
                                        .clickable { onEditPurposeOfWithdraw() },
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit"
                                )
                            }
                        }

                        Divider(modifier = Modifier.fillMaxWidth(), thickness = 3.dp)
                    }

                }
                item {
                    InformationItem(
                        hide = chequeNoHide,
                        value = chequeNo,
                        textValue=chequeNoText,
                        onEditClick = { onEditChequeNoClick() },
                        onValueChange = { onChequeNoChange(it) },
                        onDOneClick = {onDoneChequeNoClick()},
                        icon = Icons.Default.LocationOn,
                        title = "Cheque NO",
                    )
                }
                item {
                    InformationItem(
                        hide = voucherNoHide,
                        value = voucherNo,
                        textValue=voucherNoText,
                        onEditClick = { onEditVoucherNoClick() },
                        onValueChange = { onvoucherNoChange(it) },
                        onDOneClick = {onDonevoucherNoClick()},
                        icon = Icons.Default.LocationOn,
                        title = "Voucher No.",
                    )
                }
                item {
                    InformationItem(
                        hide = accountCodeHide,
                        value = accountCode,
                        textValue=accountCodeText,
                        onEditClick = { onEditAccountCodeClick() },
                        onValueChange = { onAccountCodeChange(it) },
                        onDOneClick = {onDoneAccountCodeClick()},
                        icon = Icons.Default.LocationOn,
                        title = "Account Code",
                    )
                }
                item {
                    // Date of Payment
                    if (dateOfWithdrawthHide) {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            DatePicker(
                                state = WithdrawDatePickerState,
                                showModeToggle = false
                            )
                            Button(onClick = { onDateWithdrawPick() }) {
                                Text(text = "Done")
                            }
                        }
                    }
                    else {

                        Row(modifier = Modifier
                            .padding(vertical = 20.dp)
                            .fillMaxWidth()) {
                            Icon(
                                modifier = Modifier.weight(1f),
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "DateRange"
                            )
                            Column(
                                modifier = Modifier.weight(7f),
                            ) {
                                Text(text = "Date Of Withdraw", fontSize = 12.sp)
                                Text(text = selectedWithdrawDate, fontSize = 14.sp)
                            }
                            Icon(
                                modifier = Modifier
                                    .weight(2f)
                                    .clickable { onEditWithdrawDateClick() },
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit"
                            )
                        }

                        Divider(modifier = Modifier.fillMaxWidth(), thickness = 3.dp)
                    }
                }
                item {
                    InformationItem(
                        hide = amountWithdrawHide,
                        value = amountWithdraw,
                        textValue=amountWithdrawText,
                        onEditClick = { onEditWithdrawAmountClick() },
                        onValueChange = { onAmountWithdrawChange(it) },
                        onDOneClick = {onDoneWithdrawAmountClick()},
                        icon = Icons.Default.LocationOn,
                        title = "Amount",
                    )
                }

            }

        }

        if (amountWithdrawHide) {
            Log.e(ContentValues.TAG, "PersonalInfo: $amountWithdrawHide",)
        }else if (purposeOfWithdrawHide){
            Log.e(ContentValues.TAG, "PersonalInfo: $purposeOfWithdrawHide",)
        }else if (chequeNoHide){
            Log.e(ContentValues.TAG, "PersonalInfo: $chequeNoHide",)
        }else if (voucherNoHide){
            Log.e(ContentValues.TAG, "PersonalInfo: $voucherNoHide",)
        }else if (accountCodeHide){
            Log.e(ContentValues.TAG, "PersonalInfo: $accountCodeHide",)
        }else if (dateOfWithdrawthHide){
            Log.e(ContentValues.TAG, "PersonalInfo: $dateOfWithdrawthHide",)
        }
        else{
            if (withdrawalObjectId.isNotEmpty()){
                Row(modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(end = 20.dp),
                    horizontalArrangement = Arrangement.End) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onUpdateWithdrawClick() }) {
                        Text(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            text = "Update Withdraw")
                    }
                }
            }else{
                Row(modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(end = 20.dp),
                    horizontalArrangement = Arrangement.End) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onWithdrawAddClick() }) {
                        Text(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            text = "Withdraw")
                    }
                }
            }

        }

    }

}