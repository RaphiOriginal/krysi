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
		//TODO encode cleartext
		return clear;
	}
	
	public byte[] decode(byte[] chiffre){
		//TODO decode chiffretext
		return chiffre;
	}

}
