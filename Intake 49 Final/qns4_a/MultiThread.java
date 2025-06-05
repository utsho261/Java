package qns4_a;
/*
Write a program to create three threads. Inside the first thread print your
names 15 times but wait for l second before printing each time. Inside the second
thread print your student ID 10 times. Inside the third thread print your CGPA 10
times. Make sure the second thread gets more OS access than the first thread and
the third thread starts after finishing the second thread.
 */
public class MultiThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() ->{
            for (int i = 0; i < 15; i++) {
                System.out.println("Name: Utsho");
            }
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                System.out.println(e);
            }
        });

        Thread t2 = new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                System.out.println("ID: 22235103261");
            }
        });

        Thread t3 = new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                System.out.println("CGPA: 2.00");
            }
        });
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
        t2.join();
        t3.start();
    }
}
