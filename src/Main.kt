import data.BookKeeper
import adapter.CommandFactory
import domain.entities.User

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    var bk = BookKeeper

    bk.addUser(User("reyaan", "reyaan@gmail.com", "reyaan"))
    bk.addUser(User("shriyan", "shriyan@gmail.com", "shriyan"))
    bk.addUser(User("areana", "areana@gamil.com", "areana"))

    CommandFactory.invoke("create_group group1 0 1 2".split(" "))
    bk.showAllBalances()

    println("******")
    CommandFactory.invoke("add_expense equal shopping 1000 0 0 0".split(" "))
    bk.showAllBalances()


    println("******")
    CommandFactory.invoke("settle 0 1 0".split(" "))
    bk.showAllBalances()

    println("******")
    CommandFactory.invoke("create_group group1 1 2".split(" "))
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