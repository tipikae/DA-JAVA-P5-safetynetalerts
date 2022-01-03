package com.tipikae.safetynetalerts.dtoconverter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tipikae.safetynetalerts.dto.FirestationDTO;
import com.tipikae.safetynetalerts.exception.ConverterException;
import com.tipikae.safetynetalerts.model.Firestation;

@Component
public class FirestationConverterImpl implements IFirestationConverter {

	/**
	 * {@inheritDoc}
	 * @param dto {@inheritDoc}
	 * @return {@inheritDoc}
	 * @throws {@inheritDoc}
	 */
	@Override
	public Firestation toEntity(FirestationDTO dto) throws ConverterException {
		try {
			return new Firestation(dto.getAddress(), dto.getStation());
		} catch(Exception e) {
			throw new ConverterException(e.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 * @param entity {@inheritDoc}
	 * @return {@inheritDoc}
	 * @throws {@inheritDoc}
	 */
	@Override
	public FirestationDTO toDTO(Firestation entity) throws ConverterException {
		try {
			return new FirestationDTO(entity.getAddress(), entity.getStation());
		} catch (Exception e) {
			throw new ConverterException(e.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 * @param entities {@inheritDoc}
	 * @return {@inheritDoc}
	 * @throws {@inheritDoc}
	 */
	@Override
	public List<FirestationDTO> toDTOs(List<Firestation> entities) throws ConverterException {
		try {
			if(entities == null)
				return null;
			
			List<FirestationDTO> dtos = new ArrayList<>();
			for(Firestation entity: entities) {
				FirestationDTO dto = toDTO(entity);
				dtos.add(dto);
			}
			return dtos;
		} catch (ConverterException e) {
			throw new ConverterException(e.getMessage());
		}
	}

}
