package adapter

import domain.usecases.external.ShowBalanceByGroupCommand
import javax.inject.Inject

class ShowBalanceByGroupService
@Inject
constructor(
    private val showBalanceByGroupCommand: ShowBalanceByGroupCommand
): Service{
    override suspend fun invoke() {
        print("Enter the GroupId to view expenses: ")
        val groupid = readln().toInt()
        showBalanceByGroupCommand.invoke(groupid)
    }

}