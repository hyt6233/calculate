package nwnuCalc;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
public class Calchyt {	
		static int MAX=20;	
		static char [] symbol={'+','-','*','/'};		
		static int shuzi[][]=new int[MAX][MAX]; 
		static char zifu[][]=new char[MAX][MAX];
		static int shuzigeshu[]=new int[MAX];   		
		Scanner scanner=new Scanner(System.in);
		
		  //判断操作符是否合格
	    private static boolean isOperator(String caozuofu){
	        if (caozuofu.equals("+")||caozuofu.equals("-")||caozuofu.equals("/")||caozuofu.equals("*")||caozuofu.equals("(")||caozuofu.equals(")")) 
	        {
	            return true;
	        }
	        return false;
	    }
	 //判断操作符的优先级
	    private static int youxianji(String  caozuofu){
	        switch (caozuofu) {
	            case "(":
	            	return 3;
                case ")":
                	return 3;
	            case "*":
	            	return 2;
	            case "/":
	            	return 2;
	            case "+":
	            	return 1;
	            case "-":
	            	return 1;            
	            default :
	            	return 0;
	        }
	    }
		
		
		
	//把中缀表达式转为后缀表达式
	private static StringBuilder houzhui=new StringBuilder();    
	private static LinkedList<String> symbols=new LinkedList<>();	
	private static LinkedList<String> shuchus=new LinkedList<>();    
	private static String ToPostfix(LinkedList<String> list){
	Iterator<String> it=list.iterator();
	while (it.hasNext()) 
	 {
		String s = it.next();
		if (isOperator(s)) 
		{
			if (symbols.isEmpty()) 
			{
			     symbols.push(s);
			}
			else 
			{
				if (youxianji(symbols.peek())<=youxianji(s)&&!s.equals(")")) 
				{
			          symbols.push(s);
			    }
			    else if(!s.equals(")")&&youxianji(symbols.peek())>youxianji(s))
			    {
			           while (symbols.size()!=0&&youxianji(symbols.peek())>=youxianji(s)&&!symbols.peek().equals("(")) 
			           {
			        	   if (!symbols.peek().equals("(")) 
			                            {
			        		   						String operator=symbols.pop();
			        		   						houzhui.append(operator).append(" ");
			                                shuchus.push(operator);
			                            }
			                        }
			                        symbols.push(s);
			                    }
			                    
			          else if (s.equals(")")) 
			          {
			                while (!symbols.peek().equals("(")) 
			                  {
			                       String operator=symbols.pop();
			                        houzhui.append(operator).append(" ");
			                        shuchus.push(operator);
			                              }
			                       
			         symbols.pop();
			                      }
			                     }
			                }
			            //读入的为非操作符
			            else 
			            {
			            	    houzhui.append(s).append(" ");
			                    shuchus.push(s);
			            }
			        }
			        if (!symbols.isEmpty()) 
			        {
			             Iterator<String> iterator=symbols.iterator();
			             while (iterator.hasNext()) 
			            {
			                String operator=iterator.next();
			                houzhui.append(operator).append(" ");
			                shuchus.push(operator);
			                iterator.remove();
			            }
			        }
			        String sum= jisuan();
			       return sum;
			    }
			    
    private static int cal(int number1,int number2,String operator){
        switch (operator){
            case "+":
            	return number1+number2;
            case "-":
            	return number1-number2;
            case "*":
            	return number1*number2;
            case "/":
            	return number1/number2;
            default :
            	return 0;
        }
}
		//随机生成运算式
		ArrayList<String> yunsuanshi=new ArrayList<String>(); 
		
		public void calaulate1(int number) {
			int huidazhengquede =0;
			
			int flag=0;
			while(flag==0)
			{
			for(int i=0;i<number;i++)
			{
				shuzigeshu[i]=(int)(Math.random()*2+4);
				
				for(int j=0;j<shuzigeshu[i];j++)
				{
					shuzi[i][j]=(int) (Math.random()*100+1);
				}
				for(int k=0;k<shuzigeshu[i]-1;k++)
				{
					zifu[i][k]=symbol[(int)(Math.random()*3)];	
				}
				for(int k=0;k<shuzigeshu[i]-1;k++)
				{
						while(zifu[i][0]==zifu[i][1])
								zifu[i][1]=symbol[(int)(Math.random()*3)];
							if(zifu[i][k]=='-' && (shuzi[i][k]<shuzi[i][k+1]))
							{
									int temp=0;
									temp=shuzi[i][k];
									shuzi[i][k+1]=temp;
									shuzi[i][k]=temp;
							}
							if((shuzi[i][k]%shuzi[i][k+1]!=0 &&zifu[i][k]=='/' ))
							{
								int temp=shuzi[i][k];
								shuzi[i][k] = shuzi[i][k] <shuzi[i][k+1]? shuzi[i][k]: shuzi[i][k+1];
								shuzi[i][k+1] = temp > shuzi[i][k+1]? temp: shuzi[i][k+1];
								for(int num = shuzi[i][k]; num >= 1; num--)
								{
									if(shuzi[i][k] % num == 0 && shuzi[i][k+1] % num == 0)
										{
											shuzi[i][k+1]=num;
											break;
										}	
								}
							}
					}
				String shizi=new String();
				LinkedList<String> zhongjianbiao=new LinkedList<>();
				zhongjianbiao.add(String.valueOf('('));zhongjianbiao.add(String.valueOf(shuzi[i][0]));zhongjianbiao.add(String.valueOf(zifu[i][0]));zhongjianbiao.add(String.valueOf(shuzi[i][1]));zhongjianbiao.add(String.valueOf(')'));zhongjianbiao.add(String.valueOf(zifu[i][1]));
				shizi+='('+String.valueOf(shuzi[i][0])+
						String.valueOf(zifu[i][0])+
						String.valueOf(shuzi[i][1])+')'
						+String.valueOf(zifu[i][1]);
					for(int j=2;j<shuzigeshu[i]-2;j++)
					{
						zhongjianbiao.add(String.valueOf(shuzi[i][j]));
						zhongjianbiao.add(String.valueOf(zifu[i][j]));
						shizi+=String.valueOf(shuzi[i][j])+String.valueOf(zifu[i][j]);
					}
					zhongjianbiao.add(String.valueOf('('));zhongjianbiao.add(String.valueOf(shuzi[i][shuzigeshu[i]-2]));zhongjianbiao.add(String.valueOf(zifu[i][shuzigeshu[i]-2]));zhongjianbiao.add(String.valueOf(shuzi[i][shuzigeshu[i]-1]));zhongjianbiao.add(String.valueOf(')'));
					shizi+='('+String.valueOf(shuzi[i][shuzigeshu[i]-2])
					+String.valueOf(zifu[i][shuzigeshu[i]-2])+
					String.valueOf(shuzi[i][shuzigeshu[i]-1])
					+')'+'=';
					String sum=ToPostfix(zhongjianbiao);
					char fir =sum.charAt(0);
					if(fir=='-')
						flag=0;
					else
						flag=1;
					System.out.print(shizi);
					String he=scanner.nextLine();
					if(he.equals(sum))
					{
						System.out.print("good job!:\n");  huidazhengquede++;
					}
					else
						System.out.print("come on!：\n");
					shizi+=sum;
					yunsuanshi.add(shizi);
			}
			System.out.print("共"+number+"道，正确"+huidazhengquede+"道\n");
		}
		}

		
	  //计算结果
	    private static String jisuan(){
	        LinkedList<String> jieguoList=new LinkedList<>();
	        String[] postStr=houzhui.toString().split(" ");
	        for (String reslut:postStr) 
	        {
	            if (isOperator(reslut))
	            {
	                if (!jieguoList.isEmpty())
	                {
	                    int num1=Integer.valueOf(jieguoList.pop());
	                    int num2=Integer.valueOf(jieguoList.pop());
	                    int newNum=cal(num2,num1,reslut);
	                    jieguoList.push(String.valueOf(newNum));
	                }
	            }
	            else 
	            { 
	            	//如果是数字就压入栈中
	            	jieguoList.push(reslut);
	            }
	        }
	        if (!jieguoList.isEmpty())
	        {
	            return jieguoList.pop();
	        }
			return null;
	    }



		}

	

