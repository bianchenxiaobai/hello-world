package practice.compile.languagerule;

import org.junit.Test;

public class Token extends Sign{
	public String name;
	public Object value;
	public Token(String name,Object value){
		this.name=name;
		this.value=value;
	}
	// ACTION符号--终结符号
	public static final String ID = "id";//asfqew21,r21r
	public static final String ADD = "+";
	public static final String SUB = "-";
	public static final String MUL = "*";
	public static final String DIV = "/";
	public static final String EOF = "$";
	//没有用上
	public static final String LKUO = "(";
	public static final String RKUO = ")";
	//
	public static final String LLKUO = "{";
	public static final String RLKUO = "}";
	public static final String LMKUO = "[";
	public static final String RMKUO = "]";
	//public static final String RELOP = "relop";//< <= > >=比较与运算符
	public static final String LT="<";
	public static final String LE="<=";
	public static final String GT=">";
	public static final String GE=">=";
	public static final String ASSIGN= "=";//=
	public static final String ROP="!";//!;
	public static final String EOP="==";//==
	public static final String NEOP="!=";//!=
	public static final String AND="&&";//&&
	public static final String OR="||";//||
	public static final String TRUE="true";//TRUE
	public static final String FALSE="false";//FALSE
	public static final String BASIC="basic";//int,float
	public static final String KEY="key";//其他关键字
	public static final String IF="if";//if
	public static final String ELSE="else";//else
	public static final String DO="do";
	public static final String WHILE="while";//while
	public static final String BREAK="break";//break
	
	//数字
	public static final String NUM="num";//212
	public static final String REAL="real";//123.123
	public static final String SIGN_DIV=";";//;
	public static final String ERROR="error";//error
	public static final String UNKNOWN="unknown";//unknown
	
	public static final String tokenArray[]={
			ID,ADD,SUB,MUL,DIV,EOF,LKUO,RKUO,LLKUO,RLKUO,LMKUO,RMKUO,LT,LE,GT,GE,ASSIGN,
			ROP,EOP,NEOP,AND,OR,TRUE,FALSE,BASIC,KEY,IF,DO,WHILE,BREAK,NUM,REAL,SIGN_DIV
	};
	//@Test
	public void showTable(){
		System.out.println(tokenArray.length);
		for(int i=0;i<tokenArray.length;i++){
			System.out.println(tokenArray[i]);
		}
	}
	static{
		for(int i=0;i<tokenArray.length;i++){
			tokenList.add(tokenArray[i]);
		}
	}
}
