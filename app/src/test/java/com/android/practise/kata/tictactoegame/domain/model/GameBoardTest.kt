package com.android.practise.kata.tictactoegame.domain.model

import com.android.practise.kata.tictactoegame.domain.model.GameBoard.Companion.BOARD_SIZE
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameBoardTest {

    private lateinit var gameBoard: GameBoard

    @BeforeEach
    fun setUp() {
        gameBoard = GameBoard()
    }

    @Test
    fun `verify board size should be 3`() {
        assertEquals(3, BOARD_SIZE)
    }

    @Test
    fun `when game starts, all cells should be empty`() {
        for (row in 0 until BOARD_SIZE) {
            for (col in 0 until BOARD_SIZE) {
                assertEquals(CellState.Empty, gameBoard.getCell(row, col))
            }
        }
    }

    @Test
    fun `when a player makes a move, the cell should be occupied by that player`() {
        val playerX = Player.X
        val row = 0
        val col = 0
        gameBoard.makeMove(row, col, playerX)
        assertEquals(CellState.Filled(playerX), gameBoard.getCell(row, col))
    }

    @Test
    fun `when a player makes a move to an occupied cell, the move should be ignored`() {
        val playerX = Player.X
        val row = 0
        val col = 0
        val result1 = gameBoard.makeMove(row, col, playerX)
        assert(result1)
        val result2 = gameBoard.makeMove(row, col, Player.O)
        assert(!result2)
        assertEquals(CellState.Filled(playerX), gameBoard.getCell(row, col))
    }

    @Test
    fun `when a player makes a move to an invalid cell, the move should be ignored`() {
        val playerX = Player.X
        val row = 3
        val col = 3
        val result = gameBoard.makeMove(row, col, playerX)
        assert(!result)
    }

    @Test
    fun `when player makes move to valid cells, all moves should be recorded`() {
        val moves = listOf(
            0 to 0,
            0 to 1,
            0 to 2,
            1 to 0,
            1 to 1,
            1 to 2,
            2 to 0,
            2 to 2,
            2 to 1
        )
       for((row, col) in moves) {
          val result = gameBoard.makeMove(row, col, Player.X)
           assert(result)
       }

    }

    @Test
    fun `when all cells are filled, no more moves should be allowed`() {
        val moves = listOf(
            0 to 0,
            0 to 1,
            0 to 2,
            1 to 0,
            1 to 1,
            1 to 2,
            2 to 0,
            2 to 2,
            2 to 1
        )
        for((row, col) in moves) {
            val result = gameBoard.makeMove(row, col, Player.X)
            assert(result)
        }
        val result = gameBoard.makeMove(0, 0, Player.O)
        assert(!result)
    }

    @Test
    fun `when a player gets all cells in a horizontal row, the game is won`() {
        val moves = listOf(
            0 to 0,
            0 to 1,
            0 to 2
        )
        for((row, col) in moves) {
            val result = gameBoard.makeMove(row, col, Player.X)
            assert(result)
        }
        assertEquals(CellState.Filled(Player.X), gameBoard.getCell(0, 0))
        assertEquals(CellState.Filled(Player.X), gameBoard.getCell(0, 1))
        assertEquals(CellState.Filled(Player.X), gameBoard.getCell(0, 2))
        assert(gameBoard.isGameWon())
    }

    @Test
    fun `when a player gets all cells in a vertical column, the game is won`() {
        val moves = listOf(
            0 to 0,
            1 to 0,
            2 to 0
        )
        for((row, col) in moves) {
            val result = gameBoard.makeMove(row, col, Player.O)
            assert(result)
        }
        assertEquals(CellState.Filled(Player.O), gameBoard.getCell(0, 0))
        assertEquals(CellState.Filled(Player.O), gameBoard.getCell(1, 0))
        assertEquals(CellState.Filled(Player.O), gameBoard.getCell(2, 0))
        assert(gameBoard.isGameWon())

    }

    @Test
    fun `when a player does not get all cells in a row, the game is not won `() {
        gameBoard.makeMove(0, 0, Player.X)
        gameBoard.makeMove(0, 1, Player.X)
        gameBoard.makeMove(0, 2, Player.O)
        assert(!gameBoard.isGameWon())
    }

    @Test
    fun `when a player does not get all cells in a column, the game is not won`() {
        gameBoard.makeMove(0, 0, Player.X)
        gameBoard.makeMove(1, 0, Player.X)
        gameBoard.makeMove(2, 0, Player.O)
        assert(!gameBoard.isGameWon())
    }

    @Test
    fun `when a player gets all cells in a diagonal, the game is won`() {
        val moves = listOf(
            0 to 0,
            1 to 1,
            2 to 2
        )
        for((row, col) in moves) {
            val result = gameBoard.makeMove(row, col, Player.X)
            assert(result)
        }
        assertEquals(CellState.Filled(Player.X), gameBoard.getCell(0, 0))
        assertEquals(CellState.Filled(Player.X), gameBoard.getCell(1, 1))
        assertEquals(CellState.Filled(Player.X), gameBoard.getCell(2, 2))
        assert(gameBoard.isGameWon())
    }

    @Test
    fun `when a player gets all cells in a second-diagonal, the game is won`() {
        val moves = listOf(
            0 to 2,
            1 to 1,
            2 to 0
        )
        for((row, col) in moves) {
            val result = gameBoard.makeMove(row, col, Player.X)
            assert(result)
        }
        assertEquals(CellState.Filled(Player.X), gameBoard.getCell(0, 2))
        assertEquals(CellState.Filled(Player.X), gameBoard.getCell(1, 1))
        assertEquals(CellState.Filled(Player.X), gameBoard.getCell(2, 0))
        assert(gameBoard.isGameWon())

    }

    @Test
    fun `when a player does not get all cells in a diagonal, the game is not won`() {
        gameBoard.makeMove(0, 0, Player.X)
        gameBoard.makeMove(0, 1, Player.X)
        gameBoard.makeMove(0, 2, Player.O)
        assert(!gameBoard.isGameWon())
    }

    @Test
    fun `when a player does not get all cells in a second-diagonal, the game is not won`() {
        gameBoard.makeMove(0, 2, Player.X)
        gameBoard.makeMove(1, 1, Player.X)
        gameBoard.makeMove(2, 0, Player.O)
        assert(!gameBoard.isGameWon())
    }

    @Test
    fun `when a player wins a game, the same player should be the winner`() {
        val moves = listOf(
            0 to 0,
            0 to 1,
            0 to 2)
        for((row, col) in moves) {
            val result = gameBoard.makeMove(row, col, Player.X)
            assert(result)
        }
        assert(gameBoard.isGameWon())
        assertEquals(Player.X, gameBoard.winner)
    }

    @Test
    fun `when all cells are filled, and no player has won, the winner should be NONE`() {
        val moves = listOf(
            Triple(0, 0, Player.X), Triple(0, 1, Player.O),
            Triple(0, 2, Player.X), Triple(1, 0, Player.O),
            Triple(1, 1, Player.X), Triple(1, 2, Player.O),
            Triple(2, 0, Player.O), Triple(2, 1, Player.X),
            Triple(2, 2, Player.O)
        )
        for ((row, col, player) in moves) {
            gameBoard.makeMove(row, col, player)
        }
        assert(!gameBoard.isGameWon())
        assertNull(gameBoard.winner)
    }

    @Test
    fun `when all cells are filled, the board should be full`(){
        val moves = listOf(
            Triple(0, 0, Player.X), Triple(0, 1, Player.O),
            Triple(0, 2, Player.X), Triple(1, 0, Player.O),
            Triple(1, 1, Player.X), Triple(1, 2, Player.O),
            Triple(2, 0, Player.O), Triple(2, 1, Player.X),
            Triple(2, 2, Player.O)
        )
        for ((row, col, player) in moves) {
            gameBoard.makeMove(row, col, player)
        }
        assert(gameBoard.isBoardFull())
    }

    @Test
    fun `when all cells are partially filled, the board should not be full`(){
        val moves = listOf(
            Triple(0, 0, Player.X), Triple(0, 1, Player.O),
            Triple(0, 2, Player.X), Triple(1, 0, Player.O),
            Triple(1, 1, Player.X), Triple(1, 2, Player.O),
            Triple(2, 0, Player.O), Triple(2, 1, Player.X)
        )
        for ((row, col, player) in moves) {
            gameBoard.makeMove(row, col, player)
        }
        assert(!gameBoard.isBoardFull())

    }

    @Test
    fun `when game is reset, all cells should be empty`() {
        val moves = listOf(
            Triple(0, 0, Player.X), Triple(0, 1, Player.O),
            Triple(0, 2, Player.X), Triple(1, 0, Player.O),
            Triple(1, 1, Player.X), Triple(1, 2, Player.O),
            Triple(2, 0, Player.O), Triple(2, 1, Player.X)
        )
        for ((row, col, player) in moves) {
            gameBoard.makeMove(row, col, player)
        }
        gameBoard.reset()
        for (row in 0 until BOARD_SIZE) {
            for (col in 0 until BOARD_SIZE) {
                assertEquals(CellState.Empty, gameBoard.getCell(row, col))
            }
        }
    }

}