package Requester;

import Service.Service;

public class RequesterFactory {
    private static int requesterId = 0;

    public static Requester createRequester(Service service) {
        return new Requester("Requester-" + requesterId++, service);
    }
}
