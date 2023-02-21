/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2023-01-29T17:32:34.392297+09:00
 */
package com.plan.manager.infrastructure.database.mapper

import com.plan.manager.infrastructure.database.mapper.UserDynamicSqlSupport.User
import com.plan.manager.infrastructure.database.mapper.UserDynamicSqlSupport.User.email
import com.plan.manager.infrastructure.database.mapper.UserDynamicSqlSupport.User.id
import com.plan.manager.infrastructure.database.mapper.UserDynamicSqlSupport.User.name
import com.plan.manager.infrastructure.database.mapper.UserDynamicSqlSupport.User.password
import com.plan.manager.infrastructure.database.mapper.UserDynamicSqlSupport.User.roleType
import com.plan.manager.infrastructure.database.record.UserRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.CountCompleter
import org.mybatis.dynamic.sql.util.kotlin.DeleteCompleter
import org.mybatis.dynamic.sql.util.kotlin.KotlinUpdateBuilder
import org.mybatis.dynamic.sql.util.kotlin.SelectCompleter
import org.mybatis.dynamic.sql.util.kotlin.UpdateCompleter
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.countFrom
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.deleteFrom
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.insert
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.insertMultiple
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.selectDistinct
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.selectList
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.selectOne
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.update

fun UserMapper.count(completer: CountCompleter) =
    countFrom(this::count, User, completer)

fun UserMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, User, completer)

fun UserMapper.deleteByPrimaryKey(id_: Long) =
    delete {
        where(id, isEqualTo(id_))
    }

fun UserMapper.insert(record: UserRecord) =
    insert(this::insert, record, User) {
        map(id).toProperty("id")
        map(email).toProperty("email")
        map(password).toProperty("password")
        map(name).toProperty("name")
        map(roleType).toProperty("roleType")
    }

fun UserMapper.insertMultiple(records: Collection<UserRecord>) =
    insertMultiple(this::insertMultiple, records, User) {
        map(id).toProperty("id")
        map(email).toProperty("email")
        map(password).toProperty("password")
        map(name).toProperty("name")
        map(roleType).toProperty("roleType")
    }

fun UserMapper.insertMultiple(vararg records: UserRecord) =
    insertMultiple(records.toList())

fun UserMapper.insertSelective(record: UserRecord) =
    insert(this::insert, record, User) {
        map(id).toPropertyWhenPresent("id", record::id)
        map(email).toPropertyWhenPresent("email", record::email)
        map(password).toPropertyWhenPresent("password", record::password)
        map(name).toPropertyWhenPresent("name", record::name)
        map(roleType).toPropertyWhenPresent("roleType", record::roleType)
    }

private val columnList = listOf(id, email, password, name, roleType)

fun UserMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, User, completer)

fun UserMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, User, completer)

fun UserMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, User, completer)

fun UserMapper.selectByPrimaryKey(id_: Long) =
    selectOne {
        where(id, isEqualTo(id_))
    }

fun UserMapper.update(completer: UpdateCompleter) =
    update(this::update, User, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: UserRecord) =
    apply {
        set(id).equalTo(record::id)
        set(email).equalTo(record::email)
        set(password).equalTo(record::password)
        set(name).equalTo(record::name)
        set(roleType).equalTo(record::roleType)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: UserRecord) =
    apply {
        set(id).equalToWhenPresent(record::id)
        set(email).equalToWhenPresent(record::email)
        set(password).equalToWhenPresent(record::password)
        set(name).equalToWhenPresent(record::name)
        set(roleType).equalToWhenPresent(record::roleType)
    }

fun UserMapper.updateByPrimaryKey(record: UserRecord) =
    update {
        set(email).equalTo(record::email)
        set(password).equalTo(record::password)
        set(name).equalTo(record::name)
        set(roleType).equalTo(record::roleType)
        where(id, isEqualTo(record::id))
    }

fun UserMapper.updateByPrimaryKeySelective(record: UserRecord) =
    update {
        set(email).equalToWhenPresent(record::email)
        set(password).equalToWhenPresent(record::password)
        set(name).equalToWhenPresent(record::name)
        set(roleType).equalToWhenPresent(record::roleType)
        where(id, isEqualTo(record::id))
    }
