package com.driagon.springboot.datajpa.app.view.xlsx;

import com.driagon.springboot.datajpa.app.models.entity.Factura;
import com.driagon.springboot.datajpa.app.models.entity.ItemFactura;
import org.apache.poi.ss.usermodel.*;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component("factura/ver.xlsx")
public class FacturaXlsxView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"factura_view.xlsx\"");
        Factura factura = (Factura) model.get("factura");
        Sheet sheet = workbook.createSheet("Factura Spring");
        MessageSourceAccessor mensajes = getMessageSourceAccessor();

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue(mensajes.getMessage("text.cliente.informacion"));

        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue(factura.getCliente().getNombre() + " " + factura.getCliente().getApellido());

        row = sheet.createRow(2);
        cell = row.createCell(0);
        cell.setCellValue(factura.getCliente().getEmail());

        sheet.createRow(4).createCell(0).setCellValue((mensajes.getMessage("text.factura.informacion")));
        sheet.createRow(5).createCell(0).setCellValue(mensajes.getMessage("text.campo.factura.folio") + ": " + factura.getId());
        sheet.createRow(6).createCell(0).setCellValue(mensajes.getMessage("text.campo.factura.descripcion") + ": " + factura.getDescripcion());
        sheet.createRow(7).createCell(0).setCellValue(mensajes.getMessage("text.campo.factura.fecha") + ": " + factura.getCreateAt());

        CellStyle theaderStyle = workbook.createCellStyle();
        theaderStyle.setBorderBottom(BorderStyle.MEDIUM);
        theaderStyle.setBorderTop(BorderStyle.MEDIUM);
        theaderStyle.setBorderRight(BorderStyle.MEDIUM);
        theaderStyle.setBorderLeft(BorderStyle.MEDIUM);
        theaderStyle.setFillForegroundColor(IndexedColors.GOLD.index);
        theaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle tbodyStyle = workbook.createCellStyle();
        tbodyStyle.setBorderBottom(BorderStyle.THIN);
        tbodyStyle.setBorderTop(BorderStyle.THIN);
        tbodyStyle.setBorderRight(BorderStyle.THIN);
        tbodyStyle.setBorderLeft(BorderStyle.THIN);

        Row header = sheet.createRow(9);
        header.createCell(0).setCellValue(mensajes.getMessage("text.campo.producto"));
        header.createCell(1).setCellValue(mensajes.getMessage("text.campo.producto.precio"));
        header.createCell(2).setCellValue(mensajes.getMessage("text.campo.producto.cantidad"));
        header.createCell(3).setCellValue(mensajes.getMessage("text.campo.producto.total"));

        header.getCell(0).setCellStyle(theaderStyle);
        header.getCell(1).setCellStyle(theaderStyle);
        header.getCell(2).setCellStyle(theaderStyle);
        header.getCell(3).setCellStyle(theaderStyle);

        int rownum = 10;
        for (ItemFactura item : factura.getItems()) {
            Row fila = sheet.createRow(rownum++);
            cell = fila.createCell(0);
            fila.createCell(0).setCellValue(item.getProducto().getNombre());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(1);
            fila.createCell(1).setCellValue(item.getProducto().getPrecio());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(2);
            fila.createCell(2).setCellValue(item.getCantidad());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(3);
            fila.createCell(3).setCellValue(item.calcularImporte());
            cell.setCellStyle(tbodyStyle);
        }

        Row filaTotal = sheet.createRow(rownum);
        cell = filaTotal.createCell(2);
        cell.setCellValue(mensajes.getMessage("text.campo.factura.gran-total") + ": ");
        cell.setCellStyle(tbodyStyle);

        cell = filaTotal.createCell(3);
        cell.setCellValue(factura.getTotal());
        cell.setCellStyle(tbodyStyle);
    }
}