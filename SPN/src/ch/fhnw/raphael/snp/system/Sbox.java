package ch.fhnw.raphael.snp.system;

import java.util.Map;

public class Sbox {
	
	private Map<Byte, Byte> box;
	
	public Sbox(Map<Byte, Byte> box){
		this.box = box;
	}
	
	public Map<Byte, Byte>getBox(){
		return box;
	}
	
	public int size(){
		return box.size();
	}
	
	public byte[] use(byte[] input){
		//TODO implement box using
		return input;
	}

}
