package com.stevdza.san.testapp.screen.component

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stevdza.san.testapp.dataMain.GrtWithdrawal

@Composable
fun WithdrawalUiNotEditable(
    withdraw: GrtWithdrawal,
    purposeOfWithdraw: String,
    memberId: String,

    ){

    Column(modifier = Modifier
        .fillMaxWidth()
    ) {
        Column(modifier = Modifier.fillMaxWidth()
        ) {
                Row {
                    Column(modifier = Modifier.weight(6f)) {
                        Row {
                            Column(modifier = Modifier.weight(5f)) {
                                Text(text = "Acct. Code", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                                Text(text = withdraw.accountCode, fontSize = 12.sp)
                            }
                            Column(modifier = Modifier.weight(5f)) {
                                Text(text = "Cheque No", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                                Text(text = withdraw.chequeNo, fontSize = 12.sp)
                            }
                        }
                        Spacer(modifier = Modifier.padding(2.dp))

                        if (memberId.isNotEmpty()){
                            Text(modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                                text = memberId, fontSize = 14.sp)
                        }else{
                            Text(modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                                text = purposeOfWithdraw, fontSize = 14.sp)
                        }

                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    Column(modifier = Modifier
                        .weight(4f)
                        .fillMaxWidth()) {
                        Text(text = "Voucher No: ${withdraw.voucherNo}", fontSize = 14.sp)
                        Spacer(modifier = Modifier.padding(3.dp))
                        Card(shape = RoundedCornerShape(10.dp), elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)) {
                            Text(modifier = Modifier.padding(7.dp), text = withdraw.amount.toString(), fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.padding(3.dp))
                        Text(text = withdraw.withdrawDate, fontSize = 12.sp)
                    }
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Divider(modifier = Modifier.fillMaxWidth(), thickness = 2.dp, color = Color.Black)
            }
    }


}