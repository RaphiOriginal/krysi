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
		BigInteger bigStep = new BigInteger(String.valueOf(step));
		//add step to original hash value
		h = h.add(bigStep);
		for(int i = 0; i < l; i++){
			//modulo new hash with number of characters
			BigInteger r = h.mod(new BigInteger(String.valueOf(z.length)));
			//divide new hash with number of characters
			h = h.divide(new BigInteger(String.valueOf(z.length)));
			//get charachter for r and add the rest of the string on tail
			res = z[r.intValue()] + res;
		}
		return res;
	}
}
