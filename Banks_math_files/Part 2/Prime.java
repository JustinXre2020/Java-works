class Prime {
    // Task 2: main method
    // your code here
    public static void main(String[] args) {
        for (String s : args) {
            if (isNumericInt(s)) {
                int i = Integer.parseInt(s);
                if (isNumPrime(i) && i > 0) {
                    System.out.println(i);
                }
            }
        }
    }

    // isNumPrime helper method
    // Returns true for prime number, false otherwise
    static boolean isNumPrime(int num) {
        boolean isPrime = true;
        
        for (int i = 2; i <= num / 2; ++i) {
            if (num % i == 0) {
                isPrime = false;
                break;
            }
        }

        return isPrime;
    }

    public static boolean isNumericInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}