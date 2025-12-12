package com.android.practise.kata.tictactoegame.domain.model

internal fun GameBoard.toDomainList(): List<List<Player>> {
    return List(size) { row ->
        List(size) { col ->
            getCell(row, col)
        }
    }
}