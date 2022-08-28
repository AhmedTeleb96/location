package com.example.mylocation.domain.repository

import android.location.Location
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    fun getLocation(): Flow<Location>

}