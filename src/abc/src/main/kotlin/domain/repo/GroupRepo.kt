package domain.repo

import domain.entities.Group
import domain.entities.User

interface GroupRepo {
    suspend fun createGroup(group: Group)
    suspend fun getGroup(id: String)
    fun addUserToGroup(user: User, group: Group)
}