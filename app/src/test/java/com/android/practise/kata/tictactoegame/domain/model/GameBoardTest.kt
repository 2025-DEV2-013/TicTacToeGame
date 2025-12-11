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

}