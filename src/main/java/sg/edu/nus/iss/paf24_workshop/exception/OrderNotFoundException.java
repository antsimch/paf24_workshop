package sg.edu.nus.iss.paf24_workshop.exception;

public class OrderNotFoundException extends RuntimeException{
    
    public OrderNotFoundException() {
        super();
    }
    
    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(Throwable cause) {
        super(cause);
    }
}
