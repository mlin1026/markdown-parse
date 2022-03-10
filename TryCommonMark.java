import java.util.ArrayList;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

class LinkCountVisitor extends AbstractVisitor {
    ArrayList<String> linkCount;

    @Override
    public void visit(Link link) {
        // This is called for all Text nodes. Override other visit methods for other node types.

        // Count words (this is just an example, don't actually do it this way for various reasons).
        linkCount.add(link.toString());

        // Descend into children (could be omitted in this case because Text nodes don't have children).
        visitChildren(link);
    }
}
class WordCountVisitor extends AbstractVisitor {
    int wordCount = 0;
    ArrayList<String> linkCount;

    @Override
    public void visit(Text text) {
        // This is called for all Text nodes. Override other visit methods for other node types.

        // Count words (this is just an example, don't actually do it this way for various reasons).
        wordCount += text.getLiteral().split("\\W+").length;

        // Descend into children (could be omitted in this case because Text nodes don't have children).
        visitChildren(text);
    }
}

class TryCommonMark {
    public static void main(String[] args) {

        Parser parser = Parser.builder().build();
        Node document = parser.parse("This is *Sparta*");
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        renderer.render(document);  // "<p>This is <em>Sparta</em></p>\n"
        //Example\n=======\n\nSome more text
        Node node = parser.parse("[a](google.com)");
        LinkCountVisitor visitor = new LinkCountVisitor();
        node.accept(visitor);
        System.out.println(visitor.linkCount);
          // 1
    }
}