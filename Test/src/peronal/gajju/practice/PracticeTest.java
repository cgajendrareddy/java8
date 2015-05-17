package peronal.gajju.practice;

import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * Created by cgajendra-0220 on 5/17/15.
 */
public class PracticeTest {

    public static void main(String[] args)
    {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950)
        );

        //1)Find all transactions in the year 2011 and sort them by value (small to high).
        System.out.println("transactions.stream().filter(t->t.getYear()==2011).sorted().collect(toList()) = " + transactions.stream().filter(t -> t.getYear() == 2011).sorted(comparing(Transaction::getValue)).collect(toList()));
        //2)What are all the unique cities where the traders work?
        System.out.println("transactions.stream().map(Transaction::getTrader).map(Trader::getCity).distinct().collect(toList()) = " + transactions.stream().map(Transaction::getTrader).map(Trader::getCity).distinct().collect(toList()));
        //3. Find all traders from Cambridge and sort them by name.
        System.out.println("transactions.stream().map(Transaction::getTrader).filter(t -> t.getCity().equalsIgnoreCase(\"Cambridge\")).distinct().sorted(comparing(Trader::getName)).collect(toList()) = " + transactions.stream().map(Transaction::getTrader).filter(t -> t.getCity().equalsIgnoreCase("Cambridge")).distinct().sorted(comparing(Trader::getName)).collect(toList()));
        //4. Return a string of all traders’ names sorted alphabetically.
        System.out.println("transactions.stream().map(Transaction::getTrader).map(Trader::getName).distinct().sorted().reduce(\"\",(a,b)->a+b) = " + transactions.stream().map(Transaction::getTrader).map(Trader::getName).distinct().sorted().reduce("", (a, b) -> a + b));
        //5. Are any traders based in Milan
        System.out.println("transactions.stream().map(Transaction::getTrader).anyMatch(t -> t.getCity().equalsIgnoreCase(\"Milan\")) = " + transactions.stream().map(Transaction::getTrader).anyMatch(t -> t.getCity().equalsIgnoreCase("Milan")));
        //6. Print all transactions’ values from the traders living in Cambridge
        transactions.stream().filter(t->t.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue).forEach(System.out::println);
        //7. What’s the highest value of all the transactions?
        System.out.println("transactions.stream().map(Transaction::getValue).reduce(0,(a,b)->(a>b)?a:b) = " + transactions.stream().map(Transaction::getValue).reduce(0,(a,b)->(a>b)?a:b));
        System.out.println("transactions.stream().map(Transaction::getValue).reduce(0,(a,b)->(a>b)?a:b) = " + transactions.stream().map(Transaction::getValue).reduce(Integer::max));
        //8. Find the transaction with the smallest value
        System.out.println("transactions.stream().map(Transaction::getValue).reduce((a,b)->(a<b)?a:b = " + transactions.stream().map(Transaction::getValue).reduce((a,b)->(a<b)?a:b));
    }
}
