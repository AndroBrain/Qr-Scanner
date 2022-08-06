package com.androbrain.qr.scanner.data.text.local

import com.androbrain.qr.scanner.data.text.TextModel
import kotlinx.coroutines.flow.Flow

interface TextDataSource {
    suspend fun insert(textModel: TextModel)

    fun getAll(): Flow<List<TextModel>>
}
