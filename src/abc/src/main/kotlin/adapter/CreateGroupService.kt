package adapter

import domain.usecases.external.CreateGroupCommand
import javax.inject.Inject

class CreateGroupService
@Inject
constructor(
    private val createGroupCommand: CreateGroupCommand
): Service{
    override suspend fun invoke() {
        print("Enter Group Name:")
        val name = readln()
        print("Enter UserIds to include in group:(space seperated)")
        val userids = readln().split(" ").map { it.toInt() }
        createGroupCommand.invoke(name,userids)
    }

}