/**
 * 
 */
package com.jing.xie;

import javapns.Push;
import javapns.communication.exceptions.CommunicationException;

/**
 * @author jamesxieaudaexplorecom
 *
 */
public class JavaPush2 {

	/**
	 * @param args
	 * @throws Exception 
	 * @throws CommunicationException 
	 */
	public static void main(String[] args) throws CommunicationException, Exception {
		// TODO Auto-generated method stub
		 Push.alert("You Estimate is Ready!", "apn_production.p12", "password", true, "6af08827715c5a98f8a19be6034750695e280a431698bf8a2be7287bd28ab93b");
		 Push.alert("You Estimate is Ready!", "apn_production2.p12", "password", true, "6af08827715c5a98f8a19be6034750695e280a431698bf8a2be7287bd28ab93b");
		 Push.alert("You Estimate is Ready!", "apns-dev-cert.p12", "password", false, "db35d9cf3d066446daea55a67188d1d59b83a1d597c87481ac84c87a1f8766a7");
	}

}
