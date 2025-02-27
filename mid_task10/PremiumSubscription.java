package mid_task10;

public class PremiumSubscription extends Subscription {
    double premiumFee;
    PremiumSubscription(double premiumFee, double baseFee) {
        super(baseFee);
        this.premiumFee = premiumFee;
    }
    void calculateFee() {
        super.calculateFee();
        System.out.println("Premium Fee: " + premiumFee);
    }
}
