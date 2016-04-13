package ch.fhnw.rainbow;

public class Reduktionfunction {
	
	private char[] z;
	private int l;
	
	public Reduktionfunction(char[] z, int l){
		this.z = z;
		this.l = l;
	}
	public String reduction(long hash, int step){
		String res = "";
		long h = hash;
		h = h + step;
		for(int i = 0; i < l; i++){
			int r = (int) (h % z.length);
			h = h / z.length;
			res = z[r] + res;
		}
		return res;
	}
}
