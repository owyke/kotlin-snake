package se.wyko.snake.implementations

import org.slf4j.LoggerFactory
import se.wyko.snake.Direction
import se.wyko.snake.SnakeBrain
import se.wyko.snake.api.Game
import se.wyko.snake.model.State

object RandomSafeSnakeBrain : SnakeBrain {
    val log = LoggerFactory.getLogger(RandomSafeSnakeBrain::class.java)

    private fun getAllOccupied(state: State) =
        state.snakes.flatMap { snake -> snake.body } + state.you.body


    override fun nextMove(state: State): Direction {
        val currPos = state.you.body.first()
        log.info(getAllOccupied(state).toString())

        return Direction.values().filter { dir ->
            val newPos = currPos + dir.point
            newPos !in getAllOccupied(state) && state.withinBounds(newPos)
        }.random()
    }
}
