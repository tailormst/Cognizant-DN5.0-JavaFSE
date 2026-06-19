public class FinancialForecasterTest {

    public static void main(String[] args) {

        FinancialForecaster forecaster = new FinancialForecaster();

        double principal = 10000.0;
        double growthRate = 0.08;
        int years = 10;

        System.out.println("--- Plain recursive forecast ---");
        double futureValue = forecaster.forecastFutureValue(principal, growthRate, years);
        System.out.printf("Starting amount: $%.2f%n", principal);
        System.out.printf("Annual growth rate: %.0f%%%n", growthRate * 100);
        System.out.printf("Forecast after %d years: $%.2f%n", years, futureValue);

        System.out.println("\n--- Memoized recursive forecast (same inputs, repeated) ---");
        long start = System.nanoTime();
        double memoResult1 = forecaster.forecastFutureValueMemoized(principal, growthRate, years);
        long firstCallTime = System.nanoTime() - start;

        start = System.nanoTime();
        double memoResult2 = forecaster.forecastFutureValueMemoized(principal, growthRate, years);
        long secondCallTime = System.nanoTime() - start;

        System.out.printf("First call result:  $%.2f (took %d ns)%n", memoResult1, firstCallTime);
        System.out.printf("Second call result: $%.2f (took %d ns, served from cache)%n",
                memoResult2, secondCallTime);

        System.out.println("\n--- Analysis ---");
        System.out.println("Time complexity of the recursive forecast: O(n), where n is the number");
        System.out.println("of years. Each recursive call does one multiplication and makes exactly");
        System.out.println("one further call, forming a simple chain rather than a branching tree.");
        System.out.println();
        System.out.println("Optimization: without memoization, asking for the same forecast many");
        System.out.println("times recomputes the entire chain every time. By caching results keyed");
        System.out.println("on (principal, rate, years), repeated requests become O(1) lookups.");
        System.out.println("For very large 'years' values, an iterative loop is also a good");
        System.out.println("alternative to recursion, since it avoids the overhead of deep call");
        System.out.println("stacks (and the risk of a StackOverflowError for huge inputs).");
    }
}