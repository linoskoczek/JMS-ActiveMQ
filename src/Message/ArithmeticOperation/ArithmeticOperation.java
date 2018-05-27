package Message;

abstract class ArithmeticOperation {

    float result(float num1, float num2, Operator operator) {
        switch (operator) {
            case ADD:
                return add(num1, num2);
            case DIVIDE:
                return divide(num1, num2);
            case MULTIPLY:
                return multiply(num1, num2);
            case SUBTRACT:
                return subtract(num1, num2);
            default:
                throw new IllegalArgumentException("Incorrect operator!");
        }
    }

    float add(float num1, float num2) {
        return num1 + num2;
    }

    float subtract(float num1, float num2) {
        return num1 - num2;
    }

    float multiply(float num1, float num2) {
        return num1 * num2;
    }

    float divide(float num1, float num2) throws IllegalArgumentException {
        return num1 / num2;
    }
}
