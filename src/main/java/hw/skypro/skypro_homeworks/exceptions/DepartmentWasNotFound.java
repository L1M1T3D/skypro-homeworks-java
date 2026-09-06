package hw.skypro.skypro_homeworks.exceptions;

public class DepartmentWasNotFound extends RuntimeException {
    public DepartmentWasNotFound(String message) {
        super(message);
    }

    public DepartmentWasNotFound() {
        super();
    }

    public DepartmentWasNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartmentWasNotFound(Throwable cause) {
        super(cause);
    }

    protected DepartmentWasNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
