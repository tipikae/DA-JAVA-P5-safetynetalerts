package com.tipikae.safetynetalerts.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tipikae.safetynetalerts.dao.IPersonDAO;
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

	private static final Logger LOGGER = LogManager.getLogger("PersonServiceImpl");

	/**
	 * The DAO.
	 */
	@Autowired
	private IPersonDAO dao;

	/**
	 * {@inheritDoc}
	 * @param person {@inheritDoc}
	 * @return {@inheritDoc}
	 * @throws {@inheritDoc}
	 * @throws {@inheritDoc}
	 */
	@Override
	public Person addPerson(Person person) throws ServiceException, StorageException {
		if(dao.findByFirstnameLastname(person.getFirstName(), person.getLastName()) == null) {
			return dao.save(person);
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
	 */
	@Override
	public Person updatePerson(String firstname, String lastname, Person person) 
			throws ServiceException, StorageException {
		if (firstname.equals(person.getFirstName()) && lastname.equals(person.getLastName())) {
			if (dao.findByFirstnameLastname(firstname, lastname) != null) {
				return dao.update(person);
			} else {
				LOGGER.error("updatePerson: Firstname: " + firstname + " and lastname:"
						+ lastname + " not found in Person.");
				throw new ServiceException("Firstname: " + firstname + " and lastname:"
						+ lastname + " not found in Person.");
			} 
		} else {
			LOGGER.error("updatePerson: Firstname: " + firstname + " and lastname:"
					+ lastname + " are different from Person firstname: " + person.getFirstName() 
					+ " lastname: " + person.getLastName());
			throw new ServiceException("Firstname: " + firstname + " and lastname:"
					+ lastname + " are different from Person firstname: " + person.getFirstName()
					+ "	lastname: " + person.getLastName());
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
		Person person = dao.findByFirstnameLastname(firstname, lastname);
		if(person != null) {
			dao.delete(person);
		} else {
			LOGGER.error("deletePerson: Firstname: " + firstname + " and lastname:"
					+ lastname + " not found in Person.");
			throw new ServiceException("Firstname: " + firstname + " and lastname:"
					+ lastname + " not found in Person.");
		}
	}

}
