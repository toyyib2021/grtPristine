package com.stevdza.san.testapp.dataMain

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class GrtWithdrawal : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var _ID: String = ""
    var memberId: String = ""
    var modeOfWithdraw: String = ""
    var withdrawDate: String = ""
    var purposeOfWithdraw: String = ""
    var withdrawalMonth: String = ""
    var withdrawalYear: String = ""
    var chequeNo: String = ""
    var accountCode: String = ""
    var voucherNo: String = ""
    var description: String = ""
    var amount: Int = 0
}