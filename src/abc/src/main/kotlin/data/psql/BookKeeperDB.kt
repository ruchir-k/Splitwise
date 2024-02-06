package data.psql

import data.psql.tables.UserTable
import domain.entities.ExpenseType
import domain.entities.Group
import domain.entities.Split
import domain.entities.User
import domain.repo.ExpenseRepo
import domain.repo.GroupRepo
import domain.repo.UserRepo
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.statements.InsertStatement

object BookKeeperDB : UserRepo, GroupRepo, ExpenseRepo {
    override fun addExpense(
        name: String,
        type: ExpenseType,
        group: Group,
        createdBy: User,
        paidBy: User,
        splits: MutableList<Split>,
        totalAmount: Double
    ) {
        TODO("Not yet implemented")
    }

    override fun settleExpense(group: Group, ower: User, owedTo: User) {
        TODO("Not yet implemented")
    }

    override fun showBalance(group: Group, user: User) {
        TODO("Not yet implemented")
    }

    override fun showAllBalancesGroup(group: Group) {
        TODO("Not yet implemented")
    }

    override fun showAllBalances() {
        TODO("Not yet implemented")
    }

    override suspend fun createGroup(group: Group) {
        TODO("Not yet implemented")
    }

    override suspend fun getGroup(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun addUser(name: String, email: String): User {
        var statement: InsertStatement<Number>? = null
        DatabaseFactory.dbInsertQuery {
            statement = UserTable.insert { userNew ->
                userNew[UserTable.name] = name
                userNew[UserTable.email] = email
            }
        }
        return statement?.resultedValues?.get(0).let { //mapper }
    }

    override suspend fun getUser(id: String): User {
        TODO("Not yet implemented")
    }

    override fun addUserToGroup(user: User, group: Group) {
        TODO("Not yet implemented")
    }


}