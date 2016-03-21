package ch.fhnw.raphael.snp.system;

public class SNP {
	
	private int r;
	private int n;
	private int m;
	private Sbox box;
	private Bitpermutation bit;
	private int s;
	private Key key;
	
	public SNP(int r, int n, int m, Sbox box, Bitpermutation bit, int s, Key key){
		int size = n * m;
		if(size == 0 || size%8 != 0) throw new IllegalArgumentException("n * m musst be devideable with 8!");
		if(s == 0 || s%8 != 0) throw new IllegalArgumentException("s must be devideable with 8!");
		if(box == null || key == null) throw new NullPointerException("SBox and Key are nessesary!");
		if(r < 2) throw new IllegalArgumentException("At least two rounds nessesary!");
		this.r = r;
		this.n = n;
		this.m = m;
		this.box = box;
		this.bit = bit;
		this.s = s;
		this.key = key;
	}
	
	public byte[] encode(byte[] clear){
		int round = 0;
		
		//initialer Weissschritt
		byte[] chiffre = xor(clear, getRoundKey(round++));
		//reguläre Runden
		while(round < r){
			chiffre = box.use(chiffre);
			chiffre = bit.use(chiffre, m);
			chiffre = xor(chiffre, getRoundKey(round++));
		}
		//verkürzte Runde
		chiffre = box.use(chiffre);
		return xor(chiffre, getRoundKey(round));
	}
	
	public byte[] decode(byte[] chiffre){
		int round = r;
		
		//initialer Weissschritt
		byte[] clear = xor(chiffre, getRoundKey(round--));
		//reguläre Runden
		while(round > 0){
			clear = box.useInvers(clear);
			clear = bit.use(clear, m);
			clear = xor(clear, getRoundKey(round--));
		}
		//verkürzte Runde
		clear = box.useInvers(clear);
		return xor(clear, getRoundKey(round));
	}
	
	private byte[] xor(byte[] one, byte[] two){
		byte[] res = new byte[one.length];
		for(int i = 0; i < one.length; i++){
			res[i] = (byte) (one[i] ^ two[i]);
		}
		return res;
	}
	
	private byte[] getRoundKey(final int round){
		final int size = (m*n)/8;
		byte[] roundKey = new byte[size];
		byte[] fullKey = key.getKey();
		final int index = round/8;
		
		for(int i = index; i < index + size; i++){
			byte b1 = fullKey[i];
			byte b2 = fullKey[i + 1];
			b1 = (byte) (b1 << round);
			b2 = (byte) (b2 >>> 8-round);
			roundKey[i] = (byte)(0xff & (b1 | b2));
		}
		
		return roundKey;
	}

}
