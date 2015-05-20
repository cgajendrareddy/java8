package personal.gajju;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * Created by cgajendra-0220 on 5/17/15.
 */
public class Menu {

    public static void main(String[] args)
    {
        List<Dish> menu= Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        System.out.println(menu);

        Stream<String> dishes= menu.stream().filter(dish->{
            System.out.println("filter :"+dish+ dish.getCalories());return dish.getCalories()>300;})
                .map(d->{System.out.println("map:  "+d+d.getName());
                    return d.getName();})
                .limit(3)
                .collect(toList()).stream();
        dishes.forEach(System.out::println);

        //filter
        List<Dish> vegDishes=menu.stream().filter(Dish::isVegetarian).collect(toList());
        System.out.println("Menu.main"+vegDishes);

        //distinct
        List<Integer> distinctValues=Arrays.asList(1,2,3,2,3,4,6,6,4).stream().filter(i->i%2==0).distinct().collect(toList());
        System.out.println("distinctValues = " + distinctValues);

        //limit
        List<Dish> firstTwoHighCaloriedDishes=menu.stream().filter(d->d.getCalories()>300).limit(2).collect(toList());
        System.out.println("firstTwoHighCaloriedDishes = " + firstTwoHighCaloriedDishes);

        //skip
        List<Dish> skipFirstTwoHighCaloriedDishes=menu.stream().filter(d -> d.getCalories() > 300).skip(2).collect(toList());
        System.out.println("skipFirstTwoHighCaloriedDishes = " + skipFirstTwoHighCaloriedDishes);

        //map
        List<Integer> stringLengthOfEachMeatDish=menu.stream().filter(d->d.getType()== Dish.Type.MEAT).map(Dish::getName).map(String::length).collect(toList());
        System.out.println("stringLengthOfEachMeatDish = " + stringLengthOfEachMeatDish);

        //flatmapworstexample
        System.out.println("menu.stream().map(Dish::getName).map(s->s.split(\"\")).distinct().collect(toList()) = " + menu.stream().map(Dish::getName).map(s -> s.split("")).distinct().collect(toList()));
        List<Stream> temp1=menu.stream().map(Dish::getName).map(s->s.split("")).map(Arrays::stream).distinct().collect(toList());
        System.out.println("temp1 = " + Arrays.asList(temp1.toArray()));

        //flat map
        System.out.println("menu.stream().map(Dish::getName).map(s -> s.split(\"\")).flatMap(Arrays::stream).distinct().collect(toList()) = " + menu.stream().map(Dish::getName).map(s -> s.split("")).flatMap(Arrays::stream).distinct().collect(toList()));

        List<Integer> list1=Arrays.asList(1,2,3);
        List<Integer> list2=Arrays.asList(3, 4);

        //flat map to print pairs
        List<int[]> temp3=list1.stream().flatMap(i -> list2.stream().map(j -> new int[]{i, j})).collect(toList());
        System.out.println("temp3 = " + temp3);
        //flat map to print pairs whose sum is greater than
        List<int[]> temp4=list1.stream().flatMap(i -> list2.stream().filter(j -> (i + j )% 3 == 0).map(j -> new int[]{i, j})).collect(toList());
        System.out.println("temp4 = " + temp4);

        //any match
        System.out.println("menu.stream().anyMatch(Dish::isVegetarian) = " + menu.stream().anyMatch(Dish::isVegetarian));
        
        //all match
        System.out.println("menu.stream().allMatch(d->d.getCalories()<1000) = " + menu.stream().allMatch(d -> d.getCalories() < 1000));
        
        //none match
        System.out.println("menu.stream().noneMatch(d->d.getCalories()>0) = " + menu.stream().noneMatch(d -> d.getCalories() > 0));

        //find first
        System.out.println("menu.stream().filter(d->d.getType()== Dish.Type.MEAT).findFirst() = " + menu.stream().filter(d -> d.getType() == Dish.Type.MEAT).findFirst());
        //find any
        System.out.println("menu.stream().filter(Dish::isVegetarian).findAny() = " + menu.stream().filter(Dish::isVegetarian).findAny());
        
        //reduce
        System.out.println("menu.stream().reduce((d1,d2)->(d1.getCalories()>d2.getCalories())?d1:d2 = " + menu.stream().reduce((d1,d2)->(d1.getCalories()>d2.getCalories())?d1:d2));
        System.out.println("menu.stream().map(d->1).reduce(0,(a,b)->a+b) = " + menu.stream().map(d->1).reduce(0,(a,b)->a+b));



        //int stream
        System.out.println("menu.stream().mapToInt(Dish::getCalories).sum() = " + menu.stream().mapToInt(Dish::getCalories).sum());
        //optional int
        System.out.println("menu.stream().mapToInt(Dish::getCalories).max().orElse(0) = " + menu.stream().mapToInt(Dish::getCalories).max().orElse(0));
        //dishes.forEach(System.out::println);


        Stream<int[]> temp2=IntStream.rangeClosed(1,100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));
        //List<int[]> temp5=temp2

        temp2.forEach(a -> {
            List<Integer> tt = IntStream.of(a).boxed().collect(toList());
            for (int i = 0; i < a.length; i++) {
                System.out.print("a[i] = " + a[i]);
            }
            System.out.println("");
        });


        Stream<double[]> ttee=IntStream.rangeClosed(1,100).boxed().flatMap(a->IntStream.rangeClosed(a,100).mapToObj(b->new double[]{a,b,Math.sqrt(a*a+b*b)}).filter(t->t[2]%1==0));
        ttee.forEach(a -> {
            DoubleStream.of(a).boxed().collect(toList()).forEach(System.out::print);
            System.out.println();
        });


        System.out.println("===============================================");
        //counting
        System.out.println("menu.stream().collect(Collectors.counting()) = " + menu.stream().collect(Collectors.counting()));
        System.out.println("menu.stream().collect(maxBy((d1,d2)->(d1.getCalories()>d2.getCalories())?1:0)) = " + menu.stream().collect(maxBy((d1, d2) -> (d1.getCalories() > d2.getCalories()) ? 1 : 0)));
        Comparator<Dish> comp= Comparator.comparingInt(Dish::getCalories);
        //maxBy
        System.out.println("menu.stream().collect(maxBy(comp)) = " + menu.stream().collect(maxBy(comp)));
        //summarizing int
        System.out.println("menu.stream().collect(summarizingInt(Dish::getCalories)) = " + menu.stream().collect(summarizingInt(Dish::getCalories)));
        //joining
        System.out.println("menu.stream().map(Dish::getName) = " + menu.stream().map(Dish::getName).collect(joining()));
        //joining with deli
        System.out.println("menu.stream().map(Dish::getName).collect(joining(\",\", \"[\", \"]\")) = " + menu.stream().map(Dish::getName).collect(joining(",", "[", "]")));

        //reducing
        System.out.println("menu.stream().collect(reducing(0, Dish::getCalories, (a, b) -> a + b)) = " + menu.stream().collect(reducing(0, Dish::getCalories, (a, b) -> a + b)));
        System.out.println("menu.stream().collect(reducing((d1,d2)->(d1.getCalories()>d2.getCalories())?d1:d2)) = " + menu.stream().collect(reducing((d1, d2) -> (d1.getCalories() > d2.getCalories()) ? d1 : d2)));

        System.out.println("menu.stream().collect(groupingBy(Dish::getType)) = " + menu.stream().collect(groupingBy(Dish::getType)));
        System.out.println("menu.stream().collect(groupingBy(d->{)) = " + menu.stream().collect(groupingBy(
                dish -> {
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else return CaloricLevel.FAT;
        })));

        System.out.println("menu.stream().collect(groupingBy(Dish::getType,groupingBy(dish->{if (dish.getCalories() <= 400) return CaloricLevel.DIET;\n        else return CaloricLevel.FAT; }))) = " + menu.stream().collect(groupingBy(Dish::getType, groupingBy(dish -> {
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else return CaloricLevel.FAT;
        }))));
        //Collecting data in subgroups
        System.out.println("menu.stream().collect(groupingBy(Dish::getType,counting())) = " + menu.stream().collect(groupingBy(Dish::getType, counting())));
        System.out.println("menu.stream().collect(groupingBy(Dish::getType,maxBy((Dish1,Dish2)->(Dish1.getCalories()>Dish2.getCalories())?1:0))) = " + menu.stream().collect(groupingBy(Dish::getType, maxBy((Dish1, Dish2) -> (Dish1.getCalories() > Dish2.getCalories()) ? 1 : 0))));


        //Collecting data in subgroups
        System.out.println("menu.stream().collect(groupingBy(Dish::getType,collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get))) = " + menu.stream().collect(groupingBy(Dish::getType, collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get))));
        //Mapping 
        System.out.println("menu.stream().collect(groupingBy(Dish::getType,mapping(dish))) = " + menu.stream().collect(groupingBy(Dish::getType, mapping(dish -> {
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        }, toCollection(HashSet::new)))));

        //partitioning
        System.out.println("menu.stream().collect(partitioningBy(Dish::isVegetarian)) = " + menu.stream().collect(partitioningBy(Dish::isVegetarian)));
        System.out.println("menu.stream().collect(partitioningBy(Dish::isVegetarian,groupingBy(Dish::getType))) = " + menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType))));
        System.out.println("menu.stream().collect(partitioningBy(Dish::isVegetarian,maxBy(Comparator.comparingInt(Dish::getCalories)))) = " + menu.stream().collect(partitioningBy(Dish::isVegetarian, maxBy(Comparator.comparingInt(Dish::getCalories)))));
        System.out.println("IntStream.rangeClosed(1,100).boxed().collect(partitioningBy()) = " + IntStream.rangeClosed(2,100).boxed().collect(partitioningBy(i->isPrime(i))));
        
        //custom collector
        System.out.println("menu.stream().collect(new ToListCollecotr<Dish>()) = " + menu.stream().collect(new ToListCollecotr<Dish>()));
        
        //Performing a custom collect without creating a Collector
        System.out.println("menu.stream().collect(ArrayList::new,List::add,List::addAll) = " + menu.stream().collect(ArrayList::new, List::add, List::addAll));


        
        


    }

    public static boolean isPrime(int number)
    {
        int candidateRoot=(int)Math.sqrt(number);
        return IntStream.range(2,candidateRoot).noneMatch(i->number%i==0);
    }
    public enum CaloricLevel{DIET,NORMAL,FAT};


}
