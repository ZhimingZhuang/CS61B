public class Palindrome {
	
	public static Deque<Character> wordToDeque(String word) {
		LinkedListDeque<Character> linkedDeque = new LinkedListDeque<Character>();
		for(int i = 0; i < word.length(); i++) {
			linkedDeque.addLast(word.charAt(i));
		}
		return linkedDeque;
	}

	public static boolean isPalindrome(String word) {
		int l = 0;
		int r = word.length() - 1;
		while(l < r) {
			if(word.charAt(l) != word.charAt(r))
				return false;
			l++;
			r--;
		}
		return true;
	}

	public static boolean isPalindrome(String word, CharacterComparator cc) {
		int l = 0;
		int r = word.length() - 1;
		while(l < r) {
			if(!cc.equalChars(word.charAt(l), word.charAt(r)))
				return false;
			l++;
			r--;
		}
		return true;
	}
}