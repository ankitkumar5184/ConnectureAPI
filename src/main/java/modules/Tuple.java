package modules;

public class Tuple<T, U> {
    private final T firstValue;
    private final U secondValue;

    public Tuple(T firstValue, U secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public T getFirstValue() {
        return this.firstValue;
    }

    public U getSecondValue() {
        return this.secondValue;
    }
}