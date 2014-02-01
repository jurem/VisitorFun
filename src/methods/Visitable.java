package methods;


public interface Visitable {

    void print(int indent);

    void dump();

    void exec();

    int size();

    Node compile();

}


abstract class Node implements Visitable {

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    protected void printIndent(int indent, String msg) {
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


class Comment extends Node {

    public final String comment;

    public Comment(String comment) {
        this.comment = comment;
    }

    @Override
    public void print(int indent) {
        printIndent(indent, "// " + comment);
    }

    @Override
    public void exec() {
    }

    @Override
    public int size() {
        return 0;
    }

}


abstract class Statement extends Node {
}


class Print extends Statement {

    public final String message;

    public Print(String message) {
        this.message = message;
    }

    @Override
    public void print(int indent) {
        printIndent(indent, "print '" + message + "'");
    }

    @Override
    public void exec() {
        System.out.println(message);
    }

    @Override
    public int size() {
        return message.length();
    }

}


class PrintFancy extends Print {

    public PrintFancy(String message) {
        super(message);
    }

    @Override
    public void print(int indent) {
        printIndent(indent, "printFancy '" + message + "'");
    }

    @Override
    public void exec() {
        System.out.println("**" + message + "**");
    }

    @Override
    public Node compile() {
        return new Block(new Print("**"), new Block(new Print(message), new Print("**")));
    }

}


class Block extends Statement {

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
