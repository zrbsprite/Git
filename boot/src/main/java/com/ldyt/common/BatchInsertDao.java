package com.ldyt.common;

import java.util.List;

interface BatchInsertDao<T> {

	/**
     * 批量插入，支持数据库自增字段，支持回写
     * @param recordList
     * @return
     */
    int batchInsert(List<T> recordList);
}
