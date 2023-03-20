package app.mindlikewater.core.persistence.repository

import app.mindlikewater.core.persistence.domain.CapturedItem
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface CapturedItemRepository : R2dbcRepository<CapturedItem, Int> {
}
