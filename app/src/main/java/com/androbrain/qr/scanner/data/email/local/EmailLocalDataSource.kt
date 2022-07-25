package com.androbrain.qr.scanner.data.email.local

import com.androbrain.qr.scanner.data.email.EmailDataSource
import com.androbrain.qr.scanner.data.email.EmailModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class EmailLocalDataSource @Inject constructor(
    private val emailDao: EmailDao,
) : EmailDataSource {
    override suspend fun insert(emailModel: EmailModel) = withContext(Dispatchers.IO) {
        emailDao.insert(emailModel.toEntity())
    }

    override fun getAll() = emailDao.getAll().map { emailList ->
        emailList.map { email -> email.toModel() }
    }
}
