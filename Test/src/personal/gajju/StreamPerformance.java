package personal.gajju;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by cgajendra-0220 on 5/19/15.
 */
public class StreamPerformance {
    public static void main(String[] args) {
        System.out.println("Iterative sum done in: " + measureSumPerformance(StreamPerformance::iterativeSum, 10_000_000) + " msecs");
        System.out.println("Seq sum done in: " + measureSumPerformance(StreamPerformance::sequentialSum, 10_000_000) + " msecs");
        System.out.println("Parallel sum done in: " + measureSumPerformance(StreamPerformance::parallelSum, 10_000_000) + " msecs");

        System.out.println("SideEffect parallel sum done in: " + measureSumPerformance(StreamPerformance::sideEffectParallelSum, 10_000_000L) + " msecs" );
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
    public static long iterativeSum(long n)
    {
        long result=0;
        for (long i = 0; i <= n; i++) {
            result+=i;
        }
        return result;
    }
    public static long sequentialSum(long n)
    {
        //return Stream.iterate(0L,i->i+1).limit(n).reduce(0L,(i,j)->i+j);
        return LongStream.rangeClosed(0,n).reduce(0L, Long::sum);
    }

    public static long parallelSum(long n)
    {
        //return Stream.iterate(0L,i->i+1).limit(n).parallel().reduce(0L,(i,j)->i+j);
        return LongStream.rangeClosed(0, n).parallel().reduce(0L,Long::sum);
    }
    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }

    public static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }


}

 class Accumulator {
    public long total = 0;
    public void add(long value) { total += value; }
}


