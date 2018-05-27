import Requester.*;
import Service.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {

    private static final Random random = new Random();

    public static void main(String[] args) {
        List<Service> services = new LinkedList<>();
        List<Requester> requesters = new LinkedList<>();

        for(int i = 0; i < 5; i++) {
            Service service = ServiceFactory.createService();
            services.add(service);
            new Thread(service).start();
        }

        for(int i = 0; i < 10; i++) {
            requesters.add(RequesterFactory.createRequester(services.get(random.nextInt(5))));
        }

        for (Requester requester : requesters) {
            new Thread(requester).start();
        }
    }
}
