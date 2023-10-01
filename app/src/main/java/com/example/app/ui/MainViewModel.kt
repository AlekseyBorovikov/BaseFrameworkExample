package com.example.app.ui

import com.example.common.framework.mvvm.MvvmViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): MvvmViewModel<MainViewState>() {
    override fun initState() = MainViewState()
}