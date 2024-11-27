package app.mvi_developers.data

import app.mvi_developers.domain.DeveloperRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideDeveloperRepo(impl: DeveloperRepoImpl): DeveloperRepo = impl

}