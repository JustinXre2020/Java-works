import tester.*;
import java.util.Collections;
import java.util.ArrayList;

class Pair {
  public int a;
  public int b;
  Pair(int i, int j) {
      a = i;
      b = j;
  }
  public static Pair maxmin(int[] arr) {
      int min = Integer.MAX_VALUE;
      int max = Integer.MIN_VALUE;

      for (int i : arr) {
          if (i < min) {
              min = i;
          }
          if (i > max) {
              max = i;
          }
      }
      return new Pair(min, max);
  }
}

class ArrayExamples{
  public static String joinWith(String[] str, String s) {
		String result = "";
		for (int i = 0; i < str.length; i++) {
			if (i == str.length - 1) {
				result += str[i];
			} else {
				result += str[i] + s;
			}
		}
		return result;
	}

	public static boolean somethingFalse(boolean[] bool) {
		Boolean result = false;
		for (Boolean temp : bool) {
			if (temp == false) {
				return true;
			}
		}
		return result;
	}

	public static int countWithinRange(double[] d, double low, double high) {
		int count = 0;
		for (double temp : d) {
			if (temp <= high && temp >= low) {
				count++;
			}
		}
		return count;
	}

	public static double[] numsWithinRange(double[] d, double low, double high) {
		int size = countWithinRange(d, low, high);
		double[] result;
		result = new double[size];
		int count = 0;
		for (double temp : d) {
			if (temp <= high && temp >= low) {
				result[count] = temp;
				count++;
			}
		}
		return result;
	}

  public static String earliest(String[] str) {
    ArrayList<String> arr = new ArrayList<>();
    for (String s : str) {
        arr.add(s);
    }
    Collections.sort(arr);
    return arr.get(0);
  } 
}

class ProvidedArrayExamples {
  void testJoinWith(Tester t){
    String[] example1 = {"a", "b","c"};
    t.checkExpect(ArrayExamples.joinWith(example1, ":"), "a:b:c");
  }

  void testSomethingFalse(Tester t){
    boolean[] example1 = {true, false};
    t.checkExpect(ArrayExamples.somethingFalse(example1), true);
  }

  void testCountWithinRange(Tester t){
    double[] example = {0.1, 1.3, 2.6};
    t.checkExpect(ArrayExamples.countWithinRange(example, 1.1, 2.2), 1);
  }

  void testNumsWithinRange(Tester t){
    double[] example = {0.0, 3.0, 1.4, 1.5, 2.7, 9.1, 2.1};
    double[] expected = {1.4, 1.5, 2.1};
    t.checkExpect(ArrayExamples.numsWithinRange(example, 1.1, 2.2), expected);
  }

  void testMaxmin(Tester t){
    int[] example = {4, 5, 2, 3, 1};
    t.checkExpect(Pair.maxmin(example), new Pair(1, 5));
  }

  void testEarliest(Tester t){
    String[] example = {"aa", "aab", "abcd", "a"};
    t.checkExpect(ArrayExamples.earliest(example), "a");
  }
}