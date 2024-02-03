package br.com.mateusvenancio.tokyo.models

import java.math.BigDecimal
import java.time.LocalDate

enum class OperationType {
    INCOME, OUTCOME
}

data class Operation(
    val type: OperationType,
    val title: String,
    val value: BigDecimal,
    val date: LocalDate,
)