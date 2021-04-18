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

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class AgregarProfesor extends JPanel{
    ArrayList <ArrayList> materias= new ArrayList<ArrayList>();
   Conexion conexion;
   Consulta cons;
   JPanel cartas;
   int indice=0;
   String nomAnterior="";
   JScrollPane scroll;
   public final static int AGREGAR=0;
   public final static int MODIFICAR=1;
   int acc=0;
   int numAreas=0;
   boolean IND=false;
   
   public Object [][] mat;
   

    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JComboBox jComboBox1 = new JComboBox();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JLabel jLabel6 = new JLabel();
    private JLabel jLabel7 = new JLabel();
    private JTextField jTextField1 = new JTextField();
    private JTextField jTextField2 = new JTextField();
    private JTextField jTextField3 = new JTextField();
    private JTextField jTextField4 = new JTextField();
    private JTextField jTextField5 = new JTextField();
    private JLabel jLabel8 = new JLabel();
    private JSeparator jSeparator1 = new JSeparator();
    private JSeparator jSeparator2 = new JSeparator();
    private JLabel jLabel10 = new JLabel();
    private JComboBox jComboBox3 = new JComboBox();
    private JLabel jLabel9 = new JLabel();
    private JButton jButton1 = new JButton();
    DefaultListModel modelo;
    DefaultListModel modelo2;
    private JList jList1 = new JList();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JComboBox jComboBox2 = new JComboBox();
    private JScrollPane jScrollPane2 = new JScrollPane();
    private JList jList2 = new JList();
    private JButton jButton3 = new JButton();
    private JButton jButton4 = new JButton();
    private JButton jButton5 = new JButton();
    private JLabel jLabel13 = new JLabel();
    private JComboBox jComboBox4 = new JComboBox();
    private JComboBox jComboBox5 = new JComboBox();


    JPanel jbInit(){
        this.setLayout(null);
        this.setSize(new Dimension(574, 499));
        this.setPreferredSize(new Dimension(574, 499)); //574,499
        this.setOpaque(false);
        
        modelo=new DefaultListModel();
        modelo2=new DefaultListModel();
        
        jLabel1.setText("Nombre(s):");
        jLabel1.setBounds(new Rectangle(10, 60, 90, 20));
        jLabel1.setFont(new Font("Arial",0,12));
        
        jLabel2.setText("Apellido Paterno:");
        jLabel2.setBounds(new Rectangle(10, 90, 95, 20));
        jLabel2.setFont(new Font("Arial",0,12));
        
        jLabel3.setText("Apellido Materno");
        jLabel3.setBounds(new Rectangle(10, 120, 105, 25));
        jLabel3.setFont(new Font("Arial",0,12));
        
        
        jLabel4.setText("Puesto En la Academia:");
        jLabel4.setBounds(new Rectangle(10, 375, 155, 20));
        jLabel4.setFont(new Font("Arial",0,12));
        
        jLabel5.setText("Materia(s) que Imparte:");
        jLabel5.setBounds(new Rectangle(220, 200, 135, 25));
        jLabel5.setFont(new Font("Arial", 1, 12));
        
        jLabel6.setText("Correo Electronico:");
        jLabel6.setBounds(new Rectangle(265, 55, 115, 20));
        jLabel6.setFont(new Font("Arial",0,12));
        
        jLabel7.setText("RFC:");
        jLabel7.setBounds(new Rectangle(300, 90, 50, 20));
        jLabel7.setFont(new Font("Arial",0,12));
        
        jLabel8.setText("Datos Del Profesor:");
        jLabel8.setBounds(new Rectangle(30, 15, 140, 20));
        jLabel8.setFont(new Font("Arial", 1, 14));

        jLabel9.setText("Area Academica:");
        jLabel9.setBounds(new Rectangle(25, 160, 150, 20));
        jLabel9.setFont(new Font("Arial", 1, 14));

        jLabel10.setText("Academia:");
        jLabel10.setBounds(new Rectangle(10, 215, 135, 25));
        jLabel10.setFont(new Font("Arial",0,12));
        
        jLabel13.setText("Grado:");
        jLabel13.setBounds(new Rectangle(300, 120, 55, 25));
       // jLabel13.setBounds(new Rectangle(10, 215, 135, 25));
        jLabel13.setFont(new Font("Arial",0,12));
        
        jButton1.setText("Guardar");
        jButton1.setBounds(new Rectangle(415, 440, 90, 25));
        jButton1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if(acc==0){
                        //Ingresar(acc);
                        validacion();
                    }else{
                        //Ingresar(acc);
                        validacion();
                    }
                }
            });
        
        
        jButton3.setText("Agregar");
        jButton3.setBounds(new Rectangle(245, 285, 80, 20));

        jButton3.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    agregarMat(e);
                }
            });
        jButton4.setText("Quitar");
        jButton4.setBounds(new Rectangle(245, 320, 80, 20));

        jButton4.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    quitarMat(e);
                }
            });
        jButton5.setText("Salir");
        jButton5.setBounds(new Rectangle(20, 445, 85, 25));


        jButton5.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    salir(e);
                }
            });
        jComboBox4.setBounds(new Rectangle(375, 120, 110, 20));
        jComboBox5.setBounds(new Rectangle(300, 375, 200, 20));
        jComboBox4.addItem("Licenciatura");
        jComboBox4.addItem("Maestria");
        jComboBox4.addItem("Doctorado");
        
        
        jList1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollPane1.setBounds(new Rectangle(10, 285, 230, 65));
        
        jList2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollPane2.setBounds(new Rectangle(330, 285, 235, 65));


        jSeparator1.setBounds(new Rectangle(0, 40, 570, 5));
        jSeparator2.setBounds(new Rectangle(0, 190, 570, 10));
        
        jComboBox1.setBounds(new Rectangle(145, 375, 140, 20));
        jComboBox1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jComboBox1_actionPerformed(e);
                }
            });
        jComboBox1.addItem("Presidente");
        jComboBox1.addItem("Secretario");
        jComboBox1.addItem("Ninguno");
        
        
        jComboBox2.setBounds(new Rectangle(330, 245, 200, 20));
        jComboBox2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jComboBox2_actionPerformed(e);
                }
            });
        jComboBox3.setBounds(new Rectangle(10, 245, 190, 20));

        jComboBox3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jComboBox3_actionPerformed(e);
                }
            });
        jTextField1.setBounds(new Rectangle(125, 55, 125, 25));
        jTextField1.setFont(new Font("Arial",0,12));
       // jTextField1.setDocument(new DocumentoJTF(3));
        
        jTextField2.setBounds(new Rectangle(125, 90, 125, 25));
        jTextField2.setFont(new Font("Arial",0,12));
        //jTextField2.setDocument(new DocumentoJTF(3));
        
        jTextField3.setBounds(new Rectangle(125, 120, 125, 25));
        jTextField3.setFont(new Font("Arial",0,12));
        //jTextField3.setDocument(new DocumentoJTF(3));
        
        jTextField4.setBounds(new Rectangle(375, 55, 110, 25));
        jTextField4.setFont(new Font("Arial",0,12));
        
        jTextField5.setBounds(new Rectangle(375, 90, 70, 25));
        jTextField5.setFont(new Font("Arial",0,12));
        jTextField5.setDocument(new DocumentoJTF(jTextField5,0,10));


        //this.getAcademias();
        //this.numMaterias();
        //this.setMaterias();

        this.add(jComboBox5, null);
        this.add(jComboBox4, null);
        this.add(jLabel13, null);
        this.add(jButton5, null);
        this.add(jButton4, null);
        this.add(jButton3, null);
        jScrollPane2.getViewport().add(jList2, null);
        this.add(jScrollPane2, null);
        this.add(jComboBox2, null);
        jScrollPane1.getViewport().add(jList1, null);
        this.add(jScrollPane1, null);
        this.add(jButton1, null);
        this.add(jLabel9, null);
        this.add(jComboBox3, null);
        this.add(jLabel10, null);
        this.add(jSeparator2, null);
        this.add(jSeparator1, null);
        this.add(jLabel8, null);
        this.add(jTextField5, null);
        this.add(jTextField4, null);
        this.add(jTextField3, null);
        this.add(jTextField2, null);
        this.add(jTextField1, null);
        this.add(jLabel7, null);
        this.add(jLabel6, null);
        this.add(jLabel5, null);
        this.add(jLabel4, null);
        this.add(jComboBox1, null);
        this.add(jLabel3, null);
        this.add(jLabel2, null);
        this.add(jLabel1, null);
        return this;
    }
    
    public void getAcademias(){
        //materias=new ArrayList<ArrayList>();
        int cont=0;
        ArrayList aux = new ArrayList();
        String sql="SELECT NOMBRE_ACADEMIA FROM ACADEMIA";
        ResultSet res =conexion.getConsulta(sql);
        try{
            while(res.next()){
                Object obj = res.getObject("NOMBRE_ACADEMIA");
                jComboBox3.addItem(obj);
                jComboBox2.addItem(obj);
                jComboBox5.addItem(obj);
                cont++;
                aux.add(obj);
            }
        }catch(SQLException e){
            System.out.println("Error en getMaterias");
        }
        this.numAreas=cont;
        //mat=new Object[cont][];
        //materias=new ArrayList[cont];
        for(int i=0;i<cont;i++){
            materias.add(new ArrayList<ArrayList>());
        }
       /* for(int i=0;i<aux.size();i++){
            (materias.get(i)).add(aux.get(i));
            System.out.println(aux.get(i));
        }*/
        IND=true;
    }
    
    public void setMaterias(){
        jList1.setModel(modelo);
        jList2.setModel(modelo2);
        setList(1);
    }
    
    
    //Modificar
    
    public void setInfo(String nom){
        this.acc=this.MODIFICAR;
        int []cont=new int[numAreas];
        //materias=new ArrayList<ArrayList>();
        String sql="SELECT INDICE,NOMBRE,A_PATERNO,A_MATERNO,RFC,GRADO,EMAIL FROM PROFESOR WHERE NOMBRE||' '||A_PATERNO||' '||A_MATERNO='"+nom+"'";
        ResultSet con = conexion.getConsulta(sql);
        try{
            con.next();
            indice=con.getInt("INDICE");
            nomAnterior=con.getString("NOMBRE")+" "+con.getString("A_PATERNO")+" "+con.getString("A_MATERNO");
            jTextField1.setText(con.getString("NOMBRE"));
            jTextField2.setText(con.getString("A_PATERNO"));
            jTextField3.setText(con.getString("A_MATERNO"));
            jTextField4.setText(con.getString("EMAIL"));
            jTextField5.setText(con.getString("RFC"));
            jComboBox4.setSelectedItem(con.getString("GRADO"));
            
            con.close();/****/
            String sql2="SELECT DISTINCT PUESTO,NOMBRE_ACADEMIA FROM ACADEMIA_PROFESOR WHERE NOMBREP='"+nom+"'";
            ResultSet res2=conexion.getConsulta(sql2);
            while(res2.next()){
                if(res2.getString("PUESTO").equals("Presidente")==true || res2.getString("PUESTO").equals("Secretario")==true){
                    jComboBox1.setSelectedItem(res2.getObject("PUESTO"));
                    jComboBox5.setSelectedItem(res2.getObject("NOMBRE_ACADEMIA"));
                    jComboBox5.setEnabled(true);
                    break;
                }
                else{
                    jComboBox1.setSelectedItem(res2.getObject("PUESTO"));
                    jComboBox5.setSelectedItem(res2.getObject("NOMBRE_ACADEMIA"));
                    jComboBox5.setEnabled(false);
                }
            }
        }catch(SQLException e){
            System.out.println("Error en Setinfo 1");
        }
        
        String sql2="SELECT MATERIA,ACADEMIA FROM PROFESOR_UNIDAD WHERE NOMBRE='"+nom+"'";
        ResultSet con2 = conexion.getConsulta(sql2);
        try{
            while(con2.next()){
                Object [] a = new Object[2];
                a[0]=con2.getObject("MATERIA");
                a[1]=con2.getObject("ACADEMIA");
                for(int i=0;i<this.numAreas;i++){
                    if(jComboBox3.getItemAt(i).equals(a[1])==true){
                        (materias.get(i)).add(a[0]);
                    }
                }
            }
        }catch(SQLException e){
            System.out.println("Error en SetInfo 2");
        }
        setList(1);
        IND=true;
        setList(2);
    }
    
    private void setList(int numLista){
        String area="";
        int val=0;
        this.limpiar(numLista);
        if(numLista==1){
            String sql="SELECT NOMBRE FROM UNIDAD_APRENDIZAJE WHERE ACADEMIA='"+jComboBox3.getSelectedItem()+"'";
            ResultSet res=conexion.getConsulta(sql);
            try{
                while(res.next()){
                    Object a =res.getObject("NOMBRE");
                    if(modelo2.contains(a)==false){
                        modelo.addElement(a);
                    }
                   
                }
            }catch(SQLException e){
                System.out.println("Error en SetList");
            }
        }
        if(numLista==2){
            area=jComboBox2.getSelectedItem().toString();
            val=jComboBox2.getSelectedIndex();
            /*for(int i=0;i<numAreas;i++){
                if(mat[i][0].equals(area)==true){
                    break;
                }
                val++;
            }*/
            if(IND==true){
                for(int i=0;i<(materias.get(val)).size();i++){
                    modelo2.addElement((materias.get(val)).get(i));
                    if(modelo.contains((materias.get(val)).get(i))==true){
                        modelo.removeElement((materias.get(val)).get(i));
                    }
                }
            }
        }

    }
    
    private void limpiar(int numLista){
        if(numLista==1){
            if(modelo.size()!=0){
                modelo.removeAllElements();
            }
        }
        else{
            if(modelo2.size()!=0){
                modelo2.removeAllElements();
            }
        }
    }
    
    public void initConexion(){
        conexion=Acceso.conexion;
    }
    
    public void setCard(JPanel car){
        cartas=car;
    }

    
    private void jComboBox3_actionPerformed(ActionEvent e) {
        try{
            setList(1);
            jComboBox2.setSelectedItem(jComboBox3.getSelectedItem());
        }catch(NullPointerException a){
            
        }
    }

    private void jComboBox2_actionPerformed(ActionEvent e) {
        try{
            setList(2);
            jComboBox3.setSelectedItem(jComboBox2.getSelectedItem());
        }catch(NullPointerException a){
            
        }
        
    }
    

    private void agregarMat(MouseEvent e) {
        if(jList1.getSelectedIndex()!=-1 && modelo.size()!=0){
            Object []a =jList1.getSelectedValues();
            
            for(int i=0;i<a.length;i++){
                modelo.removeElement(a[i]);
            }
            
            for(int i=0;i<a.length;i++){
                modelo2.addElement(a[i]);
            }
            
            int r=jComboBox3.getSelectedIndex();
            for(int i=0;i<modelo2.getSize();i++){
                if((materias.get(r)).contains(modelo2.get(i))==false){
                    (materias.get(r)).add(modelo2.get(i));
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(this,"No Ha Seleccionado Materias","Atencion",JOptionPane.WARNING_MESSAGE);
        }
        
    }


    private void quitarMat(MouseEvent e) {
        if(jList2.getSelectedIndex()!=-1 && modelo2.size()!=0){
            Object []a =jList2.getSelectedValues();
            for(int i=0;i<a.length;i++){
                modelo2.removeElement(a[i]);
            }
            for(int i=0;i<a.length;i++){
                modelo.addElement(a[i]);
            }
            int r=jComboBox3.getSelectedIndex();
            
            for(int i=0;i<a.length;i++){
                if((materias.get(r)).contains(a[i])==true){
                    (materias.get(r)).remove(a[i]);
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(this,"No Ha Seleccionado Materias","Atencion",JOptionPane.WARNING_MESSAGE);
        }
        
       
        
    }

    private void Ingresar(int acc) {
        String nomb=jTextField1.getText();
        String rfc=jTextField5.getText();
        String ap=jTextField2.getText();
        String am=jTextField3.getText();
        String grado=jComboBox4.getSelectedItem().toString();
        String puesto=jComboBox1.getSelectedItem().toString();
        String email=jTextField4.getText();
        String acad="";
        String mater="";
        String sem=setSemestre();
        int r,c;
        switch(acc){
        case AGREGAR:
                int ind=this.getIndice();
                String sql="INSERT INTO PROFESOR VALUES("+ind+",'"+rfc+"','"+nomb+"','"+ap+"','"+am+"','"+grado+"','"+email+"')";
                conexion.Ingresarsentencia(sql); 
            for(r=0;r<this.numAreas;r++){
                for(c=0;c<(materias.get(r)).size();c++){
                       // acad=mat[r][0].toString();
                       // mater=mat[r][c].toString();
                        acad=jComboBox3.getItemAt(r).toString();
                        mater=(materias.get(r)).get(c).toString();
                    
                    if(jComboBox5.isEnabled()==false){
                        String sql2="INSERT INTO ACADEMIA_PROFESOR VALUES("+ind+",'"+acad+"','"+nomb+" "+ap+" "+am+"','"+puesto+"')";
                        conexion.Ingresarsentencia(sql2);
                    }else{
                        System.out.println(acad+":"+jComboBox5.getSelectedItem());
                        if(acad.equals(jComboBox5.getSelectedItem().toString())==true){
                            puesto=jComboBox1.getSelectedItem().toString();
                            String sql2="INSERT INTO ACADEMIA_PROFESOR VALUES("+ind+",'"+acad+"','"+nomb+" "+ap+" "+am+"','"+puesto+"')";
                            conexion.Ingresarsentencia(sql2);
                        }else{
                            puesto="Ninguno";
                            String sql2="INSERT INTO ACADEMIA_PROFESOR VALUES("+ind+",'"+acad+"','"+nomb+" "+ap+" "+am+"','"+puesto+"')";
                            conexion.Ingresarsentencia(sql2);
                        }
                    }
                        int idmat=this.getIDUnidad(mater);
                        
                        String sql3="INSERT INTO PROFESOR_UNIDAD VALUES("+ind+","+idmat+",'"+nomb+" "+ap+" "+am+"','"+mater+"','"+acad+"','"+sem+"')";
                        conexion.Ingresarsentencia(sql3);
                }
            }
            JOptionPane.showMessageDialog(this,"Profesor Agregado","Profesor", JOptionPane.INFORMATION_MESSAGE);
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jComboBox2.removeAllItems();
            jComboBox3.removeAllItems();
            String sqla="COMMIT";
            conexion.Ingresarsentencia(sqla);
            modelo.removeAllElements();
            modelo2.removeAllElements();
            for(int i=0;i<materias.size();i++){
                (materias.get(i)).clear();
            }
            scroll.getViewport().setViewPosition(new Point(0,0));
            CardLayout cl = (CardLayout)(cartas.getLayout());
            cl.show(cartas,"Menu");
            cons.limpiar();
            cons.setInfoTable();
            cons.setVisible(true);
            break;
        case MODIFICAR:
            /*** REVISAR*/
            String sql01="DELETE FROM ACADEMIA_PROFESOR WHERE INDICE='"+indice+"'";
            conexion.Ingresarsentencia(sql01);
            
            try{
                String sql1="DELETE FROM PROFESOR_UNIDAD WHERE NOMBRE='"+nomAnterior+"' AND SEMESTRE='"+this.setSemestre()+"'";
                conexion.Ingresarsentencia(sql1);
            }catch(Exception e){
             System.out.println("Error en Delete");   
            }
            String sqlx="UPDATE PROFESOR SET RFC='"+rfc+"',NOMBRE='"+nomb+"',A_PATERNO='"+ap+"',A_MATERNO='"+am+"',GRADO='"+grado+"',EMAIL='"+email+"' " +
                "WHERE INDICE="+indice+"";
            conexion.Ingresarsentencia(sqlx);
            
            for(r=0;r<this.numAreas;r++){
                for(c=0;c<(materias.get(r)).size();c++){
                    acad=jComboBox3.getItemAt(r).toString();
                    mater=(materias.get(r)).get(c).toString();
                    
                    if(jComboBox5.isEnabled()==false){
                        String sql2="INSERT INTO ACADEMIA_PROFESOR VALUES("+indice+",'"+acad+"','"+nomb+" "+ap+" "+am+"','"+puesto+"')";
                        conexion.Ingresarsentencia(sql2);
                    }else{
                        if(acad.equals(jComboBox5.getSelectedItem())==true){
                            String sql2="INSERT INTO ACADEMIA_PROFESOR VALUES("+indice+",'"+acad+"','"+nomb+" "+ap+" "+am+"','"+puesto+"')";
                            conexion.Ingresarsentencia(sql2);
                           
                        }else{
                            puesto="Ninguno";
                            String sql2="INSERT INTO ACADEMIA_PROFESOR VALUES("+indice+",'"+acad+"','"+nomb+" "+ap+" "+am+"','"+puesto+"')";
                            conexion.Ingresarsentencia(sql2);
                            
                        }
                    }
                    
                    int idmat=this.getIDUnidad(mater);
                    String sql3="INSERT INTO PROFESOR_UNIDAD VALUES("+indice+","+idmat+",'"+nomb+" "+ap+" "+am+"','"+mater+"','"+acad+"','"+sem+"')";
                    conexion.Ingresarsentencia(sql3);
                        
                }
            }
            String sql4="UPDATE PROFESOR_UNIDAD SET NOMBRE='"+nomb+" "+ap+" "+am+"' WHERE INDICE="+indice+" AND NOMBRE='"+this.nomAnterior+"'";
            conexion.Ingresarsentencia(sql4);
            JOptionPane.showMessageDialog(this,"Modificacion Exitosa","Profesor", JOptionPane.INFORMATION_MESSAGE);
            scroll.getViewport().setViewPosition(new Point(0,0));
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jComboBox2.removeAllItems();
            jComboBox3.removeAllItems();
            String sql1="COMMIT";
            conexion.Ingresarsentencia(sql1);
            modelo.removeAllElements();
            modelo2.removeAllElements();
            for(int i=0;i<materias.size();i++){
                (materias.get(i)).clear();
            }
            CardLayout c2 = (CardLayout)(cartas.getLayout());
            c2.show(cartas,"Menu");
            cons.limpiar();
            cons.setInfoTable();
            cons.setVisible(true);
            break;
        }
        
    }
    
    private String setSemestre(){
        Calendar cal = new GregorianCalendar();
        String sem="";
        int año=cal.get(Calendar.YEAR);
        int mes=cal.get(Calendar.MONTH);
        if(mes<=6){
            sem=año+"A";
        }
        else{
            sem=año+"B";
        }
        return sem;
    }
    
    public int getIndice(){
        String sql="SELECT MAX(INDICE) M FROM PROFESOR";
        int indice=0;
        ResultSet res= conexion.getConsulta(sql);
        try{
            res.next();
            indice=res.getInt("M")+1;
        }catch(SQLException e){
            indice=1;
        }
        System.out.println(indice);
        return indice;
    }
    
    public int getIDUnidad(String nomMat){
        String sql="SELECT ID_UAPRENDIZAJE FROM UNIDAD_APRENDIZAJE WHERE NOMBRE='"+nomMat+"'";
        ResultSet res=conexion.getConsulta(sql);
        int ind=0;
        try{
            res.next();
            ind=res.getInt("ID_UAPRENDIZAJE");
        }catch(SQLException e){
            System.out.println("Error en getIDUnidad");
        }
        return ind;
    }
    
    public void setConsulta(Consulta a){
        cons=a;
    }
    
    public void setScroll(JScrollPane a){
        scroll=a;
    }

    private void salir(MouseEvent e) {
        scroll.getViewport().setViewPosition(new Point(0,0));
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        this.acc=0;
        modelo.removeAllElements();
        modelo2.removeAllElements();
        CardLayout c2 = (CardLayout)(cartas.getLayout());
        c2.show(cartas,"Menu");
        cons.limpiar();
        cons.setInfoTable();
        cons.setVisible(true);
        
    }

    private void jComboBox1_actionPerformed(ActionEvent e) {
        if(jComboBox1.getSelectedIndex()==2){
            jComboBox5.setEnabled(false);
        }
        else{
            jComboBox5.setEnabled(true);
        }
    }
    
    private void validacion(){
        String nomb=jTextField1.getText()+" "+jTextField2.getText()+" "+jTextField3.getText();
        String puesto=jComboBox1.getSelectedItem().toString();
        String acad="";
        if(jComboBox5.getItemCount()==0){
            acad="";
        }else{
            acad=jComboBox5.getSelectedItem().toString();
        }
        if(campos()==true){
            if(comprobarUser(nomb,this.acc)==true){
                JOptionPane.showMessageDialog(this,"Error, Ya hay un profesor registrado con ese nombre","Error",JOptionPane.ERROR_MESSAGE);
            }else{
                if(comprobarPuesto(acad,puesto,this.acc)==true){
                    JOptionPane.showMessageDialog(this,"Error, Ya hay un "+puesto+" en la academia: "+acad,"Error",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    this.Ingresar(acc);
                }
            }
        }
    }
    
    private boolean comprobarUser(String nomb,int acc){
        boolean ind=false;
        switch(acc){
        case AGREGAR:
            String sql="SELECT INDICE FROM PROFESOR WHERE NOMBRE||' '||A_PATERNO||' '||A_MATERNO='"+nomb+"'";
            ResultSet res=conexion.getConsulta(sql);
            try{
                res.next();
                res.getString("INDICE");
                ind=true;
            }catch(SQLException e){
                ind=false;
            }
            break;
        case MODIFICAR:
            String sql2="SELECT INDICE FROM PROFESOR WHERE NOMBRE||' '||A_PATERNO||' '||A_MATERNO='"+nomb+"' AND INDICE="+indice;
            ResultSet res2=conexion.getConsulta(sql2);
            System.out.println(sql2);
            try{
                res2.next();
                res2.getString("INDICE");
                ind=false;
            }catch(SQLException e){
                ind=true;
                if(comprobarUser(nomb,1)==false){
                    ind=false;
                }
            }
            break;
        }
        return ind;
    }
    
    private boolean comprobarPuesto(String acad,String puest,int acc){
        boolean ind=false;
        switch(acc){
        case AGREGAR:
            if(puest.equals("Ninguno")==false){
                String sql="SELECT COUNT(PUESTO) A FROM ACADEMIA_PROFESOR WHERE NOMBRE_ACADEMIA='"+acad+"' AND PUESTO='"+puest+"'";
                ResultSet res=conexion.getConsulta(sql);
                try{
                    res.next();
                    if(res.getInt("A")==1)
                        ind=true;
                }catch(SQLException e){
                    ind=false;
                }
            }else{
                ind=false;
            }
            break;
        case MODIFICAR:
            if(puest.equals("Ninguno")==false){
                String sql="SELECT COUNT(PUESTO) A FROM ACADEMIA_PROFESOR WHERE NOMBRE_ACADEMIA='"+acad+"' AND PUESTO='"+puest+"' AND INDICE="+indice;
                ResultSet res=conexion.getConsulta(sql);
                try{
                    res.next();
                    if(res.getInt("A")==1)
                        ind=false;
                }catch(SQLException e){
                    ind=true;
                    if(comprobarPuesto(acad,puest,1)==false){
                        ind=false;
                    }
                }
            }else{
                ind=false;
            }
            break;
        }
       return ind;
    }
    
    private boolean campos(){
        boolean ind=true;
        String a=jTextField1.getText();
        String b=jTextField2.getText();
        String c=jTextField3.getText();
        String d=jTextField4.getText();
        String e=jTextField5.getText();
        if(a.equals("")==true || b.equals("")==true || c.equals("")==true ||d.equals("")==true || e.equals("")==true){
            JOptionPane.showMessageDialog(this,"Error, faltan campos por llenar","Error",JOptionPane.ERROR_MESSAGE);
            ind=false;
        }
        else{
            ind=false;
            for(int i=0;i<materias.size();i++){
                for(int j=0;j<(materias.get(i)).size();j++){
                    ind=true;
                }
            }
            if(ind==false){
        JOptionPane.showMessageDialog(this,"Error, no ha ingresado materias","Error",JOptionPane.ERROR_MESSAGE);
            }
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
