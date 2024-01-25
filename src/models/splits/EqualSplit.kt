package models.splits

import models.User
import models.expenses.ExpenseType

class EqualSplit(user: User): Split(user, ExpenseType.EQUAL) {
}

//fun main() {
//    val u1: User = User("ruchir", "email", "123", "none", "abc")
//    val equalSplit1: EqualSplit = EqualSplit(u1)
//    println(equalSplit1.amount)
//}