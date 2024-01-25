package models.splits

import models.User
import models.expenses.ExpenseType

class SplitFactory {
    companion object {
        fun createSplit(type: ExpenseType,user: User, amountOrPercent: Double): Split {
            return when(type) {
                ExpenseType.EQUAL -> EqualSplit(user)
                ExpenseType.PERCENT -> TODO()
                ExpenseType.EXACT -> TODO()
                else -> TODO()
            }
        }
    }
}