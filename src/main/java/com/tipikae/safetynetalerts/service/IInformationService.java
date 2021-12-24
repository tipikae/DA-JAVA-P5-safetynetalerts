package com.tipikae.safetynetalerts.service;

import java.util.List;

import com.tipikae.safetynetalerts.dto.ChildAlertDTO;
import com.tipikae.safetynetalerts.dto.CommunityEmailDTO;
import com.tipikae.safetynetalerts.dto.FireDTO;
import com.tipikae.safetynetalerts.dto.FirestationDTO;
import com.tipikae.safetynetalerts.dto.FloodDTO;
import com.tipikae.safetynetalerts.dto.PersonInfoDTO;
import com.tipikae.safetynetalerts.dto.PhoneAlertDTO;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;

/**
 * An interface providing services for InformationController.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IInformationService {

	/**
	 * Get residents by station number.
	 * @param stationNumber an int station number.
	 * @return FirestationDTO
	 * @throws ServiceException
	 * @throws StorageException
	 */
	FirestationDTO getResidentsByStation(int stationNumber) throws ServiceException, StorageException;
	/**
	 * Get children by address.
	 * @param address a String.
	 * @return ChildAlertDTO
	 * @throws ServiceException
	 * @throws StorageException
	 */
	ChildAlertDTO getChildrenByAddress(String address) throws ServiceException, StorageException;
	/**
	 * Get phone numbers by station.
	 * @param station an int station number.
	 * @return PhoneAlertDTO
	 * @throws ServiceException
	 * @throws StorageException
	 */
	PhoneAlertDTO getPhoneNumbersByStation(int station) throws ServiceException, StorageException;
	/**
	 * Get members by address.
	 * @param address a String.
	 * @return FireDTO
	 * @throws ServiceException
	 * @throws StorageException
	 */
	FireDTO getMembersByAddress(String address) throws ServiceException, StorageException;
	/**
	 * Get residents by station numbers.
	 * @param stations a List of int
	 * @return List<FloodDTO>
	 * @throws ServiceException
	 * @throws StorageException
	 */
	List<FloodDTO> getResidentsByStations(List<Integer> stations) throws ServiceException, StorageException;
	/**
	 * Get persons information by lastname.
	 * @param firstname a String.
	 * @param lastname a String.
	 * @return PersonInfoDTO
	 * @throws ServiceException
	 * @throws StorageException
	 */
	PersonInfoDTO getPersonInfoByLastname(String firstname, String lastname) 
			throws ServiceException, StorageException;
	/**
	 * Get emails by city.
	 * @param city a String.
	 * @return CommunityEmailDTO
	 * @throws ServiceException
	 * @throws StorageException
	 */
	CommunityEmailDTO getEmailsByCity(String city) throws ServiceException, StorageException;
}
