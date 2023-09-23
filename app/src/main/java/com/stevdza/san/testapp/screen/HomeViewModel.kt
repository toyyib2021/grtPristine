package com.stevdza.san.testapp.screen

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stevdza.san.testapp.dataMain.Admin
import com.stevdza.san.testapp.dataMain.Contact
import com.stevdza.san.testapp.dataMain.Employer
import com.stevdza.san.testapp.dataMain.GrtProfile
import com.stevdza.san.testapp.dataMain.NextOfKin
import com.stevdza.san.testapp.dataMain.Referee
import com.stevdza.san.testapp.di.AlMurabbiDB
//import com.stevdza.san.testApp.di.GrtPristineDB
import com.stevdza.san.testapp.ui.Constants.MEMBER_LIST
import com.stevdza.san.testapp.ui.Constants.PAYMENT_LIST
import com.stevdza.san.testapp.ui.Constants.PERSONAL_INFOR_PROFILE_LIST
import com.stevdza.san.testapp.ui.Constants.PROFILE
import com.stevdza.san.testapp.ui.Constants.PROFILE_LIST
import io.realm.kotlin.exceptions.RealmException
import io.realm.kotlin.mongodb.Credentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.mongodb.kbson.ObjectId


class HomeViewModel: ViewModel() {
    var objectId = mutableStateOf("")
    var name = mutableStateOf("")
    var formNo = mutableStateOf("")
    var dateOfBirth = mutableStateOf("")
    var sex = mutableStateOf("")
    var stateOfOrigin = mutableStateOf("")
    var lga = mutableStateOf("")
    var hometown = mutableStateOf("")
    var maritalStatus = mutableStateOf("")
    var pleadge = mutableStateOf("0")

    //    var adminFee = mutableStateOf("0")
    var loan = mutableStateOf("0")

    var pledgeBal = mutableStateOf("0")
    var loanBal = mutableStateOf("0")
    var sharesValue = mutableStateOf("0")
    var adminBal = mutableStateOf("0")
    var targeBal = mutableStateOf("0")
    var accessPin = mutableStateOf("0")

    var memberAndRegState = mutableStateOf(MEMBER_LIST)
    var registationState = mutableStateOf(PERSONAL_INFOR_PROFILE_LIST)
    var paymentType = mutableStateOf("")
    var withdrawType = mutableStateOf("")
    var listState = mutableStateOf(PAYMENT_LIST)
    var profileState = mutableStateOf(PROFILE_LIST)


    // Contact
    var email = mutableStateOf("")
    var address = mutableStateOf("")
    var phone = mutableStateOf("")

    // Employee
    var employeeName = mutableStateOf("")
    var employeeAddress = mutableStateOf("")
    var responsibilities = mutableStateOf("")
    var post = mutableStateOf("")


    // Next of Kin
    var nokName = mutableStateOf("")
    var nokPhone = mutableStateOf("")
    var nokRelationship = mutableStateOf("")
    var occupation = mutableStateOf("")
    var age = mutableStateOf("")


    // Referee
    var refName = mutableStateOf("")
    var refPhone = mutableStateOf("")
    var refMembershipNo = mutableStateOf("")
    var refName2 = mutableStateOf("")
    var refPhone2 = mutableStateOf("")
    var refMembershipNo2 = mutableStateOf("")


    var data = mutableStateOf(emptyList<GrtProfile>())
    var adminData = mutableStateOf(emptyList<Admin>())

    init {

        viewModelScope.launch {
            AlMurabbiDB.getGrtProfileList().collect {
                data.value = it
            }
        }

        viewModelScope.launch {
            AlMurabbiDB.getAdminData().collect {
                adminData.value = it
            }
        }
    }


    var member = mutableStateOf<GrtProfile?>(null)
        var admin = mutableStateOf<Admin?>(null)


    var adminObjectId = mutableStateOf("650c2f4d390fba517bb2b493")
    var secPin = mutableStateOf("")
    var finPin = mutableStateOf("")
    var adminFee = mutableStateOf("")
    var shareUnitValue = mutableStateOf("")

//    var secPinInsert = mutableStateOf("12345")
//    var finPinInsert = mutableStateOf("12345")
//    var adminFeeInsert = mutableStateOf("200")
//    var shareUnitValueInsert = mutableStateOf("20000")
//
//    fun insertAdmin() {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                AlMurabbiDB.insertAdmin(admin = Admin().apply {
//                    secPin = this@HomeViewModel.secPinInsert.value
//                    finPin = this@HomeViewModel.finPinInsert.value
//                    adminFee = this@HomeViewModel.adminFeeInsert.value
//                    sharesUnitValue = this@HomeViewModel.shareUnitValueInsert.value
//                })
//            } catch (e: RealmException) {
//                Log.e(TAG, "insertAdmin: ${e.message}",)
//            }
//        }
//    }



    fun updateAdmin(
        onError:()-> Unit,
        onSuccess:()-> Unit,
    ) {
        viewModelScope.launch{
            if (
                adminObjectId.value.isNotEmpty() && secPin.value.isNotEmpty()
                && finPin.value.isNotEmpty() && shareUnitValue.value.isNotEmpty()
            ) {
                try {
                    val result = withContext(Dispatchers.IO) {
                        AlMurabbiDB.updateAdmin(admin = Admin().apply {
                            _id = ObjectId(hexString = this@HomeViewModel.adminObjectId.value)
                            secPin = this@HomeViewModel.secPin.value
                            finPin = this@HomeViewModel.finPin.value
                            sharesUnitValue = this@HomeViewModel.shareUnitValue.value
                            adminFee = this@HomeViewModel.adminFee.value
                        })
                        return@withContext true
                    }
                    withContext(Dispatchers.Main) {
                        if (result){
                            onSuccess()
                        }
                    }

                } catch (e: RealmException) {
                    Log.e(TAG, "insertAdmin: ${e.message}",)
                    onError()
                }
            }
        }
    }



    fun getAdminWIthID() {
        viewModelScope.launch(Dispatchers.IO) {
            if (adminObjectId.value.isNotEmpty()) {
                try {
                    AlMurabbiDB.getWithAdminID(_id = ObjectId(hexString = adminObjectId.value))
                        .let {
                            admin.value = it
                        }
                } catch (e: RealmException) {
                    Log.e(TAG, "error Getting admin with Id: $e",)
                }
            }

        }
    }


    fun insertGrtProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            if (name.value.isNotEmpty()) {
                try {
                    AlMurabbiDB.insertMember(grtProfile = GrtProfile().apply {
                        name = this@HomeViewModel.name.value
                        refNo = this@HomeViewModel.formNo.value
                        dateOfBirth = this@HomeViewModel.dateOfBirth.value
                        sex = this@HomeViewModel.sex.value
                        stateOfOrgin = this@HomeViewModel.stateOfOrigin.value
                        lga = this@HomeViewModel.lga.value
                        homeTown = this@HomeViewModel.hometown.value
                        maritalStatus = this@HomeViewModel.maritalStatus.value

                        pleadge = this@HomeViewModel.pleadge.value.toInt()
                        pledgeBal = this@HomeViewModel.pledgeBal.value.toInt()
                        loanBal = this@HomeViewModel.loanBal.value.toInt()
                        sharesBal = this@HomeViewModel.sharesValue.value.toInt()
                        adminFeeBal = this@HomeViewModel.adminBal.value.toInt()
                        targetBal = this@HomeViewModel.targeBal.value.toInt()
                        loanBal = this@HomeViewModel.loan.value.toInt()
                        accessPin = this@HomeViewModel.accessPin.value

                        contact = Contact().apply {
                            email = this@HomeViewModel.email.value
                            address = this@HomeViewModel.address.value
                            phone = this@HomeViewModel.phone.value
                        }
                        employer = Employer().apply {
                            employerName = this@HomeViewModel.employeeName.value
                            employerAddress = this@HomeViewModel.employeeAddress.value
                            responsibilities = this@HomeViewModel.responsibilities.value
                            post = this@HomeViewModel.post.value
                        }
                        nextOfKin = NextOfKin().apply {
                            name = this@HomeViewModel.nokName.value
                            phone = this@HomeViewModel.nokPhone.value
                            relationship = this@HomeViewModel.nokRelationship.value
                            occupation = this@HomeViewModel.occupation.value
                            age = this@HomeViewModel.age.value
                        }
                        referee = Referee().apply {
                            name = this@HomeViewModel.refName.value
                            phone = this@HomeViewModel.refPhone.value
                            reMemberNo = this@HomeViewModel.refMembershipNo.value
                            name2 = this@HomeViewModel.refName2.value
                            phone2 = this@HomeViewModel.refPhone2.value
                            reMemberNo2 = this@HomeViewModel.refMembershipNo2.value
                        }

                    })
                } catch (e: Exception) {
                    Log.e(TAG, "insertGrtProfile: $e",)

                }
            }
        }
    }

    fun updateGtrPersonalProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            if (objectId.value.isNotEmpty()) {
                try {
                    AlMurabbiDB.updateMemberPersonalInfo(grtProfile = GrtProfile().apply {
                        _id = ObjectId(hexString = this@HomeViewModel.objectId.value)
                        name = this@HomeViewModel.name.value
                        refNo = this@HomeViewModel.formNo.value
                        dateOfBirth = this@HomeViewModel.dateOfBirth.value
                        sex = this@HomeViewModel.sex.value
                        stateOfOrgin = this@HomeViewModel.stateOfOrigin.value
                        lga = this@HomeViewModel.lga.value
                        homeTown = this@HomeViewModel.hometown.value
                        maritalStatus = this@HomeViewModel.maritalStatus.value

                    })
                } catch (e: Exception) {
                    Log.e(TAG, "updateGtrProfile: ${e.message}",)
                }

            }

        }
    }
    fun updateGtrContactProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            if (objectId.value.isNotEmpty()) {
                try {
                    AlMurabbiDB.updateMemberContactInfo(grtProfile = GrtProfile().apply {
                        _id = ObjectId(hexString = this@HomeViewModel.objectId.value)
                        contact = Contact().apply {
                            email = this@HomeViewModel.email.value
                            address = this@HomeViewModel.address.value
                            phone = this@HomeViewModel.phone.value
                        }


                    })
                } catch (e: Exception) {
                    Log.e(TAG, "updateGtrProfile: ${e.message}",)
                }

            }

        }
    }
    fun updateGtrEmployerProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            if (objectId.value.isNotEmpty()) {
                try {
                    AlMurabbiDB.updateMemberEmploymentInfo(grtProfile = GrtProfile().apply {
                        _id = ObjectId(hexString = this@HomeViewModel.objectId.value)
                        employer = Employer().apply {
                            employerName = this@HomeViewModel.employeeName.value
                            employerAddress = this@HomeViewModel.employeeAddress.value
                            responsibilities = this@HomeViewModel.responsibilities.value
                            post = this@HomeViewModel.post.value
                        }


                    })
                } catch (e: Exception) {
                    Log.e(TAG, "updateGtrProfile: ${e.message}",)
                }

            }

        }
    }
    fun updateGtrNextOfKinProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            if (objectId.value.isNotEmpty()) {
                try {
                    AlMurabbiDB.updateMemberNextOfKinInfo(grtProfile = GrtProfile().apply {
                        _id = ObjectId(hexString = this@HomeViewModel.objectId.value)
                        nextOfKin = NextOfKin().apply {
                            name = this@HomeViewModel.nokName.value
                            phone = this@HomeViewModel.nokPhone.value
                            relationship = this@HomeViewModel.nokRelationship.value
                            occupation = this@HomeViewModel.occupation.value
                            age = this@HomeViewModel.age.value
                        }
                    })
                } catch (e: Exception) {
                    Log.e(TAG, "updateGtrProfile: ${e.message}",)
                }

            }

        }
    }
    fun updateGtrRefereeProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            if (objectId.value.isNotEmpty()) {
                try {
                    AlMurabbiDB.updateMemberRefereeInfo(grtProfile = GrtProfile().apply {
                        _id = ObjectId(hexString = this@HomeViewModel.objectId.value)
                        referee = Referee().apply {
                            name = this@HomeViewModel.refName.value
                            phone = this@HomeViewModel.refPhone.value
                            reMemberNo = this@HomeViewModel.refMembershipNo.value
                            name2 = this@HomeViewModel.refName2.value
                            phone2 = this@HomeViewModel.refPhone2.value
                            reMemberNo2 = this@HomeViewModel.refMembershipNo2.value
                        }

                    })
                } catch (e: Exception) {
                    Log.e(TAG, "updateGtrProfile: ${e.message}",)
                }

            }

        }
    }

    fun deleteGtrProfile() {
        viewModelScope.launch {
            if (objectId.value.isNotEmpty()) {
                AlMurabbiDB.deleteMember(id = ObjectId(hexString = objectId.value))
            }
        }
    }


    fun getMemberWIthID() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                AlMurabbiDB.getMemberWithID(_id = ObjectId(hexString = objectId.value))
                    .let {
                        member.value = it
                    }
            } catch (e: RealmException) {
                Log.e(TAG, "error Getting Member with Id: $e",)
            }
        }
    }

    fun getMemberWIthMemberRegNo() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                AlMurabbiDB.getMemberWithMemberRegNp(memberRegNo = formNo.value)
                    .collect {
                        data.value = it
                    }
            } catch (e: RealmException) {
                Log.e(TAG, "error Getting Member with Id: $e",)
            }
        }
    }


}