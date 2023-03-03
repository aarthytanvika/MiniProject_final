package developer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class database {

	public static void main(String[] args) {
		
		String dbURL="jdbc:mysql://localhost:3306/test";
		String username="root";
		String password="Aarthy@1";
		Connection con;
		int ch=0;
		int op=0;
		Scanner sc=new Scanner(System.in);
		{
			try {
				con=DriverManager.getConnection(dbURL, username, password);
				Statement ss1=con.createStatement();
				if(con!=null){
					System.out.println("connected");
				}
				System.out.println("                       ****************WELCOME TO STUDENT DATABASES***************");
				
				System.out.print("@ Enter Admin Username:-->");
				String username1=sc.next();
				System.out.print("@ Enter Admin Password:-->");
				String password1=sc.next();
				System.out.println();
				String sqla="select * from admin where username='"+username1+"' and pass='"+password1+"'";
				ResultSet rs=ss1.executeQuery(sqla);
				if(rs.next())
				{
					System.out.println(" ----->Login Process success");
					System.out.println();
				}
				else
					System.out.println(" ----->Login Process Failed");
				    System.out.println();
				   
				do {
					switch(op) {
					 case 1:
				do {
							
					System.out.println("             ## INSERT THE STUDENTS RECORD ##");
					System.out.print("ENTER ID :");
					int id=sc.nextInt();
					System.out.print("ENTER NAME :");
					String name=sc.next();
					System.out.print("ENTER COURSE :");
					String course=sc.next();
					System.out.print("ENTER FEES :");
					int fees=sc.nextInt();
					System.out.print("ENTER STATUS :");
					String status=sc.next();
									
					String sql="insert into ttable(id,name,course,fees,status)values(?,?,?,?,?)";
							
					PreparedStatement statement=con.prepareStatement(sql);
					statement.setInt(1, id);
					statement.setString(2, name);
					statement.setString(3, course);
					statement.setInt(4, fees);
					statement.setString(5, status);
					int input=statement.executeUpdate();
					if(input>0) {
						System.out.println("    ----> Inserted  Successfully!!!");
					}
					ch=sc.nextInt();
					}while(ch==1);
					break;
					case 2:
						System.out.println("                   ## UPDATE THE STUDENTS RECORD ##");
						System.out.print("Enter Student status :");
						String status=sc.next();
						String sql2="update ttable set status=? where id=?";
						PreparedStatement statement2=con.prepareStatement(sql2);
						statement2.setString(1,status);
						statement2.setInt(2,18);
						int Updated=statement2.executeUpdate();
						if(Updated>0) {
							System.out.println("  ----->  Updated Successfully!!!!");
						}break;
						
					 case 3:
						System.out.println("                    ## DELETE THE STUDENT RECORDS ##");
						System.out.print(" Enter Student ID :");
						int did=sc.nextInt();
						String sql3="delete from ttable where id=?";
						PreparedStatement statement3=con.prepareStatement(sql3);
						statement3.setInt(1, did);
						int Deleted=statement3.executeUpdate();
						if(Deleted>0) {
							System.out.println("  ----->   Deleted Successfully!!!!");
						}break;
					 case 4:
						System.out.println("                     ## DISPLAY ALL THE STUDENTS RECORD ##");
						String sql1="select * from ttable";
						Statement stat=con.createStatement();
						ResultSet result=stat.executeQuery(sql1);
						int count=0;
						while(result.next()) {
							int eid=result.getInt("id");
							String ename=result.getString("name");
							String ecourse=result.getString("course");
							int efees=result.getInt("fees");
							String estatus=result.getString("status");
							
							String output="student#%d: 1).%d   2).%s   3).%s   4).%d   5).%s";
							System.out.println(String.format(output, ++count,eid,ename,ecourse,efees,estatus));
							System.out.println(" -----> Records Displayed Successfully!!!");
						}
						break;
					 case 5:
						System.out.println("                  ## RETRIVE  PARTICULAR  RECORD ##");
						String sql5="select * from ttable where course='java'";
						Statement stat1=con.createStatement();
						ResultSet result1=stat1.executeQuery(sql5);
						int count1=0;
						while(result1.next()) {
							int fid=result1.getInt(1);
							String fname=result1.getString(2);
							String fcourse=result1.getString(3);
							int ffees=result1.getInt(4);
							String fstatus=result1.getString(5);
							
							String output="student#%d:  1.%d  2.%s  3.-%s  4.%d   5.%s";
							System.out.println(String.format(output, ++count1,fid,fname,fcourse,ffees,fstatus));
							System.out.println(" -----> Retrived Successfully!!!");
						}
						break;
					 case 6:
						Statement stat2=con.createStatement();
						System.out.println("------>Thanking You For Visit Our Database <------");
						System.out.println();
						break;
					}
					
					System.out.println("*******Enter Your Choices********\n  1.Add \n  2.Update \n  3.Delete \n  4.Display \n  5.Extract \n  6.Exit \n");
					op=sc.nextInt();
				}while(op!=0);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		   }
		 }	
	   }


