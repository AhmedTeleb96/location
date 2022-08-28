package com.example.mylocation.di

import android.content.Context
import com.example.mylocation.data.repository.LocationRepositoryImp
import com.example.mylocation.domain.repository.LocationRepository
import com.example.socialmedia.main.data.models.source.GoogleLocationDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideGoogleLocationDataSource(@ApplicationContext context: Context): GoogleLocationDataSource =
        GoogleLocationDataSource(context)




    @Singleton
    @Provides
    fun providesLocationRepository(googleLocationDataSource: GoogleLocationDataSource): LocationRepository {
        return LocationRepositoryImp(googleLocationDataSource)
    }

}