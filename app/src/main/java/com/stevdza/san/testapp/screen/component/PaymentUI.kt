package com.stevdza.san.testapp.screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stevdza.san.testapp.dataMain.GrtPayment

@Composable
fun PaymentUINotEditable(
    modifier: Modifier = Modifier,
    payment: GrtPayment,
    memberId:String,
){
    Column(
        modifier
            .fillMaxWidth()
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
                Column(modifier = Modifier
                    .weight(6f)) {
                    Row(modifier = Modifier
                        .fillMaxWidth()) {
                        Text(modifier = Modifier
                            .weight(5f), text = payment.purposeOfPayment, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Text(modifier = Modifier
                            .weight(5f),text = payment.modeOfPayment, fontSize = 14.sp)

                    }

                    Text(text = payment.paymentReference, fontSize = 12.sp)
                    Spacer(modifier = Modifier.padding(5.dp))
                    if (memberId.isNotEmpty()){
                        Text(text = memberId, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }

                }

                Column(modifier = Modifier
                    .weight(4f)
                    .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = payment.month, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.padding(3.dp))
                    Card(shape = RoundedCornerShape(10.dp), elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)) {
                        Text(modifier = Modifier.padding(5.dp), text = payment.amount.toString())
                    }
                    Spacer(modifier = Modifier.padding(3.dp))
                    Text(text = payment.paymentDate, fontSize = 12.sp)
                }
            }
        Spacer(modifier = Modifier.padding(3.dp))
        Divider(modifier = Modifier.fillMaxWidth(), thickness = 2.dp, color = Color.Black)
    }


}
