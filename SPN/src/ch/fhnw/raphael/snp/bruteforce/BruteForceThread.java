package ch.fhnw.raphael.snp.bruteforce;

import java.util.List;
import java.util.Map;

import ch.fhnw.raphael.snp.system.SNP;

public class BruteForceThread extends Thread{
	
	private int times;
	private SNP snp;
	private Map<Byte[], Byte[]> test;
	private List<Byte[]> list;
	
	public BruteForceThread(SNP snp, int times, Map<Byte[], Byte[]> test, List<Byte[]> list){
		this.times = times;
		this.snp = snp;
		this.test = test;
		this.list = list;
	}
	public void run(){
		int round = 0;
		while (round < times){
			boolean foundkey = true;
			//check all known chiffretext if we find maching key
			for(Byte[] b: test.keySet()){
				byte[] bt =  new byte[b.length];
				for(int i = 0; i < bt.length; i++){
					bt[i] = b[i];
				}
				for(int i = 0; i < bt.length; i++){
					//Check if we found a matching key
					if(bt[i] != snp.decode(bt)[i]){
						foundkey = false;
						break;
					}
				}
				//if no key match, we don't need to check for the others
				if(!foundkey) break;
			}
			if(foundkey) list.add(snp.getKey());
			round++;
		}
	}
}
