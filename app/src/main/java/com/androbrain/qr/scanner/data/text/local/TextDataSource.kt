package com.androbrain.qr.scanner.data.text

import kotlinx.coroutines.flow.Flow

interface TextDataSource {
    suspend fun insert(textModel: TextModel)

    fun getAll(): Flow<List<TextModel>>
}
