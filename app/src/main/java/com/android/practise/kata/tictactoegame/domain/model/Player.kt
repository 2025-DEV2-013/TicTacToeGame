package com.android.practise.kata.tictactoegame.domain.model

enum class Player {
    X,
    O;

    fun next() : Player =
        when(this){
            X -> O
            O -> X
        }
}


