package co.com.pragma.exceptions;

public class DomainException extends RuntimeException {
    public DomainException(String message){
        super(message);
    }
}
