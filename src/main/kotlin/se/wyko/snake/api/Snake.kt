package se.wyko.snake.api

import se.wyko.snake.model.Point

data class Snake(
    val body: List<Point>,
    val health: Short,
    val id: String,
    val name: String,
    val shout: String?
)

