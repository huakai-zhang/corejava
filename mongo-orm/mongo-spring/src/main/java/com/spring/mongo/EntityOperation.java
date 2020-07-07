package com.spring.mongo;

/**
 * 实体对象的反射操作
 * @author Tom
 *
 * @param <T>
 */
public class EntityOperation<T> {
	// 泛型实体Class对象
	public Class<T> entityClass = null;

	public EntityOperation(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
}

