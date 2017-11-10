package java.fsops;
import java.util.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String cmd, String args[])
    {
        int n = args.length;
        if(cmd.equals("mkdir")) {
        	for(int i = 0; i < n; i++) {
        		File file = new File(args[i]);
        		file.mkdir();
        	}
        }
        if(cmd.equals("nfile")) {
        	for(int i = 0; i < n; i++) {
        		File file = new File(args[i]);
        		try {
        			file.createNewFile();
        		}
        		catch(Exception e){
        	         e.printStackTrace();
        	      }
        	}
        }
        if(cmd.equals("rm")) {
        	for(int i = 0; i < n; i++) {
        		File file = new File(args[i]);
        		file.delete();
        	}
        }
        if(cmd.equals("ls")) {
        	File file = new File(args[0]);
        	String[] info = file.list();
        	int len = info.length;
        	for(int i = 0; i < len; i++) {
        		System.out.println(info[i]);
        	}
        }
    }
}