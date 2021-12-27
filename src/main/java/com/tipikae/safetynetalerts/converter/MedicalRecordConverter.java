package com.tipikae.safetynetalerts.converter;

import java.util.ArrayList;
import java.util.List;

import com.tipikae.safetynetalerts.dto.MedicalRecordDTO;
import com.tipikae.safetynetalerts.model.MedicalRecord;

/**
 * A class for converting DTO MedicalRecord to entity and vice-versa.
 * @author tipikae
 * @version 1.0
 *
 */
public class MedicalRecordConverter {
	
	/**
	 * Convert DTO to entity.
	 * @param dto a MedicalRecordDTO object.
	 * @return MedicalRecord
	 */
	public static MedicalRecord toEntity(MedicalRecordDTO dto) {
		return new MedicalRecord(dto.getFirstName(), dto.getLastName(), dto.getBirthdate(), 
				dto.getMedications(), dto.getAllergies());
	}

	/**
	 * Convert entity to DTO
	 * @param entity a MedicalRecord object.
	 * @return MedicalRecordDTO
	 */
	public static MedicalRecordDTO toDTO(MedicalRecord entity) {
		return new MedicalRecordDTO(entity.getFirstName(), entity.getLastName(), entity.getBirthdate(), 
				entity.getMedications(), entity.getAllergies());
	}

	/**
	 * Convert a List<MedicalRecord> to a List<MedicalRecordDTO>
	 * @param entities a List of MedicalRecord
	 * @return List<MedicalRecordDTO>
	 */
	public static List<MedicalRecordDTO> toDTOs(List<MedicalRecord> entities) {
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
