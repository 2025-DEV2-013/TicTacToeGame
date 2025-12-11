package com.android.practise.kata.tictactoegame.domain.model

import org.junit.jupiter.api.Assertions.assertEquals
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
        assertEquals(3, gameBoard.size)
    }

    @Test
    fun `when game starts, all cells should be empty`() {
        for (row in 0 until gameBoard.size) {
            for (col in 0 until gameBoard.size) {
                assertEquals(Player.NONE, gameBoard.getCell(row, col))
            }
        }
    }

    @Test
    fun `when a player makes a move, the cell should be occupied by that player`() {
        val playerX = Player.X
        val row = 0
        val col = 0
        gameBoard.makeMove(row, col, playerX)
        assertEquals(playerX, gameBoard.getCell(row, col))
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
        assertEquals(playerX, gameBoard.getCell(row, col))
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
        assertEquals(Player.X, gameBoard.getCell(0, 0))
        assertEquals(Player.X, gameBoard.getCell(0, 1))
        assertEquals(Player.X, gameBoard.getCell(0, 2))
        assert(gameBoard.isGameWon())
    }

}