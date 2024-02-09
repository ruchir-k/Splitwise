package domain.usecases.external

import domain.repo.GroupRepo
import javax.inject.Inject

class CreateGroupCommand
@Inject
constructor(
    private val groupRepo: GroupRepo
){
    suspend fun invoke(name: String, users: List<Int>) {
        val newGroup = groupRepo.createGroup(name, users)
        println("New Group with id: ${newGroup.id} and name: ${newGroup.name} created")
    }
}