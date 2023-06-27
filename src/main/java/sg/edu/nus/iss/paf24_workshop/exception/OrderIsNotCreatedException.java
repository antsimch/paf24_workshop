package sg.edu.nus.iss.paf24_workshop.exception;

public class OrderIsNotCreatedException extends RuntimeException {

    public OrderIsNotCreatedException() {
        super();
    }
    
    public OrderIsNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderIsNotCreatedException(String message) {
        super(message);
    }

    public OrderIsNotCreatedException(Throwable cause) {
        super(cause);
    }
}
