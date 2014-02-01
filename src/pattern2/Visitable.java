package pattern2;


public interface Visitable {

    void accept(Visitor visitor);

}


abstract class Node implements Visitable {

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public abstract void accept(Visitor visitor);

}


class Comment extends Node {

    public final String comment;

    public Comment(String comment) {
        this.comment = comment;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
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
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}


class PrintFancy extends Print {

    public PrintFancy(String message) {
        super(message);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
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
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
