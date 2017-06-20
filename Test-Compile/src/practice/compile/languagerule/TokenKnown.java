package practice.compile.languagerule;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TokenKnown {
	private static int state=0,index=0,head=0;
	private static char ch=0;
	private static String str=null;
	private static String filename="src/practice/compile/languagerule/input2.txt";
	public static Token getToken(){
		switch(state){
		case 0:
			if(ch=='<'){state=1;break;}
			else if(ch=='!'){state=10;break;}
			else if(ch=='='){state=11;break;}
			else if(ch=='>'){state=2;break;}
			else if(ch=='&'){state=12;break;}
			else if(ch=='|'){state=13;break;}
			else if(ch=='+'){return new Token(Token.ADD,'+');}
			else if(ch=='-'){return new Token(Token.SUB,'-');}
			else if(ch=='*'){return new Token(Token.MUL,'*');}
			else if(ch=='/'){return new Token(Token.DIV,'/');}
			else if(ch=='('){return new Token(Token.LKUO,'(');}
			else if(ch==')'){return new Token(Token.RKUO,')');}
			else if(ch=='['){return new Token(Token.LMKUO,'[');}
			else if(ch==']'){return new Token(Token.RMKUO,']');}
			else if(ch=='{'){return new Token(Token.LLKUO,'{');}
			else if(ch=='}'){return new Token(Token.RLKUO,'}');}
			else if(ch==';'){return new Token(Token.SIGN_DIV,';');}
			else if(Character.isLetter(ch)){state=3;break;}
			else if(Character.isDigit(ch)){state=4;break;}
			else{break;}
		case 1://<
			if(ch=='='){return new Token(Token.LE,"<=");}
			else{index--;return new Token(Token.LT,"<");}
		case 2://>
			if(ch=='=')return new Token(Token.GE,">=");
			else{index--;return new Token(Token.GT,">");}
		case 3://字母标识符
			if(Character.isLetter(ch)||Character.isDigit(ch))break;
			else{index--;String word=str.substring(head, index+1);
			return new Token(KeyTable.getTokenName(word),word);}
		case 4://数字
			if(Character.isDigit(ch)){break;}
			else if(ch=='.'){state=5;break;}
			else if(Character.toUpperCase(ch)=='E'){state=7;break;}
			else{index--;return new Token(Token.NUM,Integer.valueOf(str.substring(head, index+1)));}
		case 5://数字子状态
			if(Character.isDigit(ch)){state=6;break;}
			else{index--;return new Token(Token.ERROR,"数字.后面应该跟数字");}
		case 6://数字子状态
			if(Character.isDigit(ch)){break;}
			else if(Character.toUpperCase(ch)=='E'){state=7;break;}
			else{index--;return new Token(Token.REAL,Double.valueOf(str.substring(head, index+1)));}
		case 7://数字子状态
			if(ch=='+'||ch=='-'){state=8;break;}
			else if(Character.isDigit(ch)){state=9;break;}
			else{index--;return new Token(Token.ERROR,"数字E后面应该跟数字");}
		case 8://数字子状态
			if(Character.isDigit(ch)){state=9;break;}
			else{index--;return new Token(Token.ERROR,"数字E+-后面应该跟数字");}
		case 9://字母标识符子状态
			if(Character.isDigit(ch)){break;}
			else{index--;String word=str.substring(head, index+1);
			return new Token(KeyTable.getTokenName(word),word);}
		case 10://!,!=
			if(ch=='='){return new Token(Token.NEOP,null); 
			}else{index--;return new Token(Token.ROP,null);}
		case 11://=,==
			if(ch=='='){return new Token(Token.EOP,"==");}
			else{index--;return new Token(Token.ASSIGN,null);}
		case 12://&
			if(ch=='&'){return new Token(Token.AND,"&&");}
			else{index--;return new Token(Token.ERROR,"未识别逻辑与&符号");}
		case 13://|
			if(ch=='|'){return new Token(Token.OR,"||");}
			else{index--;return new Token(Token.ERROR,"未识别逻辑或|符号");}
		default:break;
		}
		return null;
	}
	public static List<Token> getTokenList() throws FileNotFoundException{
		return getTokenList(filename);
	}
	public static List<Token> getTokenList(String filename) throws FileNotFoundException{
		List<Token> list=new ArrayList<Token>();
		boolean flag=true;
		StringBuilder sb=new StringBuilder();
		BufferedReader reader;
		reader = new BufferedReader(new FileReader(filename));
		int bufsize=1024,len;
		char buf[]=new char[bufsize];
		while(true){
			try {
				len=reader.read(buf, 0, bufsize);
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
			if(len!=-1){
				String bufstr=new String(buf,0,len);
				sb.append(bufstr);
			}else{
				break;
			}
		}
		sb.append('$');
		str=sb.toString();
		Token token=null;
		while(flag){
			ch=str.charAt(index);
			token=getToken();
			if(token==null&&state==0){
				token=new Token(Token.UNKNOWN,str.substring(head, index+1));
			}
			if(token!=null){
				state=0;
				if(token.name!=Token.UNKNOWN&&token.name!=Token.ERROR)
					list.add(token);
				token=null;
			}
			if(state==0)head=index+1;
			index++;
			if(index>=str.length())break;
		}
		System.out.println("list.size-"+list.size());
		return list;
	}
	public static void showTokens(List<Token> tokens){
		for(Token t: tokens){
			System.out.println("<"+t.name+","+t.value+">");
		}
	}
	public static void main(String[] args) throws FileNotFoundException{
		List<Token> list=getTokenList();
		for(Token t:list){
			System.out.println("<"+t.name+","+t.value+">");
		}
	}
}
