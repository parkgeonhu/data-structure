package project;

/**
 * ArrayBag<T> 클래스
 * @author 20171622 박건후
 * @version ArrayBag.java 1.0
 */
public final class ArrayBag<T> implements BagInterface<T>{
    
    
    private final T[] bag;
    private int numberOfEntries;
    private boolean initialized=false;
    private static final int DEFAULT_CAPACITY=25;
    private static final int MAX_CAPACITY=10000;
    
    public ArrayBag(){
        this(DEFAULT_CAPACITY);
    }
    
    public ArrayBag(int desiredcapacity){
        if(desiredcapacity<=MAX_CAPACITY){
            @SuppressWarnings("unchecked")
            T[] tempBag = (T[])new Object[desiredcapacity];
            bag = tempBag;
            numberOfEntries=0;
            initialized=true;
        }
        else
            throw new IllegalStateException("Attempt to create a bag whose capacity exceeds allowed maximum.");
    }
    

    
    private boolean isArrayFull(){
        return numberOfEntries>=bag.length;
    }
    
    private void checkInitialization(){
        if(!initialized){
            throw new SecurityException("ArrayBag object is not initialized properly");
        }
    }
    
    public boolean add(T newEntry){
        checkInitialization();
        boolean result=true;
        if(isArrayFull()){
            result=false;
        }
        else{
            bag[numberOfEntries]=newEntry;
            numberOfEntries++;
        }
        
        return result;
    }
    
    public T remove(){
        checkInitialization();
        T result=null;
        if(numberOfEntries>0){
            result=bag[numberOfEntries-1];
            bag[numberOfEntries-1]=null;
            numberOfEntries--;
        }
        return result;
    }
    
    public boolean remove(T anEntry){
        checkInitialization();
        int index=getIndexOf(anEntry);
        T result=removeEntry(index);
        return anEntry.equals(result);
        
    }
    
    private T removeEntry(int givenIndex){
        T result=null;
        
        //givenindex 자리를 bag 마지막 원소로 채우고, 마지막 원소는 null로 설정
        if(!isEmpty() && (givenIndex>=0)){
            result=bag[givenIndex];
            bag[givenIndex] = bag[numberOfEntries-1];
            bag[numberOfEntries-1]=null;            
        }
        return result;
    }
    
    //index가 -1이면 못 찾은 것을 의미함
    private int getIndexOf(T anEntry){
        int where=-1;
        boolean found = false;
        int index=0;
        while(!found && (index<numberOfEntries)){
            if(anEntry.equals(bag[index])){
                found=true;
                where=index;
            }
            index++;
        }
        return where;
    }
    
    
    public boolean isEmpty(){
        return numberOfEntries == 0;
    }
    
    public int getCurrentSize(){
        return numberOfEntries;
    }
    
    public void clear(){
        while(!isEmpty())
            remove();
    }
    
    public int getFrequencyOf(T anEntry){
        checkInitialization();
        int counter=0;
        for(int index=0;index<numberOfEntries;index++){
            if(anEntry.equals(bag[index])){
                counter++;
            }
        }
        return counter;
    }
    
    public boolean contains(T anEntry){
        checkInitialization();
        return getIndexOf(anEntry)>-1;
    }
    
    public T[] toArray(){
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries];
        
        for(int index=0;index<numberOfEntries;index++){
            result[index]=bag[index];
        }
        return result;
    }
}