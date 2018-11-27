package com.biblioteca.pdf;


import java.awt.Color;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.biblioteca.entity.AutorCybertesisDetalle;
import com.biblioteca.entity.AutorizCybertesis;
import com.biblioteca.qrcode.ImageService;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfDocument;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

//import com.itextpdf.text.Document;

@Component("reportePDF")
public class ReportePdfView extends AbstractPdfView{

	private static final Log LOG = LogFactory.getLog(ReportePdfView.class);
	
	@Autowired
	ImageService imageService;
	
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
		LOG.info("METHOD: buildPdfDocument()==>INICIO<==");
		
		AutorizCybertesis autorizacion = (AutorizCybertesis) model.get("Autorizacion");
		LOG.info("METHOD: buildPdfDocument()=>"+ autorizacion.toString());				
		
		BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		
		String rutaReport = request.getSession().getServletContext().getRealPath("resources//static//imgs");		
		//String rutaReport = request.getSession().getServletContext().getRealPath("WEB-INF//classes//static//imgs");	
		
		URL rutaEscudoUCSM=getClass().getResource("/static/imgs/Escudo_UCSM_OK.png");			
		LOG.info("METHOD: buildPdfDocument() rutaEscudoUCSM:"+ rutaEscudoUCSM.getPath());
		
		Image img = Image.getInstance(rutaEscudoUCSM.getPath());
        //Image img = Image.getInstance(bufeBufferedImage, null);
		img.setAbsolutePosition(0, 725);
		img.scaleToFit(100, 110);
        document.add(img);
        
        URL rutaLogoBiblio=getClass().getResource("/static/imgs/logo_biblioteca.png");	
        Image logoBiblio = Image.getInstance(rutaLogoBiblio.getPath());        
        logoBiblio.setAbsolutePosition(480, 735);
        logoBiblio.scaleToFit(100, 110);
        document.add(logoBiblio);
        
		//Color verde=new Color(7,90,65);
		//Font fuente=new Font(bf, 22f,Font.BOLD,verde);
        Font fuente=new Font(bf, 22f,Font.BOLD);
		PdfPCell cell= null;			
		cell=new PdfPCell(new Phrase("Universidad Católica de Santa María",fuente));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell.setBorder(Rectangle.NO_BORDER);
		//cell.setBackgroundColor(new Color(255,255,45));
		PdfPTable tablaPrincipal=new PdfPTable(1);
		tablaPrincipal.addCell(cell);
		tablaPrincipal.setSpacingAfter(5);
		document.add(tablaPrincipal);
		
		Font SubTitulo=new Font(bf, 18f,Font.BOLD);
		cell=new PdfPCell(new Phrase("Centro de Información y Bibliotecas",SubTitulo));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell.setBorder(Rectangle.NO_BORDER);
		PdfPTable tablaSubtitulo=new PdfPTable(1);
		tablaSubtitulo.addCell(cell);
		tablaSubtitulo.setSpacingAfter(15);
		
		document.add(tablaSubtitulo);
		
		Font SubTitulo2=new Font(bf, 12f,Font.BOLD);
		cell=new PdfPCell(new Phrase("Autorización para Publicación de Trabajo de Investigación y/o Tesis",SubTitulo2));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell.setBorder(Rectangle.NO_BORDER);		
		PdfPTable tablaSubtitulo2=new PdfPTable(1);
		tablaSubtitulo2.setWidthPercentage(85);
		tablaSubtitulo2.addCell(cell);
		tablaSubtitulo2.setSpacingAfter(5);			
		document.add(tablaSubtitulo2);
		
		Font parrafo=new Font(bf, 10f);
		//Paragraph parra=new Paragraph("Mediante el presente documento autorizó al Centro de Información y Bibliotecas de la Universidad Católica de Santa María, para que publique la versión");
		Phrase frase = new Phrase("Mediante el presente documento autorizó al Centro de Información y Bibliotecas de la Universidad Católica de Santa María, para que publique la versión " + 
				"electrónica de mi trabajo de investigación y/o tesis por internet o cualquier medio de transmisión, sin fines de lucro.",parrafo);
				
		cell=new PdfPCell(frase);
		cell.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
		cell.setBorder(Rectangle.NO_BORDER);	
		cell.setLeading(1.2f, 1.2f);
		PdfPTable tbl_parrafo=new PdfPTable(1);
		tbl_parrafo.addCell(cell);
		tbl_parrafo.setWidthPercentage(100);
		tbl_parrafo.setSpacingAfter(25);		
		document.add(tbl_parrafo);
		
		Font Fdescriptor=new Font(bf, 12f,Font.BOLD);		
		Font Fvalor=new Font(bf, 12f);					
		PdfPTable tbl_datos=new PdfPTable(4);	
		tbl_datos.setWidthPercentage(100); 
		tbl_datos.setWidths(new float[] {0.5f,0.8f,0.5f,0.3f});
		cell=new PdfPCell(new Phrase("Nro Autorización:",Fdescriptor));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		cell.setBorder(Rectangle.NO_BORDER);	
		tbl_datos.addCell(cell);
		cell=new PdfPCell(new Phrase(autorizacion.getAutorizCompl(),Fvalor));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		cell.setBorder(Rectangle.NO_BORDER);	
		tbl_datos.addCell(cell);
		
		SimpleDateFormat sdf;
		cell=new PdfPCell(new Phrase("Fecha:",Fdescriptor));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		cell.setBorder(Rectangle.NO_BORDER);	
		tbl_datos.addCell(cell);	
		sdf=new SimpleDateFormat("dd/MM/YYYY");
		String dateString=sdf.format(autorizacion.getFecha());
		cell=new PdfPCell(new Phrase(dateString,Fvalor));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		cell.setBorder(Rectangle.NO_BORDER);	
		tbl_datos.addCell(cell);		
		tbl_datos.setWidthPercentage(100);
		tbl_datos.setSpacingAfter(10);		
		document.add(tbl_datos);
		
		PdfPTable tbl_datos2=new PdfPTable(4);	
		tbl_datos2.setWidthPercentage(100); 
		tbl_datos2.setWidths(new float[] {0.5f,0.8f,0.5f,0.3f});
		cell=new PdfPCell(new Phrase("Modalidad:",Fdescriptor));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		cell.setBorder(Rectangle.NO_BORDER);	
		tbl_datos2.addCell(cell);
		cell=new PdfPCell(new Phrase(autorizacion.getModalidad().getcModDe2(),Fvalor));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		cell.setBorder(Rectangle.NO_BORDER);	
		tbl_datos2.addCell(cell);
		cell=new PdfPCell(new Phrase(""));
		cell.setBorder(Rectangle.NO_BORDER);	
		tbl_datos2.addCell(cell);
		tbl_datos2.addCell(cell);
		tbl_datos2.setSpacingAfter(10);	
		document.add(tbl_datos2);
		
		Font Ftitulo=new Font(bf, 10f);		
		PdfPTable tbl_datos3=new PdfPTable(4);	
		tbl_datos3.setWidthPercentage(100); 
		tbl_datos3.setWidths(new float[] {0.62f,2,0,0});
		cell=new PdfPCell(new Phrase("Título:",Fdescriptor));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		cell.setBorder(Rectangle.NO_BORDER);	
		tbl_datos3.addCell(cell);
		cell=new PdfPCell(new Phrase(autorizacion.getTitulo(),Ftitulo));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
		cell.setBorder(Rectangle.NO_BORDER);	
		tbl_datos3.addCell(cell);
		cell=new PdfPCell(new Phrase(""));
		cell.setBorder(Rectangle.NO_BORDER);	
		tbl_datos3.addCell(cell);
		tbl_datos3.addCell(cell);
		tbl_datos3.setSpacingAfter(15);	
		document.add(tbl_datos3);
		
		Font SubTitulo3=new Font(bf, 15f);
		cell=new PdfPCell(new Phrase("Autor(es)",SubTitulo3));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell.setBorder(Rectangle.NO_BORDER);		
		PdfPTable tablaSubtitulo3=new PdfPTable(1);
		tablaSubtitulo3.setWidthPercentage(85);
		tablaSubtitulo3.addCell(cell);
		tablaSubtitulo3.setSpacingAfter(5);			
		document.add(tablaSubtitulo3);
		
		PdfPTable tablaAutores=null;	
		
		for(AutorCybertesisDetalle autores : autorizacion.getAutores()) {
			
			tablaAutores=new PdfPTable(4);
			tablaAutores.setWidthPercentage(100); 
			tablaAutores.setWidths(new float[] {1.5f,4.5f,1.5f,4.5f});
			
			cell=new PdfPCell(new Phrase("Código:",Fdescriptor));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBorder(Rectangle.NO_BORDER);	
			tablaAutores.addCell(cell);
			cell=new PdfPCell(new Phrase(autores.getCodAlumno(),Fvalor));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBorder(Rectangle.NO_BORDER);	
			tablaAutores.addCell(cell);
			
			cell=new PdfPCell(new Phrase("DNI:",Fdescriptor));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			cell.setBorder(Rectangle.NO_BORDER);
			tablaAutores.addCell(cell);			
			cell=new PdfPCell(new Phrase(autores.getDni(),Fvalor));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBorder(Rectangle.NO_BORDER);
			tablaAutores.addCell(cell);
			
			cell=new PdfPCell(new Phrase("Apellidos y Nombres:",Fdescriptor));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);			
			cell.setBorder(Rectangle.NO_BORDER);	
			tablaAutores.addCell(cell);
			cell=new PdfPCell(new Phrase(autores.getApellidos()+" "+autores.getNombres(),Fvalor));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);	
			tablaAutores.addCell(cell);
			
			cell=new PdfPCell(new Phrase("Telefono:",Fdescriptor));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			tablaAutores.addCell(cell);			
			cell=new PdfPCell(new Phrase(autores.getTelefono(),Fvalor));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			tablaAutores.addCell(cell);
			
			cell=new PdfPCell(new Phrase("Correo:",Fdescriptor));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);			
			cell.setBorder(Rectangle.NO_BORDER);	
			tablaAutores.addCell(cell);
			cell=new PdfPCell(new Phrase(autores.getCorreo(),Fvalor));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);	
			tablaAutores.addCell(cell);
			
			cell=new PdfPCell(new Phrase("Celular:",Fdescriptor));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			tablaAutores.addCell(cell);			
			cell=new PdfPCell(new Phrase(autores.getCelular(),Fvalor));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			tablaAutores.addCell(cell);
			
			cell=new PdfPCell(new Phrase("Facultad:",Fdescriptor));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);			
			cell.setBorder(Rectangle.NO_BORDER);	
			tablaAutores.addCell(cell);
			cell=new PdfPCell(new Phrase(autores.getFacultad().getFacultad(),Fvalor));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);	
			tablaAutores.addCell(cell);
			
			cell=new PdfPCell(new Phrase("Escuela:",Fdescriptor));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			tablaAutores.addCell(cell);			
			cell=new PdfPCell(new Phrase(autores.getPrograma().getProgProf(),Fvalor));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			tablaAutores.addCell(cell);
			
			cell=new PdfPCell(new Phrase("Dirección:",Fdescriptor));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);			
			cell.setBorder(Rectangle.NO_BORDER);	
			tablaAutores.addCell(cell);
			cell=new PdfPCell(new Phrase(autores.getDomicilio(),Fvalor));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);	
			tablaAutores.addCell(cell);
			
			cell=new PdfPCell(new Phrase("Grado:",Fdescriptor));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			tablaAutores.addCell(cell);			
			cell=new PdfPCell(new Phrase(autores.getGrado().getGrado().toUpperCase(),Fvalor));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			tablaAutores.addCell(cell);	
			tablaAutores.setSpacingAfter(20);
			document.add(tablaAutores);
			
			tablaAutores=new PdfPTable(1);
			tablaAutores.setWidthPercentage(100); 
			cell=new PdfPCell(new Phrase("________________",Fvalor));	
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			cell.setBorder(Rectangle.NO_BORDER);
			tablaAutores.addCell(cell);				
			document.add(tablaAutores);
			
			tablaAutores=new PdfPTable(1);
			tablaAutores.setWidthPercentage(100); 
			cell=new PdfPCell(new Phrase("Firma",Fvalor));	
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			cell.setBorder(Rectangle.NO_BORDER);
			tablaAutores.addCell(cell);					
			document.add(tablaAutores);
			
			tablaAutores=new PdfPTable(1);
			tablaAutores.setWidthPercentage(100); 
			cell=new PdfPCell(new Phrase("",Fvalor));
			cell.setBorder(Rectangle.BOTTOM);
			tablaAutores.addCell(cell);		
			tablaAutores.setSpacingAfter(10);	
			document.add(tablaAutores);
						
		}
						
		PdfPTable tbl_asesor=new PdfPTable(2);	
		tbl_asesor.setSpacingBefore(20);
		tbl_asesor.setWidthPercentage(100); 
		tbl_asesor.setWidths(new float[] {2.5f,7.5f});
		cell=new PdfPCell(new Phrase("Asesor(a):",Fdescriptor));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		cell.setBorder(Rectangle.NO_BORDER);	
		tbl_asesor.addCell(cell);
		cell=new PdfPCell(new Phrase(autorizacion.getAsesor(),Fvalor));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		cell.setBorder(Rectangle.NO_BORDER);	
		tbl_asesor.addCell(cell);		
		document.add(tbl_asesor);
		
		URL rutaSello=getClass().getResource("/static/imgs/sello.png");	
		Image ImgSello = Image.getInstance(rutaSello.getPath());        
		ImgSello.setAbsolutePosition(480, 35);
		ImgSello.scaleToFit(75, 75);
		document.add(ImgSello);
		
		//Image imgQR = Image.getInstance(rutaEscudoUCSM.getPath());
		String urlPDF="http://cib.ucsm.edu.pe/autorizaciontesis/verPDF?idAutorizacion="+autorizacion.getAutorizac()+"&format=pdf";
		Image imgQR = Image.getInstance(imageService.generateQRCodeAsync(urlPDF, 256, 256).get());
        //Image img = Image.getInstance(bufeBufferedImage, null);
		imgQR.setAbsolutePosition(50, 35);
		imgQR.scaleToFit(85, 85);
        document.add(imgQR);
						
	}

}
