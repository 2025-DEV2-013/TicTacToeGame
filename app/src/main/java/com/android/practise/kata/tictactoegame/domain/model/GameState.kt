package com.android.practise.kata.tictactoegame.domain.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

internal class GameState @Inject constructor(val board: GameBoard) {

    private val _state = MutableStateFlow(
        GameStateDomainModel(board.toDomainList(),
        Player.X,
          Player.NONE,
            isGameOver = false))

    val state: StateFlow<GameStateDomainModel>
        get() = _state.asStateFlow()


    fun makeMove(row: Int, col: Int){
        val currentStateModel = _state.value
        board.makeMove(row, col, currentStateModel.currentPlayer)
        val isWon = board.isGameWon()
        val winner = if(isWon) currentStateModel.currentPlayer else Player.NONE
        val nextPlayer = if(currentStateModel.currentPlayer == Player.X) Player.O else Player.X
        _state.value = _state.value.copy(
            board = board.toDomainList(),
            currentPlayer = nextPlayer,
            winner = winner,
            isGameOver = _state.value.isGameOver
        )
    }

}