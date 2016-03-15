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
		int[] result = new int[size()];
		for(int i = 0; i < size(); i++){
			result[betha[i]] = i;
		}
		return result;
	}
	
	public int size(){
		return betha.length;
	}

}
