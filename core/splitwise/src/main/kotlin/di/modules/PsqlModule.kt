package di.modules

import dagger.Module
import dagger.Provides
import data.psql.DatabaseFactory
import data.psql.repos.PsqlExpenseRepo
import data.psql.repos.PsqlGroupRepo
import data.psql.repos.PsqlUserRepo
import data.psql.tables.mapper.ExpenseMapper
import data.psql.tables.mapper.GroupMapper
import data.psql.tables.mapper.UserMapper
import domain.repo.ExpenseRepo
import domain.repo.GroupRepo
import domain.repo.UserRepo
import javax.inject.Singleton

@Module
class PsqlModule {

    @Singleton
    @Provides
    fun provideDatabaseFactory(): DatabaseFactory = DatabaseFactory()

    @Singleton
    @Provides
    fun provideExpenseMapper(): ExpenseMapper = ExpenseMapper()

    @Singleton
    @Provides
    fun provideGroupMapper(): GroupMapper = GroupMapper()

    @Singleton
    @Provides
    fun provideUserMapper(): UserMapper = UserMapper()

    @Singleton
    @Provides
    fun provideExpenseRepo(db: DatabaseFactory, expenseMapper: ExpenseMapper): ExpenseRepo =
        PsqlExpenseRepo(db, expenseMapper)

    @Singleton
    @Provides
    fun provideGroupRepo(db: DatabaseFactory, groupMapper: GroupMapper): GroupRepo =
        PsqlGroupRepo(db, groupMapper)

    @Singleton
    @Provides
    fun provideUserRepo(db: DatabaseFactory, userMapper: UserMapper): UserRepo =
        PsqlUserRepo(db, userMapper)
}