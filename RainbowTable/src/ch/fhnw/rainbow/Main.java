package ch.fhnw.rainbow;

import java.math.BigInteger;
import java.security.*;

public class Main {
	//mögliche Zeichen der Passwörter
	private char[] z = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
			'w', 'x', 'y','z'};
	private RainbowTable table;
	private Reduktionfunction red;
	
	private boolean debug = true;
	
	public Main(){
		table = new RainbowTable();
		red = new Reduktionfunction(z, 7);
		fill();
	}
	
	public static void main(String[] args){
		Main m = new Main();
		try {
			System.out.println(m.attack(new BigInteger("1d56a37fb6b08aa709fe90e12ca59e12", 16)));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String attack(BigInteger hash) throws Exception{
		String pw = findStartWord(hash);
		BigInteger h = hash(pw);
		int counter = 0;
		while(hash != h){
			pw = red.reduction(h, counter++);
			h = hash(pw);
		}
		return pw;
	}
	
	public String findStartWord(BigInteger hash) throws Exception {
		BigInteger h = hash;
		String pw = "";
		for(int i = 2000 -1; i > 0 - 1; i--){
			for(int j = i; j < 2000; j++){
				pw = red.reduction(h, i);
				h = hash(pw);
			}
			if(table.getStart(pw) != null) {
				return table.getStart(pw);
			}
		}
		throw new Exception("No Start value found!");
	}
	
	private void fill(){
		String value = "";
		String end = "";
		for(int i = 0; i < 2000; i++){
			int c = i;
			value += z[c % z.length];
			while(c/36 > 1){
				if(value.length() < 7){
					value = z[c % z.length] + value;
				}
				c /= 36;
			}
			while(value.length() < 7){
				value = '0' + value;
			}
			end = value;
			for(int j = 0; j < 2000; j++){
				end = hashreduct(end, j);
			}
			table.addPair(end, value);
		}
	}
	
	public String hashreduct(String value, int step){
		if(debug && step == 4) debug = false;
		if(debug) System.out.println(value);
		BigInteger hashtext = hash(value);
		String res = red.reduction(hashtext, step);
		return res;
	}
	
	public BigInteger hash(String value){
		MessageDigest ms;
		try {
			ms = MessageDigest.getInstance("MD5");
			ms.reset();
			ms.update(value.getBytes());
			return new BigInteger(1, ms.digest());
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Oh, oooooooh!");
			e.printStackTrace();
		}
		return null;
	}
}
