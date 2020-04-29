package se.wyko.snake

import se.wyko.snake.implementations.DefaultSnakeBrain
import se.wyko.snake.implementations.RandomSafeSnakeBrain
import se.wyko.snake.implementations.RandomSnakeBrain
import se.wyko.snake.model.Point
import se.wyko.snake.model.State

enum class Breed(val snakeBrain: SnakeBrain) {
    DEFAULT(DefaultSnakeBrain),
    RANDOM(RandomSnakeBrain),
    RANDOMSAFE(RandomSafeSnakeBrain)
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
