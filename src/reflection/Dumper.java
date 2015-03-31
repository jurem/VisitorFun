package reflection;

import visitable.clean.*;

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
        visit(block.first);
        System.out.print(',');
        visit(block.second);
    }

}