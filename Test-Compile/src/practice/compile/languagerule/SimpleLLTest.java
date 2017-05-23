package practice.compile.languagerule;

import org.junit.Test;

public class SimpleLLTest {
	//������ʾ
	public static final String errorA="��ǰ���ַ�û��ƥ��Ĳ���ʽ";
	public static final String errorB="��ƥ��ʷ���Ԫ-";
	//һ���ַ�Ϊһ���ʷ���Ԫ����β��$��������ʷ���Ԫ
	//�մ�������#����
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
			//ƥ�����ʽE->TE'
			System.out.println("ƥ�����ʽE->TE'");
			T();
			Eo();
		}else{
			throw new Exception(errorA);
		}
	}
	//E'
	public static void Eo() throws Exception{
		if(lookAheadisToken("+")){
			//ƥ�����ʽE'->+TE'
			System.out.println("ƥ�����ʽE'->+TE'");
			match("+");
			T();
			Eo();
		}else if(lookAheadisToken("#")){
			//ƥ�����ʽE'->�մ�
			System.out.println("ƥ�����ʽE'->�մ�");
			//match("#");
		}
	}
	//T
	public static void T() throws Exception{
		if(lookAheadisToken("(")||lookAheadisToken("num")){
			//ƥ��T->FT;
			System.out.println("ƥ�����ʽT->FT");
			F();
			T();
		}else if(lookAheadisToken("*")){
			//ƥ��T->*FT;
			System.out.println("ƥ�����ʽT->*FT");
			match("*");
			F();
			T();
		}else if(lookAheadisToken("#")){
			//ƥ��T->�մ�
			System.out.println("ƥ�����ʽT->�մ�");
			//match("#");
		}
	
	}
	//F
	public static void F() throws Exception{
		if(lookAheadisToken("(")){
			//ƥ��F->(E)
			System.out.println("ƥ�����ʽF->(E)");
			match("(");
			E();
			match(")");
		}else if(lookAheadisToken("num")){
			//ƥ��F->num;
			System.out.println("ƥ�����ʽF->num");
			match("num");
		}else{
			throw new Exception(errorA);
		}
		
	}
	public static void main(String[] args) throws Exception{
		E();
		System.out.println("û�б���˵��ƥ��ɹ�");
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
