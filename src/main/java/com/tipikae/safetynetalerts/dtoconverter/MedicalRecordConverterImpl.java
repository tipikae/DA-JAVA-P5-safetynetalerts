package com.tipikae.safetynetalerts.dtoconverter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tipikae.safetynetalerts.dto.MedicalRecordDTO;
import com.tipikae.safetynetalerts.model.MedicalRecord;

@Component
public class MedicalRecordConverterImpl implements ImedicalRecordConverter {

	/**
	 * {@inheritDoc}
	 * @param dto {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public MedicalRecord toEntity(MedicalRecordDTO dto) {
		return new MedicalRecord(dto.getFirstName(), dto.getLastName(), dto.getBirthdate(), 
				dto.getMedications(), dto.getAllergies());
	}

	/**
	 * {@inheritDoc}
	 * @param entity {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public MedicalRecordDTO toDTO(MedicalRecord entity) {
		return new MedicalRecordDTO(entity.getFirstName(), entity.getLastName(), entity.getBirthdate(), 
				entity.getMedications(), entity.getAllergies());
	}

	/**
	 * {@inheritDoc}
	 * @param entities {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public List<MedicalRecordDTO> toDTOs(List<MedicalRecord> entities) {
		if(entities == null)
			return null;
		
		List<MedicalRecordDTO> dtos = new ArrayList<>();
		for(MedicalRecord entity: entities) {
			MedicalRecordDTO dto = toDTO(entity);
			dtos.add(dto);
		}
		return dtos;
	}

}
