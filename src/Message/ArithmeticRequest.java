package Message;

import Interfaces.IRequest;
import Interfaces.IResponse;

import java.util.Random;

public class ArithmeticRequest extends ArithmeticOperation implements IRequest {
    private float num1, num2;
    private String destination;
    private Operator operator;

    public ArithmeticRequest(float num1, float num2, Operator operator, String destination) {
        this.num1 = num1;
        this.num2 = num2;
        this.operator = operator;
        this.destination = destination;
    }

    @Override
    public String getDestination() {
        return destination;
    }

    @Override
    public IResponse processRequest() {
        //example purpose
        try {
            Thread.sleep(new Random().nextInt(5000) + 3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ArithmeticResponse(result(num1, num2, operator));
    }

    @Override
    public String toString() {
        return num1 + " " + operator.name() + " " + num2;
    }
}
