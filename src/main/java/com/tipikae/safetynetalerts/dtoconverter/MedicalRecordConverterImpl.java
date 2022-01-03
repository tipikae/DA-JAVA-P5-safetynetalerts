package com.tipikae.safetynetalerts.dtoconverter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tipikae.safetynetalerts.dto.MedicalRecordDTO;
import com.tipikae.safetynetalerts.exception.ConverterException;
import com.tipikae.safetynetalerts.model.MedicalRecord;

@Component
public class MedicalRecordConverterImpl implements IMedicalRecordConverter {

	/**
	 * {@inheritDoc}
	 * @param dto {@inheritDoc}
	 * @return {@inheritDoc}
	 * @throws {@inheritDoc}
	 */
	@Override
	public MedicalRecord toEntity(MedicalRecordDTO dto) throws ConverterException {
		try {
			return new MedicalRecord(dto.getFirstName(), dto.getLastName(), dto.getBirthdate(), 
					dto.getMedications(), dto.getAllergies());
		} catch (Exception e) {
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
	public MedicalRecordDTO toDTO(MedicalRecord entity) throws ConverterException {
		try {
			return new MedicalRecordDTO(entity.getFirstName(), entity.getLastName(), entity.getBirthdate(), 
					entity.getMedications(), entity.getAllergies());
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
	public List<MedicalRecordDTO> toDTOs(List<MedicalRecord> entities) throws ConverterException {
		try {
			if(entities == null)
				return null;
			
			List<MedicalRecordDTO> dtos = new ArrayList<>();
			for(MedicalRecord entity: entities) {
				MedicalRecordDTO dto = toDTO(entity);
				dtos.add(dto);
			}
			return dtos;
		} catch (ConverterException e) {
			throw new ConverterException(e.getMessage());
		}
	}

}
