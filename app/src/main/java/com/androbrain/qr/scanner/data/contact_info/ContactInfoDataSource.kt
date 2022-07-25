package com.androbrain.qr.scanner.data.contact_info

import kotlinx.coroutines.flow.Flow

interface ContactInfoDataSource {
    suspend fun insert(contactinfoModel: ContactInfoModel)

    fun getAll(): Flow<List<ContactInfoModel>>
}
