package com.example.episodes

import com.example.common.framework.mvvm.MvvmViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class EpisodesViewModel(): MvvmViewModel<EpisodesViewState>() {
    override fun initState() = EpisodesViewState()
}