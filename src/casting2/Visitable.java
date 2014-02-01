package casting2;


public interface Visitable {
}


abstract class Node implements Visitable {

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}


class Comment extends Node {

    public final String comment;

    public Comment(String comment) {
        this.comment = comment;
    }

}


abstract class Statement extends Node {
}


class Print extends Statement {

    public final String message;

    public Print(String message) {
        this.message = message;
    }

}


class PrintFancy extends Print {

    public PrintFancy(String message) {
        super(message);
    }

}


class Block extends Statement {

    public final Node first;
    public final Node second;

    public Block(Node first, Node second) {
        this.first = first;
        this.second = second;
    }

}
