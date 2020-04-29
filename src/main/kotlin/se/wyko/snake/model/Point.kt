package se.wyko.snake.model

data class Point(
    val x: Int,
    val y: Int
) {
    operator fun plus(other: Point) =
        Point(x + other.x, y + other.y)
}
