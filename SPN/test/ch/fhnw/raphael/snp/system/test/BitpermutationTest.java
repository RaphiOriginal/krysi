package ch.fhnw.raphael.snp.system.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.raphael.snp.system.Bitpermutation;

public class BitpermutationTest {
	
	private Bitpermutation bit;
	
	@Before
	public void setUp(){
		final int[] boxArray = {2, 1, 3, 0};
		bit = new Bitpermutation(boxArray);
	}

	@Test
	public void testGetPermutation() {
		final int[] referenceArray = {2, 1, 3, 0};
		assertEquals(referenceArray.length, bit.getPermutation().length);
		for(int i = 0; i < referenceArray.length; i++) {
			assertEquals(referenceArray[i], bit.getPermutation()[i]);
		}
	}
	
	@Test
	public void testGetInvertedPermutation() {
		final int[] referenceArray = {3, 1, 0, 2};
		assertEquals(referenceArray.length, bit.getInvertedPermutation().length);
		for(int i = 0; i < referenceArray.length; i++) {
			assertEquals(referenceArray[i], bit.getInvertedPermutation()[i]);
		}
	}
	
	@Test
	public void testUse(){
		byte[] input = {(byte)0x12, (byte)0x34};
		byte[] expected = {(byte)0x32, (byte)0x41};
		for(int i = 0; i < input.length; i++){
			assertEquals(expected[i], bit.use(input)[i]);
		}
	}
	
	@Test
	public void testSize() {
		final int referenceSize = 4;
		assertEquals(referenceSize, bit.size());
	}

}
