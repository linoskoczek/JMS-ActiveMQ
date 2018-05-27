package Message;

import Interfaces.IResponse;

public class ArithmeticResponse implements IResponse {
    private float result;

    ArithmeticResponse(float result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return String.valueOf(result);
    }
}
