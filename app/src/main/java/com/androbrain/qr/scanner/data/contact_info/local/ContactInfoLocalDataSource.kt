package com.androbrain.qr.scanner.data.contact_info.local

import com.androbrain.qr.scanner.data.contact_info.ContactInfoDataSource
import com.androbrain.qr.scanner.data.contact_info.ContactInfoModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ContactInfoLocalDataSource @Inject constructor(
    private val contactInfoDao: ContactInfoDao,
) : ContactInfoDataSource {
    override suspend fun insert(contactinfoModel: ContactInfoModel) = withContext(Dispatchers.IO) {
        contactInfoDao.insert(contactinfoModel.toEntity())
    }

    override fun getAll() = contactInfoDao.getAll().map { contactinfoList ->
        contactinfoList.map { contactinfo -> contactinfo.toModel() }
    }
}
