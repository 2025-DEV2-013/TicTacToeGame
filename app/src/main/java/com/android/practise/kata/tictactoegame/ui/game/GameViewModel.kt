package com.android.practise.kata.tictactoegame.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.practise.kata.tictactoegame.domain.model.CellState
import com.android.practise.kata.tictactoegame.domain.model.GameState
import com.android.practise.kata.tictactoegame.domain.model.GameStateDomainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(private val gameState: GameState) : ViewModel() {

    val state : StateFlow<GameUiState> = gameState.state.map { state ->
        GameUiState(
            board = mapToUiModel(state),
            currentPlayer = state.currentPlayer,
            isGameOver = state.isGameOver,
            winner = state.winner
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = GameUiState()
    )

    private fun mapToUiModel(state: GameStateDomainModel): List<List<CellStateUiModel>> =
        state.board.map { row ->
            row.map { cell ->
                when (cell) {
                    is CellState.Empty -> CellStateUiModel.Empty
                    is CellState.Filled -> CellStateUiModel.Filled(cell.player)
                }
            }
        }

    fun makeMove(row : Int, col : Int) {
        viewModelScope.launch {
            gameState.makeMove(row, col)
        }
    }

    fun resetGame() {
        viewModelScope.launch {
            gameState.reset()
        }
    }

}