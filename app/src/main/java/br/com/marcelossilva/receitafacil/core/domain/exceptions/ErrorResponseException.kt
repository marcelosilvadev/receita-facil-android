package br.com.marcelossilva.receitafacil.core.domain.exceptions

import br.com.marcelossilva.receitafacil.core.data.remote.responses.ErrorResponse

class ErrorResponseException(val error: ErrorResponse): RuntimeException() {

}