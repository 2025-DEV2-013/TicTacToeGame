package com.android.practise.kata.tictactoegame.domain.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class GameState {

    private val board = GameBoard()

    private val _state = MutableStateFlow(GameStateDomainModel(board.toDomainList()))

    val state: StateFlow<GameStateDomainModel>
        get() = _state.asStateFlow()

}