package br.com.mateusvenancio.tokyo.services

import br.com.mateusvenancio.tokyo.models.Operation
import br.com.mateusvenancio.tokyo.models.OperationType
import java.math.BigDecimal
import java.time.LocalDate

class OperationsService {
    fun getAll(): List<Operation> {
        return listOf<Operation>(
            Operation(
                type = OperationType.OUTCOME,
                title = "Lanche",
                value = BigDecimal("22.35"),
                date = LocalDate.now(),
            ),
            Operation(
                type = OperationType.INCOME,
                title = "Salário",
                value = BigDecimal("2000"),
                date = LocalDate.of(2024, 1, 30),
            ),
            Operation(
                type = OperationType.INCOME,
                title = "VA",
                value = BigDecimal("600"),
                date = LocalDate.of(2024, 1, 31),
            ),
            Operation(
                type = OperationType.OUTCOME,
                title = "Sabão",
                value = BigDecimal("0.9"),
                date = LocalDate.now(),
            ),
            Operation(
                type = OperationType.OUTCOME,
                title = "Algum título bem grande pra eu ver como fica no display",
                value = BigDecimal("4.20"),
                date = LocalDate.now(),
            ),
            Operation(
                type = OperationType.OUTCOME,
                title = "Sorvete",
                value = BigDecimal("12.4"),
                date = LocalDate.now(),
            ),
        );
    }
}