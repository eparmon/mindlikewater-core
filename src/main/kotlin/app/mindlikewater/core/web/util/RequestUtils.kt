package app.mindlikewater.core.web.util

import app.mindlikewater.core.web.dto.ApiErrorDto
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

fun <T : Any> buildOkResponse(body: T): Mono<ServerResponse> {
    return ServerResponse.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(body)
}

fun buildInternalServerErrorResponse(): Mono<ServerResponse?> {
    return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(ApiErrorDto("Something went wrong"))
}
