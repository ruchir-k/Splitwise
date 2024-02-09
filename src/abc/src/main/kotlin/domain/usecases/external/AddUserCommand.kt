package domain.usecases.external

import domain.repo.UserRepo
import javax.inject.Inject

class AddUserCommand
@Inject
constructor(
    private val userRepo: UserRepo
){
    suspend fun invoke(name: String, email: String) {
        userRepo.addUser(name, email)
    }
}