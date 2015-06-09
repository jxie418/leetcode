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
		 Push.alert("You Estimate is Ready!", "apn_development.p12", "password", false, "db35d9cf3d066446daea55a67188d1d59b83a1d597c87481ac84c87a1f8766a7");
		 Push.alert("You Estimate is Ready!", "apn_development.p12", "password", false, "65d3567a886b53db22e801af44a096775696818e45a26601ca734e76db0c41b5");
		 Push.alert("You Estimate is Ready!", "apn_development.p12", "password", false, "5bc8a616969bb96f49716b8ad577f940dd4cf040d8c6d52dc3592473135ae9b7");
		 Push.alert("You Estimate is Ready!", "apn_development.p12", "password", false, "ed231a99d2a145a85faa86fae7bff4635dded0eadb677f7ca89eefd17b6038ee");
		 Push.alert("You Estimate is Ready!", "apn_development.p12", "password", false, "cee17c0aadfc9fafa256f8e6ec59571a458bc91ef00ad676316aa342245631e8");
		 Push.alert("You Estimate is Ready!", "apn_development.p12", "password", false, "abc5ff419a5f1c378880de9197570f284842e020d4ebadb22d06a0b984fe0135");
		 Push.alert("You Estimate is Ready!", "apn_development.p12", "password", false, "f43fabd21f586a97f42ef0c7749745f80754883bca99f2399e9ec284db417fa9");

		 Push.alert("You Estimate is Ready!", "apn_development.p12", "password", false, "1835988db57373b7c18454db0adcaea85007ac0c980d8d1a1420d9a6f64302ae");
		 Push.alert("You Estimate is Ready!", "apn_development.p12", "password", false, "25f193a15f2fcf2d08fcfa9553defe0f4ab63aa71496bdd921c8c30dd4b71a7c");
		 Push.alert("You Estimate is Ready!", "apn_development.p12", "password", false, "e4c52e7c158f8c53372107781f32a4c594be80c96c7db1015609456f9cb02dc3");
		 
	}

}
