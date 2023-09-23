package com.stevdza.san.testapp.screen.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stevdza.san.testapp.dataMain.GrtWithdrawal
import com.stevdza.san.testapp.di.AlMurabbiDB
import com.stevdza.san.testapp.ui.Constants.ADMIN
import com.stevdza.san.testapp.ui.Constants.LOAN
import com.stevdza.san.testapp.ui.Constants.PLEDGE
import com.stevdza.san.testapp.ui.Constants.SIF
import com.stevdza.san.testapp.ui.Constants.TARGET
import io.realm.kotlin.exceptions.RealmException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

class WithdrawalViewModel: ViewModel() {


    var objectId = mutableStateOf("")
    var memberId = mutableStateOf("")
    var modeOfWithdraw = mutableStateOf("")
    var withdrawDate = mutableStateOf("")
    var purposeOfWithdraw = mutableStateOf("")
    var withdrawMonth = mutableStateOf("")
    var withdrawYear = mutableStateOf("")
    var chequeNo = mutableStateOf("")
    var accountCode = mutableStateOf("")
    var voucherNo = mutableStateOf("")
    var description = mutableStateOf("")
    var amount = mutableStateOf("")

    var addWithdrawalState = mutableStateOf(false)


//        val index = payments.indexOf()
//        payments.set(index, s)


    var withdrawalData = mutableStateOf(emptyList<GrtWithdrawal>())
    var withdrawDataWithMemberId = mutableStateOf(emptyList<GrtWithdrawal>())
    var withdrawDataWithMemberIdForCalculations = mutableStateOf(emptyList<GrtWithdrawal>())
    var withdraw = mutableStateOf<GrtWithdrawal?>(null)

    var withdrawDataForAdmin = mutableStateOf(emptyList<GrtWithdrawal>())
    var withdrawDataForPledge = mutableStateOf(emptyList<GrtWithdrawal>())
    var withdrawDataForTarget = mutableStateOf(emptyList<GrtWithdrawal>())
    var withdrawDataForLoan = mutableStateOf(emptyList<GrtWithdrawal>())
    var withdrawDataForSif = mutableStateOf(emptyList<GrtWithdrawal>())


    init {
        viewModelScope.launch {
            AlMurabbiDB.getWithdrawData().collect {
                withdrawalData.value = it
            }
        }
        getWithdrawDataAdmin()
        getWithdrawDataPledge()
        getWithdrawDataTarget()
        getWithdrawDataLoan()
        getWithdrawDataSif()
    }


    fun getWithdrawalWithMemberID(){
        viewModelScope.launch {
            AlMurabbiDB.getWithdrawalWithMemberId(memberId.value).collect {
                withdrawDataWithMemberId.value = it
                withdrawDataWithMemberIdForCalculations.value = it
            }
        }
    }

    fun getWithdrawDataAdmin(){
        viewModelScope.launch {
            AlMurabbiDB.getPaymentWithPurposeOfWithdraw(ADMIN).collect {
                withdrawDataForAdmin.value = it
            }
        }
    }

    fun getWithdrawDataPledge(){
        viewModelScope.launch {
            AlMurabbiDB.getPaymentWithPurposeOfWithdraw(PLEDGE).collect {
                withdrawDataForPledge.value = it
            }
        }
    }

    fun getWithdrawDataTarget(){
        viewModelScope.launch {
            AlMurabbiDB.getPaymentWithPurposeOfWithdraw(TARGET).collect {
                withdrawDataForTarget.value = it
            }
        }
    }

    fun getWithdrawDataLoan(){
        viewModelScope.launch {
            AlMurabbiDB.getPaymentWithPurposeOfWithdraw(LOAN).collect {
                withdrawDataForLoan.value = it
            }
        }
    }

    fun getWithdrawDataSif(){
        viewModelScope.launch {
            AlMurabbiDB.getPaymentWithPurposeOfWithdraw(SIF).collect {
                withdrawDataForSif.value = it
            }
        }
    }



    fun getWithdrawalWithMemberIDAndLoan(){
        viewModelScope.launch {
            AlMurabbiDB.getWithdrawalWithMemberId(memberId.value).collect {
                it.filter {
                    it.purposeOfWithdraw == "Loan"
                }.let {
                    withdrawDataWithMemberId.value = it
                }
            }
        }
    }

    fun insertGrtWithdrawal() {
        viewModelScope.launch(Dispatchers.IO) {
            if (memberId.value.isNotEmpty()) {
                try {
                    AlMurabbiDB.insertWithdraw(grtWithdraw = GrtWithdrawal().apply {
                        memberId=this@WithdrawalViewModel.memberId.value
                        modeOfWithdraw=this@WithdrawalViewModel.modeOfWithdraw.value
                        withdrawDate=this@WithdrawalViewModel.withdrawDate.value
                        purposeOfWithdraw=this@WithdrawalViewModel.purposeOfWithdraw.value
                        withdrawalMonth=this@WithdrawalViewModel.withdrawMonth.value
                        withdrawalYear=this@WithdrawalViewModel.withdrawYear.value
                        chequeNo=this@WithdrawalViewModel.chequeNo.value
                        accountCode=this@WithdrawalViewModel.accountCode.value
                        voucherNo=this@WithdrawalViewModel.voucherNo.value
                        description=this@WithdrawalViewModel.description.value
                        amount=this@WithdrawalViewModel.amount.value.toInt()
                    })
                }catch (e: Exception){
                    Log.e(TAG, "insertGrtProfile: ${e.message}", )

                }
            }
        }
    }

    fun updateGtrWithdrawal() {
        viewModelScope.launch(Dispatchers.IO) {
            if (objectId.value.isNotEmpty()) {
                try {
                    AlMurabbiDB.updateWithdraw(grtWithdraw = GrtWithdrawal().apply {
                        _id = ObjectId(hexString = this@WithdrawalViewModel.objectId.value)
                        memberId=this@WithdrawalViewModel.memberId.value
                        modeOfWithdraw=this@WithdrawalViewModel.modeOfWithdraw.value
                        withdrawDate=this@WithdrawalViewModel.withdrawDate.value
                        purposeOfWithdraw=this@WithdrawalViewModel.purposeOfWithdraw.value
                        withdrawalMonth=this@WithdrawalViewModel.withdrawMonth.value
                        withdrawalYear=this@WithdrawalViewModel.withdrawYear.value
                        chequeNo=this@WithdrawalViewModel.chequeNo.value
                        accountCode=this@WithdrawalViewModel.accountCode.value
                        voucherNo=this@WithdrawalViewModel.voucherNo.value
                        description=this@WithdrawalViewModel.description.value
                        amount=this@WithdrawalViewModel.amount.value.toInt()
                    })
                }catch (e: Exception){
                    Log.e(TAG, "updateGtrProfile: ${e.message}", )

                }
            }

        }
    }

    fun deleteGtrWithdrawal(id: String) {
        viewModelScope.launch {
            if (objectId.value.isNotEmpty()) {
                AlMurabbiDB.deleteWithdraw(id = ObjectId(hexString = id))
            }
        }
    }


    fun getWithdrawalWIthID() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                AlMurabbiDB.getWithdrawWithID(_id = ObjectId(hexString = objectId.value)).let {
                    withdraw.value = it
                }
            }catch (e: RealmException){
                Log.e(TAG, "error Getting payment with Id: ${e.message}", )
            }
        }
    }

}