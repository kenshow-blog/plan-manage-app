/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2023-01-29T17:32:34.388103+09:00
 */
package com.plan.manager.infrastructure.database.mapper

import com.plan.manager.domain.type.RoleType
import java.sql.JDBCType
import org.mybatis.dynamic.sql.SqlTable

object UserDynamicSqlSupport {
    object User : SqlTable("public.user") {
        val id = column<Long>("id", JDBCType.BIGINT)

        val email = column<String>("email", JDBCType.VARCHAR)

        val password = column<String>("password", JDBCType.VARCHAR)

        val name = column<String>("name", JDBCType.VARCHAR)

        val roleType = column<RoleType>("role_type", JDBCType.VARCHAR, "org.apache.ibatis.type.EnumTypeHandler")
    }
}