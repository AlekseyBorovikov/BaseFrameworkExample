package com.example.characters

import com.example.common.framework.mvvm.MvvmViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(): MvvmViewModel<CharactersViewState>() {
    override fun initState() = CharactersViewState()
}