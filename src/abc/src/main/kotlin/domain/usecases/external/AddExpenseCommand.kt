package domain.usecases.external

import data.BookKeeper
import utils.Utils
import exceptions.InvalidExpenseTypeException
import exceptions.NoSuchGroupException
import exceptions.NoSuchUserException
import domain.entities.Group
import domain.entities.User
import domain.repo.Command
import domain.entities.Expense
import adapter.ExpenseFactory
import domain.entities.ExpenseType
import domain.entities.Split
import adapter.SplitFactory
import java.util.*

class AddExpenseCommand: Command {
    override suspend fun invoke(cmd: List<String>) {
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
                throw NoSuchUserException("user not present in the group id: ${group.id}, group name: ${group.name}")
            }
        } catch (e: NoSuchUserException) {
            println(e.message)
            return
        }

        val expense: Expense
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
                throw NoSuchUserException("user not present in the group id: ${group.id}, group name: ${group.name}")
            }
        } catch (e: NoSuchUserException) {
            println(e.message)
            return
        }

        expense.paidBy = paidBy

        val splits = mutableListOf<Split>()

        if(exptype == ExpenseType.EQUAL) {
            val splitAmount = totalAmount/group.users.size
            group.users.forEach { user: User ->
                val split = SplitFactory.createSplit(exptype, user, splitAmount, 0.0)
                splits.add(split)
            }
        }

        expense.splits = splits
        bk.addExpense(name,exptype, group, createdBy,paidBy, splits, totalAmount)
    }
}