package com.yuriysurzhikov.gidassistant.di

import android.content.Context
import androidx.room.Room
import com.yuriysurzhikov.gidassistant.repository.interests.local.InterestsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideInterestsDatabase(@ApplicationContext context: Context): InterestsDatabase {
        return Room.databaseBuilder(context, InterestsDatabase::class.java, INTERESTS_DB_NAME).build()
    }

    companion object {
        const val INTERESTS_DB_NAME = "interests_db"
    }

}