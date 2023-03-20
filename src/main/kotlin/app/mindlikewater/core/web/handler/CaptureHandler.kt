package app.mindlikewater.core.web.handler

import app.mindlikewater.core.logging.logger
import app.mindlikewater.core.persistence.domain.Account
import app.mindlikewater.core.persistence.domain.CapturedItem
import app.mindlikewater.core.persistence.repository.AccountRepository
import app.mindlikewater.core.persistence.repository.CapturedItemRepository
import app.mindlikewater.core.web.dto.CaptureRequestDto
import app.mindlikewater.core.web.util.buildOkResponse
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class CaptureHandler(
    val accountRepository: AccountRepository,
    val capturedItemRepository: CapturedItemRepository
) : RequestHandler {

    val log = logger()

    override fun handle(serverRequest: ServerRequest): Mono<ServerResponse> {
        return serverRequest.bodyToMono(CaptureRequestDto::class.java)
            .doOnNext { log.info("Capture Request: $it") }
            .flatMap { requestBody -> saveCapturedItem(requestBody)}
            .doOnNext { log.info("Saved CapturedItem with id ${it.id}") }
            .flatMap { buildOkResponse(it) }
    }

    private fun saveCapturedItem(requestBody: CaptureRequestDto): Mono<CapturedItem> {
        return accountRepository.findByTelegramChatId(requestBody.telegramChatId)
            .switchIfEmpty(accountRepository.save(Account(requestBody.telegramChatId)))
            .flatMap { account -> capturedItemRepository.save(CapturedItem(account.id!!, requestBody.text)) }
    }

}
