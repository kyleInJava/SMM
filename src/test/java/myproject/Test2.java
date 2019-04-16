package myproject;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

public class Test2 {

	public static void main(String[] args) {
		
		String str = "hello"; 
		String base64Encoded = Base64.encodeToString(str.getBytes()); 
		String str2 = Base64.decodeToString(base64Encoded); 
		System.out.println(base64Encoded+":"+str2);
		
		String base64Encoded2 = Hex.encodeToString(str.getBytes()); 
		String str3 = new String(Hex.decode(base64Encoded2.getBytes())); 
		System.out.println(base64Encoded2+":"+str3);
		
		String str4  = "hello";
		String salt4 = "123";
		String pwd = new Md5Hash(str4, salt4,1).toString();
		System.out.println(pwd+"+++"+pwd.length());
		
		String str5  = "hello";
		String salt5 = "123";
		String pwd5 = new Sha256Hash(str5, salt5,2).toString();
		System.out.println(pwd5+"+++"+pwd5.length());
		
		String str6  = "hello";
		String salt6 = "123";
		String pwd6 = new SimpleHash("SHA-1",str6, salt6,2).toString();
		System.out.println(pwd6+"+++"+pwd6.length());
		
		String pwd7 = new SimpleHash("SHA-512",str6, salt6,2).toString();
		System.out.println(pwd7+"+++"+pwd7.length());
		
		SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
		randomNumberGenerator.setSeed("123".getBytes());
		String hex = randomNumberGenerator.nextBytes().toHex();
		System.out.println(hex);
		
	}
}
