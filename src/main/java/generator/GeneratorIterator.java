package generator;

import java.util.Iterator;

/**
 * Defines a specific lind of iterator to used within generators.
 */
class GeneratorIterator<T> implements Iterator<T> {
    // ATTRIBUTES
    private final Generator<T> generator;


    // CONSTRUCTOR
    public GeneratorIterator(Generator<T> generator) {
        this.generator = generator;
    }


    // REQUEST
    @Override
    public boolean hasNext() {
        return generator.hasNext();
    }


    // COMMAND
    @Override
    public T next() {
        return generator.next();
    }
}
