package qns2_a;

public class InsufficientMoneyException extends Exception{
    InsufficientMoneyException(){
        super("Insufficient Money");
    }
}