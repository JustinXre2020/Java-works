import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;


interface Command{
    String[] execute(String[] data);
}


class CmdTool{
    public static void main(String[] args) {
        String[] str = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            str[i] = args[i].toLowerCase();
        }
        System.out.println(Arrays.toString(str));
        if (str[0].equals("-l") || str[0].equals("-list")) {
            List<String> arr = new ArrayList<String>();
            List<String> arr_str = new ArrayList<String>();
            List<String> arr_spe_num = new ArrayList<String>();
            for (int i = 1; i < str.length; i++) {
                if (isNumeric(str[i])) {
                    if (str[i - 1].equals("greater") || str[i - 1].equals("lesser") || str[i - 1].equals("equal")) {
                        arr_spe_num.add(str[i]);
                    } else {
                        arr.add(str[i]);
                    }
                } else {
                    arr_str.add(str[i]);
                }
            }
            for (int i = 0; i < arr_str.size(); i++) { 
                boolean print = false;   // Whether print or not
                if (i == arr_str.size() - 1) {
                    print = true;
                }
                if (arr_str.get(i).equals("greater") || arr_str.get(i).equals("lesser") || arr_str.get(i).equals("equal")) {
                    runClass1(arr_str.get(i), arr_spe_num, arr, print);  
                } else {
                    runClass2(arr_str.get(i), arr, print);  
                }
            }   
        } else {
            if (str[0].equals("greater") || str[0].equals("lesser") || str[0].equals("equal")) {
                ArrayList<String> arr = new ArrayList<>();
                double index = Double.parseDouble(str[1]);
                for (int i = 2; i < str.length; i++) {
                    if (isNumeric(str[i])) {
                        arr.add(str[i]);
                    } 
                }
                if (str[0].equals("greater")) {
                    Greater g = new Greater(index, arr.toArray(new String[arr.size()]));
                    String[] ar = g.execute();
                    printArray(ar);
                } else if (str[0].equals("lesser")) {
                    Lesser g = new Lesser(index, arr.toArray(new String[arr.size()]));
                    String[] ar = g.execute();
                    printArray(ar);
                } else if (str[0].equals("equal")) {
                    Equal g = new Equal(index, arr.toArray(new String[arr.size()]));
                    String[] ar = g.execute();
                    printArray(ar);
                }
            } else {
                ArrayList<String> arr = new ArrayList<>();
                for (int i = 1; i < str.length; i++) {
                    if (isNumeric(str[i])) {
                        arr.add(str[i]);
                    } 
                }
                if (str[0].equals("sum")) {
                    printArray(Sum.execute(arr.toArray(new String[arr.size()])));
                } else if (str[0].equals("product")) {
                    printArray(Product.execute(arr.toArray(new String[arr.size()])));
                } else if (str[0].equals("mean")) {
                    printArray(Mean.execute(arr.toArray(new String[arr.size()])));
                } else if (str[0].equals("max")) {
                    printArray(Max.execute(arr.toArray(new String[arr.size()])));
                } else if (str[0].equals("min")) {
                    printArray(Min.execute(arr.toArray(new String[arr.size()])));
                } else if (str[0].equals("positive")) {
                    printArray(Positive.execute(arr.toArray(new String[arr.size()])));
                } else if (str[0].equals("negative")) {
                    printArray(Negative.execute(arr.toArray(new String[arr.size()])));
                } else if (str[0].equals("count")) {
                    printArray(Count.execute(arr.toArray(new String[arr.size()])));
                } 
            }
        }
    }


    // Check whether it is a number or not
    public static boolean isNumeric(String str) { 
        try {  
          Double.parseDouble(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
    }


    // Print an array of integers
    public static void printArray(String[] array) {
        for (String s : array) {
            if (s == array[array.length - 1]) {
                System.out.print(s);
            } else {
                System.out.print(s + " ");
            } 
        }
    }


    public static void runClass2(String s, List<String> arr, boolean shouldprint) {
        if (s.equals("sum")) {
            String[] i = Sum.execute(arr.toArray(new String[arr.size()]));
            arr.clear();
            Collections.addAll(arr, i);
            if (shouldprint) { 
                printArray(i);
            }
        } else if (s.equals("product")) {
            String[] i = Product.execute(arr.toArray(new String[arr.size()]));
            arr.clear();
            Collections.addAll(arr, i);
            if (shouldprint) { 
                printArray(i); 
            }
        } else if (s.equals("mean")) {
            String[] i = Mean.execute(arr.toArray(new String[arr.size()]));
            arr.clear();
            Collections.addAll(arr, i);
            if (shouldprint) { 
                printArray(i);
            }
        } else if (s.equals("max")) {
            String[] i = Max.execute(arr.toArray(new String[arr.size()]));
            arr.clear();
            Collections.addAll(arr, i);
            if (shouldprint) { 
                printArray(i);
            }
        } else if (s.equals("min")) {
            String[] i = Min.execute(arr.toArray(new String[arr.size()]));
            arr.clear();
            Collections.addAll(arr, i);
            if (shouldprint) { 
                printArray(i);
            }
        } else if (s.equals("positive")) {
            String[] i = Positive.execute(arr.toArray(new String[arr.size()]));
            arr.clear();
            Collections.addAll(arr, i);
            if (shouldprint) { 
                printArray(i);
            }
        } else if (s.equals("negative")) {
            String[] i = Negative.execute(arr.toArray(new String[arr.size()]));
            arr.clear();
            Collections.addAll(arr, i);
            if (shouldprint) { 
                printArray(i);
            }
        } else if (s.equals("count")) {
            String[] i = Count.execute(arr.toArray(new String[arr.size()]));
            arr.clear();
            Collections.addAll(arr, i);
            if (shouldprint) { 
                printArray(i);
            }
        }    
    }


    public static void runClass1(String s, List<String> arr_spe_num, List<String> arr, boolean shouldprint) {
        Double delimiter = Double.parseDouble(arr_spe_num.get(0));
        if (s.equals("greater")) {
            Greater g = new Greater(delimiter, arr.toArray(new String[arr.size()]));
            String[] ar = g.execute();
            arr.clear();
            Collections.addAll(arr, ar);
            arr_spe_num.remove(0);
            if (shouldprint) { 
                printArray(ar);
            }
        } else if (s.equals("lesser")) {
            Lesser g = new Lesser(delimiter, arr.toArray(new String[arr.size()]));
            String[] ar = g.execute();
            arr.clear();
            Collections.addAll(arr, ar);
            if (shouldprint) { 
                printArray(ar);
            }
        } else if (s.equals("equal")) {
            Equal g = new Equal(delimiter, arr.toArray(new String[arr.size()]));
            String[] ar = g.execute();
            arr.clear();
            Collections.addAll(arr, ar);
            if (shouldprint) { 
                printArray(ar);
            }
        }
    }
}


class Sum {
    public static String[] execute(String[] nums) {
        if (nums.length == 0) {
            String[] result = {"0"};
            return result;
        }
        String[] result = new String[1];
        double d = 0.0;
        for (String num : nums) {
            d += Double.parseDouble(num);
        }
        if (d % 1 == 0) {
            int i = (int) d;
            result[0] = Integer.toString(i);
        } else {
            result[0] = Double.toString(d);
        }
        return result;
    }
}

class Product {
    public static String[] execute(String[] nums) {
        if (nums.length == 0) {
            String[] result = {"1"};
            return result;
        }
        String[] result = new String[1];
        double d = 0.0;
        for (String num : nums) {
            d *= Double.parseDouble(num);
        }
        if (d % 1 == 0) {
            int i = (int) d;
            result[0] = Integer.toString(i);
        } else {
            result[0] = Double.toString(d);
        }
        return result;
    }
}

class Mean {
    public static String[] execute(String[] nums) {
        if (nums.length == 0) {
            String[] result = {"0"};
            return result;
        }
        String[] result = new String[1];
        double d = 0.0;
        for (String num : nums) {
            d += Double.parseDouble(num);
        }
        result[0] = Double.toString(d / (double) nums.length);
        return result;
    }
}

class Max {
    public static String[] execute(String[] nums) {
        String[] result = new String[1];
        if (nums.length == 0) {
            String[] empty = {};
            return empty;
        }
        double max = Double.parseDouble(nums[0]);
        for (String num : nums) {
            double d = Double.parseDouble(num);
            if (d > max) {
                max = d;
            }
        }
        if (max % 1 == 0) {
            int i = (int) max;
            result[0] = Integer.toString(i);
        } else {
            result[0] = Double.toString(max);
        }
        return result;
    }
}

class Min {
    public static String[] execute(String[] nums) {
        String[] result = new String[1];
        if (nums.length == 0) {
            String[] empty = {};
            return empty;
        }
        double max = Double.parseDouble(nums[0]);
        for (String num : nums) {
            double d = Double.parseDouble(num);
            if (d < max) {
                max = d;
            }
        }
        if (max % 1 == 0) {
            int i = (int) max;
            result[0] = Integer.toString(i);
        } else {
            result[0] = Double.toString(max);
        }
        return result;
    }
}

class Positive {
    public static String[] execute(String[] nums) {
        List<String> result = new ArrayList<String>();
        for (String num : nums) {
            Double d = Double.parseDouble(num);
            if (d > 0.0) {
                if (d % 1 == 0) {
                    int i = Integer.parseInt(num);
                    result.add(Integer.toString(i));
                } else {
                    result.add(Double.toString(d));
                }
            }
        }
        return result.toArray(new String[result.size()]);
    }
}

class Negative {
    public static String[] execute(String[] nums) {
        List<String> result = new ArrayList<String>();
        for (String num : nums) {
            Double d = Double.parseDouble(num);
            if (d < 0.0) {
                if (d % 1 == 0) {
                    int i = Integer.parseInt(num);
                    result.add(Integer.toString(i));
                } else {
                    result.add(Double.toString(d));
                }
            }
        }
        return result.toArray(new String[result.size()]);
    }
}

class Count {
    public static String[] execute(String[] nums) {
        String[] len = {Integer.toString(nums.length)};
        return len;
    }
}

class Greater {
    private double min;
    private String[] nums;

    Greater(double num, String[] nums) {
        this.min = num;
        this.nums = nums;
    }

    public String[] execute() {
        List<String> result = new ArrayList<String>();
        for (String num : nums) {
            Double d = Double.parseDouble(num);
            if (d > this.min) {
                if (d % 1 == 0) {
                    int i = Integer.parseInt(num);
                    result.add(Integer.toString(i));
                } else {
                    result.add(Double.toString(d));
                }
            }
        }
        return result.toArray(new String[result.size()]);
    }
}

class Lesser {
    private double max;
    private String[] nums;

    Lesser(double num, String[] nums) {
        this.max = num;
        this.nums = nums;
    }

    public String[] execute() {
        List<String> result = new ArrayList<String>();
        for (String num : nums) {
            Double d = Double.parseDouble(num);
            if (d < this.max) {
                if (d % 1 == 0) {
                    int i = Integer.parseInt(num);
                    result.add(Integer.toString(i));
                } else {
                    result.add(Double.toString(d));
                }
            }
        }
        return result.toArray(new String[result.size()]);
    }
}

class Equal {
    private double val;
    private String[] nums;

    Equal(double num, String[] nums) {
        this.val = num;
        this.nums = nums;
    }

    public String[] execute() {
        List<String> result = new ArrayList<String>();
        for (String num : nums) {
            Double d = Double.parseDouble(num);
            if (d == this.val) {
                if (d % 1 == 0) {
                    int i = Integer.parseInt(num);
                    result.add(Integer.toString(i));
                } else {
                    result.add(Double.toString(d));
                }
            }
        }
        return result.toArray(new String[result.size()]);
    }
}



