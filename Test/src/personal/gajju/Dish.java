package personal.gajju;

import com.sun.xml.internal.xsom.impl.WildcardImpl;

/**
 * Created by cgajendra-0220 on 5/17/15.
 */
public class Dish {
    private final String name;
    private final boolean vegetarian;

    public Type getType() {
        return type;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public String getName() {
        return name;
    }

    private final int calories;
    private final Type type;

    public Dish(String name,boolean vegetarian,int calories,Type type)
    {
        this.name=name;
        this.vegetarian=vegetarian;
        this.calories=calories;
        this.type=type;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                '}';
    }

    public enum Type{MEAT,FISH, OTHER};

}
