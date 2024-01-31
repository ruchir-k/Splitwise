package data

import exceptions.NoSuchUserException
import domain.entities.Group
import domain.entities.Expense
import domain.entities.User
import adapter.ExpenseFactory
import domain.entities.ExpenseType
import domain.entities.Split
import domain.repo.ExpenseRepo
import domain.repo.GroupRepo
import domain.repo.UserRepo

object BookKeeper: UserRepo, GroupRepo, ExpenseRepo {
    var expenses = mutableMapOf<Long, Expense>()
    var users = mutableMapOf<Long, User>()
    val groups = mutableMapOf<Long, Group>()
    var userByEmail = mutableMapOf<String, User>()
    var balances = mutableMapOf<Group, MutableMap<User, MutableMap<User, Double>>>()

    override fun addUser(user: User) {
        users.put(user.uid, user)
        if(user.email != "") {
            userByEmail.put(user.email, user)
        }
    }

    override fun createGroup(group: Group) {
        groups.put(group.uid, group)
        balances.put(group, mutableMapOf())
        group.users.forEach { user: User ->
            balances[group]?.put(user, mutableMapOf())
        }
    }

    override fun addUserToGroup(user: User, group: Group) {
        balances[group]?.put(user, mutableMapOf())
    }

    override fun addExpense(name: String, type: ExpenseType, group: Group, createdBy: User, paidBy: User,
                            splits: MutableList<Split>, totalAmount: Double) {
        val e: Expense = ExpenseFactory.createExpense(type, name, createdBy, totalAmount)
        expenses.put(e.uid, e)
        createdBy.expenses?.add(e)
        e.splits = splits
        e.recalculate()

        if(!balances[group]?.containsKey(paidBy)!!) {
            throw NoSuchUserException("Please add the user before adding their expenses")
        }

        var userBalances: MutableMap<User, Double>
        for (s in splits) {
            val paidTo: User = s.user
            if(paidBy.equals(paidTo)) {
                continue
            }
            userBalances = balances[group]?.get(paidTo)!!
            userBalances.put(paidBy, s.amount + userBalances.getOrDefault(paidTo,0.0))
            balances[group]?.put(paidTo, userBalances)
        }

    }

    override fun settleExpense(group: Group, ower: User, owedTo: User) {
        if(!balances[group]?.containsKey(ower)!! || !balances[group]?.get(ower)?.containsKey(owedTo)!!) {
            println("No expense to settle")
            return
        }
        balances[group]?.get(ower)?.remove(owedTo)
    }

    override fun showBalance(group: Group, user: User) {
        var userBalances = balances[group]?.get(user)
        var hadBalance: Boolean = false

        for (user1 in userBalances!!.keys) {
            val amount = userBalances.get(user1)
            if(amount!! > 0) {
                println("${user.name} owes $amount to ${user1.name}")
                hadBalance = true
            }
            else if (amount < 0) {
                println("${user.name} owes ${-1*amount!!} to ${user1.name}")
                hadBalance = true
            }
        }

        if (!hadBalance) {
            println("${user.name} has no dues")
        }
    }

    override fun showAllBalancesGroup(group: Group) {
        for(user1 in balances[group]?.keys!!) {
            showBalance(group, user1)
        }
    }

    override fun showAllBalances() {
        for (group1 in groups.values) {
            println("Group name: ${group1.name} (Group id: ${group1.uid}) balances:")
            showAllBalancesGroup(group1)
        }
    }
}