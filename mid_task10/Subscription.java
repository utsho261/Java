package mid_task10;

public class Subscription {
    double baseFee;

    Subscription(double baseFee) {
        this.baseFee = baseFee;
    }
    void calculateFee() {
        System.out.println("Base Fee: " + baseFee);
    }
}
