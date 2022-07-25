package com.androbrain.qr.scanner.data.driver_license.local

import com.androbrain.qr.scanner.data.driver_license.DriverLicenseDataSource
import com.androbrain.qr.scanner.data.driver_license.DriverLicenseModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DriverLicenseLocalDataSource @Inject constructor(
    private val driverLicenseDao: DriverLicenseDao,
) : DriverLicenseDataSource {
    override suspend fun insert(driverlicenseModel: DriverLicenseModel) =
        withContext(Dispatchers.IO) {
            driverLicenseDao.insert(driverlicenseModel.toEntity())
        }

    override fun getAll() = driverLicenseDao.getAll().map { driverlicenseList ->
        driverlicenseList.map { driverlicense -> driverlicense.toModel() }
    }
}
