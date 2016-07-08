package trial;

import trial.helper;

import java.io.*;
public class Readnow {

public static void main(String[] args) {	
try {	
BufferedReader br1 = null;


br1 = new BufferedReader(new InputStreamReader(System.in));

System.out.println("Enter input file name:");	
String input_file_name=br1.readLine();

System.out.println("Enter output file name:");	
String output_file_name=br1.readLine();


double values[][] = helper.readMatrixSequenceFile(input_file_name);

String content="";
for(int i=0; i<values.length;i++)
{
for(int j=0; j<values[i].length;j++)
content+=values[i][j]+" ";
content+="\n";
}

File file = new File(output_file_name);

// if file doesnt exists, then create it
if (!file.exists()) 

file.createNewFile();

FileWriter fw = new FileWriter(file.getAbsoluteFile());
BufferedWriter bw = new BufferedWriter(fw);
bw.write(content);
bw.close();



} catch (Exception e) {
e.printStackTrace();
} 

}
}