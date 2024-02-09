package adapter

import domain.usecases.external.ShowBalanceByGroupUserCommand
import javax.inject.Inject

class ShowBalanceByGroupUserService
@Inject
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