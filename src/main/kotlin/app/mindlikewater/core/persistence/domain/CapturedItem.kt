package app.mindlikewater.core.persistence.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("captured_items")
class CapturedItem(val accountId: Int, val text: String) {

    @Id
    var id: Int? = null

}
