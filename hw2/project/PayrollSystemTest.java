package project;
// Exercise 10.8 Solution: PayrollSystemTest.java
// Employee hierarchy test program.
import java.util.Scanner; // program uses Scanner to obtain user input
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;


public class PayrollSystemTest {
    public static String date2String(int value){
        if(value<10){
            return "0"+String.valueOf(value);
        }
        else{
            return String.valueOf(value);
        }
    }
    
    
    public static boolean isTenYears(Employee employee, int currentMonth){
        Calendar cal = Calendar.getInstance();
        
        int currentYear = cal.get ( cal.YEAR );
        
        int employeeJoinYear=employee.getJoinDate().getYear();
        int employeeJoinMonth=employee.getJoinDate().getMonth();
        int employeeJoinDay=employee.getJoinDate().getDay();
        String employeeJoinDate=String.valueOf(employeeJoinYear)+date2String(employeeJoinMonth)+date2String(employeeJoinDay);
        
        String currentDate=String.valueOf(currentYear)+date2String(currentMonth)+"01";
        String strFormat = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
        try{
            Date startDate = sdf.parse(employeeJoinDate);
            Date endDate = sdf.parse(currentDate);

            long diffDay = (endDate.getTime() - startDate.getTime()) / (24*60*60*1000);
            System.out.println("근속년수 : "+diffDay+"일");
            
            if(diffDay>3650){
                return true;
            }

            
        }catch(ParseException e){
            e.printStackTrace();
        }
        
        return false;
    }
    
    public static void paySalary(Employee employees[], int currentMonth){
              // generically process each element in array employees
      for ( Employee currentEmployee : employees ) {
          
         System.out.println( currentEmployee ); // invokes toString

         // determine whether element is a BasePlusCommissionEmployee
         if ( currentEmployee instanceof BasePlusCommissionEmployee ) 
         {
            // downcast Employee reference to 
            // BasePlusCommissionEmployee reference
            BasePlusCommissionEmployee employee = 
               ( BasePlusCommissionEmployee ) currentEmployee;

            double oldBaseSalary = employee.getBaseSalary();
            employee.setBaseSalary( 1.10 * oldBaseSalary );
            System.out.printf( 
               "new base salary with 10%% increase is: $%,.2f\n",
               employee.getBaseSalary() );
         } // end if
          
         double tempSalary=currentEmployee.earnings();

         // if month of employee's birthday, add $100 to salary
         if ( currentMonth == currentEmployee.getBirthDate().getMonth() ){
             tempSalary+=100.0;
             System.out.printf("birthday bonus : +$100.00 \n");             
         }

            // System.out.printf(
            //    "earned $%,.2f %s\n\n", currentEmployee.earnings()+100.0, 
            //    "(plus $100.00 birthday bonus)" );
 
          // if month of employee's work 10 years, multiply 1.1 to salary
          if (isTenYears(currentEmployee,currentMonth)==true){
              tempSalary*=1.10;
              System.out.print("(multiply 1.1 to salary / 10years) " );  
          }
          
          //print final salary
          System.out.printf("earned $ %.2f\n\n", tempSalary );
          
          
      } // end for
        
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
      
      SalariedEmployee salariedEmployee1 = 
         new SalariedEmployee( 
         "박", "건후", "555-55-5555", 2, 9, 1998, 800.00 );
      HourlyEmployee hourlyEmployee1 = 
         new HourlyEmployee( 
         "맹", "산하", "666-66-6666", 12, 29, 1996, 16.75, 40 );
      CommissionEmployee commissionEmployee1 = 
         new CommissionEmployee( 
         "민", "대인", "777-77-7777", 9, 8, 1998, 10000, .06 );
      BasePlusCommissionEmployee basePlusCommissionEmployee1 = 
         new BasePlusCommissionEmployee( 
         "문", "성찬", "888-88-8888", 3, 2, 1998, 5000, .04, 300 );
       
      SalariedEmployee salariedEmployee2 = 
         new SalariedEmployee( 
         "김", "학균", "999-99-9999", 6, 15, 1998, 800.00 );
      HourlyEmployee hourlyEmployee2 = 
         new HourlyEmployee( 
         "노", "성환", "1010-10-1010", 12, 29, 1998, 16.75, 40 );
       
       
       //setting join date
       salariedEmployee.setJoinDate(3, 2, 2019);
       hourlyEmployee.setJoinDate(3, 2, 2019);
       commissionEmployee.setJoinDate(3, 2, 2019);
       basePlusCommissionEmployee.setJoinDate(3, 2, 2019);
       salariedEmployee1.setJoinDate(3, 2, 1990);
       hourlyEmployee1.setJoinDate(3, 2, 1993);
       commissionEmployee1.setJoinDate(3, 2, 1999);
       basePlusCommissionEmployee1.setJoinDate(3, 2, 2000);
       salariedEmployee2.setJoinDate(3, 2, 2014);
       hourlyEmployee2.setJoinDate(3, 2, 2012);
       
       
      System.out.println( "Employees processed individually:\n" );
      
      System.out.printf( "%s\n%s: $%,.2f\n\n", 
         salariedEmployee, "earned", salariedEmployee.earnings() );
      System.out.printf( "%s\n%s: $%,.2f\n\n",
         hourlyEmployee, "earned", hourlyEmployee.earnings() );
      System.out.printf( "%s\n%s: $%,.2f\n\n",
         commissionEmployee, "earned", commissionEmployee.earnings() );
      System.out.printf( "%s\n%s: $%,.2f\n\n", 
         basePlusCommissionEmployee, 
         "earned", basePlusCommissionEmployee.earnings() );
       
      System.out.printf( "%s\n%s: $%,.2f\n\n", 
         salariedEmployee1, "earned", salariedEmployee1.earnings() );
      System.out.printf( "%s\n%s: $%,.2f\n\n",
         hourlyEmployee1, "earned", hourlyEmployee1.earnings() );
      System.out.printf( "%s\n%s: $%,.2f\n\n",
         commissionEmployee1, "earned", commissionEmployee1.earnings() );
      System.out.printf( "%s\n%s: $%,.2f\n\n", 
         basePlusCommissionEmployee1, 
         "earned", basePlusCommissionEmployee1.earnings() );
       
    System.out.printf( "%s\n%s: $%,.2f\n\n", 
         salariedEmployee2, "earned", salariedEmployee2.earnings() );
      System.out.printf( "%s\n%s: $%,.2f\n\n",
         hourlyEmployee2, "earned", hourlyEmployee2.earnings() );
       
      // create four-element Employee array
      Employee[] employees = new Employee[ 10 ]; 

      // initialize array with Employees
      employees[ 0 ] = salariedEmployee;
      employees[ 1 ] = hourlyEmployee;
      employees[ 2 ] = commissionEmployee; 
      employees[ 3 ] = basePlusCommissionEmployee;
      employees[ 4 ] = salariedEmployee1;
      employees[ 5 ] = hourlyEmployee1;
      employees[ 6 ] = commissionEmployee1; 
      employees[ 7 ] = basePlusCommissionEmployee1;
      employees[ 8 ] = salariedEmployee2;
      employees[ 9 ] = hourlyEmployee2;

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
       
       paySalary(employees, currentMonth);

      // get type name of each object in employees array
      for ( int j = 0; j < employees.length; j++ )
         System.out.printf( "Employee %d is a %s\n", j, employees[ j ].getClass().getName() ); 
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
