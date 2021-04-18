package FBD;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class AgregarReunion extends JPanel {
    
    Conexion conexion;
    JPanel cartas;
    JScrollPane scroll;
    Consultar4 cons;
    public final static int AGREGAR=0;
    public final static int MODIFICAR=1;
    int acc=0;
    int ID=0;
    String Acad="";
    String [] arr= {"Ordinaria","Extraordinaria"};
    private JComboBox jComboBox2 = new JComboBox(arr);
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    DefaultListModel modelo;
    DefaultListModel modelo2;

    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JLabel jLabel6 = new JLabel();
    
    int reuniones=1;
    
    String [] dia = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"
                     ,"21","22","23","24","25","26","27","28","29","30","31"};
    String [] mes = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"}; 
    String [] hora={"7:00 A.M","8:00 A.M","9:00 A.M","10:00 A.M","11:00 A.M","12:00 A.M","1:00 P.M","2:00 P.M","3:00 P.M","4:00 P.M",
                    "5:00 P.M","6:00 P.M","7:00 P.M","8:00 P.M"};
    
    
    private JComboBox jComboBox3 = new JComboBox(dia);
    private JComboBox jComboBox4 = new JComboBox(mes);
    private JComboBox jComboBox5 = new JComboBox();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JLabel jLabel7 = new JLabel();
    private JTextField jTextField1 = new JTextField();
    private JLabel jLabel8 = new JLabel();
    private JComboBox jComboBox6 = new JComboBox();
    private JLabel jLabel9 = new JLabel();
    private JComboBox jComboBox7 = new JComboBox(hora);
    private JButton jButton1 = new JButton();
    private JButton jButton2 = new JButton();
    private JButton jButton3 = new JButton();
    private JSeparator jSeparator1 = new JSeparator();
    private JLabel jLabel10 = new JLabel();
    private JTextArea jTextArea1 = new JTextArea();
    private JLabel jLabel11 = new JLabel();
    private JLabel jLabel12 = new JLabel();
    private JScrollPane jScrollPane2 = new JScrollPane();
    private JLabel jLabel13 = new JLabel();
    private JScrollPane jScrollPane3 = new JScrollPane();
    private JScrollPane jScrollPane4 = new JScrollPane();
    private JButton jButton4 = new JButton();
    private JButton jButton5 = new JButton();
    private JSeparator jSeparator2 = new JSeparator();
    private JSeparator jSeparator3 = new JSeparator();
    private JSeparator jSeparator4 = new JSeparator();
    private JSeparator jSeparator5 = new JSeparator();
    private JSeparator jSeparator6 = new JSeparator();
    private JList jList1 = new JList();
    private JList jList2 = new JList();
    private JTextArea jTextArea2 = new JTextArea();
    private JLabel jLabel14 = new JLabel();

    JPanel jbInit() {
        this.setLayout( null );
        this.setSize(new Dimension(540, 686));
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(540,686)); //519,686
        
        String []vec=getSemestre();
        jComboBox6.addItem(vec[0]);
        jComboBox5.addItem(vec[1]);
        
        jLabel1.setText("Nombre Academia:");
        jLabel1.setBounds(new Rectangle(25, 50, 120, 25));
        jLabel1.setFont(new Font("Arial",1,12));
        
        jLabel2.setText("Tipo Reunion:");
        jLabel2.setBounds(new Rectangle(25, 115, 90, 25));
        jLabel2.setFont(new Font("Arial",1,12));
        
        jLabel3.setText("Asunto");
        jLabel3.setBounds(new Rectangle(25, 240, 55, 25));
        jLabel3.setFont(new Font("Arial",1,12));
        
        jLabel4.setText("Dia");
        jLabel4.setBounds(new Rectangle(310, 150, 40, 25));
        jLabel4.setFont(new Font("Arial",1,12));
        
        jLabel5.setText("Mes");
        jLabel5.setBounds(new Rectangle(310, 120, 60, 25));
        jLabel5.setFont(new Font("Arial",1,12));
        
        jLabel6.setText("Año");
        jLabel6.setBounds(new Rectangle(310, 190, 35, 25));
        jLabel6.setFont(new Font("Arial",1,12));
        
        jLabel7.setText("Lugar:");
        jLabel7.setBounds(new Rectangle(25, 150, 100, 25));
        jLabel7.setFont(new Font("Arial",1,12));
        
        jLabel8.setText("Semestre:");
        jLabel8.setBounds(new Rectangle(25, 190, 110, 25));
        jLabel8.setFont(new Font("Arial",1,12));

        jLabel9.setText("Hora:");
        jLabel9.setBounds(new Rectangle(310, 85, 85, 25));
        jLabel9.setFont(new Font("Arial",1,12));
        
        jLabel10.setText("Reunion: "+reuniones);
        jLabel10.setBounds(new Rectangle(185, 15, 255, 25));
        jLabel10.setFont(new Font("Arial",1,16));
        

        jLabel11.setText("");
        jLabel11.setBounds(new Rectangle(150, 50, 295, 25));
        jLabel11.setFont(new Font("Arial",0,13));

        jLabel12.setText("Acuerdos");
        jLabel12.setBounds(new Rectangle(25, 340, 105, 30));
        jLabel12.setFont(new Font("Arial",1,13));
        
        
        jLabel13.setText("Profesores");
        jLabel13.setBounds(new Rectangle(30, 445, 95, 30));
        jLabel13.setFont(new Font("Arial",1,13));
        
        jLabel14.setText("Asistentes");
        jLabel14.setBounds(new Rectangle(275, 450, 135, 20));
        jLabel14.setFont(new Font("Arial",1,13));
        
        modelo = new DefaultListModel();
        modelo2=new DefaultListModel();
        jList1.setModel(modelo);
        jList2.setModel(modelo2);
        jList1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jList1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        jComboBox2.setBounds(new Rectangle(120, 115, 145, 25));
        jComboBox3.setBounds(new Rectangle(380, 155, 90, 25));
        jComboBox4.setBounds(new Rectangle(380, 120, 90, 25));
        jComboBox5.setBounds(new Rectangle(380, 190, 90, 25));
        jComboBox6.setBounds(new Rectangle(120, 190, 90, 25));
        jComboBox7.setBounds(new Rectangle(380, 85, 90, 25));
        
        jButton1.setText("Hecho");
        jButton1.setBounds(new Rectangle(400, 620, 100, 25));
        jButton1.setEnabled(false);

        jButton1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    hecho(e);
                }
            });
        jButton2.setText("Guardar");
        jButton2.setBounds(new Rectangle(300, 620, 95, 25));

        jButton2.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if(acc==0){
                        //guardarReunion(e);
                        validar(acc);
                    }else{
                        validar(acc);
                    }
                    
                }
            });
        jButton3.setText("Salir");
        jButton3.setBounds(new Rectangle(15, 615, 100, 25));
        jButton3.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    salir();
                }
            });
        
        jButton4.setText("Agregar");
        jButton4.setBounds(new Rectangle(165, 560, 90, 25));
        jButton4.setEnabled(false);

        jButton4.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    jButton4_mouseClicked(e);
                }
            });
        jButton5.setText("Quitar");
        jButton5.setBounds(new Rectangle(270, 560, 100, 25));
        jButton5.setEnabled(false);

        jButton5.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    jButton5_mouseClicked(e);
                }
            });
        jSeparator1.setBounds(new Rectangle(275, 80, 15, 150));
        jSeparator1.setOrientation(JSeparator.VERTICAL);
        
        jTextArea1.setFont(new Font("Arial",0,12));
        jTextArea1.setLineWrap(true);
        jTextArea2.setFont(new Font("Arial",0,12));
        jTextArea2.setLineWrap(true);

        jTextField1.setBounds(new Rectangle(120, 150, 145, 25));
        jTextField1.setFont(new Font("Arial",0,12));
        
        jScrollPane1.setBounds(new Rectangle(55, 270, 415, 55));
        jScrollPane2.setBounds(new Rectangle(60, 370, 415, 60));
        jScrollPane3.setBounds(new Rectangle(30, 480, 220, 65));
        jScrollPane4.setBounds(new Rectangle(275, 480, 235, 65));
        
        jSeparator2.setBounds(new Rectangle(5, 230, 530, 5));
        jSeparator3.setBounds(new Rectangle(0, 0, 0, 2));
        jSeparator4.setBounds(new Rectangle(5, 80, 535, 15));
        jSeparator5.setBounds(new Rectangle(5, 335, 530, 2));
        jSeparator6.setBounds(new Rectangle(5, 440, 530, 5));

        this.add(jLabel14, null);
        this.add(jSeparator6, null);
        this.add(jSeparator5, null);
        this.add(jSeparator4, null);
        this.add(jSeparator3, null);
        this.add(jSeparator2, null);
        this.add(jButton5, null);
        this.add(jButton4, null);
        jScrollPane4.getViewport().add(jList2, null);
        this.add(jScrollPane4, null);
        jScrollPane3.getViewport().add(jList1, null);
        this.add(jScrollPane3, null);
        this.add(jLabel13, null);
        jScrollPane2.getViewport().add(jTextArea2, null);
        this.add(jScrollPane2, null);
        this.add(jLabel12, null);
        this.add(jLabel11, null);
        this.add(jLabel10, null);
        this.add(jSeparator1, null);
        this.add(jButton3, null);
        this.add(jButton2, null);
        this.add(jButton1, null);
        this.add(jComboBox7, null);
        this.add(jLabel9, null);
        this.add(jComboBox6, null);
        this.add(jLabel8, null);
        this.add(jTextField1, null);
        this.add(jLabel7, null);
        jScrollPane1.getViewport().add(jTextArea1, null);
        this.add(jScrollPane1, null);
        this.add(jComboBox5, null);
        this.add(jComboBox4, null);
        this.add(jComboBox3, null);
        this.add(jLabel6, null);
        this.add(jLabel5, null);
        this.add(jLabel4, null);
        this.add(jLabel3, null);
        this.add(jLabel2, null);
        this.add(jLabel1, null);
        this.add(jComboBox2, null);
        return this;
    }
    
    public void getProfesores(){
        String sql="SELECT DISTINCT NOMBREP FROM ACADEMIA_PROFESOR WHERE NOMBRE_ACADEMIA=(SELECT " +
            "NOMBRE_ACADEMIA FROM ACADEMIA WHERE NOMBRE_ACADEMIA='"+this.Acad+"')";
        ResultSet res=conexion.getConsulta(sql);
        try{
            while(res.next()){
                modelo.addElement(res.getString("NOMBREP"));
            }
            jButton4.setEnabled(true);
            jButton5.setEnabled(true);
        }catch(SQLException e){
            jButton4.setEnabled(false);
            jButton5.setEnabled(false);
        }
    }
    
    public int getCountReunion(){
        String sem=jComboBox6.getSelectedItem().toString();
        String sql="SELECT COUNT(ID_REUNION) C FROM REUNION WHERE SEMESTRE='"+sem+"' AND NOMBRE_ACADEMIA='"+this.Acad+"'";
        ResultSet res=conexion.getConsulta(sql);
        int num=0;
        try{
            res.next();
            num=res.getInt("C")+1;
        }catch(SQLException e){
            num=0;
        }
        return num;
    }
    
    public String[] getSemestre(){
        Calendar cal = new GregorianCalendar();
        String [] vec = new String[2];
        String sem="";
        String anio="";
        int año=cal.get(Calendar.YEAR);
        int mes=cal.get(Calendar.MONTH);
        if(mes<=6){
            sem=año+"A";
        }
        else{
            sem=año+"B";
        }
        anio=String.valueOf(año);
        vec[0]=sem;
        vec[1]=anio;
        return vec;
    }
    
    public void setSemestre(){
        String sql="SELECT DISTINCT SEMESTRE FROM REUNION ORDER BY SEMESTRE ASC";
        ResultSet res=conexion.getConsulta(sql);
        int cont=0;
        try{
            while(res.next()){
                jComboBox6.addItem(res.getObject("SEMESTRE"));
                cont++;
            }
            if(cont==0){
                
            }
        }catch(SQLException e){
            
        }
    }
    
    public void setAño(){
        String sql="SELECT DISTINCT TO_CHAR(FECHA,'YYYY') AS AÑO FROM REUNION ORDER BY AÑO ASC";
        ResultSet res=conexion.getConsulta(sql);
        try{
            while(res.next()){
                jComboBox5.addItem(res.getObject("AÑO"));
            }
        }catch(SQLException e){}
    }
    
    
    
    public void setAcademia(String acad){
        this.Acad=acad;
        int numR=getCountReunion();
        if(numR==0){
            reuniones=1;
            jButton3.setEnabled(false);
        }
        else{
            reuniones=numR;
            if(reuniones<3){
                jButton3.setEnabled(false);
            }
        }
        jLabel10.setText("Reunion: "+numR);
        jLabel11.setText(acad);
        this.getProfesores();
    }
    
    public void ingresarReunion(){
        String hora=jComboBox7.getSelectedItem().toString();
        String dia=jComboBox3.getSelectedItem().toString();
        if(Integer.parseInt(dia)<10){
            dia="0"+dia;
        }
       // String mes=jComboBox4.getSelectedItem().toString();
       String mes1="";
        int mes=jComboBox4.getSelectedIndex()+1;
        if(mes<10){
            mes1="0"+mes;
        }
        else{
            mes1=""+mes;
        }
        String año=jComboBox5.getSelectedItem().toString();
        String academia=jLabel11.getText();
        String semestre =jComboBox6.getSelectedItem().toString();
        String acuerdos=jTextArea2.getText();
        
        
        int id=this.getID();
String sql="INSERT INTO REUNION VALUES("+id+",'"+jComboBox2.getSelectedItem().toString()+"','"+
            jTextField1.getText()+"','"+jTextArea1.getText()+"','"+acuerdos+"','"+hora+"',TO_DATE('"+mes1+" "+dia+","+año+"','MM DD,YYYY'),"+reuniones+",'"+semestre+"','"+academia+"')";
        conexion.Ingresarsentencia(sql);
        
        for(int i=0;i<modelo2.getSize();i++){
            String nomb=modelo2.get(i).toString();
            String sql2="INSERT INTO PROFESOR_REUNION VALUES((SELECT DISTINCT INDICE FROM ACADEMIA_PROFESOR WHERE NOMBREP='"+nomb+"'),"+id+"," +
                "'"+nomb+"','"+semestre+"',TO_DATE('"+mes1+" "+dia+","+año+"','MM DD,YYYY'),'"+this.Acad+"')";
            conexion.Ingresarsentencia(sql2);
        }
    }
    
    public void modificarReunion(){
        String hora=jComboBox7.getSelectedItem().toString();
        String dia=jComboBox3.getSelectedItem().toString();
        if(Integer.parseInt(dia)<10){
            dia="0"+dia;
        }
        String mes1="";
        int mes=jComboBox4.getSelectedIndex()+1;
        if(mes<10){
            mes1="0"+mes;
        }
        else{
            mes1=""+mes;
        }
        //String mes=jComboBox4.getSelectedItem().toString();
        String año=jComboBox5.getSelectedItem().toString();
        String semestre =jComboBox6.getSelectedItem().toString();
        String asunto=jTextArea1.getText();
        String acuerdos=jTextArea2.getText();
        String lugar=jTextField1.getText();
        String tipo=jComboBox2.getSelectedItem().toString();
        
        String sql="UPDATE REUNION SET TIPO_R='"+tipo+"',LUGAR='"+lugar+"',ASUNTO='"+asunto+"',ACUERDOS='"+acuerdos+"',HORA='"+hora+"'," +
            "FECHA=TO_DATE('"+mes1+" "+dia+","+año+"','MM DD, YYYY'),NOREUNION="+reuniones+",SEMESTRE='"+semestre+"',NOMBRE_ACADEMIA='"+this.Acad+"' WHERE ID_REUNION="+ID+"";
        conexion.Ingresarsentencia(sql);
        
        String sql2="DELETE FROM PROFESOR_REUNION WHERE ID_REUNION="+ID+"";
        conexion.Ingresarsentencia(sql2);
        
        for(int i=0;i<modelo2.getSize();i++){
            String nomb=modelo2.get(i).toString();
            String sql3="INSERT INTO PROFESOR_REUNION VALUES((SELECT DISTINCT INDICE FROM ACADEMIA_PROFESOR WHERE NOMBREP='"+nomb+"'),"+ID+"," +
                "'"+nomb+"','"+semestre+"',TO_DATE('"+mes1+" "+dia+","+año+"','MM DD,YYYY'),'"+this.Acad+"')";
            conexion.Ingresarsentencia(sql3);
        }
        JOptionPane.showMessageDialog(this,"Reunion Modificada","Exito",JOptionPane.INFORMATION_MESSAGE);
        jButton2.setBounds(new Rectangle(300, 620, 95, 25));
        salir();
    }
    
    
    public int getID(){
        String sql = "SELECT MAX(ID_REUNION) C FROM REUNION";
        ResultSet res = conexion.getConsulta(sql);
        int indice=1;
        try{
            res.next();
            indice =res.getInt("C")+1;
        }catch(SQLException e){
            indice=1;
        }
        return indice;
    }
    
    public void setCart(JPanel card){
        cartas=card;
        //CardLayout cl = (CardLayout)(cartas.getLayout());
        //cl.show(cartas,"Panel 2");
    }

    private void hecho(MouseEvent e) {
        CardLayout cl = (CardLayout)(cartas.getLayout());
        cl.show(cartas,"Menu");
    }

    private void guardarReunion() {
        ingresarReunion();
        if(jComboBox2.getSelectedIndex()!=1){
            reuniones++;
            jLabel10.setText("Reunion: "+reuniones);
        }else{
            jLabel10.setText("Reunion: Extraordinaria");
        }
        if(reuniones>3){
            jButton1.setEnabled(true);
            jButton3.setEnabled(true);
        }
        JOptionPane.showMessageDialog(this,"Reunion Guardada","Exito",JOptionPane.INFORMATION_MESSAGE);
        limpiar();
    }
    
    public void setConsulta(Consultar4 c){
        cons=c;
    }

    private void salir() {
        this.acc=0;
        scroll.getViewport().setViewPosition(new Point(0,0));
        modelo.removeAllElements();
        modelo2.removeAllElements();
        jButton2.setBounds(new Rectangle(300, 620, 95, 25));
        jButton1.setVisible(true);
        jButton1.setEnabled(true);
        
        this.limpiar();
        //a.setPanel(this);
        //a.setCard(cartas);
        //a.getAcademia();
        //a.setInfoTable();
        CardLayout cl = (CardLayout)(cartas.getLayout());
        cl.show(cartas,"Menu");
        cons.getSemestre();
        cons.limpiar();
        cons.setInfoTable();
        cons.setVisible(true);
    }
    
    public void initConexion(){
        conexion=Acceso.conexion;
    }
    
    private void limpiar(){
        jTextField1.setText("");
        jTextArea1.setText("");
        jTextArea2.setText("");
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
        jComboBox5.setSelectedIndex(0);
        jComboBox6.setSelectedIndex(0);
        jComboBox7.setSelectedIndex(0);
        jLabel10.setText("Reunion: "+reuniones);
    }
    
    public void setInfo(int id){
        this.ID=id;
String sql="SELECT NOMBRE_ACADEMIA,ID_REUNION,TIPO_R,LUGAR,ASUNTO,ACUERDOS,HORA,TO_CHAR(FECHA,'DD') AS DIA,TO_CHAR(FECHA,'MM') AS MES,TO_CHAR(FECHA,'YYYY') AS ANIO,NOREUNION,SEMESTRE,NOMBRE FROM REUNION " +
            "NATURAL JOIN PROFESOR_REUNION WHERE ID_REUNION="+id+"";
        ResultSet res=conexion.getConsulta(sql);
        try{
            this.acc=this.MODIFICAR;
            jButton1.setVisible(false);
            jButton1.setEnabled(false);
            jButton2.setBounds(new Rectangle(400, 620, 100, 25));
            jComboBox5.removeAllItems();
            jComboBox6.removeAllItems();
            String año="";
            String mes="";
            String dia="";
            res.next();
            this.setSemestre();
            this.setAño();
            this.Acad=res.getString("NOMBRE_ACADEMIA");
            jLabel11.setText(res.getString("NOMBRE_ACADEMIA"));
            jComboBox2.setSelectedItem(res.getObject("TIPO_R"));
            jTextField1.setText(res.getString("LUGAR"));
            jTextArea1.setText(res.getString("ASUNTO"));
            jLabel10.setText("Reunion: "+res.getString("NOREUNION"));
            jComboBox6.setSelectedItem(res.getString("SEMESTRE"));
            jComboBox7.setSelectedItem(res.getString("HORA"));
            año=res.getString("ANIO");
            mes=res.getString("MES");
            
            System.out.println(mes.length());
            dia=res.getString("DIA");
            jTextArea1.setText(res.getString("ASUNTO"));
            jTextArea2.setText(res.getString("ACUERDOS"));
            jComboBox4.setSelectedIndex(Integer.parseInt(mes)-1);
            if(dia.startsWith("0")==true){
                 dia=dia.substring(1);
            }
            jComboBox3.setSelectedItem(dia);
            jComboBox5.setSelectedItem(año);
            this.getProfesores();
            String prof=res.getString("NOMBRE");
            modelo2.addElement(prof);
            if(modelo.contains(prof)==true){
                modelo.removeElement(prof);
            }
            while(res.next()){
                String prof1=res.getString("NOMBRE");
                modelo2.addElement(prof1);
                if(modelo.contains(prof1)==true){
                    modelo.removeElement(prof1);
                }
            }
        }catch(SQLException e){
            System.out.println("Error en SetInfo:AR");
            SetInfo2(this.ID);
        }
    }
    
    private void SetInfo2(int id){
       /* String sql="SELECT NOMBRE_ACADEMIA,ID_REUNION,TIPO_R,LUGAR,ASUNTO,ACUERDOS,HORA,TO_CHAR(FECHA,'DD') AS DIA,TO_CHAR(FECHA,'Month') AS MES,TO_CHAR(FECHA,'YYYY') AS ANIO,NOREUNION,SEMESTRE FROM REUNION " +
                    "WHERE ID_REUNION="+id+"";*/
        String sql="SELECT NOMBRE_ACADEMIA,ID_REUNION,TIPO_R,LUGAR,ASUNTO,ACUERDOS,HORA,TO_CHAR(FECHA,'DD') AS DIA,TO_CHAR(FECHA,'MM') AS MES,TO_CHAR(FECHA,'YYYY') AS ANIO,NOREUNION,SEMESTRE FROM REUNION " +
                    "WHERE ID_REUNION="+id+"";
        ResultSet res=conexion.getConsulta(sql);
        try{
            res.next();
            this.acc=this.MODIFICAR;
            jButton1.setVisible(false);
            jButton1.setEnabled(false);
            jButton2.setBounds(new Rectangle(400, 620, 100, 25));
            jComboBox5.removeAllItems();
            jComboBox6.removeAllItems();
            String año="";
            String mes="";
            String dia="";
            this.setSemestre();
            this.setAño();
            this.Acad=res.getString("NOMBRE_ACADEMIA");
            jLabel11.setText(res.getString("NOMBRE_ACADEMIA"));
            jComboBox2.setSelectedItem(res.getObject("TIPO_R"));
            jTextField1.setText(res.getString("LUGAR"));
            jTextArea1.setText(res.getString("ASUNTO"));
            jLabel10.setText("Reunion: "+res.getString("NOREUNION"));
            jComboBox6.setSelectedItem(res.getString("SEMESTRE"));
            jComboBox7.setSelectedItem(res.getString("HORA"));
            año=res.getString("ANIO");
            mes=res.getString("MES");
            dia=res.getString("DIA");
            jTextArea1.setText(res.getString("ASUNTO"));
            jTextArea2.setText(res.getString("ACUERDOS"));
            jComboBox4.setSelectedIndex(Integer.parseInt(mes)-1);
            if(dia.startsWith("0")==true){
                 dia=dia.substring(1);
            }
            jComboBox3.setSelectedItem(dia);
            jComboBox5.setSelectedItem(año);
            this.getProfesores();
            
        }catch(SQLException e){
            System.out.println("Error en SetInfo2");
        }
    }

    private void jComboBox1_itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==ItemEvent.SELECTED){
            limpiar();
            reuniones=1;
            jLabel10.setText("Reunion: "+reuniones);
        }
    }

    private void jButton4_mouseClicked(MouseEvent e) {
        if(modelo.size()!=0){
            Object []prof = jList1.getSelectedValues();
            for(int i=0;i<prof.length;i++){
                modelo2.addElement(prof[i]);
                modelo.removeElement(prof[i]);
            }
        }
        else{
            JOptionPane.showMessageDialog(this,"No Hay Profesores Seleccionados","Error",JOptionPane.ERROR_MESSAGE);
        }
       
    }

    private void jButton5_mouseClicked(MouseEvent e) {
        if(modelo2.size()!=0){
            Object []prof = jList2.getSelectedValues();
            for(int i=0;i<prof.length;i++){
                modelo.addElement(prof[i]);
                modelo2.removeElement(prof[i]);
            }
        }else{
            JOptionPane.showMessageDialog(this,"No Hay Profesores Seleccionados","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void setScroll(JScrollPane scr){
        this.scroll=scr;
    }
    
    public void validar(int tipo){
        if(jTextField1.getText().equals("")==true){
            JOptionPane.showMessageDialog(this,"Error, debe especificar el lugar de la reunion","Error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            if(jTextArea1.getText().equals("")==true){
                JOptionPane.showMessageDialog(this,"Error, debe especificar el asunto de la reunion","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                if(compReunion(tipo)==true){
                    JOptionPane.showMessageDialog(this,"Error, la academia: "+this.Acad+" ya tiene una reunion\n programada a esa hora","Error",JOptionPane.ERROR_MESSAGE);
                }else{
                    if(compFecha()==false){
                        JOptionPane.showMessageDialog(this,"Error, el mes esta equivocado","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        if(compDia()==true){
                            JOptionPane.showMessageDialog(this,"Error, el dia no coincide con el mes","Error",JOptionPane.ERROR_MESSAGE);
                        }
                        else{
                            if(tipo==0){
                                this.guardarReunion();
                            }
                            else{
                                this.modificarReunion();
                            }
                        }
                    }
                }
            }
        }
    }
    
    public boolean compFecha(){
        boolean ind=true,ind2=true,ind3=true;
        GregorianCalendar a = new GregorianCalendar();
        int mes=a.get(Calendar.MONTH);
        if((jComboBox4.getSelectedIndex()+1)<mes){
            ind=false;
        }
        else{
            ind=true;
        }
        return ind;
    }
    
    public boolean compDia(){
        boolean ind=true;
        int dia=Integer.parseInt(jComboBox3.getSelectedItem().toString());
        int mes=jComboBox4.getSelectedIndex()+1;
        switch(mes){
        case 1:
            ind=(dia<=31)?false:true;
            break;
        case 2:
            ind=(dia<=28)?false:true;
            break;
        case 3:
            ind=(dia<=31)?false:true;
            break;
        case 4:
            ind=(dia<=30)?false:true;
            break;
        case 5:
            ind=(dia<=31)?false:true;
            break;
        case 6:
            ind=(dia<=30)?false:true;
            break;
        case 7:
            ind=(dia<=31)?false:true;
            break;
        case 8:
            ind=(dia<=31)?false:true;
            break;
        case 9:
            ind=(dia<=30)?false:true;
            break;
        case 10:
            ind=(dia<=31)?false:true;
            break;
        case 11:
            ind=(dia<=30)?false:true;
            break;
        case 12:
            ind=(dia<=31)?false:true;
            break;
        }
        return ind;
    }
    
    public boolean compReunion(int tipo){
        boolean ind=false;
        String año=jComboBox5.getSelectedItem().toString();
        String dia=jComboBox3.getSelectedItem().toString();
        String mes=jComboBox4.getSelectedItem().toString();
        String hora=jComboBox7.getSelectedItem().toString();
        String sem=jComboBox6.getSelectedItem().toString();
        switch(tipo){
        case AGREGAR:
            String sql="SELECT ID_REUNION FROM REUNION WHERE FECHA=TO_DATE('"+mes+" "+dia+","+año+"','MON DD,YYYY') And SEMESTRE='"+sem+"' " +
                "AND HORA='"+hora+"' AND NOMBRE_ACADEMIA='"+this.Acad+"'";
            ResultSet res=conexion.getConsulta(sql);
            try{
                res.next();
                res.getString("ID_REUNION");
                ind=true;
            }catch(SQLException e){
                ind=false;
            }
            break;
        case MODIFICAR:
            ind=true;
            String sql2="SELECT ID_REUNION FROM REUNION WHERE FECHA=TO_DATE('"+mes+" "+dia+","+año+"','MON DD,YYYY') AND SEMESTRE='"+sem+"' " +
                "AND HORA='"+hora+"' AND NOMBRE_ACADEMIA='"+this.Acad+"' AND ID_REUNION="+this.ID+"";
            ResultSet res2=conexion.getConsulta(sql2);
            try{
                res2.next();
                System.out.println(sql2);
                System.out.println("sss");
                res2.getInt("ID_REUNION");
                ind=false;
                
            }catch(SQLException f){
               /* if(this.compReunion(1)==false){
                    ind=false;
                }else{
                    ind=true;
                }*/
            }
            break;
        }
        return ind;
    }
    
    public void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D)g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gradientPaint =
                    new GradientPaint(new Point(0, 0),Color.ORANGE, new Point(0, getHeight() / 2),
                                      Color.ORANGE);
                g2d.setPaint(gradientPaint);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);

    
    }
    
}
