package courseonline4399.online.Util;

import com.itextpdf.text.*;
import courseonline4399.online.model.Course;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Component
public class ExportToPDF {

    public static void exportTableToPDF(List<Course> courses, String bankCode, String noiDung, String date,
                                        String nguoiMua, double tongTien) throws DocumentException, IOException {

        // Tạo tài liệu PDF
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("D:/YourOrder" +date+ ".pdf"));

        // Mở tài liệu
        document.open();

        // Thiết lập font chữ
        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Font contentFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

        // Tiêu đề và nội dung
        Paragraph title = new Paragraph("Your Order", titleFont);
        Paragraph bankInfo = new Paragraph("Ngân hàng giao dịch: " + bankCode, contentFont);
        Paragraph paymentInfo = new Paragraph("Nội dung thanh toán: " + noiDung, contentFont);
        Paragraph paymentTime = new Paragraph("Thời gian thanh toán: " + date, contentFont);
        Paragraph buyerInfo = new Paragraph("Người mua: " + nguoiMua, contentFont);


        Paragraph totalPrice = new Paragraph("Tổng tiền: " + tongTien + " VNĐ");

        // Căn giữa tiêu đề
        title.setAlignment(Element.ALIGN_CENTER);

        // Tạo bảng
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Định dạng cột tiêu đề
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setPadding(5);

        // Thêm tiêu đề cột
        cell.setPhrase(new Phrase("Tên sản phẩm", contentFont));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Giá sản phẩm", contentFont));
        table.addCell(cell);

        // Thêm dữ liệu vào bảng
        for (Course c : courses) {
            table.addCell(new Phrase(c.getCoursename(), contentFont));
            table.addCell(new Phrase(String.valueOf(c.getPrice()), contentFont));
        }

        // Thêm tiêu đề và nội dung vào tài liệu
        document.add(title);
        document.add(bankInfo);
        document.add(paymentInfo);
        document.add(paymentTime);
        document.add(buyerInfo);
        document.add(totalPrice);

        // Thêm bảng vào tài liệu
        document.add(new Paragraph("Danh sách sản phẩm:", contentFont));
        document.add(table);

        // Đóng tài liệu
        document.close();

        System.out.println("File PDF đã được xuất thành công!");
    }

}
