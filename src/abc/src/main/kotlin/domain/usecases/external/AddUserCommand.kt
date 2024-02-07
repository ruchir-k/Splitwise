package domain.usecases.external

import domain.repo.UserRepo

class AddUserCommand
constructor(
    private val userRepo: UserRepo
){
    suspend fun invoke(name: String, email: String) {
        userRepo.addUser(name, email)
    }
}