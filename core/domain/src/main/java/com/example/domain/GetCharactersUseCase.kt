package com.example.domain

import com.example.common.framework.usecase.DataStateUseCase
import com.example.data.repository.CharacterRepository
import com.example.module.SerialCharacter
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
): DataStateUseCase<Int, List<SerialCharacter>>() {
    override suspend fun FlowCollector<Result<List<SerialCharacter>>>.execute(page: Int) {
        emit(characterRepository.getCharacters(page))
    }
}