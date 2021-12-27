package com.tipikae.safetynetalerts.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tipikae.safetynetalerts.dao.IFirestationDAO;
import com.tipikae.safetynetalerts.dao.IMedicalRecordDAO;
import com.tipikae.safetynetalerts.dao.IPersonDAO;
import com.tipikae.safetynetalerts.dto.ChildAlert;
import com.tipikae.safetynetalerts.dto.ChildAlertDTO;
import com.tipikae.safetynetalerts.dto.CommunityEmail;
import com.tipikae.safetynetalerts.dto.CommunityEmailDTO;
import com.tipikae.safetynetalerts.dto.Fire;
import com.tipikae.safetynetalerts.dto.FireDTO;
import com.tipikae.safetynetalerts.dto.FirestationInfoDTO;
import com.tipikae.safetynetalerts.dto.FirestationInfo;
import com.tipikae.safetynetalerts.dto.Flood;
import com.tipikae.safetynetalerts.dto.FloodAddress;
import com.tipikae.safetynetalerts.dto.FloodDTO;
import com.tipikae.safetynetalerts.dto.PersonInfo;
import com.tipikae.safetynetalerts.dto.PersonInfoDTO;
import com.tipikae.safetynetalerts.dto.PhoneAlert;
import com.tipikae.safetynetalerts.dto.PhoneAlertDTO;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Firestation;
import com.tipikae.safetynetalerts.model.MedicalRecord;
import com.tipikae.safetynetalerts.model.Person;
import com.tipikae.safetynetalerts.util.Util;

/**
 * An implementation of IInformationService.
 * @author tipikae
 * @version 1.0
 *
 */
@Service
public class InformationServiceImpl implements IInformationService {
	
	private static final Logger LOGGER = LogManager.getLogger("InformationService");

	/**
	 * The IFirestationDAO.
	 */
	@Autowired
	private IFirestationDAO firestationDao;
	/**
	 * The IPersonDAO.
	 */
	@Autowired
	private IPersonDAO personDao;
	/**
	 * The IMedicalRecordDAO.
	 */
	@Autowired
	private IMedicalRecordDAO medicalRecordDao;

	/**
	 * Set firestationDAO.
	 * @param firestationDao a IFirestationDAO interface.
	 */
	public void setFirestationDao(IFirestationDAO firestationDao) {
		this.firestationDao = firestationDao;
	}

	/**
	 * Set personDAO.
	 * @param personDao a IPersonDAO interface.
	 */
	public void setPersonDao(IPersonDAO personDao) {
		this.personDao = personDao;
	}

	/**
	 * Set medicalRecordDAO.
	 * @param medicalRecordDao a IMedicalRecordDAO interface.
	 */
	public void setMedicalRecordDao(IMedicalRecordDAO medicalRecordDao) {
		this.medicalRecordDao = medicalRecordDao;
	}

	/**
	 * {@inheritDoc}
	 * @param station {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public FirestationInfoDTO getResidentsByStation(int stationNumber) throws ServiceException, StorageException {
		List<Firestation> firestations = firestationDao.findByStation(stationNumber);
		if(!firestations.isEmpty()) {
			List<FirestationInfo> residents = new ArrayList<>();
			int nbAdults = 0;
			int nbChildren = 0;
			for(Firestation firestation: firestations) {
				List<Person> persons = personDao.findByAddress(firestation.getAddress());
				for(Person person: persons) {
					MedicalRecord medicalRecord = medicalRecordDao.findByFirstnameLastname(
							person.getFirstName(), person.getLastName());
					if(medicalRecord != null) {
						if(Util.isAdult(medicalRecord.getBirthdate())) {
							nbAdults++;
						} else {
							nbChildren++;
						}
					}
					FirestationInfo resident = new FirestationInfo(person.getFirstName(), person.getLastName(), 
							person.getAddress(), person.getPhone());
					residents.add(resident);
				}
			}
			
			return new FirestationInfoDTO(stationNumber, nbAdults, nbChildren, residents);
		} else {
			LOGGER.error("getResidentsByStation: station: " + stationNumber + " not found.");
			throw new ServiceException("station: " + stationNumber + " not found.");
		}
	}

	/**
	 * {@inheritDoc}
	 * @param address {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public ChildAlertDTO getChildrenByAddress(String address) throws ServiceException, StorageException {
		List<Person> persons = personDao.findByAddress(address);
		if(!persons.isEmpty()) {
			List<ChildAlert> children = new ArrayList<>();
			List<ChildAlert> adults = new ArrayList<>();
			for(Person person: persons) {
				MedicalRecord medicalRecord = medicalRecordDao.findByFirstnameLastname(
						person.getFirstName(), person.getLastName());
				if(Util.isAdult(medicalRecord.getBirthdate())) {
					adults.add(new ChildAlert(person.getFirstName(), person.getLastName(), 
							Util.calculateAge(medicalRecord.getBirthdate())));
				} else {
					children.add(new ChildAlert(person.getFirstName(), person.getLastName(), 
							Util.calculateAge(medicalRecord.getBirthdate())));
				}
			}
			
			if(!children.isEmpty()) {
				return new ChildAlertDTO(address, children, adults);
			} else {
				return new ChildAlertDTO(address, new ArrayList<ChildAlert>(), new ArrayList<ChildAlert>());
			}
		} else {
			LOGGER.error("getChildrenByAddress: address: " + address + " not found.");
			throw new ServiceException("address: " + address + " not found.");
		}
	}

	/**
	 * {@inheritDoc}
	 * @param station {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public PhoneAlertDTO getPhoneNumbersByStation(int station) throws ServiceException, StorageException {
		List<Firestation> firestations = firestationDao.findByStation(station);
		if(!firestations.isEmpty()) {
			List<PhoneAlert> phones = new ArrayList<>();
			for(Firestation firestation: firestations) {
				List<Person> persons = personDao.findByAddress(firestation.getAddress());
				for(Person person: persons) {
					phones.add(new PhoneAlert(person.getPhone()));
				}
			}
			
			return new PhoneAlertDTO(phones);
		} else {
			LOGGER.error("getPhoneNumbersByStation: station: " + station + " not found.");
			throw new ServiceException("station: " + station + " not found.");
		}
	}

	/**
	 * {@inheritDoc}
	 * @param address {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public FireDTO getMembersByAddress(String address) throws ServiceException, StorageException {
		List<Person> persons = personDao.findByAddress(address);
		if(!persons.isEmpty()) {
			List<Fire> members = new ArrayList<>();
			Firestation firestation = firestationDao.findByAddress(address);
			int station = firestation.getStation();
			
			for(Person person: persons) {
				MedicalRecord medicalRecord = medicalRecordDao.findByFirstnameLastname(
						person.getFirstName(), person.getLastName());
				Fire member;
				if(medicalRecord != null) {
					int age = Util.calculateAge(medicalRecord.getBirthdate());
					member = new Fire(person.getFirstName(), person.getLastName(), person.getPhone(), 
							age, medicalRecord.getMedications(), medicalRecord.getAllergies());
				} else {
					member = new Fire(person.getFirstName(), person.getLastName(), person.getPhone(), 
							0, null, null);
				}
				members.add(member);
			}
			
			return new FireDTO(address, station, members);
		} else {
			LOGGER.error("getMembersByAddress: address: " + address + " not found.");
			throw new ServiceException("address: " + address + " not found.");
		}
	}

	/**
	 * {@inheritDoc}
	 * @param stations {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public List<FloodDTO> getResidentsByStations(List<Integer> stations) throws ServiceException, StorageException {
		List<FloodDTO> dtos = new ArrayList<>();
		for(Integer station: stations) {
			List<FloodAddress> adresses = new ArrayList<>();
			List<Firestation> firestations = firestationDao.findByStation(station);
			if (!firestations.isEmpty()) {
				for (Firestation firestation : firestations) {
					List<Flood> residents = new ArrayList<>();
					List<Person> persons = personDao.findByAddress(firestation.getAddress());
					if(!persons.isEmpty()) {
						for(Person person: persons) {
							MedicalRecord medicalRecord = medicalRecordDao.findByFirstnameLastname(
									person.getFirstName(), person.getLastName());
							if(medicalRecord != null) {
								residents.add(new Flood(person.getFirstName(), person.getLastName(), 
										person.getPhone(), Util.calculateAge(medicalRecord.getBirthdate()), 
										medicalRecord.getMedications(), medicalRecord.getAllergies()));
							} else {
								LOGGER.error("getResidentsByStations: name: " + person.getFirstName() + " " + 
										person.getLastName() + " not found in MedicalRecord.");
							}
						}
						adresses.add(new FloodAddress(firestation.getAddress(), residents));
					} else {
						LOGGER.error("getResidentsByStations: address: " + firestation.getAddress() + 
								" not found in Person.");
					}
				} 
				dtos.add(new FloodDTO(station, adresses));
			} else {
				LOGGER.error("getResidentsByStations: station: " + station + " not found in Firestation.");
			}
		}
		
		if(!dtos.isEmpty()) {
			return dtos;
		} else {
			StringBuilder sb = new StringBuilder();
			for(Integer station: stations) {
				sb.append(station.toString() + ",");
			}
			sb.deleteCharAt(sb.length() - 1);
			LOGGER.error("getResidentsByStations: stations: " + sb + " not found.");
			throw new ServiceException("stations: " + sb + " not found.");
		}
	}

	/**
	 * {@inheritDoc}
	 * @param firstname {@inheritDoc}
	 * @param lastname {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public PersonInfoDTO getPersonInfoByLastname(String firstname, String lastname)
			throws ServiceException, StorageException {
		List<Person> persons = personDao.findAll();
		List<PersonInfo> personsInfo = new ArrayList<>();
		
		for(Person person: persons) {
			if (person.getLastName().equals(lastname)) {
				MedicalRecord medicalRecord = medicalRecordDao.findByFirstnameLastname(
						person.getFirstName(), lastname);
				if(medicalRecord != null) {
					personsInfo.add(new PersonInfo(person.getFirstName(), lastname, person.getAddress(), 
							Util.calculateAge(medicalRecord.getBirthdate()), person.getEmail(), 
							medicalRecord.getMedications(), medicalRecord.getAllergies()));
				}
			}
		}
		
		if(!personsInfo.isEmpty()) {
			return new PersonInfoDTO(lastname, personsInfo);
		} else {
			LOGGER.error("getPersonInfoByLastname: lastname: " + lastname + " not found.");
			throw new ServiceException("lastname: " + lastname + " not found.");
		}
	}

	/**
	 * {@inheritDoc}
	 * @param city {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public CommunityEmailDTO getEmailsByCity(String city) throws ServiceException, StorageException {
		List<Person> persons = personDao.findByCity(city);
		if(!persons.isEmpty()) {
			List<CommunityEmail> emails = new ArrayList<>();
			for(Person person: persons) {
				emails.add(new CommunityEmail(person.getEmail()));
			}
			
			return new CommunityEmailDTO(emails);
		} else {
			LOGGER.error("getEmailsByCity: city: " + city + " not found.");
			throw new ServiceException("city: " + city + " not found.");
		}
	}

}
