package mid_task10;
/*
You are developing a multi-tiered payment system with different
levels of subscriptions.
There is a base class Subscription, which handles common features
like calculating the basic fee. The PremiumSubscription class
extends Subscription and adds premium features, while the
VIPSubscription class further extends PreiniumSubscription and
adds VIP-only features. The base class Subscription has a constructor
that takes a double baseFee and assigns it to a class variable.
The PremiumSubscription class has an additional feature for a
double premiumFee. Its constructor should invoke the Subscription
class's constructor using super() and initialize the premium fee. The
VIPSubscription class further adds a double vipFee, Its constructor
should invoke the PremiumSubscription constructor using super()
and initialize the VIP fee.
Implement the classes Subscription, PremiumSubscription, and
VIPSubscription. Use the super keyword appropriately to call
constructors and methods. Implement the code for the given scenario.
 */
public class Main {
    public static void main(String[] args) {
        VIPSubscription vipSubscription = new VIPSubscription(100.00,50,150);
        vipSubscription.calculateFee();
    }
}
