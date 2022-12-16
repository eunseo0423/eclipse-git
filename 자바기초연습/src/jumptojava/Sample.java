package jumptojava;

import java.util.Arrays;

public class Sample {

	public static void main(String[] args) {
		int i = 3;
		boolean isOdd = i % 2 == 1;
		System.out.println(isOdd);
		
		String a = "He:llo";
		String[] result = a.split(":");
//		for (String tmp:result) 
//			System.out.println(tmp);
		System.out.println(result);
		
//		char[] arr = new char[result.length-1];
//		
//		for(int j = 0; j < result.length; j++) {
//			arr = result[j].toCharArray();
//		}
//		
//		System.out.println(arr);
		
		System.out.println(Arrays.toString(result));
		
	}
}
