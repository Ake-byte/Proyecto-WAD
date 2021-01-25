package com.proyectowad.app.view.pdf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.proyectowad.app.modelo.entidades.Producto;
import com.proyectowad.app.modelo.service.IUploadFileService;

@Component("verProducto")
public class ProductosPdfView extends AbstractPdfView{

	private IUploadFileService uploadFileService;
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		 
		Producto producto = (Producto) model.get("producto");
		
		//Image image = Image.getInstance(producto.getFoto());
		
		////uploadFileService.copy((MultipartFile) image);
		
		PdfPTable tabla = new PdfPTable(1);
		tabla.addCell("Datos del Producto");
		tabla.addCell(producto.getNombre());
		tabla.addCell(producto.getFoto());
		
		//image.setAlignment(450); 
		//tabla.addCell(uploadFileService.copy((MultipartFile) image));
		tabla.addCell(producto.getDescripcion());
		
		document.add(tabla);
	}

}
