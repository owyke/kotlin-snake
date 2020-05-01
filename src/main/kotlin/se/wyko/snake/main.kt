package se.wyko.snake

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.validation.JavalinValidation
import io.javalin.http.Context
import org.eclipse.jetty.http.HttpStatus
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import se.wyko.snake.api.Game

val log: Logger = LoggerFactory.getLogger("Main")

fun main() {
    val app = Javalin.create { config ->
        config.requestLogger { ctx, ms ->
            log.info("${ctx.path()} took [$ms ms]")
        }
    }.start(System.getenv("PORT")?.toInt() ?: 7000)

    JavalinValidation.register(Breed::class.java) {
        Breed.valueOf(it.toUpperCase())
    }

    app.routes {
        path(":breed") {
            post("/move", ::move)
            post("/start", ::start)
            post("/end", ::end)
            post("/ping", ::ping)
        }
    }
}

private fun ping(ctx: Context) {
    ctx.status(HttpStatus.OK_200)
}

private fun end(ctx: Context) {
    ctx.status(HttpStatus.OK_200)
}

private fun start(ctx: Context) {
ctx.status(200)
    ctx.json(
        mapOf(
            "headType" to "bendr",
            "tailType" to "pixel"
        )
    )

}

private fun move(ctx: Context) {
    val game = ctx.bodyValidator<Game>().get()
    val breed = ctx.pathParam<Breed>("breed").get()
    val moveStr = breed.snake.nextMove(game.toState()).toString().toLowerCase()
    log.info("Moving $moveStr")
    ctx.json(mapOf("move" to moveStr))
}


