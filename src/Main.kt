import commands.CommandFactory
import exceptions.BadCommandFormatException
import models.User
import models.expenses.EqualExpense
import models.expenses.ExpenseType
import models.splits.EqualSplit
import models.splits.Split
import java.util.Scanner

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    var bk = BookKeeper

    bk.addUser(User("reyaan", "reyaan@gmail.com", "reyaan"))
    bk.addUser(User("shriyan", "shriyan@gmail.com", "shriyan"))
    bk.addUser(User("areana", "areana@gamil.com", "areana"))

    CommandFactory.execute("add_expense equal shopping 1000 0 1 1 0 2".split(" "))
    CommandFactory.execute("add_expense equal shopping 4000 0 2 1 0 2".split(" "))

    bk.showAllBalances()

    CommandFactory.execute("settle 0 1".split(" "))
    println("******")
    bk.showAllBalances()

//    val sc = Scanner(System.`in`)
//    while(true) {
//        println("> ")
//        val cmd = sc.nextLine().split(" ")
//        try {
//            CommandFactory.execute(cmd)
//        } catch (e: BadCommandFormatException) {
//            println(e.message)
//        }
//    }
}