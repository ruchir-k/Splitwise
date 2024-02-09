package domain.repo

import domain.entities.Group
import domain.entities.User

interface GroupRepo {
    suspend fun createGroup(name: String, users: List<Int>): Group
    suspend fun getGroup(id: Int): Group?
    suspend fun addUserToGroup(userid: Int, groupid: Int)
}