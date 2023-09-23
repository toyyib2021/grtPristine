package com.stevdza.san.testapp.di

import android.util.Log
import com.stevdza.san.testapp.dataMain.Admin
import com.stevdza.san.testapp.dataMain.Contact
import com.stevdza.san.testapp.dataMain.Employer
import com.stevdza.san.testapp.dataMain.GrtPayment
import com.stevdza.san.testapp.dataMain.GrtProfile
import com.stevdza.san.testapp.dataMain.GrtWithdrawal
import com.stevdza.san.testapp.dataMain.NextOfKin
import com.stevdza.san.testapp.dataMain.Referee
import com.stevdza.san.testapp.ui.Constants.APP_ID
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId

object AlMurabbiDB : AlMurabbiRepository {
    private val app = App.create(APP_ID)
    private val user = app.currentUser
    private lateinit var realm: Realm

    init {
        configureTheRealm()
    }


    override fun configureTheRealm() {
        if (user != null) {
            val config = SyncConfiguration.Builder(
                user,
                setOf(
                    GrtProfile::class, Contact::class, Employer::class,
                    NextOfKin::class, Referee::class, GrtWithdrawal::class,
                    GrtPayment::class, Admin::class
                   ))
                .initialSubscriptions { sub ->
                    add(query = sub.query<GrtProfile>(query = "_ID == $0", "222222"))
                    add(query = sub.query<GrtWithdrawal>(query = "_ID == $0", "222222"))
                    add(query = sub.query<GrtPayment>(query = "_ID == $0", "222222"))
                    add(query = sub.query<Admin>(query = "_ID == $0", "222222"))
                }
                .log(LogLevel.ALL)
                .build()
            realm = Realm.open(config)
        }
    }

    // GRT Profile
    override fun getGrtProfileList(): Flow<List<GrtProfile>> {
        return realm.query<GrtProfile>().asFlow().map { it.list }
    }

    override fun filterGrtProfileListByName(name: String): Flow<List<GrtProfile>> {
        return realm.query<GrtProfile>(query = "name CONTAINS[c] $0", name).asFlow().map { it.list }
    }

    override fun getMemberWithMemberRegNp(memberRegNo: String): Flow<List<GrtProfile>> {
        return realm.query<GrtProfile>(query = "refNo CONTAINS[c] $0", memberRegNo).asFlow().map { it.list }
    }

    override fun getMemberWithID(_id: ObjectId): GrtProfile {
        return realm.query<GrtProfile>(query = "_id == $0", _id).first().find() as GrtProfile
    }

    override suspend fun insertMember(grtProfile: GrtProfile) {
        if (user != null) {
            realm.write {
                try {
                    copyToRealm(grtProfile.apply { _ID = "222222" })
                } catch (e: Exception) {
                    Log.d("insertMember", e.message.toString())
                }
            }
        }
    }

    override suspend fun updateMemberPersonalInfo(grtProfile: GrtProfile) {
        realm.write {
            val queriedPerson = query<GrtProfile>(query = "_id == $0", grtProfile._id).first().find()
            queriedPerson?.name = grtProfile.name
            queriedPerson?.refNo = grtProfile.refNo
            queriedPerson?.dateOfBirth = grtProfile.dateOfBirth
            queriedPerson?.sex = grtProfile.sex
            queriedPerson?.stateOfOrgin = grtProfile.stateOfOrgin
            queriedPerson?.lga = grtProfile.lga
            queriedPerson?.homeTown = grtProfile.homeTown
            queriedPerson?.maritalStatus = grtProfile.maritalStatus


        }
    }

    override suspend fun updateMemberContactInfo(grtProfile: GrtProfile) {
        realm.write {
            val queriedPerson = query<GrtProfile>(query = "_id == $0", grtProfile._id).first().find()
            queriedPerson?.contact = grtProfile.contact


        }
    }
    override suspend fun updateMemberNextOfKinInfo(grtProfile: GrtProfile) {
        realm.write {
            val queriedPerson = query<GrtProfile>(query = "_id == $0", grtProfile._id).first().find()
            queriedPerson?.nextOfKin = grtProfile.nextOfKin
        }
    }

    override suspend fun updateMemberEmploymentInfo(grtProfile: GrtProfile) {
        realm.write {
            val queriedPerson = query<GrtProfile>(query = "_id == $0", grtProfile._id).first().find()
            queriedPerson?.employer = grtProfile.employer
        }
    }

    override suspend fun updateMemberRefereeInfo(grtProfile: GrtProfile) {
        realm.write {
            val queriedPerson = query<GrtProfile>(query = "_id == $0", grtProfile._id).first().find()
            queriedPerson?.referee = grtProfile.referee
        }
    }

    override suspend fun deleteMember(id: ObjectId) {
        realm.write {
            val person = query<GrtProfile>(query = "_id == $0", id).first().find()
            try {
                person?.let { delete(it) }
            } catch (e: Exception) {
                Log.d("deleteMember", "${e.message}")
            }
        }
    }

    // Payment
    override fun getPaymentData(): Flow<List<GrtPayment>> {
        return realm.query<GrtPayment>().asFlow().map { it.list }

    }

    override fun getPaymentWithMemberId(memberId: String): Flow<List<GrtPayment>> {
        return realm.query<GrtPayment>(query = "memberId CONTAINS[c] $0", memberId).asFlow().map { it.list }
    }

    override fun getPaymentWithPurposeOfPayment(purposeOfPayment: String): Flow<List<GrtPayment>> {
        return realm.query<GrtPayment>(query = "purposeOfPayment CONTAINS[c] $0", purposeOfPayment).asFlow().map { it.list }
    }

    override fun getPaymentWithID(_id: ObjectId): GrtPayment {
        return realm.query<GrtPayment>(query = "_id == $0", _id).first().find() as GrtPayment

    }

    override suspend fun insertPayment(grtPayment: GrtPayment) {
        if (user != null) {
            realm.write {
                try {
                    copyToRealm(grtPayment.apply { _ID = "222222" })
                } catch (e: Exception) {
                    Log.d("insertPayment", e.message.toString())
                }
            }
        }
    }

    override suspend fun updatePayment(grtPayment: GrtPayment) {
        realm.write {
            val queriedPayment = query<GrtPayment>(query = "_id == $0", grtPayment._id).first().find()
            queriedPayment?.memberId = grtPayment.memberId
            queriedPayment?.modeOfPayment = grtPayment.modeOfPayment
            queriedPayment?.purposeOfPayment = grtPayment.purposeOfPayment
            queriedPayment?.paymentReference = grtPayment.paymentReference
            queriedPayment?.paymentDate = grtPayment.paymentDate
            queriedPayment?.paymentMonth = grtPayment.paymentMonth
            queriedPayment?.month = grtPayment.month
            queriedPayment?.paymentYear = grtPayment.paymentYear
            queriedPayment?.amount = grtPayment.amount

        }
    }

    override suspend fun deletePayment(id: ObjectId) {
        realm.write {
            val payment = query<GrtPayment>(query = "_id == $0", id).first().find()
            try {
                payment?.let { delete(it) }
            } catch (e: Exception) {
                Log.d("MongoRepositoryImpl", "${e.message}")
            }
        }
    }


    // WithDrawal
    override fun getWithdrawData(): Flow<List<GrtWithdrawal>> {
        return realm.query<GrtWithdrawal>().asFlow().map { it.list }
    }

    override fun getWithdrawalWithMemberId(memberId: String): Flow<List<GrtWithdrawal>> {
        return realm.query<GrtWithdrawal>(query = "memberId CONTAINS[c] $0", memberId).asFlow().map { it.list }
    }

    override fun getPaymentWithPurposeOfWithdraw(purposeOfWithdraw: String): Flow<List<GrtWithdrawal>> {
        return realm.query<GrtWithdrawal>(query = "purposeOfWithdraw CONTAINS[c] $0", purposeOfWithdraw).asFlow().map { it.list }
    }

    override fun getWithdrawWithID(_id: ObjectId): GrtWithdrawal {
        return realm.query<GrtWithdrawal>(query = "_id == $0", _id).first().find() as GrtWithdrawal
    }

    override suspend fun insertWithdraw(grtWithdraw: GrtWithdrawal) {
        if (user != null) {
            realm.write {
                try {
                    copyToRealm(grtWithdraw.apply { _ID = "222222" })
                } catch (e: Exception) {
                    Log.d("insertWithdraw", e.message.toString())
                }
            }
        }
    }

    override suspend fun updateWithdraw(grtWithdraw: GrtWithdrawal) {
        realm.write {
            val queriedWithdraw = query<GrtWithdrawal>(query = "_id == $0", grtWithdraw._id).first().find()
            queriedWithdraw?.memberId = grtWithdraw.memberId
            queriedWithdraw?.modeOfWithdraw = grtWithdraw.modeOfWithdraw
            queriedWithdraw?.withdrawDate = grtWithdraw.withdrawDate
            queriedWithdraw?.purposeOfWithdraw = grtWithdraw.purposeOfWithdraw
            queriedWithdraw?.withdrawalMonth = grtWithdraw.withdrawalMonth
            queriedWithdraw?.withdrawalYear = grtWithdraw.withdrawalYear
            queriedWithdraw?.chequeNo = grtWithdraw.chequeNo
            queriedWithdraw?.accountCode = grtWithdraw.accountCode
            queriedWithdraw?.description = grtWithdraw.description
            queriedWithdraw?.amount = grtWithdraw.amount
        }
    }

    override suspend fun deleteWithdraw(id: ObjectId) {
        realm.write {
            val withdraw = query<GrtWithdrawal>(query = "_id == $0", id).first().find()
            try {
                withdraw?.let { delete(it) }
            } catch (e: Exception) {
                Log.d("deleteWithdraw", "${e.message}")
            }
        }
    }

    override fun getAdminData(): Flow<List<Admin>> {
        return realm.query<Admin>().asFlow().map { it.list }
    }

    override fun getWithAdminID(_id: ObjectId): Admin {
        return realm.query<Admin>(query = "_id == $0", _id).first().find() as Admin
    }

    override suspend fun insertAdmin(admin: Admin) {
        if (user != null) {
            realm.write {
                try {
                    copyToRealm(admin.apply { _ID = "222222" })
                } catch (e: Exception) {
                    Log.d("insertAdmin", e.message.toString())
                }
            }
        }
    }


    override suspend fun updateAdmin(admin: Admin) {
        realm.write {
            val querieAdmin = query<Admin>(query = "_id == $0", admin._id).first().find()
            querieAdmin?.secPin = admin.secPin
            querieAdmin?.finPin = admin.finPin
            querieAdmin?.adminFee = admin.adminFee
            querieAdmin?.sharesUnitValue = admin.sharesUnitValue
        }
    }

    override suspend fun deleteAdmin(id: ObjectId) {
        realm.write {
            val admin = query<Admin>(query = "_id == $0", id).first().find()
            try {
                admin?.let { delete(it) }
            } catch (e: Exception) {
                Log.d("admin", "${e.message}")
            }
        }
    }
}