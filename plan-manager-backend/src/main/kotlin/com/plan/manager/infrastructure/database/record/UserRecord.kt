/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2023-01-23T23:24:18.990501+09:00
 */
package com.plan.manager.infrastructure.database.record

import com.plan.manager.domain.type.RoleType

data class UserRecord(
    var id: Long? = null,
    var email: String? = null,
    var password: String? = null,
    var name: String? = null,
    var roleType: RoleType? = null
)