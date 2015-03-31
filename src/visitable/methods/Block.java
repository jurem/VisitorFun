package visitable.methods;

public class Block extends Statement {

    public final Node first;
    public final Node second;

    public Block(Node first, Node second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public void print(int indent) {
        printIndent(indent, "begin");
        first.print(indent + 1);
        second.print(indent + 1);
        printIndent(indent, "end");
    }

    @Override
    public void dump() {
        System.out.print(this + ":");
        first.dump();
        System.out.print(',');
        second.dump();
    }

    @Override
    public void exec() {
        first.exec();
        second.exec();
    }

    @Override
    public int size() {
        return first.size() + second.size();
    }

    @Override
    public Node compile() {
        boolean fc = first instanceof Comment;
        boolean sc = second instanceof Comment;
        if (fc && sc) return new Comment("Cannot compile this.");
        if (fc) return second.compile();
        if (sc) return first.compile();
        return new Block(first.compile(), second.compile());
    }

}
