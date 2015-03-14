package com.test.jms;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JMSReceiver implements MessageListener {
	private static InitialContext context = null;
	private static final String QUEUE_NAME = "jms/TestJMSQueue";
	private static final String CON_FACTORY = "jms/TestConnectionFactory";
	private static QueueConnection qc = null;
	private static QueueConnectionFactory qcf = null;
	private static QueueSession qs = null;
	private static Queue queue = null;
	private static TextMessage message = null;
	private QueueReceiver receiver;

	// private static QueueReceiver = null;

	@Override
	public void onMessage(Message arg0) {
		Hashtable properties = setProperty();
		try {
			context = new InitialContext(properties);
			qcf = (QueueConnectionFactory) context.lookup(CON_FACTORY);
			qc = qcf.createQueueConnection();
			qs = qc.createQueueSession(false, 0);
			queue = (Queue) context.lookup(QUEUE_NAME);
			receiver = qs.createReceiver(queue);
			receiver.setMessageListener(this);
			qc.start();
			String textMessage = null;
			if (arg0 instanceof TextMessage) {
				textMessage = ((TextMessage) arg0).getText();
			}

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static Hashtable setProperty() {
		Hashtable properties = new Hashtable();
		properties.put(Context.INITIAL_CONTEXT_FACTORY,
				"weblogic.jndi.WLInitialContextFactory");
		// NOTE: The port number of the server is provided in the next line,
		// followed by the userid and password on the next two lines.
		properties.put(Context.PROVIDER_URL, "t3://localhost:7001");
		properties.put(Context.SECURITY_PRINCIPAL, "weblogic");
		properties.put(Context.SECURITY_CREDENTIALS, "welcome1");
		return properties;
	}

}
