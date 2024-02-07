package adapter

import domain.usecases.external.AddExpenseCommand

class AddExpenseService
constructor(
    private val addExpenseCommand: AddExpenseCommand
): Service{
    override suspend fun invoke() {
        print("Enter the GroupId to add expense: ")
        val groupid = readln().toInt()
        print("Enter the payer UserId: ")
        val payerUserId = readln().toInt()
        print("Enter the expense amount: ")
        val amount = readln().toDouble()
        addExpenseCommand.invoke(groupid,payerUserId,amount)
    }

}