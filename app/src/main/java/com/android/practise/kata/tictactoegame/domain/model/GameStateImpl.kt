package com.android.practise.kata.tictactoegame.domain.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class GameStateImpl @Inject constructor(private val board: GameBoard) : GameState {

    private val _state = MutableStateFlow(
        GameStateDomainModel(board.toDomainList(),
        Player.X,
          null,
            isGameOver = false))

    override val state: StateFlow<GameStateDomainModel>
        get() = _state.asStateFlow()


    override fun makeMove(row: Int, col: Int): Boolean {
        val currentStateModel = _state.value
        if(currentStateModel.isGameOver) return false
        if(board.makeMove(row, col, currentStateModel.currentPlayer)){
            updateGameState(currentStateModel)
            return true
        }
        return false
    }

    private fun updateGameState(currentStateModel: GameStateDomainModel) {
        val isWon = board.isGameWon()
        val isDraw = board.isBoardFull()
        val isGameOver = isWon || isDraw
        val winner = if (isWon) currentStateModel.currentPlayer else null
        val nextPlayer = identifyNextPlayer(isGameOver, currentStateModel)

        _state.update {
            it.copy(
                board = board.toDomainList(),
                currentPlayer = nextPlayer,
                winner = winner,
                isGameOver = isGameOver
            )
        }
    }

    private fun identifyNextPlayer(
        isGameOver: Boolean,
        currentStateModel: GameStateDomainModel
    ): Player = if (!isGameOver) currentStateModel.currentPlayer.next() else currentStateModel.currentPlayer


    override fun reset(){
        board.reset()
        _state.update {
            it.copy(
                board = board.toDomainList(),
                currentPlayer = Player.X,
                winner = null,
                isGameOver = false
            )
        }
    }

}