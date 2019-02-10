package section2;

public class Term {
	
	public int coef;
	public int expo;
	
	public Term(int c, int e) {
		this.coef = c;
		this.expo = e;
	}
	
	public int calc(int x) {
		return coef * (int)Math.pow(x, expo);
	}
}
