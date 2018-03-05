package exceptions;

public class CantConnectToApi extends Exception {
    public CantConnectToApi(String message) {
        super(message);
    }
}
