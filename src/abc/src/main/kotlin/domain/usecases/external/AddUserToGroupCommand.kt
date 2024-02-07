package domain.usecases.external

import domain.repo.GroupRepo

class AddUserToGroupCommand
constructor(
    private val groupRepo: GroupRepo
){
    suspend fun invoke(userid: Int, groupid: Int) {
        groupRepo.addUserToGroup(userid, groupid)
    }
}