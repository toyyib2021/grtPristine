package com.stevdza.san.testapp.screen.profileDetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stevdza.san.testapp.screen.HomeViewModel
import com.stevdza.san.testapp.screen.memberDetails.ContactInfo
import com.stevdza.san.testapp.screen.memberDetails.EmployeeInfo
import com.stevdza.san.testapp.screen.memberDetails.NextOfKinInfo
import com.stevdza.san.testapp.screen.memberDetails.PersonalInfo
import com.stevdza.san.testapp.screen.memberDetails.ProfileDetails
import com.stevdza.san.testapp.screen.memberDetails.RefereeInfo
import com.stevdza.san.testapp.screen.memberDetails.TopAppBar
import com.stevdza.san.testapp.ui.Constants
import com.stevdza.san.testapp.ui.Constants.EMPLOYMENT_INFO_PROFILE_LIST
import com.stevdza.san.testapp.ui.Constants.LOCATION_INFO_PROFILE_LIST
import com.stevdza.san.testapp.ui.Constants.NEXT_OF_KIN_PROFILE_LIST
import com.stevdza.san.testapp.ui.Constants.PERSONAL_INFOR_PROFILE_LIST
import com.stevdza.san.testapp.ui.Constants.PROFILE_LIST
import com.stevdza.san.testapp.ui.Constants.REFEREE_INFOR_PROFILE_LIST
import com.stevdza.san.testapp.ui.theme.RoyalHealht

@Composable
fun ProfileScreen(onBackClick:()-> Unit, memberId: String){
    val homeVM: HomeViewModel = viewModel()

    LaunchedEffect(key1 = true ){
        homeVM.objectId.value=memberId
        homeVM.getMemberWIthID()
    }

    homeVM.member.value?.let {member->
        homeVM.name.value = member.name
        homeVM.formNo.value = member.refNo
        homeVM.dateOfBirth.value = member.dateOfBirth
        homeVM.sex.value = member.sex
        homeVM.stateOfOrigin.value = member.stateOfOrgin
        homeVM.lga.value = member.lga
        homeVM.hometown.value = member.homeTown
        homeVM.maritalStatus.value = member.maritalStatus
        homeVM.pleadge.value = member.pleadge.toString()
        member.contact?.let {contact->
            homeVM.email.value = contact.email
            homeVM.phone.value = contact.phone
            homeVM.address.value = contact.address
        }
        member.employer?.let {employer->
            homeVM.employeeName.value = employer.employerName
            homeVM.employeeAddress.value = employer.employerAddress
            homeVM.responsibilities.value = employer.responsibilities
            homeVM.post.value = employer.post

        }
        member.nextOfKin?.let {nextOfKin->
            homeVM.nokName.value = nextOfKin.name
            homeVM.nokPhone.value = nextOfKin.phone
            homeVM.nokRelationship.value = nextOfKin.relationship
            homeVM.occupation.value = nextOfKin.occupation
            homeVM.age.value = nextOfKin.age

        }
        member.referee?.let {referee->
            homeVM.refName.value = referee.name
            homeVM.refPhone.value = referee.phone
            homeVM.refMembershipNo.value = referee.reMemberNo
            homeVM.refName2.value = referee.name2
            homeVM.refPhone2.value = referee.phone2
            homeVM.refMembershipNo2.value = referee.reMemberNo2
        }
    }
    var snackbarMessage by remember { mutableStateOf("") }
    var snackbarVisible by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            TopAppBar(
                listState = homeVM.profileState.value,
                onBackClick = {},
                profileList = { homeVM.profileState.value = PROFILE_LIST },
                paymentList = { onBackClick() },
                withdrawalList = {  },
                title =homeVM.name.value)
        }
    ) {
        val pad = it
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(it)) {
            if(snackbarVisible){
                Snackbar(
                    modifier=Modifier.padding(horizontal = 15.dp),
                    backgroundColor = RoyalHealht,
                    contentColor = Color.White,
                    action = {
                        Text(modifier = Modifier.clickable {
                        snackbarVisible=false
                        }, text = "Dismiss", color = Color.White, fontSize = 10.sp)
                    }) {
                    Text(text = snackbarMessage, color = Color.White, fontSize = 12.sp )
                }
            }
            when(homeVM.profileState.value){
                PROFILE_LIST ->{
                    ProfileDetails(
                        modifier = Modifier.fillMaxSize(),
                        onPersonalClick = {homeVM.profileState.value = PERSONAL_INFOR_PROFILE_LIST },
                        onLocationClick = {homeVM.profileState.value = LOCATION_INFO_PROFILE_LIST },
                        onEmploymentClick = {homeVM.profileState.value = EMPLOYMENT_INFO_PROFILE_LIST },
                        onNextOfKinClick = {homeVM.profileState.value = NEXT_OF_KIN_PROFILE_LIST },
                        onRefereeClick = {homeVM.profileState.value = REFEREE_INFOR_PROFILE_LIST },
                    )
                }
                PERSONAL_INFOR_PROFILE_LIST ->{
                    PersonalInfo(
                        modifier = Modifier.fillMaxWidth(),
                        onPersonalInfoUpdate = {
                            homeVM.updateGtrPersonalProfile()
                            snackbarMessage="Saved Successfully "
                            snackbarVisible=true
                        }
                    )
                }
                LOCATION_INFO_PROFILE_LIST -> {
                    ContactInfo(
                        modifier = Modifier.fillMaxWidth(),
                        onContactInfoUpdate = {
                            homeVM.updateGtrContactProfile()
                            snackbarMessage="Saved Successfully "
                            snackbarVisible=true
                        }

                    )
                }
                EMPLOYMENT_INFO_PROFILE_LIST -> {
                    EmployeeInfo(
                        modifier = Modifier.fillMaxWidth(),
                        onEmployeeInfoUpdate = {
                            homeVM.updateGtrEmployerProfile()
                            snackbarMessage="Saved Successfully "
                            snackbarVisible=true
                        }
                    )
                }
                NEXT_OF_KIN_PROFILE_LIST -> {
                    NextOfKinInfo(
                        modifier = Modifier.fillMaxWidth(),
                        onEmployeeInfoUpdate = {
                            homeVM.updateGtrNextOfKinProfile()
                            snackbarMessage="Saved Successfully "
                            snackbarVisible=true
                        }
                    )
                }
                REFEREE_INFOR_PROFILE_LIST -> {
                    RefereeInfo(
                        modifier = Modifier.fillMaxWidth(),
                        onRefereeInfoUpdate = {
                            homeVM.updateGtrRefereeProfile()
                            snackbarMessage="Saved Successfully "
                            snackbarVisible=true
                        }
                    )
                }

            }

        }
    }


}

