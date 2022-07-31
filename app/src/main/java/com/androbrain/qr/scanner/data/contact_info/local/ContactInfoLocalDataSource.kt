package com.androbrain.qr.scanner.data.contact_info.local

import android.util.Log
import com.androbrain.qr.scanner.data.contact_info.ContactInfoDataSource
import com.androbrain.qr.scanner.data.contact_info.ContactInfoModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ContactInfoLocalDataSource @Inject constructor(
    private val contactInfoDao: ContactInfoDao,
) : ContactInfoDataSource {
    override suspend fun insert(contactInfoModel: ContactInfoModel) = withContext(Dispatchers.IO) {
        contactInfoDao.insert(contactInfoModel.toEntity()).also {
            Log.d("ContactInfoModel", "inserted successfully")
        }
    }

    override fun getAll() = contactInfoDao.getAll().map { contactInfoList ->
        contactInfoList.map { contactInfo -> contactInfo.toModel() }
    }
}
