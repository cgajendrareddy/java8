package personal.gajju;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import static java.util.stream.Collector.Characteristics.*;
/**
 * Created by cgajendra-0220 on 5/19/15.
 */
public class ToListCollecotr<T> implements Collector<T,List<T>,List<T>> {
    @Override
    public Supplier<List<T>> supplier() {
        //return ()->new ArrayList<>();
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        //return (t,list)->list.add(t);
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1,list2)->{list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH,CONCURRENT));
    }
}
