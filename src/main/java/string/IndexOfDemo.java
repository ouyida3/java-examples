package string;

public class IndexOfDemo {
	
	public static void main(String[] args) throws Exception {
		String s = "abc${yyyyMMdd}.def";
		System.out.println(s.indexOf("${"));// 3
		System.out.println(s.indexOf("}"));// 13
		System.out.println(s.substring(3, 13));// ${yyyyMMdd
	}
	
}
