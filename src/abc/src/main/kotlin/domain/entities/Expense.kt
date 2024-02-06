package domain.entities

import utils.Utils

abstract class Expense (val name: String, var totalAmount: Double,
                             val createdBy: User, val  type: ExpenseType?, val id: Long = 0L) {

    lateinit var splits: MutableList<Split>
    lateinit var paidBy: User

//    abstract fun validateSplits(): Unit

    fun validate(): Boolean {
        var sum: Double = 0.0
        for (split in splits) {
            sum += split.amount
        }
        return Utils.isApproxEqual(sum, totalAmount)
    }

    abstract fun recalculate(): Unit

//    @JvmName("setTotalAmountJvm")
//    fun setTotalAmount(totalAmount: Double) {
//        this.totalAmount = totalAmount
//        recalculate()
//    }
//
//    @JvmName("setSplitsJvm")
//    fun setSplits(splits:List<Split>) {
//        this.splits = splits.toMutableList()
//        validateSplits()
//        recalculate()
//    }
//
//    @JvmName("addSplitsJvm")
//    fun addSplits(split: Split) {
//        splits.add(split)
//        validateSplits()
//        recalculate()
//    }
//
//    @JvmName("removeSplitJvm")
//    fun removeSplit(split: Split) {
//        splits.remove(split)
//        recalculate()
//    }
}