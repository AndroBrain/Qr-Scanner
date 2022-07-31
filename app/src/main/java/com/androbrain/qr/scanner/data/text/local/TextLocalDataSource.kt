package com.androbrain.qr.scanner.data.text.local

import com.androbrain.qr.scanner.data.text.TextModel
import com.androbrain.qr.scanner.data.text.TextDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TextLocalDataSource @Inject constructor(
    private val textDao: TextDao,
) : TextDataSource {
    override suspend fun insert(textModel: TextModel) = withContext(Dispatchers.IO) {
        textDao.insert(textModel.toEntity())
    }

    override fun getAll() = textDao.getAll().map { textList ->
        textList.map { text -> text.toModel() }
    }
}
