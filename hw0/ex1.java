public class ex1 {
	public static void main (String[] args){
		int n = Integer.parseInt(args[0]);
		if (n < 0) return;
		drawTriangle(n);
	}

	public static void drawTriangle(int n) {
		for (int i = 0; i < n; i++) {
			String s = new String();
			for(int j = 0; j <= i; j++) {
				s = s + "*";
			}
			System.out.println(s);
		}
	}
}