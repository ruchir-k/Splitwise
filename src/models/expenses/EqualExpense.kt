package models.expenses

import Utils
import exceptions.IllegalSplitException
import models.User
import models.splits.EqualSplit
import models.splits.Split

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

//fun main() {
//
//    val u1: User = User("ruchir", "email", "abc")
//    val u2: User = User("ruchir2", "email2", "abc")
//    val expense1: EqualExpense = EqualExpense("shopping",1000.0, u1)
//    println(expense1.totalAmount)
//    val split1: Split = EqualSplit(u1)
//    val split2: Split = EqualSplit(u2)
//    val splits = mutableListOf<Split>()
//    splits += split1
//    splits += split2
//    expense1.splits = splits
//
//    expense1.recalculate()
//    println(expense1.splits.get(1).amount)
//}