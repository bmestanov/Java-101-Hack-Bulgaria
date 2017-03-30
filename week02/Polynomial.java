package week02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Polynomial {
    private final List<Integer> coefficients;

    private Polynomial(List<Integer> coefficients) {
        this.coefficients = new ArrayList<>();
    }

    public Polynomial(Integer... ns) {
        coefficients = new ArrayList<Integer>(ns.length);
        Collections.addAll(coefficients, ns);
    }

    public Polynomial sum(Polynomial b) {
        int sumSize = Math.min(size(), b.size());
        List<Integer> sumCoefficients = new ArrayList<>(sumSize);
        for (int i = 0; i < sumSize; i++) {
            sumCoefficients.add(get(i) + b.get(i));
        }

        Polynomial rest = (sumSize < size()) ? b : this;

        for (Integer i : rest.coefficients.subList(sumSize, size())) {
            sumCoefficients.add(i);
        }

        return new Polynomial(sumCoefficients);
    }

    public Polynomial sub(Polynomial b) {
        int sumSize = Math.min(size(), b.size());
        List<Integer> sumCoefficients = new ArrayList<>(sumSize);
        for (int i = 0; i < sumSize; i++) {
            sumCoefficients.add(get(i) - b.get(i));
        }

        Polynomial rest = (sumSize < size()) ? b : this;

        for (Integer i : rest.coefficients.subList(sumSize, size())) {
            sumCoefficients.add(i);
        }

        return new Polynomial(sumCoefficients);
    }

    public Polynomial prod(Polynomial b) {
        int sumSize = Math.min(size(), b.size());
        List<Integer> sumCoefficients = new ArrayList<>(sumSize);
        for (int i = 0; i < sumSize; i++) {
            sumCoefficients.add(get(i) * b.get(i));
        }

        Polynomial rest = (sumSize < size()) ? b : this;

        for (Integer i : rest.coefficients.subList(sumSize, size())) {
            sumCoefficients.add(i);
        }

        return new Polynomial(sumCoefficients);
    }

    public Polynomial multiply(Integer c) {
        return new Polynomial(coefficients
                .stream()
                .mapToInt(n -> c * n)
                .boxed()
                .collect(Collectors.toList())
        );
    }

    private int size() {
        return coefficients.size();
    }

    private Integer get(int i) {
        return coefficients.get(i);
    }
}
