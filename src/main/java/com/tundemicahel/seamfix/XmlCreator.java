package com.tundemichael.reader;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Michael.Orokola
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/readerQueue")
})
public class XmlCreator implements MessageListener {

    private static final Logger LOG = Logger.getLogger(XmlCreator.class.getName());

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof ObjectMessage) {
                ObjectMessage msg = (ObjectMessage) message;
                Subscriber sub = (Subscriber) msg.getObject();
                File file = new File("C:/Users/TundeMichael/Documents/Reader/"
                + sub.getPhone() + ".xml");
                JAXBContext jaxbContext = JAXBContext.newInstance(Subscriber.class);
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                jaxbMarshaller.marshal(sub, file);
                jaxbMarshaller.marshal(sub, System.out);
            } else if (message instanceof TextMessage) {
                TextMessage msg = (TextMessage) message;
                System.out.println(msg.getText());
            }
        } catch (JMSException | JAXBException e) {
            LOG.log(Level.SEVERE, null, e);
        }
    }

}
