package enums;

public class EnumTest {
	public static void main(String[] args) {
		DatabaseType t = DatabaseType.valueOf("orderdb");
		System.out.println(t);// orderdb
		
		t = DatabaseType.valueOf("notexists");
		// java.lang.IllegalArgumentException: No enum constant enums.DatabaseType.notexists
		System.out.println(t);
	}
}
