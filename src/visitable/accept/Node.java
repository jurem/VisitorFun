package visitable.accept;

public abstract class Node implements Visitable {

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public abstract void accept(Visitor visitor);

}
