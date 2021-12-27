package com.tipikae.safetynetalerts.dtoconverter;

import java.util.ArrayList;
import java.util.List;

import com.tipikae.safetynetalerts.dto.PersonDTO;
import com.tipikae.safetynetalerts.model.Person;

/**
 * A class for converting DTO Person to entity and vice-versa.
 * @author tipikae
 * @version 1.0
 *
 */
public class PersonConverter {
	
	/**
	 * Convert DTO to entity.
	 * @param dto a PersonDTO object.
	 * @return Person
	 */
	public static Person toEntity(PersonDTO dto) {
		return new Person(dto.getFirstName(), dto.getLastName(), dto.getAddress(), dto.getCity(), 
				dto.getZip(), dto.getPhone(), dto.getEmail());
	}

	/**
	 * Convert entity to DTO
	 * @param entity a Person object.
	 * @return PersonDTO
	 */
	public static PersonDTO toDTO(Person entity) {
		return new PersonDTO(entity.getFirstName(), entity.getLastName(), entity.getAddress(), entity.getCity(), 
				entity.getZip(), entity.getPhone(), entity.getEmail());
	}

	/**
	 * Convert a List<Person> to a List<PersonDTO>
	 * @param entities a List of Person
	 * @return List<PersonDTO>
	 */
	public static List<PersonDTO> toDTOs(List<Person> entities) {
		if(entities == null)
			return null;
		
		List<PersonDTO> dtos = new ArrayList<>();
		for(Person entity: entities) {
			PersonDTO dto = toDTO(entity);
			dtos.add(dto);
		}
		return dtos;
	}
}
