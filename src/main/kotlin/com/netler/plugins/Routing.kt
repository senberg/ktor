package com.netler.plugins

import com.netler.routes.customerRouting
import com.netler.routes.getOrderRoute
import com.netler.routes.listOrdersRoute
import com.netler.routes.totalizeOrderRoute
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(StatusPages) {
        exception<IllegalStateException> { call, cause ->
            call.respondText("App in illegal state as ${cause.message}")
        }
    }

    routing {
        staticResources("/content", "mycontent");

        get("/") {
            call.respondText("Hello World!")
        }

        get("/test1") {
            val text = "<h1>Hello From Ktor</h1>"
            val type = ContentType.parse("text/html")
            call.respondText(text, type)
        }
        get("/error-test") {
            throw IllegalStateException("Too Busy")
        }
        customerRouting()

        listOrdersRoute()
        getOrderRoute()
        totalizeOrderRoute()
    }
}