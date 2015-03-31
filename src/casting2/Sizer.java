package casting2;

import visitable.clean.*;

public class Sizer extends AbstractVisitor {

    private int size;

    public int size() {
        return size;
    }

    public Sizer(Node root) {
        super(root);
    }

    @Override
    public void go() {
        size = 0;
        super.go();
    }

    @Override
    public void visit(Comment comment) {
    }

    @Override
    public void visit(Print print) {
        size += print.message.length();
    }

    @Override
    public void visit(PrintBold printBold) {
        size += printBold.message.length();
    }

    @Override
    public void visit(Block block) {
        visit(block.first);
        visit(block.second);
    }

}
