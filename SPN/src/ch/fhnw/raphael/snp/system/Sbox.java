package ch.fhnw.raphael.snp.system;

public class Sbox {
	
	private byte[] box;
	
	public Sbox(byte[] box){
		this.box = box;
	}
	
	public byte[] getBox(){
		return box;
	}
	
	public int size(){
		return box.length;
	}

}
