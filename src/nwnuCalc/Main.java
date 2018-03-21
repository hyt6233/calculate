package nwnuCalc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Main {
	static int number;
	public static void main(String args[]) throws IOException
	{
		//输入参数
		System.out.print("input the number of suanshi：");
		
		Scanner scan=new Scanner(System.in);
		
		number=scan.nextInt();
		
		Calchyt calc=new Calchyt();
		//进行计算
		calc.calaulate1(number);
		
		//输入到文件中
		File f=new File("./result.txt");
        BufferedWriter pw=new BufferedWriter(new FileWriter(f));
        pw.write("201571030111  郝延婷");
        pw.newLine();
        for(int i=0;i<calc.yunsuanshi.size();i++){
             pw.write(calc.yunsuanshi.get(i));  pw.newLine();
         }
         pw.close();
	}
}