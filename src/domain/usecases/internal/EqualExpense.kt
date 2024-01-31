package domain.usecases.internal

import domain.entities.Expense
import domain.entities.ExpenseType
import utils.Utils
import exceptions.IllegalSplitException
import domain.entities.User

class EqualExpense(name: String, totalAmount: Double, createdBy: User
): Expense(name, totalAmount, createdBy, ExpenseType.EQUAL) {

    override fun validateSplits() {
        splits.forEach {
            if(it !is EqualSplit) throw IllegalSplitException("EqualExpense must have EqualSplits only")
        }
    }

    override fun recalculate() {
        val numUsers: Int = splits.size
        var sum: Double = 0.0
        val amount: Double = Utils.roundOff(totalAmount/numUsers)
        for (split in splits) {
            split.amount = amount
            sum += split.amount
        }
        if(!Utils.isApproxEqual(sum, totalAmount)) {
            splits.get(0).amount = splits.get(0).amount + totalAmount - sum
        }
    }

}