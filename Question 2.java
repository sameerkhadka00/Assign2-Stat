import java.util.Random;

public class ChebyshevsInequalityVerification {

    public static void main(String[] args) {
        Random rand = new Random();

        double[] normalData = generateNormalData(50, 10, 0.5);
        double[] uniformData = generateUniformData(50, -20, 20);

        verifyChebyshevsInequality(normalData, "normal distribution");
        verifyChebyshevsInequality(uniformData, "uniform distribution");
    }

    public static double[] generateNormalData(int size, double mean, double stdDev) {
        Random rand = new Random();
        double[] data = new double[size];
        for (int i = 0; i < size; i++) {
            data[i] = stdDev * rand.nextGaussian() + mean;
        }
        return data;
    }
 public static double[] generateUniformData(int size, double min, double max) {
        Random rand = new Random();
        double[] data = new double[size];
        for (int i = 0; i < size; i++) {
            data[i] = rand.nextDouble() * (max - min) + min;
        }
        return data;
    }

public static void verifyChebyshevsInequality(double[] data, String distributionType) {
        int k1 = 1;
        int k2 = 2;
        
        int countK1 = countValuesWithinKStandardDeviations(data, k1);
        int countK2 = countValuesWithinKStandardDeviations(data, k2);
        
        double proportionK1 = (double) countK1 / data.length;
        double proportionK2 = (double) countK2 / data.length;
        
        boolean isK1Verified = proportionK1 >= 1 - 1 / Math.pow(k1, 2);
        boolean isK2Verified = proportionK2 >= 1 - 1 / Math.pow(k2, 2);

        System.out.println("For " + distributionType + ":");
        System.out.println("For k=1, Chebyshev's inequality is " + (isK1Verified ? "verified" : "not verified"));
        System.out.println("For k=2, Chebyshev's inequality is " + (isK2Verified ? "verified" : "not verified"));
        System.out.println();
    }

public static int countValuesWithinKStandardDeviations(double[] data, int k) {
        int count = 0;
        double mean = calculateMean(data);
        double stdDev = calculateStandardDeviation(data, mean);

        for (double value : data) {
            if (value >= (mean - k * stdDev) && value <= (mean + k * stdDev)) {
                count++;
            }
        }
        return count;
    }
 public static double calculateMean(double[] data) {
        double sum = 0;
        for (double value : data) {
            sum += value;
        }
        return sum / data.length;
    }

    public static double calculateStandardDeviation(double[] data, double mean) {
        double sumSquaredDiff = 0;
        for (double value : data) {
            sumSquaredDiff += Math.pow(value - mean, 2);
        }
        return Math.sqrt(sumSquaredDiff / data.length);
    }
}
