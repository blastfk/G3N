package util;

/**
 * Defines a proposition mathematically speaking.
 * Aims at telling if a given value is verifying a specific boolean condition.
 */
public abstract class Condition<T> {
    // REQUESTS
    /**
     * Indicates if this specific value verify this condition
     */
    abstract boolean check(T value);


    // PRIMITIVES TYPES IMPLEMENTATION
    abstract class Int extends Condition<java.lang.Integer> {
        @Override
        boolean check(java.lang.Integer value) {
            return check((int) value);
        }
        abstract boolean check(int value);
    }
    abstract class Double extends Condition<java.lang.Double> {
        @Override
        boolean check(java.lang.Double value) {
            return check((double) value);
        }
        abstract boolean check(double value);
    }
    abstract class Float extends Condition<java.lang.Float> {
        @Override
        boolean check(java.lang.Float value) {
            return check((float) value);
        }
        abstract boolean check(float value);
    }
    abstract class Long extends Condition<java.lang.Long> {
        @Override
        boolean check(java.lang.Long value) {
            return check((long) value);
        }
        abstract boolean check(long value);
    }
}
