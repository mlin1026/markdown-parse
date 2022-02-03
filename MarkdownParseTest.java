import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParseTest {
    @Test
    public void addition() {
    assertEquals(2, 1 + 1);
    }
    
    @Test
    public void getLinks() throws IOException {
        Path fileName = Path.of("test-file.md");
	    String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals("https://something.com", links.get(0));
        assertEquals("some-page.html", links.get(1));
    }
    @Test
    public void getLinks2() throws IOException {
        Path fileName = Path.of("breaking-test.md");
        ArrayList<String> expect = new ArrayList<>();
        expect.add("www.gooog()le.com");
	    String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(expect, links);
    }

    @Test
    public void getLinks3() throws IOException {
        Path fileName = Path.of("breaking-test_2.md");
	    String contents = Files.readString(fileName);
        ArrayList<String> result = MarkdownParse.getLinks(contents);;
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(result, links);
    }

    @Test
    public void getLinks4() throws IOException {
        Path fileName = Path.of("breaking-test_3.md");
	    String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(0, links.size());
    }
    @Test
    public void getLinks5() throws IOException {
        Path fileName = Path.of("breaking-test4.md");
	    String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals("https://something.com", links.get(0));
        assertEquals("some-page.html", links.get(1));
    }
}

