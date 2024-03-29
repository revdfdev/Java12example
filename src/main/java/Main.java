import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

/*
    Parking lot
    use this parking lot to see
    for some of the terms in documentation
    that will be explained in the lower part of the class:

    Pure functions:
    Side - effects:
    Higher order functions:
    Method Reference Instance:
    Lazy:
    Functional Composition:

 */


public class Main {

    /*
     * For example my daughter wants to eat cake and she is allergic to eggs
     * So prior to java 8 I would do the following.
     */

    private static Consumer<String> printer = System.out::println;

    public static void yellOut (String words) {
        Objects.requireNonNull(words, () -> "Created issue " + Main.createIssue());
        System.out.printf("%s!!!! %n", words.toUpperCase());
    }

    public static String createIssue() {
        System.out.println("Some external api call to bug tracker");
        return "ABC-1234";
    }

    public static void main(String[] args) {
        /*
         * I would list out all the ingredients in a cake.
         * Think of this as, I am in a restaurant.
         */
        var ingredients = Arrays.asList(
          "Flour",
          "salt",
          "baking powder",
          "butter",
          "eggs",
          "milk"
        );

        /*
         * This is the imperative way of answer by the waiter.
         * This would be the wrong way to answer the questions
         *  The waiter wont just go and tell you each of the ingredients
         * in the cake.
         */

        boolean hasEggs = false;
        for (String ingredient: ingredients) {
            if (ingredient.equals("eggs")) {
                hasEggs = true;
                break;
            }
        }

        if (hasEggs) {
            System.out.println("Sorry , cake contains eggs ");
        }


        /*
         * Rather than he will be declarative in answering the question
         * he would do the following.
         * contains is a part of the collections
         * api which was introduced in java 8
         *
         */

        if (ingredients.contains("eggs")) {
            System.out.println("Sorry, cake contains eggs");
        }

        // A more better way with imperative style would
        // To print out every ingredient in a cake
        for (String ingredient: ingredients) {
            System.out.println(ingredient);
        }

        /*
         * Lets explore forEach method on collections
         *
         * {@code Consumer}
         * Consumer : Represents an operation that accepts a single input argument and returns no
         * result. Unlike most other functional interfaces, {@code Consumer} is expected
         * to operate via side-effects.the type of the input to the operation
         *
         * @since 1.8
         *
         * Functional interfaces: Functional interfaces are interfaces which
         * have only one abstract method
         *
         * Abstract methods as we know are just declared in the interfaces
         * and no operations are performed in them
         * they can only be overridden in the implementing class
         *
         * So finally we created an anonymous function
         * and creating anonymous functions is the heart of functional
         * programming
         *
         * A method that accepts a function as parameter
         * is referred to as a higher order function.
         */
        ingredients.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("Ingredient name: " + s);
            }
        });

        /*
         * The above syntax however just to print only the ingredient
         * names we are writing 3-4 lines of code.
         * we can still minimize that
         * to one line using lambdas
         *
         * So to start with lambda expression
         * in the above example we see that
         * Consumer is a functional interface
         * which has only one abstract method
         * which is implement to a  generic of List class
         * and the List is a type of String
         * so therefore the accept method implemented by the interface
         * will expect a String to be passed
         * so below are 3 ways to write a lambda expression
         *
         */

        ingredients.forEach((String ingredient) -> {
            System.out.println(ingredient);
        });

        // Which can be reduced to

        // Since we know what type is expected
        // the lambda expression syntax
        // will actually infer the type
        // So we can remove the type
        // String from the ingredient
        ingredients.forEach( (ingredient) -> {
            System.out.println(ingredient);
        });

        // In this case there wont be any side effects
        // Side effects are operations that modifies the state / value of a
        // variable, So this is a pure function.
        // Pure functions: Pure functions are functions that does not have
        // any side effects

        // and when there is only one parameter we can remove the paranthesis in
        // in the params.
        ingredients.forEach( ingredient -> System.out.println(ingredient));

        // There are chances of side effects
        // here, because between the brackets
        // we can mutate the value of the out -
        // - put that is expected

        // In this case every ingredient will be Boiled eggs instead of the
        // actual name of the ingredient
        ingredients.forEach( (ingredient) -> {
            // for example
            ingredient = "Boiled eggs";
            System.out.println(ingredient);
        });


        // You can also make Consumer functions for reuse  first class.
        // Even more concise and useful.
        // Here consumer can be moved outside the class scope as well
        // into another class , an can pe imported static
        // or outside the scope of this main function


        //Consumer<String> printer = ingredient -> System.out.println(ingredient);
        ingredients.forEach(printer);


        // Lambdas can be more concise with method reference
        /**
         * In the forEach function the parameter consumer only
         * accepts a string as a parameter
         * and returns nothing or you may say the function's
         * return type is void.
         *
         *
         * Using method reference , you can just pass the string
         * that is coming from the parameter and pass it on to
         * the println method.
         *
         * Like telling the forEach method take every ingredient in
         * the cake and pass it on to the println method
         *
         * So Method reference means is this is referring to
         * instance method on the static out instance object of
         * System.
         *
         * The signature is exactly what the forEach method is looking for
         *
         * Explaining System.out.println:
         * out on System is static but println is an instance method
         * to the print stream
         */
        ingredients.forEach(System.out::println);

        //Example
        Main.yellOut("But I want that cake");

        // You can see that
        // How the syntax is exactly the same
        // It was able to infer what context the call
        // thinks through
        // As long as the lamdba function matches the expected
        // signature, we can use it as an argument
        // This signatures are of called as function shapes

        ingredients.forEach(Main::yellOut);

        /**
         * Please visit
         * https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
         * for more information on the java.util.function package
         *
         * Lets see Supplier
         * Supplier represents a supplier of results.
         */
        Main.yellOut(null);


        // Next function shape is Function
        // Represents a function that accepts one
        // arguments and reproduces a result.
        // Function(Functional interface) takes single argment and returns
        // a value
        // This is great for transformation


        // Next function shape is Predicate
        // Accepts a parameter and returns
        // a boolean value
        // helpful for filter values.


        // The amount of parameter a function accepts
        // is referred to as arity
        //
    }
}
