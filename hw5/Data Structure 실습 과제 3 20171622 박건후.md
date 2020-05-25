# 자료구조 실습과제3 20171622 박건후

## 0. Summary

This project is for comparing [Insertion Sort] Time and [Selection Sort] time. First, Create Employee instance and append to EmployeeBag composed of LinkedBag. Second, Convert EmployeeBag to Array. Employee[] bagToEmployeeArray in EmployeeSortTest.java will Second task. Third, Copy employees to employees1 array. Forth, Sort employees and employees each sort way(selection, insertion). Fifth, Compare  [Insertion Sort] Time and [Selection Sort] time. like below:

```bash
TestCase 100) Selection Sort : 0 Insertion Sort : 1
TestCase 1000) Selection Sort : 10 Insertion Sort : 9
TestCase 10000) Selection Sort : 116 Insertion Sort : 78
TestCase 100000) Selection Sort : 81957 Insertion Sort : 20046
```



## 1. Source Code

### 1.1 EmployeeSortTest.java

```java
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
```

### 1.2 Employee.java

```java
package hw3;
// Exercise 10.8 Solution: Employee.java
// Employee abstract superclass.

public abstract class Employee implements Comparable<Employee>{
   private String firstName;
   private String lastName;
   private int ssn;
   private Date birthDate;
    private Date joinDate;
    private Date covidDate;
    private boolean isCovid=false;
    

   // six-argument constructor
   public Employee( String first, String last, int socialSecurityNumber, 
      int month, int day, int year )
   {
      firstName = first;
      lastName = last;
      ssn = socialSecurityNumber;
      birthDate = new Date( month, day, year );
   } // end six-argument Employee constructor
    
    public void setJoinDate(int month, int day, int year){
        joinDate= new Date( month, day, year );
    }
    
    public void setCovidDate(int month, int day, int year){
        isCovid=true;
        covidDate= new Date( month, day, year );
    }
    
    public boolean is_Covid(){
      return isCovid;
   } // end method setLastName
    
    
    public Date getJoinDate(){
        return joinDate;
    }
    
    public Date getCovidDate(){
        return covidDate;
    }

   // set first name
   public void setFirstName( String first )
   {
      firstName = first;
   } // end method setFirstName

   // return first name
   public String getFirstName()
   {
      return firstName;
   } // end method getFirstName

   // set last name
   public void setLastName( String last )
   {
      lastName = last;
   } // end method setLastName

   // return last name
   public String getLastName()
   {
      return lastName;
   } // end method getLastName

   // set social security number
   public void setSocialSecurityNumber( int socialSecurityNumber )
   {
      ssn = socialSecurityNumber;  // should validate
   } // end method setSocialSecurityNumber

   // return social security number
   public int getSocialSecurityNumber()
   {
      return ssn;
   } // end method getSocialSecurityNumber

   // set birth date
   public void setBirthDate( int month, int day, int year )
   {
      birthDate = new Date( month, day, year );
   } // end method setBirthDate

   // return birth date
   public Date getBirthDate()
   {
      return birthDate;
   } // end method getBirthDate

   // return String representation of Employee object
   @Override
   public String toString()
   {
      return String.format( "%s %s\n%s: %s\n%s: %s\n%s: %s", 
         getFirstName(), getLastName(), 
         "social security number", getSocialSecurityNumber(), 
         "birth date", getBirthDate(), "join date", getJoinDate() );
   } // end method toString
    
    
    @Override
    public int compareTo(Employee e) {
        if(this.ssn > e.ssn) {
            return 1;
        }
        else if(this.ssn < e.ssn) {
            return -1;
        }
        else{
            return 0;   
        }
    }
    
   // abstract method overridden by subclasses
   public abstract double earnings();
} // end abstract class Employee
```

### 1.3 SalariedEmployee.java

```java
package hw3;
// Exercise 10.8 Solution: SalariedEmployee.java
// SalariedEmployee class derived from Employee.

public class SalariedEmployee extends Employee 
{
   private double weeklySalary;

   // seven-argument constructor
   public SalariedEmployee( String first, String last, int ssn, 
      int month, int day, int year, double salary )
   {
      super( first, last, ssn, month, day, year ); 
      setWeeklySalary( salary );
   } // end seven-argument SalariedEmployee constructor

   // set salary
   public void setWeeklySalary( double salary )
   {
      weeklySalary = salary < 0.0 ? 0.0 : salary;
   } // end method setWeeklySalary

   // return salary
   public double getWeeklySalary()
   {
      return weeklySalary;
   } // end method getWeeklySalary

   // calculate earnings; override abstract method earnings in Employee
   @Override
   public double earnings()
   {
      return getWeeklySalary();
   } // end method earnings

   // return String representation of SalariedEmployee object
   @Override
   public String toString()
   {
      return String.format( "salaried employee: %s\n%s: $%,.2f", 
         super.toString(), "weekly salary", getWeeklySalary() );
   } // end method toString   
} // end class SalariedEmployee
```



## 2. Compare Insertion sort and Selection sort

 선택정렬은 최솟값을 찾아 앞쪽으로 이동하기를 배열 크기만큼 반복하는 알고리즘입니다. 삽입 정렬은 자료 배열의 모든 요소를 앞에서부터 차례대로 이미 정렬된 배열 부분과 비교하여, 자신의 위치를 찾아 삽입함으로써 정렬을 완성하는 알고리즘입니다.
 선택정렬은 정렬되어 있지 않은 수를 전부 확인해 가장 작은 수를 찾아야합니다. 정렬이 전부 되어 있어도 모든 수를 비교합니다. 삽입 정렬은 정확한 위치에 들어가는 것을 결정하기 위해 정렬 중인 array를 확인 해야합니다. 필요한 값만 읽고 정렬된 리스트에서 비교합니다.
 n개의 element가 있다고 가정할 때, selection은 n번만 쓰면 됩니다. 뒤에서부터 쓰기 때문에 array의 key들이 이동할 필요가 없지만, insertion은 key들이 전부 이동할 수도 있기 때문에 writing이 더 많이 일어납니다.
 만약 전부 정렬된 리스트를 비교한다면 insertion sort의 시간복잡도는 O(n)이지만, selection sort는 여전히 O(n^2)입니다. 

![Analysis of Sorting Algorithms](https://hashanp.xyz/images/benchmarks/Graph2.png)



|  BASIS FOR COMPARISON  |                        INSERTION SORT                        |                        SELECTION SORT                        |
| :--------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
|         Basic          | The data is sorted by inserting the data into an existing sorted file. | The data is sorted by selecting and placing the consecutive elements in sorted location. |
|         Nature         |                            Stable                            |                           Unstable                           |
| Process to be followed | Elements are known beforehand while location to place them is searched. |  Location is previously known while elements are searched.   |
|     Immediate data     | Insertion sort is live sorting technique which can deal with immediate data. | It can not deal with immediate data, it needs to be present at the beginning. |
|  Best case complexity  |                             O(n)                             |                            O(n^2)                            |



## 3. Screen dumps

![screen_dump](C:\Users\myhome\Desktop\실습과제3_20171622_박건후\screen_dump.PNG)

