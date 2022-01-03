package com.tipikae.safetynetalerts.dtoconverter;

import java.util.List;

import com.tipikae.safetynetalerts.exception.ConverterException;

public interface IConverter<E, D> {

	/**
	 * Convert a D object to an E object.
	 * @param dto D
	 * @return E
	 * @throws ConverterException
	 */
	E toEntity(D dto) throws ConverterException;
	/**
	 * Convert an E object to a D object.
	 * @param entity E
	 * @return D
	 * @throws ConverterException
	 */
	D toDTO(E entity) throws ConverterException;
	/**
	 * Convert an entities E list to a DTO D list.
	 * @param entities List<E>
	 * @return List<D>
	 * @throws ConverterException
	 */
	List<D> toDTOs(List<E> entities) throws ConverterException;
}
