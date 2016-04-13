package ch.fhnw.rainbow;

import java.security.*;

public class Main {
	//mögliche Zeichen der Passwörter
	private char[] z = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
			'w', 'x', 'y','z'};
	private RainbowTable table;
	private Reduktionfunction red;
	
	public Main(){
		table = new RainbowTable();
		red = new Reduktionfunction(z, 7);
		fill();
	}
	
	public static void main(String[] args){
		Main m = new Main();
		System.out.println(m.attack("1d56a37fb6b08aa709fe90e12ca59e12"));
	}
	
	public String attack(String hash){
		String pw = findStartWord(hash);
		String h = hash(pw);
		int counter = 0;
		while(hash != h){
			pw = red.reduction(Long.parseLong(h,16), counter++);
			h = hash(pw);
		}
		return pw;
	}
	
	public String findStartWord(String hash){
		String h = hash;
		String pw = "";
		for(int i = 2000 -1; i > 0 - 1; i--){
			for(int j = i; j < 2000; j++){
				pw = red.reduction(Integer.parseInt(h,16), i);
				h = hash(pw);
			}
			if(table.getStart(pw) != null) return table.getStart(pw);
		}
		return null;
	}
	
	private void fill(){
		String value = "";
		String end = "";
		for(int i = 0; i < 2000; i++){
			int c = i;
			value += z[c % 36];
			while(c/36 > 1){
				if(value.length() < 7){
					value = z[c % 36] + value;
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
		if(step < 4) System.out.println(value);
		String hashtext = hash(value);
		if(step < 4) System.out.println(hashtext);
		String res = red.reduction(Long.parseLong(hashtext,16), step);
		return res;
	}
	
	public String hash(String value){
		MessageDigest ms;
		try {
			ms = MessageDigest.getInstance("MD5");
			ms.reset();
			ms.update(value.getBytes());
			return hexToString(ms.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//found on: http://stackoverflow.com/questions/5886619/hexadecimal-to-integer-in-java
	private String hexToString(byte[] output) {
	    char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
	            'a', 'b', 'c', 'd', 'e', 'f' };
	    StringBuffer buf = new StringBuffer();
	    for (int j = 0; j < output.length; j++) {
	        buf.append(hexDigit[(output[j] >> 4) & 0x0f]);
	        buf.append(hexDigit[output[j] & 0x0f]);
	    }
	    return buf.toString();

	}

}
