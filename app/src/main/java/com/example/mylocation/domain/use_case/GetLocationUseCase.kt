package com.example.mylocation.domain.use_case

import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.example.mylocation.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    operator fun invoke() : Flow<Location> = locationRepository.getLocation()

}