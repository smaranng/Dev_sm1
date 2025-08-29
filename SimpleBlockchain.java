package Blockchain;
import java.util.*;
import java.security.*;
class Block
{
	public String hash;
	public String previoushash;
	private String data;
	private long timestamp;
	private int nonce;
	
	public Block(String data, String previoushash)
	{
		this.data=data;
		this.previoushash=previoushash;
		this.hash=calculateHash();
		this.timestamp=new Date().getTime();
	}
	public String calculateHash()
	{
		String input = previoushash+Long.toString(timestamp)+Integer.toString(nonce)+data;
		return applySHA(input);
	}
	public String applySHA(String input)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hashbytes = md.digest(input.getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			for(byte b:hashbytes)
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
	public void mineblock(int diff)
	{
		String target = new String(new char[diff]).replace('\0', '0');
		while(!hash.substring(0,diff).equals(target))
		{
			nonce++;
			hash=calculateHash();
		}
		System.out.println("Block mined: "+hash);
	}
}
public class SimpleBlockchain {
	int diff=4;
	ArrayList<Block> blockchain = new ArrayList<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}


}
