package com.example.mylocation.presiention.fragments

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mylocation.R
import com.example.mylocation.common.utils.Resource
import com.example.mylocation.presiention.LocationViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_location.*
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

@AndroidEntryPoint
class FragmentLocation : Fragment(R.layout.fragment_location) , EasyPermissions.PermissionCallbacks{

    companion object {
        const val PERMISSION_LOCATION_REQUEST_CODE = 1
    }

    private val viewModel by viewModels<LocationViewModel>()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        btn_getLocation.setOnClickListener {
            requestLocationPermission()
        }



        viewModel.getLocation()

        lifecycleScope.launchWhenStarted {
            viewModel.locationState.collect{
                when(it){
                    is Resource.Success -> {

                        Log.i("Address", "long: ${it.data?.longitude} " +
                                "lat: ${it.data?.latitude}")


                    }
                    is Resource.Error -> {

                        Log.i("Address", "error" + it.message)
                        Snackbar.make(
                                requireView(), "error" + it.message, Snackbar.LENGTH_LONG
                        ).show()

                    }
                    is Resource.Loading -> {
                        Log.i("Address", "loading" )
                    }
                }

            }
        }

    }



    private fun hasLocationPermission() =
        EasyPermissions.hasPermissions(requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        )

    private fun requestLocationPermission() {
        EasyPermissions.requestPermissions(
            this,
            "This application cannot work without Location Permission.",
            PERMISSION_LOCATION_REQUEST_CODE,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        } else {
            requestLocationPermission()
        }
    }
    var latLng = LatLng(0.0, 0.0)

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        Toast.makeText(
            requireContext(),
            "Permission Granted!",
            Toast.LENGTH_SHORT
        ).show()


        //here we get the location
        viewModel.getLocation()

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }






}