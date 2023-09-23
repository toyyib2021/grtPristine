package com.stevdza.san.testapp.dataMain

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


class Admin : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var _ID: String = ""
    var secPin: String = ""
    var finPin: String = ""
    var adminFee: String = ""
    var sharesUnitValue: String = ""
}