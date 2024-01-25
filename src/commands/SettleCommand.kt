package commands

import BookKeeper
import exceptions.NoSuchUserException
import models.User

class SettleCommand:Command {
    override fun execute(cmd: List<String>) {
        val bk = BookKeeper
        val ower: User
        try {
            ower = Utils.getUser(cmd[1])
        } catch (e: NoSuchUserException) {
            println(e.message)
            return
        }
        val owedTo: User
        try {
            owedTo = Utils.getUser(cmd[2])
        } catch (e: NoSuchUserException) {
            println(e.message)
            return
        }

        bk.settleBalance(ower, owedTo)

    }
}