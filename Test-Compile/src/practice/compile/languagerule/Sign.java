package practice.compile.languagerule;

import java.util.ArrayList;
import java.util.List;

public class Sign{
	public static List<String> ntlist=new ArrayList<String>();
	public static List<String> tokenList=new ArrayList<String>();
	public static List<String> allList=null;
	public static List<String> getAllList(){
		if(allList==null){
			List<String> list=new ArrayList<String>();
			list.addAll(ntlist);
			list.addAll(tokenList);
			allList=list;
		}
		return allList;
	}
}
