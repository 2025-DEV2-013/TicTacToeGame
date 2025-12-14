package com.android.practise.kata.tictactoegame.domain.model

import com.android.practise.kata.tictactoegame.domain.model.GameBoard.Companion.BOARD_SIZE

internal fun GameBoard.toDomainList(): List<List<CellState>> {
    return List(BOARD_SIZE) { row ->
        List(BOARD_SIZE) { col ->
            if(getCell(row, col) == Player.NONE) CellState.Empty
            else CellState.Filled(getCell(row, col))
        }
    }
}