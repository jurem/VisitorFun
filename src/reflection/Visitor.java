package reflection;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface Visitor {

    void visit(Comment comment);

    void visit(Print print);

    void visit(PrintFancy printFancy);

    void visit(Block block);

}


abstract class AbstractVisitor implements Visitor {

    public final Node root;

    protected AbstractVisitor(Node root) {
        this.root = root;
    }

    public void go() {
        visit(root);
    }

    protected Method findVisitMethod(Node node) {
        Method method = null;
        Class visitorClass = getClass();
        Class nodeClass = node.getClass();
        // DFS dispatcher for visit() methods
        do {
            try {
                method = visitorClass.getDeclaredMethod("visit", new Class[]{nodeClass});
            } catch (NoSuchMethodException e) {}
            nodeClass = nodeClass.getSuperclass();
        } while (nodeClass != null && method == null);
        return method;
    }

    public void visit(Node node) {
        Method method = findVisitMethod(node);
        if (method == null) return;
        try {
            method.invoke(this, node);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    // ************ visits

    public abstract void visit(Comment comment);

    public abstract void visit(Print print);

    public abstract void visit(PrintFancy printFancy);

    public abstract void visit(Block block);

}


class Printer extends AbstractVisitor {

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
    public void visit(PrintFancy printFancy) {
        printIndent("printFancy '" + printFancy.message + "'");
    }

    @Override
    public void visit(Block block) {
        printIndent("begin");
        indent++;
        visit(block.first);
        visit(block.second);
        indent--;
        printIndent("end");
    }

}


class Dumper extends AbstractVisitor {

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
    public void visit(PrintFancy printFancy) {
        System.out.print(printFancy);
    }

    @Override
    public void visit(Block block) {
        System.out.print(block + ":");
        visit(block.first);
        System.out.print(',');
        visit(block.second);
    }

}


class Executor extends AbstractVisitor {

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
    public void visit(PrintFancy printFancy) {
        System.out.println("**" + printFancy.message + "**");
    }

    @Override
    public void visit(Block block) {
        visit(block.first);
        visit(block.second);
    }

}


class Sizer extends AbstractVisitor {

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
    public void visit(PrintFancy printFancy) {
        size += printFancy.message.length();
    }

    @Override
    public void visit(Block block) {
        visit(block.first);
        visit(block.second);
    }

}


class Compiler extends AbstractVisitor {

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
    public void visit(PrintFancy printFancy) {
        result = new Block(new Print("**"), new Block(new Print(printFancy.message), new Print("**")));
    }

    @Override
    public void visit(Block block) {
        boolean fc = block.first instanceof Comment;
        boolean sc = block.second instanceof Comment;
        if (fc && sc) { result = new Comment("Cannot compile this."); return; }
        if (fc) { visit(block.second); return; };
        if (sc) { visit(block.first); return; }
        visit(block.first);
        Node tempResult = result;
        visit(block.second);
        result = new Block(tempResult, result);
    }

}
