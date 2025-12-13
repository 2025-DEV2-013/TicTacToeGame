package com.android.practise.kata.tictactoegame.ui.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.practise.kata.tictactoegame.R
import com.android.practise.kata.tictactoegame.domain.model.Player


@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    board: List<List<Player>>,
    currentPlayer: Player,
    isGameOver: Boolean,
    winner: Player,
    onCellClick: (Int, Int) -> Unit,
    onResetGame : () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = when {
                isGameOver && winner != Player.NONE -> stringResource(R.string.player_wins, winner.name)
                isGameOver && winner == Player.NONE -> stringResource(R.string.game_draw)
                else -> stringResource(R.string.current_player, currentPlayer.name)
            },
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        GameBoard(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            board = board,
            onCellClick ={
                    row, col ->
                if(!isGameOver) {
                    onCellClick(row, col)
                }

            })

        if(isGameOver){
            Button(onClick = onResetGame,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .height(50.dp)) {
                Text(text = stringResource(R.string.play_again))
            }
        }
    }

}

@Preview
@Composable
fun GameScreenPreview() {
    GameScreen(
        board = listOf(
            listOf(Player.NONE, Player.NONE, Player.NONE),
            listOf(Player.NONE, Player.NONE, Player.NONE),
            listOf(Player.NONE, Player.NONE, Player.NONE)
        ), currentPlayer = Player.X,
        isGameOver = false,
        winner = Player.NONE, onCellClick = { _, _ -> }, onResetGame = {})
}