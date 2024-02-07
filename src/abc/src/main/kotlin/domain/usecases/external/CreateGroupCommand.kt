package domain.usecases.external

import domain.repo.GroupRepo

class CreateGroupCommand
constructor(
    private val groupRepo: GroupRepo
){
    suspend fun invoke(name: String, users: List<Int>) {
        val newGroup = groupRepo.createGroup(name, users)
        println("New Group with id: ${newGroup.id} and name: ${newGroup.name} created")
    }
}