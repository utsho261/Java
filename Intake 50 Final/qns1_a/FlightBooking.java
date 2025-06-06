package qns1_a;
/*
Design a flight booking system that validates passengers' age, seat availability, and
payment status while fetching flight details from a server. Passengers must be between 18
and 80 years old; otherwise, throw custom exceptions Too YoungException or TooOld
Exception, as there are no built-in exceptions for such validations. If no seats are
available, throw a NoSeatsAvailableException, ensuring the check is logged using a
finally block. Write methods to handle the scenarios, and demonstrate their use in a
program, ensuring all exceptions are caught, logged, and properly managed using try,
catch, throw, and finally.
 */
public class FlightBooking {
    int age, availableSeats;
    boolean paymentDone;

    public FlightBooking(int age, int availableSeats, boolean paymentDone) {
        this.age = age;
        this.availableSeats = availableSeats;
        this.paymentDone = paymentDone;
    }

    void bookFlight() throws Exception {
        try{
            if (age<18) throw new TooYoungException();
            if(age> 80 ) throw new TooOldException();
            if(availableSeats<=0) throw new NoSeatsAvailableException();
            if(!paymentDone) throw new Exception("Payment not done");
            System.out.println("Flight booked!");
        }catch (Exception e){
            System.out.println(e);
        }
        finally {
            System.out.println("Checked seats and age validation.");
        }
    }

    public static void main(String[] args) {
        FlightBooking flightBooking = new FlightBooking(15, 5, true);
        FlightBooking flightBooking2 = new FlightBooking(81, 5, true);
        FlightBooking flightBooking3 = new FlightBooking(20, 0, true);
        FlightBooking flightBooking4 = new FlightBooking(20, 5, false);
        FlightBooking flightBooking5 = new FlightBooking(30, 5, true);

        try {
            System.out.println("Too young: ");
            flightBooking.bookFlight();
            System.out.println("Too old: ");
            flightBooking2.bookFlight();
            System.out.println("No seats available: ");
            flightBooking3.bookFlight();
            System.out.println("Not paid yet: ");
            flightBooking4.bookFlight();
            System.out.println("All ok: ");
            flightBooking5.bookFlight();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
