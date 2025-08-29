package Blockchain;
import java.util.*;
import java.security.*;
public class digital_signature {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//Gen keys
		KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
		keygen.initialize(2048);
		KeyPair keypair = keygen.generateKeyPair();
		PublicKey pubkey = keypair.getPublic();
		PrivateKey privkey = keypair.getPrivate();
		String transaction="Sender: Alice, Receiver: Bob, Amount:100";
		System.out.println("Transaction: "+transaction);
		
		//Sign using privkey
		Signature sign = Signature.getInstance("SHA256withRSA");
		sign.initSign(privkey);
		sign.update(transaction.getBytes("UTF-8"));
		byte[] signbytes = sign.sign();
		String signature = Base64.getEncoder().encodeToString(signbytes);
		System.out.println("Digital Signature: "+signature);
		
		//Verify using public key
		Signature verifysign = Signature.getInstance("SHA256withRSA");
		verifysign.initVerify(pubkey);
		verifysign.update(transaction.getBytes("UTF-8"));
		boolean isver = verifysign.verify(signbytes);
		System.out.println("Is verified: "+isver);
	}

}
