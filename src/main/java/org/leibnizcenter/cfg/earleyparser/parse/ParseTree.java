
package org.leibnizcenter.cfg.earleyparser.parse;

import org.leibnizcenter.cfg.Grammar;
import org.leibnizcenter.cfg.category.Category;
import org.leibnizcenter.cfg.earleyparser.chart.State;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;

/**
 * A parse tree that represents the derivation of a string based on the
 * rules in a {@link Grammar}. Parse trees recursively contain
 * {@link #getChildren() other parse trees}, so they can be iterated through to
 * find the entire derivation of a category. A parse tree can also be
 * traversed upward by calling {@link #getParent()} for each successive parse
 * tree until it returns <code>null</code>.
 * <p>
 * Parse trees are essentially partial views of a {@link Chart} from a
 * given {@link State} or {@link Category}. They represent the completed
 * category at a given string index and origin position. The special
 * {@link Category#START} category is not included in a parse tree at the root
 * (only category that are actually specified in the corresponding grammar
 * are represented).
 *
 * @see Parse#getParseTreesFor(Category, int, int)
 */
public class ParseTree {
    Category node;
    ParseTree parent;
    ParseTree[] children = null;

    /**
     * Creates a new parse tree with the specified category and parent parse
     * tree.
     *
     * @see #ParseTree(Category, ParseTree, ParseTree[])
     */
    public ParseTree(Category node, ParseTree parent) {
        this(node, parent, null);
    }

    /**
     * Creates a new parse tree with the specified category, parent, and
     * child trees.
     *
     * @param node     The category of the {@link #getNode() node} of this parse
     *                 tree.
     * @param parent   This parse tree's parent tree, or <code>null</code> if
     *                 this parse tree is the root node.
     * @param children The list of children of this parse tree, in their linear
     *                 order.
     */
    public ParseTree(Category node, ParseTree parent, ParseTree[] children) {
        this.node = node;
        this.parent = parent;
        this.children = children;
    }

    /**
     * Creates a parse tree based on the specified edge that is the root of the
     * resulting parse tree.
     *
     * @param edge The edge that is to be at the root of the parse tree.
     * @return The result of calling {@link #newParseTree(State, ParseTree)} with
     * <code>null</code> as the argument for the parent parse tree.
     * @see #newParseTree(State, ParseTree)
     */
    public static ParseTree newParseTree(State edge) {
        return ParseTree.newParseTree(edge, null);
    }

    /**
     * Creates a new parse tree based on the specified edge and parent tree.
     *
     * @param edge   The edge to use to create a parse tree. For a parse tree
     *               that is the root, this should be <code>null</code>.
     * @param parent The parent tree of the new parse tree.
     * @return A new parse tree whose {@link #getNode() node} is the
     * specified edge's dotted rule's left side and whose children are based
     * on the {@link State#getBases() bases} of the specified edge.
     */
    public static ParseTree newParseTree(State edge, ParseTree parent) {
//        State e;
//        ParseTree parentTree;
//
//        if (edge.rule.left.equals(START)) { // first child if START
//            e = edge.bases.iterator().next();
//            parentTree = null;
//        } else {
//            e = edge;
//            parentTree = (parent != null && parent.node.equals(START))
//                    ? null : parent;
//        }
//
//        Rule dr = e.rule;
//        ParseTree newTree;
//
//        Optional<Category> activeCategory = e.getActiveCategory();
//        if (!activeCategory.isPresent()) { // basis from a completion?
//            int basisCount = e.bases.size();
//            newTree = new ParseTree(dr.left, parentTree, (basisCount == 0) ? null : new ParseTree[basisCount]);
//
//            if (basisCount > 0) {
//                int i = 0;
//                for (State base : e.bases) {
//                    newTree.children[i] = ParseTree.newParseTree(base, newTree);
//                    i++;
//                }
//            }
//        } else { // from a scan
//            newTree = new ParseTree(activeCategory.get(), parentTree, null);
//        }
//
//        return newTree;
        throw new NotImplementedException();
    }

    /**
     * Gets the node category of this parse tree.
     *
     * @return <code>NP</code> for a subtree <code>NP -> Det N</code>.
     */
    public Category getNode() {
        return node;
    }

    /**
     * Gets the parent parse tree, if any.
     *
     * @return A parse tree containing (for example) <code>S -> NP VP</code>
     * if this parse tree's {@link #getNode() node} is <code>NP</code> and is
     * one of the children of <code>S</code>. If this parse tree is the root
     * node in a series of parse trees, returns <code>null</code>.
     */
    public ParseTree getParent() {
        return parent;
    }

    /**
     * Gets the child parse trees of this parse tree, retaining their linear
     * ordering.
     *
     * @return For a subtree <code>NP -> Det N</code>, returns an array
     * that contains parse trees whose {@link #getNode() node} is
     * <code>Det, N</code> in that order, or <code>null</code> if this parse
     * tree has no children.
     */
    public ParseTree[] getChildren() {
        return children;
    }

    /**
     * Tests whether this parse tree is equal to another by comparing its
     * node, parent, and child parse trees.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ParseTree) {
            ParseTree op = (ParseTree) obj;

            return (node.equals(op.node)
                    && ((parent == null && op.parent == null)
                    || parent.node.equals(op.parent.node))
                    && ((children == null && op.children == null)
                    || Arrays.equals(children, op.children)));
        }

        return false;
    }

    /**
     * Computes a hash code for this parse tree based on its underlying edge
     * and child parse trees.
     */
    @Override
    public int hashCode() {
        int hash = (31 * node.hashCode());
        if (parent != null) {
            hash *= (17 * parent.node.hashCode());
        }
        if (children != null) {
            hash *= Arrays.hashCode(children);
        }

        return hash;
    }

    /**
     * Gets a string representation of this parse tree.
     *
     * @return For the string &quot;the boy left&quot;, possibly something like:
     * <blockquote><code>[S[NP[Det[the]][N[boy]]][VP[left]]]</code></blockquote>
     * (The actual string would depend on the grammar rules in effect for the
     * parse).
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(node.toString());

        // recursively append children
        if (children != null) {
            for (ParseTree child : children) {
                sb.append(child.toString());
            }
        }

        sb.append(']');

        return sb.toString();
    }

}
