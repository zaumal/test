package test;

import java.io.File;
import java.util.UUID;

public class Test {
	public static void main(String[] args) {
		String uuid = UUID.randomUUID().toString() +"12345sdjfsdf";
		
		
		System.out.println(uuid + "   " + uuid.length());
		
		System.out.println(uuid.substring(36));
		
		
		String t = "tmp/1/2/3/4";
		
		File dir = new File(t);
		
		if(!dir.exists()) {
			dir.mkdirs();
		}
		System.out.println(dir.getAbsolutePath());
	}
}
