package com.tipikae.safetynetalerts.dtoconverter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tipikae.safetynetalerts.dto.PersonDTO;
import com.tipikae.safetynetalerts.exception.ConverterException;
import com.tipikae.safetynetalerts.model.Person;

@Component
public class PersonConverterImpl implements IPersonConverter {

	/**
	 * {@inheritDoc}
	 * @param dto {@inheritDoc}
	 * @return {@inheritDoc}
	 * @throws {@inheritDoc}
	 */
	@Override
	public Person toEntity(PersonDTO dto) throws ConverterException {
		try {
			return new Person(dto.getFirstName(), dto.getLastName(), dto.getAddress(), dto.getCity(), 
					dto.getZip(), dto.getPhone(), dto.getEmail());
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
	public PersonDTO toDTO(Person entity) throws ConverterException {
		try {
			return new PersonDTO(entity.getFirstName(), entity.getLastName(), entity.getAddress(), entity.getCity(), 
					entity.getZip(), entity.getPhone(), entity.getEmail());
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
	public List<PersonDTO> toDTOs(List<Person> entities) throws ConverterException {
		try {
			if(entities == null)
				return null;
			
			List<PersonDTO> dtos = new ArrayList<>();
			for(Person entity: entities) {
				PersonDTO dto = toDTO(entity);
				dtos.add(dto);
			}
			return dtos;
		} catch (ConverterException e) {
			throw new ConverterException(e.getMessage());
		}
	}

}
