//package di.components
//
//import dagger.Component
//import data.psql.DatabaseFactory
//import data.psql.tables.mapper.ExpenseMapper
//import di.modules.PsqlModule
//
//@Component(
//    modules = [
//        PsqlModule::class
//    ]
//)
//interface SplitwiseComponent {
//    val db: DatabaseFactory
//    val expenseMapper: ExpenseMapper
//
//}