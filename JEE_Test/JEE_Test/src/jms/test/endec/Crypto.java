package jms.test.endec;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Crypto {

  Cipher cipher ;
  private static final String KEY = "sync123412341234";
  
  public Crypto() {
    try {
      cipher = Cipher.getInstance("AES");
    } catch (NoSuchAlgorithmException e) {     
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {     
      e.printStackTrace();
    }
  }
  
  public String encrypt(String pwd) {
    Key aesKey = new SecretKeySpec(KEY.getBytes(), "AES");
    try {
      cipher.init(Cipher.ENCRYPT_MODE, aesKey);
      byte[] encrypted = cipher.doFinal(pwd.getBytes());
      return new String(encrypted);
    } catch (InvalidKeyException e) {   
      e.printStackTrace();
    } catch (IllegalBlockSizeException e) {    
      e.printStackTrace();
    } catch (BadPaddingException e) {   
      e.printStackTrace();
    }    
    return null;
  }

  public String decrypt(String crypt) {
    Key aesKey = new SecretKeySpec(KEY.getBytes(), "AES");
    try {
      cipher.init(Cipher.DECRYPT_MODE, aesKey);
      byte[] decrypted = cipher.doFinal(crypt.getBytes());
      return new String(decrypted);
    } catch (InvalidKeyException e) {   
      e.printStackTrace();
    } catch (IllegalBlockSizeException e) {    
      e.printStackTrace();
    } catch (BadPaddingException e) {   
      e.printStackTrace();
    }    
    return null;
  }

  public static void main(String[] args) {
    Crypto crypto = new Crypto();
    String encrypt = crypto.encrypt("Password14");
    String decrypt = crypto.decrypt(encrypt);
    if("Password14".equals(decrypt)){
      System.out.println("Encrption Decryption alo works");
    }else {
      System.out.println("It fails");
    }
    
  }

}
