package pl.orionproject.service;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import org.springframework.stereotype.Service;
import pl.orionproject.model.User;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PdfService {

    public void createPurchaseInvoice(User user) throws IOException {
        String filePath = "C:\\orion\\test.pdf";
        String imageSrc = "C:\\orion\\logo.png";


        String imFile = imageSrc;
        ImageData data = ImageDataFactory.create(imFile);
        Image image = new Image(data);

        PdfWriter pdfWriter = new PdfWriter(filePath);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);

        pdfDocument.setDefaultPageSize(PageSize.A4);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formatted = df.format(new Date());

        Paragraph storeName = new Paragraph("Wystawca: OrionStore");

        storeName.setFixedPosition(35, 815, 200);
        document.add(storeName);

        Paragraph date = new Paragraph("Data wystawienia: " + formatted);

        date.setFixedPosition(395, 815, 200);
        document.add(date);

        Paragraph vat = new Paragraph("FAKTURA VAT FA/06");
        vat.setFontSize(8);
        vat.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.TOP).setFontSize(15f).setBold();
        document.add(vat);

        float col = 280f;
        float[] columnWidth = {col, col};


        Table table = new Table(columnWidth).setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.setBackgroundColor(new DeviceRgb(255,255,255)).setFontColor(new DeviceRgb(226, 135, 67));
        table.addCell(new Cell().setBorder(Border.NO_BORDER).add(image).setTextAlignment(TextAlignment.CENTER)
                .setMarginTop(15f));
        table.addCell(new Cell().setBorder(Border.NO_BORDER).add(user.getFirstName() + " " + user.getLastName() + "\n" + user.getAddress() + "\n" + user.getPostalCode() + " " + user.getCity() + "\n" + user.getPhone())).setTextAlignment(TextAlignment.RIGHT)
                .setMarginTop(30f)
                .setMarginBottom(30f)
                .setFontSize(15f);
        float[] colWidth = {80, 300, 100, 80};
        Table products = new Table(colWidth);
        products.addCell(new Cell(0, 4).setBackgroundColor(Color.convertRgbToCmyk(new DeviceRgb(226, 135, 67)))
                .add("Twoje zamowione produkty:")
                .setBold().setTextAlignment(TextAlignment.LEFT));
        products.addCell(new Cell().add("Name"));
        products.addCell(new Cell().add("Name"));
        products.addCell(new Cell().add("Name"));
        products.addCell(new Cell().add("Name"));
        products.addCell(new Cell(0, 4).add("Razem do zaplaty: ")).setTextAlignment(TextAlignment.RIGHT).setBold();


        document.add(table);
        document.add(products);

        Paragraph foot = new Paragraph("Dziekujemy za zakup, zapraszamy ponownie.");
        foot.setFontSize(8);
        foot.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.BOTTOM);
        document.add(foot);

        document.close();
    }
}