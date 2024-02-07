import exceptions.BadCommandFormatException
import java.util.*


suspend fun main() {

    val serviceFactory = ServiceFactory
    val sc = Scanner(System.`in`)
    while(true) {
        println("> ")
        val cmd = sc.nextLine()
        try {
            serviceFactory.invoke(cmd)
        } catch (e: BadCommandFormatException) {
            println(e.message)
        }
    }

}