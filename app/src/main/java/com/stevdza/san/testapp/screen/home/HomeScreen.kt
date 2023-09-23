package com.stevdza.san.testapp.screen.home


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Snackbar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material.icons.filled.ArrowCircleRight
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SwitchAccount
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stevdza.san.testapp.dataMain.GrtProfile
import com.stevdza.san.testapp.dataMain.dataStore.FinOrSec
import com.stevdza.san.testapp.screen.HomeViewModel
import com.stevdza.san.testapp.ui.Constants.BALANCE_INFOR_PROFILE_LIST
import com.stevdza.san.testapp.ui.Constants.EMPLOYMENT_INFO_PROFILE_LIST
import com.stevdza.san.testapp.ui.Constants.LOCATION_INFO_PROFILE_LIST
import com.stevdza.san.testapp.ui.Constants.MEMBER_LIST
import com.stevdza.san.testapp.ui.Constants.NEXT_OF_KIN_PROFILE_LIST
import com.stevdza.san.testapp.ui.Constants.PERSONAL_INFOR_PROFILE_LIST
import com.stevdza.san.testapp.ui.Constants.PREVIEW
import com.stevdza.san.testapp.ui.Constants.REFEREE_INFOR_PROFILE_LIST
import com.stevdza.san.testapp.ui.Constants.REGISTER
import com.stevdza.san.testapp.ui.theme.RoyalHealht

import kotlinx.coroutines.delay
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(navToProfileScreen:(String)->Unit, navToAccountScreen:()->Unit, onSwitchAccountClick:()->Unit){
    val homeVM: HomeViewModel = viewModel()
    val grtProfile = homeVM.data.value
    var memberName by remember { mutableStateOf("") }
    homeVM.formNo.value =memberName
    var searchBarState by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val finOrSec = FinOrSec(context)
    val finOrSecKey = finOrSec.getKey.collectAsState(initial = "")
//    val finOrSecKeyi by remember { mutableStateOf("Sec") }

    Log.e(TAG, "finOrSecKey: ${finOrSecKey.value}", )
    Log.e(TAG, "memberName: $memberName", )
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if (finOrSecKey.value == "fin"){
                Log.e(TAG, "formNo: ${homeVM.formNo.value}", )
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)) {
//                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    TextField(
                        value = homeVM.formNo.value,
                        onValueChange = {homeVM.formNo.value=it},
                        shape = RectangleShape,
                        label = {
                            Text(text = "Enter Member No. Here")
                        },
                        modifier = Modifier.weight(9f),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(onSearch = {
                            homeVM.getMemberWIthMemberRegNo()
                        }),
                        trailingIcon = {
                            Icon(modifier = Modifier.clickable { homeVM.getMemberWIthMemberRegNo() },
                                imageVector = Icons.Default.Search, contentDescription ="Search" )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = RoyalHealht
                        )
                    )
                    Icon(modifier = Modifier.weight(1f).padding(top=15.dp).clickable { onSwitchAccountClick() },
                        imageVector = Icons.Default.SwitchAccount, contentDescription = "SwitchAccount")
                }
            }else{
                if (homeVM.memberAndRegState.value == MEMBER_LIST){
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)) {
                        Log.e(TAG, "formNo: ${homeVM.formNo.value}", )

                     TextField(
                         value = homeVM.formNo.value,
                         onValueChange = {homeVM.formNo.value=it},
                         shape = RectangleShape,
                         label = {
                             Text(text = "Enter Member No. Here")
                         },
                         modifier = Modifier.weight(9f),
                         keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                         keyboardActions = KeyboardActions(onSearch = {
                             homeVM.getMemberWIthMemberRegNo()
                         }),
                         trailingIcon = {
                             Icon(modifier = Modifier.clickable { homeVM.getMemberWIthMemberRegNo() },
                                 imageVector = Icons.Default.Search, contentDescription ="Search" )
                         },
                         colors = TextFieldDefaults.textFieldColors(
                             containerColor = Color.Transparent,
                             focusedIndicatorColor = RoyalHealht
                         )
                     )
                        Icon(modifier = Modifier.weight(1f).padding(top=15.dp).clickable { onSwitchAccountClick() },
                            imageVector = Icons.Default.SwitchAccount, contentDescription = "SwitchAccount")
                    }
                }else{
                    TopAppBar(title = "GRT Pristine", ) {
                        if (homeVM.memberAndRegState.value == REGISTER){
                            homeVM.memberAndRegState.value = MEMBER_LIST
                        }
                    }
                }

            }


        },
        content = {

            var dateOfBirthHide by remember { mutableStateOf(false) }
            val calendar = Calendar.getInstance()
            calendar.set(2023, 6, 14) // add year, month (Jan), date
            val datePickerState = rememberDatePickerState(
                initialSelectedDateMillis = calendar.timeInMillis,
                initialDisplayMode = DisplayMode.Picker

            )
            val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.ROOT)
            val dateOfBirth = formatter.format(Date(datePickerState.selectedDateMillis!!))

            val sexOptions = listOf("Male", "Female")
            var sexHide by remember { mutableStateOf(false) }
            var sexvm by remember { mutableStateOf("") }
            val (sex, sexOptionList) = remember { mutableStateOf(sexOptions[0] ) }
            sexvm=sex
            homeVM.sex.value=sexvm
            Log.e(TAG, "sexvm: $sexvm", )
            var snackbarVisible by remember { mutableStateOf(false) }
            var snackbarMessage by remember { mutableStateOf("") }

            LaunchedEffect(key1 = snackbarVisible){
                if (snackbarVisible){
                    delay(2000)
                    snackbarVisible = false
                }
            }


//            val scope = rememberCoroutineScope()



            Column(modifier = Modifier.padding(it),) {
                if(snackbarVisible){
                    Snackbar(
                        backgroundColor = Color.Black,
                        action = {
                            androidx.compose.material.Text(modifier = Modifier.clickable {
                                snackbarVisible=false
                            }, text = "Dismiss", color = Color.White)
                        }) {
                        androidx.compose.material.Text(text = snackbarMessage, color = Color.White)
                    }
                }

                DashboardContent(
                    grtProfile =grtProfile ,
                    onRegisterClick ={
                        homeVM.registationState.value= PERSONAL_INFOR_PROFILE_LIST
                        homeVM.memberAndRegState.value= REGISTER} ,
                    onMembersClick = {homeVM.memberAndRegState.value= MEMBER_LIST},
                    memberAndRegState = homeVM.memberAndRegState.value,
                    registationState = homeVM.registationState.value,
                    onNextClick = {
                        when(homeVM.registationState.value){
                            PERSONAL_INFOR_PROFILE_LIST ->{
                                if (
                                    homeVM.name.value.isNotEmpty()
                                    && homeVM.formNo.value.isNotEmpty() && homeVM.dateOfBirth.value.isNotEmpty() && homeVM.sex.value.isNotEmpty()
                                    && homeVM.stateOfOrigin.value.isNotEmpty() && homeVM.lga.value.isNotEmpty() && homeVM.hometown.value.isNotEmpty()
                                    && homeVM.maritalStatus.value.isNotEmpty()
                                ){
                                    Log.e(TAG, "date of birth: ${homeVM.dateOfBirth.value}", )
                                    Log.e(TAG, "sex: ${homeVM.sex.value}", )
                                    homeVM.registationState.value=LOCATION_INFO_PROFILE_LIST
                                }else{
                                    Log.e(TAG, "date of birth: ${homeVM.dateOfBirth.value}", )
                                    Log.e(TAG, "sex: ${homeVM.sex.value}", )
                                    snackbarVisible=true
                                    snackbarMessage="Some feilds are empty"
                                }
                            }
                            LOCATION_INFO_PROFILE_LIST ->{
                                if (homeVM.email.value.isNotEmpty()
                                    && homeVM.address.value.isNotEmpty() && homeVM.phone.value.isNotEmpty()
                                ){
                                    homeVM.registationState.value=EMPLOYMENT_INFO_PROFILE_LIST
//
                                }else{
                                    snackbarVisible=true
                                    snackbarMessage="Some feilds are empty"
                                }
                            }
                            EMPLOYMENT_INFO_PROFILE_LIST ->{
                                if (
                                    homeVM.employeeName.value.isNotEmpty() && homeVM.employeeAddress.value.isNotEmpty() && homeVM.responsibilities.value.isNotEmpty()
                                    && homeVM.post.value.isNotEmpty()
                                ){
                                    homeVM.registationState.value=NEXT_OF_KIN_PROFILE_LIST
                                }else{
                                    snackbarVisible=true
                                    snackbarMessage="Some feilds are empty"
                                }

                            }
                            NEXT_OF_KIN_PROFILE_LIST ->{
                                if (
                                    homeVM.nokName.value.isNotEmpty() && homeVM.nokPhone.value.isNotEmpty() && homeVM.occupation.value.isNotEmpty()
                                    && homeVM.nokRelationship.value.isNotEmpty() && homeVM.age.value.isNotEmpty()
                                ){
                                    homeVM.registationState.value= BALANCE_INFOR_PROFILE_LIST
                                }else{
                                    snackbarVisible=true
                                    snackbarMessage="Some feilds are empty"
                                }

                            }
                            REFEREE_INFOR_PROFILE_LIST ->{
                                if (
                                    homeVM.refName.value.isNotEmpty()
                                    && homeVM.refPhone.value.isNotEmpty() && homeVM.refMembershipNo.value.isNotEmpty() &&
                                    homeVM.refName.value.isNotEmpty() && homeVM.refPhone.value.isNotEmpty() &&
                                    homeVM.refMembershipNo.value.isNotEmpty()
                                ){
                                    homeVM.registationState.value= PREVIEW
                                }else{
                                    snackbarVisible=true
                                    snackbarMessage="Some fields are empty"
                                }

                            }
                            BALANCE_INFOR_PROFILE_LIST ->{
                                if (
                                    homeVM.adminBal.value.isNotEmpty() && homeVM.pledgeBal.value.isNotEmpty() &&
                                    homeVM.targeBal.value.isNotEmpty() && homeVM.sharesValue.value.isNotEmpty() &&
                                    homeVM.loanBal.value.isNotEmpty()
                                ){
                                    homeVM.registationState.value= REFEREE_INFOR_PROFILE_LIST
                                }else{
                                    snackbarVisible=true
                                    snackbarMessage="Some fields are empty"
                                }
                            }

                        }
                    },
                    onBackClick= {
                        when(homeVM.registationState.value){
                            PERSONAL_INFOR_PROFILE_LIST ->{
                                homeVM.registationState.value=PERSONAL_INFOR_PROFILE_LIST
                            }
                            LOCATION_INFO_PROFILE_LIST ->{
                                homeVM.registationState.value=PERSONAL_INFOR_PROFILE_LIST
                            }
                            EMPLOYMENT_INFO_PROFILE_LIST ->{
                                homeVM.registationState.value=LOCATION_INFO_PROFILE_LIST
                            }
                            NEXT_OF_KIN_PROFILE_LIST ->{
                                homeVM.registationState.value=EMPLOYMENT_INFO_PROFILE_LIST
                            }
                            BALANCE_INFOR_PROFILE_LIST ->{
                                homeVM.memberAndRegState.value= NEXT_OF_KIN_PROFILE_LIST

                            }
                            REFEREE_INFOR_PROFILE_LIST ->{
                                homeVM.memberAndRegState.value= BALANCE_INFOR_PROFILE_LIST
                            }

                        }
                    },
                    onSubmitClick = {
                        homeVM.memberAndRegState.value= MEMBER_LIST
                        homeVM.insertGrtProfile()
                    },
                    onPersonalInfoClick={
                        homeVM.registationState.value= PERSONAL_INFOR_PROFILE_LIST
                    },
                    onContactInfoClick={
                        homeVM.registationState.value= LOCATION_INFO_PROFILE_LIST
                    },
                    onEmploymentInfoClick={
                        homeVM.registationState.value= EMPLOYMENT_INFO_PROFILE_LIST
                    },
                    onNextOfKinInfoClick={
                        homeVM.registationState.value= NEXT_OF_KIN_PROFILE_LIST
                    },
                    onRefereeInfoClick={
                        homeVM.registationState.value= REFEREE_INFOR_PROFILE_LIST
                    },
                    navToProfileScreen = navToProfileScreen,
                    name = homeVM.name.value, onNameChange = {homeVM.name.value=it}, label = "Name", placeholder = "Enter Your Name Here",
                    refNo=homeVM.formNo.value,
                    onRefNoChange={homeVM.formNo.value=it},
                    refNoLabel="Membership Number",
                    refNoPlaceholder="Enter Your Membership Number Here",
                    dateOfBirth=homeVM.dateOfBirth.value,
                    datePickerState=datePickerState,
                    dateOfBirthHide=dateOfBirthHide,
                    onEditDateClick={dateOfBirthHide=true},
                    onDatePick={
                        homeVM.dateOfBirth.value=dateOfBirth
                        dateOfBirthHide=false},
                    SelectedDateOfBirth=dateOfBirth,
                    sex=homeVM.sex.value,
                    sexHide=sexHide,
                    onSexDropDownMenuClick={
                        sexOptionList(it)
                        homeVM.sex.value = sex
                        sexHide=false
                    },
                    onSexEdit={sexHide=true},
                    radioOptions=sexOptions,
                    stateOfOrigin=homeVM.stateOfOrigin.value,
                    onStateOfOriginChange={homeVM.stateOfOrigin.value=it},
                    stateOfOriginLabel="State of Origin",
                    stateOfOriginPlaceholder="Enter your State of Origin Here",
                    lga=homeVM.lga.value,
                    onLgaChange={homeVM.lga.value=it},
                    lgaLabel="LGA",
                    lgaPlaceholder="Enter your LGA Here",
                    homeTown=homeVM.hometown.value,
                    onHometownChange={homeVM.hometown.value=it},
                    homeTownLabel="Home Town",
                    homeTownPlaceholder="Enter your Home Town",
                    maritalStatus=homeVM.maritalStatus.value,
                    onMaritalStatusChange={homeVM.maritalStatus.value=it},
                    maritalStatusLabel="Marital Status",
                    maritalStatusPlaceholder="Enter your Marital Status Here",


                    email = homeVM.email.value, onEmailChange = {homeVM.email.value=it},emailLabel = "Email", emailPlaceholder = "Enter Email Here",
                    address=homeVM.address.value, onAddressChange={homeVM.address.value=it}, addressLabel="Address", addressPlaceholder="Enter you Address Here",
                    phone=homeVM.phone.value, onPhoneChange={homeVM.phone.value=it }, nokphoneLabel="Phone", phonePlaceholder="Enter your Phone Numner Here",

                    employeeName = homeVM.employeeName.value, onEmployeeNameChange = {homeVM.employeeName.value=it},
                    employeeNameLabel = "Company Name / Employee Name",
                    employeeNamePlaceholder ="Enter Company Name Here",

                    employeeAddress=homeVM.employeeAddress.value,
                    onEmployeeAddressChange={homeVM.employeeAddress.value=it},
                    employeeAddressLabel="Employee Address",
                    employeeAddressPlaceholder="Enter your Employee Address here",
                    responsibilities=homeVM.responsibilities.value,
                    onResponsibilitiesChange={homeVM.responsibilities.value=it },
                    responsibilitiesLabel="Responsibilities",
                    responsibilitiesPlaceholder="Enter Your Responsibilities Here",
                    post=homeVM.post.value,
                    onPostChange={homeVM.post.value=it},
                    postLabel="Post",
                    postPlaceholder="Enter your Post Here",

                    nokName = homeVM.nokName.value,
                    onNokNameChange = {homeVM.nokName.value=it},
                    nokNameLabel = "Next of Kin Name",
                    nokNamePlaceholder ="Enter Next of Kin Name Here",

                    nokPhone=homeVM.nokPhone.value,
                    onNokPhoneChange={homeVM.nokPhone.value=it},
                    nokPhoneLabel ="Phone Number",
                    nokPhonePlaceholder ="Enter your not",

                    relationship=homeVM.nokRelationship.value,
                    onRelationshipChange={homeVM.nokRelationship.value=it},
                    relationshipLabel="Relationship",
                    relationshipPlaceholder="Enter Your Relationship Here",

                    occupation=homeVM.occupation.value,
                    onOccupationChange={homeVM.occupation.value=it},
                    occupationLabel="N O K Occupation",
                    occupationPlaceholder="Enter Next of Occupation",

                    age=homeVM.age.value,
                    onAgeChange={homeVM.age.value=it},
                    ageLabel="Age",
                    agePlaceholder="Enter your Year Here",


                    refName= homeVM.refName.value,
                    onRefNameChange = {homeVM.refName.value=it },
                    refNameLabel = "Referee Name",
                    refNamePlaceholder = "Enter Referee Name Here",

                    refPhone=homeVM.refPhone.value,
                    onRefPhoneChange={homeVM.refPhone.value=it},
                    refPhoneLabel="Referee Phone",
                    refPhonePlaceholder="Enter Referee Phone Here",

                    refMemberNo=homeVM.refMembershipNo.value,
                    onRefMemberNoChange={homeVM.refMembershipNo.value=it},
                    refMemberNoLabel="Referee Member Number",
                    refMemberNoPlaceholder="Enter Referee Member Number here",

                    refName2=homeVM.refName2.value,
                    onRefNameChange2={homeVM.refName2.value=it},
                    refNameLabel2="Referee Name",
                    refNamePlaceholder2="Enter Referee Name Here",

                    refPhone2=homeVM.refPhone2.value,
                    onRefPhoneChange2={homeVM.refPhone2.value=it},
                    refPhoneLabel2="Referee Phone",
                    refPhonePlaceholder2="Enter RefereePhone here",

                    refMemberNo2=homeVM.refMembershipNo2.value,
                    onRefMemberNoChange2={homeVM.refMembershipNo2.value=it},
                    refMemberNoLabel2="Referee Member No",
                    refMemberNoPlaceholder2="Enter Referee Member Number here",

                    adminFeeBal = homeVM.adminBal.value,
                    onAdminFeeBalChange = {homeVM.adminBal.value=it.filter { it.isDigit() }},
                    adminFeeBalLabel = "Admin",
                    adminFeeBalPlaceholder = "Enter Admin Payment here",

                    pledgeBal = homeVM.pledgeBal.value,
                    onPledgeBalChange = {homeVM.pledgeBal.value=it.filter { it.isDigit() }},
                    pledgeBalLabel = "Pledge",
                    pledgeBalPlaceholder = "Enter Pledge Payment Here",

                    loanBal = homeVM.loanBal.value,
                    onLoanBalChange = {homeVM.loanBal.value=it.filter { it.isDigit() }},
                    loanBalLabel = "Loan",
                    loanBalPlaceholder = "Enter Loan Payment Here",

                    sharesBal = homeVM.sharesValue.value,
                    onSharesBalChange = {homeVM.sharesValue.value=it.filter { it.isDigit() }},
                    sharesBalLabel = "Share's Unit",
                    sharesBalPlaceholder = "Enter Share Unit Here",

                    targetBal = homeVM.targeBal.value,
                    onTargetBalChange = {homeVM.targeBal.value=it.filter { it.isDigit() }},
                    targetBalLabel = "Target",
                    targetBalPlaceholder = "Enter Target Payment Here",
                    finOrSec = finOrSecKey.value,
                    onAccountClick = { navToAccountScreen() }

                )
            }

        }


    )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardContent(
    modifier:Modifier=Modifier,
    grtProfile: List<GrtProfile>,
    onRegisterClick: ()-> Unit,
    onMembersClick:()-> Unit,
    memberAndRegState: String,
    navToProfileScreen:(String)->Unit,
    name: String,
    onNameChange:(String)->Unit,
    label: String,
    placeholder: String,
    refNo: String,
    onRefNoChange:(String)->Unit,
    refNoLabel: String,
    refNoPlaceholder: String,
    dateOfBirth: String,
    datePickerState: DatePickerState,
    dateOfBirthHide: Boolean,
    onEditDateClick:()->Unit,
    onDatePick:()->Unit,
    SelectedDateOfBirth: String,
    sex: String,
    sexHide: Boolean,
    onSexDropDownMenuClick:(String)->Unit,
    onSexEdit:()->Unit,
    radioOptions: List<String>,
    stateOfOrigin: String,
    onStateOfOriginChange:(String)->Unit,
    stateOfOriginLabel: String,
    stateOfOriginPlaceholder: String,
    lga: String,
    onLgaChange:(String)->Unit,
    lgaLabel: String,
    lgaPlaceholder: String,
    homeTown: String,
    onHometownChange:(String)->Unit,
    homeTownLabel: String,
    homeTownPlaceholder: String,
    maritalStatus: String,
    onMaritalStatusChange:(String)->Unit,
    maritalStatusLabel: String,
    maritalStatusPlaceholder: String,

    registationState: String,
    onNextClick: ()->Unit,
    onBackClick: ()->Unit,
    onSubmitClick: ()->Unit,
    onPersonalInfoClick: ()-> Unit,
    onContactInfoClick: ()-> Unit,
    onEmploymentInfoClick: ()-> Unit,
    onNextOfKinInfoClick: ()-> Unit,
    onRefereeInfoClick: ()-> Unit,

    email: String,
    onEmailChange:(String)->Unit,
    emailLabel: String,
    emailPlaceholder: String,
    address: String,
    onAddressChange:(String)->Unit,
    addressLabel: String,
    addressPlaceholder: String,
    phone: String,
    onPhoneChange:(String)->Unit,
    nokphoneLabel: String,
    phonePlaceholder: String,

    employeeName: String,
    onEmployeeNameChange:(String)->Unit,
    employeeNameLabel: String,
    employeeNamePlaceholder: String,
    employeeAddress: String,
    onEmployeeAddressChange:(String)->Unit,
    employeeAddressLabel: String,
    employeeAddressPlaceholder: String,
    responsibilities: String,
    onResponsibilitiesChange:(String)->Unit,
    responsibilitiesLabel: String,
    responsibilitiesPlaceholder: String,
    post: String,
    onPostChange:(String)->Unit,
    postLabel: String,
    postPlaceholder: String,
    nokName: String,
    onNokNameChange:(String)->Unit,
    nokNameLabel: String,
    nokNamePlaceholder: String,
    nokPhone: String,
    onNokPhoneChange:(String)->Unit,
    nokPhoneLabel: String,
    nokPhonePlaceholder: String,
    relationship: String,
    onRelationshipChange:(String)->Unit,
    relationshipLabel: String,
    relationshipPlaceholder: String,
    occupation: String,
    onOccupationChange:(String)->Unit,
    occupationLabel: String,
    occupationPlaceholder: String,
    age: String,
    onAgeChange:(String)->Unit,
    ageLabel: String,
    agePlaceholder: String,


    refName: String,
    onRefNameChange:(String)->Unit,
    refNameLabel: String,
    refNamePlaceholder: String,

    refPhone: String,
    onRefPhoneChange:(String)->Unit,
    refPhoneLabel: String,
    refPhonePlaceholder: String,

    refMemberNo: String,
    onRefMemberNoChange:(String)->Unit,
    refMemberNoLabel: String,
    refMemberNoPlaceholder: String,

    refName2: String,
    onRefNameChange2:(String)->Unit,
    refNameLabel2: String,
    refNamePlaceholder2: String,

    refPhone2: String,
    onRefPhoneChange2:(String)->Unit,
    refPhoneLabel2: String,
    refPhonePlaceholder2: String,

    refMemberNo2: String,
    onRefMemberNoChange2:(String)->Unit,
    refMemberNoLabel2: String,
    refMemberNoPlaceholder2: String,

    adminFeeBal: String,
    onAdminFeeBalChange: (String)-> Unit,
    adminFeeBalLabel:String,
    adminFeeBalPlaceholder:String,

    pledgeBal: String,
    onPledgeBalChange: (String)-> Unit,
    pledgeBalLabel:String,
    pledgeBalPlaceholder:String,

    loanBal: String,
    onLoanBalChange: (String)-> Unit,
    loanBalLabel:String,
    loanBalPlaceholder:String,

    sharesBal: String,
    onSharesBalChange: (String)-> Unit,
    sharesBalLabel:String,
    sharesBalPlaceholder:String,

    targetBal: String,
    onTargetBalChange: (String)-> Unit,
    targetBalLabel:String,
    targetBalPlaceholder:String,

    finOrSec: String,
    onAccountClick:()-> Unit,
){
    Column {
        if (memberAndRegState == MEMBER_LIST){
            Column(
                modifier
                    .weight(9f)
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                LazyColumn {
                    items(grtProfile){
                        Column(modifier = Modifier.clickable { navToProfileScreen(it._id.toHexString()) }) {
                            Text(text = it.name, color = RoyalHealht,
                                fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Text(text = it.refNo, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.padding(5.dp))
                            Divider(modifier=Modifier.fillMaxWidth(), thickness = 3.dp)

                        }
                    }
                }
            }

        }
        else if (memberAndRegState == REGISTER){
            Column(
                modifier
                    .weight(9f)
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                if (registationState == PREVIEW){
                    Card(
                        modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp), shape = RectangleShape,
                        colors = CardDefaults.cardColors(
                            contentColor = Color.White,
                            containerColor = RoyalHealht
                        ),


                    ) {
                        Text(modifier=Modifier.padding(10.dp),text = "Preview Information")
                    }
                }
                else{
                    Text(
                        text =
                        when (registationState ) {
                            PERSONAL_INFOR_PROFILE_LIST ->{
                                "Personal Information"
                            }
                            LOCATION_INFO_PROFILE_LIST ->{
                                "Contact Information"
                            }
                            EMPLOYMENT_INFO_PROFILE_LIST ->{
                                "Employment Information"
                            }
                            NEXT_OF_KIN_PROFILE_LIST ->{
                                "Next of Kin Information"
                            }
                            REFEREE_INFOR_PROFILE_LIST ->{
                                "Referee Information"
                            }
                            BALANCE_INFOR_PROFILE_LIST ->{
                                "Account Opening Balance"
                            }

                            else -> {
                                ""
                            }
                        },
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                LazyColumn{
                    item{
                        when (registationState ) {
                            PERSONAL_INFOR_PROFILE_LIST ->{
                                Register(
                                    name = name, onNameChange =onNameChange , label = label, placeholder =placeholder,
                                    refNo=refNo,
                                    onRefNoChange=onRefNoChange,
                                    refNoLabel=refNoLabel,
                                    refNoPlaceholder=refNoPlaceholder,
                                    dateOfBirth=dateOfBirth,
                                    datePickerState=datePickerState,
                                    dateOfBirthHide=dateOfBirthHide,
                                    onEditDateClick=onEditDateClick,
                                    onDatePick=onDatePick,
                                    SelectedDateOfBirth=SelectedDateOfBirth,
                                    sex=sex,
                                    sexHide=sexHide,
                                    onSexDropDownMenuClick=onSexDropDownMenuClick,
                                    onSexEdit=onSexEdit,
                                    radioOptions=radioOptions,
                                    stateOfOrigin=stateOfOrigin,
                                    onStateOfOriginChange=onStateOfOriginChange,
                                    stateOfOriginLabel=stateOfOriginLabel,
                                    stateOfOriginPlaceholder=stateOfOriginPlaceholder,
                                    lga=lga,
                                    onLgaChange=onLgaChange,
                                    lgaLabel=lgaLabel,
                                    lgaPlaceholder=lgaPlaceholder,
                                    homeTown=homeTown,
                                    onHometownChange=onHometownChange,
                                    homeTownLabel=homeTownLabel,
                                    homeTownPlaceholder=homeTownPlaceholder,
                                    maritalStatus=maritalStatus,
                                    onMaritalStatusChange=onMaritalStatusChange,
                                    maritalStatusLabel=maritalStatusLabel,
                                    maritalStatusPlaceholder=maritalStatusPlaceholder,
                                )
                            }
                            LOCATION_INFO_PROFILE_LIST ->{
                                ContactInfo(
                                    email = email, onEmailChange = onEmailChange,emailLabel = emailLabel, emailPlaceholder = emailPlaceholder,
                                    address=address,
                                    onAddressChange=onAddressChange,
                                    addressLabel=addressLabel,
                                    addressPlaceholder=addressPlaceholder,
                                    phone=phone,
                                    onPhoneChange=onPhoneChange,
                                    phoneLabel=nokphoneLabel,
                                    phonePlaceholder=phonePlaceholder,
                                )
                            }
                            EMPLOYMENT_INFO_PROFILE_LIST ->{
                                EmployeeInfo(
                                    employeeName = employeeName,
                                    onEmployeeNameChange = onEmployeeNameChange,
                                    employeeNameLabel = employeeNameLabel,
                                    employeeNamePlaceholder = employeeNamePlaceholder,
                                    employeeAddress=employeeAddress,
                                    onEmployeeAddressChange=onEmployeeAddressChange,
                                    employeeAddressLabel=employeeAddressLabel,
                                    employeeAddressPlaceholder=employeeAddressPlaceholder,
                                    responsibilities=responsibilities,
                                    onResponsibilitiesChange=onResponsibilitiesChange,
                                    responsibilitiesLabel=responsibilitiesLabel,
                                    responsibilitiesPlaceholder=responsibilitiesPlaceholder,
                                    post=post,
                                    onPostChange=onPostChange,
                                    postLabel=postLabel,
                                    postPlaceholder=postPlaceholder,
                                )
                            }
                            NEXT_OF_KIN_PROFILE_LIST ->{
                                NextOfKinInfo(
                                    nokName = nokName,
                                    onNokNameChange = onNokNameChange,
                                    nokNameLabel = nokNameLabel,
                                    nokNamePlaceholder =nokNamePlaceholder,
                                    nokPhone=nokPhone,
                                    onNokPhoneChange=onNokPhoneChange,
                                    phoneLabel=nokPhoneLabel,
                                    nokPhonePlaceholder=nokPhonePlaceholder,
                                    relationship=relationship,
                                    onRelationshipChange=onRelationshipChange,
                                    relationshipLabel=relationshipLabel,
                                    relationshipPlaceholder=relationshipPlaceholder,
                                    occupation=occupation,
                                    onOccupationChange=onOccupationChange,
                                    occupationLabel=occupationLabel,
                                    occupationPlaceholder=occupationPlaceholder,
                                    age=age,
                                    onAgeChange=onAgeChange,
                                    ageLabel=ageLabel,
                                    agePlaceholder=agePlaceholder

                                )

                            }
                            REFEREE_INFOR_PROFILE_LIST ->{
                                RefereeInfo(
                                    refName = refName,
                                    onRefNameChange = onRefNameChange,
                                    refNameLabel = refNameLabel,
                                    refNamePlaceholder = refNamePlaceholder,
                                    refPhone=refPhone,
                                    onRefPhoneChange=onRefPhoneChange,
                                    refPhoneLabel=refPhoneLabel,
                                    refPhonePlaceholder=refPhonePlaceholder,

                                    refMemberNo=refMemberNo,
                                    onRefMemberNoChange=onRefMemberNoChange,
                                    refMemberNoLabel=refMemberNoLabel,
                                    refMemberNoPlaceholder=refMemberNoPlaceholder,

                                    refName2=refName2,
                                    onRefNameChange2=onRefNameChange2,
                                    refNameLabel2=refNameLabel2,
                                    refNamePlaceholder2=refNamePlaceholder2,

                                    refPhone2=refPhone2,
                                    onRefPhoneChange2=onRefPhoneChange2,
                                    refPhoneLabel2=refPhoneLabel2,
                                    refPhonePlaceholder2=refPhonePlaceholder2,

                                    refMemberNo2=refMemberNo2,
                                    onRefMemberNoChange2=onRefMemberNoChange2,
                                    refMemberNoLabel2=refMemberNoLabel2,
                                    refMemberNoPlaceholder2=refMemberNoPlaceholder2,
                                )
                            }
                            PREVIEW ->{
                                PreviewRegistrationDetails(
                                    onPersonalInfoClick=onPersonalInfoClick,
                                    onContactInfoClick=onContactInfoClick,
                                    onEmploymentInfoClick=onEmploymentInfoClick,
                                    onNextOfKinInfoClick=onNextOfKinInfoClick,
                                    onRefereeInfoClick=onRefereeInfoClick,
                                    name = name,
                                    label = label,
                                    refNo = refNo,
                                    refMemberNoLabel = refNoLabel,
                                    dateOfBirth = dateOfBirth,
                                    dateOfBirthLabel = "Date of Birth",
                                    sex = sex,
                                    sexLabel = "Sex",
                                    stateOfOrigin = stateOfOrigin,
                                    stateOfOriginLabel = stateOfOriginLabel,
                                    lga = lga,
                                    lgaLabel = lgaLabel,
                                    homeTown = homeTown,
                                    homeTownLabel = homeTownLabel,
                                    maritalStatus = maritalStatus,
                                    maritalStatusLabel = maritalStatusLabel,

                                    email = email,
                                    emailLabel = emailLabel,
                                    address=address,
                                    addressLabel=addressLabel,
                                    phone=phone,
                                    phoneLabel=nokPhoneLabel,

                                    employeeName =employeeName ,
                                    employeeNameLabel = employeeNameLabel,
                                    employeeAddress=employeeAddress,
                                    employeeAddressLabel=employeeAddressLabel,
                                    responsibilities=responsibilities,
                                    responsibilitiesLabel=responsibilitiesLabel,
                                    post=post,
                                    postLabel=postLabel,


                                    nokName = nokName,
                                    nokNameLabel = nokNameLabel,
                                    nokPhone=nokPhone,
                                    nokPhoneLabel=nokphoneLabel,
                                    relationship=relationship,
                                    relationshipLabel=relationshipLabel,
                                    occupation=occupation,
                                    occupationLabel=occupationLabel,
                                    age=age,
                                    ageLabel=ageLabel,


                                    refName = refName,
                                    refName2=refName2,
                                    refNameLabel =refNameLabel,
                                    refPhone = refPhone,
                                    refPhone2=refPhone2,
                                    refPhoneLabel = refPhoneLabel,
                                    refMemberNo = refMemberNo,
                                    refMemberNo2 = refMemberNo2,
                                    refNoLabel = refNoLabel,

                                    adminFeeBal= adminFeeBal,
                                    adminFeeBalLabel= adminFeeBalLabel,

                                    pledgeBal=pledgeBal,
                                    pledgeBalLabel=pledgeBalLabel,

                                    loanBal=loanBal,
                                    loanBalLabel=loanBalLabel,

                                    sharesBal=sharesBal,
                                    sharesBalLabel=sharesBalLabel,

                                    targetBal=targetBal,
                                    targetBalLabel=targetBalLabel,




                                    )
                            }
                            BALANCE_INFOR_PROFILE_LIST ->{
                                AccountBalInfo(
                                    adminFeeBal = adminFeeBal,
                                    onAdminFeeBalChange = onAdminFeeBalChange,
                                    adminFeeBalLabel = adminFeeBalLabel,
                                    adminFeeBalPlaceholder = adminFeeBalPlaceholder,
                                    pledgeBal = pledgeBal,
                                    onPledgeBalChange = onPledgeBalChange,
                                    pledgeBalLabel = pledgeBalLabel,
                                    pledgeBalPlaceholder = pledgeBalPlaceholder,
                                    loanBal = loanBal,
                                    onLoanBalChange = onLoanBalChange,
                                    loanBalLabel = loanBalLabel,
                                    loanBalPlaceholder = loanBalPlaceholder,
                                    sharesBal = sharesBal,
                                    onSharesBalChange = onSharesBalChange,
                                    sharesBalLabel = sharesBalLabel,
                                    sharesBalPlaceholder = sharesBalPlaceholder,
                                    targetBal = targetBal,
                                    onTargetBalChange = onTargetBalChange,
                                    targetBalLabel = targetBalLabel,
                                    targetBalPlaceholder = targetBalPlaceholder
                                )
                            }
                        }
                    }

                }
            }

        }


        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .weight(1f),
        ) {

            if (memberAndRegState != REGISTER){
                when(finOrSec){
                    "sec"->{
                        Button(
                            modifier = Modifier.weight(5f),
                            onClick = { onRegisterClick() },
                            colors = ButtonDefaults.buttonColors(
                                contentColor = RoyalHealht,
                                containerColor = Color.White
                            ),
                            shape = RectangleShape
                        ) {
                            Text(text = "Register")
                        }
                    }
                    "fin"->{
                        Button(
                            modifier = Modifier.weight(5f),
                            onClick = { onAccountClick() },
                            colors = ButtonDefaults.buttonColors(
                                contentColor = RoyalHealht,
                                containerColor = Color.White
                            ),
                            shape = RectangleShape
                        ) {
                            Text(text = "Account")
                        }
                    }
                }


                Spacer(modifier = Modifier.padding(10.dp))
                Button(
                    modifier = Modifier.weight(5f),
                    onClick = { onMembersClick() },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = RoyalHealht
                    ),
                    shape = RectangleShape
                ) {
                    Text(text = "Member's")
                }
            }
            else{
                if (registationState != PREVIEW){
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
//                            .weight(1f)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Card(
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(
                                containerColor = RoyalHealht,
                                contentColor = Color.White)
                        ) {
                            Row {
                                IconButton(onClick = { onBackClick() },
                                    colors = IconButtonDefaults.iconButtonColors(
                                        containerColor = RoyalHealht
                                    )
                                ){
                                    Icon(
                                        imageVector =
                                        when(registationState){
                                            PERSONAL_INFOR_PROFILE_LIST -> {
                                                Icons.Default.ArrowCircleRight
                                            }else -> {
                                            Icons.Default.ArrowCircleLeft
                                        }
                                        },
                                        contentDescription ="ArrowCircleLeft" )
                                }
                                Spacer(modifier = Modifier.padding(5.dp))
                                IconButton(onClick = { onNextClick() },
                                    colors = IconButtonDefaults.iconButtonColors(
                                        containerColor = RoyalHealht
                                    )
                                ){
                                    Icon(
                                        imageVector =
                                        when(registationState){
                                            REFEREE_INFOR_PROFILE_LIST -> {
                                                Icons.Default.CheckCircle
                                            }else -> {
                                            Icons.Default.ArrowCircleRight
                                        }
                                        },
                                        contentDescription ="ArrowCircleRight" )
                                }
                            }

                        }


                    }
                }else {
                    Button(onClick = { onSubmitClick() }, colors = ButtonDefaults.buttonColors(
                        containerColor = RoyalHealht,
                        contentColor = Color.White
                    )) {
                        Text(text = "Submit")
                    }
                }
            }

        }
    }
}

@Composable
fun AccountBalInfo(
    adminFeeBal: String,
    onAdminFeeBalChange: (String)-> Unit,
    adminFeeBalLabel:String,
    adminFeeBalPlaceholder:String,

    pledgeBal: String,
    onPledgeBalChange: (String)-> Unit,
    pledgeBalLabel:String,
    pledgeBalPlaceholder:String,

    loanBal: String,
    onLoanBalChange: (String)-> Unit,
    loanBalLabel:String,
    loanBalPlaceholder:String,

    sharesBal: String,
    onSharesBalChange: (String)-> Unit,
    sharesBalLabel:String,
    sharesBalPlaceholder:String,

    targetBal: String,
    onTargetBalChange: (String)-> Unit,
    targetBalLabel:String,
    targetBalPlaceholder:String,

    ){


    Column {
        RegInputText(adminFeeBal, onAdminFeeBalChange, adminFeeBalLabel, adminFeeBalPlaceholder)
        RegInputText(pledgeBal, onPledgeBalChange, pledgeBalLabel, pledgeBalPlaceholder)
        RegInputText(targetBal, onTargetBalChange, targetBalLabel, targetBalPlaceholder)
        RegInputText(sharesBal, onSharesBalChange, sharesBalLabel, sharesBalPlaceholder)
        RegInputText(loanBal, onLoanBalChange, loanBalLabel, loanBalPlaceholder)
    }
}
@Composable
fun PreviewRegistrationDetails(
    onPersonalInfoClick: ()-> Unit,
    onContactInfoClick: ()-> Unit,
    onEmploymentInfoClick: ()-> Unit,
    onNextOfKinInfoClick: ()-> Unit,
    onRefereeInfoClick: ()-> Unit,

    name: String,
    label: String,
    refNo: String,
    refNoLabel: String,
    dateOfBirth: String,
    dateOfBirthLabel: String,
    sex: String,
    sexLabel: String,
    stateOfOrigin: String,
    stateOfOriginLabel: String,
    lga: String,
    lgaLabel: String,
    homeTown: String,
    homeTownLabel: String,
    maritalStatus: String,
    maritalStatusLabel: String,

    email: String,
    emailLabel: String,
    address: String,
    addressLabel: String,
    phone: String,
    phoneLabel: String,

    employeeName: String,
    employeeNameLabel: String,
    employeeAddress: String,
    employeeAddressLabel: String,
    responsibilities: String,
    responsibilitiesLabel: String,
    post: String,
    postLabel: String,

    nokName: String,
    nokNameLabel: String,
    nokPhone: String,
    nokPhoneLabel: String,
    relationship: String,
    relationshipLabel: String,
    occupation: String,
    occupationLabel: String,
    age: String,
    ageLabel: String,

    refName: String,
    refName2: String,
    refPhone: String,
    refPhone2: String,
    refMemberNo: String,
    refMemberNo2: String,
    refMemberNoLabel: String,
    refPhoneLabel: String,
    refNameLabel: String,


    adminFeeBal: String,
    adminFeeBalLabel:String,

    pledgeBal: String,
    pledgeBalLabel:String,

    loanBal: String,
    loanBalLabel:String,

    sharesBal: String,
    sharesBalLabel:String,

    targetBal: String,
    targetBalLabel:String,

    ){
    Column {
        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onPersonalInfoClick() },
                text ="Personal Information",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = RoyalHealht
            )
            RegReviewText(name = name, label = label)
            RegReviewText(name = refNo, label = refNoLabel)
            RegReviewText(name = dateOfBirth, label = dateOfBirthLabel)
            RegReviewText(name = sex, label = sexLabel)
            RegReviewText(name = stateOfOrigin, label = stateOfOriginLabel)
            RegReviewText(name = lga, label = lgaLabel)
            RegReviewText(name = homeTown, label = homeTownLabel)
            RegReviewText(name = maritalStatus, label = maritalStatusLabel)

            Divider(modifier = Modifier.fillMaxWidth(), thickness = 3.dp)
        }
        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onContactInfoClick() },
                text ="Contact Information",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = RoyalHealht
            )
            RegReviewText(email, emailLabel,)
            RegReviewText(address, addressLabel,)
            RegReviewText(phone, phoneLabel,)
            Divider(modifier = Modifier.fillMaxWidth(), thickness = 3.dp)
        }
        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onEmploymentInfoClick() },
                text ="Employment Information",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = RoyalHealht
            )
            RegReviewText(employeeName, employeeNameLabel,)
            RegReviewText(employeeAddress, employeeAddressLabel,)
            RegReviewText(responsibilities, responsibilitiesLabel,)
            RegReviewText(post, postLabel,)
            Divider(modifier = Modifier.fillMaxWidth(), thickness = 3.dp)
        }
        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNextOfKinInfoClick() },
                text ="Next of Kin Information",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = RoyalHealht
            )
            RegReviewText(nokName, nokNameLabel,)
            RegReviewText(nokPhone, nokPhoneLabel,)
            RegReviewText(relationship, relationshipLabel,)
            RegReviewText(occupation, occupationLabel,)
            RegReviewText(age, ageLabel,)
            Divider(modifier = Modifier.fillMaxWidth(), thickness = 3.dp)
        }
        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onRefereeInfoClick() },
                text ="Referee Information",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = RoyalHealht
            )
            RegReviewText(refName, refNameLabel)
            RegReviewText(refPhone, refPhoneLabel)
            RegReviewText(refMemberNo, refMemberNoLabel)
            Divider(modifier = Modifier.fillMaxWidth(), thickness = 3.dp)
            RegReviewText(refName2, refNameLabel)
            RegReviewText(refPhone2, refPhoneLabel)
            RegReviewText(refMemberNo2, refMemberNoLabel)
            Divider(modifier = Modifier.fillMaxWidth(), thickness = 3.dp)
        }
        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onRefereeInfoClick() },
                text ="Account Opening Information",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = RoyalHealht
            )
            RegReviewText(adminFeeBal, adminFeeBalLabel)
            RegReviewText(pledgeBal, pledgeBalLabel)
            RegReviewText(targetBal, targetBalLabel)
            RegReviewText(sharesBal, sharesBalLabel)
            RegReviewText(loanBal, loanBalLabel)
        }
//        Column(modifier = Modifier.fillMaxWidth()) {
//            Button(onClick = { onSubmitClick() }) {
//                Text(text = "Submit")
//            }
//        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(
    name: String,
    onNameChange:(String)->Unit,
    label: String,
    placeholder: String,
    refNo: String,
    onRefNoChange:(String)->Unit,
    refNoLabel: String,
    refNoPlaceholder: String,
    dateOfBirth: String,
    datePickerState: DatePickerState,
    dateOfBirthHide: Boolean,
    onEditDateClick:()->Unit,
    onDatePick:()->Unit,
    SelectedDateOfBirth: String,
    sex: String,
    sexHide: Boolean,
    onSexDropDownMenuClick:(String)->Unit,
    onSexEdit:()->Unit,
    radioOptions: List<String>,
    stateOfOrigin: String,
    onStateOfOriginChange:(String)->Unit,
    stateOfOriginLabel: String,
    stateOfOriginPlaceholder: String,
    lga: String,
    onLgaChange:(String)->Unit,
    lgaLabel: String,
    lgaPlaceholder: String,
    homeTown: String,
    onHometownChange:(String)->Unit,
    homeTownLabel: String,
    homeTownPlaceholder: String,
    maritalStatus: String,
    onMaritalStatusChange:(String)->Unit,
    maritalStatusLabel: String,
    maritalStatusPlaceholder: String,
){
    Column {
        RegInputText(name, onNameChange, label, placeholder)
        RegInputText(refNo, onRefNoChange, refNoLabel, refNoPlaceholder)
        // Date of Brith
        if (dateOfBirthHide) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                DatePicker(
                    state = datePickerState,
                    showModeToggle = false,
                    colors = DatePickerDefaults.colors(
                        selectedYearContainerColor = RoyalHealht,
                        selectedYearContentColor = Color.White,
                        selectedDayContainerColor = RoyalHealht,
                        selectedDayContentColor = Color.White,
                    )
                )
                Button(onClick = { onDatePick() }, colors = ButtonDefaults.buttonColors(
                    containerColor = RoyalHealht
                )
                ) {
                    Text(text = SelectedDateOfBirth)
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
                    Text(text = "Date Of Birth", fontSize = 12.sp, color = RoyalHealht)
                    Text(text = dateOfBirth, fontSize = 14.sp)

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
        // Sex
//        val (selectedSex, sexOptionList) = remember { mutableStateOf(radioOptions[0] ) }
        if (!sexHide){
            Column {
                Row(modifier = Modifier.padding(vertical = 20.dp)) {

                    Icon(
                        modifier = Modifier.weight(1f),
                        imageVector = Icons.Default.Person,
                        contentDescription = "Person"
                    )
                    Column(
                        modifier = Modifier.weight(7f),
                    ) {
                        Text(text = "Sex", fontSize = 12.sp, color = RoyalHealht)
                        Text(text = sex, fontSize = 14.sp)
                    }
                    Icon(
                        modifier = Modifier
                            .weight(2f)
                            .clickable { onSexEdit() },
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit"
                    )
                }
                Divider(modifier = Modifier.fillMaxWidth(), thickness = 3.dp)
            }

        }
        else{
            Column {
                radioOptions.forEach { text ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (text == sex),
                                onClick = {
                                    onSexDropDownMenuClick(text)
//                                    sexOptionList(text)
//                                    sexHide = false
                                }
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        RadioButton(
                            selected = (text == sex),
                            onClick = {
                                onSexDropDownMenuClick(text)
//                                sexOptionList(text)
//                                sexHide=false
                            }
                        )
                        Text(
                            text = text,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                }
                Divider(modifier = Modifier.fillMaxWidth(), thickness = 3.dp)

            }
        }
        RegInputText(stateOfOrigin, onStateOfOriginChange, stateOfOriginLabel, stateOfOriginPlaceholder)
        RegInputText(lga, onLgaChange, lgaLabel, lgaPlaceholder)
        RegInputText(homeTown, onHometownChange, homeTownLabel, homeTownPlaceholder)
        RegInputText(maritalStatus, onMaritalStatusChange, maritalStatusLabel, maritalStatusPlaceholder)
    }
}

@Composable
fun ContactInfo(
    email: String,
    onEmailChange:(String)->Unit,
    emailLabel: String,
    emailPlaceholder: String,
    address: String,
    onAddressChange:(String)->Unit,
    addressLabel: String,
    addressPlaceholder: String,
    phone: String,
    onPhoneChange:(String)->Unit,
    phoneLabel: String,
    phonePlaceholder: String,
){
    Column {
        RegInputText(email, onEmailChange, emailLabel, emailPlaceholder)
        RegInputText(address, onAddressChange, addressLabel, addressPlaceholder)
        RegInputText(phone, onPhoneChange, phoneLabel, phonePlaceholder)
    }

}


@Composable
fun EmployeeInfo(
    employeeName: String,
    onEmployeeNameChange:(String)->Unit,
    employeeNameLabel: String,
    employeeNamePlaceholder: String,

    employeeAddress: String,
    onEmployeeAddressChange:(String)->Unit,
    employeeAddressLabel: String,
    employeeAddressPlaceholder: String,

    responsibilities: String,
    onResponsibilitiesChange:(String)->Unit,
    responsibilitiesLabel: String,
    responsibilitiesPlaceholder: String,

    post: String,
    onPostChange:(String)->Unit,
    postLabel: String,
    postPlaceholder: String,

    ){
    Column {
        RegInputText(employeeName, onEmployeeNameChange, employeeNameLabel, employeeNamePlaceholder)
        RegInputText(employeeAddress, onEmployeeAddressChange, employeeAddressLabel, employeeAddressPlaceholder)
        RegInputText(responsibilities, onResponsibilitiesChange, responsibilitiesLabel, responsibilitiesPlaceholder)
        RegInputText(post, onPostChange, postLabel, postPlaceholder)

    }

}

@Composable
fun NextOfKinInfo(
    nokName: String,
    onNokNameChange:(String)->Unit,
    nokNameLabel: String,
    nokNamePlaceholder: String,

    nokPhone: String,
    onNokPhoneChange:(String)->Unit,
    phoneLabel: String,
    nokPhonePlaceholder: String,

    relationship: String,
    onRelationshipChange:(String)->Unit,
    relationshipLabel: String,
    relationshipPlaceholder: String,

    occupation: String,
    onOccupationChange:(String)->Unit,
    occupationLabel: String,
    occupationPlaceholder: String,

    age: String,
    onAgeChange:(String)->Unit,
    ageLabel: String,
    agePlaceholder: String,
){
    Column {
        RegInputText(nokName, onNokNameChange, nokNameLabel, nokNamePlaceholder)
        RegInputText(nokPhone, onNokPhoneChange, phoneLabel, nokPhonePlaceholder)
        RegInputText(relationship, onRelationshipChange, relationshipLabel, relationshipPlaceholder)
        RegInputText(occupation, onOccupationChange, occupationLabel, occupationPlaceholder)
        RegInputText(age, onAgeChange, ageLabel, agePlaceholder)

    }

}


@Composable
fun RefereeInfo(
    refName: String,
    onRefNameChange:(String)->Unit,
    refNameLabel: String,
    refNamePlaceholder: String,

    refPhone: String,
    onRefPhoneChange:(String)->Unit,
    refPhoneLabel: String,
    refPhonePlaceholder: String,

    refMemberNo: String,
    onRefMemberNoChange:(String)->Unit,
    refMemberNoLabel: String,
    refMemberNoPlaceholder: String,

    refName2: String,
    onRefNameChange2:(String)->Unit,
    refNameLabel2: String,
    refNamePlaceholder2: String,

    refPhone2: String,
    onRefPhoneChange2:(String)->Unit,
    refPhoneLabel2: String,
    refPhonePlaceholder2: String,

    refMemberNo2: String,
    onRefMemberNoChange2:(String)->Unit,
    refMemberNoLabel2: String,
    refMemberNoPlaceholder2: String,


    ){
    Column {
        RegInputText(refName, onRefNameChange, refNameLabel, refNamePlaceholder)
        RegInputText(refPhone, onRefPhoneChange, refPhoneLabel, refPhonePlaceholder)
        RegInputText(refMemberNo, onRefMemberNoChange, refMemberNoLabel, refMemberNoPlaceholder)
        Text(text = "Referee Two")
        RegInputText(refName2, onRefNameChange2, refNameLabel2, refNamePlaceholder2)
        RegInputText(refPhone2, onRefPhoneChange2, refPhoneLabel2, refPhonePlaceholder2)
        RegInputText(refMemberNo2, onRefMemberNoChange2, refMemberNoLabel2, refMemberNoPlaceholder2)

    }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RegInputText(
    name: String,
    onNameChange: (String) -> Unit,
    label: String,
    placeholder: String
) {
    TextField(
        modifier= Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        value = name,
        onValueChange = onNameChange,
        maxLines = 3,
        label = { Text(text = label, color = RoyalHealht) },
        placeholder = { Text(text = placeholder) },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
            containerColor = Color.Transparent
        ),
        shape = RectangleShape

    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RegReviewText(
    name: String,
    label: String
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier= Modifier
                .padding(horizontal = 10.dp),
//                .fillMaxWidth(),
            text = "$label :",
            fontWeight = FontWeight.Bold, fontSize = 12.sp,
            color = RoyalHealht
        )
        Text(
            modifier= Modifier
                .padding(horizontal = 10.dp),
            text = name, fontSize = 12.sp
        )
        Spacer(modifier = Modifier.padding(4.dp))

    }

}

@Composable
fun TopAppBar(
    title: String,
    onBackClick: ()-> Unit,
){
    Row(verticalAlignment = Alignment.CenterVertically, ) {
        IconButton(modifier = Modifier.weight(1f),
            onClick = {
                onBackClick()
            }
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "ArrowBack")
        }
        Text(modifier = Modifier.weight(9f), text = title, textAlign = TextAlign.Center)
    }
}
