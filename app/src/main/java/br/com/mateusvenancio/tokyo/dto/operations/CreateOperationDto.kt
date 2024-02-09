package br.com.mateusvenancio.tokyo.dto.operations

import br.com.mateusvenancio.tokyo.models.OperationType
import java.math.BigDecimal
import java.time.LocalDate

data class CreateOperationDto(
    val type: OperationType,
    val title: String,
    val value: BigDecimal,
    val date: LocalDate,
)
