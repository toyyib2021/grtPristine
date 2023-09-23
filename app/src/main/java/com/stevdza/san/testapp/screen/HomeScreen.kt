package com.stevdza.san.testapp.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {


    Scaffold(
        topBar = {

        },
        content = {
            var name by remember { mutableStateOf("") }
            var dateOfBirth by remember { mutableStateOf("") }
            var sex by remember { mutableStateOf("") }
            var statOfOrigin by remember { mutableStateOf("") }
            var lga by remember { mutableStateOf("") }
            var homeTown by remember { mutableStateOf("") }
            var maritalStatus by remember { mutableStateOf("") }
            var formOrList by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var address by remember { mutableStateOf("") }
            var phone by remember { mutableStateOf("") }
            var employeeName by remember { mutableStateOf("") }
            var employeeAddress by remember { mutableStateOf("") }
            var responsibilities by remember { mutableStateOf("") }
            var post by remember { mutableStateOf("") }
            var nokName by remember { mutableStateOf("") }
            var nokAddress by remember { mutableStateOf("") }
            var relationShip by remember { mutableStateOf("") }
            var occupation by remember { mutableStateOf("") }
            var age by remember { mutableStateOf("") }
            var nameOne by remember { mutableStateOf("") }
            var phoneOne by remember { mutableStateOf("") }
            var membershipNoOne by remember { mutableStateOf("") }
            var nameTwo by remember { mutableStateOf("") }
            var phoneTwo by remember { mutableStateOf("") }
            var membershipNoTwo by remember { mutableStateOf("") }
//            var screenState by remember { mutableStateOf("") }

            Content(
                name = name,
                dateOfBirth = dateOfBirth,
                sex = sex,
                statOfOrigin = statOfOrigin,
                lga = lga,
                homeTown = homeTown,
                maritalStatus = maritalStatus,
                onNameChange = {name = it},
                onDateOfBirthChange = {dateOfBirth = it},
                onSexChange = {sex = it},
                onLgaChange = {lga = it},
                onHomeTownChange = {homeTown = it},
                onMaritalStatusChange = {maritalStatus = it},
                onStateOfOriginChange = {statOfOrigin = it},
                formOrList = formOrList,
                email = email,
                address = address,
                phone = phone,
                onEmailChange = {email = it},
                onAddressChange = {address = it},
                onPhoneChange = {phone = it},
                companyName = employeeName,
                employeeAddress = employeeAddress,
                responsibilities = responsibilities,
                post =post,
                onCompanyNameChange = {employeeName=it},
                onEmployeeAddressChange = {employeeAddress=it},
                onResponsibilitiesChange = {responsibilities=it},
                onPostChange = {post=it},
                nokName = nokName,
                nokAddress = nokAddress,
                relationship = relationShip,
                occupation = occupation,
                age = age,
                onNokNameChange = {nokName=it},
                onNokAddressChange = {nokAddress=it},
                onRelationshipChange = {relationShip=it},
                onOccupationChange = {occupation =it},
                onAgeChange = {age=it},
                nameOne = nameOne,
                nameTwo = nameTwo,
                phoneOne = phoneOne,
                phoneTwo = phoneTwo,
                membershipNoOne = membershipNoOne,
                membershipNoTwo = membershipNoTwo,
                onNameOneChange = {nameOne=it},
                onNameTwoChange = {nameTwo=it},
                onPhoneOneChange = {phoneOne=it},
                onPhoneTwoChange = {phoneTwo=it},
                onMembershipNoOneChange = {membershipNoOne=it},
                onMembershipNoTwoChange = {membershipNoTwo=it},
                onRegisterClick = {formOrList = ""},
                onMemberClick = {formOrList = "List"}
            )
        }
    )
}


@Composable
fun TopBar(){


}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(
    name: String,
    dateOfBirth: String,
    sex: String,
    statOfOrigin: String,
    lga: String,
    homeTown: String,
    maritalStatus: String,
    onNameChange: (String) -> Unit,
    onDateOfBirthChange: (String) -> Unit,
    onSexChange: (String) -> Unit,
    onLgaChange: (String)-> Unit,
    onHomeTownChange: (String)-> Unit,
    onMaritalStatusChange: (String)-> Unit,
    onStateOfOriginChange: (String)-> Unit,
    formOrList: String,
    email: String,
    address: String,
    phone: String,
    onEmailChange: (String)-> Unit,
    onAddressChange: (String)-> Unit,
    onPhoneChange: (String)-> Unit,

    companyName: String,
    employeeAddress: String,
    responsibilities: String,
    post: String,
    onCompanyNameChange: (String)-> Unit,
    onEmployeeAddressChange: (String)-> Unit,
    onResponsibilitiesChange: (String)-> Unit,
    onPostChange: (String)-> Unit,

    nokName: String,
    nokAddress: String,
    relationship: String,
    occupation: String,
    age: String,
    onNokNameChange: (String)-> Unit,
    onNokAddressChange: (String)-> Unit,
    onRelationshipChange: (String)-> Unit,
    onOccupationChange: (String)-> Unit,
    onAgeChange: (String)-> Unit,

    nameOne: String,
    nameTwo: String,
    phoneOne: String,
    phoneTwo: String,
    membershipNoOne: String,
    membershipNoTwo: String,
    onNameOneChange: (String) -> Unit,
    onNameTwoChange: (String) -> Unit,
    onPhoneOneChange: (String)-> Unit,
    onPhoneTwoChange: (String)-> Unit,
    onMembershipNoOneChange: (String)-> Unit,
    onMembershipNoTwoChange: (String)-> Unit,

    onRegisterClick: ()-> Unit,
    onMemberClick: ()-> Unit
){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(modifier = Modifier.weight(9f)) {
            if (formOrList == "List"){
                DatePickerSample()
            }else{
                LazyColumn{
                    item {
                        PersonalInfo(
                            name = name,
                            dateOfBirth = dateOfBirth,
                            sex = sex,
                            statOfOrigin = statOfOrigin,
                            lga = lga,
                            homeTown = homeTown,
                            maritalStatus = maritalStatus,
                            onNameChange = onNameChange,
                            onDateOfBirthChange = onDateOfBirthChange,
                            onSexChange = onSexChange,
                            onLgaChange = onLgaChange,
                            onHomeTownChange = onHomeTownChange,
                            onMaritalStatusChange = onMaritalStatusChange,
                            onStateOfOriginChange = onStateOfOriginChange
                        )
                    }
                    item {
                        ContactInfo(
                            email = email,
                            address = address,
                            phone = phone,
                            onEmailChange = {onEmailChange(it)},
                            onAddressChange = {onAddressChange(it)},
                            onPhoneChange = {onPhoneChange(it)}
                        )
                    }
                    item {
                        EmploymentInfo(
                            companyName = companyName,
                            address = employeeAddress,
                            responsibilities = responsibilities,
                            post = post,
                            onCompanyNameChange = {onCompanyNameChange(it)},
                            onAddressChange = {onEmployeeAddressChange(it)},
                            onResponsibilitiesChange = {onResponsibilitiesChange(it)},
                            onPostChange = {onPostChange(it)}
                        )
                    }
                    item {
                        NextOfKinInfo(
                            name = nokName,
                            address = nokAddress,
                            relationship = relationship,
                            occupation = occupation,
                            age = age,
                            onNameChange = {onNokNameChange(it)},
                            onAddressChange = {onNokAddressChange(it)},
                            onRelationshipChange = {onRelationshipChange(it)},
                            onOccupationChange = {onOccupationChange(it)},
                            onAgeChange = {onAgeChange(it)}
                        )
                    }
                    item {
                        RefereeInfo(
                            nameOne = nameOne,
                            nameTwo = nameTwo,
                            phoneOne = phoneOne,
                            phoneTwo = phoneTwo,
                            membershipNoOne = membershipNoOne,
                            membershipNoTwo = membershipNoTwo,
                            onNameOneChange = {onNameChange(it)},
                            onNameTwoChange = {onNameTwoChange(it)},
                            onPhoneOneChange = {onPhoneOneChange(it)},
                            onPhoneTwoChange = {onPhoneTwoChange(it)},
                            onMembershipNoOneChange = {onMembershipNoOneChange(it)},
                            onMembershipNoTwoChange = {onMembershipNoTwoChange(it)}
                        )
                    }
                }
            }
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .weight(1f)
        ) {
            Card(modifier = Modifier
                .fillMaxSize()
                .weight(5f),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                )
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .clickable { onRegisterClick() }
                    .padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(text = "Register", style = MaterialTheme.typography.bodyMedium)

                }
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Card(modifier = Modifier
                .fillMaxSize()
                .weight(5f),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                )
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .clickable { onMemberClick() }
                    .padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(text = "Member's", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerSample() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        val calendar = Calendar.getInstance()
        calendar.set(1990, 0, 22) // add year, month (Jan), date
        // Pre-select a date for January 4, 2020
        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = calendar.timeInMillis)
        DatePicker(state = datePickerState, modifier = Modifier.padding(16.dp))

        val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.ROOT)

        val dateSelected = remember { mutableStateOf(formatter.format(Date(datePickerState.selectedDateMillis!!))) }
        Text("Selected date timestamp: $dateSelected")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalInfo(
    name: String,
    dateOfBirth: String,
    sex: String,
    statOfOrigin: String,
    lga: String,
    homeTown: String,
    maritalStatus: String,
    onNameChange: (String) -> Unit,
    onDateOfBirthChange: (String) -> Unit,
    onSexChange: (String) -> Unit,
    onLgaChange: (String)-> Unit,
    onHomeTownChange: (String)-> Unit,
    onMaritalStatusChange: (String)-> Unit,
    onStateOfOriginChange: (String)-> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Personal Information", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 30.dp),
                    value = name,
                    onValueChange = { onNameChange(it) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                    ),
                    shape = RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    label = { Text(text = "Full Name") },
                    placeholder = { Text(text = "Enter Full Name here Surname First") },
                    leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Person") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 30.dp),
                    value = dateOfBirth,
                    onValueChange = { onDateOfBirthChange(it) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                    ),
                    shape = RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    label = { Text(text = "Data of Birth") },
                    placeholder = { Text(text = "dd/mm/yyyy") },
                    leadingIcon = { Icon(imageVector = Icons.Default.DateRange, contentDescription = "DateRange") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 30.dp),
                    value = sex,
                    onValueChange = { onSexChange(it) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                    ),
                    shape = RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    label = { Text(text = "Sex") },
                    placeholder = { Text(text = "sex") },
//                    leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Person") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 30.dp),
                    value = statOfOrigin,
                    onValueChange = { onStateOfOriginChange(it) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                    ),
                    shape = RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    label = { Text(text = "State of Origin") },
                    placeholder = { Text(text = "Enter your State of Origin here") },
//                    leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Person") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 30.dp),
                    value = lga,
                    onValueChange = { onLgaChange(it) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                    ),
                    shape = RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    label = { Text(text = "Local Govt. Area") },
                    placeholder = { Text(text = "Enter your LGA here") },
//                    leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Person") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 30.dp),
                    value = homeTown,
                    onValueChange = { onHomeTownChange(it) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                    ),
                    shape = RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    label = { Text(text = "Home Town") },
                    placeholder = { Text(text = "Enter your Home Town here") },
//                    leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Person") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 30.dp),
                    value = maritalStatus,
                    onValueChange = { onMaritalStatusChange(it) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                    ),
                    shape = RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    label = { Text(text = "Marital Status") },
                    placeholder = { Text(text = "Enter your Marital Status here") },
//                    leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Person") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                Spacer(modifier = Modifier.padding(5.dp))
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactInfo(
    email: String,
    address: String,
    phone: String,
    onEmailChange: (String)-> Unit,
    onAddressChange: (String)-> Unit,
    onPhoneChange: (String)-> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Contact Information", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 30.dp),
                    value = email,
                    onValueChange = { onEmailChange(it) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                    ),
                    shape = RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    label = { Text(text = "Email") },
                    placeholder = { Text(text = "Enter email here") },
                    leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Person") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )

                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 30.dp),
                    value = address,
                    onValueChange = { onAddressChange(it) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                    ),
                    shape = RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    label = { Text(text = "Address") },
                    placeholder = { Text(text = "Enter your Address here") },
                    leadingIcon = { Icon(imageVector = Icons.Default.LocationOn, contentDescription = "LocationOn") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                Spacer(modifier = Modifier.padding(5.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 30.dp),
                    value = phone,
                    onValueChange = { onPhoneChange(it) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                    ),
                    shape = RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    label = { Text(text = "Phone Number") },
                    placeholder = { Text(text = "Enter your Phone Number Here") },
                    leadingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                Spacer(modifier = Modifier.padding(5.dp))
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmploymentInfo(
    companyName: String,
    address: String,
    responsibilities: String,
    post: String,
    onCompanyNameChange: (String)-> Unit,
    onAddressChange: (String)-> Unit,
    onResponsibilitiesChange: (String)-> Unit,
    onPostChange: (String)-> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Employment Information", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 30.dp),
                    value = companyName,
                    onValueChange = { onCompanyNameChange(it) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                    ),
                    shape = RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    label = { Text(text = "Company Name") },
                    placeholder = { Text(text = "Enter Company Name here") },
                    leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Person") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )

                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 30.dp),
                    value = address,
                    onValueChange = { onAddressChange(it) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                    ),
                    shape = RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    label = { Text(text = "Address") },
                    placeholder = { Text(text = "Enter your Address here") },
                    leadingIcon = { Icon(imageVector = Icons.Default.LocationOn, contentDescription = "LocationOn") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                Spacer(modifier = Modifier.padding(5.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 30.dp),
                    value = responsibilities,
                    onValueChange = { onResponsibilitiesChange(it) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                    ),
                    shape = RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    label = { Text(text = "Responsibilities") },
                    placeholder = { Text(text = "Enter your Responsibility Here") },
                    leadingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 30.dp),
                    value = post,
                    onValueChange = { onPostChange(it) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                    ),
                    shape = RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    label = { Text(text = "Post") },
                    placeholder = { Text(text = "Enter your Post Here") },
                    leadingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                Spacer(modifier = Modifier.padding(5.dp))
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NextOfKinInfo(
    name: String,
    address: String,
    relationship: String,
    occupation: String,
    age: String,
    onNameChange: (String)-> Unit,
    onAddressChange: (String)-> Unit,
    onRelationshipChange: (String)-> Unit,
    onOccupationChange: (String)-> Unit,
    onAgeChange: (String)-> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Next Of Kin", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 30.dp),
                    value = name,
                    onValueChange = { onNameChange(it) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                    ),
                    shape = RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    label = { Text(text = "Full Name") },
                    placeholder = { Text(text = "Enter Full Name here") },
//                    leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Person") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )

                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 30.dp),
                    value = address,
                    onValueChange = { onAddressChange(it) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                    ),
                    shape = RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    label = { Text(text = "Address") },
                    placeholder = { Text(text = "Enter your Address here") },
//                    leadingIcon = { Icon(imageVector = Icons.Default.LocationOn, contentDescription = "LocationOn") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                Spacer(modifier = Modifier.padding(5.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 30.dp),
                    value = relationship,
                    onValueChange = { onRelationshipChange(it) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                    ),
                    shape = RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    label = { Text(text = "Relationship") },
                    placeholder = { Text(text = "Enter your Relationship with kin Here") },
//                    leadingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 30.dp),
                    value = occupation,
                    onValueChange = { onOccupationChange(it) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                    ),
                    shape = RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    label = { Text(text = "Occupation") },
                    placeholder = { Text(text = "Enter kin occupation Here") },
//                    leadingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 30.dp),
                    value = age,
                    onValueChange = { onAgeChange(it) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                    ),
                    shape = RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    label = { Text(text = "Age") },
                    placeholder = { Text(text = "Enter your kin age here") },
//                    leadingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RefereeInfo(
    nameOne: String,
    nameTwo: String,
    phoneOne: String,
    phoneTwo: String,
    membershipNoOne: String,
    membershipNoTwo: String,
    onNameOneChange: (String) -> Unit,
    onNameTwoChange: (String) -> Unit,
    onPhoneOneChange: (String)-> Unit,
    onPhoneTwoChange: (String)-> Unit,
    onMembershipNoOneChange: (String)-> Unit,
    onMembershipNoTwoChange: (String)-> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Column {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "First Referee", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.padding(5.dp))
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(horizontal = 30.dp),
                        value = nameOne,
                        onValueChange = { onNameOneChange(it) },
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                        ),
                        shape = RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp),
                        singleLine = true,
                        textStyle = MaterialTheme.typography.bodyMedium,
                        label = { Text(text = "Full Name") },
                        placeholder = { Text(text = "Enter Full Name here") },
//                    leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Person") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )

                    Spacer(modifier = Modifier.padding(5.dp))
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(horizontal = 30.dp),
                        value = phoneOne,
                        onValueChange = { onPhoneOneChange(it) },
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                        ),
                        shape = RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp),
                        singleLine = true,
                        textStyle = MaterialTheme.typography.bodyMedium,
                        label = { Text(text = "Phone") },
                        placeholder = { Text(text = "Enter your Phone here") },
//                    leadingIcon = { Icon(imageVector = Icons.Default.LocationOn, contentDescription = "LocationOn") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )
                    Spacer(modifier = Modifier.padding(5.dp))

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(horizontal = 30.dp),
                        value = membershipNoOne,
                        onValueChange = { onMembershipNoOneChange(it) },
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                        ),
                        shape = RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp),
                        singleLine = true,
                        textStyle = MaterialTheme.typography.bodyMedium,
                        label = { Text(text = "Membership") },
                        placeholder = { Text(text = "Enter your Membership Here") },
//                    leadingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Second Referee", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.padding(5.dp))
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(horizontal = 30.dp),
                        value = nameTwo,
                        onValueChange = { onNameTwoChange(it) },
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                        ),
                        shape = RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp),
                        singleLine = true,
                        textStyle = MaterialTheme.typography.bodyMedium,
                        label = { Text(text = "Full Name") },
                        placeholder = { Text(text = "Enter Full Name here") },
//                    leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Person") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )

                    Spacer(modifier = Modifier.padding(5.dp))
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(horizontal = 30.dp),
                        value = phoneTwo,
                        onValueChange = { onPhoneTwoChange(it) },
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                        ),
                        shape = RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp),
                        singleLine = true,
                        textStyle = MaterialTheme.typography.bodyMedium,
                        label = { Text(text = "Phone") },
                        placeholder = { Text(text = "Enter your Phone here") },
//                    leadingIcon = { Icon(imageVector = Icons.Default.LocationOn, contentDescription = "LocationOn") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )
                    Spacer(modifier = Modifier.padding(5.dp))

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(horizontal = 30.dp),
                        value = membershipNoTwo,
                        onValueChange = { onMembershipNoTwoChange(it) },
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                        ),
                        shape = RoundedCornerShape(topEnd = 10.dp, bottomStart = 10.dp),
                        singleLine = true,
                        textStyle = MaterialTheme.typography.bodyMedium,
                        label = { Text(text = "Membership") },
                        placeholder = { Text(text = "Enter your Membership Here") },
//                    leadingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                }
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberList(
    nameQuery: String,
    onNameQueryChange: (String) -> Unit,
    active: Boolean,

){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        SearchBar(
            query = nameQuery,
            onQueryChange = {onNameQueryChange(it)},
            onSearch = {},
            active = active,
            onActiveChange ={},
            shape = RoundedCornerShape(5.dp),
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search") },
            trailingIcon = {Icon(imageVector = Icons.Default.Close, contentDescription = "Close")}
        ) {

        }
    }
}