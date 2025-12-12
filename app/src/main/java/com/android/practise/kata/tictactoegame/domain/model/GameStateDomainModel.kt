package com.android.practise.kata.tictactoegame.domain.model

data class GameStateDomainModel(
    val board: List<List<Player>>,
    val currentPlayer: Player,
    val winner: Player)

