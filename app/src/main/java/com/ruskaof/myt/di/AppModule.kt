package com.ruskaof.myt.di

import android.content.Context
import androidx.room.Room
import com.ruskaof.myt.data.local.repository.MainDb
import com.ruskaof.myt.data.local.repository.PlansDao
import com.ruskaof.myt.data.local.repository.PlansRepositoryImpl
import com.ruskaof.myt.domain.repository.PlansRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        MainDb::class.java,
        "main_data_base"
    ).build()

    @Provides
    @Singleton
    fun providePlansRepository(plansDao: PlansDao) : PlansRepository {
        return PlansRepositoryImpl(plansDao)
    }

    @Singleton
    @Provides
    fun provideYourDao(db: MainDb) = db.getPlansDao()
}