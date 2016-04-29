package com.jing.xie.twilio;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Sms;
public class TwilioTest {

	 public static final String ACCOUNT_SID = "AC8ae1c772561cb2c33f75154e269d0938";
	  public static final String AUTH_TOKEN = "303202fde68aa768d35893c2c93af9a4";
	public static void main(String[] args) throws TwilioRestException {
		// TODO Auto-generated method stub
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
		 
	    // Build a filter for the MessageList
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("To", "+13123799838"); // Replace with a valid phone number
	    params.put("From", "+19252302439"); // Replace with a valid phone number in your account
	    params.put("Body", "This is a test message!");
	    
	 
	    SmsFactory messageFactory = client.getAccount().getSmsFactory();
	    Sms message = messageFactory.create(params);
	    System.out.println(message.getSid());
	}

}
