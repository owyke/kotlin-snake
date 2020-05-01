package se.wyko.snake

import se.wyko.snake.implementations.*
import se.wyko.snake.model.Point
import se.wyko.snake.model.State

enum class Breed(
    val snake: Snake
) {
    DEFAULT(Default),
    RANDOM(Random),
    RANDOMSAFE(RandomSafe),
    AVOIDNARROW(AvoidNarrow),
    @ExperimentalStdlibApi
    FILLSEARCH(FillSearch)

}

enum class Direction(val point: Point) {
    UP(Point(0, -1)),
    DOWN(Point(0, 1)),
    LEFT(Point(-1, 0)),
    RIGHT(Point(1, 0))
}

interface Snake {
    fun nextMove(state: State): Direction
}
