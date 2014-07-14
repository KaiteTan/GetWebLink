package parser;

import org.htmlparser.Parser;
import org.htmlparser.beans.StringBean;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.parserapplications.StringExtractor;
import org.htmlparser.tags.BodyTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * 
 * ʹ��HtmlParserץȥ��ҳ����: Ҫץȥҳ����������ķ�������ʹ��StringBean. �����м�������ҳ�����ݵļ�������.
 * 
 * �ں���Ĵ����л���˵��. Htmlparser���л���һ��ʾ��StringExtractor �����и�ֱ�ӵõ����ݵķ���,
 * 
 * ����Ҳ��ʹ����StringBean . ����ֱ�ӽ���Parser��ÿ����ǩҲ���Ե�.
 * 
 */

public class GetContent {

	public void getContentUsingStringBean(String url) {

		StringBean sb = new StringBean();  //StringBean
		sb.setLinks(true); // �Ƿ���ʾwebҳ�������(Links)
		sb.setCollapse(true); // �����true�Ļ���һϵ�пհ��ַ���һ���ַ����.
		// Ϊ��ȡ��ҳ�����������һ��������������Ϊtrue , ���Ҫ����ҳ���ԭ�и�ʽ, �����ҳ��Ŀո����� ��������Ϊfalse
		sb.setReplaceNonBreakingSpaces(true);// If true regular space
		sb.setURL("http://www.blogjava.net/51AOP/archive/2006/07/19/59064.html");

		System.out.println("The Content is :\n" + sb.getStrings());

	}

	public void getContentUsingStringExtractor(String url, boolean link) {

		// StringExtractor�ڲ����ƺ������һ��.����һ�°�װ

		StringExtractor se = new StringExtractor(url);

		String text = null;

		try {

			text = se.extractStrings(link);

			System.out.println("The content is :\n" + text);

		} catch (ParserException e) {

			e.printStackTrace();

		}

	}

	public void getContentUsingParser(String url) {

		NodeList nl;

		try {

			Parser p = new Parser(url);

			nl = p.parse(new NodeClassFilter(BodyTag.class));

			BodyTag bt = (BodyTag) nl.elementAt(0);

			System.out.println(bt.toPlainTextString()); // ����ԭ�������ݸ�ʽ. ����js����

		} catch (ParserException e) {

			e.printStackTrace();

		}

	}

	/**
	 * 
	 * @param args
	 */

	public static void main(String[] args) {

		String url = "http://www.blogjava.net/51AOP/archive/2006/07/19/59064.html";

		// new GetContent().getContentUsingParser(url);

		// --------------------------------------------------

		new GetContent().getContentUsingStringBean(url);

	}

}
