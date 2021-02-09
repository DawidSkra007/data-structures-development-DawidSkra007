public class BracketChecker {

    public static boolean checkParentheses(String in) {
        ArrayStack<Character> stack = new ArrayStack<>();

        if (in.isEmpty()) {//simple initial check
            return false;
        }

        for (int i = 0; i < in.length(); i++) {//reads string

            char b = in.charAt(i);
            if ((b == '{') || (b == '(') || (b == '[')) {//pushes opening delimitter onto stack
                stack.push(b);
            }
            if ((b == '}') || (b == ')') || (b == ']')) {
                if (stack.isEmpty()) {//then ^ are first in string,so must be false
                    return false;
                }
                char top = stack.top();
                //checks is the delimitters match
                if ((b == '}' && top == '{') || (b == ')' && top == '(') || (b == ']' && top == '[')) {
                    stack.pop();
                }
                else {
                    return false;
                }
            }
        }
        return (stack.isEmpty());
    }



    public static void main(String[] args) {
        String[] inputs = {
                "[]]()()",//NO
                "c[d]",//YES
                "a{b[c]d}e",//YES
                "a{b(c]d}e",//NO
                "a[b{c}d]e}",//NO
                "a{b(c)",//NO
                "][]][][[]][]][][[[",//NO
                "(((abc))((d)))))" };//

        for (String input:  inputs) {
            boolean isBalanced = BracketChecker.checkParentheses(input);
            System.out.println("isBalanced " + (isBalanced ? "Yes " : "No ") + input);
        }
    }
}
