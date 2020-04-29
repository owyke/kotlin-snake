package se.wyko.snake.implementations

import se.wyko.snake.Direction
import se.wyko.snake.SnakeBrain
import se.wyko.snake.model.State


object Default : SnakeBrain {
    override fun nextMove(state: State) = Direction.UP
}

