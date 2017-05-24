package practice.compile.languagerule;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Stack;

public class SimpleLRStackTest {
	// 状态栈，符号栈
	private static Stack<Integer> stStack = new Stack<Integer>();
	private static Stack<String> signStack = new Stack<String>();
	//词法单元流
	private static List<Token> tokens;
	public static int i = 0;
	public static void main(String[] args) throws FileNotFoundException {
		tokens=TokenKnown.getTokenList();
		tokens.add(new Token(Token.EOF,"$"));
		TokenKnown.showTokens(tokens);
		//初始化状态为0
		stStack.push(0);
		//初始化读取指针为开始
		int k=0;
		System.out.printf("%20s %20s %40s\n","status","sign","action");
		
		while(true){
			Token token=tokens.get(k);
			Action action=StatusTable.getAction(stStack.peek(),token.name);
			//输出当前状态
			StringBuilder sb=null;
			sb=new StringBuilder();
			for(int i=0;i<stStack.size();i++){
				sb.append(""+stStack.get(i));
			}
			System.out.printf("%20s ",sb.toString());
			sb=new StringBuilder();
			//输出当前符号栈
			for(int i=0;i<signStack.size();i++){
				sb.append(""+signStack.get(i));
			}
			System.out.printf("%20s ", sb.toString());
			if(action.type==Action.SHIFT){
				//移动到状态i同时更新
				stStack.push(action.no);
				signStack.push(token.name);
				System.out.printf("%40s ","移入状态"+action.no);
				k++;
			}else if(action.type==Action.REDUCTION){
				//使用第i个产生式进行归约
				List<String> list=CreateTable.getRightList(action.no);
				//弹出右边部分，加入左边部分,同步弹出状态
				for(int i=0;i<list.size();i++){
					signStack.pop();
					stStack.pop();
				}
				//重新使用左部更新状态
				signStack.push(CreateTable.getLeftNTSign(action.no));
				Action gt=StatusTable.getAction(stStack.peek(), CreateTable.getLeftNTSign(action.no));
				stStack.push(gt.no);
				//更新成功
				System.out.printf("%40s ","归约产生式为"+CreateTable.getAllList(action.no));
			}else if(action.type==Action.ACCEPT){
				System.out.println();
				System.out.println("输入字符串被接受");
				break;
			}else{
				System.out.println();
				System.out.println("st=="+stStack.peek());
				System.out.println("token.name="+token.name);
				System.out.println(StatusTable.getAction(stStack.peek(), token.name).type);
				System.out.println(StatusTable.getAction(stStack.peek(), token.name).no);
				System.out.println("发生了一个错误");
				break;
			}
			System.out.println();
		}
	
	}
}
