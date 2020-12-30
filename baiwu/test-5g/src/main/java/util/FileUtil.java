package util;

import java.io.Closeable;
import java.io.IOException;

public class FileUtil {
	public static void close(Closeable closeable) {
		try {
			closeable.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
