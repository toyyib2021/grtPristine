package com.stevdza.san.testapp.screen.memberDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.stevdza.san.testapp.dataMain.GrtPayment
import com.stevdza.san.testapp.dataMain.GrtWithdrawal
import com.stevdza.san.testapp.ui.Constants.ADD_PAYMENT_LIST
import com.stevdza.san.testapp.ui.Constants.ADD_WITHDRAW_LIST
import com.stevdza.san.testapp.ui.Constants.EMPLOYMENT_INFO_PROFILE_LIST
import com.stevdza.san.testapp.ui.Constants.LOCATION_INFO_PROFILE_LIST
import com.stevdza.san.testapp.ui.Constants.NEXT_OF_KIN_PROFILE_LIST
import com.stevdza.san.testapp.ui.Constants.PAYMENT_LIST
import com.stevdza.san.testapp.ui.Constants.PAYMENT_SUMMARY_LIST
import com.stevdza.san.testapp.ui.Constants.PERSONAL_INFOR_PROFILE_LIST
import com.stevdza.san.testapp.ui.Constants.PROFILE_LIST
import com.stevdza.san.testapp.ui.Constants.REFEREE_INFOR_PROFILE_LIST
import com.stevdza.san.testapp.ui.Constants.WITHDRAW_LIST


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberDetailSection(
    listState: String,
    onPaymentClick:()-> Unit,
    onWithdrawalClick: ()-> Unit,
    paymentType: String,
    onTargetClick: ()-> Unit,
    onAllPaymentClick: ()-> Unit,
    onLoanCLick: ()-> Unit,
    onAdminClick: ()-> Unit,
    onPledgeClick: ()-> Unit,
    onSifClick: ()-> Unit,
    onTotalPaymentClick: ()-> Unit,
    withdrawType: String,
    onWithdrawLoanClick: ()-> Unit,
    onWithdrawClick: ()-> Unit,
    payment: List<GrtPayment>,
    onRefreshSlide: () -> Unit,
    swipeRefreshState: SwipeRefreshState,
    onEditClick: (GrtPayment)-> Unit,
    onDeleteClick: (GrtPayment)-> Unit,
    withdraw: List<GrtWithdrawal>,
    onRefreshSlideWithdraw: () -> Unit,
    swipeRefreshStateWithdraw: SwipeRefreshState,
    onEditClickWithdraw: (GrtWithdrawal)-> Unit,
    onDeleteClickWithdraw: (GrtWithdrawal)-> Unit,
    onProfileClick:()-> Unit,
    loanBalance: String,
    onAddPaymentClick:()->Unit,
    totalPledge: String,
    totalAdminFee: String,
    totalTarget: String,
    totalLoan: String,
    totalSif: String,
    totalAvalableBal: String,
    onPersonalClick:()-> Unit,
    onLocationClick:()-> Unit,
    onEmploymentClick:()-> Unit,
    onNextOfKinClick:()-> Unit,
    onRefereeClick:()-> Unit,

    selectedModeOfPayment: String,
    modeOfPaymentList: List<String>,
    modeOfPayment: String,
    onDrowdownModeOfPaymentMenuClick:(String)-> Unit,
    amountHide: Boolean,
    modeOfPaymentHide: Boolean,
    amount: String,
    amountPaidText: String,
    onAmountChange: (String)-> Unit,
    onEditAmountClick:()->Unit,
    onDoneAmountClick:()->Unit,
    onAddClick:()->Unit,
    onEditModeOfPayment:()->Unit,
    purposeOfPaymentHide: Boolean,
    selectedPurposeOfPayment: String,
    purposeOfPaymentList: List<String>,
    purposeOfPayment: String,
    onEditPurposeOfPayment: ()-> Unit,
    onDrowdownPurposeOfPaymentMenuClick:(String)->Unit,
    dateOfPaymenthHide: Boolean,
    onDatePick: ()-> Unit,
    onEditDateClick: ()-> Unit,
    selectedDate: String,
    datePickerState: DatePickerState,
    paymentReftHide: Boolean,
    paymentRef: String,
    paymentRefText: String,
    onEditPaymentRefClick:()->Unit,
    onPaymentRefChange:(String)-> Unit,
    onDonePaymentRefClick:()->Unit,


    amountWithdrawHide: Boolean,
    amountWithdraw: String,
    amountWithdrawText: String,
    onAmountWithdrawChange: (String)-> Unit,
    onEditWithdrawAmountClick:()->Unit,
    onDoneWithdrawAmountClick:()->Unit,

    onWithdrawAddClick:()->Unit,

    purposeOfWithdrawHide: Boolean,
    selectedPurposeOfWithdraw: String,
    purposeOfWithdrawList: List<String>,
    purposeOfWithdraw: String,
    onEditPurposeOfWithdraw: ()-> Unit,
    onDrowdownPurposeOfWithdrawMenuClick:(String)->Unit,

    dateOfWithdrawthHide: Boolean,
    onDateWithdrawPick: ()-> Unit,
    onEditWithdrawDateClick: ()-> Unit,
    selectedWithdrawDate: String,
    WithdrawDatePickerState: DatePickerState,

    chequeNoHide:Boolean,
    chequeNo: String,
    chequeNoText: String,
    onEditChequeNoClick:()->Unit,
    onChequeNoChange:(String)->Unit,
    onDoneChequeNoClick:()->Unit,

    voucherNoHide: Boolean,
    voucherNo: String,
    voucherNoText: String,
    onEditVoucherNoClick:()->Unit,
    onvoucherNoChange:(String)->Unit,
    onDonevoucherNoClick:()->Unit,

    accountCodeHide: Boolean,
    accountCode:String,
    accountCodeText:String,
    onEditAccountCodeClick:()->Unit,
    onAccountCodeChange:(String)->Unit,
    onDoneAccountCodeClick:()-> Unit,

    monthPledgePaymentHide: Boolean, selectedMonthPledgePayment: String, monthPledgePaymentList: List<String>,
    monthPledgePayment: String, onEditMonthPledge: ()-> Unit, onDrowdownMonthPledgePaymentMenuClick:(String)->Unit,

    objectId: String, onUpdatePaymentClick:()->Unit, withdrawalObjectId: String, onUpdateWithdrawClick:()->Unit,
    targetBal: String, pledgeBal: String,
    finOrSec: String

    ){
    Column(modifier = Modifier.padding(7.dp)) {
        when(listState) {
            WITHDRAW_LIST  -> {
                WithdrawalList(
                    modifier = Modifier.weight(8f),
                    onRefreshSlide ={onRefreshSlideWithdraw()},
                    swipeRefreshState = swipeRefreshStateWithdraw,
                    onEditClick = {onEditClickWithdraw(it)},
                    onDeleteClick = {onDeleteClickWithdraw(it)},
                    withdraw = withdraw,
                    secOrFin = finOrSec
                )
                Spacer(modifier = Modifier.padding(10.dp))
                WithdrawalTypes(
                    modifier = Modifier.weight(1f),
                    withdrawType = withdrawType,
                    onWithdrawLoanClick = onWithdrawLoanClick,
                    onWithdrawClick = onWithdrawClick,
                    listState = listState,
                    secOrFin = finOrSec

                )
            }
            PAYMENT_LIST -> {
                PaymentList(
                    modifier = Modifier.weight(8f),
                    payment = payment,
                    onRefreshSlide = {onRefreshSlide()},
                    swipeRefreshState=swipeRefreshState,
                    onEditClick = onEditClick,
                    onDeleteClick = onDeleteClick,
                    loanBalance = loanBalance,
                    paymentType = paymentType,
                    targetBal = targetBal,
                    pledgeBal = pledgeBal,
                    secOrFin = finOrSec
                )
                Spacer(modifier = Modifier.padding(5.dp))
                PaymentTypes(
                    modifier = Modifier.weight(1f),
                    paymentType = paymentType,
                    onTargetClick = onTargetClick,
                    onLoanCLick =onLoanCLick,
                    onAdminClick =onAdminClick,
                    onPledgeClick =onPledgeClick,
                    onSifClick = onSifClick,
                    onTotalPaymentClick = onTotalPaymentClick,
                    onProfileClick = onProfileClick,
                    listState = listState,
                    onAddPaymentClick = onAddPaymentClick,
                    onAllPaymentClick=onAllPaymentClick,
                    secOrFin = finOrSec
                )
            }

            PAYMENT_SUMMARY_LIST -> {
                PaymentSummary(
                    modifier = Modifier.weight(9f),
                    pledge = totalPledge,
                    adminFee = totalAdminFee,
                    target = totalTarget,
                    loan = totalLoan,
                    sif = totalSif,
                    avalableBal = totalAvalableBal
                )
            }
            ADD_PAYMENT_LIST -> {
                AddPayment(
                    modifier = Modifier.weight(9f),
                    selectedModeOfPayment = selectedModeOfPayment,
                    modeOfPaymentList = modeOfPaymentList,
                    modeOfPayment = modeOfPayment,
                    onDrowdownModeOfPaymentMenuClick = onDrowdownModeOfPaymentMenuClick,
                    amountHide = amountHide,
                    modeOfPaymentHide = modeOfPaymentHide,
                    amount = amount,
                    onAmountChange = onAmountChange,
                    onEditAmountClick = { onEditAmountClick() },
                    onDoneAmountClick = { onDoneAmountClick() },
                    onAddClick = { onAddClick() },
                    onEditModeOfPayment = {onEditModeOfPayment()},
                    purposeOfPaymentHide=purposeOfPaymentHide,
                    selectedPurposeOfPayment=selectedPurposeOfPayment,
                    purposeOfPaymentList=purposeOfPaymentList,
                    purposeOfPayment=purposeOfPayment,
                    onEditPurposeOfPayment=onEditPurposeOfPayment,
                    onDrowdownPurposeOfPaymentMenuClick = onDrowdownPurposeOfPaymentMenuClick,
                    dateOfPaymenthHide = dateOfPaymenthHide,
                    onDatePick = onDatePick,
                    onEditDateClick = onEditDateClick,
                    selectedDate = selectedDate,
                    datePickerState = datePickerState,
                    paymentReftHide=paymentReftHide,
                    paymentRef=paymentRef,
                    onEditPaymentRefClick=onEditPaymentRefClick,
                    onPaymentRefChange=onPaymentRefChange,
                    onDonePaymentRefClick=onDonePaymentRefClick,
                    amountPaidText = amountPaidText,
                    paymentRefText = paymentRefText,
                    objectId = objectId,
                    monthPledgePaymentHide=monthPledgePaymentHide,
                    selectedMonthPledgePayment=selectedMonthPledgePayment,
                    monthPledgePaymentList=monthPledgePaymentList,
                    monthPledgePayment=monthPledgePayment,
                    onEditMonthPledge=onEditMonthPledge,
                    onDrowdownMonthPledgePaymentMenuClick=onDrowdownMonthPledgePaymentMenuClick,
                    onUpdatePaymentClick=onUpdatePaymentClick
                )
            }
            ADD_WITHDRAW_LIST -> {
                AddWithdraw(
                    modifier= Modifier.weight(9f),
                    amountWithdrawHide = amountWithdrawHide,
                    amountWithdraw = amountWithdraw,
                    onAmountWithdrawChange = onAmountWithdrawChange,
                    onEditWithdrawAmountClick = onEditWithdrawAmountClick,
                    onDoneWithdrawAmountClick = onDoneWithdrawAmountClick,
                    onWithdrawAddClick = onWithdrawAddClick,
                    purposeOfWithdrawHide = purposeOfWithdrawHide,
                    selectedPurposeOfWithdraw = selectedPurposeOfWithdraw,
                    purposeOfWithdrawList = purposeOfWithdrawList,
                    purposeOfWithdraw = purposeOfWithdraw,
                    onEditPurposeOfWithdraw = onEditPurposeOfWithdraw,
                    onDrowdownPurposeOfWithdrawMenuClick = onDrowdownPurposeOfWithdrawMenuClick,
                    dateOfWithdrawthHide = dateOfWithdrawthHide,
                    onDateWithdrawPick = onDateWithdrawPick,
                    onEditWithdrawDateClick = onEditWithdrawDateClick,
                    selectedWithdrawDate = selectedWithdrawDate,
                    WithdrawDatePickerState = WithdrawDatePickerState,
                    chequeNoHide = chequeNoHide,
                    chequeNo = chequeNo,
                    onEditChequeNoClick = onEditChequeNoClick,
                    onChequeNoChange = onChequeNoChange,
                    onDoneChequeNoClick = onDoneChequeNoClick,
                    voucherNoHide = voucherNoHide,
                    voucherNo = voucherNo,
                    onEditVoucherNoClick = onEditVoucherNoClick,
                    onvoucherNoChange = onvoucherNoChange,
                    onDonevoucherNoClick = onDonevoucherNoClick,
                    accountCodeHide = accountCodeHide,
                    accountCode = accountCode,
                    onEditAccountCodeClick = onEditAccountCodeClick,
                    onAccountCodeChange = onAccountCodeChange,
                    onDoneAccountCodeClick = onDoneAccountCodeClick,
                    amountWithdrawText = amountWithdrawText,
                    chequeNoText = chequeNoText,
                    voucherNoText = voucherNoText,
                    accountCodeText = accountCodeText,
                    withdrawalObjectId = withdrawalObjectId,
                    onUpdateWithdrawClick=onUpdateWithdrawClick)

            }

        }
        Spacer(modifier = Modifier.padding(5.dp))


        PaymentOrWithdraw(modifier = Modifier.weight(1f), onPaymentClick = onPaymentClick,
            onWithdrawalClick = onWithdrawalClick, listState = listState)
    }

}
