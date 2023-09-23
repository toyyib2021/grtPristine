package com.stevdza.san.testapp.screen.account

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.stevdza.san.testapp.screen.HomeViewModel
import com.stevdza.san.testapp.screen.home.PaymentViewModel
import com.stevdza.san.testapp.screen.home.WithdrawalViewModel
import com.stevdza.san.testapp.screen.account.GRTAccounts
import com.stevdza.san.testapp.screen.account.PaymentAndWithdrawalBtn
import com.stevdza.san.testapp.screen.account.TopBarAccount
import kotlinx.coroutines.delay


@Composable
fun StatementForMembers(memberId: String, onBackClick: ()-> Unit){

    val homeViewModel: HomeViewModel = viewModel()
    val payment: PaymentViewModel = viewModel()
    val withdraw: WithdrawalViewModel = viewModel()

    Log.e(TAG, "member Id: $memberId", )
    LaunchedEffect(key1 = true ){
        payment.memberId.value=memberId
        payment.getPaymentWithMemberID()
        withdraw.memberId.value=memberId
        withdraw.getWithdrawalWithMemberID()
    }

    var year by remember { mutableStateOf("2023") }
    var month by remember { mutableStateOf("Select") }
    var paymentAndWithdrawalState by remember { mutableStateOf("Payment") }

    // Statement
    val withdrawDataForMemberId = withdraw.withdrawDataWithMemberId.value.filter {
        it.withdrawalYear == year }
    val withdrawDataMemberIdYearAmount = withdrawDataForMemberId.sumOf { it.amount }

    val withdrawDataMemberMonthAndYear = withdraw.withdrawDataWithMemberId.value.filter {
        it.withdrawalYear == year && it.withdrawalMonth == month}
    val withdrawDataMemberIDYearAndMonthAmount = withdrawDataMemberMonthAndYear.sumOf { it.amount }


    val paymentDataForAYear = payment.paymentData.value.filter {
        it.paymentYear == year }
    val paymentDataForAYearAmount = paymentDataForAYear.sumOf { it.amount }

    val paymentDataForAdMonth = payment.paymentData.value.filter {
        it.paymentYear == year && it.paymentMonth == month}
    val paymentDataForAMonthAmount = paymentDataForAdMonth.sumOf { it.amount }


    // Swipe to Refresh Withdraw
    var isRefreshingPayment by remember { mutableStateOf(false) }
    val swipeRefreshStateWithdraw = rememberSwipeRefreshState(isRefreshingPayment)

    var yearState by remember { mutableStateOf(false) }
    var yearTextfield by remember { mutableStateOf("") }
    var yearText by remember { mutableStateOf("2022") }
    yearText=year

    var monthHide by remember { mutableStateOf(false) }
    var selectedMonth by remember { mutableStateOf("") }
    val monthList = listOf<String>(
        "Select", "January", "February", "March", "April", "May", "June", "July",
        "August", "September", "October", "November", "December"
    )

    LaunchedEffect(isRefreshingPayment) {
        if (isRefreshingPayment) {
            delay(1000L)
            isRefreshingPayment = false
        }
    }

    Column {
        TopBarAccount(
            modifier= Modifier.weight(1f),
            attcountType = "Statement",
            accountBal = "",
            onBackArrowClick = {onBackClick()}
        )
        GRTAccounts(
            modifier = Modifier
                .fillMaxWidth().padding(horizontal = 10.dp)
                .weight(8f),
            payment = when (month) {
                "Select" -> {
                    paymentDataForAYear
                }
                else -> {
                    paymentDataForAdMonth
                }
            },
            withdraw = when (month) {
                "Select" -> {
                    withdrawDataForMemberId
                }
                else -> {
                    withdrawDataMemberMonthAndYear
                }
            },

            totalWithdrawAdmin =  when (month) {
                "Select" -> {
                    withdrawDataMemberIdYearAmount.toString()
                }
                else -> {
                    withdrawDataMemberIDYearAndMonthAmount.toString()
                }
            },
            totalPaymentAdmin = when (month) {
                "Select" -> {
                    paymentDataForAYearAmount.toString()
                }
                else -> {
                    paymentDataForAMonthAmount.toString()
                }
            },

            onRefreshSlide = { isRefreshingPayment=true },
            swipeRefreshState = swipeRefreshStateWithdraw,
            yearState = yearState,
            yearTextfield = yearTextfield,
            yearText=year,
            onYearTextFieldChange ={
                year=it
                yearTextfield=it.filter { it.isDigit() }},
            onDoneClick ={ yearState=false},
            onYearTextClick ={yearState=true},
            selectedMonth =month,
            monthList =monthList,
            onDropDownMenuClick = {
                month=it
                selectedMonth=it},
            paymentAndWithdrawalState =paymentAndWithdrawalState,
            grtProfile = homeViewModel.data.value
        )

        PaymentAndWithdrawalBtn(
            modifier = Modifier.weight(1f),
            onWithdrawClick = { paymentAndWithdrawalState = "Withdraw" },
            onPaymentClick = {paymentAndWithdrawalState = "Payment"},
            paymentAndWithdrawalState = paymentAndWithdrawalState
        )
    }

}
