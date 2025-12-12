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


    fun makeMove(row: Int, col: Int): Boolean {
        val currentStateModel = _state.value
        if(currentStateModel.isGameOver) return false
        if(board.makeMove(row, col, currentStateModel.currentPlayer)){
            val isWon = board.isGameWon()
            val isDraw = board.isBoardFull()
            val isGameOver = isWon || isDraw
            val winner = if(isWon) currentStateModel.currentPlayer else Player.NONE
            val nextPlayer = if(!isGameOver){
                if(currentStateModel.currentPlayer == Player.X) Player.O else Player.X
            } else {
                currentStateModel.currentPlayer
            }

            _state.value = _state.value.copy(
                board = board.toDomainList(),
                currentPlayer = nextPlayer,
                winner = winner,
                isGameOver = isGameOver
            )
            return true
        }
        return false

    }

}