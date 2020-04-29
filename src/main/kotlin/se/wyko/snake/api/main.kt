package se.wyko.snake.api

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.validation.JavalinValidation
import io.javalin.http.Context
import org.eclipse.jetty.http.HttpStatus
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import se.wyko.snake.Breed

val log: Logger = LoggerFactory.getLogger("Main")

fun main() {
    val app = Javalin.create { config ->
        config.requestLogger { ctx, ms ->
            log.info("${ctx.path()} took [$ms ms]")
        }
        config.showJavalinBanner = false
    }.start(7000)

    JavalinValidation.register(Breed::class.java) {
        Breed.valueOf(it.toUpperCase())
    }

    app.routes {
        path(":breed") {
            get("/move", ::move)
            post("/move", ::move)
            get("/start", ::start)
            post("/start", ::start)
            get("/end", ::end)
            post("/end", ::end)
            get("/ping", ::ping)
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
    ctx.json(
        mapOf(
            "color" to "#000000",
            "headType" to "bendr",
            "tailType" to "pixel"
        )
    )

}

private fun move(ctx: Context) {
    val game = ctx.bodyAsClass(Game::class.java)
    val moveStr = ctx.pathParam("breed", Breed::class.java).get().snakeBrain.nextMove(game.toState()).toString().toLowerCase()
    log.info("Moving $moveStr")
    ctx.json(mapOf("move" to moveStr))
}


