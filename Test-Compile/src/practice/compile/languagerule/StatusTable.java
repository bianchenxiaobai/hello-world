package practice.compile.languagerule;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.Attributes.Name;
import java.util.Scanner;
import org.junit.Test;

public class StatusTable {
	// LR(1)预测份额分析表
	// 参数1:当前状态
	// 参数2:当前文法符号
	// 查表获得移入归约行为
	static List<Map<String, Action>> statusTable = new ArrayList<Map<String, Action>>();
	//表的大小属性
	public static final int statusNumber=12;
	public static final String filename="src/practice/compile/languagerule/LR1.txt";
	public static Action getAction(int st,String tokenname){
		return statusTable.get(st).get(tokenname);
	}
	// 准备好所需要的表
	/*@Test
	public void testTable() throws FileNotFoundException{
		
	}*/
	static{
		Scanner scan=null;
		try {
			scan = new Scanner(new FileInputStream(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		scan.nextLine();
		String head=scan.nextLine();
		Scanner headScan=new Scanner(head);
		List<String> nameList=new ArrayList<String>();
		while(headScan.hasNext()){
			nameList.add(headScan.next());
		}
		headScan.close();
		//System.out.println("读取的符号总共有50个");
		//一行行读取字符
		while(scan.hasNext()){
			String line=scan.nextLine();
			Scanner lineScan=new Scanner(line);
			//过略状态数字
			lineScan.next();
			//读取字符到映射中
			Map<String,Action> map=new HashMap<String,Action>();
			for(int i=0;i<nameList.size();i++){
				String str=lineScan.next();
				if(str.equals("acc")){
					map.put(nameList.get(i), Action.getAcceptAction());
				}else{
					Integer no=Integer.parseInt(str);
					if(no==0){
						map.put(nameList.get(i), Action.getErrorAction());
					}else if(no>0){
						map.put(nameList.get(i), Action.getAction(Action.SHIFT, no));
					}else{
						map.put(nameList.get(i),Action.getAction(Action.REDUCTION, -no));
					}
				}
			}
			//System.out.println("读取的map大小为"+map.size());
			statusTable.add(map);
			lineScan.close();
		}
		/*System.out.println("输出如下");
		for(int i=0;i<nameList.size();i++){
			System.out.printf("%7s",nameList.get(i));
		}
		System.out.println();
		for(int i=0;i<statusTable.size();i++){
			Map<String,Action> map=statusTable.get(i);
			for(int k=0;k<map.size();k++){
				System.out.printf("%7s",map.get(nameList.get(k)).no);
			}
			System.out.println();
		}*/
		//System.out.println("总共的符号有"+nameList.size());
		//System.out.println("总共状态有"+statusTable.size());
		//System.out.println("读取LR(1)表成功");
		scan.close();
		/*
		//初始化表
		for(int i=0;i<statusNumber;i++){
			Map<String,Action> map=new HashMap<String,Action>();
			//初始化为空白错误项
			for(String str : Sign.getAllList()){
				Action action=Action.getErrorAction();
				map.put(str,action);
			}
			statusTable.add(map);
		}
		//开始填写LR(1)表
		//第一行
		//ACTION
		statusTable.get(0).put(ID,Action.getAction(Action.SHIFT, 5));
		statusTable.get(0).put(LKUO,Action.getAction(Action.SHIFT, 4));
		//GOTO
		statusTable.get(0).put(E, Action.getAction(Action.SHIFT, 1));
		statusTable.get(0).put(T, Action.getAction(Action.SHIFT, 2));
		statusTable.get(0).put(F, Action.getAction(Action.SHIFT, 3));
		//第二行
		statusTable.get(1).put(ADD, Action.getAction(Action.SHIFT, 6));
		statusTable.get(1).put(EOF, Action.getAcceptAction());
		//第三行
		statusTable.get(2).put(ADD, Action.getAction(Action.REDUCTION, 2));
		statusTable.get(2).put(RKUO, Action.getAction(Action.REDUCTION, 2));
		statusTable.get(2).put(MUL, Action.getAction(Action.SHIFT, 7));
		statusTable.get(2).put(EOF, Action.getAction(Action.REDUCTION, 2));
		//第四行
		statusTable.get(3).put(ADD, Action.getAction(Action.REDUCTION, 4));
		statusTable.get(3).put(MUL, Action.getAction(Action.REDUCTION, 4));
		statusTable.get(3).put(RKUO, Action.getAction(Action.REDUCTION, 4));
		statusTable.get(3).put(EOF, Action.getAction(Action.REDUCTION, 4));
		//第五行
		//ACTION
		statusTable.get(4).put(ID, Action.getAction(Action.SHIFT, 5));
		statusTable.get(4).put(LKUO, Action.getAction(Action.SHIFT, 4));
		//GOTO
		statusTable.get(4).put(E, Action.getAction(Action.SHIFT, 8));
		statusTable.get(4).put(T, Action.getAction(Action.SHIFT, 2));
		statusTable.get(4).put(F, Action.getAction(Action.SHIFT, 3));
		//第六行
		//ACTION
		statusTable.get(5).put(ADD, Action.getAction(Action.REDUCTION, 6));
		statusTable.get(5).put(MUL, Action.getAction(Action.REDUCTION, 6));
		statusTable.get(5).put(RKUO, Action.getAction(Action.REDUCTION, 6));
		statusTable.get(5).put(EOF, Action.getAction(Action.REDUCTION, 6));
		//GOTO
		statusTable.get(5).put(T, Action.getAction(Action.SHIFT, 9));
		statusTable.get(5).put(F, Action.getAction(Action.SHIFT, 3));
		//第七行
		//ACTION
		statusTable.get(6).put(ID, Action.getAction(Action.SHIFT, 5));
		statusTable.get(6).put(LKUO, Action.getAction(Action.SHIFT, 4));
		//GOTO
		statusTable.get(6).put(T, Action.getAction(Action.SHIFT, 9));
		statusTable.get(6).put(F, Action.getAction(Action.SHIFT, 3));
		//第八行
		//ACTION
		statusTable.get(7).put(ID, Action.getAction(Action.SHIFT, 5));
		statusTable.get(7).put(LKUO, Action.getAction(Action.SHIFT, 4));
		//GOTO
		statusTable.get(7).put(F, Action.getAction(Action.SHIFT, 10));
		//第九行
		statusTable.get(8).put(ADD, Action.getAction(Action.SHIFT, 6));
		statusTable.get(8).put(RKUO, Action.getAction(Action.SHIFT, 11));
		//第十行
		statusTable.get(9).put(ADD, Action.getAction(Action.REDUCTION, 1));
		statusTable.get(9).put(MUL, Action.getAction(Action.SHIFT, 7));
		statusTable.get(9).put(RKUO, Action.getAction(Action.REDUCTION, 1));
		statusTable.get(9).put(EOF, Action.getAction(Action.REDUCTION, 1));
		//第十一行
		statusTable.get(10).put(ADD, Action.getAction(Action.REDUCTION, 3));
		statusTable.get(10).put(MUL, Action.getAction(Action.REDUCTION, 3));
		statusTable.get(10).put(RKUO, Action.getAction(Action.REDUCTION, 3));
		statusTable.get(10).put(EOF, Action.getAction(Action.REDUCTION, 3));
		//第十二行
		statusTable.get(11).put(ADD, Action.getAction(Action.REDUCTION, 5));
		statusTable.get(11).put(MUL, Action.getAction(Action.REDUCTION, 5));
		statusTable.get(11).put(RKUO, Action.getAction(Action.REDUCTION, 5));
		statusTable.get(11).put(EOF, Action.getAction(Action.REDUCTION, 5));
		*/
	}
}
