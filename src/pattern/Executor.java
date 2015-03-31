package pattern;

import visitable.accept.*;

public class Executor extends AbstractVisitor {

    public Executor(Node root) {
        super(root);
    }

    @Override
    public void visit(Comment comment) {
    }

    @Override
    public void visit(Print print) {
        System.out.println(print.message);
    }

    @Override
    public void visit(PrintBold printBold) {
        System.out.println("**" + printBold.message + "**");
    }

    @Override
    public void visit(Block block) {
        block.first.accept(this);
        block.second.accept(this);
    }

}
