package br.com.gbraghim.eventmanager.service.exception;

/**
 * Created by gustavoB on 8/24/17.
 */
public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
