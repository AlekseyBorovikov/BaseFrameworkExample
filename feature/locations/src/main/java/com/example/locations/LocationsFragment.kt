package com.example.locations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.common.framework.mvvm.MvvmFragment
import com.example.locations.databinding.FragmentLocationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationsFragment: MvvmFragment<FragmentLocationsBinding, LocationsViewModel, LocationsViewState>() {
    override fun getViewModel() = viewModels<LocationsViewModel>().value

    override fun createViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) = FragmentLocationsBinding.inflate(inflater, parent, false)

    override fun setupView() = Unit

    override fun handleState(state: LocationsViewState) = Unit
}