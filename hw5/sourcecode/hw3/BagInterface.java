package hw3;

/**
 * BagInterface 인터페이스
 * @author 20171622 박건후
 * @version BagInterface.java 1.0
 */
public interface BagInterface<T>{
    
    public int getCurrentSize();
    
    public boolean add(T newEntry);
    
    public T remove();
    
    public boolean remove(T anEntry);
    
    public boolean isEmpty();
    
    public void clear();
    
    public int getFrequencyOf(T anEntry);
    
    public boolean contains(T anEntry);
    
    public T[] toArray();
    
}