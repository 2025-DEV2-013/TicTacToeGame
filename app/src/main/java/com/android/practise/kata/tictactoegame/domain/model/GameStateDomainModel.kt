package com.android.practise.kata.tictactoegame.domain.model

data class GameStateDomainModel(
    val board: List<List<CellState>>,
    val currentPlayer: Player,
    val winner: Player,
    val isGameOver: Boolean)

