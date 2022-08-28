package com.example.mylocation.presiention

import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylocation.common.utils.Resource
import com.example.mylocation.domain.use_case.GetLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel  @Inject constructor(
    val getLocationUseCase: GetLocationUseCase
) : ViewModel(){

    private var _locationState= MutableStateFlow<Resource<Location,String>>(Resource.Empty())
    val locationState: StateFlow<Resource<Location, String>> = _locationState




    fun getLocation(){
        viewModelScope.launch {
            _locationState.value=Resource.Loading()  // loading
            getLocationUseCase.invoke().catch {e->
                _locationState.value=Resource.Error(e.message) // error
            }.collect {
                Log.i("Address", "getLocation: $it")
                _locationState.value= Resource.Success(it)   // success
            }
        }

    }

}