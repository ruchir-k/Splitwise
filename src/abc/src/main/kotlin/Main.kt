import data.BookKeeper
import adapter.CommandFactory
import com.zaxxer.hikari.HikariDataSource
import domain.entities.User
import java.sql.DriverManager


suspend fun main() {
//    var bk = BookKeeper
//
//    bk.addUser(User("reyaan", "reyaan@gmail.com", "reyaan",0))
//    bk.addUser(User("shriyan", "shriyan@gmail.com", "shriyan",1))
//    bk.addUser(User("areana", "areana@gamil.com", "areana",2))
//
//    CommandFactory.invoke("create_group group1 0 1 2".split(" "))
//    bk.showAllBalances()
//
//    println("******")
//    CommandFactory.invoke("add_expense equal shopping 1000 0 0 0".split(" "))
//    bk.showAllBalances()
//
//
//    println("******")
//    CommandFactory.invoke("settle 0 1 0".split(" "))
//    bk.showAllBalances()
//
//    println("******")
//    CommandFactory.invoke("create_group group1 1 2".split(" "))
//    bk.showAllBalances()

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

    // create a dataSource
    val dataSource = HikariDataSource()

    // set the jdbcUrl
    dataSource.jdbcUrl = "jdbc:postgresql://localhost:5432/splitwise"

    // set the username
    dataSource.username = "postgres"

    // set the password
    dataSource.password = "1234"

    println(dataSource.maximumPoolSize)

    // get a connection
    val connection = dataSource.connection


    // prints true if the connection is valid
    println(connection.isValid(0))

    // the query is only prepared not executed
    val query = connection.prepareStatement("SELECT * FROM users")

    // the query is executed and results are fetched
    val result = query.executeQuery()

    // an empty list for holding the results
    val users = mutableListOf<User>()

    while(result.next()){

        // getting the value of the id column
        val id = result.getInt("id")

        // getting the value of the name column
        val name = result.getString("name")

        /*
        constructing a User object and
        putting data into the list
         */
        users.add(User(name, "email", "hashedPass", id.toLong()))
    }
    /*
    [User(id=1, name=Kohli), User(id=2, name=Rohit),
    User(id=3, name=Bumrah), User(id=4, name=Dhawan)]
     */
    println(users)
}