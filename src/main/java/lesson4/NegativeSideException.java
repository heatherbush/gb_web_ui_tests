package lesson4;

public class NegativeSideException extends Exception {
    public NegativeSideException() {
        System.out.println("Для одной или нескольких сторон треугольника введено отрицательное число.");
    }
}
