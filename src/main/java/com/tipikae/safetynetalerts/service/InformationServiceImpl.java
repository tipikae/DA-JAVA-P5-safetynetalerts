package com.tipikae.safetynetalerts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	
	private static final Logger LOGGER = LogManager.getLogger("InformationServiceImpl");

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
	 * {@inheritDoc}
	 * @param station {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public FirestationInfoDTO getResidentsByStation(int stationNumber) throws ServiceException, StorageException {
		Optional<List<Firestation>> optFirestations = firestationDao.findByStation(stationNumber);
		if(optFirestations.isPresent()) {
			List<Firestation> firestations = optFirestations.get();
			List<FirestationInfo> residents = new ArrayList<>();
			int nbAdults = 0;
			int nbChildren = 0;
			for(Firestation firestation: firestations) {
				Optional<List<Person>> optPersons = personDao.findByAddress(firestation.getAddress());
				if(optPersons.isPresent()) {
					List<Person> persons = optPersons.get();
					for(Person person: persons) {
						Optional<MedicalRecord> optMedicalRecord = medicalRecordDao.findByFirstnameLastname(
								person.getFirstName(), person.getLastName());
						if(optMedicalRecord.isPresent()) {
							MedicalRecord medicalRecord = optMedicalRecord.get();
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
				} else {
					// LOGGER.debug("");
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
		Optional<List<Person>> optPersons = personDao.findByAddress(address);
		if(optPersons.isPresent()) {
			List<Person> persons = optPersons.get();
			List<ChildAlert> children = new ArrayList<>();
			List<ChildAlert> adults = new ArrayList<>();
			for(Person person: persons) {
				Optional<MedicalRecord> optMedicalRecord = medicalRecordDao.findByFirstnameLastname(
						person.getFirstName(), person.getLastName());
				if(optMedicalRecord.isPresent()) {
					MedicalRecord medicalRecord = optMedicalRecord.get();
					if(Util.isAdult(medicalRecord.getBirthdate())) {
						adults.add(new ChildAlert(person.getFirstName(), person.getLastName(), 
								Util.calculateAge(medicalRecord.getBirthdate())));
					} else {
						children.add(new ChildAlert(person.getFirstName(), person.getLastName(), 
								Util.calculateAge(medicalRecord.getBirthdate())));
					}
				} else {
					// LOGGER.debug("");
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
		Optional<List<Firestation>> optFirestation = firestationDao.findByStation(station);
		if(optFirestation.isPresent()) {
			List<Firestation> firestations = optFirestation.get();
			List<PhoneAlert> phones = new ArrayList<>();
			for(Firestation firestation: firestations) {
				Optional<List<Person>> optPersons = personDao.findByAddress(firestation.getAddress());
				if(optPersons.isPresent()) {
					List<Person> persons = optPersons.get();
					for(Person person: persons) {
						phones.add(new PhoneAlert(person.getPhone()));
					}
				} else {
					// LOGGER.debug("");
				}				
			}
			
			return new PhoneAlertDTO(station, phones);
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
		Optional<List<Person>> optPersons = personDao.findByAddress(address);
		if(optPersons.isPresent()) {
			List<Person> persons = optPersons.get();
			List<Fire> members = new ArrayList<>();
			Optional<Firestation> optFirestation = firestationDao.findByAddress(address);
			if(optFirestation.isPresent()) {
				Firestation firestation = optFirestation.get();
				int station = firestation.getStation();
				
				for(Person person: persons) {
					Optional<MedicalRecord> optMedicalRecord = medicalRecordDao.findByFirstnameLastname(
							person.getFirstName(), person.getLastName());
					if(optMedicalRecord.isPresent()) {
						MedicalRecord medicalRecord = optMedicalRecord.get();
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
					} else {
						// LOGGER.debug("");
					}
				}
				
				return new FireDTO(address, station, members);
			} else {
				LOGGER.error("getMembersByAddress: firestation not found.");
				throw new ServiceException("address: firestation not found.");
			}
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
			Optional<List<Firestation>> optFirestation = firestationDao.findByStation(station);
			if (optFirestation.isPresent()) {
				List<Firestation> firestations = optFirestation.get();
				for (Firestation firestation : firestations) {
					Optional<List<Person>> optPersons = personDao.findByAddress(firestation.getAddress());
					if(optPersons.isPresent()) {
						List<Person> persons = optPersons.get();
						List<Flood> residents = new ArrayList<>();
						for(Person person: persons) {
							Optional<MedicalRecord> optMedicalRecord = medicalRecordDao.findByFirstnameLastname(
									person.getFirstName(), person.getLastName());
							if(optMedicalRecord.isPresent()) {
								MedicalRecord medicalRecord = optMedicalRecord.get();
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
		Optional<List<Person>> optPersons = personDao.findAll();
		List<PersonInfo> personsInfo = new ArrayList<>();
		if(optPersons.isPresent()) {
			List<Person> persons = optPersons.get();
			for(Person person: persons) {
				if (person.getLastName().equals(lastname)) {
					Optional<MedicalRecord> optMedicalRecord = medicalRecordDao.findByFirstnameLastname(
							person.getFirstName(), person.getLastName());
					if(optMedicalRecord.isPresent()) {
						MedicalRecord medicalRecord = optMedicalRecord.get();
						personsInfo.add(new PersonInfo(person.getFirstName(), lastname, person.getAddress(), 
								Util.calculateAge(medicalRecord.getBirthdate()), person.getEmail(), 
								medicalRecord.getMedications(), medicalRecord.getAllergies()));
					}
				}
			}
		} else {
			// LOGGER.debug("");
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
		Optional<List<Person>> optPersons = personDao.findByCity(city);
		if(optPersons.isPresent()) {
			List<Person> persons = optPersons.get();
			List<CommunityEmail> emails = new ArrayList<>();
			for(Person person: persons) {
				emails.add(new CommunityEmail(person.getEmail()));
			}
			
			return new CommunityEmailDTO(city, emails);
		} else {
			LOGGER.error("getEmailsByCity: city: " + city + " not found.");
			throw new ServiceException("city: " + city + " not found.");
		}
	}

}
