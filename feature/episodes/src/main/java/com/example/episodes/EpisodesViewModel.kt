package com.example.episodes

import com.example.common.framework.mvvm.MvvmViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(): MvvmViewModel<EpisodesViewState>() {
    override fun initState() = EpisodesViewState()
}