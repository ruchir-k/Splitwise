package domain.repo

import domain.entities.Group
import domain.entities.User

interface UserRepo {
    fun addUser(user: User)
    fun addUserToGroup(user: User, group: Group)

}