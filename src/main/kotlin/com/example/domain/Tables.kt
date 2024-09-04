package com.example.domain

import com.example.shared.CafeMenuCategory
import com.example.shared.CafeOrderStatus
import com.example.shared.CafeUserRole
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime

// LongIdTable : 테이블의 pk를 long으로 쓰겠다는 뜻
object CafeMenuTable : LongIdTable(name = "cafe_menu") {
    val name = varchar("menu_name", length = 50)
    val price = integer("price")
    // enumeration<>() 으로 사용하면 숫자로 저장해서 버그 발생 확률 생김
    val category = enumerationByName("category", 10, CafeMenuCategory::class)
    val image = text("image")
}

object CafeUserTable : LongIdTable(name = "cafe_user") {
    val nickname = varchar("nickname", length = 50)
    val password = varchar("password", length = 100)
    // 커스텀 된 리스트
    val roles = enumList("roles", CafeUserRole::class.java, 20)
}

object CafeOrderTable : LongIdTable(name = "cafe_order") {
    val orderCode = varchar("order_code", length = 50)
    val cafeUserId = reference("cafe_user_id", CafeUserTable)
    val cafeMenuId = reference("cafe_menu_id", CafeMenuTable)
    val price = integer("price")
    val status = enumerationByName("status", 10, CafeOrderStatus::class)
    val orderedAt = datetime("ordered_at")
}
   