import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

interface Command {
    String[] execute(String[] data);
}


class CmdTool {
    public static void main(String[] args) {
        String[] str = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            str[i] = args[i].toLowerCase();
        }
        if (str[0].equals("-l") || str[0].equals("-list")) {
            List<String> arr = new ArrayList<String>();
            Command[] arr_str = processCmdList(str);
            for (int i = 1; i < str.length; i++) {
                if (isNumeric(str[i])) {
                    if (str[i - 1].equals("greater") || str[i - 1].equals("lesser") || str[i - 1].equals("equal")) {
                        continue;
                    } else {
                        arr.add(str[i]);
                    }
                } 
            }
            String[] numbers = arr.toArray(new String[arr.size()]);
            CmdList commands = new CmdList(arr_str);
            printArray(commands.execute(numbers));
        } else {
            if (str[0].equals("greater") || str[0].equals("lesser") || str[0].equals("equal")) {
                ArrayList<String> arr = new ArrayList<>();
                String index = str[1];
                for (int i = 2; i < str.length; i++) {
                    if (isNumeric(str[i])) {
                        arr.add(str[i]);
                    } 
                }
                if (str[0].equals("greater")) {
                    Greater g = new Greater(index);
                    String[] ar = g.execute(arr.toArray(new String[arr.size()]));
                    printArray(ar);
                } else if (str[0].equals("lesser")) {
                    Lesser g = new Lesser(index);
                    String[] ar = g.execute(arr.toArray(new String[arr.size()]));
                    printArray(ar);
                } else if (str[0].equals("equal")) {
                    Equal g = new Equal(index);
                    String[] ar = g.execute(arr.toArray(new String[arr.size()]));
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
                    Sum obj = new Sum();
                    printArray(obj.execute(arr.toArray(new String[arr.size()])));
                } else if (str[0].equals("product")) {
                    Product obj = new Product();
                    printArray(obj.execute(arr.toArray(new String[arr.size()])));
                } else if (str[0].equals("mean")) {
                    Mean obj = new Mean();
                    printArray(obj.execute(arr.toArray(new String[arr.size()])));
                } else if (str[0].equals("max")) {
                    Max obj = new Max();
                    printArray(obj.execute(arr.toArray(new String[arr.size()])));
                } else if (str[0].equals("min")) {
                    Min obj = new Min();
                    printArray(obj.execute(arr.toArray(new String[arr.size()])));
                } else if (str[0].equals("positive")) {
                    Positive obj = new Positive();
                    printArray(obj.execute(arr.toArray(new String[arr.size()])));
                } else if (str[0].equals("negative")) {
                    Negative obj = new Negative();
                    printArray(obj.execute(arr.toArray(new String[arr.size()])));
                } else if (str[0].equals("count")) {
                    Count obj = new Count();
                    printArray(obj.execute(arr.toArray(new String[arr.size()])));
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

    public static Command[] processCmdList(String[] str) {
        List<Command> result = new ArrayList<Command>();
        for (int i = 0; i < str.length; i++) {
            if (str[i].equals("sum")) {
                result.add(new Sum());
            } else if (str[i].equals("product")) {
                result.add(new Product());
            } else if (str[i].equals("mean")) {
                result.add(new Mean());
            } else if (str[i].equals("max")) {
                result.add(new Max());
            } else if (str[i].equals("min")) {
                result.add(new Min());
            } else if (str[i].equals("positive")) {
                result.add(new Positive());
            } else if (str[i].equals("negative")) {
                result.add(new Negative());
            } else if (str[i].equals("count")) {
                result.add(new Count());
            } else if (str[i].equals("greater")) {
                result.add(new Greater(str[i + 1]));
            } else if (str[i].equals("equal")) {
                result.add(new Equal(str[i + 1]));
            } else if (str[i].equals("lesser")) {
                result.add(new Lesser(str[i + 1]));
            } 
        }
        return result.toArray(new Command[result.size()]); 
    }
 
}


class Sum implements Command {
    @Override
    public String[] execute(String[] nums) {
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
        Arrays.toString(result);
        return result;
    }
}

class Product implements Command {
    @Override
    public String[] execute(String[] nums) {
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

class Mean implements Command {
    @Override
    public String[] execute(String[] nums) {
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

class Max implements Command {
    @Override
    public String[] execute(String[] nums) {
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

class Min implements Command {
    @Override
    public String[] execute(String[] nums) {
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

class Positive implements Command {
    @Override
    public String[] execute(String[] nums) {
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

class Negative implements Command {
    @Override
    public String[] execute(String[] nums) {
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

class Count implements Command {
    @Override
    public String[] execute(String[] nums) {
        String[] len = {Integer.toString(nums.length)};
        return len;
    }
}

class Greater implements Command {
    private double min;

    Greater(String num) {
        double d = Double.parseDouble(num);
        this.min = d;
    }
    @Override
    public String[] execute(String[] nums) {
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

class Lesser implements Command {
    private double max;

    Lesser(String num) {
        double d = Double.parseDouble(num);
        this.max = d;
    }
    @Override
    public String[] execute(String[] nums) {
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

class Equal implements Command {
    private double val;

    Equal(String num) {
        double d = Double.parseDouble(num);
        this.val = d;
    }
    @Override
    public String[] execute(String[] nums) {
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

  
class CmdList implements Command {
    private Command[] commands_;

    CmdList(Command[] commands) {
        commands_ = commands;
    } 

    @Override
    public String[] execute(String[] arr) {
        for (int i = 0; i < commands_.length; i++) { 
            if (commands_[i] instanceof Greater || commands_[i] instanceof Lesser || commands_[i] instanceof Equal) {
                if (commands_[i] instanceof Greater) {
                    Greater obj = (Greater) commands_[i];
                    arr = Arrays.copyOf(obj.execute(arr), obj.execute(arr).length);
                } else if (commands_[i] instanceof Lesser) {
                    Lesser obj = (Lesser) commands_[i];
                    arr = Arrays.copyOf(obj.execute(arr), obj.execute(arr).length);
                } else if (commands_[i] instanceof Equal) {
                    Equal obj = (Equal) commands_[i];
                    arr = Arrays.copyOf(obj.execute(arr), obj.execute(arr).length);
                }
            } else {
                if (commands_[i] instanceof Sum) {
                    Sum obj = (Sum) commands_[i];
                    arr = Arrays.copyOf(obj.execute(arr), obj.execute(arr).length);
                } else if (commands_[i] instanceof Product) {
                    Product obj = (Product) commands_[i];
                    arr = Arrays.copyOf(obj.execute(arr), obj.execute(arr).length);
                } else if (commands_[i] instanceof Mean) {
                    Mean obj = (Mean) commands_[i];
                    arr = Arrays.copyOf(obj.execute(arr), obj.execute(arr).length);
                } else if (commands_[i] instanceof Max) {
                    Max obj = (Max) commands_[i];
                    arr = Arrays.copyOf(obj.execute(arr), obj.execute(arr).length);
                } else if (commands_[i] instanceof Min) {
                    Min obj = (Min) commands_[i];
                    arr = Arrays.copyOf(obj.execute(arr), obj.execute(arr).length);
                } else if (commands_[i] instanceof Positive) {
                    Positive obj = (Positive) commands_[i];
                    arr = Arrays.copyOf(obj.execute(arr), obj.execute(arr).length);
                } else if (commands_[i] instanceof Negative) {
                    Negative obj = (Negative) commands_[i];
                    arr = Arrays.copyOf(obj.execute(arr), obj.execute(arr).length);
                } else if (commands_[i] instanceof Count) {
                    Count obj = (Count) commands_[i];
                    arr = Arrays.copyOf(obj.execute(arr), obj.execute(arr).length);
                }   
            }
        }  
        return arr;
    }
}



