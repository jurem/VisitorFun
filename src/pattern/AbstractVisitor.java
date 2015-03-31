package pattern;

import visitable.accept.*;

public abstract class AbstractVisitor implements Visitor {

    public final Node root;

    protected AbstractVisitor(Node root) {
        this.root = root;
    }

    public void go() {
        root.accept(this);
    }

    // ************ visits

    public abstract void visit(Comment comment);

    public abstract void visit(Print print);

    public abstract void visit(PrintBold printBold);

    public abstract void visit(Block block);

}
