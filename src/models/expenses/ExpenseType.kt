package models.expenses

import com.sun.jdi.InvalidTypeException

enum class ExpenseType(val asString: String) {
    EQUAL("equal"),
    PERCENT("percent"),
    EXACT("exact")
}

fun fromString(type: String):ExpenseType {
    return ExpenseType.values().firstOrNull { it.asString == type} ?: throw InvalidTypeException()
}