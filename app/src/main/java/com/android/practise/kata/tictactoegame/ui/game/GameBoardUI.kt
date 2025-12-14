package com.android.practise.kata.tictactoegame.ui.game

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.practise.kata.tictactoegame.domain.model.Player

@Composable
fun GameBoard(
    modifier: Modifier = Modifier,
    board: List<List<CellStateUiModel>>,
    onCellClick: (Int, Int) -> Unit
){
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            board.forEachIndexed { rowIndex, rowItems ->
                Row {
                    rowItems.forEachIndexed { columnIndex, cellState ->
                        Cell(
                            modifier = Modifier.weight(1f),
                            state = cellState,
                            onClick = { onCellClick(rowIndex, columnIndex) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Cell(
    modifier: Modifier = Modifier,
    state: CellStateUiModel,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .aspectRatio(1f)
            .padding(4.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(4.dp)
            )
            .then(
                if(state == CellStateUiModel.Empty) {
                    Modifier.clickable(
                        enabled = true,
                        onClick = onClick
                    )
                } else {
                    modifier
                }
            ),
    ){
        if(state is CellStateUiModel.Filled){
            Text(
                text = state.player.name,
                style = MaterialTheme.typography.headlineMedium,
                color = if(state.player == Player.X) Color.Red else Color.Blue
            )
        }
    }
}


@Preview
@Composable
fun GameBoardPreview() {
    GameBoard(
        board = listOf(
            listOf(CellStateUiModel.Empty,CellStateUiModel.Empty,CellStateUiModel.Empty),
            listOf(CellStateUiModel.Empty,CellStateUiModel.Empty,CellStateUiModel.Empty),
            listOf(CellStateUiModel.Empty,CellStateUiModel.Empty,CellStateUiModel.Empty)
        ),
        onCellClick = { _, _ -> }
    )
}