package MyLib;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFReport {
	public String pdfName;

	public PDFReport(String DateL, ArrayList<String> names, ArrayList<String> counts, ArrayList<String> prices,
			String total) {

		pdfName = "Report_" + System.currentTimeMillis();
		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Reports/" + pdfName + ".pdf"));
			document.open();

			// Absolute position
			PdfContentByte over = writer.getDirectContent();
			over.saveState();
			BaseFont bf = BaseFont.createFont();
			over.beginText();
			over.setTextRenderingMode(PdfContentByte.TEXT_RENDER_MODE_FILL_STROKE);
			over.setLineWidth(1.5f);
			over.setRGBColorFill(0x00, 0x00, 0x00);
			over.setFontAndSize(bf, 30);
			over.setTextRenderingMode(PdfContentByte.TEXT_RENDER_MODE_FILL);

			// Shop Name
			over.setTextMatrix(140, 800);
			over.showText("Koungmon Supermarket");

			// Sale Invoice
			over.setFontAndSize(bf, 20);
			over.setTextMatrix(220, 760);
			over.showText("SALES INVOICE");

			// Stars
			over.setTextMatrix(180, 720);
			over.showText("* * * * * * * * * * * * * * * * *");

			// Printed Date
			over.setFontAndSize(bf, 15);
			over.setTextMatrix(430, 700);
			over.showText("Date  " + DateL);

			// table//////////
			over.setTextMatrix(50, 660);
			over.showText("No.");

			over.setTextMatrix(90, 660);
			over.showText("Description");

			over.setTextMatrix(290, 660);
			over.showText("Qty");

			over.setTextMatrix(330, 660);
			over.showText("Unit");

			over.setTextMatrix(390, 660);
			over.showText("Price");

			over.setTextMatrix(480, 660);
			over.showText("Amount");

			over.setFontAndSize(bf, 12);
			// Data Loop

			int yinit = 630;
			int countNo = 1;
			for (int i = 0; i < names.size(); i++) {

				over.setTextMatrix(50, yinit);
				over.showText("" + countNo);

				over.setTextMatrix(90, yinit);
				over.showText("" + names.get(i));

				over.setTextMatrix(290, yinit);
				over.showText("" + counts.get(i));

				over.setTextMatrix(330, yinit);
				over.showText("UPC");

				over.setTextMatrix(390, yinit);
				over.showText("" + prices.get(i));

				double res = Double.parseDouble(prices.get(i)) * Integer.parseInt(counts.get(i));
				over.setTextMatrix(480, yinit);
				over.showText("" + res);

				yinit = yinit - 20;
				countNo++;
			}
			// Line
			over.setTextMatrix(50, yinit - 10);
			over.showText(
					"-----------------------------------------------------------------------------------------------------------------------");

			// Total Price
			over.setFontAndSize(bf, 15);
			over.setTextMatrix(390, yinit - 40);
			over.showText("Total");
			over.setTextMatrix(480, yinit - 40);
			over.showText(total);

			///////////
			over.endText();
			over.restoreState();
			document.close();

			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
