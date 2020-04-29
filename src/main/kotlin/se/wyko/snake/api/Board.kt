package se.wyko.snake.api

import se.wyko.snake.model.Point

data class Board(
    val food: List<Point>,
    val height: Int,
    val snakes: List<Snake>,
    val width: Int
)
