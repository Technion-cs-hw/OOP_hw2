package OOP2.Provided;

import java.util.Iterator;

public interface StatusIterator extends Iterator<Status>{


    @Override
    default boolean hasNext() {
        return false;
    }

    @Override
    default Status next() {
        return null;
    }

    @Override
    default void remove() {
        Iterator.super.remove();
    }
}
