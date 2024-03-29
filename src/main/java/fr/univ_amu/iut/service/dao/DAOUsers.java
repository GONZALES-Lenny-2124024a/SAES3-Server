package fr.univ_amu.iut.service.dao;

import fr.univ_amu.iut.domain.User;
import fr.univ_amu.iut.exceptions.UserIsNotInTheDatabaseException;

import java.sql.SQLException;

/**
 * The methods' signature for the Users table
 * @author LennyGonzales
 */
public interface DAOUsers extends DAO<User, Integer>{
    /**
     * Get the user by email and password
     * @param email the input email of the user
     * @param password the input password of the user
     * @return an instance of User or null
     * @throws SQLException the SQL request didn't go well
     */
    User getUser(String email, String password) throws SQLException;

    /**
     * Get the points of a user by his email
     * @param email the email of the user
     * @return the points of the user
     * @throws SQLException the SQL request didn't go well
     * @throws UserIsNotInTheDatabaseException if the user isn't in the database
     */
    int getPointsByEmail(String email) throws SQLException, UserIsNotInTheDatabaseException;

    /**
     * Set the points of a user
     * @param email the email of the user
     * @param newUserPoints the new points of the user
     * @throws SQLException the SQL request didn't go well
     */
    void setPointsByEmail(String email, int newUserPoints) throws SQLException, UserIsNotInTheDatabaseException;

    /**
     * Verify if a email is in the database
     * @param email the email to verify
     * @return true - if the email is in the databse | else, return false
     * @throws SQLException if the SQL request didn't go well
     */
    boolean verifyEmail(String email) throws SQLException, UserIsNotInTheDatabaseException;
}