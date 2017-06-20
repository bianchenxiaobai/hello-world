package practice.compile.languagerule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class NTSign extends Sign{
	// GOTO符号--非终结符号
	public static final String ST="ST";
	public static final String PG="PG";
	public static final String BK="BK";
	public static final String DS="DS";
	public static final String D="D";
	public static final String T="T";
	public static final String SS="SS";
	public static final String S="S";
	public static final String L="L";
	public static final String B="B";
	public static final String J="J";
	public static final String E="E";
	public static final String R="R";
	public static final String ER="ER";
	public static final String TE="TE";
	public static final String U="U";
	public static final String F="F";
	public static String ntArray[]={
			ST,PG,BK,DS,D,T,SS,S,L,B,J,E,R,ER,TE,U,F
	};
	static{
		//加入到ntlist中
		for(int i=0;i<ntArray.length;i++){
			ntlist.add(ntArray[i]);
		}
	}
	@Test
	public void testNumber(){
		System.out.println(ntArray.length);
		for(int i=0;i<ntArray.length;i++){
			System.out.println(ntArray[i]);
		}
	}

}
