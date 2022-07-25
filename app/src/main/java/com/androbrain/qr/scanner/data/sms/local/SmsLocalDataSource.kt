package com.androbrain.qr.scanner.data.sms.local

import com.androbrain.qr.scanner.data.sms.SmsDataSource
import com.androbrain.qr.scanner.data.sms.SmsModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class SmsLocalDataSource @Inject constructor(
    private val smsDao: SmsDao,
) : SmsDataSource {
    override suspend fun insert(smsModel: SmsModel) = withContext(Dispatchers.IO) {
        smsDao.insert(smsModel.toEntity())
    }

    override fun getAll() = smsDao.getAll().map { smsList ->
        smsList.map { sms -> sms.toModel() }
    }
}
