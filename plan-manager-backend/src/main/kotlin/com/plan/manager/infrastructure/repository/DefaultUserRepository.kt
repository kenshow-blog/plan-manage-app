package com.plan.manager.infrastructure.repository

import com.plan.manager.domain.model.User
import com.plan.manager.domain.repository.UserRepository
import com.plan.manager.infrastructure.database.mapper.UserMapper
import com.plan.manager.infrastructure.database.mapper.selectByPrimaryKey
import org.springframework.stereotype.Repository

/**
 *  ユーザリポジトリ実装クラス
 */
@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Repository
class DefaultUserRepository (
        private val userMapper: UserMapper
): UserRepository {
    override fun findOne(id: Long): User? {
        val userRecord = userMapper.selectByPrimaryKey(id)
        userRecord?.let {
            return User(
                    it.id!!,
                    it.name!!
            )
        } ?: run {
            return null
        }
    }
}