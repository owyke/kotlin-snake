package se.wyko.snake.implementations

import se.wyko.snake.Direction
import se.wyko.snake.Snake
import se.wyko.snake.model.State

object Random: Snake {
    override fun nextMove(state: State) = Direction.values().random()
}
