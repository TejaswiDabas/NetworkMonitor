package first;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
class ShowIP
{
	public static void main(String []args)throws Exception	
	{
			// Create operating system process from arpe.bat file command
			ProcessBuilder pb = new ProcessBuilder("arpe.bat");  
 
			pb.redirectErrorStream();
			// Starts a new process using the attributes of this process builder                            
			Process p = pb.start();				
				 
			BufferedReader br = new BufferedReader (new InputStreamReader(p.getInputStream()));
 
			// String out is used to store output of this command(process)
			String out="";                                              		    	     
 
			while(true)
			{
				String l=null;
				try 
				{
					l = br.readLine();
				} 
				catch (IOException ex) {}
				if(l==null)
					break;
				out+="\n"+l;
			}
 
			// A compiled representation of a regular expression
			Pattern pattern = 
Pattern.compile(".*\\b\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\b"); 
 
			/* An engine that performs match operations on a character sequence by interpreting a Pattern */
			Matcher match = pattern.matcher(out);			
 
			out="";
			String prev="",pLoc;
 
			if(!(match.find()))        // In case no IP address Found in out
				out="No IP found!";
 
			else
			{
 
				/* Returns the input subsequence matched by the previous match in this case IP of our interface */
				pLoc = match.group();  
                    
				out+=pLoc+"\nOther Hosts'(In Same Network) IP addresses:\n";
				while(match.find())	 
				{
					pLoc = match.group();	// Returns the IP of other hosts
					out+=pLoc+"\n";
				}
				try 
				{
					br.close();
				} 
				catch (IOException ex) {}
			}
 
			// Printing IP Addresses of all computers in our network
			System.out.println(out);			
	}
}
