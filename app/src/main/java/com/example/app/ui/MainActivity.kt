package com.example.app.ui

import android.view.LayoutInflater
import androidx.activity.viewModels
import com.example.app.databinding.ActivityMainBinding
import com.example.common.framework.mvvm.MvvmActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: MvvmActivity<ActivityMainBinding, MainViewModel, MainViewState>() {

    override fun setupViewModel() = viewModels<MainViewModel>().value

    override fun createViewBinding(inflater: LayoutInflater) =
        ActivityMainBinding.inflate(inflater, null, false)

    override fun setupView() = Unit

    override fun setupActivity() = Unit

    override fun handleState(state: MainViewState) = Unit
}