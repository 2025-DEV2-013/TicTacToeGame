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
        if (row !in 0 until size || col !in 0 until size) {
            return false
        }
        if (board[row][col] != Player.NONE) {
            return false
        }
        board[row][col] = player
        return true
    }

    fun isGameWon(): Boolean {
        for(row in 0 until size) {
            if(board[row][0] != Player.NONE &&
                board[row][0] == board[row][1] &&
                board[row][1] == board[row][2]) {
                return true
            }
        }
        for(col in 0 until size) {
            if(board[0][col] != Player.NONE &&
                board[0][col] == board[1][col] &&
                board[1][col] == board[2][col]) {
                return true
            }
        }
        if(board[0][0] != Player.NONE &&
            board[0][0] == board[1][1] &&
            board[1][1] == board[2][2]) {
            return true
        }
        return false
    }
}