package Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cgajendra-0220 on 5/1/15.
 */
public class Test {

    public static void main (String[] args)
    {
       List<Integer> intList= Arrays.asList(0, 1, 2, 3, 4, 5);
        PredicateTest<Integer> test=new PredicateTest<>();
        System.out.println("all nos:"+test.testPredicate(x-> true,intList));
        System.out.println("even nos:"+test.testPredicate(x->(x&1)==0,intList));
        System.out.println("even nos:"+test.testPredicate(x->(x%2)==1,intList));

    }
}

