package com.example.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.characters.databinding.FragmentCharactersBinding
import com.example.common.framework.mvvm.MvvmFragment

class CharactersFragment: MvvmFragment<FragmentCharactersBinding, CharactersViewModel, CharactersViewState>() {
    override fun getViewModel() = viewModels<CharactersViewModel>().value

    override fun createViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) = FragmentCharactersBinding.inflate(inflater, parent, false)

    override fun setupView() = Unit

    override fun handleState(state: CharactersViewState) = Unit
}