package hw3;
/**
 * LinkedBag<T> 클래스
 * @author 20171622 박건후
 * @version LinkedBag.java 1.0
 */
public final class LinkedBag<T> implements BagInterface<T>{
    private Node firstNode;
    private int numberOfEntries;
    
    public LinkedBag(){
        firstNode=null;
        numberOfEntries=0;
    }
    
    public boolean add(T newEntry){
        Node newNode=new Node(newEntry);
        newNode.next=firstNode;
        
        firstNode=newNode;
        numberOfEntries++;
        return true;
    }
    
    public T[] toArray(){
        @SuppressWarnings("unchecked")
        T[] result=(T[])new Object[numberOfEntries];
        int index=0;
        
        Node currentNode=firstNode;
        while((index<numberOfEntries) && (currentNode!=null)){
            result[index]=currentNode.data;
            index++;
            currentNode=currentNode.next;
        }
        return result;
    }
    
    public int getFrequencyOf(T anEntry){
        int frequency=0;
        int loopCounter=0;
        Node currentNode=firstNode;
        while((loopCounter<numberOfEntries) && (currentNode!=null)){
            if(anEntry.equals(currentNode.data))
                frequency++;
            loopCounter++;
            currentNode=currentNode.next;
        }
        return frequency;
    }
    
    public boolean contains(T anEntry){
        boolean found = false;
        Node currentNode=firstNode;
        while(!found && (currentNode!=null)){
            if(anEntry.equals(currentNode.data))
                found=true;
            else
                currentNode=currentNode.next;
        }
        return found;
    }
    
    private Node getReferenceTo(T anEntry){
        boolean found=false;
        Node currentNode=firstNode;
        while(!found && (currentNode!=null)){
            if(anEntry.equals(currentNode.data))
                found=true;
            else
                currentNode=currentNode.next;
        }
        return currentNode;
    }
    
    public T remove(){
        T result=null;
        if(numberOfEntries>0){
            result=firstNode.getData();
            firstNode=firstNode.getNextNode();
            numberOfEntries--;
        }
        return result;
    }
    
    public boolean remove(T anEntry){
        boolean result=false;
        Node nodeN=getReferenceTo(anEntry);
        if(nodeN!=null){
            nodeN.data=firstNode.data;
            firstNode=firstNode.next;
            numberOfEntries--;
            result=true;
        }
        return result;
    }
    
    // public boolean replaceK(T anEntry, T replaceNode){
    //     @SuppressWarnings("unchecked")
    //     boolean result=false;
    //     Node nodeN=getReferenceTo(anEntry);
    //     if(nodeN!=null){
    //         nodeN.data=((Node)replaceNode).data;
    //         result=true;
    //     }
    //     return result;
    // }
    
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
    
    
    
    private class Node{
        private T data;
        private Node next;
    
        private Node(T DataPortion){
            this(DataPortion, null);
        }
    
        private Node(T DataPortion, Node nextNode){
            data=DataPortion;
            next=nextNode;
        }
        
        private T getData(){
            return data;
        }
        
        private void setData(T newData){
            data=newData;
        }
        
        private Node getNextNode(){
            return next;
        }
        
        private void setNextNode(Node nextNode){
            next=nextNode;
        }
        
    }  
}