package com.example.episodes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.common.framework.mvvm.MvvmFragment
import com.example.episodes.databinding.FragmentEpisodesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodesFragment: MvvmFragment<FragmentEpisodesBinding, EpisodesViewModel, EpisodesViewState>() {

    override fun getViewModel() = viewModels<EpisodesViewModel>().value

    override fun createViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) = FragmentEpisodesBinding.inflate(inflater, parent, false)

    override fun setupView() = Unit

    override fun handleState(state: EpisodesViewState) = Unit

}