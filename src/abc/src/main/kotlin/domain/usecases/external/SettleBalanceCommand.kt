package domain.usecases.external

import domain.repo.ExpenseRepo
import exceptions.NoSuchExpenseException

class SettleBalanceCommand
constructor(
    private val expenseRepo: ExpenseRepo
){
    suspend fun invoke(groupid: Int, owedToUserId: Int, oweruserid: Int) {
        if(!expenseRepo.isValidExpense(groupid, owedToUserId, oweruserid)) {
            throw NoSuchExpenseException("")
        }
        expenseRepo.settleExpense(groupid, owedToUserId, oweruserid)
    }
}