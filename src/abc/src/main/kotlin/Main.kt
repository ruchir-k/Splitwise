import exceptions.BadCommandFormatException
import java.util.*


suspend fun main() {

    val rootComponent = DaggerRootComponent.create()
    val serviceFactory = rootComponent.serviceFactory

    val sc = Scanner(System.`in`)
    while(true) {
        print("Enter command >> ")
        val cmd = sc.nextLine()
        try {
            serviceFactory.invoke(cmd)
        } catch (e: BadCommandFormatException) {
            println(e.message)
        }
    }

}