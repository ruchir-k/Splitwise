package domain.usecases.internal

import domain.entities.User
import domain.entities.ExpenseType
import domain.entities.Split

class EqualSplit(user: User): Split(user, ExpenseType.EQUAL) {
}
