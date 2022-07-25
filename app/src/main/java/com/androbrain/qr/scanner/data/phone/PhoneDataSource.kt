package com.androbrain.qr.scanner.data.phone

import kotlinx.coroutines.flow.Flow

interface PhoneDataSource {
    suspend fun insert(phoneModel: PhoneModel)

    fun getAll(): Flow<List<PhoneModel>>
}
