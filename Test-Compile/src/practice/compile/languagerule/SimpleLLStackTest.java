package practice.compile.languagerule;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Stack;

public class SimpleLLStackTest {
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
	}

}
