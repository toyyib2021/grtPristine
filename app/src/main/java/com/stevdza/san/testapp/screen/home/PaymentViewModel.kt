package com.stevdza.san.testapp.screen.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stevdza.san.testapp.dataMain.GrtPayment
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

class PaymentViewModel : ViewModel() {

    // PAYMENT
    var objectId = mutableStateOf("")
    var memberId = mutableStateOf("")
    var modeOfPayment = mutableStateOf("")
    var purposeOfPayment = mutableStateOf("")
    var paymentRefrences = mutableStateOf("")
    var paymentDate = mutableStateOf("")
    var paymentMonth = mutableStateOf("")
    var month = mutableStateOf("")
    var paymentYear = mutableStateOf("")
    var amount = mutableStateOf("")
    var addPaymentState = mutableStateOf("")


//        val index = payments.indexOf()
//        payments.set(index, s)

    var payment = mutableStateOf<GrtPayment?>(null)

    var paymentData = mutableStateOf(emptyList<GrtPayment>())
    var paymentDataWithMemberId = mutableStateOf(emptyList<GrtPayment>())

    var paymentDataForAdminWithYearAndMonth = mutableStateOf(emptyList<GrtPayment>())
    var paymentDataForPledgeWithYearAndMonth = mutableStateOf(emptyList<GrtPayment>())
    var paymentDataForTargetWithYearAndMonth = mutableStateOf(emptyList<GrtPayment>())
    var paymentDataForSifWithYearAndMonth = mutableStateOf(emptyList<GrtPayment>())
    var paymentDataForLoanWithYearAndMonth = mutableStateOf(emptyList<GrtPayment>())


    init {
        viewModelScope.launch {
            AlMurabbiDB.getPaymentData().collect {
                paymentData.value = it
            }
        }
        getPaymentDataForAdminWithYearAndMonth()
        getPaymentDataForPledgeWithYearAndMonth()
        getPaymentDataForTargetWithYearAndMonth()
        getPaymentDataForSifWithYearAndMonth()
        getPaymentDataForLoanWithYearAndMonth()
    }


    fun getPaymentWithMemberID(){
        viewModelScope.launch {
            AlMurabbiDB.getPaymentWithMemberId(memberId.value).collect {
                paymentDataWithMemberId.value = it
            }
        }
    }

    fun getPaymentDataForAdminWithYearAndMonth(){
        viewModelScope.launch {
            AlMurabbiDB.getPaymentWithPurposeOfPayment(ADMIN).collect {
                paymentDataForAdminWithYearAndMonth.value = it
            }
        }
    }

    fun getPaymentDataForPledgeWithYearAndMonth(){
        viewModelScope.launch {
            AlMurabbiDB.getPaymentWithPurposeOfPayment(PLEDGE).collect {
                paymentDataForPledgeWithYearAndMonth.value = it
            }
        }
    }

    fun getPaymentDataForTargetWithYearAndMonth(){
        viewModelScope.launch {
            AlMurabbiDB.getPaymentWithPurposeOfPayment(TARGET).collect {
                paymentDataForTargetWithYearAndMonth.value = it
            }
        }
    }

    fun getPaymentDataForSifWithYearAndMonth(){
        viewModelScope.launch {
            AlMurabbiDB.getPaymentWithPurposeOfPayment(SIF).collect {
                paymentDataForSifWithYearAndMonth.value = it
            }
        }
    }

    fun getPaymentDataForLoanWithYearAndMonth(){
        viewModelScope.launch {
            AlMurabbiDB.getPaymentWithPurposeOfPayment(LOAN).collect {
                paymentDataForLoanWithYearAndMonth.value = it
            }
        }
    }


    fun insertGrtPayment() {
        viewModelScope.launch(Dispatchers.IO) {
            if (memberId.value.isNotEmpty()) {
                try {
                    AlMurabbiDB.insertPayment(grtPayment = GrtPayment().apply {
                        memberId=this@PaymentViewModel.memberId.value
                        modeOfPayment=this@PaymentViewModel.modeOfPayment.value
                        purposeOfPayment=this@PaymentViewModel.purposeOfPayment.value
                        paymentReference=this@PaymentViewModel.paymentRefrences.value
                        paymentDate=this@PaymentViewModel.paymentDate.value
                        paymentMonth=this@PaymentViewModel.paymentMonth.value
                        month=this@PaymentViewModel.month.value
                        paymentYear=this@PaymentViewModel.paymentYear.value
                        amount=this@PaymentViewModel.amount.value.toInt()
                    })
                }catch (e: Exception){
                    Log.e(TAG, "insertGrtProfile: ${e.message}", )

                }
            }
        }
    }

    fun updateGtrPayment() {
        viewModelScope.launch(Dispatchers.IO) {
                if (objectId.value.isNotEmpty()) {
                    AlMurabbiDB.updatePayment(grtPayment = GrtPayment().apply {
                        _id = ObjectId(hexString = this@PaymentViewModel.objectId.value)
                        memberId=this@PaymentViewModel.memberId.value
                        modeOfPayment=this@PaymentViewModel.modeOfPayment.value
                        purposeOfPayment=this@PaymentViewModel.purposeOfPayment.value
                        paymentReference=this@PaymentViewModel.paymentRefrences.value
                        paymentDate=this@PaymentViewModel.paymentDate.value
                        paymentMonth=this@PaymentViewModel.paymentMonth.value
                        month=this@PaymentViewModel.month.value
                        paymentYear=this@PaymentViewModel.paymentYear.value
                        amount=this@PaymentViewModel.amount.value.toInt()
                    })

                }

        }
    }

    fun deleteGtrPayment(id: String) {
        viewModelScope.launch {
            if (objectId.value.isNotEmpty()) {
                AlMurabbiDB.deletePayment(id = ObjectId(hexString = id))
            }
        }
    }


    fun getMemberWIthID() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                AlMurabbiDB.getPaymentWithID(_id = ObjectId(hexString = objectId.value)).let {
                    payment.value = it
                }
            }catch (e: RealmException){
                Log.e(TAG, "error Getting payment with Id: ${e.message}", )
            }
        }
    }

    fun getPaymentWithMemberIDAndTarget(){
        viewModelScope.launch {
            AlMurabbiDB.getPaymentWithMemberId(memberId.value).collect {
                it.filter {
                    it.purposeOfPayment == TARGET
                }.let {
                    paymentDataWithMemberId.value = it
                }
            }
        }
    }


    fun getPaymentWithMemberIDAndLoan(){
        viewModelScope.launch {
            AlMurabbiDB.getPaymentWithMemberId(memberId.value).collect {
                it.filter {
                    it.purposeOfPayment == LOAN

                }.let {
                    paymentDataWithMemberId.value = it
                }
            }

        }
    }

    fun getPaymentWithMemberIDAndAdmin(){
        viewModelScope.launch {
            AlMurabbiDB.getPaymentWithMemberId(memberId.value).collect {
                it.filter {
                    it.purposeOfPayment == ADMIN
                }.let {
                    paymentDataWithMemberId.value = it
                }
            }
        }
    }

    fun getPaymentWithMemberIDAndPledge(){
        viewModelScope.launch {
            AlMurabbiDB.getPaymentWithMemberId(memberId.value).collect {
                it.filter {
                    it.purposeOfPayment == PLEDGE
                }.let {
                    paymentDataWithMemberId.value = it
                }
            }
        }
    }

    fun getPaymentWithMemberIDAndSif(){
        viewModelScope.launch {
            AlMurabbiDB.getPaymentWithMemberId(memberId.value).collect {
                it.filter {
                    it.purposeOfPayment == SIF
                }.let {
                    paymentDataWithMemberId.value = it
                }
            }
        }
    }


}