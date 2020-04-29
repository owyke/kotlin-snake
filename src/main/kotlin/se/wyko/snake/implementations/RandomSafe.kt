package se.wyko.snake.implementations

import org.slf4j.LoggerFactory
import se.wyko.snake.Direction
import se.wyko.snake.SnakeBrain
import se.wyko.snake.model.State

object RandomSafe : SnakeBrain {
    val log = LoggerFactory.getLogger(RandomSafe::class.java)




    override fun nextMove(state: State): Direction {
        val currPos = state.you.body.first()
        log.info(state.getAllOccupied().toString())

        return Direction.values().filter { dir ->
            val newPos = currPos + dir.point
            newPos !in state.getAllOccupied() && state.withinBounds(newPos)
        }.random()
    }
}
