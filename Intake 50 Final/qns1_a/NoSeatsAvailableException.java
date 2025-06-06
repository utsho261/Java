package qns1_a;

public class NoSeatsAvailableException extends Exception {
    public NoSeatsAvailableException() {
        super("No Seats Available");
    }
}
