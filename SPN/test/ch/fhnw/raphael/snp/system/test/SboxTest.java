package ch.fhnw.raphael.snp.system.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.raphael.snp.system.Sbox;

public class SboxTest {
	
	private Sbox box;
	
	@Before
	public void setUp(){
		final byte[] boxArray = {0x2, 0x1, 0x3, 0x0};
		box = new Sbox(boxArray);
	}

	@Test
	public void testGetBox() {
		final int[] referenceArray = {0x2, 0x1, 0x3, 0x0};
		assertEquals(referenceArray.length, box.getBox().length);
		for(int i = 0; i < referenceArray.length; i++) {
			assertEquals(referenceArray[i], box.getBox()[i]);
		}
	}
	
	@Test
	public void testSize() {
		final int referenceSize = 4;
		assertEquals(referenceSize, box.size());
	}

}
