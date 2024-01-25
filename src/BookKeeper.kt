import exceptions.NoSuchUserException
import models.expenses.Expense
import models.User
import models.expenses.ExpenseFactory
import models.expenses.ExpenseType
import models.splits.Split

object BookKeeper {
    var expenses = mutableMapOf<Long, Expense>()
    var users = mutableMapOf<Long, User>()
    var userByEmail = mutableMapOf<String, User>()
    var balances = mutableMapOf<User, MutableMap<User, Double>>()

    fun addUser(user: User) {
        users.put(user.uid, user)
        if(user.email != "") {
            userByEmail.put(user.email, user)
        }
        balances.put(user, mutableMapOf())
    }

    fun addExpense(name: String, type:ExpenseType, createdBy: User, paidBy: User,
                   splits: MutableList<Split>, totalAmount: Double) {
        val e: Expense = ExpenseFactory.createExpense(type, name, createdBy, totalAmount)
        expenses.put(e.uid, e)
        createdBy.expenses?.add(e)
        e.splits = splits
        e.recalculate()
//        println(e)

        if(!balances.containsKey(paidBy)) {
            throw NoSuchUserException("Please add the user before adding their expenses")
        }

        var userBalances: MutableMap<User, Double>
        for (s in splits) {
            val paidTo: User = s.user
            if(paidBy.equals(paidTo)) {
                continue
            }
            userBalances = balances.get(paidTo)!!
            userBalances.put(paidBy, s.amount + userBalances.getOrDefault(paidTo,0.0))
            balances.put(paidTo, userBalances)
        }

    }

    fun settleBalance(ower: User, owedTo: User) {
        if(!balances.containsKey(ower) || !balances[ower]?.containsKey(owedTo)!!) {
            println("No expense to settle")
            return
        }
        balances[ower]?.remove(owedTo)
    }

    fun showBalance(user: User) {
        var userBalances = balances.get(user)
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

    fun showAllBalances() {
        for(user1 in balances.keys) {
            showBalance(user1)
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