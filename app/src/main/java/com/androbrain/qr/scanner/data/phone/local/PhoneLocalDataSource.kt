package com.androbrain.qr.scanner.data.phone.local

import com.androbrain.qr.scanner.data.phone.PhoneDataSource
import com.androbrain.qr.scanner.data.phone.PhoneModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class PhoneLocalDataSource @Inject constructor(
    private val phoneDao: PhoneDao,
) : PhoneDataSource {
    override suspend fun insert(phoneModel: PhoneModel) = withContext(Dispatchers.IO) {
        phoneDao.insert(phoneModel.toEntity())
    }

    override fun getAll() = phoneDao.getAll().map { phoneList ->
        phoneList.map { phone -> phone.toModel() }
    }
}
