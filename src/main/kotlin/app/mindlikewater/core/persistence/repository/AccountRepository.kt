package app.mindlikewater.core.persistence.repository

import app.mindlikewater.core.persistence.domain.Account
import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Mono

interface AccountRepository : R2dbcRepository<Account, Int> {

    fun findByTelegramChatId(telegramChatId: Long): Mono<Account>

}
