package com.android.practise.kata.tictactoegame.ui.game

import com.android.practise.kata.tictactoegame.domain.model.GameConstants
import com.android.practise.kata.tictactoegame.domain.model.GameConstants.BOARD_SIZE
import com.android.practise.kata.tictactoegame.domain.model.GameState
import com.android.practise.kata.tictactoegame.domain.model.Player
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GameViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: GameViewModel
    private var mockGameState = mockk<GameState>(relaxed = true)

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = GameViewModel(mockGameState)
    }

    @Test
    fun `verify initial state`() {
        val currentState = viewModel.state.value
        assert(currentState.board == List(BOARD_SIZE) { List(BOARD_SIZE) { Player.NONE } })
        assert(currentState.currentPlayer == Player.X)
        assert(currentState.winner == Player.NONE)
    }

    @Test
    fun `when makeMove is called, gameState makeMove should be called`() = runTest {
        viewModel.makeMove(0, 0)
        advanceUntilIdle()
        verify {mockGameState.makeMove(0, 0) }
    }

    @Test
    fun `when reset is called, gameState reset should be called`() = runTest {
        viewModel.resetGame()
        advanceUntilIdle()
        verify {mockGameState.reset() }
    }


    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

}