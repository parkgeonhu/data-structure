package hw3;

import java.util.Arrays;
import java.util.Random;


/**
 * LinkedBag을 사용한 EmployeeSortTest 입니다.
 * @author 20171622 박건후
 * @version EmployeeSortTest.java 1.0
 *
 */

public class EmployeeSortTest {
    
    public static Employee[] bagToEmployeeArray(LinkedBag<Employee> employeeBag){
        Object[] bagArray=employeeBag.toArray();
        Employee [] employees=new Employee[employeeBag.getCurrentSize()];
        
        for(int index=0;index<employeeBag.getCurrentSize();index++){
            employees[index]=(Employee)bagArray[index];
        }
        
        return employees;
    }
    
    public static long getSelectionSortTime(Employee[] arr){
        int min; //최소값을 가진 데이터의 인덱스 저장 변수
        Employee temp;
        
        long startTime = System.currentTimeMillis();
        for(int i=0; i<arr.length-1; i++){ // size-1 : 마지막 요소는 자연스럽게 정렬됨
            min = i;
            for(int j=i+1; j<arr.length; j++){
                if(arr[min].compareTo(arr[j])==1){
                    min = j;
                }
            }
            temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
        long endTime = System.currentTimeMillis();
        
        return endTime - startTime;
        

    }

    
    
    public static long getInsertionSortTime(Employee[] arr){
        long startTime = System.currentTimeMillis();
		for (int i = 1; i < arr.length; i++) {
			Employee standard = arr[i];  // 기준
			int aux = i - 1;   // 비교할 대상
			while (aux >= 0 && standard.compareTo(arr[aux])==-1 ) {
				arr[aux + 1] = arr[aux];   // 비교대상이 큰 경우 오른쪽으로 밀어냄
				aux--;
			}
			arr[aux + 1] = standard;  // 기준값 저장
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
    
    public static void mixArr(Employee[] arr){
        Random rand=new Random();
        for(int i=0;i<arr.length/2;i++){
            int tempIndex=rand.nextInt(arr.length);
            Employee temp=arr[tempIndex];
            arr[tempIndex]=arr[i];
            arr[i]=temp;
        }
    }
    
    public static void testSort(int testCase){
        long selectionSortTime;
       long insertionSortTime;
        LinkedBag<Employee> employeeBag = new LinkedBag<>();
       for(int i=0;i<testCase;i++){
           SalariedEmployee employee=new SalariedEmployee("John", "Smith", i, 2, 9, 1998, 300);
           employeeBag.add(employee);
       }
       Employee employees[]=bagToEmployeeArray(employeeBag);
       Employee employees1[]=Arrays.copyOf(employees, employees.length);
        mixArr(employees);
        mixArr(employees1);
    
        selectionSortTime=getSelectionSortTime(employees);
       insertionSortTime=getInsertionSortTime(employees1);
       
       employeeBag.clear();
       
       System.out.println("TestCase "+testCase+") Selection Sort : "+selectionSortTime+" Insertion Sort : "+insertionSortTime);
    }
    
    
   public static void main( String[] args ){
       testSort(100);
       testSort(1000);
       testSort(10000);
       testSort(100000);
   } // end main
}