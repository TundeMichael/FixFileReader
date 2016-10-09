package com.tundemichael.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

/**
 *
 * @author Tunde Michael
 * 
 */

@Singleton
@Startup
public class CSVReader {

    @Resource(mappedName = "jms/readerQueue")
    private Queue seamFixQueue;
    @Inject
    @JMSConnectionFactory("jms/readerConFact")
    private JMSContext context;
    private static final Logger LOG = Logger.getLogger(CSVReader.class.getName());
    int count = 0;

    //@Schedule(minute = "*/1", hour = "*", persistent = false)
    @Schedule(minute = "*/30", hour = "*", persistent = false)
    public void readFile() {
        String csvFile = "C:/Users/TundeMichael/Downloads/Soap UI/subscribers.csv";
        BufferedReader br;
        String line;
        String split = ",";
        Subscriber s;
        ObjectMessage msg;

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] subscribers = line.split(split);
                s = new Subscriber(subscribers[0].trim().replaceAll("\"", ""),
                        subscribers[1].trim().replaceAll("\"", ""),
                        subscribers[2].trim().replaceAll("\"", ""),
                        Integer.parseInt(subscribers[3]));
                msg = (ObjectMessage) context.createObjectMessage();
                msg.setObject(s);
                sendMessage(msg);
                //sendMessage(subscribers[0] + " " + subscribers[1] + " " + subscribers[2] + " " + subscribers[3]);
            }
        } catch (IOException | JMSException e) {
            LOG.log(Level.SEVERE, null, e);
        }
    }

    public void sendMessage(Message messageData) throws JMSException {
        ObjectMessage message = (ObjectMessage) messageData;
        context.createProducer().send(seamFixQueue, message);
    }

    public void sendMessage(String message) throws JMSException {
        context.createProducer().send(seamFixQueue, message);
    }

}

