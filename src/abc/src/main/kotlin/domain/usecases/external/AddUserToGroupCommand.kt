package domain.usecases.external

import domain.repo.GroupRepo
import javax.inject.Inject

class AddUserToGroupCommand
@Inject
constructor(
    private val groupRepo: GroupRepo
){
    suspend fun invoke(userid: Int, groupid: Int) {
        groupRepo.addUserToGroup(userid, groupid)
    }
}