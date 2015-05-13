/**
 * 
 */
package com.jing.xie;
import javapns.Push;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
/**
 * @author jamesxieaudaexplorecom
 *
 */
public class JavaPush {

	/**
	 * @param args
	 * @throws KeystoreException 
	 * @throws CommunicationException 
	 */
	public static void main(String[] args) throws CommunicationException, KeystoreException {
		// TODO Auto-generated method stub
		 Push.alert("You Estimate is Ready!", "apns-dev-cert.p12", "password", false, "db35d9cf3d066446daea55a67188d1d59b83a1d597c87481ac84c87a1f8766a7");
	}

}
