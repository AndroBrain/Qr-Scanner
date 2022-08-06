package com.androbrain.qr.scanner.data.text.local

import com.androbrain.qr.scanner.data.text.TextModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

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
