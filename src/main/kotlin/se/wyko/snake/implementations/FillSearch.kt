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
        val byReachableTiles: Map<Int, List<Direction>> = Direction.values()
            .filter { dir ->
                state.canMoveTo(state.you.body.first() + dir.point)
            }
            .groupBy {
                reachableTiles(it, state)
            }
        log.info("$byReachableTiles")
        return byReachableTiles[byReachableTiles.keys.max()]!!.random()

    }

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
