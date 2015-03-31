package reflection;

import visitable.clean.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class AbstractVisitor implements Visitor {

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

    public abstract void visit(PrintBold printBold);

    public abstract void visit(Block block);

}
