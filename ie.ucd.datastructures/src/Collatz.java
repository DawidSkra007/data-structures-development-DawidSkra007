public class Collatz {

    public static int Collatz(int n) throws IllegalArgumentException{
        if (n < 1) {
            throw new IllegalArgumentException("number provided must be positive");
        }
        if (n == 1) {
            return 1;
        } else if (n % 2 == 0) {
            return  Collatz(n /= 2);
        } else {
            return  Collatz(n = (n * 3) +1);
        }
    }

    public static void main(String[] args) {
        System.out.println(Collatz(22));
        System.out.println(Collatz(243534));
    }
}
