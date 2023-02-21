package com.plan.manager.domain.repository

import com.plan.manager.domain.model.User

/**
 * ユーザーリポジトリインターフェース
 */
interface UserRepository {
    fun findOne(id: Long): User?
}
