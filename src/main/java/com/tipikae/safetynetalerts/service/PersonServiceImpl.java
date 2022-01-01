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
	private IPersonDAO personDao;

	/**
	 * {@inheritDoc}
	 * @param person {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public Person addPerson(Person person) throws StorageException {
		return personDao.save(person);
	}

	/**
	 * {@inheritDoc}
	 * @param firstname {@inheritDoc}
	 * @param lastname {@inheritDoc}
	 * @param firestation {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public Person updatePerson(String firstname, String lastname, Person person) 
			throws ServiceException, StorageException {
		if (firstname.equals(person.getFirstName()) && lastname.equals(person.getLastName())) {
			if (personDao.findByFirstnameLastname(firstname, lastname) != null) {
				return personDao.update(person);
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
	 */
	@Override
	public void deletePerson(String firstname, String lastname) throws StorageException, ServiceException {
		Person person = personDao.findByFirstnameLastname(firstname, lastname);
		if(person != null) {
			personDao.delete(person);
		} else {
			LOGGER.error("deletePerson: Firstname: " + firstname + " and lastname:"
					+ lastname + " not found in Person.");
			throw new ServiceException("Firstname: " + firstname + " and lastname:"
					+ lastname + " not found in Person.");
		}
	}

}
