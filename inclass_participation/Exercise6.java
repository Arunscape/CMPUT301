// Arun Woosaree
// Jacob Reckhard

public class Exercise6{

	public static int find(int[] arr, int x){
		return find(arr, 0, arr.length, x);
	}

	private static int find(int[] arr, int lo, int hi, int x){
		int t = (int) (((long) hi) + lo)/2;
		if (hi == lo) {
			return -1;
		} if (arr[t] == x) {
			return t;
		} else if (arr[t] > x) {
			return find(arr, lo, t, x);
		} else {
			return find(arr, t+1, hi, x);
		}

	}

	public static void main( String[] args ){
		int[] t1 = new int[]{1, 2, 3, 4, 5};
		int[] t2 = new int[]{10, 20, 30, 40, 50};
		int[] t3 = new int[]{-5, -4, -3, -2, -1};


		assert(find(t1, 1) == 0);
		assert(find(t1, 2) == 1);
		assert(find(t1, 3) == 2);
		assert(find(t1, 4) == 3);
		assert(find(t1, 5) == 4);

		assert(find(t2, 10) == 0);
		assert(find(t2, 20) == 1);
		assert(find(t2, 30) == 2);
		assert(find(t2, 40) == 3);
		assert(find(t2, 50) == 4);

		assert(find(t3, -5) == 0);
		assert(find(t3, -4) == 1);
		assert(find(t3, -3) == 2);
		assert(find(t3, -2) == 3);
		assert(find(t3, -1) == 4);
		
		assert(find(t3, 100) == -1);
		System.out.println("Success");
	}
}
