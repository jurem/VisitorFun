package casting2;

import visitable.clean.*;

public abstract class AbstractVisitor implements Visitor {

    public final Node root;

    protected AbstractVisitor(Node root) {
        this.root = root;
    }

    public void go() {
        visit(root);
    }

    public void visit(Node node) {
        if (node instanceof Comment)
            visit((Comment)node);
        else if (node instanceof Print && !(node instanceof PrintBold))
            visit((Print)node);
        else if (node instanceof PrintBold)
            visit((PrintBold)node);
        else if (node instanceof Block)
            visit((Block)node);
        else {
            System.err.println("ERR: no match for node.");
            System.exit(42);
        }
    }

    // ************ visits

    public abstract void visit(Comment comment);

    public abstract void visit(Print print);

    public abstract void visit(PrintBold printBold);

    public abstract void visit(Block block);

}
