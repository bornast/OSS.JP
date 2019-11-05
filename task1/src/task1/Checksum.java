package task1;

/**
 *
 * @author borna
 */
public interface Checksum {
    
    public void update(byte[] b, int off, int len);
    public long getValue();
    
}
