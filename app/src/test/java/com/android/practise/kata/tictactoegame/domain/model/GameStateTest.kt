package com.android.practise.kata.tictactoegame.domain.model

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
}