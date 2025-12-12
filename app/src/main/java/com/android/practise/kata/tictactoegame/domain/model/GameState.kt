package com.android.practise.kata.tictactoegame.domain.model

import kotlinx.coroutines.flow.StateFlow

interface GameState {
    val state : StateFlow<GameStateDomainModel>
    fun makeMove(row : Int, col : Int) : Boolean
    fun reset()

}