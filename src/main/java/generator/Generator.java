package generator;

/**
 * Defines a value generator. A generator produces values by
 * following a specific generation logic, defined within its next() method.
 *
 * @param <T> The type of all values produced by this generator.
 */
public interface Generator<T> extends Iterable<T> {
    // REQUESTS
    /**
     * Indicates the number of generations provided by this generator.
     */
    int count();

    /**
     * Gives the initialization value of this generator.
     * (Acts as a last, when no value has been produced before).
     */
    T initial();

    /**
     * Provides the last generated value by this generator.
     */
    T last();

    /**
     * Tells if this genrator is able to produce an other value.
     */
    boolean hasNext();


    // COMMANDS
    /**
     * Reinitialize this generator to reproduce the generation.
     *
     * @param initial The value used as initialization value after reset.
     */
    void reset(T initial);

    /**
     * Reinitialize this generator to reproduce the generation.
     */
    void reset();

    /**
     * Provides the next value.
     */
    T next();
}
