

package project;
// Exercise 10.8 Solution: PayrollSystemTest.java
// Employee hierarchy test program.
import java.util.Scanner; // program uses Scanner to obtain user input


import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

/**
 * LinkedBag을 사용한 PayrollSystemTest입니다.
 * 데이터를 Employee [] employees 에 임시로 저장한 뒤, LinkedBag에 add를 해주었습니다.
 * @author 20171622 박건후
 * @version PayrollSystemTestLinkedBag.java 1.0
 *
 */

public class Task {
    public static String date2String(int value){
        if(value<10){
            return "0"+String.valueOf(value);
        }
        else{
            return String.valueOf(value);
        }
    }
    
    
    public static boolean is_BeforeAprilCovid(Employee employee){
        Calendar cal = Calendar.getInstance();
        
        boolean temp=false;
        int employeeCovidYear=employee.getCovidDate().getYear();
        int employeeCovidMonth=employee.getCovidDate().getMonth();
        int employeeCovidDay=employee.getCovidDate().getDay();
        String employeeCovidDate=String.valueOf(employeeCovidYear)+date2String(employeeCovidMonth)+date2String(employeeCovidDay);
        
        String currentDate="20200401";
        String strFormat = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
        try{
            Date startDate = sdf.parse(employeeCovidDate);
            Date endDate = sdf.parse(currentDate);

            long diffDay = (endDate.getTime() - startDate.getTime()) / (24*60*60*1000);            
            if(diffDay>0){
                return true;
            }

        }catch(ParseException e){
            e.printStackTrace();
        }
        return false;
    }
    
    
    public static boolean isTenYears(Employee employee, int currentMonth){

        Calendar cal = Calendar.getInstance();
        int employeeJoinYear=employee.getJoinDate().getYear();
        int employeeJoinMonth=employee.getJoinDate().getMonth();
        int employeeJoinDay=employee.getJoinDate().getDay();
        int currentYear = cal.get ( cal.YEAR );
        

        String employeeJoinDate=String.valueOf(employeeJoinYear)+date2String(employeeJoinMonth)+date2String(employeeJoinDay);
        
        String currentDate=String.valueOf(currentYear)+date2String(currentMonth)+"01";
        String strFormat = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
        try{
            Date startDate = sdf.parse(employeeJoinDate);
            Date endDate = sdf.parse(currentDate);

            long diffDay = (endDate.getTime() - startDate.getTime()) / (24*60*60*1000);
            System.out.println("근속일수 : "+diffDay+"일");
            
            if(diffDay>3650){
                return true;
            }

            
        }catch(ParseException e){
            e.printStackTrace();
        }
        
        return false;
    }
    
    public static void paySalary(BagInterface<Employee> employeeBag, int currentMonth){
    
        Object[] bagArray=employeeBag.toArray();
        for(int index=0;index<employeeBag.getCurrentSize();index++){
             System.out.println( ((Employee)bagArray[index]) ); // invokes toString

             // determine whether element is a BasePlusCommissionEmployee
             if ( ((Employee)bagArray[index]) instanceof BasePlusCommissionEmployee ) {
                    // downcast Employee reference to 
                    // BasePlusCommissionEmployee reference
                BasePlusCommissionEmployee employee = ( BasePlusCommissionEmployee ) ((Employee)bagArray[index]);

                double oldBaseSalary = employee.getBaseSalary();
                employee.setBaseSalary( 1.10 * oldBaseSalary );
                System.out.printf("new base salary with 10%% increase is: $%,.2f\n",employee.getBaseSalary() );
             } // end if
             double tempSalary=((Employee)bagArray[index]).earnings();
             // if month of employee's birthday, add $100 to salary
             if ( currentMonth == ((Employee)bagArray[index]).getBirthDate().getMonth() ){
                 tempSalary+=100.0;
                 System.out.printf("birthday bonus : +$100.00 \n");             
             }
              // if month of employee's work 10 years, multiply 1.1 to salary
              if (isTenYears(((Employee)bagArray[index]),currentMonth)==true){
                  tempSalary*=1.10;
                  System.out.print("(multiply 1.1 to salary / 10years) " );  
              }
              //print final salary
              System.out.printf("earned $ %.2f\n\n", tempSalary );
        }
    }
    
    private static int displayTodayCovid(BagInterface<Employee> employeeBag){
        Object[] bagArray=employeeBag.toArray();
        int cnt=0;
        for(int index=0;index<employeeBag.getCurrentSize();index++){
            if(((Employee)bagArray[index]).is_Covid()){
                cnt++;
            }
        }
        return cnt;
    }
    
    private static int displayBeforeAprilCovid(BagInterface<Employee> employeeBag){
        Object[] bagArray=employeeBag.toArray();
        int cnt=0;
        for(int index=0;index<employeeBag.getCurrentSize();index++){
            if(((Employee)bagArray[index]).is_Covid()==true){
                if(is_BeforeAprilCovid(((Employee)bagArray[index]))){
                    cnt++;
                }
            }

        }
        return cnt;
    }

    
    private static void displayInfoBag(BagInterface<Employee> employeeBag){
        Object[] bagArray=employeeBag.toArray();
        for(int index=0;index<employeeBag.getCurrentSize();index++){
            System.out.printf( "%s\n%s: $%,.2f\n\n", bagArray[index], "earned", ((Employee)bagArray[index]).earnings() );
        }
    }
    
    private static void displayClassBag(BagInterface<Employee> employeeBag){
        Object[] bagArray=employeeBag.toArray();
        for(int index=0;index<employeeBag.getCurrentSize();index++){
            System.out.printf( "Employee %d is a %s\n", index, bagArray[index].getClass().getName() ); 
        }
    }
    
   public static void main( String[] args ) 
   {
      // create subclass objects
      SalariedEmployee salariedEmployee = 
         new SalariedEmployee( 
         "John", "Smith", "111-11-1111", 6, 15, 1944, 800.00 );
      HourlyEmployee hourlyEmployee = 
         new HourlyEmployee( 
         "Karen", "Price", "222-22-2222", 12, 29, 1960, 16.75, 40 );
      CommissionEmployee commissionEmployee = 
         new CommissionEmployee( 
         "Sue", "Jones", "333-33-3333", 9, 8, 1954, 10000, .06 );
      BasePlusCommissionEmployee basePlusCommissionEmployee = 
         new BasePlusCommissionEmployee( 
         "Bob", "Lewis", "444-44-4444", 3, 2, 1965, 5000, .04, 300 );
      
      // SalariedEmployee salariedEmployee1 = 
      //    new SalariedEmployee( 
      //    "박", "건후", "555-55-5555", 2, 9, 1998, 800.00 );
      // HourlyEmployee hourlyEmployee1 = 
      //    new HourlyEmployee( 
      //    "맹", "산하", "666-66-6666", 12, 29, 1996, 16.75, 40 );
      // CommissionEmployee commissionEmployee1 = 
      //    new CommissionEmployee( 
      //    "민", "대인", "777-77-7777", 9, 8, 1998, 10000, .06 );
      // BasePlusCommissionEmployee basePlusCommissionEmployee1 = 
      //    new BasePlusCommissionEmployee( 
      //    "문", "성찬", "888-88-8888", 3, 2, 1998, 5000, .04, 300 );
       
      // SalariedEmployee salariedEmployee2 = 
      //    new SalariedEmployee( 
      //    "김", "학균", "999-99-9999", 6, 15, 1998, 800.00 );
      // HourlyEmployee hourlyEmployee2 = 
      //    new HourlyEmployee( 
      //    "노", "성환", "1010-10-1010", 12, 29, 1998, 16.75, 40 );
       
       
       //setting join date
       salariedEmployee.setJoinDate(3, 2, 2019);
       hourlyEmployee.setJoinDate(3, 2, 2019);
       commissionEmployee.setJoinDate(3, 2, 2019);
       basePlusCommissionEmployee.setJoinDate(3, 2, 2019);
       // salariedEmployee1.setJoinDate(3, 2, 1990);
       // hourlyEmployee1.setJoinDate(3, 2, 1993);
       // commissionEmployee1.setJoinDate(3, 2, 1999);
       // basePlusCommissionEmployee1.setJoinDate(3, 2, 2000);
       // salariedEmployee2.setJoinDate(3, 2, 2014);
       // hourlyEmployee2.setJoinDate(3, 2, 2012);
       
       
       salariedEmployee.setCovidDate(4, 2, 2020);
       hourlyEmployee.setCovidDate(3, 2, 2020);
       commissionEmployee.setCovidDate(4, 2, 2020);
       
       
      System.out.println( "Employees processed individually:\n" );
      
       
      // create four-element Employee array
      Employee[] employees = new Employee[ 10 ]; 

      // initialize array with Employees
      employees[ 0 ] = salariedEmployee;
      employees[ 1 ] = hourlyEmployee;
      employees[ 2 ] = commissionEmployee; 
      employees[ 3 ] = basePlusCommissionEmployee;
      // employees[ 4 ] = salariedEmployee1;
      // employees[ 5 ] = hourlyEmployee1;
      // employees[ 6 ] = commissionEmployee1; 
      // employees[ 7 ] = basePlusCommissionEmployee1;
      // employees[ 8 ] = salariedEmployee2;
      // employees[ 9 ] = hourlyEmployee2;
       
       
       LinkedBag<Employee> employeeBag= new LinkedBag<>();
       for(int i=0;i<4;i++){
           employeeBag.add(employees[i]);
       }
       
      
       displayInfoBag(employeeBag);
       System.out.println( "오늘의 확진자 : " + displayTodayCovid(employeeBag));
       System.out.println( "200401 이전 확진자 : " + displayBeforeAprilCovid(employeeBag));
       

      Scanner input = new Scanner( System.in ); // to get current month
      int currentMonth;

      // get and validate current month
      do
      {
         System.out.print( "Enter the current month (1 - 12): " );
         currentMonth = input.nextInt();
         System.out.println();
      } while ( ( currentMonth < 1 ) || ( currentMonth > 12 ) );

      System.out.println( "Employees processed polymorphically:\n" );
       
       paySalary(employeeBag, currentMonth);
       
       System.out.println( "다음달 월급입니다." );
       paySalary(employeeBag, currentMonth+1);

      // // get type name of each object in employees array
      // for ( int j = 0; j < employeeBag.getCurrentSize(); j++ )
      //    System.out.printf( "Employee %d is a %s\n", j, employees[ j ].getClass().getName() ); 
   } // end main
} // end class PayrollSystemTest

/**************************************************************************
 * (C) Copyright 1992-2010 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
