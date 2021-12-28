package com.tipikae.safetynetalerts.dtoconverter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tipikae.safetynetalerts.dto.FirestationDTO;
import com.tipikae.safetynetalerts.model.Firestation;

@Component
public class FirestationConverterImpl implements IFirestationConverter {

	/**
	 * {@inheritDoc}
	 * @param dto {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public Firestation toEntity(FirestationDTO dto) {
		return new Firestation(dto.getAddress(), dto.getStation());
	}

	/**
	 * {@inheritDoc}
	 * @param entity {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public FirestationDTO toDTO(Firestation entity) {
		return new FirestationDTO(entity.getAddress(), entity.getStation());
	}

	/**
	 * {@inheritDoc}
	 * @param entities {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public List<FirestationDTO> toDTOs(List<Firestation> entities) {
		if(entities == null)
			return null;
		
		List<FirestationDTO> dtos = new ArrayList<>();
		for(Firestation entity: entities) {
			FirestationDTO dto = toDTO(entity);
			dtos.add(dto);
		}
		return dtos;
	}

}
