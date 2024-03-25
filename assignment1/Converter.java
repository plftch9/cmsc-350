import java.util.*;

public class Converter {

    public String prefix(String formula) throws SyntaxError {

        Stack<String> reversalStack = new Stack<String>();
        Stack<String> operandStack = new Stack<String>();

        String[] token;

        if (formula.contains(" ")) {
            token = formula.split(" ");
        } else {
            token = formula.split("");
        }

        for (int i = 0; i < token.length; i++) {
            reversalStack.push(token[i]);
        }

        while (!reversalStack.isEmpty()) {
            if (!operatorBool(reversalStack.peek())) {
                operandStack.push(reversalStack.pop());
            } else {
                try {
                    String operand = operandStack.pop();
                    String operand2 = operandStack.pop();
                    String operator = reversalStack.pop();

                    String expression = operand + " " + operand2 + " " + operator;
                    operandStack.push(expression);
                } catch (EmptyStackException e) {
                    throw new SyntaxError(formula);
                }
            }
        }
        String newExpression = operandStack.pop();
        if (!operandStack.isEmpty()) {
            throw new SyntaxError(newExpression);
        }
        return newExpression;
    }

    public String postfix(String formula) throws SyntaxError {
        Stack<String> stack = new Stack<String>();
        Stack<String> stack2 = new Stack<String>();

        String[] token;

        if (formula.contains(" ")) {
            token = formula.split(" ");
        } else {
            token = formula.split("");
        }

        while (!stack.isEmpty()) {
            stack2.push(stack.pop());
        }
        for (int i = token.length - 1; i >= 0; i--) {
            stack2.push(token[i]);
        }

        while (!stack2.isEmpty()) {
            if (!operatorBool(stack2.peek())) {
                stack.push(stack2.pop());
            } else {
                try {
                    String operand2 = stack.pop();
                    String operand = stack.pop();

                    String operator = stack2.pop();
                    String expression = operator + " " + operand + " " + operand2;

                    stack.push(expression);
                } catch (EmptyStackException e) {
                    throw new SyntaxError(formula);
                }
            }
        }
        String newExpression = stack.pop();
        if (!stack.isEmpty()) {
            throw new SyntaxError(newExpression);

        }
        return newExpression;
    }

    public boolean operatorBool(String character) {
        switch (character) {
            case ("/"):
            case ("*"):
            case ("+"):
            case ("-"):
                return true;
        }
        return false;
    }

}
