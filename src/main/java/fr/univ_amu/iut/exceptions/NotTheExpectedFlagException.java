package fr.univ_amu.iut.exceptions;

/**
 * If the flag received isn't the expected flag
 * @author LennyGonzales
 */
public class NotTheExpectedFlagException extends Exception{
    public NotTheExpectedFlagException(String expectedFlag) {
        super("This is not the expected flag. The expected flag is : " + expectedFlag);
    }
}
