package com.medhead.api.mapper;

import com.medhead.api.dao.entity.Entity;
import com.medhead.api.dto.Model;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Generic interface for all mappers.
 *
 * @param <E> Entity class
 * @param <M> Model class
 */
public interface GenericMapper<E extends Entity, M extends Model> {

	/**
	 * Convert a model to entity.
	 *
	 * @param model the model to convert
	 * @return the entity object
	 */
	E toEntity(M model);

	/**
	 * Convert a entity to model.
	 *
	 * @param entity the entity to convert
	 * @return the model object
	 */
	M toModel(E entity);

	/**
	 * Convert a list of models to list of entities.
	 *
	 * @param models the models to convert
	 * @return the list of entities
	 */
	List<E> toEntityList(List<M> models);

	/**
	 * Convert a list of model to list of entities.
	 *
	 * @param entities the list of entities to convert
	 * @return the list of models
	 */
	List<M> toModelList(List<E> entities);
}