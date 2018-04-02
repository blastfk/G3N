package generator;

import generator.AbstractGenerator;
import generator.Generator;
import org.junit.Before;
import org.junit.Test;


public class GeneratorTest {
    // CONSTANTS
    /**
     * The arbitrary value used to impose a limit to the number of values
     * produced by a generator for these tests.
     */
    public static final int VALUES_MAX = 20;


    // ATTRIBUTES
    private Generator<Integer> generator;


    // BEFORE
    @Before
    public void before() {
        generator = generateCounterTo(VALUES_MAX);
    }


    // TESTS
    // -- next() method
    @Test
    public void nextProduces10Values() {
        // WHEN/THEN
        for (int k = 1; k <= 10; ++k) {
            assert generator.hasNext();
            assert generator.next() == k;
            assert generator.count() == k;
        }
    }

    @Test
    public void nextProduces20ValuesThenIsUnusable() {
        // WHEN/THEN
        for (int k = 1; k <= 20; ++k) {
            assert generator.hasNext();
            assert generator.next() == k;
            assert generator.count() == k;
        }

        assert !generator.hasNext();
    }

    @Test(expected = AssertionError.class)
    public void nextErrorWhenProducingMoreThan20Values() {
        // WHEN/THEN
        for (int k = 1; k <= 21; ++k) generator.next();
    }


    // -- reset() method
    @Test
    public void resetWithoutNewInitialValue() {
        // GIVEN
        int initial = generator.initial();
        while (generator.hasNext()) generator.next();

        // WHEN
        generator.reset();

        // THEN
        assert generator.initial() == initial;
        assert generator.last() == initial;
        assert generator.hasNext();
    }

    @Test
    public void resetWithNewInitialValue() {
        // GIVEN
        while (generator.hasNext()) generator.next();
        int newInitial = VALUES_MAX / 2;

        // WHEN
        generator.reset(newInitial);

        // THEN
        assert generator.initial() == newInitial;
        assert generator.last() == newInitial;
        assert generator.hasNext();
    }

    @Test
    public void nextProducesAfterReset() {
        // GIVEN
        while (generator.hasNext()) generator.next();
        generator.reset();

        // WHEN/THEN
        for (int k = 1; k <= 20; ++k) {
            assert generator.hasNext();
            assert generator.next() == k;
            assert generator.count() == k;
        }
    }

    @Test
    public void nextProducesAfterResetWithNewInitial() {
        // GIVEN
        while (generator.hasNext()) generator.next();
        int newInitial = - (VALUES_MAX / 2);

        // WHEN
        generator.reset(newInitial);

        // THEN
        for (int k = 1; k <= 20; ++k) {
            assert generator.hasNext();
            assert generator.next() == k + newInitial;
            assert generator.count() == k;
        }
    }

    // -- iterator() method
    @Test
    public void iteratorIsValid() {
        int k = 0;

        // WHEN/THEN
        for (int value : generator) {
            ++k;
            assert value == k;
        }
    }


    // TOOLS
    /**
     * Creates a simple integer generator, which counts all integers from 0 to n.
     *
     * @param n
     * The counting limit.
     */
    private Generator<Integer> generateCounterTo(final int n) {
        return new AbstractGenerator<Integer>(0) {
            @Override
            public boolean hasNext() {
                return last() < n;
            }

            @Override
            protected Integer generate() {
                return last() + 1;
            }
        };
    }
}
