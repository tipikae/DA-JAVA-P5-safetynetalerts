package com.tipikae.safetynetalerts.dtoconverter;

import java.util.List;

public interface IConverter<E, D> {

	/**
	 * Convert a D object to an E object.
	 * @param dto D
	 * @return E
	 */
	E toEntity(D dto);
	/**
	 * Convert an E object to a D object.
	 * @param entity E
	 * @return D
	 */
	D toDTO(E entity);
	/**
	 * Convert an entities E list to a DTO D list.
	 * @param entities List<E>
	 * @return List<D>
	 */
	List<D> toDTOs(List<E> entities);
}
