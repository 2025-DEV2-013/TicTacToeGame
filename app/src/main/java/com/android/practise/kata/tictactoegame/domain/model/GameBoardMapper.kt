package com.android.practise.kata.tictactoegame.domain.model

import com.android.practise.kata.tictactoegame.domain.model.GameBoard.Companion.BOARD_SIZE

internal fun GameBoard.toDomainList(): List<List<Player>> {
    return List(BOARD_SIZE) { row ->
        List(BOARD_SIZE) { col ->
            getCell(row, col)
        }
    }
}