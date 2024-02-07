package adapter

import domain.usecases.external.AddUserToGroupCommand

class AddUserToGroupService
constructor(
    private val addUserToGroupCommand: AddUserToGroupCommand
): Service{
    override suspend fun invoke() {
        print("Enter GroupId: ")
        val groupid = readln().toInt()
        print("Enter UserId: ")
        val userid = readln().toInt()
        addUserToGroupCommand.invoke(userid,groupid)
    }

}