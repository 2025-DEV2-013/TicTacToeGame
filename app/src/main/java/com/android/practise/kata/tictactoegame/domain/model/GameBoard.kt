package com.android.practise.kata.tictactoegame.domain.model

import javax.inject.Inject

internal class GameBoard @Inject constructor() {

    private val size: Int = BOARD_SIZE

    companion object {
        private const val MIN_INDEX = 0
        const val BOARD_SIZE = 3
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
        for (row in 0 until size) {
            val firstCell = board[row][0]
            if (firstCell == Player.NONE) continue

            val isWin = (1 until size).all { col ->
                board[row][col] == firstCell
            }

            if (isWin) {
                winner = firstCell
                return true
            }
        }
        return false
    }

    private fun hasVerticalWin(): Boolean {
        for (col in 0 until size) {
            val firstCell = board[0][col]
            if (firstCell == Player.NONE) continue

            val isWin = (1 until size).all { row ->
                board[row][col] == firstCell
            }

            if (isWin) {
                winner = firstCell
                return true
            }
        }
        return false
    }


    private fun hasDiagonalWin(): Boolean {
        return when {
            hasMainDiagonalWon() ->  true
            else -> hasAntiDiagonalWon()
        }
    }

    private fun hasAntiDiagonalWon(): Boolean {
        val topRight = board[0][size - 1]
        if (topRight != Player.NONE) {
            val antiDiagonalWin = (1 until size).all { diagonalIndex ->
                board[diagonalIndex][size - 1 - diagonalIndex] == topRight
            }
            if (antiDiagonalWin) {
                winner = topRight
                return true
            }
        }
        return false
    }

    private fun hasMainDiagonalWon(): Boolean {
        val topLeft = board[0][0]
        if (topLeft != Player.NONE) {
            val mainDiagonalWin = (1 until size).all { diagonalIndex ->
                board[diagonalIndex][diagonalIndex] == topLeft
            }
            if (mainDiagonalWin) {
                winner = topLeft
                return true
            }
        }
        return false
    }

    fun isBoardFull(): Boolean {
        return board.all { row -> row.all { cell -> cell != Player.NONE } }
    }

    fun reset() {
        winner = Player.NONE
        board.forEach { row -> row.fill(Player.NONE) }


    }
}