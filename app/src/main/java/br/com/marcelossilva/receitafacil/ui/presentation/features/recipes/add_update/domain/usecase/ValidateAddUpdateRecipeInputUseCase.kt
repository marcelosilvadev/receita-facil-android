package br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase

import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.model.AddUpdateRecipeInputValidationType

interface ValidateAddUpdateRecipeInputUseCase {
    operator fun invoke(parameters: Parameters): AddUpdateRecipeInputValidationType
    data class Parameters(
        val name: String,
        val category: String,
        val preparationTime: String,
        val preparationMode: String
    )
}

class ValidateAddUpdateRecipeInputUseCaseImpl : ValidateAddUpdateRecipeInputUseCase {
    override fun invoke(parameters: ValidateAddUpdateRecipeInputUseCase.Parameters): AddUpdateRecipeInputValidationType {
        return if (parameters.name.isEmpty() || parameters.category.isEmpty() || parameters.preparationMode.isEmpty() || parameters.preparationTime.isEmpty()) {
            AddUpdateRecipeInputValidationType.EmptyField
        } else {
            AddUpdateRecipeInputValidationType.Valid
        }
    }
}