package Blockchain;
import java.security.*;
public class sha256 {
	public static String applySHA256(String input)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hashbytes = md.digest(input.getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			
			for(byte b: hashbytes)
			{
				String hex=Integer.toHexString(0xff&b);
				if(hex.length()==1)hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static void main(String[] args)
	{
		String msg="Hello RSA";
		String enc=applySHA256(msg);
		System.out.println("Org: "+msg+"\nEncrypted message using SHA-256: "+enc);
	}

}
