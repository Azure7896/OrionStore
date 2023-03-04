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
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import org.springframework.stereotype.Service;
import pl.orionproject.model.OrderItem;
import pl.orionproject.model.User;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PdfService {

    public String createPurchaseInvoice(User user, List<OrderItem> orderItems) throws IOException {

        Long orderNumber = orderItems.get(0).getOrder().getId();

        String filePath = "C:\\orion\\FakturaVATZamowienieNr" + orderItems.get(0).getOrder().getId() + "Dla" + user.getLastName() + ".pdf";
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

        Paragraph storeName = new Paragraph("OrionStore Szymon Napora 5ION");

        storeName.setFixedPosition(35, 815, 200);
        document.add(storeName);

        Paragraph date = new Paragraph("Data wystawienia: " + formatted);

        date.setFixedPosition(395, 815, 200);
        document.add(date);

        Paragraph vat = new Paragraph("FAKTURA VAT FA/" + orderNumber);
        vat.setFontSize(8);
        vat.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.TOP).setFontSize(15f).setBold();
        document.add(vat);

        float col = 280f;
        float[] columnWidth = {col, col};

        image.setHorizontalAlignment(HorizontalAlignment.CENTER);


        Paragraph info = new Paragraph("OrionStore SP. Z.O.O" + "\n" + "Techniczna 12/45" + "\n" + "41-711, Ruda Slaska");

        info.setFixedPosition(35, 630, 200).setTextAlignment(TextAlignment.LEFT).setFontSize(15f);


        Table firstTable = new Table(columnWidth).setVerticalAlignment(VerticalAlignment.MIDDLE);
        firstTable.addCell(new Cell().setBorder(Border.NO_BORDER))
                .setMarginTop(15f);

        firstTable.addCell(new Cell().setBorder(Border.NO_BORDER))
                .setMarginTop(30f)
                .setMarginBottom(10f)
                .setMarginRight(10f)
                .setFontSize(15f);

        firstTable.addCell(new Cell().setBorder(Border.NO_BORDER)
                .setMarginTop(30f)
                .setMarginLeft(10f)
                .setFontSize(15f));


        Table table = new Table(columnWidth).setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.setBackgroundColor(new DeviceRgb(255, 255, 255));
        table.addCell(new Cell().setBorder(Border.NO_BORDER))
                .setMarginTop(15f);

        if (user.getVatNumber().equals("Brak") || user.getVatNumber().isEmpty() || user.getOrganisationName().equals("Brak") || user.getOrganisationName().isEmpty()) {
            table.addCell(new Cell().setBorder(Border.NO_BORDER).add(user.getFirstName() + " " + user.getLastName() + "\n" + user.getAddress() + "\n" +
                            user.getPostalCode() + " " + user.getCity() + "\n" + user.getPhone())).setTextAlignment(TextAlignment.RIGHT)
                    .setMarginBottom(30f)
                    .setFontSize(15f);
        } else {
            table.addCell(new Cell().setBorder(Border.NO_BORDER).add(user.getVatNumber() + "\n" + user.getOrganisationName() + "\n" + user.getFirstName() +
                            " " + user.getLastName() + "\n" + user.getAddress() + "\n" + user.getPostalCode() + " " + user.getCity() + "\n" + user.getPhone())).setTextAlignment(TextAlignment.RIGHT)
                    .setMarginBottom(30f)
                    .setFontSize(15f);
        }


        float[] colWidth = {80, 300, 100, 80};
        Table products = new Table(colWidth);
        products.addCell(new Cell(0, 4).setBackgroundColor(Color.convertRgbToCmyk(new DeviceRgb(226, 135, 67)))
                .add("Twoje zamowione produkty:")
                .setBold().setTextAlignment(TextAlignment.LEFT));

        int counter = 1;
        double total = 0;
        for (OrderItem orderItem : orderItems) {

            products.addCell(new Cell().add(String.valueOf(counter)));
            products.addCell(new Cell().add(orderItem.getItemName()));
            products.addCell(new Cell().add(orderItem.getQuantity() + " szt."));
            products.addCell(new Cell().add(String.format("%.2f", orderItem.getQuantity() * orderItem.getTotalPrice()) + "zl"));

            total += orderItem.getQuantity() * orderItem.getTotalPrice();
            counter++;
        }

        products.addCell(new Cell(0, 4).add("Razem do zaplaty: " + String.format("%.02f", total) + "zl")).setTextAlignment(TextAlignment.RIGHT).setBold();

        document.add(image);
        document.add(info);
        document.add(firstTable);
        document.add(table);
        document.add(products);

        Paragraph foot = new Paragraph("Dziekujemy za zakup, zapraszamy ponownie.");
        foot.setFontSize(8);
        foot.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.BOTTOM);
        document.add(foot);

        document.close();
        return filePath;
    }
}
