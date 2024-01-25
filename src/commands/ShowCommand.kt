package commands

import BookKeeper
import Utils
import exceptions.NoSuchUserException
import models.User

class ShowCommand: Command {
    override fun execute(cmd: List<String>) {
        val bk = BookKeeper
        if(cmd.size > 1) {
            val user: User
            try {
                user = Utils.getUser(cmd[1])
                bk.showBalance(user)
            } catch (e: NoSuchUserException) {
                println(e.message)
            }
        }
        else {
            bk.showAllBalances()
        }
    }
}