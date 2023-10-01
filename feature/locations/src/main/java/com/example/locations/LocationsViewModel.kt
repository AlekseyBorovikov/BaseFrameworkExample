package com.example.locations

import com.example.common.framework.mvvm.MvvmViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(

): MvvmViewModel<LocationsViewState>() {
    override fun initState() = LocationsViewState()
}