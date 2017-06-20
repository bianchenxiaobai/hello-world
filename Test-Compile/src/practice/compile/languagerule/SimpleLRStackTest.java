package practice.compile.languagerule;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Stack;

public class SimpleLRStackTest {
	// ״̬ջ������ջ
	private static Stack<Integer> stStack = new Stack<Integer>();
	private static Stack<String> signStack = new Stack<String>();
	//�ʷ���Ԫ��
	private static List<Token> tokens;
	public static int i = 0;
	public static void main(String[] args) throws FileNotFoundException {
		tokens=TokenKnown.getTokenList();
		tokens.add(new Token(Token.EOF,"$"));
		TokenKnown.showTokens(tokens);
		//��ʼ��״̬Ϊ0
		stStack.push(0);
		//��ʼ����ȡָ��Ϊ��ʼ
		int k=0;
		System.out.printf("%20s %20s %40s\n","status","sign","action");
		
		while(true){
			Token token=tokens.get(k);
			Action action=StatusTable.getAction(stStack.peek(),token.name);
			//�����ǰ״̬
			StringBuilder sb=null;
			sb=new StringBuilder();
			for(int i=0;i<stStack.size();i++){
				sb.append(""+stStack.get(i));
			}
			System.out.printf("%20s ",sb.toString());
			sb=new StringBuilder();
			//�����ǰ����ջ
			for(int i=0;i<signStack.size();i++){
				sb.append(""+signStack.get(i));
			}
			System.out.printf("%20s ", sb.toString());
			if(action.type==Action.SHIFT){
				//�ƶ���״̬iͬʱ����
				stStack.push(action.no);
				signStack.push(token.name);
				System.out.printf("%40s ","����״̬"+action.no);
				k++;
			}else if(action.type==Action.REDUCTION){
				//ʹ�õ�i������ʽ���й�Լ
				List<String> list=CreateTable.getRightList(action.no);
				//�����ұ߲��֣�������߲���,ͬ������״̬
				for(int i=0;i<list.size();i++){
					signStack.pop();
					stStack.pop();
				}
				//����ʹ���󲿸���״̬
				signStack.push(CreateTable.getLeftNTSign(action.no));
				Action gt=StatusTable.getAction(stStack.peek(), CreateTable.getLeftNTSign(action.no));
				stStack.push(gt.no);
				//���³ɹ�
				System.out.printf("%40s ","��Լ����ʽΪ"+CreateTable.getAllList(action.no));
			}else if(action.type==Action.ACCEPT){
				System.out.println();
				System.out.println("�����ַ���������");
				break;
			}else{
				System.out.println();
				System.out.println("st=="+stStack.peek());
				System.out.println("token.name="+token.name);
				System.out.println(StatusTable.getAction(stStack.peek(), token.name).type);
				System.out.println(StatusTable.getAction(stStack.peek(), token.name).no);
				System.out.println("������һ������");
				break;
			}
			System.out.println();
		}
	
	}
}
