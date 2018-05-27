package Requester;

import Interfaces.IRequest;
import Interfaces.IResponse;
import Service.*;
import Message.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

@SuppressWarnings("Duplicates")
public class Requester implements Runnable {
    private String id;
    private Service service;
    private Connection connection;
    private Session session;
    private MessageConsumer consumer;
    private MessageProducer producer;

    Requester(String id, Service service) {
        this.id = id;
        this.service = service;
    }

    @Override
    public void run() {
        try {
            System.out.println("["+id+"] " + " STARTED");

            startActiveMqAndCreateSession();

            createMessageProducer();
            sendMessage();

            createMessageConsumer();
            receiveMessage();

            finish();
        } catch(JMSException e) {
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

    private void createMessageProducer() throws JMSException {
        Destination destinationService = session.createQueue(service.getId());
        producer = session.createProducer(destinationService);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
    }

    private void sendMessage() throws JMSException {
        IRequest randomRequest = new RandomRequest(1,100, this.id);
        Message message = session.createObjectMessage(randomRequest);
        System.out.println("["+id+"] " + randomRequest);
        producer.send(message);
    }

    private void createMessageConsumer() throws JMSException {
        Destination myQueue = session.createQueue(id);
        consumer = session.createConsumer(myQueue);
    }

    private void receiveMessage() throws JMSException {
        Message responseMessage = consumer.receive();
        if(responseMessage instanceof ObjectMessage) {
            IResponse response = (IResponse) ((ObjectMessage) responseMessage).getObject();
            System.out.println("[" + id + "] Got result: " + response);
        }
    }

    private void finish() throws JMSException {
        System.out.println("["+id+"] finished his job. Elo.");
        session.close();
        connection.close();
    }
}
