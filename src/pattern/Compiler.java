package pattern;

import visitable.accept.*;

public class Compiler extends AbstractVisitor {

    private Node result;

    public Node result() {
        return result;
    }

    public Compiler(Node root) {
        super(root);
    }

    @Override
    public void visit(Comment comment) {
        result = comment;
    }

    @Override
    public void visit(Print print) {
        result = print;
    }

    @Override
    public void visit(PrintBold printBold) {
        result = new Block(new Print("**"), new Block(new Print(printBold.message), new Print("**")));
    }

    @Override
    public void visit(Block block) {
        boolean fc = block.first instanceof Comment;
        boolean sc = block.second instanceof Comment;
        if (fc && sc) { result = new Comment("Cannot compile this."); return; }
        if (fc) { block.second.accept(this); return; };
        if (sc) { block.first.accept(this); return; }
        block.first.accept(this);
        Node tempResult = result;
        block.second.accept(this);
        result = new Block(tempResult, result);
    }

}