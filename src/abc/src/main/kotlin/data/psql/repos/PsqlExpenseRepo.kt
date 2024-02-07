package data.psql.repos

import data.psql.DatabaseFactory
import data.psql.tables.BalancesTable
import data.psql.tables.mapper.ExpenseMapper
import domain.entities.Expense
import domain.repo.ExpenseRepo
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class PsqlExpenseRepo
constructor(
    val db: DatabaseFactory,
    val expenseMapper: ExpenseMapper
): ExpenseRepo{
    override suspend fun addExpense(groupid: Int, owedToUserId: Int, oweruserid: Int, amount: Double) {
        db.dbQuery {
            BalancesTable.insert { balanceNew ->
                balanceNew[BalancesTable.groupid] = groupid
                balanceNew[BalancesTable.owedToUserid] = owedToUserId
                balanceNew[BalancesTable.oweruserid] = oweruserid
                balanceNew[BalancesTable.amount] = amount
            }
        }
    }

    override suspend fun getExpense(groupid: Int, owedToUserId: Int, oweruserid: Int): Expense =
        db.dbQuery {
            BalancesTable.select {
                (BalancesTable.groupid.eq(groupid)) and
                        (BalancesTable.oweruserid.eq(oweruserid)) and
                                (BalancesTable.owedToUserid.eq(owedToUserId))
            }.map {
                expenseMapper.map(it)!!
            }.single()
        }

    override suspend fun updateExpense(groupid: Int, owedToUserId: Int, oweruserid: Int, amount: Double) {
        db.dbQuery {
            BalancesTable.update ({
                (BalancesTable.groupid.eq(groupid)) and
                        ((BalancesTable.oweruserid.eq(oweruserid)) and (BalancesTable.owedToUserid.eq(owedToUserId)))
            }) {
                it[BalancesTable.amount] = amount
            }
        }
    }

    override suspend fun settleExpense(groupid: Int, owedToUserId: Int, oweruserid: Int) {
        db.dbQuery {
            BalancesTable.deleteWhere {
                (BalancesTable.groupid.eq(groupid)) and
                        (BalancesTable.owedToUserid.eq(owedToUserId)) and
                        (BalancesTable.oweruserid.eq(oweruserid))
            }
        }
    }

    override suspend fun isValidExpense(groupid: Int, owedToUserId: Int, oweruserid: Int): Boolean =
        db.dbQuery {
            BalancesTable.select {
                (BalancesTable.groupid.eq(groupid)) and
                        ((BalancesTable.oweruserid.eq(oweruserid)) and (BalancesTable.owedToUserid.eq(owedToUserId)))
            }.count() > 0
        }

    override suspend fun getBalanceByGroupUser(groupid: Int, userid: Int): List<Expense> =
        db.dbQuery {
            BalancesTable.select {
                (BalancesTable.groupid.eq(groupid)) and
                        ((BalancesTable.oweruserid.eq(userid)) or (BalancesTable.owedToUserid.eq(userid)))
            }.map {
                expenseMapper.map(it)!!
            }
        }


    override suspend fun getBalanceByGroup(groupid: Int): List<Expense> =
        db.dbQuery {
            BalancesTable.select {
                BalancesTable.groupid.eq(groupid)
            }.map {
                expenseMapper.map(it)!!
            }
        }


    override suspend fun getAllBalances(): List<Expense> =
        db.dbQuery {
            BalancesTable.selectAll().map {
                expenseMapper.map(it)!!
            }
        }

}