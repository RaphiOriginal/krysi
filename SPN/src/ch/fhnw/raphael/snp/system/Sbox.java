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
		return boxing(input, this.box);
	}
	public byte[] useInvers(byte[] input){
		return boxing(input, inverse());
	}
	private byte[] boxing(byte[] input, Map<Byte, Byte> box){
		//TODO implement box
		byte bm1 = (byte)0xf0;
		byte bm2 = (byte)0x0f;
		byte[] result = new byte[input.length];
		for(int i = 0; i < input.length; i++) {
			byte tmp = (byte) (bm1 & input[i]);
			tmp = (byte) (tmp >>> 4);
			tmp = (byte) (bm2 & tmp);
			result[i] = (byte) box.get(tmp);
			result[i] = (byte) (result[i] << 4);
			tmp = (byte) (bm2 & input[i]);
			result[i] = (byte) (result[i] | (byte) box.get(tmp));
		}
		return result;
	}
	private Map<Byte, Byte> inverse(){
		Map<Byte, Byte> inverse = new HashMap<Byte, Byte>();
		for(Byte b: this.box.keySet()){
			inverse.put(this.box.get(b), b);
		}
		return inverse;
	}

}
