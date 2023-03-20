package app.mindlikewater.core.web.handler

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

interface RequestHandler {

    fun handle(serverRequest: ServerRequest): Mono<ServerResponse>

}
