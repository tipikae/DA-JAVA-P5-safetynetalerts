package com.tipikae.safetynetalerts.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tipikae.safetynetalerts.dao.IPersonDAO;
import com.tipikae.safetynetalerts.dto.PersonDTO;
import com.tipikae.safetynetalerts.dtoconverter.IPersonConverter;
import com.tipikae.safetynetalerts.exception.ConverterException;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Person;

/**
 * An implementation of IPersonService.
 * @author tipikae
 * @version 1.0
 *
 */
@Service
public class PersonServiceImpl implements IPersonService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

	/**
	 * The DAO.
	 */
	@Autowired
	private IPersonDAO personDao;
	
	/**
	 * The DTO converter.
	 */
	@Autowired
	private IPersonConverter personConverter;

	/**
	 * {@inheritDoc}
	 * @param person {@inheritDoc}
	 * @return {@inheritDoc}
	 * @throws {@inheritDoc}
	 * @throws {@inheritDoc}
	 * @throws {@inheritDoc}
	 */
	@Override
	public PersonDTO addPerson(PersonDTO personDTO) 
			throws ServiceException, StorageException, ConverterException {
		Person person = personConverter.toEntity(personDTO);
		Optional<Person> optional = personDao.findByFirstnameLastname(person.getFirstName(), person.getLastName());
		if(!optional.isPresent()) {
			return personConverter.toDTO(personDao.save(person).get());
		} else {
			LOGGER.error("addPerson: person with firstname: " + person.getFirstName()
					+ " and lastname: " + person.getLastName() + " already exists.");
			throw new ServiceException("Person with firstname: " + person.getFirstName()
					+ " and lastname: " + person.getLastName() 
					+ " already exists.");
		}
		
	}

	/**
	 * {@inheritDoc}
	 * @param firstname {@inheritDoc}
	 * @param lastname {@inheritDoc}
	 * @param firestation {@inheritDoc}
	 * @return {@inheritDoc}
	 * @throws {@inheritDoc}
	 * @throws {@inheritDoc}
	 * @throws {@inheritDoc}
	 */
	@Override
	public PersonDTO updatePerson(String firstname, String lastname, PersonDTO personDTO) 
			throws ServiceException, StorageException, ConverterException {
		if (firstname.equals(personDTO.getFirstName()) && lastname.equals(personDTO.getLastName())) {
			Person person = personConverter.toEntity(personDTO);
			Optional<Person> optional = personDao.findByFirstnameLastname(firstname, lastname);
			if (optional.isPresent()) {
				return personConverter.toDTO(personDao.update(person).get());
			} else {
				LOGGER.error("updatePerson: Firstname: " + firstname + " and lastname:"
						+ lastname + " not found in Person.");
				throw new ServiceException("Firstname: " + firstname + " and lastname:"
						+ lastname + " not found in Person.");
			} 
		} else {
			LOGGER.error("updatePerson: Firstname: " + firstname + " and lastname:"
					+ lastname + " are different from Person firstname: " + personDTO.getFirstName() 
					+ " lastname: " + personDTO.getLastName());
			throw new ServiceException("Firstname: " + firstname + " and lastname:"
					+ lastname + " are different from Person firstname: " + personDTO.getFirstName()
					+ "	lastname: " + personDTO.getLastName());
		}
	}

	/**
	 * {@inheritDoc}
	 * @param firstname {@inheritDoc}
	 * @param lastname {@inheritDoc}
	 * @throws {@inheritDoc}
	 * @throws {@inheritDoc}
	 */
	@Override
	public void deletePerson(String firstname, String lastname) throws StorageException, ServiceException {
		Optional<Person> optional = personDao.findByFirstnameLastname(firstname, lastname);
		if(optional.isPresent()) {
			Person person = optional.get();
			personDao.delete(person);
		} else {
			LOGGER.error("deletePerson: Firstname: " + firstname + " and lastname:"
					+ lastname + " not found in Person.");
			throw new ServiceException("Firstname: " + firstname + " and lastname:"
					+ lastname + " not found in Person.");
		}
	}

}
