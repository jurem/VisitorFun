package pattern;

import visitable.accept.*;

public class Printer extends AbstractVisitor {

    private int indent;

    public Printer(Node root) {
        super(root);
    }

    protected void printIndent(String msg) {
        for (int i = 0; i < indent; i++)
            System.out.print("   ");
        System.out.println(msg);
    }

    @Override
    public void visit(Comment comment) {
        printIndent("// " + comment.comment);
    }

    @Override
    public void visit(Print print) {
        printIndent("print '" + print.message + "'");
    }

    @Override
    public void visit(PrintBold printBold) {
        printIndent("printBold '" + printBold.message + "'");
    }

    @Override
    public void visit(Block block) {
        printIndent("begin");
        indent++;
        block.first.accept(this);
        block.second.accept(this);
        indent--;
        printIndent("end");
    }

}
