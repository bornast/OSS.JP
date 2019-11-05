package task1;

/**
 *
 * @author borna
 */

public class CalculateCRC32ForByteArray {
   
    public static void main(String[] args) {
        
        String str = "Generate CRC32 Checksum For Byte Array Exampleaaaa";
        byte bytes[] = str.getBytes();
        Checksum checksum = new CRC32();

        checksum.update(bytes, 0, bytes.length);
        long lngChecksum = checksum.getValue();
        System.out.println("CRC32 checksum for byte array is:" + lngChecksum);
        
    }
    
}
