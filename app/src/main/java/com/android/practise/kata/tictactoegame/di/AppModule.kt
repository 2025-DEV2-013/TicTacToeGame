package com.android.practise.kata.tictactoegame.di

import com.android.practise.kata.tictactoegame.domain.model.GameState
import com.android.practise.kata.tictactoegame.domain.model.GameStateImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    internal abstract fun bindGameState(gameStateImpl: GameStateImpl): GameState
}