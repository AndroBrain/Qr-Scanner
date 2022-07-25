package com.androbrain.qr.scanner.data.sms

import kotlinx.coroutines.flow.Flow

interface SmsDataSource {
    suspend fun insert(smsModel: SmsModel)

    fun getAll(): Flow<List<SmsModel>>
}
