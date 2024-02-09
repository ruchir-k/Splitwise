package domain.usecases.external

import exceptions.NoSuchGroupException
import domain.repo.ExpenseRepo
import domain.repo.GroupRepo
import javax.inject.Inject


class AddExpenseCommand
@Inject
constructor(
    private val expenseRepo: ExpenseRepo,
    private val groupRepo: GroupRepo
){
    suspend fun invoke(groupid: Int, payerUserId: Int, totalAmount: Double) {
        val group = groupRepo.getGroup(groupid)!!
        val amount = totalAmount / group.users.size

        if(!group.users.contains(payerUserId)) {
            throw NoSuchGroupException("User:$payerUserId does not exist in Group:$groupid")
        }

        group.users.forEach loop@{ owerUserId->
            if(payerUserId == owerUserId) {
                return@loop
            }

            if(expenseRepo.isValidExpense(groupid,payerUserId,owerUserId)) {
                val expense = expenseRepo.getExpense(groupid,payerUserId,owerUserId)
                expenseRepo.updateExpense(groupid,payerUserId,owerUserId,amount+expense.amount)
            }
            else if(expenseRepo.isValidExpense(groupid,owerUserId,payerUserId)) {
                val expense = expenseRepo.getExpense(groupid,owerUserId,payerUserId)
                val reverseAmount = expense.amount
                if(reverseAmount == amount) {
                    expenseRepo.settleExpense(groupid,owerUserId,payerUserId)
                }
                else if(reverseAmount > amount) {
                    expenseRepo.updateExpense(groupid,owerUserId,payerUserId,reverseAmount-amount)
                }
                else if(amount > reverseAmount) {
                    expenseRepo.settleExpense(groupid,owerUserId,payerUserId)
                    expenseRepo.addExpense(groupid,payerUserId,owerUserId,amount-reverseAmount)
                }
            }
            else {
                expenseRepo.addExpense(groupid,payerUserId,owerUserId,amount)
            }
        }
    }
}