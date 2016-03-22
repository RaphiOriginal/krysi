package ch.fhnw.raphael.snp.system.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.raphael.snp.system.Bitpermutation;

public class BitpermutationTest {
	
	private Bitpermutation bit;
	
	@Before
	public void setUp(){
		final int[] boxArray = {2, 1, 3, 0, 4, 5, 6, 7};
		bit = new Bitpermutation(boxArray);
	}

	@Test
	public void testGetPermutation() {
		final int[] referenceArray = {2, 1, 3, 0, 4, 5, 6, 7};
		assertEquals(referenceArray.length, bit.getPermutation().length);
		for(int i = 0; i < referenceArray.length; i++) {
			assertEquals(referenceArray[i], bit.getPermutation()[i]);
		}
	}
	
	@Test
	public void testGetInvertedPermutation() {
		final int[] referenceArray = {3, 1, 0, 2, 4, 5, 6, 7};
		assertEquals(referenceArray.length, bit.getInvertedPermutation().length);
		for(int i = 0; i < referenceArray.length; i++) {
			assertEquals(referenceArray[i], bit.getInvertedPermutation()[i]);
		}
	}
	
	@Test
	public void testUse(){
		byte[] input = {(byte)0b0101_0101}; // 85
		byte[] expected = {(byte)0b1100_0101};// -59
		byte[] result = bit.use(input, 4);
		for(int i = 0; i < input.length; i++){
			System.out.println(expected[i] + " " + result[i]);
			assertEquals(expected[i], result[i]);
		}
	}
	
	@Test
	public void testSize() {
		final int referenceSize = 8;
		assertEquals(referenceSize, bit.size());
	}

}
