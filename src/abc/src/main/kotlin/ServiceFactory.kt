import adapter.*
import data.psql.DatabaseFactory
import data.psql.repos.PsqlExpenseRepo
import data.psql.repos.PsqlGroupRepo
import data.psql.repos.PsqlUserRepo
import data.psql.tables.mapper.ExpenseMapper
import data.psql.tables.mapper.GroupMapper
import data.psql.tables.mapper.UserMapper
import domain.usecases.external.*
import exceptions.BadCommandFormatException
import javax.inject.Inject

class ServiceFactory
@Inject
constructor(
    private val addUserCommand: AddUserCommand,
    private val addUserToGroupCommand: AddUserToGroupCommand,
    private val createGroupCommand: CreateGroupCommand,
    private val addExpenseCommand: AddExpenseCommand,
    private val showBalanceByGroupCommand: ShowBalanceByGroupCommand,
    private val showBalanceByGroupUserCommand: ShowBalanceByGroupUserCommand,
    private val settleBalanceCommand: SettleBalanceCommand
    )
{


    val serviceMap = mutableMapOf<String, Service>().apply {
        put("add_user", AddUserService(addUserCommand))
        put("add_user_to_group", AddUserToGroupService(addUserToGroupCommand))
        put("create_group", CreateGroupService(createGroupCommand))
        put("add_expense", AddExpenseService(addExpenseCommand))
        put("show_group", ShowBalanceByGroupService(showBalanceByGroupCommand))
        put("show_group_user", ShowBalanceByGroupUserService(showBalanceByGroupUserCommand))
        put("settle", SettleBalanceService(settleBalanceCommand))
    }
    suspend fun invoke(cmd: String) {
        if(!serviceMap.containsKey(cmd)) {
            throw BadCommandFormatException("Unrecognized command ${cmd}")
        }
        serviceMap[cmd]?.invoke()
    }
}