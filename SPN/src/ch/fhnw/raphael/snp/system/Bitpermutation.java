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
		
		int[] output = new int[input.length];
		for(int i = 0; i < output.length; i++){
			output[i] = 0;
		}
		for(int i = 0; i < betha.length; i++){
			int bit = (input[i/8] >> i) & 1;
			output[i/8] |= bit << betha[i];
		}
		byte[] o = new byte[output.length];
		for(int i = 0; i < o.length; i ++){
			o[i] = (byte)output[i];
		}
		return o;
		
//		int[] output = new int[input.length];
//		int bm = 0x0080;
//		int counter = 0;
//		for(int i = 0; i < output.length; i ++){
//			output[i] = (byte)0x0000;
//		}
//		for(int i = 0; i < betha.length; i++){
//			byte b = (byte)(bm & (byte)input[i/8]);
//			b = (byte) (b << counter);
//			b = (byte) (b >>> (betha[i]%8));
//			output[i/8] = (byte) (output[i/8] | b);
//			bm = (byte) (bm >>> 1);
//			counter = ++counter % 8;
//			if(counter == 0) bm = (byte) 0x0010;
//		}
//		byte[] o = new byte[output.length];
//		for(int i = 0; i < o.length; i ++){
//			o[i] = (byte)output[i];
//		}
//		
//		return o;
		
		
//		byte[] output = new byte[input.length];
//		byte bm1 = (byte)0x0f;
//		byte bm2 = (byte)0xf0;
//		for(int i = 0; i < betha.length; i++){
//			byte b;
//			if(i%2 == 1){
//				b = (byte) (bm1 & input[i/2]);
//				if(betha[i]%2 == 0) b = (byte) (b << 4);
//			} else {
//				b = (byte) (bm2 & input[i/2]);
//				if(betha[i]%2 == 1) b = (byte) (b >>> 4);
//			}
//			
//			output[betha[i]/2] = (byte) (output[betha[i]/2] | b);
//		}
	}

}
