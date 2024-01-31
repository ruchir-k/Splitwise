package adapter

import domain.repo.Command
import domain.usecases.external.*
import exceptions.BadCommandFormatException

object CommandFactory: Command {
    val commandMap = mutableMapOf<String, Command>().apply {
        put("add_user", AddUserCommand())
        put("add_user_to_group", AddUserToGroupCommand())
        put("create_group", CreateGroupCommand())
        put("add_expense", AddExpenseCommand())
        put("show", ShowCommand())
        put("settle", SettleCommand())
    }
    override fun invoke(cmd: List<String>) {
        if(!commandMap.containsKey(cmd[0])) {
            throw BadCommandFormatException("Unrecognized command ${cmd[0]}")
        }
        commandMap[cmd[0]]?.invoke(cmd)
    }
}