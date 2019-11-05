package task1;

/* http://developer.classpath.org/doc/java/util/zip/CRC32-source.html */

public class CRC32 implements Checksum {
    
    private int crc;

    public CRC32() {}
  
    /** The fast CRC table. Computed once when the CRC32 class is loaded. */
    private static int[] crc_table = make_crc_table();
   
     /** Make the table for a fast CRC. */
    private static int[] make_crc_table () {
        
        int[] crc_table = new int[256];
        for (int n = 0; n < 256; n++) {
            int c = n;
            for (int k = 8;  --k >= 0; ) {
                if ((c & 1) != 0)
                    c = 0xedb88320 ^ (c >>> 1);
                else
                    c = c >>> 1;
            }
            crc_table[n] = c;
         }
       return crc_table;
     }
         
    @Override
    public void update (byte[] buf, int off, int len) {
        int c = ~crc;
        while (--len >= 0)
            c = crc_table[(c ^ buf[off++]) & 0xff] ^ (c >>> 8);
        crc = ~c;
    }

    @Override
    public long getValue() {
        return (long)crc & 0xffffffffL;
    }
}
