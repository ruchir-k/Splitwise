package domain.usecases.external

import domain.repo.ExpenseRepo
import domain.repo.UserRepo
import javax.inject.Inject

class ShowBalanceByGroupCommand
@Inject
constructor(
    private val expenseRepo: ExpenseRepo,
    private val userRepo: UserRepo
){
    suspend fun invoke(groupid: Int) {
        val expenses = expenseRepo.getBalanceByGroup(groupid)
        expenses.forEach { expense->
            val ower = userRepo.getUser(expense.oweruserid)
            val owedTo = userRepo.getUser(expense.owedToUserId)
            println("${ower?.name}(UserId:${ower?.id}) owes to ${owedTo?.name}(UserId:${owedTo?.id}) an amount of ${expense.amount}")
        }
    }
}