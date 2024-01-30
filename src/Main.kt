import commands.CommandFactory
import models.Group
import models.User

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    var bk = BookKeeper

    bk.addUser(User("reyaan", "reyaan@gmail.com", "reyaan"))
    bk.addUser(User("shriyan", "shriyan@gmail.com", "shriyan"))
    bk.addUser(User("areana", "areana@gamil.com", "areana"))

    CommandFactory.execute("create_group group1 0 1 2".split(" "))
    bk.showAllBalances()

    println("******")
    CommandFactory.execute("add_expense equal shopping 1000 0 0 0".split(" "))
    bk.showAllBalances()


    println("******")
    CommandFactory.execute("settle 0 1 0".split(" "))
    bk.showAllBalances()

    println("******")
    CommandFactory.execute("create_group group1 1 2".split(" "))
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