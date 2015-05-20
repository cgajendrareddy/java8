package personal.gajju.forkjoin;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by cgajendra-0220 on 5/19/15.
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private long[] array;
    private int start;
    private int end;
    public static final long THRESHOLD=10000L;
    public ForkJoinSumCalculator(long[] array)
    {
        this(array,0,array.length);
    }
    public ForkJoinSumCalculator(long[] array,int start,int end)
    {
        this.array=array;
        this.start=start;
        this.end=end;
    }
    @Override
    protected Long compute() {
        long toReturn=0L;
        int length=end-start;
        if(length<=THRESHOLD)
        {
            toReturn= sequentialSum();
        }
        else
        {
            ForkJoinSumCalculator left=new ForkJoinSumCalculator(array,start,(start+length/2));
            left.fork();
            ForkJoinSumCalculator right=new ForkJoinSumCalculator(array,(start+length/2),end);
            long rightResult=right.compute();

            long leftResult=left.join();
            System.out.println(start+"->"+(start+length/2)+"leftResult = " + leftResult);
            System.out.println(((start+length/2)+1)+"->"+end+" rightResult = " + rightResult);
            toReturn=rightResult+leftResult;

        }
        return toReturn;
    }

    private long sequentialSum() {
        long toReturn=0L;
        for(int i=start;i<end;i++)
        {
            toReturn+=array[i];
        }
        return toReturn;
    }
}
