package ch.fhnw.raphael.snp.bruteforce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.fhnw.raphael.snp.system.Bitpermutation;
import ch.fhnw.raphael.snp.system.Key;
import ch.fhnw.raphael.snp.system.SNP;
import ch.fhnw.raphael.snp.system.Sbox;

public class BruteForce {

	public static void main(String[] args) {
		//prepare known chiffre and clear text for brute force
		Map<Byte[], Byte[]> known = new HashMap<>();
		Byte[] ch1 = {(byte)0b00100110, (byte)0b10110111};
		Byte[] ct1 = {(byte)0b10111100, (byte)0b11010110};
		known.put(ch1, ct1);
		Byte[] ch2 = {(byte)0b00000110, (byte)0b10110111};
		Byte[] ct2 = {(byte)0b00100101, (byte)0b00110101};
		known.put(ch2, ct2);
		Byte[] ch3 = {(byte)0b01100001, (byte)0b10110111};
		Byte[] ct3 = {(byte)0b10010100, (byte)0b01111111};
		known.put(ch3, ct3);
		Byte[] ch4 = {(byte)0b01100001, (byte)0b00000010};
		Byte[] ct4 = {(byte)0b11010111, (byte)0b11111100};
		known.put(ch4, ct4);
		Byte[] ch5 = {(byte)0b11110001, (byte)0b00101100};
		Byte[] ct5 = {(byte)0b11110100, (byte)0b00111001};
		known.put(ch5, ct5);
		Byte[] ch6 = {(byte)0b11110101, (byte)0b11111100};
		Byte[] ct6 = {(byte)0b00100111, (byte)0b10110011};
		known.put(ch6, ct6);
		Byte[] ch7 = {(byte)0b01011111, (byte)0b00001100};
		Byte[] ct7 = {(byte)0b10010011, (byte)0b00010010};
		known.put(ch7, ct7);
		
		//sbox
		Map<Byte, Byte> boxMap = new HashMap<>();
		boxMap.put((byte)0x00, (byte)0x0E);
		boxMap.put((byte)0x01, (byte)0x04);
		boxMap.put((byte)0x02, (byte)0x0D);
		boxMap.put((byte)0x03, (byte)0x01);
		boxMap.put((byte)0x04, (byte)0x02);
		boxMap.put((byte)0x05, (byte)0x0F);
		boxMap.put((byte)0x06, (byte)0x0B);
		boxMap.put((byte)0x07, (byte)0x08);
		boxMap.put((byte)0x08, (byte)0x03);
		boxMap.put((byte)0x09, (byte)0x0A);
		boxMap.put((byte)0x0A, (byte)0x06);
		boxMap.put((byte)0x0B, (byte)0x0C);
		boxMap.put((byte)0x0C, (byte)0x05);
		boxMap.put((byte)0x0D, (byte)0x09);
		boxMap.put((byte)0x0E, (byte)0x00);
		boxMap.put((byte)0x0F, (byte)0x07);
		final Sbox box = new Sbox(boxMap);
		
		//bit permutation
		final int[] bitArray = {0, 4, 8, 12, 1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15};
		final Bitpermutation bit = new Bitpermutation(bitArray);
		
		//list for the results
		List<Byte[]> list = Collections.synchronizedList(new ArrayList<Byte[]>());
		
		//snp informations
		final int r = 4;
		final int n = 4;
		final int m = 4;
		final int s = 32;
		
		//4 snp classes with seperate keys and threads
		byte[] k1 = {(byte)0x00,(byte)0x00};
		Key key1 = new Key(k1);
		SNP snp1 = new SNP(r, n, m, box, bit, s, key1);
		Thread t1 = new BruteForceThread(snp1, 16384, known, list);
		t1.start();
		
		byte[] k2 = {(byte)0x40,(byte)0x00};
		Key key2 = new Key(k2);
		SNP snp2 = new SNP(r, n, m, box, bit, s, key2);
		Thread t2 = new BruteForceThread(snp2, 16384, known, list);
		t2.start();
		
		byte[] k3 = {(byte)0x80,(byte)0x00};
		Key key3 = new Key(k3);
		SNP snp3 = new SNP(r, n, m, box, bit, s, key3);
		Thread t3 = new BruteForceThread(snp3, 16384, known, list);
		t3.start();
		
		byte[] k4 = {(byte)0xc0,(byte)0x00};
		Key key4 = new Key(k4);
		SNP snp4 = new SNP(r, n, m, box, bit, s, key4);
		Thread t4 = new BruteForceThread(snp4, 16384, known, list);
		t4.start();

		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//check how many results we got!
		if(list.size() != 1) {
			//we failed with find just one key
			System.out.println("Der Key konnte nicht gefunden werden, da es " + list.size() + " möglichkeiten gibt...");
		} else {
			//we found just one key, so we can decode
			byte[] k = new byte[list.get(0).length];
			for(int i = 0; i < k.length; i++){
				k[i] = (byte)list.get(0)[i];
			}
			Key key = new Key(k);
			SNP snp = new SNP(r, n, m, box, bit, s, key);
			byte[] chiffre = {(byte)0b01011010, (byte)0b00111111};
			System.out.println("Der gesuchte Klartext ist: " + snp.decode(chiffre));
		}
		
		

	}

}
