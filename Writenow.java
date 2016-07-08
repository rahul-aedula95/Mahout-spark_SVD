package trial;

import trial.helper;

import java.io.*;
public class Writenow {

public static void main(String[] args) {	
	BufferedReader br = null;
try {	
	BufferedReader br1 = null;
	

	br1 = new BufferedReader(new InputStreamReader(System.in));

System.out.println("Enter n:");
int n=Integer.parseInt(br1.readLine());

System.out.println("Enter input file name:");	
String file_name=br1.readLine();

double values[][]= new double[n][n];

String line;

br = new BufferedReader(new FileReader(file_name));

for(int i=0; i<n; i++)
{
int k=0;
line = br.readLine()+" ";
String number="";	

for(int j=0; j<line.length();j++)
{

char ch=line.charAt(j);
if(Character.isDigit(ch)||ch=='.')
number=number+ch;
else if(number!=null && number!="")
{
values[i][k++]=Double.parseDouble(number);
number="";
}

}
}

helper.writeMatrixSequenceFile("newsequencefile.seq", values);




} catch (Exception e) {
e.printStackTrace();
} finally {
try {
if (br != null)br.close();
} catch (IOException ex) {
ex.printStackTrace();
}
}

}
}

