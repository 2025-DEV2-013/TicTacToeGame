package com.android.practise.kata.tictactoegame.domain.model

enum class Player {
    X,
    O,
    NONE
}

class GameBoard {

    val size: Int = 3

    private val board: MutableList<MutableList<Player>> =
        MutableList(size) { MutableList(size) { Player.NONE } }

    fun getCell(row: Int, col: Int): Player {
        return board[row][col]
    }

    fun makeMove(row: Int, col: Int, player: Player): Boolean {
        if (board[row][col] != Player.NONE) {
            return false
        }
        board[row][col] = player
        return true
    }
}