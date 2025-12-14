package com.android.practise.kata.tictactoegame.domain.model

sealed class CellState {
    object Empty : CellState()
    data class Filled(val player: Player) : CellState()
}