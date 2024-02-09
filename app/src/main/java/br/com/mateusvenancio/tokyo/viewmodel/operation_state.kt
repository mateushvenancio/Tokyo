package br.com.mateusvenancio.tokyo.viewmodel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import br.com.mateusvenancio.tokyo.dto.operations.CreateOperationDto
import br.com.mateusvenancio.tokyo.models.Operation
import br.com.mateusvenancio.tokyo.services.OperationsService
import java.time.LocalDate

class OperationState(
    operationService: OperationsService = OperationsService()
) {
    private val operationService = operationService

    var operations by mutableStateOf<List<Operation>>(emptyList())
        private set
    var selectedDate by mutableStateOf(LocalDate.now())
        private set
    var total by mutableStateOf(0f)
        private set

    init {
        refreshOperations()
    }

    fun changeDate(newDate: LocalDate) {
        selectedDate = newDate
        refreshOperations()
    }

    fun refreshOperations() {
        operations = emptyList()
        operations = operationService.getAll().filter {
            val sameMonth = it.date.month == selectedDate.month
            val sameYear = it.date.year == selectedDate.year
            return@filter sameMonth && sameYear
        }
        total = operations.sumOf { it.value.toDouble() }.toFloat()
    }

    fun addOperation(dto: CreateOperationDto) {
        val operation = Operation(
            title = dto.title,
            type = dto.type,
            date = dto.date,
            value = dto.value,
        )
        operations = operations + operation
    }
}