package data.psql.tables.mapper

import data.psql.tables.BalancesTable
import domain.entities.Expense
import exceptions.NoSuchExpenseException
import org.jetbrains.exposed.sql.ResultRow
import javax.inject.Inject

class ExpenseMapper
@Inject
constructor(){
    fun map(row: ResultRow?): Expense? {
        if(row == null) {
            throw NoSuchExpenseException("error in mapper")
        }
        return Expense(
            groupid = row[BalancesTable.groupid],
            oweruserid = row[BalancesTable.oweruserid],
            owedToUserId = row[BalancesTable.owedToUserid],
            amount = row[BalancesTable.amount]
        )
    }
}