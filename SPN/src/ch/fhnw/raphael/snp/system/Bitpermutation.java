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
	
	public byte[] use(byte[] input, int m){
		//TODO implement the use of Bitpermutation
		byte[] output = new byte[input.length];
		byte bm1 = (byte)0x0f;
		byte bm2 = (byte)0xf0;
		for(int i = 0; i < output.length; i ++){
			output[i] = (byte)0x00;
		}
		for(int i = 0; i < betha.length; i++){
			byte b;
			if(i%2 == 1){
				b = (byte) (bm2 & input[i/2]);
				if(betha[i]%2 == 0) b = (byte) (b >>> 4);
			} else {
				b = (byte) (bm1 & input[i/2]);
				if(betha[i]%2 == 1) b = (byte) (b << 4);
			}
			
			output[betha[i]/2] = (byte) (output[betha[i]/2] | b);
		}
		return output;
	}

}
