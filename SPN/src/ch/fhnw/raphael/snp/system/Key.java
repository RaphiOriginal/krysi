package ch.fhnw.raphael.snp.system;

public class Key {
	
	private byte[] key;
	
	public Key(int size){
		if(size%8 != 0) throw new IllegalArgumentException("Please chose a size which is divideable with 8 and without a rest");
		key = new byte[size/8];
		for(int i = 0; i < key.length; i++){
			key[i] = (byte)0b00000000;
		}
	}
	
	public Key(byte[] key){
		this.key = key;
	}
	
	public byte[] getKey(){
		return key;
	}
	
	public void setKey(byte[] key){
		this.key = key;
	}
	
	public void addKey(byte[] key){
		if(key.length != this.key.length) throw new IllegalArgumentException("Array must have the same size as the key!");
		for(int i = 0; i < key.length; i++){
			this.key[i] += key[i];
		}
	}
	
	public int size(){
		return key.length;
	}
	
	public int byteCount(){
		return key.length * 8;
	}

}
