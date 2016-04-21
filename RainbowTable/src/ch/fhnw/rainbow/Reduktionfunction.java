package ch.fhnw.rainbow;

import java.math.BigInteger;

public class Reduktionfunction {
	
	private char[] z;
	private int l;
	
	public Reduktionfunction(char[] z, int l){
		this.z = z;
		this.l = l;
	}
	public String reduction(BigInteger hash, int step){
		String res = "";
		BigInteger h = hash;
		BigInteger bigStep = new BigInteger(""+step);
		h.add(bigStep);
		for(int i = 0; i < l; i++){
			BigInteger r = h.mod(new BigInteger(""+z.length));
			h = h.divide(new BigInteger(""+z.length));
			res = z[r.intValue()] + res;
		}
		return res;
	}
}
