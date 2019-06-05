import  java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@FunctionalInterface
interface Square {
    int calculate(int x);
}

interface Machine {

    default String createMachine() {
        return "Machine";
    }
}

interface FourWheeler {

    default String createFourWheeler () {
        return "Four wheeler";
    }
}

class Car implements Machine, FourWheeler {

    public void makeCar() {
        String machine = Machine.super.createMachine();
        String fourWheeler = FourWheeler.super.createFourWheeler();

        System.out.println("I am a " + fourWheeler + " Car with " + machine);
    }
}

public class SwitchExampleText {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Rehan Kodekar");
        names.add("Avinash Vishvakarma");
        names.add("Swapnil angane");
        names.add("Abishek pandey");

        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 45, 64, 4, 56, 6, 7, 8));

        var tester = new SwitchExampleText();

        tester.sortUsingJava8(names);
        System.out.println(names);

        tester.sorUsingJava8WithoutMethodRef(names);
        System.out.println(names);

        tester.sortUsingJava8UsingListSort(names);
        System.out.println(names);

        eval(numbers, n -> n > 10);

        final Square s = x -> x * x;

        System.out.println(s.calculate(60));

        var car = new Car();
        car.makeCar();

        names.stream().filter(String::isEmpty);
    }

    private void sorUsingJava8WithoutMethodRef(List<String> names) {
        Collections.sort(names, (var s1, var s2) -> s2.compareToIgnoreCase(s1));
    }

    private void sortUsingJava8UsingListSort(List<String> names) {
        names.sort((var s1, var s2) -> s1.compareToIgnoreCase(s2));
    }

    private void sortUsingJava8(List<String> names) {
        names.sort(String::compareToIgnoreCase);
    }

    private static void eval(List<Integer> list, Predicate<Integer> predicate) {

        for (Integer n : list) {
            if (predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }
}
