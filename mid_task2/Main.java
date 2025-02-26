package mid_task2;
/*
Let there is a class A which has a member function showA() which
displays "51 intake!!! Best of Luck for your exam !!!". Class C and
class D inherits class A and class E inherits both class C and class D.
Will there any problem if we call showA() function from the object of
class E? If yes, then how can we solve it? Write down the solved
program.
 */
public class Main {
    public static void main(String[] args) {
        E e = new E();
        e.showA();
    }
}
