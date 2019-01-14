package tools.flexmark;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.annotation.processing.Filer;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;

import com.alibaba.druid.filter.encoding.CharsetConvert;
import com.openhtmltopdf.DOMBuilder;
import com.openhtmltopdf.bidi.support.ICUBidiReorderer;
import com.openhtmltopdf.bidi.support.ICUBidiSplitter;
import com.openhtmltopdf.extend.FSSupplier;
import com.openhtmltopdf.outputdevice.helper.BaseRendererBuilder.FontStyle;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.pdf.converter.PdfConverterExtension;
import com.vladsch.flexmark.profiles.pegdown.Extensions;
import com.vladsch.flexmark.profiles.pegdown.PegdownOptionsAdapter;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.options.MutableDataHolder;
import com.vladsch.flexmark.util.options.MutableDataSet;

public class Demo {
	static final MutableDataHolder OPTIONS = PegdownOptionsAdapter
			.flexmarkOptions(Extensions.ALL & ~(Extensions.ANCHORLINKS | Extensions.EXTANCHORLINKS_WRAP)).toMutable();

	static String getResourceFileContent(String resourcePath) {
		StringWriter writer = new StringWriter();
		getResourceFileContent(writer, resourcePath);
		return writer.toString();
	}

	private static void getResourceFileContent(final StringWriter writer, final String resourcePath) {
		InputStream inputStream = Demo.class.getResourceAsStream(resourcePath);
		try {
			IOUtils.copy(inputStream, writer, "UTF-8");
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String stringToUnicode(String s) {
		try {
			StringBuffer out = new StringBuffer("");
			//直接获取字符串的unicode二进制
			byte[] bytes = s.getBytes("unicode");
			//然后将其byte转换成对应的16进制表示即可
			for (int i = 0; i < bytes.length - 1; i += 2) {
				out.append("\\u");
				String str = Integer.toHexString(bytes[i + 1] & 0xff);
				for (int j = str.length(); j < 2; j++) {
					out.append("0");
				}
				String str1 = Integer.toHexString(bytes[i] & 0xff);
				out.append(str1);
				out.append(str);
			}
			return out.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) throws IOException {
		MutableDataSet options = new MutableDataSet();

		// uncomment to set optional extensions
		options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create(), StrikethroughExtension.create()));

		// uncomment to convert soft-breaks to hard breaks
		options.set(HtmlRenderer.SOFT_BREAK, "<br />\n");

		Parser parser = Parser.builder(options).build();
		HtmlRenderer renderer = HtmlRenderer.builder(options).build();

		// You can re-use parser and renderer instances
		Node document = parser.parse("This is *Sparta*");
		String html = renderer.render(document); // "<p>This is <em>Sparta</em></p>\n"
		System.out.println(html);
		
		
		final String pegdown = "#Heading\n" +
                "-----\n" +
                "paragraph text \n" +
                "lazy continuation\n" +
                "\n" +
                "* list item\n" +
                "    > block quote\n" +
                "    lazy continuation\n" +
                "\n" +
                "~~~info\n" +
                "   with uneven indent\n" +
                "      with uneven indent\n" +
                "indented code\n" +
                "~~~ \n" +
                "\n" +
                "       with uneven indent\n" +
                "          with uneven indent\n" +
                "    indented code\n" +
                "1. numbered item 1   \n" +
                "1. numbered item 2   \n" +
                "1. numbered item 3   \n" +
                "    - bullet item 1   \n" +
                "    - bullet item 2   \n" +
                "    - bullet item 3   \n" +
                "        1. numbered sub-item 1   \n" +
                "        1. numbered sub-item 2   \n" +
                "        1. numbered sub-item 3   \n" +
                "    \n" +
                "    ~~~info\n" +
                "       with uneven indent\n" +
                "          with uneven indent\n" +
                "    indented code\n" +
                "    ~~~ \n" +
                "    \n" +
                "           with uneven indent\n" +
                "              with uneven indent\n" +
                "        indented code\n" +
                "";
        System.out.println("pegdown\n");
        System.out.println(pegdown);
        
        BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\Users\\h15039.H3C\\Desktop\\demo.md")));
        StringBuilder builder = new StringBuilder();
        while(reader.ready()) {
        	String line = reader.readLine();
        	builder.append(line).append("\n");
        }
        reader.close();
        
        String str = builder.toString();
        System.out.println(str);
        
        final Parser PARSER = Parser.builder(OPTIONS).build();
        final HtmlRenderer RENDERER = HtmlRenderer.builder(OPTIONS).build();

//        Node document1 = PARSER.parse(pegdown);
        String s = new String(str.getBytes("utf-8"),"gbk");
        Node document1 = PARSER.parse(str);
        String html1 = RENDERER.render(document1);

        File f = new File("F:\\Workspace\\demo.pdf");
        if(!f.exists()) {
        	try {
				f.createNewFile();
				System.out.println(f.getAbsolutePath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
//        PdfConverterExtension.exportToPdf(new FileOutputStream(f), html1,"", OPTIONS);
        exportToPdf(new FileOutputStream(f), html1,"", OPTIONS.get(PdfConverterExtension.DEFAULT_TEXT_DIRECTION));

	}
	
	public static void exportToPdf(final OutputStream os, final String html, final String url, final PdfRendererBuilder.TextDirection defaultTextDirection) {
        try {
            // There are more options on the builder than shown below.
            PdfRendererBuilder builder = new PdfRendererBuilder();
//            builder.useFont(new File("C:\\Users\\h15039.H3C\\Desktop\\simsun.ttc"), "simsun");
            builder.useFont(new File("C:\\Users\\h15039.H3C\\Desktop\\simkai.ttf"), "simkai");

            if (defaultTextDirection != null) {
                builder.useUnicodeBidiSplitter(new ICUBidiSplitter.ICUBidiSplitterFactory());
                builder.useUnicodeBidiReorderer(new ICUBidiReorderer());
                builder.defaultTextDirection(defaultTextDirection); // OR RTL
            }

            org.jsoup.nodes.Document doc;
            doc = Jsoup.parse(html);

            Document dom = DOMBuilder.jsoup2DOM(doc);
            builder.withW3cDocument(dom, url);
            builder.toStream(os);
            builder.run();
        } catch (Exception e) {
            e.printStackTrace();
            // LOG exception
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                // swallow
            }
        }
    }
}
