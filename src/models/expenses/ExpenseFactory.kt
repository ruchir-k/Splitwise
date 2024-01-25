package models.expenses

import models.User

class ExpenseFactory (){
    companion object {
        fun createExpense(type: ExpenseType, name: String, createdBy: User, totalAmount: Double): Expense {
            return when(type) {
                ExpenseType.EQUAL -> EqualExpense(name, totalAmount, createdBy)
                ExpenseType.PERCENT -> TODO()
                ExpenseType.EXACT -> TODO()
                else -> TODO()
            }
        }
    }
}