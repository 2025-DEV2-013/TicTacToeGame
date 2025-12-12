package com.android.practise.kata.tictactoegame.domain.model

import app.cash.turbine.test
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameStateTest {
    private lateinit var gameState: GameState
    private lateinit var stateDomainModel: GameStateDomainModel


    @BeforeEach
    fun setUp() {
        gameState = GameState(GameBoard())
        stateDomainModel = gameState.state.value
    }

    @Test
    fun `verify initial state has domain model with default board values`(){
        val expectedBoard = List(3) { List(3) { Player.NONE } }
        assert(stateDomainModel.board == expectedBoard)
    }

    @Test
    fun `verify initial state has domain model with default current player as X`() {
        assert(stateDomainModel.currentPlayer == Player.X)
    }

    @Test
    fun `verify initial state has domain model with default winner as NONE`() {
        assert(stateDomainModel.winner == Player.NONE)

    }

    @Test
    fun `verify initial state has domain model with isGameOver as false`(){
        assert(!stateDomainModel.isGameOver)
    }

    @Test
    fun `when a player makes a move, the board values should be updated on the game state domain model`() = runTest {
        val row = 0
        val col = 0
        gameState.makeMove(row, col)
        gameState.state.test {
            val model = awaitItem()
            assertEquals(Player.X, model.board[row][col])
        }
    }

    // when player X makes a move, the next player should be updated as the current player (that means 'O should be the next player)
    @Test
    fun `when a player makes a move, the current player should be updated on the game state domain model`() = runTest {
        val row = 0
        val col = 0
        gameState.makeMove(row, col) // default initial player is X
        gameState.state.test {
            val model = awaitItem()
            assertEquals(Player.O, model.currentPlayer)
        }
    }

    @Test
    fun `Given multiple moves, each should receive alternate moves`() = runTest {
        gameState.makeMove(0, 0)
        gameState.makeMove(0, 1)
        gameState.makeMove(0, 2)
        gameState.state.test {
            val model = awaitItem()
            assertEquals(Player.X, model.board[0][0])
            assertEquals(Player.O, model.board[0][1])
            assertEquals(Player.X, model.board[0][2])
            assertEquals(Player.O, model.currentPlayer)
        }
    }

    @Test
    fun `when a player wins a game, the winner should be updated on the game state domain model`() = runTest {
        val moves = listOf(
            0 to 0,
            1 to 0,
            0 to 1,
            1 to 1,
            0 to 2)
        for((row, col) in moves) {
            gameState.makeMove(row, col)
        }
        gameState.state.test {
            val model = awaitItem()
            assertEquals(Player.X, model.winner)
        }
    }

    @Test
    fun `when a player wins a game, the game should be over`() = runTest {
        val moves = listOf(
            0 to 0,
            1 to 0,
            0 to 1,
            1 to 1,
            0 to 2)
        for((row, col) in moves) {
            gameState.makeMove(row, col)
        }
        gameState.state.test {
            val model = awaitItem()
            assert(model.isGameOver)
        }
    }

    @Test
    fun `when the game board is full, the game should be over`() = runTest {
        val moves = listOf(
            0 to 0,
            1 to 0,
            0 to 1,
            1 to 1,
            0 to 2,
            1 to 2,
            2 to 0,
            2 to 1,
            2 to 2)
        for((row, col) in moves) {
            gameState.makeMove(row, col)
        }
        gameState.state.test {
            val model = awaitItem()
            assert(model.isGameOver)
        }
    }

    @Test
    fun `when the game is over, current player should not be updated`() = runTest {
        val moves = listOf(
            0 to 0,
            1 to 0,
            0 to 1,
            1 to 1,
            0 to 2)
        for((row, col) in moves) {
            gameState.makeMove(row, col)
        }
        gameState.state.test {
            val model = awaitItem()
            assert(model.isGameOver)
            assertEquals(Player.X, model.currentPlayer)
        }
    }

    @Test
    fun `when the game is over, make move should not be allowed`() = runTest {
        val moves = listOf(
            0 to 0,
            1 to 0,
            0 to 1,
            1 to 1,
            0 to 2)
        for((row, col) in moves) {
            gameState.makeMove(row, col)
        }
        gameState.state.test {
            val model = awaitItem()
            assert(model.isGameOver)
            val isValidMove = gameState.makeMove(0, 0)
            assert(!isValidMove)
        }
    }
}