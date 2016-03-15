package ch.fhnw.raphael.snp.system;

public class Key {
	
	private byte[] key;
	
	public Key(int size){
		if(size == 0 || size%8 != 0) throw new IllegalArgumentException("Please choose a size which is divideable with 8 and without a rest but can't be '0'");
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
	
	public void addKey(Key key){
		if(key.size() != size()) throw new IllegalArgumentException("Array must have the same size as the key!");
		byte[] roughKey = key.getKey();
		for(int i = 0; i < key.size(); i++){
			this.key[i] += roughKey[i];
		}
	}
	
	public int size(){
		return key.length;
	}
	
	public int byteCount(){
		return key.length * 8;
	}
	
	public boolean equals(Key other){
		if(size() != other.size()) return false;
		for(int i = 0; i < size(); i++){
			if(getKey()[i] != other.getKey()[i]) return false;
		}
		return true;
	}

}
