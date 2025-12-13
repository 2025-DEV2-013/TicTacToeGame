package com.android.practise.kata.tictactoegame.domain.model

import com.android.practise.kata.tictactoegame.domain.model.GameConstants.BOARD_SIZE
import javax.inject.Inject

enum class Player {
    X,
    O,
    NONE
}

internal class GameBoard @Inject constructor() {

     private val size: Int  = BOARD_SIZE


    var winner: Player = Player.NONE
        private set

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
                winner = board[row][0]
                return true
            }
        }
        for(col in 0 until size) {
            if(board[0][col] != Player.NONE &&
                board[0][col] == board[1][col] &&
                board[1][col] == board[2][col]) {
                winner = board[0][col]
                return true
            }
        }
        if(board[0][0] != Player.NONE &&
            board[0][0] == board[1][1] &&
            board[1][1] == board[2][2]) {
            winner = board[0][0]
            return true
        }
        if(board[0][2] != Player.NONE &&
            board[0][2] == board[1][1] &&
            board[1][1] == board[2][0]) {
            winner = board[0][2]
            return true
        }
        return false
    }

    fun isBoardFull(): Boolean {
        return board.all { row -> row.all { cell -> cell != Player.NONE } }
    }

    fun reset(){
        winner = Player.NONE
        board.forEach { row -> row.fill(Player.NONE) }


    }
}