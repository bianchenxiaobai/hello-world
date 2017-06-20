package practice.compile.languagerule;

import org.junit.Test;

public class SimpleLLTest {
	//错误提示
	public static final String errorA="向前看字符没有匹配的产生式";
	public static final String errorB="不匹配词法单元-";
	//一个字符为一个词法单元，结尾用$代表结束词法单元
	//空串符号用#代表
	public static String str="5+6*2$";
	public static int i=0;
	public static void match(String token) throws Exception{
		if(lookAheadisToken(token))
			i++;
		else{
			throw new Exception(errorB+token);
		}
	}
	//E
	public static void E() throws Exception{
		if(lookAheadisToken("*")||lookAheadisToken("(")||lookAheadisToken("num")){
			//匹配产生式E->TE'
			System.out.println("匹配产生式E->TE'");
			T();
			Eo();
		}else{
			throw new Exception(errorA);
		}
	}
	//E'
	public static void Eo() throws Exception{
		if(lookAheadisToken("+")){
			//匹配产生式E'->+TE'
			System.out.println("匹配产生式E'->+TE'");
			match("+");
			T();
			Eo();
		}else if(lookAheadisToken("#")){
			//匹配产生式E'->空串
			System.out.println("匹配产生式E'->空串");
			//match("#");
		}
	}
	//T
	public static void T() throws Exception{
		if(lookAheadisToken("(")||lookAheadisToken("num")){
			//匹配T->FT;
			System.out.println("匹配产生式T->FT");
			F();
			T();
		}else if(lookAheadisToken("*")){
			//匹配T->*FT;
			System.out.println("匹配产生式T->*FT");
			match("*");
			F();
			T();
		}else if(lookAheadisToken("#")){
			//匹配T->空串
			System.out.println("匹配产生式T->空串");
			//match("#");
		}
	
	}
	//F
	public static void F() throws Exception{
		if(lookAheadisToken("(")){
			//匹配F->(E)
			System.out.println("匹配产生式F->(E)");
			match("(");
			E();
			match(")");
		}else if(lookAheadisToken("num")){
			//匹配F->num;
			System.out.println("匹配产生式F->num");
			match("num");
		}else{
			throw new Exception(errorA);
		}
		
	}
	public static void main(String[] args) throws Exception{
		E();
		System.out.println("没有报错说明匹配成功");
	}
	public static boolean lookAheadisToken(String name){
		return isToken(getLookAhead(),name);
	}
	public static char getLookAhead(){
		return str.charAt(i);
	}
	public static boolean isToken(char ch,String name){
		if(name.equals("*")&&ch=='*'){
			return true;
		}else if(name.equals("/")&&ch=='/'){
			return true;
		}else if(name.equals("+")&&ch=='+'){
			return true;
		}else if(name.equals("-")&&ch=='-'){
			return true;
		}else if(name.equals("(")&&ch=='('){
			return true;
		}else if(name.equals(")")&&ch==')'){
			return true;
		}else if(name.equals("$")&&ch=='$'){
			return true;
		}else if(name.equals("num")&&Character.isDigit(ch)){
			return true;
		}else if(name.equals("#")){
			return true;
		}
		return false;
	}
	
	@Test
	public void Test(){
		System.out.println("- is +="+isToken('-',"+"));
		System.out.println("+ is -="+isToken('+',"-"));
		System.out.println("/ is *="+isToken('/',"*"));
		System.out.println("* is /="+isToken('*',"/"));
		System.out.println("8 is num="+isToken('8',"num"));
	}
}
