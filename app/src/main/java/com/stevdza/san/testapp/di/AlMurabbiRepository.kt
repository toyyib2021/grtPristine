package com.stevdza.san.testapp.di

import com.stevdza.san.testapp.dataMain.Admin
import com.stevdza.san.testapp.dataMain.GrtPayment
import com.stevdza.san.testapp.dataMain.GrtProfile
import com.stevdza.san.testapp.dataMain.GrtWithdrawal
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

interface AlMurabbiRepository {

    fun configureTheRealm()

    fun getGrtProfileList(): Flow<List<GrtProfile>>
    fun filterGrtProfileListByName(name: String): Flow<List<GrtProfile>>
    fun getMemberWithMemberRegNp(memberRegNo: String): Flow<List<GrtProfile>>
    fun getMemberWithID(_id: ObjectId): GrtProfile
    suspend fun insertMember(grtProfile: GrtProfile)
    suspend fun updateMemberPersonalInfo(grtProfile: GrtProfile)
    suspend fun updateMemberContactInfo(grtProfile: GrtProfile)
    suspend fun updateMemberEmploymentInfo(grtProfile: GrtProfile)
    suspend fun updateMemberNextOfKinInfo(grtProfile: GrtProfile)
    suspend fun updateMemberRefereeInfo(grtProfile: GrtProfile)
    suspend fun deleteMember(id: ObjectId)

    fun getPaymentData(): Flow<List<GrtPayment>>
    fun getPaymentWithMemberId(memberId: String): Flow<List<GrtPayment>>
    fun getPaymentWithPurposeOfPayment(purposeOfPayment: String): Flow<List<GrtPayment>>
    fun getPaymentWithID(_id: ObjectId): GrtPayment
    suspend fun insertPayment(grtPayment: GrtPayment)
    suspend fun updatePayment(grtPayment: GrtPayment)
    suspend fun deletePayment(id: ObjectId)

    fun getWithdrawData(): Flow<List<GrtWithdrawal>>
    fun getWithdrawalWithMemberId(memberId: String): Flow<List<GrtWithdrawal>>
    fun getPaymentWithPurposeOfWithdraw(purposeOfWithdraw: String): Flow<List<GrtWithdrawal>>
    fun getWithdrawWithID(_id: ObjectId): GrtWithdrawal
    suspend fun insertWithdraw(grtWithdraw: GrtWithdrawal)
    suspend fun updateWithdraw(grtWithdraw: GrtWithdrawal)
    suspend fun deleteWithdraw(id: ObjectId)

    fun getAdminData(): Flow<List<Admin>>
    fun getWithAdminID(_id: ObjectId): Admin
    suspend fun insertAdmin(admin: Admin)
    suspend fun updateAdmin(admin: Admin)
    suspend fun deleteAdmin(id: ObjectId)






}


