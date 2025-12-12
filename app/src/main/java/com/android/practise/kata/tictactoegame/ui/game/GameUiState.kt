package com.android.practise.kata.tictactoegame.ui.game

import com.android.practise.kata.tictactoegame.domain.model.Player

data class GameUiState(
    val board: List<List<Player>> = List(3) { List(3) { Player.NONE } },
    val currentPlayer: Player = Player.X,
    val isGameOver: Boolean = false,
    val winner: Player = Player.NONE)