package se.wyko.snake

import se.wyko.snake.implementations.AvoidNarrow
import se.wyko.snake.implementations.Default
import se.wyko.snake.implementations.RandomSafe
import se.wyko.snake.implementations.Random
import se.wyko.snake.model.Point
import se.wyko.snake.model.State

enum class Breed(val snakeBrain: SnakeBrain) {
    DEFAULT(Default),
    RANDOM(Random),
    RANDOMSAFE(RandomSafe),
    AVOIDNARROW(AvoidNarrow)
}

enum class Direction(val point: Point) {
    UP(Point(0, -1)),
    DOWN(Point(0, 1)),
    LEFT(Point(-1, 0)),
    RIGHT(Point(1, 0))
}

interface SnakeBrain {
    fun nextMove(game: State): Direction
}
