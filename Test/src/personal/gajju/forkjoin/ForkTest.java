package personal.gajju.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Function;
import java.util.stream.LongStream;

/**
 * Created by cgajendra-0220 on 5/19/15.
 */
public class ForkTest {
    public static void main(String[] args) {

        System.out.println("forkJoinSum = " + measureSumPerformance(ForkTest::forkJoinSum,100000000));
    }
    public static long forkJoinSum(long n)
    {
        long[] numbers= LongStream.rangeClosed(1,n).toArray();
        ForkJoinTask<Long> task=new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }
    public static long measureSumPerformance(Function<Long, Long> adder, long n)
    {
        long fastest=Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long startTime=System.nanoTime();
            long sum=adder.apply(n);
            long duration = (System.nanoTime() - startTime) / 1000000;
            System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;

        }
        return fastest;
    }
}


