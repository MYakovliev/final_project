package com.epam.web.service;


import com.epam.web.entity.Lot;

/**
 * The interface provides action for admin service
 */
public interface AdminService {

    /**
     * Method to ban user
     *
     * @param userId
     * @throws ServiceException
     */
    void ban(long userId) throws ServiceException;

    /**
     * Method to unban user
     *
     * @param userId
     * @throws ServiceException
     */
    void unban(long userId) throws ServiceException;

    /**
     * Method to submit that lot has been submitted
     *
     * @param lot
     * @throws ServiceException
     */
    void submitWinner(Lot lot) throws ServiceException;
}
