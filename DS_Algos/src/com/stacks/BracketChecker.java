
package com.stacks;

public class BracketChecker {

    public boolean checkBrackets(char[] arr) {
        StackImpl<Character> stack = new StackImpl<Character>();
        for (int i = 0; i < arr.length; i++) {
            switch (arr[i]) {
                case '{':
                case '[':
                case '(':
                    stack.push(arr[i]);
                    break;
                case '}':
                case ']':
                case ')':

                    if (stack.isEmpty() == false) {
                        char chx = stack.pop();
                        if ((chx == '{' && arr[i] != '}') || (chx == '[' && arr[i] != ']')
                                || (chx == '(' && arr[i] != ')')) {
                            // error
                            System.out.println("Error at " + i);
                            return false;
                        }

                    } else {
                        // stack is empty
                        System.out.println("Error at " + i);
                        return false;
                    }
                    break;

                default:
                    break;

            }
        }
        if (stack.isEmpty()) {
            System.out.println("the input is correct");
            return true;
        } else {
            System.out.println("error occured.. Missing the right delimiter" + stack.peek());
            return false;
        }

    }

    public static void main(String[] args) {
        BracketChecker b = new BracketChecker();
        b.checkBrackets("a{b(c[d]e)f}".toCharArray());
    }
}
