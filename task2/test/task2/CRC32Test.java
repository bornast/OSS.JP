/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2;

import org.junit.Test;
import static org.junit.Assert.*;


public class CRC32Test {
    
    private String str = "Generate CRC32 Checksum For Byte Array Example";
    private byte bytes[] = str.getBytes();
    
    public CRC32Test() {
    }

    /**
     * Test of update method, of class CRC32.
     */
    @Test
    public void testUpdate_int() {
        
        task2.Checksum actual_checksum = new task2.CRC32();
        actual_checksum.update(100);
        long actual_checksum_value = actual_checksum.getValue();
                                              
        java.util.zip.Checksum expected_checksum = new java.util.zip.CRC32();
        expected_checksum.update(100);
        long expected_checksum_value = expected_checksum.getValue();
                                               
        assertEquals(actual_checksum_value, expected_checksum_value);
        
    }

    /**
     * Test of update method, of class CRC32.
     */
    @Test
    public void testUpdate_3args() {
        
        task2.Checksum actual_checksum = new task2.CRC32();
        actual_checksum.update(bytes, 0, bytes.length);
        long actual_checksum_value = actual_checksum.getValue();
                                              
        java.util.zip.Checksum expected_checksum = new java.util.zip.CRC32();
        expected_checksum.update(bytes, 0, bytes.length);
        long expected_checksum_value = expected_checksum.getValue();
                                               
        assertEquals(actual_checksum_value, expected_checksum_value);
        
    }

    /**
     * Test of getValue method, of class CRC32.
     */
    @Test
    public void testGetValue() {
                
        task2.Checksum actual_checksum = new task2.CRC32();
        actual_checksum.update(bytes, 0, bytes.length);
        long actual_checksum_value = actual_checksum.getValue();
                                              
        java.util.zip.Checksum expected_checksum = new java.util.zip.CRC32();
        expected_checksum.update(bytes, 0, bytes.length);
        long expected_checksum_value = expected_checksum.getValue();
                                               
        assertEquals(actual_checksum_value, expected_checksum_value);        
    }

    /**
     * Test of reset method, of class CRC32.
     */
    @Test
    public void testReset() {
        
        task2.Checksum actual_checksum = new task2.CRC32();
        actual_checksum.update(bytes, 0, bytes.length);
        actual_checksum.reset();
        long actual_checksum_value = actual_checksum.getValue();
                                              
        java.util.zip.Checksum expected_checksum = new java.util.zip.CRC32();
        expected_checksum.update(bytes, 0, bytes.length);
        expected_checksum.reset();
        long expected_checksum_value = expected_checksum.getValue();
                                               
        assertEquals(actual_checksum_value, expected_checksum_value);
        
    }

    /**
     * Test of update method, of class CRC32.
     */
    @Test
    public void testUpdate_byteArr() {
        
        task2.CRC32 actual_checksum = new task2.CRC32();
        actual_checksum.update(bytes);
        long actual_checksum_value = actual_checksum.getValue();
                                              
        java.util.zip.CRC32 expected_checksum = new java.util.zip.CRC32();
        expected_checksum.update(bytes);
        long expected_checksum_value = expected_checksum.getValue();
                                               
        assertEquals(actual_checksum_value, expected_checksum_value);
    }
    
}
