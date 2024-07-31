public class FinancialForecasting {

    public static double futureVal(double pV, double G, int P) {
        if (P == 0) {
            return pV;
        }
        return (1 + G) * futureVal(pV, G, P - 1);
    }

    public static void main(String[] args) {
        
        double pV = 1000.0; 
        double G = 0.05; 
        int P = 10; 

        double futureValue = futureVal(pV, G, P);
        System.out.println("Future Value: " + futureValue);
    }
}
