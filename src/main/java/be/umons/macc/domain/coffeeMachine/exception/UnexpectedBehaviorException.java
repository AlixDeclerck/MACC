package be.umons.macc.domain.coffeeMachine.exception;

public class UnexpectedBehaviorException extends Exception {

    static final long serialVersionUID = -3387516993124229948L;

    public UnexpectedBehaviorException() {
        super();
    }

    public UnexpectedBehaviorException(String message) {
        super(message);
    }

    public UnexpectedBehaviorException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedBehaviorException(Throwable cause) {
        super(cause);
    }

}
