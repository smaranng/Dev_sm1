package Blockchain;
import java.util.*;
import java.security.*;
import javax.crypto.*;
public class SimpleRSA {
	//Generate keypair
	public static KeyPair generateKeyPair() throws Exception
	{
		KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
		keygen.initialize(2048);
		return keygen.generateKeyPair();
	}
	//encrypt using public key
	public static String encrypt(String plaintext, PublicKey pubkey) throws Exception
	{
		Cipher enc = Cipher.getInstance("RSA");
		enc.init(Cipher.ENCRYPT_MODE, pubkey);
		byte[] ciphertext = enc.doFinal(plaintext.getBytes("UTF-8"));
		return Base64.getEncoder().encodeToString(ciphertext);
	}
	public static String decrypt(String ciphertext, PrivateKey privkey) throws Exception
	{
		Cipher dec = Cipher.getInstance("RSA");
		dec.init(Cipher.DECRYPT_MODE, privkey);
		byte[] bytes = Base64.getDecoder().decode(ciphertext.getBytes("UTF-8"));
		return new String(dec.doFinal(bytes), "UTF-8");
	}

	public static void main(String[] args) throws Exception
	{
		//Gen keypair
		KeyPair keypair =  generateKeyPair();
		PublicKey pubkey = keypair.getPublic();
		PrivateKey privkey =keypair.getPrivate();
		String msg="Hello RSA";
		String enc=encrypt(msg, pubkey);
		String dec = decrypt(enc, privkey);
		System.out.println("Original: "+msg+"\nEncrypted Message: "+enc+"\nDecrypted Message: "+dec);
	}

}
