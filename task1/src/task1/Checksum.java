package task1;

public interface Checksum {
    
    public void update(byte[] b, int off, int len);
    public long getValue();
    
}
