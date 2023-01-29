package be.umons.macc.domain.coffeeMachine.exception;

public class TechnicalIssueException extends Exception {

    static final long serialVersionUID = -3387516991924229948L;

    public TechnicalIssueException() {
        super();
    }

    public TechnicalIssueException(String message) {
        super(message);
    }

    public TechnicalIssueException(String message, Throwable cause) {
        super(message, cause);
    }

    public TechnicalIssueException(Throwable cause) {
        super(cause);
    }

}
