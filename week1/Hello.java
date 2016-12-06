import java.util.Scanner;
class Hello {
	public static void main(String args[]){
		System.out.println("Enter a number: ");
		Scanner sc = new Scanner(System.in);
        //int i = sc.nextInt();
        //int j = sc.nextInt();

        //System.out.println("Odd:" + isNumberOdd(i));
        //System.out.println("Prime:" + isPrime(i));
        //System.out.println(i + "th Fibonacci number: " + fibonacci(i));
        //System.out.println("Palindrome:" + isPalindrome(i));
        //System.out.println("Sum of palindrome is palindrome: " + isSumPalindrome(i) );
        //System.out.println(reverseOddWords("This is the first lecture for Programming 101 with Java"));
        //System.out.println("Sum Of Divisors betw the numbers:" + sumOfDivisors(i,j));

		for(String s : analyze("It is 18th of Oct 2016")){
			System.out.println(s);
		}
	}

	public static boolean isNumberOdd(int number) {
		return number%2 != 0;
	}

	public static boolean isPrime(int number) {
		for(int i=2; i<=number/2;i++){
			if(number%i == 0){
				return false;
			}
		}
		return true;	
	}

	public static int fibonacci(int number) {
		int result = 0;

		for(int i=1; i<=number; i++){
			int f = fib(i);
			int len = intLen(f);
			while(len>0){
				result *= 10;
				len--;
			}
			result +=  f;
		}

		return result;
	}

	private static int fact(int number) {
		if(number == 1){
			return 1;
		}
		return number*fact(number-1);
	}

	private static int intLen(int number) {
		int i = 0;
		while(number > 0){
			i++;
			number /= 10;
		}

		return i;
	}

	public static int factSum(int number) {
		int sum = 0;
		while(number>0){
			sum += fact(number%10);
			number /= 10;
		}

		return sum;
	}

	public static int fib(int number){
		if(number <= 2){
			return 1;
		}
		return fib(number - 1) + fib(number - 2);
	}

	public static boolean isPalindrome(int number) {
		String num_str = Integer.toString(number);
		for(int i=0; i <= num_str.length()/2; i++)
			if(num_str.charAt(i) != num_str.charAt(num_str.length() - i - 1))
				return false;
			return true;	
		}

		public static String reverseOddWords(String sentence) {
			String[] words = sentence.split(" ");
			String result = "";

			for(int i=0; i<words.length; i++){
				result += isNumberOdd(i) ? reverse(words[i]) : words[i];
				result += " ";
			}

			return result;
		}

		private static String reverse(String src) {
			String result = "";
			for(int i=src.length()-1; i>=0; i--){
				result += src.charAt(i);
			}

			return result;
		}

		public static boolean isSumPalindrome(int number){
			int n_len = intLen(number);

			while(n_len >0){
				number *= 10;
				n_len--;
			}

			return isPalindrome(number + getPalindrome(number));
		}

		private static int getPalindrome(int number){
			int result = 0;
			while(number >0){
				result = result*10 + number%10;
				number /= 10;
			}

			return result;
		}

		private static int divisorSum(int number){
			int result = 0;
			if(isPrime(number)){
				return number+1;
			}

			for(int i=2;i<=number/2;i++){
				if(number%i == 0){
					result += i;
				}
			}

			return result + number + 1;

		}

		public static int sumOfDivisors(int A, int B){
			int result = 0;
			for(int i=A; i<=B;i++){
				result += divisorSum(i);
			}

			return result;
		}

		private static boolean isVowel(char a){
			return a == 'a' || a=='i' || a == 'o' || a == 'u' || a == 'e' || a == 'i';
		}

		private static boolean isDigit(char a){
			return a >= '0' && a <= '9';
		}

		public static String[] analyze(String sentence) {
			int vow_count = 0, cons_count = 0, dig_count = 0;
			sentence = sentence.toLowerCase();
			for(int i=0; i<sentence.length(); i++){

				char c = sentence.charAt(i);

				if(c == ' ') {
					continue;
				}

				if(isVowel(c)){
					vow_count++;
				} else if(isDigit(c)){
					dig_count++;
				} else {
					cons_count++;
				}

			}

			return new String[]{
				"Vowels: " + vow_count,
				"Consonants: " + cons_count,
				"Digits: " + dig_count
			};
		}

	}