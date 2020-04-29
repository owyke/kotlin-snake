package se.wyko.snake.model

inline class Snake(
    val body: List<Point>
)

data class State(
    val width: Byte,
    val height: Byte,
    val you: Snake,
    val snakes: List<Snake>,
    val food: List<Point>
) {
    fun withinBounds(point: Point) =

        point.x in 0 until width &&
                point.y in 0 until height
}
