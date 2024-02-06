package domain.repo

import domain.entities.ExpenseType
import domain.entities.Group
import domain.entities.Split
import domain.entities.User

interface ExpenseRepo {
    fun addExpense(name: String, type: ExpenseType, group: Group, createdBy: User, paidBy: User,
                   splits: MutableList<Split>, totalAmount: Double)
    fun settleExpense(group: Group, ower: User, owedTo: User)
    fun showBalance(group: Group, user: User)
    fun showAllBalancesGroup(group: Group)
    fun showAllBalances()
}