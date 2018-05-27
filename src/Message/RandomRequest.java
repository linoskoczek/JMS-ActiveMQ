package Message;

import Interfaces.IRequest;
import Interfaces.IResponse;

import java.util.Random;

public class RandomRequest implements IRequest {

    private int rangeFrom, rangeTo;
    private String requestFrom;

    public RandomRequest(int rangeFrom, int rangeTo, String requestFrom) {
        if(rangeFrom > rangeTo)
            throw new IllegalArgumentException("'Range from' must be smaller or equal 'range to'!");
        this.rangeFrom = rangeFrom;
        this.rangeTo = rangeTo;
        this.requestFrom = requestFrom;
    }

    @Override
    public String getDestination() {
        return requestFrom;
    }

    @Override
    public IResponse processRequest() {
        //example purpose
        try {
            Thread.sleep(new Random().nextInt(5000) + 3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new RandomResponse(new Random().nextInt(rangeTo) + rangeFrom);
    }

    @Override
    public String toString() {
        return "Random number between " + rangeFrom + " and " + rangeTo;
    }
}
