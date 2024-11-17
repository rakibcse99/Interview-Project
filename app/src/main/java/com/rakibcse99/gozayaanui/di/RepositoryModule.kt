package com.rakibcse99.gozayaanui.di

import com.rakibcse99.gozayaanui.repository.RecommendedRepository
import com.rakibcse99.gozayaanui.repository.RecommendedRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun provideCharacterRepository(preference: RecommendedRepositoryImpl): RecommendedRepository
}