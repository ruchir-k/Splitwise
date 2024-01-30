package commands

import BookKeeper
import Utils
import exceptions.InvalidExpenseTypeException
import exceptions.NoSuchGroupException
import exceptions.NoSuchUserException
import models.Group
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

        val group: Group
        try {
            group = Utils.getGroup(cmd[4])
        } catch (e: NoSuchGroupException) {
            println(e.message)
            return
        }

        val createdBy: User
        try {
            createdBy = Utils.getUser(cmd[5])
            if(!Utils.isUserPresentInGroup(createdBy, group)) {
                throw NoSuchUserException("user not present in the group id: ${group.uid}, group name: ${group.name}")
            }
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
            paidBy = Utils.getUser(cmd[6])
            if(!Utils.isUserPresentInGroup(createdBy, group)) {
                throw NoSuchUserException("user not present in the group id: ${group.uid}, group name: ${group.name}")
            }
        } catch (e: NoSuchUserException) {
            println(e.message)
            return
        }

        expense.paidBy = paidBy

        val splits = mutableListOf<Split>()

        if(exptype == ExpenseType.EQUAL) {
            group.users.forEach { user: User ->
                val split = SplitFactory.createSplit(exptype, user, 0.0)
                splits.add(split)
            }
        }

        expense.splits = splits
        bk.addExpense(name,exptype, group, createdBy,paidBy, splits, totalAmount)
    }
}