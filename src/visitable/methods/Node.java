package visitable.methods;

public abstract class Node implements Visitable {

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    void printIndent(int indent, String msg) {
        for (int i = 0; i < indent; i++)
            System.out.print("   ");
        System.out.println(msg);
    }

    public abstract void print(int indent);

    public void dump() {
        System.out.print(this);
    }

    public abstract void exec();

    public abstract int size();

    public Node compile() {
        return this;
    }

}
