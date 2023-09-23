package com.stevdza.san.testapp.screen.account

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.stevdza.san.testapp.dataMain.dataStore.MonthKey
import com.stevdza.san.testapp.dataMain.dataStore.YearKey
import com.stevdza.san.testapp.screen.HomeViewModel
import com.stevdza.san.testapp.screen.home.PaymentViewModel
import com.stevdza.san.testapp.screen.home.WithdrawalViewModel
import com.stevdza.san.testapp.ui.Constants.ADMIN
import com.stevdza.san.testapp.ui.Constants.LOAN
import com.stevdza.san.testapp.ui.Constants.PLEDGE
import com.stevdza.san.testapp.ui.Constants.SIF
import com.stevdza.san.testapp.ui.Constants.STATEMENT
import com.stevdza.san.testapp.ui.Constants.TARGET
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Account(onBackArrowClick:()->Unit){
    val homeViewModel: HomeViewModel = viewModel()

    val payment: PaymentViewModel = viewModel()

    val withdraw: WithdrawalViewModel = viewModel()


    var accountType by  remember { mutableStateOf("") }
    var paymentAndWithdrawalState by  remember { mutableStateOf("Payment") }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val yearKey = YearKey(context)
    val getYearKey = yearKey.getKey.collectAsState(initial = "")
    val monthKey = MonthKey(context)
    val getMonthKey = monthKey.getKey.collectAsState(initial = "")

    // Admin
    val withdrawDataForAdminWithYear = withdraw.withdrawDataForAdmin.value.filter {
        it.withdrawalYear == getYearKey.value }
    val withdrawDataForAdminWithYearAmount = withdrawDataForAdminWithYear.sumOf { it.amount }

    val withdrawDataForAdminWithYearAndMonth = withdraw.withdrawDataForAdmin.value.filter {
        it.withdrawalYear == getYearKey.value && it.withdrawalMonth == getMonthKey.value}
    val withdrawDataForAdminWithYearAndMonthAmount = withdrawDataForAdminWithYearAndMonth.sumOf { it.amount }

    val withdrawDataForAllAdminAmount = withdraw.withdrawDataForAdmin.value.sumOf { it.amount }


    val paymentDataForAdminWithYear = payment.paymentDataForAdminWithYearAndMonth.value.filter {
        it.paymentYear == getYearKey.value }
    val paymentDataForAdminWithYearAmount = paymentDataForAdminWithYear.sumOf { it.amount }

    val paymentDataForAdminWithYearAndMonth = payment.paymentDataForAdminWithYearAndMonth.value.filter {
        it.paymentYear == getYearKey.value && it.paymentMonth == getMonthKey.value}
    val paymentDataForAdminWithYearAndMonthAmount = paymentDataForAdminWithYearAndMonth.sumOf { it.amount }

    val paymentDataForAllAdminAmount = payment.paymentDataForAdminWithYearAndMonth.value.sumOf { it.amount }

    val adminBal = paymentDataForAllAdminAmount - withdrawDataForAllAdminAmount

    // Pledge
    val withdrawDataForPledgeWithYear = withdraw.withdrawDataForPledge.value.filter {
        it.withdrawalYear == getYearKey.value }
    val withdrawDataForPledgeWithYearAmount = withdrawDataForPledgeWithYear.sumOf { it.amount }

    val withdrawDataForPledgeWithYearAndMonth = withdraw.withdrawDataForPledge.value.filter {
        it.withdrawalYear == getYearKey.value && it.withdrawalMonth == getMonthKey.value}
    val withdrawDataForPledgeWithYearAndMonthAmount = withdrawDataForPledgeWithYearAndMonth.sumOf { it.amount }

    val withdrawDataForAllPledgeAmount = withdraw.withdrawDataForPledge.value.sumOf { it.amount }


    val paymentDataForPledgeWithYear = payment.paymentDataForPledgeWithYearAndMonth.value.filter {
        it.paymentYear == getYearKey.value }
    val paymentDataForPledgeWithYearAmount = paymentDataForPledgeWithYear.sumOf { it.amount }

    val paymentDataForPledgeWithYearAndMonth = payment.paymentDataForPledgeWithYearAndMonth.value.filter {
        it.paymentYear == getYearKey.value && it.paymentMonth == getMonthKey.value}
    val paymentDataForPledgeWithYearAndMonthAmount = paymentDataForPledgeWithYearAndMonth.sumOf { it.amount }

    val paymentDataForAllPledgeAmount = payment.paymentDataForPledgeWithYearAndMonth.value.sumOf { it.amount }

    val pledgeBal = paymentDataForAllPledgeAmount - withdrawDataForAllPledgeAmount

    // Target
    val withdrawDataForTargetWithYear = withdraw.withdrawDataForTarget.value.filter {
        it.withdrawalYear == getYearKey.value }
    val withdrawDataForTargetWithYearAmount = withdrawDataForTargetWithYear.sumOf { it.amount }

    val withdrawDataForTargetWithYearAndMonth = withdraw.withdrawDataForTarget.value.filter {
        it.withdrawalYear == getYearKey.value && it.withdrawalMonth == getMonthKey.value}
    val withdrawDataForTargetWithYearAndMonthAmount = withdrawDataForTargetWithYearAndMonth.sumOf { it.amount }

    val withdrawDataForAllTargetAmount = withdraw.withdrawDataForTarget.value.sumOf { it.amount }


    val paymentDataForTargetWithYear = payment.paymentDataForTargetWithYearAndMonth.value.filter {
        it.paymentYear == getYearKey.value }
    val paymentDataForTargetWithYearAmount = paymentDataForTargetWithYear.sumOf { it.amount }

    val paymentDataForTargetWithYearAndMonth = payment.paymentDataForTargetWithYearAndMonth.value.filter {
        it.paymentYear == getYearKey.value && it.paymentMonth == getMonthKey.value}
    val paymentDataForTargetWithYearAndMonthAmount = paymentDataForTargetWithYearAndMonth.sumOf { it.amount }

    val paymentDataForAllTargetAmount = payment.paymentDataForTargetWithYearAndMonth.value.sumOf { it.amount }

    val targetBal = paymentDataForAllTargetAmount - withdrawDataForAllTargetAmount

    // Loan
    val withdrawDataForLoanWithYear = withdraw.withdrawDataForLoan.value.filter {
        it.withdrawalYear == getYearKey.value }
    val withdrawDataForLoanWithYearAmount = withdrawDataForLoanWithYear.sumOf { it.amount }

    val withdrawDataForLoanWithYearAndMonth = withdraw.withdrawDataForLoan.value.filter {
        it.withdrawalYear == getYearKey.value && it.withdrawalMonth == getMonthKey.value}
    val withdrawDataForLoanWithYearAndMonthAmount = withdrawDataForLoanWithYearAndMonth.sumOf { it.amount }

    val withdrawDataForAllLoanAmount = withdraw.withdrawDataForLoan.value.sumOf { it.amount }


    val paymentDataForLoanWithYear = payment.paymentDataForLoanWithYearAndMonth.value.filter {
        it.paymentYear == getYearKey.value }
    val paymentDataForLoanWithYearAmount = paymentDataForLoanWithYear.sumOf { it.amount }

    val paymentDataForLoanWithYearAndMonth = payment.paymentDataForLoanWithYearAndMonth.value.filter {
        it.paymentYear == getYearKey.value && it.paymentMonth == getMonthKey.value}
    val paymentDataForLoanWithYearAndMonthAmount = paymentDataForLoanWithYearAndMonth.sumOf { it.amount }

    val paymentDataForAllLoanAmount = payment.paymentDataForLoanWithYearAndMonth.value.sumOf { it.amount }

    val loanBal = withdrawDataForAllLoanAmount - paymentDataForAllLoanAmount

    // SIF
    val withdrawDataForSifWithYear = withdraw.withdrawDataForSif.value.filter {
        it.withdrawalYear == getYearKey.value }
    val withdrawDataForSifWithYearAmount = withdrawDataForSifWithYear.sumOf { it.amount }

    val withdrawDataForSifWithYearAndMonth = withdraw.withdrawDataForSif.value.filter {
        it.withdrawalYear == getYearKey.value && it.withdrawalMonth == getMonthKey.value}
    val withdrawDataForSifWithYearAndMonthAmount = withdrawDataForSifWithYearAndMonth.sumOf { it.amount }

    val withdrawDataForAlSifAmount = withdraw.withdrawDataForSif.value.sumOf { it.amount }


    val paymentDataForSifWithYear = payment.paymentDataForSifWithYearAndMonth.value.filter {
        it.paymentYear == getYearKey.value }
    val paymentDataForSifWithYearAmount = paymentDataForSifWithYear.sumOf { it.amount }

    val paymentDataForSifWithYearAndMonth = payment.paymentDataForSifWithYearAndMonth.value.filter {
        it.paymentYear == getYearKey.value && it.paymentMonth == getMonthKey.value}
    val paymentDataForSifWithYearAndMonthAmount = paymentDataForSifWithYearAndMonth.sumOf { it.amount }

    val paymentDataForAllSifAmount = payment.paymentDataForSifWithYearAndMonth.value.sumOf { it.amount }

    val sifBal = paymentDataForAllSifAmount - withdrawDataForAlSifAmount

    // Statement
    val withdrawDataForAYear = withdraw.withdrawalData.value.filter {
        it.withdrawalYear == getYearKey.value }
    val withdrawDataForAYearAmount = withdrawDataForAYear.sumOf { it.amount }

    val withdrawDataForAMonth = withdraw.withdrawalData.value.filter {
        it.withdrawalYear == getYearKey.value && it.withdrawalMonth == getMonthKey.value}
    val withdrawDataForAMonthAmount = withdrawDataForAMonth.sumOf { it.amount }

    val withdrawDataAllAmount = withdraw.withdrawalData.value.sumOf { it.amount }


    val paymentDataForAYear = payment.paymentData.value.filter {
        it.paymentYear == getYearKey.value }
    val paymentDataForAYearAmount = paymentDataForAYear.sumOf { it.amount }

    val paymentDataForAdMonth = payment.paymentData.value.filter {
        it.paymentYear == getYearKey.value && it.paymentMonth == getMonthKey.value}
    val paymentDataForAMonthAmount = paymentDataForAdMonth.sumOf { it.amount }

    val paymentDataAllAmount = payment.paymentData.value.sumOf { it.amount }

    val statementBal = paymentDataAllAmount - withdrawDataAllAmount


    // Swipe to Refresh Withdraw
    var isRefreshingPayment by remember { mutableStateOf(false) }
    val swipeRefreshStateWithdraw = rememberSwipeRefreshState(isRefreshingPayment)

    var yearState by remember { mutableStateOf(false) }
    var yearTextfield by remember { mutableStateOf("") }
    var yearText by remember { mutableStateOf("2022") }
    yearText=getYearKey.value

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

    BackHandler {
        when (accountType) {
            "" -> {
                onBackArrowClick()
            }

            else -> {
                accountType = ""
            }
        }
    }

    if (accountType == ""){
        AccountType(
            onAdminAccountClick = { accountType=ADMIN },
            onPledgeAccountClick = { accountType= PLEDGE },
            onTargetAccountClick = { accountType= TARGET },
            onSIFAccountClick = { accountType= SIF },
            onLoanAccountClick = { accountType=LOAN },
            onStatementAccountClick = {accountType= STATEMENT}
        )
    }else{
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
        ) {


            TopBarAccount(
                modifier= Modifier.weight(1f),
                attcountType = when(accountType){
                    ADMIN->{
                        ADMIN
                    }
                    SIF->{
                        SIF
                    }
                    PLEDGE->{
                        PLEDGE
                    }
                    TARGET->{
                        TARGET
                    }
                    LOAN->{
                        LOAN
                    }
                    else -> {
                        STATEMENT
                    }
                },
                accountBal = when(accountType){
                    ADMIN->{
                        adminBal.toString()
                    }
                    SIF->{
                        sifBal.toString()
                    }
                    PLEDGE->{
                        pledgeBal.toString()
                    }
                    TARGET->{
                        targetBal.toString()
                    }
                    LOAN->{
                        loanBal.toString()
                    }
                    else -> {
                        statementBal.toString()
                    }
                },
                onBackArrowClick = {
                    when (accountType) {
                        "" -> {
                            onBackArrowClick()
                        }

                        else -> {
                            accountType = ""
                        }
                    }
                }

            )

            GRTAccounts(
                modifier=Modifier.weight(8f),

                payment = when(accountType) {
                    ADMIN -> {
                        when (getMonthKey.value) {
                            "Select" -> {
                                paymentDataForAdminWithYear
                            }
                            else -> {
                                paymentDataForAdminWithYearAndMonth
                            }
                        }
                    }
                    PLEDGE->{
                        when (getMonthKey.value) {
                            "Select" -> {
                                paymentDataForPledgeWithYear
                            }
                            else -> {
                                paymentDataForPledgeWithYearAndMonth
                            }
                        }
                    }
                    TARGET->{
                        when (getMonthKey.value) {
                            "Select" -> {
                                paymentDataForTargetWithYear
                            }
                            else -> {
                                paymentDataForTargetWithYearAndMonth
                            }
                        }
                    }
                    SIF->{
                        when (getMonthKey.value) {
                            "Select" -> {
                                paymentDataForSifWithYear
                            }
                            else -> {
                                paymentDataForSifWithYearAndMonth
                            }
                        }
                    }
                    LOAN->{
                        when (getMonthKey.value) {
                            "Select" -> {
                                paymentDataForLoanWithYear
                            }
                            else -> {
                                paymentDataForLoanWithYearAndMonth
                            }
                        }
                    }
                    STATEMENT->{
                        when (getMonthKey.value) {
                            "Select" -> {
                                paymentDataForAYear
                            }
                            else -> {
                                paymentDataForAdMonth
                            }
                        }
                    }
                    else -> {
                        paymentDataForAdminWithYearAndMonth
                    }

                },
                withdraw =when(accountType){
                    ADMIN->{
                        when(getMonthKey.value){
                            "Select"->{
                                withdrawDataForAdminWithYear
                            }
                            else->{
                                withdrawDataForAdminWithYearAndMonth
                            }
                        }
                    }
                    PLEDGE->{
                        when(getMonthKey.value){
                            "Select"->{
                                withdrawDataForPledgeWithYear
                            }
                            else->{
                                withdrawDataForPledgeWithYearAndMonth
                            }
                        }
                    }
                    TARGET->{
                        when(getMonthKey.value){
                            "Select"->{
                                withdrawDataForTargetWithYear
                            }
                            else->{
                                withdrawDataForTargetWithYearAndMonth
                            }
                        }
                    }
                    LOAN->{
                        when(getMonthKey.value){
                            "Select"->{
                                withdrawDataForLoanWithYear
                            }
                            else->{
                                withdrawDataForLoanWithYearAndMonth
                            }
                        }
                    }
                    SIF->{
                        when(getMonthKey.value){
                            "Select"->{
                                withdrawDataForSifWithYear
                            }
                            else->{
                                withdrawDataForSifWithYearAndMonth
                            }
                        }
                    }
                    STATEMENT->{
                        when(getMonthKey.value){
                            "Select"->{
                                withdrawDataForAYear
                            }
                            else->{
                                withdrawDataForAMonth
                            }
                        }
                    }

                    else -> {
                        withdrawDataForAdminWithYearAndMonth
                    }
                },

                totalWithdrawAdmin =when(accountType){
                    ADMIN->{
                        when(getMonthKey.value){
                            "Select"->{
                                withdrawDataForAdminWithYearAmount.toString()
                            }
                            else->{
                                withdrawDataForAdminWithYearAndMonthAmount.toString()
                            }
                        }
                    }
                    PLEDGE->{
                        when(getMonthKey.value){
                            "Select"->{
                                withdrawDataForPledgeWithYearAmount.toString()
                            }
                            else->{
                                withdrawDataForPledgeWithYearAndMonthAmount.toString()
                            }
                        }
                    }
                    TARGET->{
                        when(getMonthKey.value){
                            "Select"->{
                                withdrawDataForTargetWithYearAmount.toString()
                            }
                            else->{
                                withdrawDataForTargetWithYearAndMonthAmount.toString()
                            }
                        }
                    }
                    LOAN->{
                        when(getMonthKey.value){
                            "Select"->{
                                withdrawDataForLoanWithYearAmount.toString()
                            }
                            else->{
                                withdrawDataForLoanWithYearAndMonthAmount.toString()
                            }
                        }
                    }
                    SIF->{
                        when(getMonthKey.value){
                            "Select"->{
                                withdrawDataForSifWithYearAmount.toString()
                            }
                            else->{
                                withdrawDataForSifWithYearAndMonthAmount.toString()
                            }
                        }
                    }
                    STATEMENT->{
                        when(getMonthKey.value){
                            "Select"->{
                                withdrawDataForAYearAmount.toString()
                            }
                            else->{
                                withdrawDataForAMonthAmount.toString()
                            }
                        }
                    }

                    else -> {
                        withdrawDataForPledgeWithYearAndMonthAmount.toString()
                    }
                },
                totalPaymentAdmin =when(accountType){
                    ADMIN->{
                        when(getMonthKey.value){
                            "Select"->{
                                paymentDataForAdminWithYearAmount.toString()
                            }
                            else->{
                                paymentDataForAdminWithYearAndMonthAmount.toString()
                            }
                        }
                    }
                    PLEDGE->{
                        when(getMonthKey.value){
                            "Select"->{
                                paymentDataForPledgeWithYearAmount.toString()
                            }
                            else->{
                                paymentDataForPledgeWithYearAndMonthAmount.toString()
                            }
                        }
                    }
                    TARGET->{
                        when(getMonthKey.value){
                            "Select"->{
                                paymentDataForTargetWithYearAmount.toString()
                            }
                            else->{
                                paymentDataForTargetWithYearAndMonthAmount.toString()
                            }
                        }
                    }
                    LOAN->{
                        when(getMonthKey.value){
                            "Select"->{
                                paymentDataForLoanWithYearAmount.toString()
                            }
                            else->{
                                paymentDataForLoanWithYearAndMonthAmount.toString()
                            }
                        }
                    }
                    SIF->{
                        when(getMonthKey.value){
                            "Select"->{
                                paymentDataForSifWithYearAmount.toString()
                            }
                            else->{
                                paymentDataForSifWithYearAndMonthAmount.toString()
                            }
                        }
                    }
                    STATEMENT->{
                        when(getMonthKey.value){
                            "Select"->{
                                paymentDataForAYearAmount.toString()
                            }
                            else->{
                                paymentDataForAMonthAmount.toString()
                            }
                        }
                    }

                    else -> {
                        paymentDataForAdminWithYearAndMonthAmount.toString()
                    }
                },


                onRefreshSlide = { isRefreshingPayment=true },
                swipeRefreshState = swipeRefreshStateWithdraw,
                yearState = yearState,
                yearTextfield = yearTextfield,
                yearText=getYearKey.value,
                onYearTextFieldChange ={yearTextfield=it.filter { it.isDigit() }},
                onDoneClick ={
                    scope.launch {
                        yearKey.saveKey(yearTextfield)
                    }
                    yearState=false},
                onYearTextClick ={yearState=true},
                selectedMonth =getMonthKey.value,
                monthList =monthList,
                onDropDownMenuClick = {
                    scope.launch {
                        monthKey.saveKey(it)
                    }
                    selectedMonth=it},
                paymentAndWithdrawalState =paymentAndWithdrawalState,
                grtProfile = homeViewModel.data.value
            )


            PaymentAndWithdrawalBtn(
                onWithdrawClick = { paymentAndWithdrawalState = "Withdraw" },
                onPaymentClick = {paymentAndWithdrawalState = "Payment"},
                paymentAndWithdrawalState = paymentAndWithdrawalState
            )


        }
    }

}

@Composable
fun AccountType(
    onAdminAccountClick: ()->Unit,
    onPledgeAccountClick: ()->Unit,
    onTargetAccountClick: ()->Unit,
    onSIFAccountClick: ()->Unit,
    onLoanAccountClick: ()->Unit,
    onStatementAccountClick: ()->Unit,
){
    Column(modifier= Modifier
        .fillMaxWidth()
        .padding(10.dp),
        ) {
        Spacer(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth())

        Text(modifier= Modifier.fillMaxWidth(), text = "Account Type's", fontSize = 14.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onAdminAccountClick()}) {
            Text( modifier = Modifier.fillMaxWidth(), text = "Admin Account", textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth())
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onPledgeAccountClick()}) {
            Text( modifier = Modifier.fillMaxWidth(), text = "Pledge Account", textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth())
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onTargetAccountClick()}) {
            Text( modifier = Modifier.fillMaxWidth(), text = "Target Account", textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth())
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onSIFAccountClick()}) {
            Text( modifier = Modifier.fillMaxWidth(), text = "SIF Account", textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth())
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onLoanAccountClick()}) {
            Text( modifier = Modifier.fillMaxWidth(), text = "Loan Account", textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth())
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onStatementAccountClick()}) {
            Text( modifier = Modifier.fillMaxWidth(), text = "Account Statement", textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth())

    }

}


@Composable
fun TopBarAccount(
    modifier: Modifier = Modifier,
    attcountType: String,
    accountBal: String,
    onBackArrowClick:()->Unit,
){
    Row(
        modifier.fillMaxWidth() ,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(modifier = Modifier
            .weight(1f)
            .clickable { onBackArrowClick() },imageVector = Icons.Default.ArrowBack, contentDescription ="ArrowBackIos" )
        Text(modifier = Modifier
            .weight(6f)
            .padding(horizontal = 30.dp),text = attcountType, fontSize = 14.sp)
        Text(modifier = Modifier.weight(3f), text = accountBal, fontSize = 14.sp, fontWeight = FontWeight.Bold)

    }
}

@Composable
fun PaymentAndWithdrawalBtn(
    modifier: Modifier = Modifier,
    onWithdrawClick:()->Unit,
    onPaymentClick:()->Unit,
    paymentAndWithdrawalState: String,
    ){
    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(modifier = Modifier
            .weight(5f)
            .padding(horizontal = 10.dp),
            onClick = {
                onWithdrawClick()
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = when(paymentAndWithdrawalState){
                    "Payment"->{
                        Color.Black
                    }
                    "Withdraw"->{
                        Color.White
                    }

                    else -> {
                        Color.White
                    }
                },
                backgroundColor = when(paymentAndWithdrawalState){
                    "Payment"->{
                        Color.White
                    }
                    "Withdraw"->{
                        Color.Black
                    }

                    else -> {
                        Color.White
                    }
                },
            )
        ) {
            Text(text = "Withdrawal",)
        }
        Button(modifier = Modifier
            .weight(5f)
            .padding(horizontal = 10.dp),
            onClick = {
                onPaymentClick()
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = when(paymentAndWithdrawalState){
                    "Payment"->{
                        Color.White
                    }
                    "Withdraw"->{
                        Color.Black
                    }

                    else -> {
                        Color.White
                    }
                },
                backgroundColor = when(paymentAndWithdrawalState){
                    "Payment"->{
                        Color.Black
                    }
                    "Withdraw"->{
                        Color.White
                    }

                    else -> {
                        Color.White
                    }
                },
            )
        ) {
            Text(text = "Payment",)
        }
    }
}