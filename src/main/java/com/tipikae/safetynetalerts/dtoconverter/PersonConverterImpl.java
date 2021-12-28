package com.tipikae.safetynetalerts.dtoconverter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tipikae.safetynetalerts.dto.PersonDTO;
import com.tipikae.safetynetalerts.model.Person;

@Component
public class PersonConverterImpl implements IpersonConverter {

	/**
	 * {@inheritDoc}
	 * @param dto {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public Person toEntity(PersonDTO dto) {
		return new Person(dto.getFirstName(), dto.getLastName(), dto.getAddress(), dto.getCity(), 
				dto.getZip(), dto.getPhone(), dto.getEmail());
	}

	/**
	 * {@inheritDoc}
	 * @param entity {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public PersonDTO toDTO(Person entity) {
		return new PersonDTO(entity.getFirstName(), entity.getLastName(), entity.getAddress(), entity.getCity(), 
				entity.getZip(), entity.getPhone(), entity.getEmail());
	}

	/**
	 * {@inheritDoc}
	 * @param entities {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public List<PersonDTO> toDTOs(List<Person> entities) {
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
