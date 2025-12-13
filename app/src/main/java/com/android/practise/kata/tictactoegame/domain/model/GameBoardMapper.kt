package com.android.practise.kata.tictactoegame.domain.model

internal fun GameBoard.toDomainList(): List<List<Player>> {
    return List(GameConstants.BOARD_SIZE) { row ->
        List(GameConstants.BOARD_SIZE) { col ->
            getCell(row, col)
        }
    }
}