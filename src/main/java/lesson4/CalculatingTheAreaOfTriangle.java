package lesson4;

public class CalculatingTheAreaOfTriangle {

    public static void main(String[] args) {
    }

    public static double calcTheAreaOfTriangle(double sideA, double sideB, double sideC) throws NegativeSideException {
        if (sideA < 0 || sideB < 0 || sideC < 0) throw new NegativeSideException();
        double p = (sideA + sideB + sideC) / 2;
        double square = Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
        System.out.println("Площадь треугольника равна:" + square);
        return square;
    }
}
