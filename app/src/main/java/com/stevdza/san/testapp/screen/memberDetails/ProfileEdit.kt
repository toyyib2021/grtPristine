package com.stevdza.san.testapp.screen.memberDetails

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stevdza.san.testapp.screen.HomeViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalInfo(
    modifier: Modifier=Modifier,
    onPersonalInfoUpdate:()->Unit
){
    val homeVM: HomeViewModel = viewModel()

    Column(modifier.fillMaxWidth()) {
        var memberNoHide by remember { mutableStateOf(false) }
        var memberNo by remember { mutableStateOf("") }
        var stateOfOriginHide by remember { mutableStateOf(false) }
        var stateOfOrigin by remember { mutableStateOf("") }
        var lgaHide by remember { mutableStateOf(false) }
        var lga by remember { mutableStateOf("") }
        var homeTownHide by remember { mutableStateOf(false) }
        var homeTown by remember { mutableStateOf("") }
        var maritalStatusHide by remember { mutableStateOf(false) }
        var maritalStatus by remember { mutableStateOf("") }

        var dateOfBirthHide by remember { mutableStateOf(false) }
        val calendar = Calendar.getInstance()
        calendar.set(1996, 6, 14) // add year, month (Jan), date
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = calendar.timeInMillis,
            initialDisplayMode = DisplayMode.Picker

        )
        val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.ROOT)
        val dateSelected = formatter.format(Date(datePickerState.selectedDateMillis!!))


        var sexHide by remember { mutableStateOf(false) }

        Column(modifier = Modifier.weight(9f)) {

            LazyColumn{
                item {
                    InformationItem(
                        hide = memberNoHide,
                        value = homeVM.formNo.value,
                        textValue = homeVM.formNo.value,
                        onEditClick = { memberNoHide=true },
                        onValueChange = {homeVM.formNo.value=it },
                        onDOneClick = {memberNoHide=false},
                        icon = Icons.Default.Person,
                        title = "MemberShip No.",
                    )
                }
                item {
                    // Date of Birth
                    Row(modifier = Modifier.padding(vertical = 20.dp)) {

                        if (dateOfBirthHide){
                            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                DatePicker(
                                    state = datePickerState,
//                                    modifier = Modifier.padding(16.dp),
                                    showModeToggle = false
                                )
                                Button(onClick = {
                                    homeVM.dateOfBirth.value = dateSelected
                                    dateOfBirthHide=false }) {
                                    Text(text = dateSelected)
                                }
                            }
                        }
                            Icon(
                                modifier = Modifier.weight(1f),
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "DateRange"
                            )

                            Column(
                                modifier = Modifier.weight(7f),
                            ) {
                                Text(text = "Date Of Birth", fontSize = 12.sp)
                                Text(text = homeVM.dateOfBirth.value, fontSize = 14.sp)
                            }

                            Icon(
                                modifier = Modifier
                                    .weight(2f)
                                    .clickable { dateOfBirthHide = true },
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit"
                            )

                    }
                    Divider(modifier = Modifier.fillMaxWidth(), thickness = 3.dp)

                }
                item {
                    // Sex
                    val radioOptions = listOf("Male", "Female")
                    val (selectedSex, sexOptionList) = remember { mutableStateOf(radioOptions[0] ) }
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
                                    Text(text = "Sex", fontSize = 12.sp)
                                    Text(text = homeVM.sex.value, fontSize = 14.sp)
                                }
                                Icon(
                                    modifier = Modifier
                                        .weight(2f)
                                        .clickable { sexHide = true },
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit"
                                )
                            }
                            Divider(modifier = Modifier.fillMaxWidth(), thickness = 3.dp)
                        }

                    }else{
                        Column {
                            radioOptions.forEach { text ->
                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .selectable(
                                            selected = (text == selectedSex),
                                            onClick = {
                                                sexOptionList(text)
                                                homeVM.sex.value = text
                                                sexHide = false
                                            }
                                        )
                                        .padding(horizontal = 16.dp),
                                    verticalAlignment = Alignment.CenterVertically

                                ) {
                                    RadioButton(
                                        selected = (text == selectedSex),
                                        onClick = {
                                            sexOptionList(text)
                                            homeVM.sex.value = text
                                            sexHide=false
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

                }
                item {
                    InformationItem(
                        hide = stateOfOriginHide,
                        value = homeVM.stateOfOrigin.value,
                        textValue =homeVM.stateOfOrigin.value,
                        onEditClick = { stateOfOriginHide=true },
                        onValueChange = {
                            homeVM.stateOfOrigin.value=it
                            stateOfOrigin=it },
                        onDOneClick = {stateOfOriginHide=false},
                        icon = Icons.Default.LocationOn,
                        title = "State Of Origin"
                    )
                }
                item {
                    InformationItem(
                        hide = lgaHide,
                        value = homeVM.lga.value,
                        textValue= homeVM.lga.value,
                        onEditClick = { lgaHide=true },
                        onValueChange = {
                            homeVM.lga.value=it
                            lga=it },
                        onDOneClick = {lgaHide=false},
                        icon = Icons.Default.Person,
                        title = "LGA"
                    )
                }
                item {
                    InformationItem(
                        hide = homeTownHide,
                        value = homeVM.hometown.value,
                        textValue= homeVM.hometown.value,
                        onEditClick = { homeTownHide=true},
                        onValueChange = {
                            homeVM.hometown.value=it
                            homeTown=it },
                        onDOneClick = {homeTownHide=false},
                        icon = Icons.Default.CheckCircle,
                        title = "Home Town"
                    )
                }
                item {
                    InformationItem(
                        hide = maritalStatusHide,
                        value = homeVM.maritalStatus.value,
                        textValue=homeVM.maritalStatus.value,
                        onEditClick = { maritalStatusHide=true },
                        onValueChange = {
                            homeVM.maritalStatus.value=it
                            maritalStatus=it },
                        onDOneClick = {maritalStatusHide=false},
                        icon = Icons.Default.Home,
                        title = "Marital Status"
                    )
                }
            }
        }


        if (dateOfBirthHide){
            Log.e(TAG, "dateOfBirthHide: $dateOfBirthHide", )
        }else if (memberNoHide){
            Log.e(TAG, "PersonalInfo: $memberNoHide", )
        }else if (sexHide){
            Log.e(TAG, "sexHide: $sexHide", )
        }else if (stateOfOriginHide){
            Log.e(TAG, "stateOfOrigin: $stateOfOrigin", )
        }else if (lgaHide){
            Log.e(TAG, "lgaHide: $lgaHide", )
        }else if (homeTownHide){
            Log.e(TAG, "homeTownHide: $homeTownHide", )
        }else if (maritalStatusHide){
            Log.e(TAG, "maritalStatusHide: $maritalStatusHide", )
        }
        else{
            Row(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(end = 20.dp),
                horizontalArrangement = Arrangement.End) {
                Button(
                    onClick = { onPersonalInfoUpdate() }) {
                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = "Save")
                }
            }
        }

    }
}



@Composable
fun ContactInfo(
    modifier: Modifier=Modifier,
    onContactInfoUpdate:()->Unit
){
    val homeVM: HomeViewModel = viewModel()

    var emailHide by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }

    var addressHide by remember { mutableStateOf(false) }
    var address by remember { mutableStateOf("") }

    var phoneHide by remember { mutableStateOf(false) }
    var phone by remember { mutableStateOf("") }

    Column(modifier.fillMaxWidth()) {
        Column(modifier = Modifier.weight(9f)) {
            LazyColumn{
                item{
                    InformationItem(
                        hide = emailHide,
                        value = homeVM.email.value,
                        textValue=homeVM.email.value,
                        onEditClick = { emailHide=true },
                        onValueChange = {homeVM.email.value=it },
                        onDOneClick = {emailHide=false},
                        icon = Icons.Default.Email,
                        title = "Email",
                    )
                }
                item {
                    InformationItem(
                        hide = phoneHide,
                        value = homeVM.phone.value,
                        textValue= homeVM.phone.value,
                        onEditClick = { phoneHide=true },
                        onValueChange = { homeVM.phone.value=it },
                        onDOneClick = {phoneHide=false},
                        icon = Icons.Default.Phone,
                        title = "Phone",
                    )
                }
                item {
                    InformationItem(
                        hide = addressHide,
                        value = homeVM.address.value,
                        textValue=homeVM.address.value,
                        onEditClick = { addressHide=true },
                        onValueChange = { homeVM.address.value =it },
                        onDOneClick = {addressHide=false},
                        icon = Icons.Default.LocationOn,
                        title = "Address",
                    )
                }
            }
        }

        if (emailHide){
            Log.e(TAG, "emailHide: $emailHide", )
        }else if (addressHide){
            Log.e(TAG, "addressHide: $addressHide", )
        }else if (phoneHide) {
            Log.e(TAG, "phoneHide: $phoneHide",)
        }
        else{
            Row(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(end = 20.dp),
                horizontalArrangement = Arrangement.End) {
                Button(
                    onClick = { onContactInfoUpdate() }) {
                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = "Save")
                }
            }
        }

    }
}



@Composable
fun EmployeeInfo(
    modifier: Modifier=Modifier,
    onEmployeeInfoUpdate:()->Unit
){
    val homeVM: HomeViewModel = viewModel()

    var employeNameHide by remember { mutableStateOf(false) }
    var employeName by remember { mutableStateOf("") }

    var employeAddressHide by remember { mutableStateOf(false) }
    var employeAddress by remember { mutableStateOf("") }

    var responsibilitiesHide by remember { mutableStateOf(false) }
    var responsibilities by remember { mutableStateOf("") }

    var postHide by remember { mutableStateOf(false) }
    var post by remember { mutableStateOf("") }



    Column(modifier.fillMaxWidth()) {
        Column(modifier = Modifier.weight(9f)) {
            LazyColumn{
                item{
                    InformationItem(
                        hide = employeNameHide,
                        value = homeVM.employeeName.value,
                        textValue=homeVM.employeeName.value,
                        onEditClick = { employeNameHide=true },
                        onValueChange = { homeVM.employeeName.value=it },
                        onDOneClick = {employeNameHide=false},
                        icon = Icons.Default.Person,
                        title = "Employee Name",
                    )
                }
                item {
                    InformationItem(
                        hide = employeAddressHide,
                        value = homeVM.address.value,
                        textValue=homeVM.address.value,
                        onEditClick = {employeAddressHide=true},
                        onValueChange = { homeVM.address.value=it },
                        onDOneClick = {employeAddressHide=false},
                        icon = Icons.Default.LocationOn,
                        title = "Address",
                    )
                }
                item {
                    InformationItem(
                        hide = responsibilitiesHide,
                        value = homeVM.responsibilities.value,
                        textValue=homeVM.responsibilities.value,
                        onEditClick = { responsibilitiesHide=true },
                        onValueChange = { homeVM.responsibilities.value=it },
                        onDOneClick = {responsibilitiesHide=false},
                        icon = Icons.Default.LocationOn,
                        title = "Responsibility",
                    )
                }
                item {
                    InformationItem(
                        hide = postHide,
                        value = homeVM.post.value,
                        textValue=homeVM.post.value,
                        onEditClick = { postHide=true },
                        onValueChange = { homeVM.post.value=it },
                        onDOneClick = {postHide=false},
                        icon = Icons.Default.Place,
                        title = "Post",
                    )
                }
            }
        }

        if (employeNameHide){
            Log.e(TAG, "PersonalInfo: $employeNameHide", )
        }else if (employeAddressHide){
            Log.e(TAG, "PersonalInfo: $employeAddressHide", )
        }else if (responsibilitiesHide) {
            Log.e(TAG, "PersonalInfo: $responsibilitiesHide",)
        }else if (postHide) {
            Log.e(TAG, "PersonalInfo: $postHide",)
        }
        else{
            Row(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(end = 20.dp),
                horizontalArrangement = Arrangement.End) {
                Button(
                    onClick = { onEmployeeInfoUpdate() }) {
                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = "Save")
                }
            }
        }

    }
}


@Composable
fun NextOfKinInfo(
    modifier: Modifier=Modifier,
    onEmployeeInfoUpdate:()->Unit
){
    val homeVM: HomeViewModel = viewModel()

    var nokNameHide by remember { mutableStateOf(false) }
    var nokPhoneHide by remember { mutableStateOf(false) }
    var nokRelationshipHide by remember { mutableStateOf(false) }
    var nokOccupationHide by remember { mutableStateOf(false) }
    var nokAgeHide by remember { mutableStateOf(false) }

    var nokName by remember { mutableStateOf("Al Nur Ya Rabbi Ya Salam") }
    var nokPhone by remember { mutableStateOf("09030987789") }
    var nokRelationship by remember { mutableStateOf("Brother") }
    var nokOccupation by remember { mutableStateOf("Trader") }
    var nokAge by remember { mutableStateOf("26") }

    Column(modifier.fillMaxWidth()) {
        Column(modifier = Modifier.weight(9f)) {
            LazyColumn{
                item{
                    InformationItem(
                        hide = nokNameHide,
                        value = homeVM.nokName.value,
                        textValue=homeVM.nokName.value,
                        onEditClick = { nokNameHide=true },
                        onValueChange = { homeVM.nokName.value=it },
                        onDOneClick = {nokNameHide=false},
                        icon = Icons.Default.Person,
                        title = "Next of Kin Name",
                    )
                }
                item {
                    InformationItem(
                        hide = nokPhoneHide,
                        value = homeVM.nokPhone.value,
                        textValue=homeVM.nokPhone.value,
                        onEditClick = { nokPhoneHide=true },
                        onValueChange = { homeVM.nokPhone.value=it },
                        onDOneClick = {nokPhoneHide=false},
                        icon = Icons.Default.Phone,
                        title = "Next of Kin Phone",
                    )
                }
                item {
                    InformationItem(
                        hide = nokRelationshipHide,
                        value = homeVM.nokRelationship.value,
                        textValue=homeVM.nokRelationship.value,
                        onEditClick = { nokRelationshipHide=true },
                        onValueChange = { homeVM.nokRelationship.value=it },
                        onDOneClick = {nokRelationshipHide=false},
                        icon = Icons.Default.PlayArrow,
                        title = "RelationShip With Next of Kin",
                    )
                }
                item {
                    InformationItem(
                        hide = nokOccupationHide,
                        value = homeVM.occupation.value,
                        textValue=homeVM.occupation.value,
                        onEditClick = { nokOccupationHide=true },
                        onValueChange = { homeVM.occupation.value=it },
                        onDOneClick = {nokOccupationHide=false},
                        icon = Icons.Default.Info,
                        title = "Next of Kin Occupation",
                    )
                }
                item {
                    InformationItem(
                        hide = nokAgeHide,
                        value = homeVM.age.value,
                        textValue=homeVM.age.value,
                        onEditClick = { nokAgeHide=true },
                        onValueChange = { homeVM.age.value=it },
                        onDOneClick = {nokAgeHide=false},
                        icon = Icons.Default.AddCircle,
                        title = "Next of Kin Age",
                    )
                }
            }
        }

        if (nokNameHide){
            Log.e(TAG, "PersonalInfo: $nokNameHide", )
        }else if (nokPhoneHide){
            Log.e(TAG, "PersonalInfo: $nokPhoneHide", )
        }else if (nokRelationshipHide) {
            Log.e(TAG, "PersonalInfo: $nokRelationshipHide",)
        }else if (nokOccupationHide) {
            Log.e(TAG, "PersonalInfo: $nokOccupationHide",)
        }else if (nokAgeHide) {
            Log.e(TAG, "PersonalInfo: $nokAgeHide",)
        }
        else{
            Row(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(end = 20.dp),
                horizontalArrangement = Arrangement.End) {
                Button(
                    onClick = { onEmployeeInfoUpdate() }) {
                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = "Save")
                }
            }
        }

    }
}



@Composable
fun RefereeInfo(
    modifier: Modifier=Modifier,
    onRefereeInfoUpdate:()->Unit
){
    val homeVM: HomeViewModel = viewModel()

    var refereePhoneHide by remember { mutableStateOf(false) }
    var referedByHide by remember { mutableStateOf(false) }
    var refereePhone2Hide by remember { mutableStateOf(false) }
    var referedBy2Hide by remember { mutableStateOf(false) }

    var refereePhone by remember { mutableStateOf("09030987789") }
    var referedBy by remember { mutableStateOf("MPM/PS/00098/00234") }
    var refereePhone2 by remember { mutableStateOf("09030987789") }
    var referedBy2 by remember { mutableStateOf("MPM/PS/00098/00234") }


    Column(modifier.fillMaxWidth()) {
        Column(modifier = Modifier.weight(9f)) {
            LazyColumn{

                item{
                    Text(modifier = Modifier.padding(start = 10.dp), text = "Referee One", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.padding(10.dp))
                    InformationItem(
                        hide = referedByHide,
                        value = homeVM.refMembershipNo.value,
                        textValue=homeVM.refMembershipNo.value,
                        onEditClick = { referedByHide=true },
                        onValueChange = { homeVM.refMembershipNo.value=it },
                        onDOneClick = {referedByHide=false},
                        icon = Icons.Default.Person,
                        title = "MemberShip No.",
                    )
                }
                item {
                    InformationItem(
                        hide = refereePhoneHide,
                        value = homeVM.refPhone.value,
                        textValue=homeVM.refPhone.value,
                        onEditClick = { refereePhoneHide=true },
                        onValueChange = { homeVM.refPhone.value=it },
                        onDOneClick = {refereePhoneHide=false},
                        icon = Icons.Default.Phone,
                        title = "Phone No.",
                    )
                }
                item {
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(text = "Referee Two", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.padding(10.dp))
                    InformationItem(
                        hide = referedBy2Hide,
                        value = homeVM.refMembershipNo2.value,
                        textValue=homeVM.refMembershipNo2.value,
                        onEditClick = { referedBy2Hide=true },
                        onValueChange = { homeVM.refMembershipNo2.value=it },
                        onDOneClick = {referedBy2Hide=false},
                        icon = Icons.Default.PlayArrow,
                        title = "Membership No.",
                    )
                }
                item {
                    InformationItem(
                        hide = refereePhone2Hide,
                        value = homeVM.refPhone2.value,
                        textValue=homeVM.refPhone2.value,
                        onEditClick = { refereePhone2Hide=true },
                        onValueChange = { homeVM.refPhone2.value=it },
                        onDOneClick = {refereePhone2Hide=false},
                        icon = Icons.Default.Info,
                        title = "Phone No.",
                    )
                }
            }
        }

        if (referedByHide){
            Log.e(TAG, "PersonalInfo: $referedByHide", )
        }else if (referedBy2Hide){
            Log.e(TAG, "PersonalInfo: $referedBy2Hide", )
        }else if (refereePhone2Hide) {
            Log.e(TAG, "PersonalInfo: $refereePhone2Hide",)
        }else if (refereePhoneHide) {
            Log.e(TAG, "PersonalInfo: $refereePhoneHide",)
        }
        else{
            Row(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(end = 20.dp),
                horizontalArrangement = Arrangement.End) {
                Button(
                    onClick = { onRefereeInfoUpdate() }) {
                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = "Save")
                }
            }
        }

    }
}




@Composable
fun InformationItem(
    hide: Boolean,
    value: String,
    textValue: String,
    onEditClick:()-> Unit,
    onValueChange:(String)-> Unit,
    onDOneClick: ()-> Unit,
    icon: ImageVector,
    title: String,
) {

    Column {
        Row(modifier = Modifier.padding(vertical = 20.dp)) {
            if (!hide) {
                Icon(
                    modifier = Modifier.weight(1f),
                    imageVector = icon,
                    contentDescription = "Email"
                )

                Column(
                    modifier = Modifier.weight(7f),
                ) {
                    Text(text = title, fontSize = 12.sp)
                    Text(text = textValue, fontSize = 14.sp)
                }

                Icon(
                    modifier = Modifier
                        .weight(2f)
                        .clickable { onEditClick() },
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit"
                )
            } else {
                Row {
                    TextField(
                        modifier = Modifier
                            .weight(8f),
                        value = value,
                        onValueChange = { onValueChange(it) },
                        maxLines = 1,
                        label = { Text(text = title)}
                    )
                    Icon(
                        modifier = Modifier
                            .weight(2f)
                            .clickable { onDOneClick() },
                        imageVector = Icons.Default.Done,
                        contentDescription = "Edit"
                    )
                }
            }
        }
        Divider(modifier = Modifier.fillMaxWidth(), thickness = 3.dp)

    }

}