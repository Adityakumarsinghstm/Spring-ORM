package com.spirng.orm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spirng.orm.dao.StudentDao;
import com.spirng.orm.entities.Student;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
	    StudentDao dao = context.getBean("studentDao",StudentDao.class);
	    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    
	    boolean go = true;
	    while(go)
	    {
	    	System.out.println("Press 1 for add new student");
	    	System.out.println("Press 2 for display all students details");
	    	System.out.println("Press 3 for get single student details");
	    	System.out.println("Press 4 for delete student");
	    	System.out.println("Press 5 for update student details");
	    	System.out.println("Press 6 for Exit");
	    	
	    	try {
	    		int input = Integer.parseInt(reader.readLine());
	    		
	    		switch (input) {
				case 1:
					//add new student
					//taking input
					System.out.print("Enter student Id : ");
					int sId = Integer.parseInt(reader.readLine());
					
					System.out.print("Enter student Name : ");
					String sName = reader.readLine();
					
					System.out.print("Enter student City : ");
					String sCity = reader.readLine();
					
					//creating object of student
					Student student = new Student();
					student.setStudentId(sId);
					student.setStudentName(sName);
					student.setStudentCity(sCity);
					
					//setting object
					int r = dao.insert(student);
					System.out.println(r+ " student added");
					break;
                
				case 2:
					//display all students
					
					List<Student> students = dao.getAllStudent();
					for(Student st :students)
					{
						System.out.println();
						System.out.println("Id: "+st.getStudentId());
						System.out.println("Name: "+st.getStudentName());
						System.out.println("City: "+st.getStudentCity());
						System.out.println("----------------------------------");
					}
					System.out.println("+++++++++++++++++++++++++++++++++++++");
					break;
				case 3:
					//get single student details
					System.out.print("Enter the studentId for which you want details: ");
					int stuId = Integer.parseInt(reader.readLine());
					Student stuDetails = dao.getStudent(stuId);
					System.out.println("                             ");
					System.out.println("Id: "+stuDetails.getStudentId());
					System.out.println("Name: "+stuDetails.getStudentName());
					System.out.println("City: "+stuDetails.getStudentCity());
					System.out.println("++++++++++++++++++++++++++++++++++++++++");
					break;
				case 4:
					//delete student
					System.out.print("Enter the studentId for which delete that student: ");
					int stId= Integer.parseInt(reader.readLine());
					dao.deleteStudent(stId);
					System.out.println("student deleted....");
					
					break;
				case 5:
					//update studet
					System.out.print("Enter the studentId whose data you want to update..");
					int studId = Integer.parseInt(reader.readLine());
					Student stud = dao.getStudent(studId);
					
					System.out.println("New Name");
					String newName = reader.readLine();
					System.out.println("New City");
					String newCity = reader.readLine();
					
					Student stud1 = new Student();
					
					stud1.setStudentName(newName);
					stud1.setStudentCity(newCity);
					
					dao.updateStudent(stud1);
					System.out.println("Sudent id "+studId +"details updated");
					break;
				case 6:
					//Exit
					go = false;
					break;
				
				}
	    		
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Invalid Input try with another!!");
				System.out.println(e.getMessage());
			}
	    	
	    }
	    System.out.println("Thank you for using our application");
	    System.out.println("see you soon!!");
    }
}
