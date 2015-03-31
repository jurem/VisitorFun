package visitable.clean;

public abstract class Node implements Visitable {

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}