package practice.compile.languagerule;


//����һ����������--������͹�Լ�ͽ��ܺͱ����ܹ���������,��һ���ֶ�Ϊ���ͣ��ڶ����ֶ�Ϊ��Ӧ�ĺ����ŵ��±�
public class Action {
	public static final String SHIFT = "SHIFT";
	public static final String REDUCTION = "REDUCTION";
	public static final String ACCEPT = "ACCEPT";
	public static final String ERROR = "ERROR";
	public String type;//ѡȡ���ֶ���
	public int no;//ʵ��Ϊ����ʽ�ı�Ż���״̬
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
