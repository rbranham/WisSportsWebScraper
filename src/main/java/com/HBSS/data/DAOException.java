package com.HBSS.data;

public class DAOException extends RuntimeException {

	// Constants ---------------------------------------
	private static final long serialVersionUID = 1L;

	// Constructors ------------------------------------
	
    public DAOException(String message) {
        super(message);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
