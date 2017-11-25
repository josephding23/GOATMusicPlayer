package fsops;
import java.io.*;
import java.sql.*;  
import java.text.*;
import java.util.Date;
import java.util.logging.*;
import java.nio.file.*;
import java.nio.file.attribute.*;

/**
 * Hello world!
 *
 */
public class FileManagerWithLogAttached
{
	FileManagerWithLogAttached() {	}
	public static void createDirectory(String args[]) {
        int n = args.length;
		for(int i = 5; i < n; i++) {
    		File file = new File(args[i]);
    		if(!file.exists()) {
    			file.mkdir();
    			System.out.println("Successfully Made " + args[i]);
    		}
    		else {
    			System.out.println("The directory is already there.");
    		}
    	}
	}
	public static void createNewFile(String args[]) {
        int n = args.length;
		for(int i = 5; i < n; i++) {
    		File file = new File(args[i]);
    		try {
    			file.createNewFile();
    			System.out.println("Successfully Created " + args[i]);
    		}
    		catch(Exception e){
    	         e.printStackTrace();
    	      }
    	}
    }
	public static void remove(String args[]) {
        int n = args.length;
    	for(int i = 5; i < n; i++) {
    		File file = new File(args[i]);
    		if(file.exists()) {
    			file.delete();
    			System.out.println("Susssfully removed " + args[i]);
    		}
    		else {
    			System.out.println("Cannot find the directory, please input the right directory.");
    		}
    	}
	}
	
	public static void list(String arg[]) throws IOException{
    	File file = new File(arg[5]);
    	if(file.exists()) {
    		File lists[] = file.listFiles();
    		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    		String lastMdf=df.format(new Date(file.lastModified()));
        	int len = lists.length;
        	for(int i = 0; i < 60; i++)
        		System.out.format("*");
        	System.out.println();
        	String[] info = {"Name", "Create Date", "Last Modified", "Type"};
        	System.out.format("%-30s%-30s%-30s%-30s\n", info[0], info[1], info[2], info[3]);
    		for(int i = 0; i < len; i++) {
    			String type = lists[i].isDirectory()? "d" : "f";
    			System.out.format("%-30s%-30s%-30s%-30s\n", lists[i].getName(), getCreateTime(lists[i].getPath()), lastMdf, type);
    		}
    	}
    	else 
    		System.out.println("Cannot find the directory that you are intended to list.");
    }
	public static String getCreateTime(String filePath) throws IOException{  
		Path p = Paths.get(filePath);
	    	BasicFileAttributes view = Files.getFileAttributeView(p, BasicFileAttributeView.class).readAttributes();
		    long creationTime = view.creationTime().toMillis();   
		    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    		String crtTime=df.format(new Date(creationTime));
    		return crtTime;
	}
	public static void printHelp() {
    	System.out.format("Manipulations exist in this JAR: \n"
    			+ " 1. Making a new directory: \"mkdir\" + #namesOfDirectories\n"
    			+ " 2. Creating a new file: \"nfile\" + #namesOfDirectories\n"
    			+ " 3. Removing a file or a directory: \"rm\" + #namesOfDirectories\n"
    			+ " 4. Listing information of files inside a directory\n");
    	}
	public static void printSelf() {
		System.out.format("Application Name: FileManager\n"
				+ "Author & Copyright: Joseph Ding\n"
				+ "Current Version: v1.0\n"
				+ "Date of Birth: 17.10");
	}
	public void inputInfo(String uml, String ops, String hostname, String password, String date)  
	  {  
		Connection conn = null;  
	    Statement stmt = null;     
	    try  
	    {  
	      conn = DriverManager.getConnection(uml, hostname, password);   
	      stmt = conn.createStatement();  
	    }
	    catch (SQLException ex) {
	    	System.out.println("Database not exits");
	    	} 
	    try{
	      String query = "insert into filesyslog (hostname, operation, date) value ('" + hostname + "', '" + ops + "', '" + date + "')";
	      stmt.executeUpdate(query);  
	    }  
	    catch (SQLException ex) {
	    	System.out.println("Table not exists, system automatically create filesyslog");
	    	try {
	    	stmt.executeUpdate("create table filesyslog " +  "(id int not null auto_increment, " + "hostname varchar(30), operation varchar(30), date varchar(40), primary key(id))");
	    	stmt.executeUpdate("alter table filesyslog auto_increment=1");
	    	String query = "insert into filesyslog (hostname, operation, date) value ('" + hostname + "', '" + ops + "', '" + date + "')";
	    	stmt.executeUpdate(query);
	    	} 
	    	catch(SQLException exx) {
		    	Logger lgr = Logger.getLogger(FileManagerWithLogAttached.class.getName());
		    	lgr.log(Level.SEVERE, exx.getMessage(), exx);	
	    	}
	    }
	    finally {
		    	try {
		    		if (stmt != null) {
		    			stmt.close();
			    	}
			    	if (conn != null) {
			    		conn.close();
			    	}
		    	} catch (SQLException ex) {
		    		Logger lgr = Logger.getLogger(FileManagerWithLogAttached.class.getName());
		    		lgr.log(Level.SEVERE, ex.getMessage(), ex);
		    	}
	    	}
	  }  
    public static void main(String args[]) throws ClassNotFoundException
    {
    	Class.forName("com.mysql.jdbc.Driver");
    	String cmd = args[0], uml = args[1] + "?useUnicode=true&characterEncoding=utf-8&useSSL=false", ops = args[4];
    	String hostname = args[2];String password = args[3];;
    	FileManagerWithLogAttached fileman = new FileManagerWithLogAttached();
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	if(cmd.equals("-logdb")) {
	        if(ops.equals("mkdir")) {
	        	createDirectory(args);
	        	fileman.inputInfo(uml, ops, hostname, password, df.format(new Date()));
	        	System.out.println("Log updated�� \nUML: " + uml + "\nHostname: " + hostname + "\nPassword: " + password + "Operation: " + ops);
	        }
	        if(ops.equals("nfile")) {
	        	createNewFile(args);
	        	fileman.inputInfo(uml, ops, hostname, password, df.format(new Date()));
	        	System.out.println("Log updated�� \nUML: " + uml + "\nHostname: " + hostname + "\nPassword: " + password + "Operation: " + ops);
	        }
	        if(ops.equals("rm")) {
	        	remove(args);
	        	fileman.inputInfo(uml, ops, hostname, password, df.format(new Date()));
	        	System.out.println("Log updated�� \nUML: " + uml + "\nHostname: " + hostname + "\nPassword: " + password + "Operation: " + ops);
	        }
	        if(ops.equals("ls"))
				try {
					list(args);
		        	fileman.inputInfo(uml, ops, hostname, password, df.format(new Date()));					
		        	System.out.println("Log updated�� \nUML: " + uml + "\nHostname: " + hostname + "\nPassword: " + password + "Operation: " + ops);
				} catch (IOException e) {
					e.printStackTrace();
				}
	        if(ops.equals("help")) {
	        	printHelp();
	        	fileman.inputInfo(uml, ops, hostname, password, df.format(new Date()));
	        	System.out.println("Log updated�� \nUML: " + uml + "\nHostname: " + hostname + "\nPassword: " + password + "Operation: " + ops);
	        }
	        if(ops.equals("self")) {
	        	printSelf();
	        	fileman.inputInfo(uml, ops, hostname, password, df.format(new Date()));
	        	System.out.println("Log updated�� \nUML: " + uml + "\nHostname: " + hostname + "\nPassword: " + password + "Operation: " + ops);
	        }
	      }
    }
}