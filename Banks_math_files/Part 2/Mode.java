class Mode {
    // Task 1: mode method
    // Your code here
    public static void main(String[] args) {
        int[] i1 = new int[]{1, 3, 3, 3, 5, 7, 1};
        Mode m1 = new Mode();
        System.out.println("The mode of int array is : " + m1.mode(i1));

        int[] i2 = new int[]{12, 45, 12, 45, 45, 67, 12, 67};
        Mode m2 = new Mode();
        System.out.println("The mode of int array is : " + m2.mode(i2));

        int[] i3 = new int[]{18, 26, 3, 3, 77, 64, 25, 25};
        Mode m3 = new Mode();
        System.out.println("The mode of int array is : " + m3.mode(i3));
    }


    public int mode(int[] arr) {
        if (arr.length == 0) {
            return 0;
        } 
        int maxValue = 0; 
        int maxCount = 0;
        System.out.println("value start   value end");
        for (int i = 0; i < arr.length; ++i) {   
            int count = 0;
            for (int j = 0; j < arr.length; ++j) {
                if (arr[j] == arr[i]) {
                    count++;
                }
            }
            int previousMax = maxValue;
            if (count > maxCount) {
                maxCount = count;
                maxValue = arr[i];
            }
            System.out.println(previousMax + "             " + maxValue);
        }
        return maxValue;
    }
}