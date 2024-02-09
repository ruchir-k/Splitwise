package domain.repo

import domain.entities.*

interface ExpenseRepo {
    suspend fun addExpense(groupid: Int, owedToUserId: Int, oweruserid:Int, amount: Double)
    suspend fun getExpense(groupid: Int, owedToUserId: Int, oweruserid:Int): Expense
    suspend fun updateExpense(groupid: Int, owedToUserId: Int, oweruserid:Int, amount: Double)
    suspend fun settleExpense(groupid: Int, owedToUserId: Int, oweruserid:Int)
    suspend fun isValidExpense(groupid: Int, owedToUserId: Int, oweruserid:Int): Boolean
    suspend fun getBalanceByGroupUser(groupid: Int, userid: Int): List<Expense>
    suspend fun getBalanceByGroup(groupid: Int): List<Expense>
    suspend fun getAllBalances(): List<Expense>
}