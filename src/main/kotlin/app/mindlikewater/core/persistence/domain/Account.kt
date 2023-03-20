package app.mindlikewater.core.persistence.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("accounts")
class Account(var telegramChatId: Long?) {

    @Id
    var id: Int? = null

}
