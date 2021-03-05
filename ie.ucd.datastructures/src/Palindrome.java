public class Palindrome {
    public static boolean isPalindrome(String input) {
        input = input.toLowerCase().replaceAll("\\s+","").replaceAll("[^a-zA-Z ]", "");
        int n = input.length();

        if (n == 0) {
            return true;
        }

        return isPalindromeHelper(input,0,n - 1);
    }

    private static boolean isPalindromeHelper(String input, int first,int last) {
        if (first == last) { // only one char
            return true;
        }

        if (input.charAt(first) != input.charAt(last)) {// if chars at these two indexes are different, the string cannot be a palindrome
            return false;
        }

        if (first < last + 1) {// if input string is bigger than 2
            return isPalindromeHelper(input,first + 1,last - 1);
        }
        return true;
    }

    public static void main(String[] args) {
        String[] inputs = {
                "Racecar",
                "Radar",
                "Step on no pets",
                "Top spot",
                "Was it a cat I saw?",
                "eva,can I see bees in a cave?",
                "no lemon, no melon"
        };

        for(String input : inputs) {
            System.out.println(Palindrome.isPalindrome(input) + " ←--> " + input);
        }
    }

}
