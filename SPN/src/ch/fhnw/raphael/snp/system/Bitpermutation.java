package ch.fhnw.raphael.snp.system;

public class Bitpermutation {
	
	private int[] betha;
	
	public Bitpermutation(int[] betha){
		this.betha = betha;
	}
	
	public int[] getPermutation(){
		return betha; 
	}
	
	public int[] getInvertedPermutation(){
		//TODO invert the array
		return betha;
	}
	
	public int size(){
		return betha.length;
	}

}
