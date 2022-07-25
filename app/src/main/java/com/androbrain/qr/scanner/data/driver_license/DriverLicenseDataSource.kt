package com.androbrain.qr.scanner.data.driver_license

import kotlinx.coroutines.flow.Flow

interface DriverLicenseDataSource {
    suspend fun insert(driverlicenseModel: DriverLicenseModel)

    fun getAll(): Flow<List<DriverLicenseModel>>
}
