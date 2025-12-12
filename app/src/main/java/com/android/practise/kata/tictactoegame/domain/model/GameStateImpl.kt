package com.android.practise.kata.tictactoegame.domain.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class GameStateImpl @Inject constructor(val board: GameBoard) : GameState {

    private val _state = MutableStateFlow(
        GameStateDomainModel(board.toDomainList(),
        Player.X,
          Player.NONE,
            isGameOver = false))

    override val state: StateFlow<GameStateDomainModel>
        get() = _state.asStateFlow()


    override fun makeMove(row: Int, col: Int): Boolean {
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

    override fun reset(){
        board.reset()
        _state.update {
            it.copy(
                board = board.toDomainList(),
                currentPlayer = Player.X,
                winner = Player.NONE,
                isGameOver = false
            )
        }
    }

}