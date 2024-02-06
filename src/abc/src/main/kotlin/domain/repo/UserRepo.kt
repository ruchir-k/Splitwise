package domain.repo

import domain.entities.Group
import domain.entities.User

interface UserRepo {
    suspend fun addUser(name: String, email: String): User
    suspend fun getUser(id: String): User
    fun addUserToGroup(user: User, group: Group)

}