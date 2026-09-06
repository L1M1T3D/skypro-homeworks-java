package hw.skypro.skypro_homeworks.exceptions;

public class EmployeeWasNotFound extends RuntimeException {
    public EmployeeWasNotFound() {
    }

    public EmployeeWasNotFound(String message) {
        super(message);
    }

    public EmployeeWasNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeWasNotFound(Throwable cause) {
        super(cause);
    }

    public EmployeeWasNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
