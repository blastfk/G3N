import generator.AbstractGenerator;
import generator.Generator;

public class Main {
    public static void main(String... args) {
        Generator<Integer> generator = new AbstractGenerator<Integer>(1) {
            @Override
            protected Integer generate() {
                return 2 * last();
            }

            @Override
            public boolean hasNext() {
                return last() <= Integer.MAX_VALUE / 3;
            }
        };

        for (int n : generator) System.out.println(n);
    }
}
