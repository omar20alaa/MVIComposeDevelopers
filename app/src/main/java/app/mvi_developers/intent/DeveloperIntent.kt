package app.mvi_developers.intent



sealed class DeveloperIntent {

    data object FetchDevelopers : DeveloperIntent()

}