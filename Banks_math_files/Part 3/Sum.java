import tester.*;
import java.util.Arrays;
import java.util.List;

interface Summable<T> {
    int getVal(T t);
}

class Sum {
    public <T> int sum(List<T> l, Summable<T> s) {
        int result = 0;
        if (l.size() == 0) {
            return 0;
        }
        for (T temp : l) {
            if (temp == null) {
                return 0;
            }
            result += s.getVal(temp);
        }
        return result;
    }
}

class Summa<T> implements Summable<T> {
    @Override
    public int getVal(T t) {
        return (int) t;  
    } 
}

class Examples{
    void test(Tester t) {
      List<Integer> is = Arrays.asList(5, 6, 4);
      Summa<Integer> ss = new Summa<Integer>();
      Sum s = new Sum();
      System.out.println("The final result is: " + s.sum(is, ss));
    }
}

/*
class          Method        this reference         other variables            Result
Examples       test             <ignore>        is = :1  s = :3  ss = :2         
Sum            sum                :3                  temp = 1                   15
Summa<T>       getVal             :2                            
*/