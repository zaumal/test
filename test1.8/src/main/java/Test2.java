import java.util.ArrayList;
import java.util.List;

public class Test2 {
	public static void main(String[] args) {
		ThreadLocal<Integer> tl = new ThreadLocal<Integer>();
		tl.set(1);
		
	}
}
