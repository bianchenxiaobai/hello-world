package practice.compile.languagerule;

import java.util.Scanner;

public class Count {
	public static void main(String[] args){
		Scanner scan=new Scanner(System.in);
		int cnt=0;
		while(true){
			String str=scan.next();
			if(str.equals("@")){
				break;
			}else{
				cnt++;
			}
		}
		System.out.println("�ܹ��ַ�����Ϊ="+cnt);
	}
}
