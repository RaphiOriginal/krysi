package ch.fhnw.raphael.snp.system.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.raphael.snp.system.Key;

public class KeyTest {
	
	private Key key;

	@Test
	public void testConstructor() {
		try {
			key = new Key(0);
			fail("IllegalArgumentException expected! (Used '0')");
		} catch (IllegalArgumentException e){
			//Do nothing
		}
		key = null;
		try {
			key = new Key(7);
			fail("IllegalArgumentException expected! (not devideable with 8)");
		} catch (IllegalArgumentException e){
			//Do nothing
		}
		key = null;
		byte[] expectedKeyArray = {(byte)0b00000000};
		Key expectedKey = new Key(expectedKeyArray);
		key = new Key(8);
		assertTrue(expectedKey.equals(key));
	}
	
	@Test
	public void testEquals(){
		key = new Key(8);
		Key other = new Key(16);
		byte[] keyArray = {(byte)0b00000001};
		Key third = new Key(keyArray);
		byte[] keyArray2 = {(byte)0b00000000};
		Key last = new Key(keyArray2);
		assertTrue(key.equals(key));
		assertFalse(key.equals(other));
		assertFalse(key.equals(third));
		assertTrue(key.equals(last));
	}
	
	@Test
	public void testAdd(){
		key = new Key(8);
		byte[] keyArray = {(byte)0b00000001};
		Key additionalKey = new Key(keyArray);
		key.add();
		assertTrue(additionalKey.equals(key));
	}

}
