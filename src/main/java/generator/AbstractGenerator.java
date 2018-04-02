package generator;

import java.util.Iterator;

/**
 * Default implementation of a generator.
 *
 * @param <T> The type of all values produced by this generator.
 */
public abstract class AbstractGenerator<T> implements Generator<T> {
    // ATTRIBUTES
    private T initial;
    private T last;
    private int count;


    // CONSTRUCTEUR
    protected AbstractGenerator(T initial) {
        initialize(initial);
    }
    protected AbstractGenerator() {
        this(null);
    }


    // REQUESTS
    @Override
    public int count() {
        return count;
    }

    @Override
    public T initial() {
        return initial;
    }

    @Override
    public T last() {
        return last;
    }

    @Override
    public boolean hasNext() {
        return true; // By default, generators logic aren't limited or stopped...
    }


    // COMMANDS
    @Override
    public void reset(T initial) {
        initialize(initial);
    }
    @Override
    public void reset() {
        reset(initial);
    }

    @Override
    public final T next() {
        if (!hasNext()) throw new AssertionError();

        update(generate());
        return last;
    }

    @Override
    public Iterator<T> iterator() {
        return new GeneratorIterator<>(this);
    }


    // TOOLS
    /**
     * Defines how this generator should be initialized. This method is call by the constructor,
     * and by the reset() function.
     * (Can be redefined, but be sure to make a call to the super method...)
     *
     * @param initial
     */
    protected void initialize(T initial) {
        this.initial = initial;
        last = initial;
        count = 0;
    }
    protected void initialize() {
        initialize(initial);
    }

    /**
     * Defines the logic of generation used by this generator in order to produce
     * the next value to create.
     */
    abstract protected T generate();

    /**
     * Defines how this generator will change its internal state after
     * the generation of a given value.
     * (Can be redefined, but be sure to make a call to the super method...)
     *
     * @param value The generated value.
     */
    protected void update(T value) {
        last = value;
        count++;
    }
    protected void update() {
        count++;
    }
}
