package personal.gajju;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

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
        System.out.println("menu.stream().filter(d->d.getType()== Dish.Type.MEAT).findFirst() = " + menu.stream().filter(d->d.getType()== Dish.Type.MEAT).findFirst());
        //find any
        System.out.println("menu.stream().filter(Dish::isVegetarian).findAny() = " + menu.stream().filter(Dish::isVegetarian).findAny());
        
        //reduce
        System.out.println("menu.stream().reduce((d1,d2)->(d1.getCalories()>d2.getCalories())?d1:d2 = " + menu.stream().reduce((d1,d2)->(d1.getCalories()>d2.getCalories())?d1:d2));
        System.out.println("menu.stream().map(d->1).reduce(0,(a,b)->a+b) = " + menu.stream().map(d->1).reduce(0,(a,b)->a+b));




        //dishes.forEach(System.out::println);
    }

}