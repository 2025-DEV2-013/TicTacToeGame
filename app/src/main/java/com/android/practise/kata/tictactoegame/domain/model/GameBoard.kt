package com.android.practise.kata.tictactoegame.domain.model

import com.android.practise.kata.tictactoegame.domain.model.GameConstants.BOARD_SIZE
import javax.inject.Inject

internal class GameBoard @Inject constructor() {

    private val size: Int  = BOARD_SIZE

    companion object {
        private const val MIN_INDEX = 0
        private const val FIRST_ROW = 0
        private const val SECOND_ROW = 1
        private const val THIRD_ROW = 2
        private const val FIRST_COL = 0
        private const val SECOND_COL = 1
        private const val THIRD_COL = 2
    }

    var winner: Player = Player.NONE
        private set

    private val board: MutableList<MutableList<Player>> =
        MutableList(size) { MutableList(size) { Player.NONE } }

    fun getCell(row: Int, col: Int): Player {
        return board[row][col]
    }

    fun makeMove(row: Int, col: Int, player: Player): Boolean {
        if (row !in MIN_INDEX until size || col !in MIN_INDEX until size) {
            return false
        }
        if (board[row][col] != Player.NONE) {
            return false
        }
        board[row][col] = player
        return true
    }

    fun isGameWon(): Boolean {
        return hasHorizontalWin() || hasVerticalWin() || hasDiagonalWin()
    }

    private fun hasHorizontalWin(): Boolean {
        for(row in 0 until size) {
            if(board[row][FIRST_COL] != Player.NONE &&
                board[row][FIRST_COL] == board[row][SECOND_COL] &&
                board[row][SECOND_COL] == board[row][THIRD_COL]) {
                winner = board[row][0]
                return true
            }
        }
        return false
    }

    private fun hasVerticalWin(): Boolean {
        for(col in 0 until size) {
            if(board[FIRST_ROW][col] != Player.NONE &&
                board[FIRST_ROW][col] == board[SECOND_ROW][col] &&
                board[SECOND_ROW][col] == board[THIRD_ROW][col]) {
                winner = board[0][col]
                return true
            }
        }
        return false
    }


    private fun hasDiagonalWin(): Boolean {
        if (board[FIRST_ROW][FIRST_COL] != Player.NONE &&
            board[FIRST_ROW][FIRST_COL] == board[SECOND_ROW][SECOND_COL] &&
            board[SECOND_ROW][SECOND_COL] == board[THIRD_ROW][THIRD_COL]) {
            winner = board[FIRST_ROW][FIRST_COL]
            return true
        }

        if (board[FIRST_ROW][THIRD_COL] != Player.NONE &&
            board[FIRST_ROW][THIRD_COL] == board[SECOND_ROW][SECOND_COL] &&
            board[SECOND_ROW][SECOND_COL] == board[THIRD_ROW][FIRST_COL]) {
            winner = board[FIRST_ROW][THIRD_COL]
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