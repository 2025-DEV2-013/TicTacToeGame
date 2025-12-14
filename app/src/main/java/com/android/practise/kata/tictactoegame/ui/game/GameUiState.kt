package com.android.practise.kata.tictactoegame.ui.game

import com.android.practise.kata.tictactoegame.domain.model.GameBoard.Companion.BOARD_SIZE
import com.android.practise.kata.tictactoegame.domain.model.Player

data class GameUiState(
    val board: List<List<CellStateUiModel>> = List(BOARD_SIZE) { List(BOARD_SIZE) { CellStateUiModel.Empty} },
    val currentPlayer: Player = Player.X,
    val isGameOver: Boolean = false,
    val winner: Player = Player.NONE)