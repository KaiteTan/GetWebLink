package parser;

import org.htmlparser.Parser;
import org.htmlparser.NodeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.HtmlPage;

/** 
 * htmlparserȡ��һ��html�����������е����ӵ�ַ����������
 */

public class Testhtmlparser {
	public static void main(String[] args) throws Exception {

		String htmlcode = GetWebString.getWeb();
		Parser parser = Parser.createParser(htmlcode, "GBK");
		HtmlPage page = new HtmlPage(parser);// ����HtmlPage����HtmlPage(Parser parser)

		try {
			parser.visitAllNodesWith(page);// HtmlPage extends visitor,Apply the given visitor to the current page.
		} catch (ParserException e1) {
			e1 = null;
		}

		NodeList nodelist = page.getBody();// ���еĽڵ�
		NodeFilter filter = new TagNameFilter("A");
		nodelist = nodelist.extractAllNodesThatMatch(filter, true);

		for (int i = 0; i < nodelist.size(); i++) {
			LinkTag link = (LinkTag) nodelist.elementAt(i);
			System.out.println(link.getAttribute("href") + "\n");
			System.out.println(link.getStringText());

		}

	}

}
