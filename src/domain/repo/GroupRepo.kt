package domain.repo

import domain.entities.Group
import domain.entities.User

interface GroupRepo {
    fun createGroup(group: Group)
    fun addUserToGroup(user: User, group: Group)
}