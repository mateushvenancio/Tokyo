package br.com.mateusvenancio.tokyo.core

import br.com.mateusvenancio.tokyo.viewmodel.OperationState

class ServiceLocator {
    companion object {
        val operationsState: OperationState = OperationState()
    }
}