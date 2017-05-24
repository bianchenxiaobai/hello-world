package practice.compile.languagerule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KeyTable {
	public static String keys[]={
			"auto","break","case","char","const","continue","default","do",
			"double","else","enum","extern","float","for","goto","if",
			"int","long","register","return","short","signed","sizeof","static",
			"struct","switch","typedef","union","unsigned","void","volatile","while"
	};
	//�洢�÷��ŵ��±꣬û��Ϊ��
	private static Map<String,String> map=new HashMap<String,String>();
	static{
		for(int i=0;i<keys.length;i++)
			map.put(keys[i], Token.KEY);
		//�������һ��
		map.put("if", Token.IF);
		map.put("else", Token.ELSE);
		map.put("do", Token.DO);
		map.put("while", Token.WHILE);
		map.put("break", Token.BREAK);
		map.put("true", Token.TRUE);
		map.put("false", Token.FALSE);
		map.put("char", Token.BASIC);
		map.put("long", Token.BASIC);
		map.put("int", Token.BASIC);
		map.put("float", Token.BASIC);
		map.put("double", Token.BASIC);
	}
	//�������map�У�˵���ǹؼ���
	public static String getTokenName(String word){
		String name=map.get(word);
		if(name!=null){
			return name;
		}else{
			return Token.ID;
		}
	}
}
