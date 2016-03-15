package ch.fhnw.raphael.snp.system.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.raphael.snp.system.Bitpermutation;
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
		final Sbox box = new Sbox(boxArray);
		final int[] bitArray = {0, 4, 8, 12, 1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15};
		final Bitpermutation bit = new Bitpermutation(bitArray);
		final byte[] key = {(byte)0b00100010, (byte)0b01010001, (byte)0b00011000, (byte)0b0000000};
		
		snp = new SNP(r, n, m, box, bit, s, key);
		
	}

	@Test
	public void testExample() {
		final byte[] x = {(byte)0b00010010, (byte)0b10001111};
		final byte[] y = {(byte)0b10101110, (byte)0b10110100};
		assertEquals(x, snp.decode(y));
		assertEquals(y, snp.encode(x));
	}

}
