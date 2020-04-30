package se.wyko.snake.model

data class Snake(
    val body: List<Point>,
    val health: Short
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

    fun getAllOccupied() =
        snakes.flatMap { snake -> snake.body } + you.body

    fun canMoveTo(point: Point) = !getAllOccupied().contains(point) && withinBounds(point)

}
