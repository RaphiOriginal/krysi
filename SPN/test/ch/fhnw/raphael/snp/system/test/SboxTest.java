package ch.fhnw.raphael.snp.system.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.raphael.snp.system.Sbox;

public class SboxTest {
	
	private Sbox box;
	
	@Before
	public void setUp(){
		final int[] boxArray = {2, 1, 3, 0};
		box = new Sbox(boxArray);
	}

	@Test
	public void testGetBox() {
		final int[] referenceArray = {2, 1, 3, 0};
		assertEquals(referenceArray.length, box.getBox().length);
		for(int i = 0; i < referenceArray.length; i++) {
			assertEquals(referenceArray[i], box.getBox()[i]);
		}
	}
	
	@Test
	public void testGetInvertedBox() {
		final int[] referenceArray = {3, 1, 0, 2};
		assertEquals(referenceArray.length, box.getInvertedBox().length);
		for(int i = 0; i < referenceArray.length; i++) {
			assertEquals(referenceArray[i], box.getInvertedBox()[i]);
		}
	}
	
	@Test
	public void testSize() {
		final int referenceSize = 4;
		assertEquals(referenceSize, box.size());
	}

}
