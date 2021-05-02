package com.epam.web.service;

/**
 * The interface provides action for getting a size of a list
 *
 * @author Nikita Yakovlev
 */
public interface AmountService {
    /**
     * @return amount of all lots
     * @throws ServiceException
     */
    int findAllLotAmount() throws ServiceException;

    /**
     * @return amount of all lots that are active now
     * @throws ServiceException
     */
    int findActiveLotAmount() throws ServiceException;

    /**
     * @param name
     * @return amount of all lots that has exact string in their name
     * @throws ServiceException
     */
    int findLotByNameAmount(String name) throws ServiceException;

    /**
     * @param sellerId
     * @return amount of all lots that are selling by exact seller
     * @throws ServiceException
     */
    int findLotBySellerIdAmount(long sellerId) throws ServiceException;

    /**
     * @param buyerId
     * @return amount of lots that were bid by exact buyer
     * @throws ServiceException
     */
    int findLotByBuyerIdAmount(long buyerId) throws ServiceException;

    /**
     * @return amount of all users
     * @throws ServiceException
     */
    int findAllUserAmount() throws ServiceException;

    /**
     * @param name
     * @return amount of all users that have exact string in their name
     * @throws ServiceException
     */
    int findUserByNameAmount(String name) throws ServiceException;
}
