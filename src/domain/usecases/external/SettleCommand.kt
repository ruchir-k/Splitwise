package domain.usecases.external

import data.BookKeeper
import exceptions.NoSuchGroupException
import exceptions.NoSuchUserException
import domain.entities.Group
import domain.entities.User
import domain.repo.Command
import utils.Utils

class SettleCommand: Command {
    override fun invoke(cmd: List<String>) {
        val bk = BookKeeper
        val group: Group
        try {
            group = Utils.getGroup(cmd[1])
        } catch (e: NoSuchGroupException) {
            println(e.message)
            return
        }
        val ower: User
        try {
            ower = Utils.getUser(cmd[2])
        } catch (e: NoSuchUserException) {
            println(e.message)
            return
        }
        val owedTo: User
        try {
            owedTo = Utils.getUser(cmd[3])
        } catch (e: NoSuchUserException) {
            println(e.message)
            return
        }

        bk.settleExpense(group, ower, owedTo)

    }
}