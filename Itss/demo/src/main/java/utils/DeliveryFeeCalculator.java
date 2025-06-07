package utils;

public class DeliveryFeeCalculator {

    public static double calculateFee(String province, double weight, boolean rushOrder) {
        double fee = 0;
        if (province.equalsIgnoreCase("Hanoi") || province.equalsIgnoreCase("Ho Chi Minh City")) {
            fee = 22000; // up to 3kg
        } else {
            fee = 30000; // up to 0.5kg
        }

        if (weight > 3) {
            fee += Math.ceil((weight - 3) / 0.5) * 2500;
        }

        if (rushOrder) {
            fee += 10000;
        }

        return fee;
    }
}
