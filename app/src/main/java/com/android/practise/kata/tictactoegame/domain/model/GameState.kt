package com.android.practise.kata.tictactoegame.domain.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

internal class GameState @Inject constructor(board: GameBoard) {

    private val _state = MutableStateFlow(
        GameStateDomainModel(board.toDomainList(),
        Player.X,
          Player.NONE,
            isGameOver = false))

    val state: StateFlow<GameStateDomainModel>
        get() = _state.asStateFlow()

}