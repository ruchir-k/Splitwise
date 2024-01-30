import exceptions.NoSuchUserException
import models.Group
import models.expenses.Expense
import models.User
import models.expenses.ExpenseFactory
import models.expenses.ExpenseType
import models.splits.Split

object BookKeeper {
    var expenses = mutableMapOf<Long, Expense>()
    var users = mutableMapOf<Long, User>()
    val groups = mutableMapOf<Long, Group>()
    var userByEmail = mutableMapOf<String, User>()
//    var balances = mutableMapOf<User, MutableMap<User, Double>>()
    var balances = mutableMapOf<Group, MutableMap<User, MutableMap<User, Double>>>()

    fun addUser(user: User) {
        users.put(user.uid, user)
        if(user.email != "") {
            userByEmail.put(user.email, user)
        }
//        balances.put(user, mutableMapOf())
    }

    fun createGroup(group: Group) {
        groups.put(group.uid, group)
        balances.put(group, mutableMapOf())
        group.users.forEach { user: User ->
            balances[group]?.put(user, mutableMapOf())
        }
    }

    fun addUserToGroup(user: User, group: Group) {
        balances[group]?.put(user, mutableMapOf())
    }

    fun addExpense(name: String, type:ExpenseType, group: Group, createdBy: User, paidBy: User,
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

    fun settleBalance(group: Group, ower: User, owedTo: User) {
        if(!balances[group]?.containsKey(ower)!! || !balances[group]?.get(ower)?.containsKey(owedTo)!!) {
            println("No expense to settle")
            return
        }
        balances[group]?.get(ower)?.remove(owedTo)
    }

    fun showBalance(group: Group, user: User) {
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

    fun showAllBalancesGroup(group: Group) {
        for(user1 in balances[group]?.keys!!) {
            showBalance(group, user1)
        }
    }

    fun showAllBalances() {
        for (group1 in groups.values) {
            println("Group name: ${group1.name} (Group id: ${group1.uid}) balances:")
            showAllBalancesGroup(group1)
        }
    }
}

//fun main() {
//    val u1: User = User("ruchir", "email", "123", "none", "abc")
//    val u2: User = User("ruchir2", "email2", "123", "none", "abc")
//    BookKeeper.addUser(u1)
//    BookKeeper.addUser(u2)
//
//    BookKeeper.showAllBalances()
//
//}