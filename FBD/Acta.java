package FBD;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;

import com.itextpdf.text.pdf.PdfPCell;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;

import java.net.MalformedURLException;

import javax.swing.JFileChooser;


public class Acta {
    int ID;
    ConsultaR reunion;
    Conexion conexion=Acceso.conexion;
    String ruta="";
    int numAsis=0;
    public Acta(int id,ConsultaR b) {
        ID=id;
        reunion=b;
    }
    
    public void principal(){
        JFileChooser dial = new JFileChooser();
        dial.setDialogType(JFileChooser.SAVE_DIALOG);
        dial.setDialogTitle("Guardar Acta");
        dial.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int opc=dial.showSaveDialog(reunion);
        if(opc==dial.APPROVE_OPTION){
             ruta =dial.getSelectedFile().getAbsolutePath();
             this.formato();
            
        }
        else{
            return;
        }
    }
    
    public void formato(){
        String sql="SELECT TIPO_R,LUGAR,ASUNTO,ACUERDOS,HORA,TO_CHAR(FECHA,'Month') AS A,TO_CHAR(FECHA,'DD') AS B," +
            "TO_CHAR(FECHA,'YYYY') AS C,SEMESTRE,NOMBRE_ACADEMIA FROM REUNION WHERE ID_REUNION="+ID+"";
        ResultSet res=conexion.getConsulta(sql);
        try{
            res.next();
            Document documento = new Document();
            FileOutputStream pdf = new FileOutputStream(ruta+".pdf");
            PdfWriter.getInstance(documento,pdf);
            documento.open();
            
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(parrafo.ALIGN_CENTER);
            parrafo.setFont(new Font(FontFactory.getFont("Arial",14,Font.BOLD)));
            parrafo.add("Universidad Autonoma del Estado de Mexico\n");
            parrafo.add("Facultad de Ingenieria\n\n");
            parrafo.add("Coordinacion de Ingenieria en Computacion\n");
            parrafo.add(res.getString("NOMBRE_ACADEMIA")+"\n");
            parrafo.add("__________________________________________________________________\n\n");
            documento.add(parrafo);
            parrafo.clear();
            
            parrafo.setAlignment(parrafo.ALIGN_CENTER);
            parrafo.setFont(new Font(FontFactory.getFont("Arial",12,Font.BOLD)));
            parrafo.add("Reunion "+res.getString("TIPO_R")+"\n");
            parrafo.add(res.getString("SEMESTRE")+"\n");
            parrafo.add(res.getString("B")+" de "+res.getString("A")+"del "+res.getString("C")+"\n\n\n");
            documento.add(parrafo);
            parrafo.clear();
            
            parrafo.setAlignment(parrafo.ALIGN_LEFT);
            parrafo.setFont(new Font(FontFactory.getFont("Arial",12,Font.BOLD)));
            parrafo.add("Reunion: ");
            parrafo.setFont(new Font(FontFactory.getFont("Arial",12,Font.NORMAL)));
            parrafo.add("Reunion de la Academia "+res.getString("NOMBRE_ACADEMIA")+". Periodo: "+res.getString("SEMESTRE")+"\n");
            parrafo.setFont(new Font(FontFactory.getFont("Arial",12,Font.BOLD)));
            parrafo.add("Lugar: ");
            parrafo.setFont(new Font(FontFactory.getFont("Arial",12,Font.NORMAL)));
            parrafo.add(res.getString("LUGAR")+"\n");
            parrafo.setFont(new Font(FontFactory.getFont("Arial",12,Font.BOLD)));
            parrafo.add("Hora: ");
            parrafo.setFont(new Font(FontFactory.getFont("Arial",12,Font.NORMAL)));
            parrafo.add(res.getString("HORA")+"\n");
            documento.add(parrafo);
            parrafo.clear();
            
            parrafo.setAlignment(parrafo.ALIGN_LEFT);
            parrafo.setFont(new Font(FontFactory.getFont("Arial",12,Font.BOLD)));
            parrafo.add("\nAsistentes: \n\t");
            
            String sql2="SELECT NOMBRE FROM PROFESOR_REUNION WHERE ID_REUNION="+ID+"";
            ResultSet res2=conexion.getConsulta(sql2);
            parrafo.setFont(new Font(FontFactory.getFont("Arial",12,Font.NORMAL)));
            while(res2.next()){
                parrafo.add("Profesor: "+res2.getString("NOMBRE")+"\n\t");
                numAsis++;
            }
            res2.close();
            documento.add(parrafo);
            parrafo.clear();
            
            parrafo.setAlignment(parrafo.ALIGN_LEFT);
            parrafo.setFont(new Font(FontFactory.getFont("Arial",12,Font.BOLD)));
            parrafo.add("\nAsuntos: \n\t");
            parrafo.setFont(new Font(FontFactory.getFont("Arial",12,Font.NORMAL)));
            parrafo.add(res.getString("ASUNTO")+"\n");
            documento.add(parrafo);
            parrafo.clear();
            
            parrafo.setAlignment(parrafo.ALIGN_LEFT);
            parrafo.setFont(new Font(FontFactory.getFont("Arial",12,Font.BOLD)));
            parrafo.add("\nAcuerdos: \n\t");
            parrafo.setFont(new Font(FontFactory.getFont("Arial",12,Font.NORMAL)));
            parrafo.add(res.getString("ACUERDOS")+"\n");
            parrafo.add("\n\n");
            documento.add(parrafo);
            parrafo.clear();
            
            PdfPTable tabla = new PdfPTable(2);
            tabla.addCell(new PdfPCell(new Paragraph("Nombre",FontFactory.getFont("Arial",12,Font.NORMAL))));
            tabla.addCell(new PdfPCell(new Paragraph("Firma",FontFactory.getFont("Arial",12,Font.NORMAL))));
            for (int i=0;i<numAsis*2; i++)
            {   
                if(i%2==0){
                    tabla.addCell(new PdfPCell(new Paragraph("Prof:\n",FontFactory.getFont("Arial",12,Font.NORMAL))));
                }
                else{
                    tabla.addCell("\n ");
                }
                
            }
            documento.add(tabla);
            
            parrafo.setAlignment(parrafo.ALIGN_LEFT);
            parrafo.setFont(new Font(FontFactory.getFont("Arial",10,Font.NORMAL)));
            parrafo.add("\n\n\n\n\nc.c.p Coordinacion de Ingenieria en Computacion");
            documento.add(parrafo);
            parrafo.clear();
            
            documento.close();
            try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + ruta + ".pdf");
           } catch (IOException ep) {
                 ep.printStackTrace();
           }
        }catch(SQLException e){
            }catch(FileNotFoundException h){} catch (DocumentException e) {
        } catch (IOException e) {
        }
    }
}
