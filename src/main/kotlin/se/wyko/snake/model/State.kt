package se.wyko.snake.model

import se.wyko.snake.Direction

data class Snake(
    val body: List<Point>,
    val health: Short
) {
    val head = body.first()
}

data class State(
    val width: Byte,
    val height: Byte,
    val you: Snake,
    val enemies: List<Snake>,
    val food: List<Point>
) {
    fun withinBounds(point: Point) =
        point.x in 0 until width &&
                point.y in 0 until height

    fun getAllOccupied() =
        enemies.flatMap { snake -> snake.body } + you.body

    fun canMoveTo(point: Point) = !getAllOccupied().contains(point) && withinBounds(point)

    fun adjacentToBiggerSnakeHead(point: Point): Boolean {
        return point in enemies
            .filter { you.body.size <= it.body.size }
            .flatMap { snek ->
                Direction.values().map { dir ->
                    snek.head + dir.point
                }
            }
    }

    fun adjacentToSmallerSnakeHead(point: Point): Boolean {
        return point in enemies
            .filter { you.body.size > it.body.size }
            .flatMap { snek ->
                Direction.values().map { dir ->
                    snek.head + dir.point
                }
            }
    }

}
