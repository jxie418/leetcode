/**
 * 
 */
package com.test.jms;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.apache.commons.io.FileUtils;

public class TopicSend {

  public final static String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";
  public final static String JMS_FACTORY = "jms/Synch/ConnectionFactory";

  public final static String TOPIC = "jms/Insight/AxnTaskTopic";

  protected TopicConnectionFactory tconFactory;

  protected TopicConnection tcon;

  protected TopicSession tsession;

  protected TopicPublisher tpublisher;

  protected Topic topic;

  protected TextMessage msg;

  public void init(Context ctx, String topicName) throws NamingException, JMSException {
    tconFactory = (TopicConnectionFactory) PortableRemoteObject.narrow(
        ctx.lookup(JMS_FACTORY),
        TopicConnectionFactory.class);
    tcon = tconFactory.createTopicConnection();
    tsession = tcon.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
    topic = (Topic) PortableRemoteObject.narrow(ctx.lookup(topicName), Topic.class);
    tpublisher = tsession.createPublisher(topic);
    msg = tsession.createTextMessage();
    tcon.start();
  }

  public void send(String message) throws JMSException {
    msg.setText(message);
    msg.setStringProperty("PerformanceGateWaySetup", "true");
    tpublisher.publish(msg);
  }

  public void close() throws JMSException {
    tpublisher.close();
    tsession.close();
    tcon.close();
  }

  public static void main(String[] args) throws Exception {
    InitialContext ic = getInitialContext();
    TopicSend ts = new TopicSend();
    ts.init(ic, TOPIC);
    readFromFileAndSend(ts);
    //readAndSend(ts);
    ts.close();
  }

  protected static void readAndSend(TopicSend ts) throws IOException, JMSException {
    BufferedReader msgStream = new BufferedReader(new InputStreamReader(System.in));
    String line = null;
    do {
      System.out.print("Enter message (\"quit\" to quit): \n");
      line = msgStream.readLine();
      if (line != null && line.trim().length() != 0) {
        
        ts.send(line);
        System.out.println("JMS Message Sent: " + line + "\n");
      }
    } while (line != null && !line.equalsIgnoreCase("quit"));
  }

  protected static void readFromFileAndSend(TopicSend ts) throws IOException, JMSException {
    BufferedReader msgStream = new BufferedReader(new InputStreamReader(System.in));
    String line = null;
    do {
      System.out.print("Enter message (\"quit\" to quit): \n");
      line = msgStream.readLine();
      if (line != null && line.trim().length() != 0) {
        String path = ts.getClass().getResource(line).getPath();
        File file = new File(path);
        String content = FileUtils.readFileToString(file);
        ts.send(content);
        System.out.println("JMS Message Sent: " + content + "\n");
      }
    } while (line != null && !line.equalsIgnoreCase("quit"));
  }

  protected static InitialContext getInitialContext() throws NamingException {
    Hashtable<String, String> env = new Hashtable<String, String>();
    env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
    env.put(Context.PROVIDER_URL, "t3://localhost:7001");
    env.put(Context.SECURITY_PRINCIPAL, "weblogic");
    env.put(Context.SECURITY_CREDENTIALS, "welcome1");
    return new InitialContext(env);
  }
}
