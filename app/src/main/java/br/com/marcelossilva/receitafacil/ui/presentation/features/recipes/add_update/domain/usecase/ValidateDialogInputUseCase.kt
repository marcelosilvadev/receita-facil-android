package br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase

import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.model.AddUpdateRecipeInputValidationType

interface ValidateDialogInputUseCase {
    operator fun invoke(parameters: Parameters): AddUpdateRecipeInputValidationType
    data class Parameters(
        val ingredientProductName: String,
        val ingredientProductAmount: String,
    )
}

class ValidateDialogInputUseCaseImpl : ValidateDialogInputUseCase {
    override fun invoke(parameters: ValidateDialogInputUseCase.Parameters): AddUpdateRecipeInputValidationType {
        return if (parameters.ingredientProductName.isEmpty() || parameters.ingredientProductAmount.isEmpty()) {
            AddUpdateRecipeInputValidationType.EmptyDialogField
        } else {
            AddUpdateRecipeInputValidationType.Valid
        }
    }
}