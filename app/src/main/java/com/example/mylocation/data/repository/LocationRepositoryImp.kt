package com.example.mylocation.data.repository

import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.example.mylocation.domain.repository.LocationRepository
import com.example.socialmedia.main.data.models.source.GoogleLocationDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationRepositoryImp @Inject constructor(
    private val googleLocationDataSource: GoogleLocationDataSource
) : LocationRepository {

    override fun getLocation(): Flow<Location> {
        return googleLocationDataSource.fetchUpdates()
    }


}