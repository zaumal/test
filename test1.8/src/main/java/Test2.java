import java.util.ArrayList;
import java.util.List;

public class Test2 {
	public static void main(String[] args) {
		ThreadLocal<Integer> tl = new ThreadLocal<Integer>();
		tl.set(1);
		
		int n = 0;
		for(int i = 0; i < 10; i++) {
			n = n++;
		}
		System.out.println(n);
	}
}

class BiTNode<T>{
	private T t;
	private BiTNode<T> lChild,rChild;
	
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	public BiTNode<T> getlChild() {
		return lChild;
	}
	public void setlChild(BiTNode<T> lChild) {
		this.lChild = lChild;
	}
	public BiTNode<T> getrChild() {
		return rChild;
	}
	public void setrChild(BiTNode<T> rChild) {
		this.rChild = rChild;
	}
}
