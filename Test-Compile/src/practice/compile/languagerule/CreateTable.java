package practice.compile.languagerule;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static practice.compile.languagerule.NTSign.*;
import static practice.compile.languagerule.Token.*;
import org.junit.Test;

public class CreateTable {
	// 存储产生式
	// 第一个为起始符号，后面为产生符号
	static List<List<String>> createTable = new ArrayList<List<String>>();
	//得到第i个产生式的左边部分
	public static String getLeftNTSign(int i){
		return createTable.get(i).get(0);
	}
	//得到第i个产生式的右边部分
	public static List<String> getRightList(int i){
		List<String> list=createTable.get(i);
		return list.subList(1, list.size());
	}
	//得到第i个产生式全部符号
	public static List<String> getAllList(int i){
		return createTable.get(i);
	}
	@Test
	public void testGetNTSign(){
		for(int i=0;i<createTable.size();i++){
			System.out.println("产生式"+i);
			System.out.println("左部非终结符号-"+CreateTable.getLeftNTSign(i));
			System.out.println("右边产生符号-"+CreateTable.getRightList(i));
			System.out.println("全部符号-"+CreateTable.getAllList(i));
		}
	}
	@Test
	public void testGetRightList(){
		
	}
	@Test
	public void testCreateTable() {
		for (int i = 0; i < createTable.size(); i++) {
			System.out.println(createTable.get(i));
		}
	}
	public static final String filename="src/practice/compile/languagerule/create.txt";
	// 开始存储产生式
	static {
		try {
			Scanner scan=new Scanner(new FileInputStream(filename));
			while(scan.hasNext()){
				String line=scan.nextLine();
				Scanner lineScan=new Scanner(line);
				if(lineScan.hasNext()){
					//加入头部
					List list=new ArrayList<String>();
					String head=lineScan.next();
					list.add(head);
					//过滤箭头
					if(lineScan.hasNext())
						lineScan.next();
					while(lineScan.hasNext()){
						String body=lineScan.next();
						//过滤空串
						if(!body.equals("''"))
							list.add(body);
					}
					createTable.add(list);
				}
			}
			//System.out.println("产生式输入完成");
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		
		/*List<String> list;
		// 0--E'->E
		list = new ArrayList<String>();
		list.add(Eo);
		list.add(E);
		createTable.add(list);
		// 1--E->E+T
		list = new ArrayList<String>();
		list.add(E);
		list.add(E);
		list.add(Token.ADD);
		list.add(T);
		createTable.add(list);
		// 2--E->T
		list = new ArrayList<String>();
		list.add(E);
		list.add(T);
		createTable.add(list);
		// 3--T->T*F
		list = new ArrayList<String>();
		list.add(T);
		list.add(T);
		list.add(Token.MUL);
		list.add(F);
		createTable.add(list);
		// 4--T->F
		list = new ArrayList<String>();
		list.add(T);
		list.add(F);
		createTable.add(list);
		// 5--F->(E)
		list = new ArrayList<String>();
		list.add(F);
		list.add(Token.LKUO);
		list.add(E);
		list.add(Token.RKUO);
		createTable.add(list);
		// 6--F->id
		list = new ArrayList<String>();
		list.add(F);
		list.add(Token.ID);
		createTable.add(list);*/
	}
}
