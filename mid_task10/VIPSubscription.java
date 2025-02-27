package mid_task10;

public class VIPSubscription extends PremiumSubscription{
    double vipFee;
    VIPSubscription(double vipFee, double baseFee, double premiumFee) {
        super(baseFee, premiumFee);
        this.vipFee = vipFee;
    }
    void calculateFee() {
        super.calculateFee();
        System.out.println("Premium Fee: " + premiumFee);
    }
}
