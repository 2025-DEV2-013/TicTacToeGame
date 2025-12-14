package com.android.practise.kata.tictactoegame.domain.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `next player should alternate between X and O`() {
        assertEquals(Player.O, Player.X.next())
        assertEquals(Player.X, Player.O.next())
    }

}