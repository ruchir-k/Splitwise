package models.splits

import models.User
import models.expenses.ExpenseType

abstract class Split (val user: User, val type: ExpenseType?) {
    var amount: Double = 0.0
    companion object {
        private var NEW_UID = 0L
    }
    val notes: String? = null
    val uid: Long = NEW_UID++
}