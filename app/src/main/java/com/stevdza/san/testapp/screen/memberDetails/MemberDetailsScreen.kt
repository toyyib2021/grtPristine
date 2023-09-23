package com.stevdza.san.testapp.screen.memberDetails

import android.content.ContentValues.TAG
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Snackbar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.stevdza.san.testapp.dataMain.GrtPayment
import com.stevdza.san.testapp.dataMain.GrtWithdrawal
import com.stevdza.san.testapp.screen.HomeViewModel
import com.stevdza.san.testapp.screen.home.PaymentViewModel
import com.stevdza.san.testapp.screen.home.WithdrawalViewModel
import com.stevdza.san.testApp.R
import com.stevdza.san.testapp.dataMain.dataStore.FinOrSec
import com.stevdza.san.testapp.ui.Constants.ADD_PAYMENT_LIST
import com.stevdza.san.testapp.ui.Constants.ADD_WITHDRAW_LIST
import com.stevdza.san.testapp.ui.Constants.ADMIN
import com.stevdza.san.testapp.ui.Constants.ALL_PAYMENT_TYPE
import com.stevdza.san.testapp.ui.Constants.EMPLOYMENT_INFO_PROFILE_LIST
import com.stevdza.san.testapp.ui.Constants.LOAN
import com.stevdza.san.testapp.ui.Constants.LOCATION_INFO_PROFILE_LIST
import com.stevdza.san.testapp.ui.Constants.NEXT_OF_KIN_PROFILE_LIST
import com.stevdza.san.testapp.ui.Constants.PAYMENT_LIST
import com.stevdza.san.testapp.ui.Constants.PAYMENT_SUMMARY_LIST
import com.stevdza.san.testapp.ui.Constants.PERSONAL_INFOR_PROFILE_LIST
import com.stevdza.san.testapp.ui.Constants.PLEDGE
import com.stevdza.san.testapp.ui.Constants.PROFILE_LIST
import com.stevdza.san.testapp.ui.Constants.REFEREE_INFOR_PROFILE_LIST
import com.stevdza.san.testapp.ui.Constants.SIF
import com.stevdza.san.testapp.ui.Constants.TARGET
import com.stevdza.san.testapp.ui.Constants.WITHDRAW_LIST
import com.stevdza.san.testapp.ui.Constants.WITHDRAW_LOAN
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MemberDetailScreen(
    memberId: String, onBackClick:()-> Unit,
    navToStatement:(String)->Unit,
    navToProfileScreen: (String)-> Unit
){

    val homeVM: HomeViewModel = viewModel()
    val paymentVM: PaymentViewModel = viewModel()
    val withdrawalVM: WithdrawalViewModel = viewModel()



    LaunchedEffect(key1 = true ){
        homeVM.objectId.value=memberId
        homeVM.getMemberWIthID()
        paymentVM.memberId.value=memberId
        paymentVM.getPaymentWithMemberID()
        withdrawalVM.memberId.value=memberId
        withdrawalVM.getWithdrawalWithMemberID()
    }

    homeVM.member.value?.let {member->
        homeVM.name.value = member.name
        homeVM.formNo.value = member.refNo
    }

    BackHandler {
        when(homeVM.listState.value){
            PERSONAL_INFOR_PROFILE_LIST ->{homeVM.listState.value = PROFILE_LIST }
            LOCATION_INFO_PROFILE_LIST ->{ homeVM.listState.value = PROFILE_LIST }
            EMPLOYMENT_INFO_PROFILE_LIST ->{ homeVM.listState.value = PROFILE_LIST }
            NEXT_OF_KIN_PROFILE_LIST ->{ homeVM.listState.value = PROFILE_LIST }
            REFEREE_INFOR_PROFILE_LIST ->{ homeVM.listState.value = PROFILE_LIST }
            PAYMENT_SUMMARY_LIST -> { homeVM.listState.value = PAYMENT_LIST }
            ADD_PAYMENT_LIST->{ homeVM.listState.value = PAYMENT_LIST }
            PAYMENT_LIST -> { onBackClick() }
            WITHDRAW_LIST -> { onBackClick() }
            ADD_WITHDRAW_LIST ->{ homeVM.listState.value = WITHDRAW_LIST }
            PROFILE_LIST -> { homeVM.listState.value = PAYMENT_LIST }
        }
    }

    Scaffold(
        topBar = {
                TopAppBar(
                    listState = homeVM.listState.value,
                    onBackClick = onBackClick,
                    profileList = { homeVM.listState.value = PROFILE_LIST },
                    paymentList = { homeVM.listState.value = PAYMENT_LIST },
                    withdrawalList = { homeVM.listState.value = WITHDRAW_LIST },
                    title =homeVM.name.value)
                }
    ) {


        // Swipe to Refresh Payment
        var isRefreshingPayment by remember { mutableStateOf(false) }
        val swipeRefreshStatePayment = rememberSwipeRefreshState(isRefreshingPayment)

        LaunchedEffect(isRefreshingPayment) {
            if (isRefreshingPayment) {
                delay(1000L)
                isRefreshingPayment = false
            }
        }

        // Swipe to Refresh Withdraw
        var isRefreshingWithdraw by remember { mutableStateOf(false) }
        val swipeRefreshStateWithdraw = rememberSwipeRefreshState(isRefreshingWithdraw)

        LaunchedEffect(isRefreshingWithdraw) {
            if (isRefreshingWithdraw) {
                delay(1000L)
                isRefreshingWithdraw = false
            }
        }


        // Snackbar
        var snackbarVisible by remember { mutableStateOf(false) }
        var snackbarMessage by remember { mutableStateOf("") }

        LaunchedEffect(key1 = snackbarVisible){
            if (snackbarVisible){
                delay(2000)
                snackbarVisible = false
            }
        }


        // Payment Year / Month / And All Click
        var isEditingPayment by remember { mutableStateOf(true) }
        val monthsPayment = listOf<String>(
             "January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"
        )
        val scope = rememberCoroutineScope()
        val context = LocalContext.current
        val finOrSec = FinOrSec(context)
        val finOrSecKey = finOrSec.getKey.collectAsState(initial = "")
        var pleadge by remember { mutableStateOf("") }

        val loanPaymentList = paymentVM.paymentDataWithMemberId.value.filter {  it.purposeOfPayment == LOAN }
        val loanPaid = loanPaymentList.map { it.amount }.sumOf { it }
        val loanCollectedList = withdrawalVM.withdrawDataWithMemberId.value.filter { it.purposeOfWithdraw == LOAN }
        val loanCollected = loanCollectedList.map { it.amount }.sumOf { it }
        val loanBalance = loanCollected - loanPaid


        val targetPaymentList = paymentVM.paymentDataWithMemberId.value.filter { it.purposeOfPayment == TARGET }
        val targetPaid = targetPaymentList.map { it.amount }.sumOf { it }
        val targetCollectedList = withdrawalVM.withdrawDataWithMemberIdForCalculations.value.filter { it.purposeOfWithdraw == TARGET }
        val targetCollected = targetCollectedList.map { it.amount }.sumOf { it }
        val targetBalance = targetPaid-targetCollected

        val pledgePaymentList = paymentVM.paymentDataWithMemberId.value.filter { it.purposeOfPayment == PLEDGE }
        val pledgePaid = pledgePaymentList.map { it.amount }.sumOf { it }
        val pledgeCollectedList = withdrawalVM.withdrawDataWithMemberIdForCalculations.value.filter { it.purposeOfWithdraw == PLEDGE }
        val pledgeCollected = pledgeCollectedList.map { it.amount }.sumOf { it }
        val pledgeBalance =  pledgePaid-pledgeCollected



        // Payment Summary
        val totalpledge by remember { mutableStateOf("9088888") }
        val totalAdminFee by remember { mutableStateOf("100000") }
        val totalTarget by remember { mutableStateOf("2000000") }
        val totalLoan by remember { mutableStateOf("3000000") }
        val totalSif by remember { mutableStateOf("40000000") }
        val totalAvalableBal by remember { mutableStateOf("50000000") }
        val adminFee by remember { mutableStateOf("200") }
        val pledge by remember { mutableStateOf("50000") }


        // Add Payment
        var amountHide by remember { mutableStateOf(false) }
        var modeOfPaymentHide by remember { mutableStateOf(false) }
        val modeOfPaymentList = listOf<String>(
             "Transfer", "Cash"
        )
        var selectedModeOfPayment by remember { mutableStateOf(modeOfPaymentList[0]) }

        var amountPayment by remember { mutableStateOf("") }


        var purposeOfPaymentHide by remember { mutableStateOf(false) }
        val purposeOfPaymentList = listOf<String>(
            "Target", "Loan", "Admin", "Pledge", "SIG"
        )

        var selectedPurposeOfPayment by remember { mutableStateOf(purposeOfPaymentList[0]) }

        var monthPledgePaymentHide by remember { mutableStateOf(false) }
        val monthPledgePaymentList = listOf<String>(
            "January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"
        )

        var selectedMonthPledgePayment by remember { mutableStateOf(monthPledgePaymentList[0]) }


        var dateOfPaymenthHide by remember { mutableStateOf(false) }

        val calendar = Calendar.getInstance()
        calendar.set(2022, 1, 12) // add year, month (Jan), date
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = calendar.timeInMillis,
            initialDisplayMode = DisplayMode.Picker

        )
        val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.ROOT)
        var dateSelected = formatter.format(Date(datePickerState.selectedDateMillis!!))


        var paymentReftHide by remember { mutableStateOf(false) }
        var paymentRef by remember { mutableStateOf("") }


        // Add Withdraw
        var amountWithdrawHide by remember { mutableStateOf(false) }
        var amountWithdraw by remember { mutableStateOf("") }

        var chequeNoHide by remember { mutableStateOf(false) }
        var chequeNo by remember { mutableStateOf("") }

        var voucherNoHide by remember { mutableStateOf(false) }
        var voucherNo by remember { mutableStateOf("") }

        var accountCodeHide by remember { mutableStateOf(false) }
        var accountCode by remember { mutableStateOf("") }

        var purposeOfWithdrawHide by remember { mutableStateOf(false) }
        val purposeOfWithdrawList = listOf<String>(
            "", "Target", "Pledge", "Admin", "SIF", "Loan"
        )

        var selectedPurposeOfWithdraw by remember { mutableStateOf(purposeOfWithdrawList[0]) }

        var dateOfWithdrawHide by remember { mutableStateOf(false) }
        val datePickerStateWithdraw = rememberDatePickerState(
            initialSelectedDateMillis = calendar.timeInMillis,
            initialDisplayMode = DisplayMode.Picker

        )
        val withdrawDateSelected = formatter.format(Date(datePickerStateWithdraw.selectedDateMillis!!))


        Column(modifier = Modifier.padding(it)) {
            if(snackbarVisible){
                Snackbar(
                    modifier=Modifier.padding(horizontal = 15.dp),
                    backgroundColor = Color.Black,
                    contentColor = Color.White,
                    action = {
                        Text(modifier = Modifier.clickable {
                            snackbarVisible=false
                        }, text = "Dismiss", color = Color.White, fontSize = 10.sp)
                    }) {
                   Text(text = snackbarMessage, color = Color.White, fontSize = 12.sp )
                }
            }
            MemberDetailSection(
                listState = homeVM.listState.value,
                onPaymentClick = { homeVM.listState.value = PAYMENT_LIST},
                onWithdrawalClick = {homeVM.listState.value = WITHDRAW_LIST},
                paymentType = homeVM.paymentType.value,
                onAllPaymentClick={
                    paymentVM.getPaymentWithMemberID()
                    homeVM.paymentType.value = ALL_PAYMENT_TYPE
                },
                onTargetClick = {
                    paymentVM.getPaymentWithMemberIDAndTarget()
                    homeVM.paymentType.value = TARGET},
                onLoanCLick = {
                    paymentVM.getPaymentWithMemberIDAndLoan()
                    homeVM.paymentType.value = LOAN},
                onAdminClick = {
                    paymentVM.getPaymentWithMemberIDAndAdmin()
                    homeVM.paymentType.value = ADMIN},
                onPledgeClick = {
                    paymentVM.getPaymentWithMemberIDAndPledge()
                    homeVM.paymentType.value = PLEDGE},
                onSifClick = {
                    paymentVM.getPaymentWithMemberIDAndSif()
                    homeVM.paymentType.value = SIF},
                // State Of Account for Member
                onTotalPaymentClick = { navToStatement(memberId)},
                onProfileClick = {
                    navToProfileScreen(memberId)
//                    homeVM.listState.value = PROFILE_LIST
                },
                withdrawType = homeVM.withdrawType.value,
                onWithdrawLoanClick = {
                    if (homeVM.withdrawType.value == ""){
                        withdrawalVM.getWithdrawalWithMemberIDAndLoan()
                        homeVM.withdrawType.value = WITHDRAW_LOAN
                    }else{
                        withdrawalVM.getWithdrawalWithMemberID()
                        homeVM.withdrawType.value = ""
                    }

                },
                onWithdrawClick = {
                    chequeNo=""
                    voucherNo=""
                    accountCode=""
                    amountWithdraw=""
                    withdrawalVM.purposeOfWithdraw.value = ""
                    withdrawalVM.objectId.value = ""
                    withdrawalVM.withdrawMonth.value = ""
                    withdrawalVM.withdrawYear.value = ""
                    withdrawalVM.chequeNo.value = ""
                    withdrawalVM.voucherNo.value = ""
                    withdrawalVM.withdrawDate.value = ""
                    withdrawalVM.amount.value = ""
                    withdrawalVM.accountCode.value = ""
                    selectedPurposeOfWithdraw =""
                    homeVM.listState.value = ADD_WITHDRAW_LIST},
                onAddPaymentClick = {
                    paymentVM.objectId.value=""
                    paymentVM.modeOfPayment.value=""
                    paymentVM.purposeOfPayment.value=""
                    selectedModeOfPayment = ""
                    selectedPurposeOfPayment=""
                    selectedMonthPledgePayment=""
                    paymentRef=""
                    amountPayment=""
                    paymentVM.paymentRefrences.value=""
                    paymentVM.paymentDate.value=""
                    paymentVM.paymentMonth.value=""
                    paymentVM.month.value=""
                    paymentVM.paymentYear.value=""
                    paymentVM.amount.value=""
                    homeVM.listState.value= ADD_PAYMENT_LIST },
                payment = paymentVM.paymentDataWithMemberId.value,
                onRefreshSlide = {isRefreshingPayment = true},
                swipeRefreshState = swipeRefreshStatePayment,
                onEditClick = {
                    paymentVM.objectId.value=it._id.toHexString()
                    paymentVM.modeOfPayment.value=it.modeOfPayment
                    paymentVM.purposeOfPayment.value=""
                    selectedModeOfPayment = it.modeOfPayment
                    selectedPurposeOfPayment=""
                    selectedMonthPledgePayment=""
                    paymentRef=it.paymentReference
                    amountPayment=""
                    paymentVM.paymentRefrences.value=it.paymentReference
                    paymentVM.paymentDate.value=it.paymentDate
                    paymentVM.paymentMonth.value=it.paymentMonth
                    paymentVM.month.value=""
                    paymentVM.paymentYear.value=it.paymentYear
                    paymentVM.amount.value=""
                    homeVM.listState.value= ADD_PAYMENT_LIST
                },
                onDeleteClick = {
                    paymentVM.deleteGtrPayment(it._id.toHexString())
                },
                withdraw = withdrawalVM.withdrawDataWithMemberId.value.sortedByDescending { it._id },
                onRefreshSlideWithdraw = {isRefreshingWithdraw = true},
                swipeRefreshStateWithdraw = swipeRefreshStateWithdraw,
                onEditClickWithdraw = {
                    withdrawalVM.objectId.value=it._id.toHexString()
                    chequeNo=it.chequeNo
                    voucherNo=it.voucherNo
                    accountCode=it.accountCode
                    amountWithdraw=it.amount.toString()
                    withdrawalVM.purposeOfWithdraw.value = it.purposeOfWithdraw
                    withdrawalVM.withdrawMonth.value = it.withdrawalMonth
                    withdrawalVM.withdrawYear.value = it.withdrawalYear
                    withdrawalVM.chequeNo.value = it.chequeNo
                    withdrawalVM.voucherNo.value = it.voucherNo
                    withdrawalVM.withdrawDate.value = it.withdrawDate
                    withdrawalVM.amount.value = it.amount.toString()
                    withdrawalVM.accountCode.value = it.accountCode
                    selectedPurposeOfWithdraw =it.purposeOfWithdraw
                    homeVM.listState.value = ADD_WITHDRAW_LIST
        },
                onDeleteClickWithdraw = {
                    withdrawalVM.deleteGtrWithdrawal(it._id.toHexString())
                },

//                onPledgeBtnClick = {
//                    isEditingPayment = false
//                },
                loanBalance = loanBalance.toString(),
                pledgeBal =pledgeBalance.toString(),
                targetBal = targetBalance.toString(),
                totalPledge = totalpledge,
                totalAdminFee = totalAdminFee,
                totalTarget = totalTarget,
                totalLoan = totalLoan,
                totalSif = totalSif,
                totalAvalableBal =totalAvalableBal,

                onPersonalClick = {homeVM.listState.value = PERSONAL_INFOR_PROFILE_LIST},
                onLocationClick = {homeVM.listState.value = LOCATION_INFO_PROFILE_LIST},
                onEmploymentClick = {homeVM.listState.value = EMPLOYMENT_INFO_PROFILE_LIST},
                onNextOfKinClick = {homeVM.listState.value = NEXT_OF_KIN_PROFILE_LIST},
                onRefereeClick = {homeVM.listState.value = REFEREE_INFOR_PROFILE_LIST},

                selectedModeOfPayment = selectedModeOfPayment,
                modeOfPaymentList = modeOfPaymentList,
                modeOfPayment = selectedModeOfPayment,
                onDrowdownModeOfPaymentMenuClick = {
                    selectedModeOfPayment = it
                    paymentVM.modeOfPayment.value = it
                    modeOfPaymentHide=false
                },
                amountHide = amountHide,
                modeOfPaymentHide = modeOfPaymentHide,
                amount =  when(selectedPurposeOfPayment) {
                    "Admin" -> {
                        adminFee
                    }
                    "Pledge" -> {
                        pledge
                    }else -> {
                        amountPayment
                    }

                },
                amountPaidText = paymentVM.amount.value,
                onAmountChange = { amountPayment = it.filter { it.isDigit() } },
                onEditAmountClick = { amountHide = true },
                onDoneAmountClick = {
                    when(selectedPurposeOfPayment){
                        "Admin" -> {
                            paymentVM.amount.value = adminFee
                        }
                        "Pledge" -> {
                            paymentVM.amount.value = pledge
                        }else -> {
                        paymentVM.amount.value =amountPayment
                    }

                    }
                    amountHide = false },
                onAddClick = {
                    if (
                        paymentVM.memberId.value.isNotEmpty() && paymentVM.modeOfPayment.value.isNotEmpty() &&
                        paymentVM.purposeOfPayment.value.isNotEmpty() && paymentVM.paymentRefrences.value.isNotEmpty() &&
                        paymentVM.paymentDate.value.isNotEmpty() && paymentVM.paymentMonth.value.isNotEmpty() &&
                        paymentVM.paymentYear.value.isNotEmpty() && paymentVM.amount.value.isNotEmpty()
                    ){
                            if (paymentVM.purposeOfPayment.value == "Pledge"){
                                if (paymentVM.amount.value == pledge && paymentVM.month.value.isNotEmpty()
                                ){
                                    paymentVM.insertGrtPayment()
                                    homeVM.listState.value= PAYMENT_LIST
                                }else{
                                    snackbarMessage="Pledge Amount Or Pledge Month is Not Correct"
                                    snackbarVisible=true
                                }
                            }else{
                                paymentVM.insertGrtPayment()
                                homeVM.listState.value= PAYMENT_LIST
                            }
                        }
                    else {
                        snackbarMessage="Fill Empty Field"
                        snackbarVisible=true
                    }
                },
                onUpdatePaymentClick = {
                    if (
                        paymentVM.memberId.value.isNotEmpty() && paymentVM.modeOfPayment.value.isNotEmpty() &&
                        paymentVM.purposeOfPayment.value.isNotEmpty() && paymentVM.paymentRefrences.value.isNotEmpty() &&
                        paymentVM.paymentDate.value.isNotEmpty() && paymentVM.paymentMonth.value.isNotEmpty() &&
                        paymentVM.paymentYear.value.isNotEmpty() && paymentVM.amount.value.isNotEmpty() && paymentVM.objectId.value.isNotEmpty()
                    ){
                        if (paymentVM.purposeOfPayment.value == "Pledge"){
                            if (paymentVM.amount.value == pledge && paymentVM.month.value.isNotEmpty()
                            ){
                                paymentVM.updateGtrPayment()
                                homeVM.listState.value= PAYMENT_LIST
                            }else{
                                snackbarMessage="Pledge Amount Or Pledge Month is Not Correct"
                                snackbarVisible=true
                            }
                        }else{
                            paymentVM.updateGtrPayment()
                            homeVM.listState.value= PAYMENT_LIST
                        }
                    }
                    else {
                        snackbarMessage="Fill Empty Field"
                        snackbarVisible=true
                    }
                },
                onEditModeOfPayment = {modeOfPaymentHide=true},
                purposeOfPaymentHide=purposeOfPaymentHide,
                selectedPurposeOfPayment=selectedPurposeOfPayment,
                purposeOfPaymentList=purposeOfPaymentList,
                purposeOfPayment=selectedPurposeOfPayment,
                onEditPurposeOfPayment={purposeOfPaymentHide=true},
                onDrowdownPurposeOfPaymentMenuClick={
                    selectedPurposeOfPayment=it
                    paymentVM.purposeOfPayment.value=it
                    purposeOfPaymentHide=false},

                monthPledgePaymentHide=monthPledgePaymentHide,
                selectedMonthPledgePayment=selectedMonthPledgePayment,
                monthPledgePaymentList=monthPledgePaymentList,
                monthPledgePayment=selectedMonthPledgePayment,
                onEditMonthPledge={monthPledgePaymentHide=true},
                onDrowdownMonthPledgePaymentMenuClick={
                    selectedMonthPledgePayment=it
                    paymentVM.month.value=it
                    monthPledgePaymentHide=false
                },


                dateOfPaymenthHide = dateOfPaymenthHide,

                onDatePick = {
                    paymentVM.paymentDate.value=dateSelected
                    val separtor = " "
                    val dateList = dateSelected.split(separtor)
                    paymentVM.paymentMonth.value=dateList[1]
                    paymentVM.paymentYear.value=dateList[2]
                    dateOfPaymenthHide = false},
                onEditDateClick = {dateOfPaymenthHide=true},
                selectedDate = paymentVM.paymentDate.value,
                datePickerState = datePickerState,


                paymentReftHide = paymentReftHide,
                paymentRef = paymentRef,
                paymentRefText = paymentVM.paymentRefrences.value,
                onEditPaymentRefClick = {paymentReftHide=true},
                onPaymentRefChange = { paymentRef=it},
                onDonePaymentRefClick = {
                    paymentVM.paymentRefrences.value =paymentRef
                    paymentReftHide=false},


                amountWithdrawHide = amountWithdrawHide,
                amountWithdraw = amountWithdraw,
                amountWithdrawText = withdrawalVM.amount.value,
                onAmountWithdrawChange = {amountWithdraw = it.filter { it.isDigit() }},
                onEditWithdrawAmountClick = { amountWithdrawHide = true },
                onDoneWithdrawAmountClick = {
                    withdrawalVM.amount.value=amountWithdraw
                    amountWithdrawHide = false },

                onUpdateWithdrawClick={
                    if (
                        withdrawalVM.memberId.value.isNotEmpty() && withdrawalVM.purposeOfWithdraw.value.isNotEmpty() &&
                        withdrawalVM.withdrawDate.value.isNotEmpty() && withdrawalVM.withdrawMonth.value.isNotEmpty() &&
                        withdrawalVM.withdrawYear.value.isNotEmpty() && withdrawalVM.chequeNo.value.isNotEmpty() &&
                        withdrawalVM.accountCode.value.isNotEmpty() && withdrawalVM.voucherNo.value.isNotEmpty() &&
                        withdrawalVM.amount.value.isNotEmpty() && withdrawalVM.objectId.value.isNotEmpty()
                    ){
                        withdrawalVM.updateGtrWithdrawal()
                        homeVM.listState.value= WITHDRAW_LIST
                    }else{
                        snackbarMessage="Fill Empty Field"
                        snackbarVisible=true
                    }
                },
                onWithdrawAddClick = {
                    if (
                        withdrawalVM.memberId.value.isNotEmpty() && withdrawalVM.purposeOfWithdraw.value.isNotEmpty() &&
                        withdrawalVM.withdrawDate.value.isNotEmpty() && withdrawalVM.withdrawMonth.value.isNotEmpty() &&
                        withdrawalVM.withdrawYear.value.isNotEmpty() && withdrawalVM.chequeNo.value.isNotEmpty() &&
                        withdrawalVM.accountCode.value.isNotEmpty() && withdrawalVM.voucherNo.value.isNotEmpty() &&
                        withdrawalVM.amount.value.isNotEmpty()
                    ){
                        withdrawalVM.insertGrtWithdrawal()
                        homeVM.listState.value= WITHDRAW_LIST
                    }else{
                        snackbarMessage="Fill Empty Field"
                        snackbarVisible=true
                    }
                 },

                purposeOfWithdrawHide = purposeOfWithdrawHide,
                selectedPurposeOfWithdraw = selectedPurposeOfWithdraw,
                purposeOfWithdrawList = purposeOfWithdrawList,
                purposeOfWithdraw = selectedPurposeOfWithdraw,
                onEditPurposeOfWithdraw = { purposeOfWithdrawHide=true },
                onDrowdownPurposeOfWithdrawMenuClick ={
                    selectedPurposeOfWithdraw=it
                    withdrawalVM.purposeOfWithdraw.value=it
                    purposeOfWithdrawHide=false
                } ,

                dateOfWithdrawthHide = dateOfWithdrawHide,
                onDateWithdrawPick = {
                    withdrawalVM.withdrawDate.value=withdrawDateSelected
                    val separtor = " "
                    val dateList = withdrawDateSelected.split(separtor)
                    withdrawalVM.withdrawMonth.value=dateList[1]
                    withdrawalVM.withdrawYear.value=dateList[2]
                    dateOfWithdrawHide = false },
                onEditWithdrawDateClick = { dateOfWithdrawHide=true},
                selectedWithdrawDate = withdrawalVM.withdrawDate.value,
                WithdrawDatePickerState = datePickerStateWithdraw,

                chequeNoHide = chequeNoHide,
                chequeNo = chequeNo,
                chequeNoText = withdrawalVM.chequeNo.value,
                onEditChequeNoClick = { chequeNoHide=true },
                onChequeNoChange = {chequeNo=it},
                onDoneChequeNoClick = {
                    withdrawalVM.chequeNo.value=chequeNo
                    chequeNoHide=false },

                voucherNoHide = voucherNoHide,
                voucherNo = voucherNo,
                voucherNoText = withdrawalVM.voucherNo.value,
                onEditVoucherNoClick = { voucherNoHide=true },
                onvoucherNoChange = {voucherNo = it},
                onDonevoucherNoClick = {
                    withdrawalVM.voucherNo.value=voucherNo
                    voucherNoHide = false },


                accountCodeHide = accountCodeHide,
                accountCode = accountCode,
                accountCodeText =withdrawalVM.accountCode.value,
                onEditAccountCodeClick = { accountCodeHide=true },
                onAccountCodeChange ={accountCode=it },
                onDoneAccountCodeClick = {
                    withdrawalVM.accountCode.value=accountCode
                    accountCodeHide=false},
                objectId=paymentVM.objectId.value,
                withdrawalObjectId=withdrawalVM.objectId.value,
                finOrSec = finOrSecKey.value


            )
        }
        Log.e(TAG, "memberId: ${paymentVM.memberId.value}", )
        Log.e(TAG, "memberId: ${paymentVM.objectId.value}", )
        Log.e(TAG, "modeOfPayment: ${paymentVM.modeOfPayment.value}", )
        Log.e(TAG, "purposeOfPayment: ${paymentVM.purposeOfPayment.value}", )
        Log.e(TAG, "paymentRefrences: ${paymentVM.paymentRefrences.value}", )
        Log.e(TAG, "paymentDate: ${paymentVM.paymentDate.value}", )
        Log.e(TAG, "paymentMonth: ${paymentVM.paymentMonth.value}", )
        Log.e(TAG, "month: ${paymentVM.month.value}", )
        Log.e(TAG, "paymentYear: ${paymentVM.paymentYear.value}", )
        Log.e(TAG, "amount: ${paymentVM.amount.value}", )
//        Log.e(TAG, "memberId: ${withdrawalVM.memberId.value}", )
//        Log.e(TAG, "purposeOfWithdraw: ${withdrawalVM.purposeOfWithdraw.value}", )
//        Log.e(TAG, "withdrawDate: ${withdrawalVM.withdrawDate.value}", )
//        Log.e(TAG, "withdrawMonth: ${withdrawalVM.withdrawMonth.value}", )
//        Log.e(TAG, "withdrawYear: ${withdrawalVM.withdrawYear.value}", )
//        Log.e(TAG, "chequeNo: ${withdrawalVM.chequeNo.value}", )
//        Log.e(TAG, "voucherNo: ${withdrawalVM.voucherNo.value}", )
//        Log.e(TAG, "accountCode: ${withdrawalVM.accountCode.value}", )
//        Log.e(TAG, "description: ${withdrawalVM.description.value}", )
//        Log.e(TAG, "amount: ${withdrawalVM.amount.value}", )


    }
}


@Composable
fun ProfileDetails(
    modifier: Modifier=Modifier,
    onPersonalClick:()-> Unit,
    onLocationClick:()-> Unit,
    onEmploymentClick:()-> Unit,
    onNextOfKinClick:()-> Unit,
    onRefereeClick:()-> Unit,
){
    Column(modifier.fillMaxWidth()) {
        Column(
            modifier= Modifier
                .weight(5f)
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(shape = CircleShape) {
                Image(
                    painter = painterResource(id = R.drawable.empty_image),
                    contentDescription = "empty_image"
                )
            }

        }
        Row( modifier= Modifier
            .weight(2f)
            .padding(horizontal = 10.dp)
            .clickable { onPersonalClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier.weight(1f),
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color.White,
                    containerColor = Color.Black
                ),
                onClick = { onPersonalClick() }) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Person")
            }
            Text(
                modifier = Modifier.weight(9f),
                text = "Personal Information",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
        Row( modifier= Modifier
            .weight(2f)
            .padding(horizontal = 10.dp)
            .clickable { onLocationClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier.weight(1f),
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color.White,
                    containerColor = Color.Black
                ),
                onClick = { onLocationClick() }) {
                Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Person")
            }
            Text(
                modifier = Modifier.weight(9f),
                text = "Contact Information",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
        Row( modifier= Modifier
            .weight(2f)
            .padding(horizontal = 10.dp)
            .clickable { onEmploymentClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier.weight(1f),
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color.White,
                    containerColor = Color.Black
                ),
                onClick = { onEmploymentClick() }) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Person")
            }
            Text(
                modifier = Modifier.weight(9f),
                text = "Employment Information",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
        Row( modifier= Modifier
            .weight(2f)
            .padding(horizontal = 10.dp)
            .clickable { onNextOfKinClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier.weight(1f),
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color.White,
                    containerColor = Color.Black
                ),
                onClick = { onNextOfKinClick() }) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Person")
            }
            Text(
                modifier = Modifier.weight(9f),
                text = "Next of Kin Information",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
        Row( modifier= Modifier
            .weight(2f)
            .padding(horizontal = 10.dp)
            .clickable { onRefereeClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onRefereeClick() },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color.White,
                    containerColor = Color.Black
                ),
                onClick = { onRefereeClick() }) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Person")
            }
            Text(
                modifier = Modifier.weight(9f),
                text = "Referee Information",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }

}


@Composable
fun PaymentSummary(
    modifier: Modifier=Modifier,
    pledge: String,
    adminFee: String,
    target: String,
    loan: String,
    sif: String,
    avalableBal: String,
){
    Column(modifier.fillMaxWidth()) {
        LazyColumn{
            item {
                Column(modifier=Modifier.fillMaxWidth()) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier= Modifier
                                .weight(3f)
                                .padding(start = 5.dp),
                            text = "Pledge: ",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                        Card(
                            modifier= Modifier
                                .weight(7f)
                                .padding(end = 5.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Black,
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                modifier= Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp),
                                text = pledge,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                }
                Spacer(modifier = Modifier.padding(10.dp))
            }
            item {
                Column(modifier=Modifier.fillMaxWidth()) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier= Modifier
                                .weight(3f)
                                .padding(start = 5.dp),
                            text = "Admin Fee: ",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                        Card(
                            modifier= Modifier
                                .weight(7f)
                                .padding(end = 5.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Black,
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                modifier= Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp),
                                text = adminFee,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                }
                Spacer(modifier = Modifier.padding(10.dp))
            }
            item {
                Column(modifier=Modifier.fillMaxWidth()) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier= Modifier
                                .weight(3f)
                                .padding(start = 5.dp),
                            text = "Target: ",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                        Card(
                            modifier= Modifier
                                .weight(7f)
                                .padding(end = 5.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Black,
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                modifier= Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp),
                                text = target,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                }
                Spacer(modifier = Modifier.padding(10.dp))
            }
            item {
                Column(modifier=Modifier.fillMaxWidth()) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier= Modifier
                                .weight(3f)
                                .padding(start = 5.dp),
                            text = "loan: ",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                        Card(
                            modifier= Modifier
                                .weight(7f)
                                .padding(end = 5.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Black,
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                modifier= Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp),
                                text = loan,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                }
                Spacer(modifier = Modifier.padding(10.dp))
            }
            item {
                Column(modifier=Modifier.fillMaxWidth()) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier= Modifier
                                .weight(3f)
                                .padding(start = 5.dp),
                            text = "SIF: ",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                        Card(
                            modifier= Modifier
                                .weight(7f)
                                .padding(end = 5.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Black,
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                modifier= Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp),
                                text = sif,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                }
                Spacer(modifier = Modifier.padding(10.dp))
            }
            item {
                Column(modifier=Modifier.fillMaxWidth()) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier= Modifier
                                .weight(3f)
                                .padding(start = 5.dp),
                            text = "Available Balance: ",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                        Card(
                            modifier= Modifier
                                .weight(7f)
                                .padding(end = 5.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Black,
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                modifier= Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp),
                                text = avalableBal,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                }
                Spacer(modifier = Modifier.padding(10.dp))
            }

        }
    }

}





@Composable
fun PaymentOrWithdraw(
    modifier: Modifier=Modifier,
    onPaymentClick: ()-> Unit,
    onWithdrawalClick: ()-> Unit,
    listState: String,
){
    Row(modifier.fillMaxWidth()) {
                Button(
                    modifier= Modifier.weight(5f),
                    onClick = { onPaymentClick() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (listState == PAYMENT_LIST) {
                            Color.Black
                        }else{
                            Color.White
                        },
                        contentColor =
                        if (listState == PAYMENT_LIST) {
                            Color.White
                        }else {
                            Color.Black
                        }
                    )
                ) {
                    Text(text = "Payment", fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Button(
                    modifier= Modifier.weight(5f),
                    onClick = { onWithdrawalClick() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (listState == WITHDRAW_LIST) {
                            Color.Black
                        }else{
                            Color.White
                        },
                        contentColor =
                        if (listState == WITHDRAW_LIST) {
                            Color.White
                        }else {
                            Color.Black
                        }
                    )
                ) {
                    Text(text = "Withdrawal", fontWeight = FontWeight.Bold)
                }
            }

}


@Composable
fun PaymentTypes(
    modifier: Modifier=Modifier,
    paymentType: String,
    onAllPaymentClick: ()-> Unit,
    onTargetClick: ()-> Unit,
    onLoanCLick: ()-> Unit,
    onAdminClick: ()-> Unit,
    onPledgeClick: ()-> Unit,
    onSifClick: ()-> Unit,
    onTotalPaymentClick: ()-> Unit,
    onProfileClick: ()-> Unit,
    listState: String,
    onAddPaymentClick: ()-> Unit,
    secOrFin: String

){

    Row(modifier.padding(1.dp)) {
        val pad = 20.dp
        LazyRow{
            item {
                Column(modifier = Modifier
                    .padding(horizontal = pad)
                    .clickable { onAllPaymentClick() }
                    .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (paymentType == ALL_PAYMENT_TYPE){
                        Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "CheckCircle", tint = Color.Black)
                    }else{
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "AccountCircle", tint = Color.Black)
                    }
                    Text(
                        text = "All Payment",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center)
                }
                Column(modifier = Modifier
                    .padding(horizontal = pad)
                    .clickable { onTargetClick() }
                    .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (paymentType == TARGET){
                        Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "CheckCircle", tint = Color.Black)
                    }else{
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "AccountCircle", tint = Color.Black)
                    }
                    Text(
                        text = TARGET,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center)
                }
                Column(modifier = Modifier
                    .padding(horizontal = pad)
                    .clickable { onLoanCLick() }
                    .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (paymentType == LOAN){
                        Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "CheckCircle", tint = Color.Black)
                    }else{
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "AccountCircle", tint = Color.Black)
                    }
                    Text(
                        text = LOAN,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center)
                }

                Column(modifier = Modifier
                    .padding(horizontal = pad)
                    .clickable { onAdminClick() }
                    .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (paymentType == ADMIN){
                        Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "CheckCircle", tint = Color.Black)
                    }else{
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "AccountCircle", tint = Color.Black)
                    }
                    Text(
                        text = ADMIN,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center)
                }
                Column(modifier = Modifier
                    .padding(horizontal = pad)
                    .clickable { onPledgeClick() }
                    .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (paymentType == PLEDGE){
                        Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "CheckCircle", tint = Color.Black)
                    }else{
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "AccountCircle", tint = Color.Black)
                    }
                    Text(
                        text = PLEDGE,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center)
                }
                Column(modifier = Modifier
                    .padding(horizontal = pad)
                    .clickable { onSifClick() }
                    .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (paymentType == SIF){
                        Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "CheckCircle", tint = Color.Black)
                    }else{
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "AccountCircle", tint = Color.Black)
                    }
                    Text(
                        text = SIF,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center)
                }
                if (secOrFin == "fin"){
                    Column(modifier = Modifier
                        .padding(horizontal = pad)
                        .clickable { onAddPaymentClick() }
                        .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (listState == ADD_PAYMENT_LIST){
                            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "CheckCircle", tint = Color.Black)
                        }else{
                            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "AccountCircle", tint = Color.Black)
                        }
                        Text(
                            text = "Add Payment",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center)

                    }
                }

                Column(modifier = Modifier
                    .padding(horizontal = pad)
                    .clickable { onTotalPaymentClick() }
                    .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (listState == PAYMENT_SUMMARY_LIST){
                        Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "CheckCircle", tint = Color.Black)
                    }else{
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "AccountCircle", tint = Color.Black)
                    }
                    Text(
                        text = "Yearly Record",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center)

                }
                Column(modifier = Modifier
                    .padding(horizontal = pad)
                    .clickable { onProfileClick() }
                    .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (listState == PROFILE_LIST){
                        Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "CheckCircle", tint = Color.Black)
                    }else{
                        Icon(imageVector = Icons.Default.Person, contentDescription = "Person", tint = Color.Black)
                    }
                    Text(
                        text = "Profile Details",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center)

                }

            }
        }

    }

}


@Composable
fun WithdrawalTypes(
    modifier: Modifier=Modifier,
    withdrawType: String,
    onWithdrawLoanClick: ()-> Unit,
    onWithdrawClick: ()-> Unit,
    listState: String,
    secOrFin: String,
){
    Row(modifier.fillMaxWidth()) {
        Column(modifier = Modifier
            .weight(5f)
            .clickable { onWithdrawLoanClick() }
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (withdrawType == WITHDRAW_LOAN){
                Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "CheckCircle", tint = Color.Black)
            }else{
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "AccountCircle", tint = Color.Black)
            }
            Text(
                text = "Loan",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center)
        }

        if (secOrFin == "fin"){
            Column(modifier = Modifier
                .weight(5f)
                .clickable { onWithdrawClick() }
                .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (listState == ADD_WITHDRAW_LIST){
                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "CheckCircle", tint = Color.Black)
                }else{
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "AccountCircle", tint = Color.Black)
                }
                Text(
                    text = "Withdraw",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center)
            }
        }

    }
}



@Composable
fun PaymentList(
    modifier: Modifier = Modifier,
    payment: List<GrtPayment>,
    onRefreshSlide: () -> Unit,
    swipeRefreshState: SwipeRefreshState,
    onEditClick: (GrtPayment)-> Unit,
    onDeleteClick: (GrtPayment)-> Unit,
    loanBalance: String,
    paymentType: String,
    targetBal: String,
    pledgeBal: String,
    secOrFin: String,

){
    Column(modifier.fillMaxWidth()) {
        Text(text = "Payment History", fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.padding(5.dp))
        when(paymentType){
            TARGET ->{
                Text(
                    modifier= Modifier
                        .fillMaxWidth()
                        .background(Color.Black)
                        .padding(vertical = 10.dp),
                    text = "Target Balance: ₦$targetBal",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontWeight = FontWeight.Bold

                )
            }
            PLEDGE ->{
                Text(
                    modifier= Modifier
                        .fillMaxWidth()
                        .background(Color.Black)
                        .padding(vertical = 10.dp),
                    text = "Pledge Balance: $pledgeBal",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontWeight = FontWeight.Bold

                )
            }
            LOAN ->{
                if (loanBalance != "0"){
                    Text(
                        modifier= Modifier
                            .fillMaxWidth()
                            .background(Color.Black)
                            .padding(vertical = 10.dp),
                        text = "Loan Balance: $loanBalance",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontWeight = FontWeight.Bold

                    )
                }
            }
        }


        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { onRefreshSlide()},
        ) {
            if (payment.isNotEmpty()){
                LazyColumn(modifier = Modifier.fillMaxWidth() ){
                    items(items = payment) {
                        PaymentUi(
                            payment = it,
                            onEditClick = {
                                if (secOrFin == "fin"){
                                    onEditClick(it)
                                }
                            },
                            onDeleteClick = {
                                if (secOrFin == "fin"){
                                    onDeleteClick(it)
                                }
                            },
                            memberId = ""

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

@Composable
fun PaymentUi(
    modifier: Modifier = Modifier,
    payment: GrtPayment,
    memberId:String,
    onEditClick: ()-> Unit,
    onDeleteClick: ()-> Unit,
){
    var hide by remember { mutableStateOf(false) }
    Column(
        modifier
            .fillMaxWidth()
            .clickable { hide = !hide }) {
        if (!hide){
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Column(modifier = Modifier
                    .weight(6f)) {
                    Row(modifier = Modifier
                        .fillMaxWidth()) {
                        Text(modifier = Modifier
                            .weight(5f), text = payment.purposeOfPayment, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Text(modifier = Modifier
                            .weight(5f),text = payment.modeOfPayment, fontSize = 14.sp)

                    }

                    Text(text = payment.paymentReference, fontSize = 12.sp)
                    Spacer(modifier = Modifier.padding(5.dp))
                    if (memberId.isNotEmpty()){
                        Text(text = memberId, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }

                }

                Column(modifier = Modifier
                    .weight(4f)
                    .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = payment.month, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.padding(3.dp))
                    Card(shape = RoundedCornerShape(10.dp), elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)) {
                        Text(modifier = Modifier.padding(5.dp), text = payment.amount.toString())
                    }
                    Spacer(modifier = Modifier.padding(3.dp))
                    Text(text = payment.paymentDate, fontSize = 12.sp)
                }
            }
        }
        else{
            Row(Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = { onEditClick() }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription ="Edit" )
                }
                IconButton(onClick = { onDeleteClick() }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription ="Delete" )
                }
            }
        }
        Spacer(modifier = Modifier.padding(5.dp))
        Divider(modifier = Modifier.fillMaxWidth(), thickness = 2.dp, color = Color.Black)
    }


}


@Composable
fun WithdrawalList(
    modifier: Modifier = Modifier,
    onRefreshSlide: () -> Unit,
    swipeRefreshState: SwipeRefreshState,
    onEditClick: (GrtWithdrawal)-> Unit,
    onDeleteClick: (GrtWithdrawal)-> Unit,
    withdraw: List<GrtWithdrawal>,
    secOrFin: String
    ){

    Column(modifier.fillMaxWidth()) {

        Text(text = "Withdrawal History")

        Spacer(modifier = Modifier.padding(5.dp))

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { onRefreshSlide()},
        ) {
            if (withdraw.isNotEmpty()){
                LazyColumn(modifier = Modifier.fillMaxWidth() ){
                    items(items = withdraw) {
                        WithdrawalUi(
                            withdraw = it,
                            onDeleteClick = {
                                if (secOrFin == "fin"){
                                    onDeleteClick(it)
                                }
                            },
                            onEditClick = {
                                if (secOrFin == "fin"){
                                    onEditClick(it)
                                }
                            },
                            purposeOfWithdraw = it.purposeOfWithdraw,
                            memberId = "",

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

@Composable
fun WithdrawalUi(
    withdraw: GrtWithdrawal,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit,
    purposeOfWithdraw: String,
    memberId: String,

){
    var hide by remember { mutableStateOf(true) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { hide =!hide }) {
        if (hide){
            Column(modifier = Modifier.fillMaxWidth()
            ) {
                Row {
                    Column(modifier = Modifier.weight(6f)) {
                        Row {
                            Column(modifier = Modifier.weight(5f)) {
                                Text(text = "Acct. Code", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                                Text(text = withdraw.accountCode, fontSize = 12.sp)
                            }
                            Column(modifier = Modifier.weight(5f)) {
                                Text(text = "Cheque No", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                                Text(text = withdraw.chequeNo, fontSize = 12.sp)
                            }
                        }
                        Spacer(modifier = Modifier.padding(2.dp))

                        if (memberId.isNotEmpty()){
                            Text(modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                                text = memberId, fontSize = 14.sp)
                        }else{
                            Text(modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                                text = purposeOfWithdraw, fontSize = 14.sp)
                        }

                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    Column(modifier = Modifier
                        .weight(4f)
                        .fillMaxWidth()) {
                        Text(text = "Voucher No: ${withdraw.voucherNo}", fontSize = 14.sp)
                        Spacer(modifier = Modifier.padding(3.dp))
                        Card(shape = RoundedCornerShape(10.dp), elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)) {
                            Text(modifier = Modifier.padding(7.dp), text = withdraw.amount.toString(), fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.padding(3.dp))
                        Text(text = withdraw.withdrawDate, fontSize = 12.sp)
                    }
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Divider(modifier = Modifier.fillMaxWidth(), thickness = 2.dp, color = Color.Black)
            }
        }
        else
        {
            Row(Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = { onEditClick() }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription ="Edit" )
                }
                IconButton(onClick = { onDeleteClick() }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription ="Delete" )
                }
            }
        }
    }


}



@Composable
fun TopAppBar(listState: String,
              onBackClick: ()-> Unit,
              profileList: ()-> Unit,
              paymentList: ()-> Unit,
              withdrawalList: ()-> Unit,
              title: String
){
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(modifier = Modifier.weight(1f),
            onClick = {
            when(listState){
                PERSONAL_INFOR_PROFILE_LIST ->{ profileList() }
                LOCATION_INFO_PROFILE_LIST ->{ profileList() }
                EMPLOYMENT_INFO_PROFILE_LIST ->{ profileList() }
                NEXT_OF_KIN_PROFILE_LIST ->{ profileList() }
                REFEREE_INFOR_PROFILE_LIST ->{ profileList() }
                PAYMENT_SUMMARY_LIST -> { paymentList() }
                ADD_PAYMENT_LIST->{ paymentList() }
                PAYMENT_LIST -> { onBackClick() }
                WITHDRAW_LIST -> { onBackClick() }
                ADD_WITHDRAW_LIST ->{ withdrawalList() }
                PROFILE_LIST -> { paymentList() }
                }
            }
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "ArrowBack")
        }
        Text(modifier = Modifier.weight(9f), text = title, textAlign = TextAlign.Center)
    }
}

@Composable
fun YearAndMonth(
    isEditing: Boolean,
    year: String,
    yearTextField: String,
    onPledgeChange: (String)-> Unit,
    onPledgeChangeDone: ()-> Unit,
    onPledgeClick: ()-> Unit
//    onAllCLick: ()-> Unit,
//    months: List<String>,
//    selectedMonth: String,
//    onDrowdownMenuClick: (String)-> Unit,
) {


    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 5.dp)) {
        Row {
            if (!isEditing){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextField(
                        modifier=Modifier.height(50.dp),
                        value = yearTextField,
                        onValueChange = { onPledgeChange(it)},
                        maxLines = 1,
                        label = {
                            Text(text = "Pledge", fontSize = 10.sp)
                        },
                        textStyle = TextStyle(fontSize = 12.sp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Icon(
                        modifier = Modifier.clickable { onPledgeChangeDone()},
                        imageVector = Icons.Default.Done, contentDescription = "Done")
                }
            }
            else {
                Button(
                    modifier = Modifier.weight(3f),
                    onClick = { onPledgeClick() }
                ) {
                    Text(text = year)
                }
            }
//            Spacer(modifier = Modifier.padding(5.dp))
//
//            var expanded by remember { mutableStateOf(false) }
//            Row(
//                modifier = Modifier
//                    .padding(5.dp)
//                    .clickable { expanded = true }
////                    .fillMaxSize()
//                    .weight(4f),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.Center
//            ) {
//                Text( text = selectedMonth)
//                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "ArrowDropDown")
//
//                DropdownMenu(
//                    expanded = expanded,
//                    onDismissRequest = { expanded = false }
//                ) {
//                    months.forEach { month ->
//                        DropdownMenuItem(text = {
//                            Text(text = month)
//                        }, onClick = {
//                            onDrowdownMenuClick(month)
//                            expanded = false
//                        })
//                    }
//                }
//            }
//
//            Spacer(modifier = Modifier.padding(5.dp))
//            Button(
//                modifier = Modifier.weight(3f),
//                onClick = {onAllCLick() }) {
//                Text(text = "All")
//            }

        }

    }

}