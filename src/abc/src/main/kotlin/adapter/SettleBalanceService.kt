package adapter

import domain.usecases.external.SettleBalanceCommand

class SettleBalanceService
constructor(
    private val settleBalanceCommand: SettleBalanceCommand
): Service{
    override suspend fun invoke() {
        print("Enter GroupId: ")
        val groupid = readln().toInt()
        print("Enter ower UserId: ")
        val oweruserid = readln().toInt()
        print("Enter owedto UserId: ")
        val owedtouserid = readln().toInt()
        settleBalanceCommand.invoke(groupid,owedtouserid,oweruserid)
    }

}