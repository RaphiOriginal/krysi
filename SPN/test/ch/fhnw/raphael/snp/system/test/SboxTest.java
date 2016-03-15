package ch.fhnw.raphael.snp.system.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.raphael.snp.system.Sbox;

public class SboxTest {
	
	private Sbox box;
	
	@Before
	public void setUp(){
		Map<Byte, Byte> boxMap = new HashMap<>();
		boxMap.put((byte)0x0, (byte)0x2);
		boxMap.put((byte)0x1, (byte)0x1);
		boxMap.put((byte)0x2, (byte)0x3);
		boxMap.put((byte)0x3, (byte)0x0);
		box = new Sbox(boxMap);
	}
	
	@Test
	public void testUse() {
		byte[] input = {(byte)0x12};
		byte[] expected = {(byte)0x13};
		for(int i=0; i < input.length; i++){
			assertEquals(expected[i], box.use(input)[i]);
		}
	}

	@Test
	public void testGetBox() {
		Map<Byte, Byte> referenceMap = new HashMap<>();
		referenceMap.put((byte)0x0, (byte)0x2);
		referenceMap.put((byte)0x1, (byte)0x1);
		referenceMap.put((byte)0x2, (byte)0x3);
		referenceMap.put((byte)0x3, (byte)0x0);
		assertEquals(referenceMap.size(), box.getBox().size());
		Iterator<Byte> refIter = referenceMap.values().iterator();
		Iterator<Byte> resIter = box.getBox().values().iterator();
		while(refIter.hasNext() && resIter.hasNext()){
			assertEquals(refIter.next(), resIter.next());
		}
	}
	
	@Test
	public void testSize() {
		final int referenceSize = 4;
		assertEquals(referenceSize, box.size());
	}

}
