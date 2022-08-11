/* Name: Mohammad Komol Hasan
 * CUNY ID: 23422236
 */
package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		try 
		{
			String repoLocation;
			System.out.println("Please enter the loaction of the repo file");
			repoLocation = input.nextLine();
			BufferedReader br = new BufferedReader(new FileReader(repoLocation + "\\Repo.txt"));
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/e_community";
			System.out.println("Enter username:");
			String username = input.nextLine();
			System.out.println("Enter password:");
			String password = input.nextLine();
			Class.forName(driver);
			
			Connection comm = DriverManager.getConnection(url,username,password);
			System.out.println("Connection Successful");
			
			/*The following lines get four sql queries from the repo file and executes them */
			
			String queryStr = br.readLine();
			PreparedStatement ps = comm.prepareStatement(queryStr);
			
			ResultSet result = ps.executeQuery();
			
			System.out.println();
			System.out.println("This gets the name of everyone living in New Jery and was designed by me");
			System.out.println("Executed Query: " + queryStr);
			while(result.next())
			{
				System.out.println("Name: "+result.getString("name"));
				System.out.println();
			}
			
			String query2 = br.readLine();
			PreparedStatement ps2 = comm.prepareStatement(query2);
			ResultSet res = ps2.executeQuery();
			
			System.out.println();
			System.out.println("This gets the name and number of all the professors and was desinged by me");
			System.out.println("Executed Query: " + query2);
			while(res.next())
			{
				System.out.println("Name: "+res.getString("name"));
				System.out.println("Phone: "+res.getString("phone"));
				System.out.println();
			}
			
			
			String query3 = br.readLine();
			PreparedStatement ps3 = comm.prepareStatement(query3);
			ResultSet res1 = ps3.executeQuery();
			
			System.out.println();
			System.out.println("This is the first question of the Spring Break homework");
			System.out.println("This gets the name, phone and address of all students taking a given course.");
			System.out.println("Here the given course is medical");
			System.out.println("Executed Query: " + query3);
			while(res1.next())
			{
				System.out.println("Name: "+res1.getString("name"));
				System.out.println("Phone: "+res1.getString("phone"));
				System.out.println("Street: "+res1.getString("street"));
				System.out.println();
			}
			
			String query4 = br.readLine();
			PreparedStatement ps4 = comm.prepareStatement(query4);
			ResultSet res2 = ps4.executeQuery();
			
			System.out.println();
			System.out.println("This is the second question of the Spring Break homework");
			System.out.println("This gets the name, phone and address of all students taking a given area code of a phone number.");
			System.out.println("Here the given course is 718");
			System.out.println("Executed Query: " + query4);
			while(res2.next())
			{
				System.out.println("Name: "+res2.getString("name"));
				System.out.println("Phone: "+res2.getString("phone"));
				System.out.println("Street: "+res2.getString("street"));
				System.out.println("Zip: "+res2.getString("zip"));
				System.out.println("City: "+res2.getString("city"));
				System.out.println("State: "+res2.getString("state"));
				System.out.println();
			}
			
			String query5 = br.readLine();
			PreparedStatement ps5 = comm.prepareStatement(query5);
			ResultSet res3 = ps5.executeQuery();
			
			System.out.println();
			System.out.println("This is the query of the final project");
			System.out.println("This gets the name and phone of all students taking a given course based on the top percentage of posts and attachments.");
			System.out.println("Here the given course is anything with 'Data' and top 5 percentage");
			System.out.println("Executed Query: " + query5);
			while(res3.next())
			{
				System.out.println("Name: "+res3.getString("name"));
				System.out.println("Phone: "+res3.getString("phone"));
				System.out.println();
			}
			/*This part lets the user store sql queries in the repo file */
			
			Writer output;
			output = new BufferedWriter(new FileWriter((repoLocation + "\\Repo.txt"),true));
			System.out.println("Press 1 from SELECT, 2 for INSERT, 3 for DELETE, 4 for UPDATE and 5 for SELECT ALL");
			int IN = input.nextInt();
			String sqlWrite;
			String column;
			String table;
			String condition;
			String value;
			if(IN == 1)
			{
				input.nextLine();
				System.out.println("Which coulumn(s) would you like to select? Please include a ',' between column names");
				column = input.nextLine();
				System.out.println("Which table would you like to select from?");
				table = input.nextLine();
				System.out.println("Please type the condition for SELECT");
				condition = input.nextLine();
				sqlWrite = ("SELECT "+ column + "FROM "+ table+ "WHERE "+ condition + ";");
				output.append("\n"+sqlWrite);
				System.out.println("Done. Please see repo file for the stored query");
			}
			if(IN == 2)
			{
				input.nextLine();
				System.out.println("Enter table name");
				table = input.nextLine();
				System.out.println("Which coulumn(s) would you like to select? Please include a ',' between column names");
				column = input.nextLine();
				System.out.println("Enter values to insert with ' ' and seperate values with ','");
				value = input.nextLine();
				sqlWrite = ("INSERT INTO "+table + "("+column+")"+" VALUES "+value+ ";");
				output.append("\n"+sqlWrite);
				System.out.println("Done. Please see repo file for the stored query");
			}
			if(IN == 3)
			{
				input.nextLine();
				System.out.println("Enter table name");
				table = input.nextLine();
				System.out.println("Please type the condition for DELETE");
				condition = input.nextLine();
				sqlWrite = ("DELETE FROM "+table + "WHERE "+ condition+ ";");
				output.append("\n"+sqlWrite);
				System.out.println("Done. Please see repo file for the stored query");
			}
			if(IN == 4)
			{
				input.nextLine();
				System.out.println("Enter table name");
				table = input.nextLine();
				System.out.println("Which coulumn would you like to update?");
				column = input.nextLine();
				System.out.println("Enter values to insert with ' '");
				value = input.nextLine();
				System.out.println("Please type the condition for UPDATE");
				condition = input.nextLine();
				sqlWrite = ("UPDATE "+ table + "SET "+ column + " = "+value + "WHERE "+ condition + ";");
				output.append("\n"+sqlWrite);
				System.out.println("Done. Please see repo file for the stored query");
			}
			if(IN == 5)
			{
				input.nextLine();
				System.out.println("Enter table name");
				table = input.nextLine();
				sqlWrite = ("SELECT"+"*"+"FROM "+table);
				output.append("\n"+sqlWrite);
				System.out.println("Done. Please see repo file for the stored query");
			}
			
			
			
			output.close();
			br.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
		
		
	input.close();	
	
	
	}
	
}
