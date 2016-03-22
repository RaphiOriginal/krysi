package ch.fhnw.raphael.snp.system.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.raphael.snp.system.Bitpermutation;
import ch.fhnw.raphael.snp.system.Key;
import ch.fhnw.raphael.snp.system.SNP;
import ch.fhnw.raphael.snp.system.Sbox;

public class SNPtest {
	
	private SNP snp;
	
	@Before
	public void setUp() {
		final int r = 4;
		final int n = 4;
		final int m = 4;
		final int s = 32;
		final byte[] boxArray = {0xE, 0x4, 0xD, 0x1, 0x2, 0xF, 0xB, 0x8, 0x3, 0xA, 0x6, 0xC, 0x5, 0x9, 0x0, 0x7};
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
		final int[] bitArray = {0, 4, 8, 12, 1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15};
		final Bitpermutation bit = new Bitpermutation(bitArray);
		final byte[] keyArray = {(byte)0b00100010, (byte)0b01010001, (byte)0b00011000, (byte)0b00000000};
		final Key key = new Key(keyArray);
		
		snp = new SNP(r, n, m, box, bit, s, key);
		
	}

	@Test
	public void testExample() {
		final byte[] x = {(byte)0b00010010, (byte)0b10001111};
		final byte[] y = {(byte)0b10101110, (byte)0b10110100};
		for(int i = 0; i < x.length; i++){
			assertEquals(y[i], snp.encode(x)[i]);
			assertEquals(x[i], snp.decode(y)[i]);
		}
	}

}
