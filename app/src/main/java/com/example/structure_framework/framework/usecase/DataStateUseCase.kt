package com.example.structure_framework.framework.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * A class in which business logic for working with data should be implemented
 * */
abstract class DataStateUseCase<in Params, ReturnType> where ReturnType : Any {
    protected abstract suspend fun FlowCollector<Result<ReturnType>>.execute(params: Params)
    suspend operator fun invoke(params: Params) = flow { execute(params) }.flowOn(Dispatchers.IO)
}

// Sample:
//
// class GetUser @Inject constructor(
//    private val userRepository: UserRepository,
// ): DataStateUseCase<String, User>() {
//
//    override suspend fun FlowCollector<Result<User>>.execute(userId: String) {
//       emit(userRepository.getUser(userId))
//    }
// }
//
// @HiltViewModel
// class UserViewModel @Inject constructor(
//    private val getUser: GetUser,
// ): MvvmViewModel<UserViewState>() {
//
//   ...
//
//    private suspend fun loadUser() {
//        execute(getUser(userId)) { user ->
//             updateUserState(user)
//        }
//    }
// }