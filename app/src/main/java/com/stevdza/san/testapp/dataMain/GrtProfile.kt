package com.stevdza.san.testapp.dataMain

import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class GrtProfile : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var _ID: String = ""
    var name: String = ""
    var refNo: String = ""
    var dateOfBirth: String = ""
    var sex: String = ""
    var stateOfOrgin: String = ""
    var lga: String = ""
    var homeTown: String = ""
    var maritalStatus: String = ""
    var pleadge: Int = 0


    var adminFeeBal: Int = 0
    var pledgeBal: Int = 0
    var loanBal: Int = 0
    var sharesBal: Int = 0
    var targetBal: Int = 0
    var accessPin: String=""


    var contact: Contact? = null
    var employer: Employer? = null
    var nextOfKin: NextOfKin? = null
    var referee: Referee? = null

//    var payment: RealmList<Payment> = realmListOf()
//    var withdraw: RealmList<Withdraw> = realmListOf()
    var timestamp: RealmInstant = RealmInstant.now()
//    var address: Address? = null
}
class Contact : EmbeddedRealmObject {
    var email: String = ""
    var address: String = ""
    var phone: String = ""
}

class Employer : EmbeddedRealmObject {
    var employerName: String = ""
    var employerAddress: String = ""
    var responsibilities: String = ""
    var post: String = ""
}
class NextOfKin : EmbeddedRealmObject {
    var name: String = ""
    var phone: String = ""
    var relationship: String = ""
    var occupation: String = ""
    var age: String = ""
}
class Referee : EmbeddedRealmObject {
    var name: String = ""
    var phone: String = ""
    var reMemberNo: String = ""
    var name2: String = ""
    var phone2: String = ""
    var reMemberNo2: String = ""
}


