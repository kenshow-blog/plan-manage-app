/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2023-01-23T23:24:19.029267+09:00
 */
package com.plan.manager.infrastructure.database.mapper

import com.plan.manager.infrastructure.database.mapper.PlanDynamicSqlSupport.Plan
import com.plan.manager.infrastructure.database.mapper.PlanDynamicSqlSupport.Plan.address
import com.plan.manager.infrastructure.database.mapper.PlanDynamicSqlSupport.Plan.description
import com.plan.manager.infrastructure.database.mapper.PlanDynamicSqlSupport.Plan.endDate
import com.plan.manager.infrastructure.database.mapper.PlanDynamicSqlSupport.Plan.id
import com.plan.manager.infrastructure.database.mapper.PlanDynamicSqlSupport.Plan.startDate
import com.plan.manager.infrastructure.database.mapper.PlanDynamicSqlSupport.Plan.status
import com.plan.manager.infrastructure.database.mapper.PlanDynamicSqlSupport.Plan.title
import com.plan.manager.infrastructure.database.mapper.PlanDynamicSqlSupport.Plan.userId
import com.plan.manager.infrastructure.database.record.PlanRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun PlanMapper.count(completer: CountCompleter) =
    countFrom(this::count, Plan, completer)

fun PlanMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, Plan, completer)

fun PlanMapper.deleteByPrimaryKey(id_: Long) =
    delete {
        where(id, isEqualTo(id_))
    }

fun PlanMapper.insert(record: PlanRecord) =
    insert(this::insert, record, Plan) {
        map(id).toProperty("id")
        map(userId).toProperty("userId")
        map(title).toProperty("title")
        map(description).toProperty("description")
        map(address).toProperty("address")
        map(startDate).toProperty("startDate")
        map(endDate).toProperty("endDate")
        map(status).toProperty("status")
    }

fun PlanMapper.insertMultiple(records: Collection<PlanRecord>) =
    insertMultiple(this::insertMultiple, records, Plan) {
        map(id).toProperty("id")
        map(userId).toProperty("userId")
        map(title).toProperty("title")
        map(description).toProperty("description")
        map(address).toProperty("address")
        map(startDate).toProperty("startDate")
        map(endDate).toProperty("endDate")
        map(status).toProperty("status")
    }

fun PlanMapper.insertMultiple(vararg records: PlanRecord) =
    insertMultiple(records.toList())

fun PlanMapper.insertSelective(record: PlanRecord) =
    insert(this::insert, record, Plan) {
        map(id).toPropertyWhenPresent("id", record::id)
        map(userId).toPropertyWhenPresent("userId", record::userId)
        map(title).toPropertyWhenPresent("title", record::title)
        map(description).toPropertyWhenPresent("description", record::description)
        map(address).toPropertyWhenPresent("address", record::address)
        map(startDate).toPropertyWhenPresent("startDate", record::startDate)
        map(endDate).toPropertyWhenPresent("endDate", record::endDate)
        map(status).toPropertyWhenPresent("status", record::status)
    }

private val columnList = listOf(id, userId, title, description, address, startDate, endDate, status)

fun PlanMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, Plan, completer)

fun PlanMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, Plan, completer)

fun PlanMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, Plan, completer)

fun PlanMapper.selectByPrimaryKey(id_: Long) =
    selectOne {
        where(id, isEqualTo(id_))
    }

fun PlanMapper.update(completer: UpdateCompleter) =
    update(this::update, Plan, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: PlanRecord) =
    apply {
        set(id).equalTo(record::id)
        set(userId).equalTo(record::userId)
        set(title).equalTo(record::title)
        set(description).equalTo(record::description)
        set(address).equalTo(record::address)
        set(startDate).equalTo(record::startDate)
        set(endDate).equalTo(record::endDate)
        set(status).equalTo(record::status)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: PlanRecord) =
    apply {
        set(id).equalToWhenPresent(record::id)
        set(userId).equalToWhenPresent(record::userId)
        set(title).equalToWhenPresent(record::title)
        set(description).equalToWhenPresent(record::description)
        set(address).equalToWhenPresent(record::address)
        set(startDate).equalToWhenPresent(record::startDate)
        set(endDate).equalToWhenPresent(record::endDate)
        set(status).equalToWhenPresent(record::status)
    }

fun PlanMapper.updateByPrimaryKey(record: PlanRecord) =
    update {
        set(userId).equalTo(record::userId)
        set(title).equalTo(record::title)
        set(description).equalTo(record::description)
        set(address).equalTo(record::address)
        set(startDate).equalTo(record::startDate)
        set(endDate).equalTo(record::endDate)
        set(status).equalTo(record::status)
        where(id, isEqualTo(record::id))
    }

fun PlanMapper.updateByPrimaryKeySelective(record: PlanRecord) =
    update {
        set(userId).equalToWhenPresent(record::userId)
        set(title).equalToWhenPresent(record::title)
        set(description).equalToWhenPresent(record::description)
        set(address).equalToWhenPresent(record::address)
        set(startDate).equalToWhenPresent(record::startDate)
        set(endDate).equalToWhenPresent(record::endDate)
        set(status).equalToWhenPresent(record::status)
        where(id, isEqualTo(record::id))
    }