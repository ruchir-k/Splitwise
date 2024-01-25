package commands

import BookKeeper
import Utils
import exceptions.InvalidExpenseTypeException
import exceptions.NoSuchUserException
import models.User
import models.expenses.Expense
import models.expenses.ExpenseFactory
import models.expenses.ExpenseType
import models.splits.Split
import models.splits.SplitFactory
import java.util.*

class AddExpenseCommand: Command {
    override fun execute(cmd: List<String>) {
        val bk = BookKeeper
        val exptype: ExpenseType
        try {
            exptype = ExpenseType.valueOf(cmd[1].uppercase(Locale.getDefault()))
        } catch (e: InvalidExpenseTypeException) {
            println(e.message)
            return
        }

        val name: String = cmd[2]
        val totalAmount: Double = cmd[3]. toDouble()
        val createdBy: User
        try {
            createdBy = Utils.getUser(cmd[4])
        } catch (e: NoSuchUserException) {
            println(e.message)
            return
        }

        val expense:Expense
        try {
            expense = ExpenseFactory.createExpense(exptype, name, createdBy, totalAmount)
        } catch (e: InvalidExpenseTypeException) {
            println(e.message)
            return
        }

        val paidBy: User
        try {
            paidBy = Utils.getUser(cmd[5])
        } catch (e: NoSuchUserException) {
            println(e.message)
            return
        }

        expense.paidBy = paidBy

        val numOfSplits: Int = cmd.size - 6
        val splits = mutableListOf<Split>()

        if(exptype == ExpenseType.EQUAL) {
            for(i in 0 until numOfSplits) {
                val user =  Utils.getUser(cmd[6+i])
                val split = SplitFactory.createSplit(exptype, user, 0.0)
                splits.add(split)
            }
        }

        expense.splits = splits
        bk.addExpense(name,exptype,createdBy,paidBy, splits, totalAmount)
    }
}