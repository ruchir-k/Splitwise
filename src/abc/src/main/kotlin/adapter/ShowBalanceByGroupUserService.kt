package adapter

import domain.usecases.external.ShowBalanceByGroupUserCommand

class ShowBalanceByGroupUserService
constructor(
    private val showBalanceByGroupUserCommand: ShowBalanceByGroupUserCommand
): Service{
    override suspend fun invoke() {
        print("Enter the GroupId to view expenses: ")
        val groupid = readln().toInt()
        print("Enter the UserId to view expenses: ")
        val userid = readln().toInt()
        showBalanceByGroupUserCommand.invoke(groupid,userid)
    }

}