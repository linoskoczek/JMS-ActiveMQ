package Service;

public class ServiceFactory {
    private static int serviceId = 0;

    public static Service createService() {
        return new Service("Service-" + serviceId++);
    }
}
