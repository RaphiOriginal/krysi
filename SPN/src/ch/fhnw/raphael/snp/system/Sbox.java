package ch.fhnw.raphael.snp.system;

import java.util.HashMap;
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
		return boxing(input, box);
	}
	public byte[] useInvers(byte[] input){
		return boxing(input, inverse());
	}
	private byte[] boxing(byte[] input, Map<Byte, Byte> box){
		//TODO implement box
		byte bm1 = (byte)0xf0;
		byte bm2 = (byte)0x0f;
		
		return input;
	}
	private Map<Byte, Byte> inverse(){
		Map<Byte, Byte> inverse = new HashMap<Byte, Byte>();
		for(Byte b: box.keySet()){
			inverse.put(box.get(b), b);
		}
		return inverse;
	}

}
