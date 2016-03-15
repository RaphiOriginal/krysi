package ch.fhnw.raphael.snp.system;

public class SNP {
	
	private int r;
	private int n;
	private int m;
	private Sbox box;
	private Bitpermutation bit;
	private int s;
	private byte[] key;
	
	public SNP(int r, int n, int m, Sbox box, Bitpermutation bit, int s, byte[] key){
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
