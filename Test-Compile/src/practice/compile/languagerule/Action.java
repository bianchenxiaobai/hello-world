package practice.compile.languagerule;


//代表一个动作的类--有移入和归约和接受和报错总共四种类型,第一个字段为类型，第二个字段为对应的函数号的下标
public class Action {
	public static final String SHIFT = "SHIFT";
	public static final String REDUCTION = "REDUCTION";
	public static final String ACCEPT = "ACCEPT";
	public static final String ERROR = "ERROR";
	public String type;//选取那种动作
	public int no;//实际为产生式的编号或者状态
	public Action(String type){
		this.type=type;
	}
	public Action(String type,int no){
		this.type=type;
		this.no=no;
	}
	public static Action getAcceptAction(){
		return new Action(ACCEPT);
	}
	public static Action getErrorAction(){
		return new Action(ERROR);
	}
	public static Action getAction(String type,int no){
		return new Action(type,no);
	}
}
