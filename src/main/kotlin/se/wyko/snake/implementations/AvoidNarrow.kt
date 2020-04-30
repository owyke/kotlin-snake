package se.wyko.snake.implementations

import org.slf4j.LoggerFactory
import se.wyko.snake.Direction
import se.wyko.snake.Snake
import se.wyko.snake.model.Point
import se.wyko.snake.model.State

object AvoidNarrow : Snake {
    val log = LoggerFactory.getLogger(AvoidNarrow::class.java)

    private fun getAdjacentOccupied(newPos: Point, state: State): Int {
        return Direction.values()
            .map {
                if (state.getAllOccupied().contains(it.point + newPos)) 1 else 0
            }
            .sum()
    }

    override fun nextMove(state: State): Direction {
        val currPos = state.you.body.first()
        log.info(state.getAllOccupied().toString())
        val byNumberOfNeighbors = Direction.values().filter { dir ->
            val newPos = currPos + dir.point
            newPos !in state.getAllOccupied() && state.withinBounds(newPos)
        }.groupBy { dir ->
            getAdjacentOccupied(currPos + dir.point, state)
        }

        return byNumberOfNeighbors[byNumberOfNeighbors.keys.min()]!!.random()
    }
}
