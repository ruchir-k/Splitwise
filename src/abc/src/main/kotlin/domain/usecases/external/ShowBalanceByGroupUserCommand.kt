package domain.usecases.external

import domain.repo.ExpenseRepo
import domain.repo.UserRepo

class ShowBalanceByGroupUserCommand
constructor(
    private val expenseRepo: ExpenseRepo,
    private val userRepo: UserRepo
){
    suspend fun invoke(groupid: Int, userid: Int) {
        val expenses = expenseRepo.getBalanceByGroupUser(groupid, userid)
        expenses.forEach { expense->
            val ower = userRepo.getUser(expense.oweruserid)
            val owedTo = userRepo.getUser(expense.owedToUserId)
            println("${ower?.name}(UserId:${ower?.id}) owes to ${owedTo?.name}(UserId:${owedTo?.id}) an amount of ${expense.amount}")
        }
    }
}