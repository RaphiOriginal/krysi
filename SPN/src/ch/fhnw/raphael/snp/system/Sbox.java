package ch.fhnw.raphael.snp.system;

public class Sbox {
	
	private int[] box;
	
	public Sbox(int[] box){
		this.box = box;
	}
	
	public int[] getBox(){
		return box;
	}
	
	public int size(){
		return box.length;
	}
	
	public int[] getInvertedBox(){
		//TODO generate inverted box
		return box;
	}

}
