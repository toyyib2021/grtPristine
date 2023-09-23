package com.stevdza.san.testapp.dataMain

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


class GrtPayment : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var _ID: String = ""
    var memberId: String = ""
    var modeOfPayment: String = ""
    var purposeOfPayment: String = ""
    var paymentReference: String = ""
    var paymentDate: String = ""
    var paymentMonth: String = ""
    var month: String = ""
    var paymentYear: String = ""
    var amount: Int = 0

}