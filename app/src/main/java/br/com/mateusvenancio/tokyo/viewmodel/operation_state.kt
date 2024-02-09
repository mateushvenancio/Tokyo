package br.com.mateusvenancio.tokyo.viewmodel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import br.com.mateusvenancio.tokyo.dto.operations.CreateOperationDto
import br.com.mateusvenancio.tokyo.models.Operation
import br.com.mateusvenancio.tokyo.services.OperationsService

class OperationState(
    operationService: OperationsService = OperationsService()
) {
    private val operationService = operationService
    var operations by mutableStateOf<List<Operation>>(emptyList())

    init {
        refreshOperations()
    }

    fun refreshOperations() {
        try {
            operations = emptyList()
            operations = operationService.getAll()
        } catch (ex: Exception) {
            Log.d("CAIUNAEX", "Deu erro aqui: $ex")
        }
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