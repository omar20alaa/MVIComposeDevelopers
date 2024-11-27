package app.mvi_developers.presentation

import app.mvi_developers.data.Developer


data class DeveloperViewState(

    val loading: Boolean = false,
    val developers: List<Developer> = emptyList(),
    val error: String? = null

)