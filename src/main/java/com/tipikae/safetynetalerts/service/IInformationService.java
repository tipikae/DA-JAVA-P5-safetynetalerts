package com.tipikae.safetynetalerts.service;

import java.util.List;

import com.tipikae.safetynetalerts.dto.DTOResponse;
import com.tipikae.safetynetalerts.dto.FloodDTO;
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
	 * @return DTOResponse
	 * @throws ServiceException
	 * @throws StorageException
	 */
	DTOResponse getResidentsByStation(int stationNumber) throws ServiceException, StorageException;
	/**
	 * Get children by address.
	 * @param address a String.
	 * @return DTOResponse
	 * @throws ServiceException
	 * @throws StorageException
	 */
	DTOResponse getChildrenByAddress(String address) throws ServiceException, StorageException;
	/**
	 * Get phone numbers by station.
	 * @param station an int station number.
	 * @return DTOResponse
	 * @throws ServiceException
	 * @throws StorageException
	 */
	DTOResponse getPhoneNumbersByStation(int station) throws ServiceException, StorageException;
	/**
	 * Get members by address.
	 * @param address a String.
	 * @return DTOResponse
	 * @throws ServiceException
	 * @throws StorageException
	 */
	DTOResponse getMembersByAddress(String address) throws ServiceException, StorageException;
	/**
	 * Get residents by station numbers.
	 * @param stations a List of int
	 * @return List<FloodDTO>
	 * @throws ServiceException
	 * @throws StorageException
	 */
	//List<FloodDTO> getResidentsByStations(List<Integer> stations) throws ServiceException, StorageException;
	List<DTOResponse> getResidentsByStations(List<Integer> stations) throws ServiceException, StorageException;
	/**
	 * Get persons information by lastname.
	 * @param firstname a String.
	 * @param lastname a String.
	 * @return DTOResponse
	 * @throws ServiceException
	 * @throws StorageException
	 */
	DTOResponse getPersonInfoByLastname(String firstname, String lastname) 
			throws ServiceException, StorageException;
	/**
	 * Get emails by city.
	 * @param city a String.
	 * @return DTOResponse
	 * @throws ServiceException
	 * @throws StorageException
	 */
	DTOResponse getEmailsByCity(String city) throws ServiceException, StorageException;
}
