package app.mindlikewater.core.web.config

import app.mindlikewater.core.logging.logger
import app.mindlikewater.core.web.handler.CaptureHandler
import app.mindlikewater.core.web.handler.RequestHandler
import app.mindlikewater.core.web.util.buildInternalServerErrorResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.POST
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class WebConfig {

    val log = logger()

    @Bean
    fun handleRequests(captureHandler: CaptureHandler): RouterFunction<ServerResponse> {
        return routePostRequest("/capture", captureHandler)
    }

    private fun routePostRequest(uri: String, handler: RequestHandler): RouterFunction<ServerResponse> {
        return RouterFunctions.route(POST(uri)) { serverRequest ->
            handler.handle(serverRequest)
                .onErrorResume {
                    log.warning(it.message)
                    buildInternalServerErrorResponse()
                }
        }
    }

}
