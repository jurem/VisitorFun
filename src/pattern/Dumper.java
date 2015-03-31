package pattern;

import visitable.accept.*;

public class Dumper extends AbstractVisitor {

    public Dumper(Node root) {
        super(root);
    }

    @Override
    public void go() {
        super.go();
        System.out.println();
    }

    @Override
    public void visit(Comment comment) {
        System.out.print(comment);
    }

    @Override
    public void visit(Print print) {
        System.out.print(print);
    }

    @Override
    public void visit(PrintBold printBold) {
        System.out.print(printBold);
    }

    @Override
    public void visit(Block block) {
        System.out.print(block + ":");
        block.first.accept(this);
        System.out.print(',');
        block.second.accept(this);
    }

}
