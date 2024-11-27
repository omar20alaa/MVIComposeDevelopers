package app.mvi_developers.data


interface DeveloperRepo {

    suspend fun getDevelopers(): List<Developer>

}