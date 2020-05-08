package se.wyko.snake.implementations

import org.slf4j.LoggerFactory
import se.wyko.snake.Direction
import se.wyko.snake.Snake
import se.wyko.snake.model.Point
import se.wyko.snake.model.State

@ExperimentalStdlibApi
object FillSearch : Snake {
    val log = LoggerFactory.getLogger(FillSearch::class.java)

    override fun nextMove(state: State): Direction {
        val moveEffects: Map<Int, List<MoveData>> =
            Direction.values().toList().shuffled()
                .filter { dir ->
                    state.canMoveTo(state.you.body.first() + dir.point)
                }
                .groupBy(
                    { reachableTiles(it, state) },
                    {
                        val newPos = state.you.head + it.point
                        MoveData(
                            it,
                            food = newPos in state.food,
                            reachableByLargerSnake = state.adjacentToBiggerSnakeHead(newPos),
                            reachableBySmallerSnake = state.adjacentToSmallerSnakeHead(newPos)
                        )
                    }
                )
        log.info("$moveEffects")
        val biggestArea = moveEffects.keys.max()
        return moveEffects[biggestArea]!!
            .filterNot { it.reachableByLargerSnake } // Dont get too close to bigger snake
            .sortedWith(compareBy({ it.reachableBySmallerSnake }, { it.food })) // Get closer to small snake, and eat if possible
            .map { it.direction }
            .last()
    }

    data class MoveData(
        val direction: Direction,
        val food: Boolean,
        val reachableByLargerSnake: Boolean,
        val reachableBySmallerSnake: Boolean
    )

    private fun reachableTiles(direction: Direction, state: State): Int {
        val currPos = state.you.body.first()
        val explored = mutableSetOf<Point>(currPos)
        val toExplore = mutableListOf(currPos + direction.point)
        while (toExplore.isNotEmpty()) {
            val curr = toExplore.removeLast()
            Direction.values().map { dir ->
                val candidate = dir.point + curr
                if (candidate !in explored && state.canMoveTo(candidate)) {
                    explored += candidate
                    toExplore += candidate
                }
            }
        }
        return explored.size

    }


}
