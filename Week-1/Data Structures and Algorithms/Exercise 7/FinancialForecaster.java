import java.util.HashMap;
import java.util.Map;

public class FinancialForecaster {

    private final Map<String, Double> cache = new HashMap<>();

    public double forecastFutureValue(double principal, double annualGrowthRate, int years) {
        if (years <= 0) {
            return principal;
        }
        double valueLastYear = forecastFutureValue(principal, annualGrowthRate, years - 1);
        return valueLastYear * (1 + annualGrowthRate);
    }

    public double forecastFutureValueMemoized(double principal, double annualGrowthRate, int years) {
        String key = principal + "_" + annualGrowthRate + "_" + years;

        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        double result;
        if (years <= 0) {
            result = principal;
        } else {
            double valueLastYear = forecastFutureValueMemoized(principal, annualGrowthRate, years - 1);
            result = valueLastYear * (1 + annualGrowthRate);
        }

        cache.put(key, result);
        return result;
    }
}