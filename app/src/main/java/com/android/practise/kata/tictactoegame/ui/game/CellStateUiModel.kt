package com.android.practise.kata.tictactoegame.ui.game

import com.android.practise.kata.tictactoegame.domain.model.Player

sealed class CellStateUiModel {
    object Empty : CellStateUiModel()
    data class Filled(val player : Player) : CellStateUiModel()
}