package com.android.practise.kata.tictactoegame.domain.model

import javax.inject.Inject

internal class GameBoard @Inject constructor() {

    private val size: Int = BOARD_SIZE

    companion object {
        private const val MIN_INDEX = 0
        const val BOARD_SIZE = 3
    }

    var winner: Player? = null
        private set

    private val board: MutableList<MutableList<CellState>> =
        MutableList(size) { MutableList(size) { CellState.Empty } }

    fun getCell(row: Int, col: Int): CellState {
        return board[row][col]
    }

    fun makeMove(row: Int, col: Int, player: Player): Boolean {
        if (isInvalidRange(row) || isInvalidRange(col)) {
            return false
        }
        if (board[row][col] is CellState.Filled) {
            return false
        }
        board[row][col] = CellState.Filled(player)
        return true
    }

    private fun isInvalidRange(rowOrCol: Int): Boolean = rowOrCol !in MIN_INDEX until size

    fun isGameWon(): Boolean {
        return hasHorizontalWin() || hasVerticalWin() || hasDiagonalWin()
    }

    private fun hasHorizontalWin(): Boolean {
        for (row in 0 until size) {
            val firstCell = board[row][0]
            if (firstCell is CellState.Filled){
                val isWin = (1 until size).all { col ->
                    board[row][col] == firstCell
                }

                if (isWin) {
                    winner = firstCell.player
                    return true
                }
            }
        }
        return false
    }

    private fun hasVerticalWin(): Boolean {
        for (col in 0 until size) {
            val firstCell = board[0][col]
            if (firstCell is CellState.Filled) {
                val isWin = (1 until size).all { row ->
                    board[row][col] == firstCell
                }

                if (isWin) {
                    winner = firstCell.player
                    return true
                }
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
        if (topRight is CellState.Filled) {
            val antiDiagonalWin = (1 until size).all { diagonalIndex ->
                board[diagonalIndex][size - 1 - diagonalIndex] == topRight
            }
            if (antiDiagonalWin) {
                winner = topRight.player
                return true
            }
        }
        return false
    }

    private fun hasMainDiagonalWon(): Boolean {
        val topLeft = board[0][0]
        if (topLeft is CellState.Filled) {
            val mainDiagonalWin = (1 until size).all { diagonalIndex ->
                board[diagonalIndex][diagonalIndex] == topLeft
            }
            if (mainDiagonalWin) {
                winner = topLeft.player
                return true
            }
        }
        return false
    }

    fun isBoardFull(): Boolean {
        return board.all { row -> row.all { cell -> cell is CellState.Filled } }
    }

    fun reset() {
        winner = null
        board.forEach { row -> row.fill(CellState.Empty) }


    }
}