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
    var initialDate by mutableStateOf(LocalDate.now())
        private set
    var finalDate by mutableStateOf(LocalDate.now())
        private set
    var total by mutableStateOf(0f)
        private set

    init {
        val today = LocalDate.now()
        initialDate = LocalDate.of(today.year, today.month, 1)
        finalDate = LocalDate.of(today.year, today.month, today.dayOfMonth)
        refreshOperations()
    }

    fun changeDates(newInitialDate: LocalDate, newFinalDate: LocalDate) {
        initialDate = newInitialDate
        finalDate = newFinalDate
        refreshOperations()
    }

//    fun changeInitialDate(newDate: LocalDate) {
//        if (newDate.isBefore(finalDate)) {
//            initialDate = newDate
//            refreshOperations()
//        }
//    }
//
//    fun changeFinalDate(newDate: LocalDate) {
//        if (newDate.isAfter(initialDate)) {
//            finalDate = newDate
//            refreshOperations()
//        }
//    }

    fun refreshOperations() {
        operations = emptyList()
        operations = operationService.getAll().filter {
            return@filter it.date.isAfter(initialDate) && (it.date.isEqual(finalDate) ||  it.date.isBefore(finalDate))
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