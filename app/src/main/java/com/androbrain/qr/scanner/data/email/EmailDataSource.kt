package com.androbrain.qr.scanner.data.email

import kotlinx.coroutines.flow.Flow

interface EmailDataSource {
    suspend fun insert(emailModel: EmailModel)

    fun getAll(): Flow<List<EmailModel>>
}
