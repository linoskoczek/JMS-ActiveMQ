package Service;

import Interfaces.IRequest;
import Interfaces.IResponse;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

@SuppressWarnings("Duplicates")
public class Service implements Runnable {
    private String id;
    private Connection connection;
    private Session session;
    private MessageConsumer consumer;

    Service(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public void run() {
        try {
            System.out.println("["+id+"] " + " STARTED");

            startActiveMqAndCreateSession();
            createConsumer();

            while(true) {
                Message message = consumer.receive();

                if(message instanceof ObjectMessage) {
                    IRequest request = (IRequest) ((ObjectMessage) message).getObject();
                    System.out.println("[" + id + "]: Received " + request);

                    IResponse response = request.processRequest();
                    System.out.println("[" + id + "]: Sent " + request);

                    sendResponse(response, request.getDestination());
                }
            }

            //session.close();
            //connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void startActiveMqAndCreateSession() throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        factory.setTrustAllPackages(true);
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    private void createConsumer() throws JMSException {
        Destination myQueue = session.createQueue(id);
        consumer = session.createConsumer(myQueue);
    }

    private void sendResponse(IResponse response, String destination) throws JMSException {
        Destination requester = session.createQueue(destination);
        MessageProducer producer = session.createProducer(requester);
        Message responseMessage = session.createObjectMessage(response);
        producer.send(responseMessage);
    }
}
