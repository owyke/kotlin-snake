package se.wyko.snake.api

import se.wyko.snake.model.State

data class Game(
    val board: Board,
    val game: GameId,
    val turn: Int,
    val you: Snake
) {

    fun toState() =
        State(
            width = board.width.toByte(),
            height = board.height.toByte(),
            you = se.wyko.snake.model.Snake(you.body, you.health),
            enemies = board.snakes.filter{it.id != you.id}.map { se.wyko.snake.model.Snake(it.body, it.health) },
            food = board.food
        )

}
