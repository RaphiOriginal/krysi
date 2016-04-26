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
	private final int ROUNDS = 2000;
	
	private boolean debug = true;
	
	public Main(){
		table = new RainbowTable();
		red = new Reduktionfunction(z, 7);
		fill();
	}
	
	public static void main(String[] args){
		Main m = new Main();
		try {
			System.out.println(m.attack("1d56a37fb6b08aa709fe90e12ca59e12"));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public String attack(String hash) throws Exception{
		BigInteger reference = new BigInteger(hash, 16);
		//find a matching start value
		String pw = findStartWord(hash);
		//hash the start value
		BigInteger h = hash(pw);
		int counter = 0;
		//search for value until you found one where hash is equal
		while(!reference.equals(h)){
			pw = red.reduction(h, counter++);
			h = hash(pw);
		}
		return pw;
	}
	
	public String findStartWord(String hash) throws Exception {
		BigInteger h = new BigInteger(hash, 16);
		String pw = red.reduction(h, ROUNDS);
		if(table.containsKey(pw)){
			return table.getStart(pw);
		}
		for(int i = ROUNDS; i >=	 0; i--){
			h = new BigInteger(hash, 16);
			for(int j = i; j <= ROUNDS; j++){
				pw = red.reduction(h, j);
				h = hash(pw);
			}
			if(table.containsKey(pw)){
				return table.getStart(pw);
			}
		}
		System.out.println(table.toString());
		throw new Exception("No Start value found!");
	}
	
	private void fill(){
		String value = "";
		String end = "";
		for(int i = 0; i < ROUNDS; i++){
			value = "";
			int c = i;
			value += z[c % z.length];
			c = c/z.length;
			while(c > 1){
				if(value.length() < 7){
					value = z[c % z.length] + value;
				}
				c = c/z.length;
			}
			while(value.length() < 7){
				value = '0' + value;
			}
			end = value;
			for(int j = 0; j <= ROUNDS; j++){
				end = hashreduct(end, j);
			}
			table.addPair(end, value);
		}
		System.out.println("Rainbow table is Ready!");
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
