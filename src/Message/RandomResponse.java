package Message;

import Interfaces.IResponse;

import java.io.Serializable;

public class RandomResponse implements Serializable, IResponse {
    private int response;

    RandomResponse(int response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return String.valueOf(response);
    }

}
