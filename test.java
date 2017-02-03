import java.io.*;
import java.util.*;
class RecursiveFileDisplay 
{
	static FileWriter wr;
	static PrintWriter out;
	public static void main(String[] args) 
	{
		try
		{
            wr=new FileWriter("output.txt");//Indian phone numbers written in this file
			out=new PrintWriter(wr);
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
		File currentDir = new File("."); // current directory
		File output=new File("output.txt");
		displayDirectoryContents(currentDir);//recursively traversing the directory to read all text files to get the required output
		out.flush();
		out.close();
		System.out.println("Output Written in "+output.getAbsolutePath());//printing the absolute path of the output file
	}
	public static void displayDirectoryContents(File dir) 
	{	
			File[] files = dir.listFiles();//storing all the files and folders in "dir"
			for (File file : files) 
			{
				if(file.isFile()) 
				{
				    checkValidPhoneNumbers(file);		  
				}
				else if(file.isDirectory())
				{
				  displayDirectoryContents(file);//if its a directory than recurse again
				}
			}
	}
	public static void checkValidPhoneNumbers(File file)
	{
		String s=file.getName();
		if(s.endsWith(".txt"))//check if the file is a text file or not
		{
		 try
		 {
		    FileReader in=new FileReader(file);
		    Scanner d=new Scanner(in);
			String x;
			//read the file
			while(d.hasNext())
			{
				x=d.next();
				int count=0,count1=0; 
				boolean flag=true;
				if(x.startsWith("+91"))//if a String is an Indian phone numbers or not
				{
					//if the phone number start with +91 followed by not more than one dash and has 10 digit number after that
					for(int i=3;i<x.length();i++)
					{
						if((int)x.charAt(i)>47&&(int)x.charAt(i)<58)
							count++;
						else if(x.charAt(i)=='-')
							count1++;
						else 
							flag=false;
					}
					if(flag&&count==10&&count1<=1)
						out.println(x);
				}
			}
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
	   }
	}
}