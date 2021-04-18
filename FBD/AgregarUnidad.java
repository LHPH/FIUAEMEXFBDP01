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
import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class AgregarUnidad extends JPanel {
    
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JComboBox jComboBox1 = new JComboBox();
    private JLabel jLabel4 = new JLabel();
    private JTextField jTextField1 = new JTextField();
    private JTextField jTextField2 = new JTextField();
    private JTextField jTextField3 = new JTextField();
    private JSeparator jSeparator1 = new JSeparator();
    private JLabel jLabel5 = new JLabel();
    private JButton jButton1 = new JButton();
    
    public static final int Agregar = 0;
    public static final int Modificar = 1;
    Consulta3 cons;
    int acc=0;
    Conexion conexion;
    JPanel cartas;
    int index;
    private JButton jButton2 = new JButton();
    private JLabel jLabel6 = new JLabel(new ImageIcon("C:\\Users\\JDK7\\Desktop\\proyectFBD\\pics_proyecto\\buholibrosjuveniles.gif"));

    JPanel jbInit(){
        this.setLayout( null );
        this.setSize(new Dimension(540, 355)); //527,265
        this.setOpaque(false);
        
        jLabel1.setText("Nombre: ");
        jLabel1.setBounds(new Rectangle(15, 70, 80, 25));
        jLabel1.setFont(new Font("Arial",1,12));
        
        jLabel2.setText("Clave:");
        jLabel2.setBounds(new Rectangle(15, 125, 145, 25));
        jLabel2.setFont(new Font("Arial",1,12));
        
        jLabel3.setText("Numero de Horas:");
        jLabel3.setBounds(new Rectangle(240, 120, 135, 30));
        jLabel3.setFont(new Font("Arial",1,12));
        
        jComboBox1.setBounds(new Rectangle(310, 70, 195, 25));
        
        jLabel4.setText("Academia:");
        jLabel4.setBounds(new Rectangle(245, 70, 75, 25));
        jLabel4.setFont(new Font("Arial",1,12));
        
        jLabel5.setText("Unidad de Aprendizaje");
        jLabel5.setBounds(new Rectangle(185, 5, 185, 30));
        jLabel5.setFont(new Font("Arial",1,14));
        
        jTextField1.setBounds(new Rectangle(70, 70, 160, 25));
        jTextField1.setFont(new Font("Arial",0,12));
        jTextField1.setDocument(new DocumentoJTF(0));
        
        jTextField2.setBounds(new Rectangle(70, 125, 160, 25));
        jTextField2.setFont(new Font("Arial",0,12));
        jTextField2.setDocument(new DocumentoJTF(jTextField2,0,6));
        
        jTextField3.setBounds(new Rectangle(350, 125, 110, 25));
        jTextField3.setFont(new Font("Arial",0,12));
        jTextField3.setDocument(new DocumentoJTF(jTextField3,2,3));
        
        jSeparator1.setBounds(new Rectangle(0, 40, 540, 10));
        
        jButton1.setText("Agregar");
        jButton1.setBounds(new Rectangle(360, 205, 100, 25));

        jButton1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    validacion();
                }
            });
        jButton2.setText("Salir");
        jButton2.setBounds(new Rectangle(360, 270, 95, 25));

        jButton2.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    salir(e);
                }
            });
        //jLabel6.setText("jLabel6");
        jLabel6.setBounds(new Rectangle(75, 160, 165, 185));
        this.add(jLabel6, null);
        this.add(jButton2, null);
        this.add(jButton1, null);
        this.add(jLabel5, null);
        this.add(jSeparator1, null);
        this.add(jTextField3, null);
        this.add(jTextField2, null);
        this.add(jTextField1, null);
        this.add(jLabel4, null);
        this.add(jComboBox1, null);
        this.add(jLabel3, null);
        this.add(jLabel2, null);
        this.add(jLabel1, null);
        return this;
    }
    
    public void initConexion(){
        conexion=Acceso.conexion;
    }
    
    public void setCard(JPanel car){
        cartas=car;
    }
    
    public void getAcademia(){
        String sql="SELECT NOMBRE_ACADEMIA FROM ACADEMIA";
        ResultSet res = conexion.getConsulta(sql);
        try{
            while(res.next()){
                Object obj = res.getObject("NOMBRE_ACADEMIA");
                jComboBox1.addItem(obj);
            }
        }catch(SQLException e){
        }
    }
    
    public int setIndice(){
        String sql="SELECT MAX(ID_UAPRENDIZAJE) M FROM UNIDAD_APRENDIZAJE";
        int indice=0;
        ResultSet res= conexion.getConsulta(sql);
        try{
            res.next();
            indice=res.getInt("M")+1;
        }catch(SQLException e){
            indice=1;
        }
        return indice;
    }
    
    public int getIndice(String nomb){
        int indice=0;
        String sql="SELECT ID_UAPRENDIZAJE FROM UNIDAD_APRENDIZAJE WHERE NOMBRE='"+nomb+"'";
        ResultSet res=conexion.getConsulta(sql);
        try{
            res.next();
            indice=res.getInt("ID_UAPRENDIZAJE");
        }catch(SQLException e){}
        return indice;
    }
    
    public void setInfo(String nomb){
        this.acc=this.Modificar;
        jButton1.setText("Modificar");
        String sql="SELECT ID_UAPRENDIZAJE,NOMBRE,CLAVE_UA,T_HORAS,ACADEMIA FROM UNIDAD_APRENDIZAJE WHERE NOMBRE='"+nomb+"'";
        ResultSet res=conexion.getConsulta(sql);
        try{
            res.next();
            index=res.getInt("ID_UAPRENDIZAJE");
            jTextField1.setText(res.getString("NOMBRE"));
            jTextField2.setText(res.getString("CLAVE_UA"));
            jTextField3.setText(res.getString("T_HORAS"));
            jComboBox1.setSelectedItem(res.getString("ACADEMIA"));
        }catch(SQLException e){
            System.out.println("Error en setInfo UA");
        }
    }

    public void insertar(){
        String nomb=jTextField1.getText();
        String clv=jTextField2.getText();
        String acad=jComboBox1.getSelectedItem().toString();
        String horas=jTextField3.getText();
        switch(acc){
        case Agregar:
            int ind=this.setIndice();
            String sql="INSERT INTO UNIDAD_APRENDIZAJE VALUES("+ind+",'"+nomb+"','"+clv+"','"+horas+"','"+acad+"')";
            conexion.Ingresarsentencia(sql);
int opc= JOptionPane.showConfirmDialog(this,"Unidad de Aprendizaje Agregada\n ¿Desea Agregar Otra Unidad de Aprendizaje?","Agregar Unidad de Aprendizaje",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
            if(opc==JOptionPane.YES_OPTION){
                jTextField1.setText("");
                jTextField2.setText("");
                jTextField3.setText("");
            }
            else{
                this.acc=this.Agregar;
                CardLayout cl = (CardLayout)(cartas.getLayout());
                cl.show(cartas,"Menu");
            }
            break;
        case Modificar:
            String sql2="UPDATE PROFESOR_UNIDAD SET MATERIA='"+nomb+"' WHERE ID_UAPRENDIZAJE="+index+"";
            conexion.Ingresarsentencia(sql2);
String sql3="UPDATE UNIDAD_APRENDIZAJE SET NOMBRE='"+nomb+"',CLAVE_UA='"+clv+"',T_HORAS='"+horas+"',ACADEMIA='"+acad+"' WHERE ID_UAPRENDIZAJE="+index+"";
            conexion.Ingresarsentencia(sql3);
            JOptionPane.showMessageDialog(this,"Unidad de Aprendizaje Actualizada","Unidad de Aprendizaje",JOptionPane.INFORMATION_MESSAGE);
            CardLayout cl = (CardLayout)(cartas.getLayout());
            cl.show(cartas,"Menu");
            break;
        }
    }

    public void setConsulta3(Consulta3 a){
        cons=a;
    }

    private void salir(MouseEvent e) {
        this.acc=Agregar;
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jComboBox1.removeAllItems();
        CardLayout cl = (CardLayout)(cartas.getLayout());
        cl.show(cartas,"Menu");
        cons.limpiar();
        cons.setInfoTable();
        cons.setVisible(true);
    }
    
    private void validacion(){
        if(compExistencia(acc)==true){
            JOptionPane.showMessageDialog(this,"Error ya hay una unidad de aprendizaje con ese nombre","Error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            if(compExistencia2(acc)==true){
                JOptionPane.showMessageDialog(this,"Error,la clave ya es usada por otra unidad de aprendizaje","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                this.insertar();
            }
        }
    }
    
    private boolean compExistencia(int acc){
        String nomb=jTextField1.getText();
        boolean ind=false;
        switch(acc){
        case Agregar:
            String sql="SELECT NOMBRE FROM UNIDAD_APRENDIZAJE WHERE NOMBRE='"+nomb+"'";
            ResultSet res=conexion.getConsulta(sql);
            try{
                res.next();
                res.getString("NOMBRE");
                ind=true;
            }catch(SQLException e){
                ind=false;
            }
            break;
        case Modificar:
            String sql2="SELECT NOMBRE FROM UNIDAD_APRENDIZAJE WHERE NOMBRE='"+nomb+"' AND ID_UAPRENDIZAJE="+index;
            ResultSet res2=conexion.getConsulta(sql2);
            try{
                res2.next();
                res2.getString("NOMBRE");
                ind=false;
            }catch(SQLException e){
                ind=true;
                if(compExistencia(1)==false){
                    ind=false;
                }
            }
            
            break;
        }
        return ind;
    }
    
    private boolean compExistencia2(int acc){
        String clv=jTextField2.getText();
        boolean ind=false;
        switch(acc){
        case Agregar:
            String sql="SELECT NOMBRE FROM UNIDAD_APRENDIZAJE WHERE CLAVE_UA='"+clv+"'";
            ResultSet res=conexion.getConsulta(sql);
            try{
                res.next();
                res.getString("NOMBRE");
                ind=true;
            }catch(SQLException e){
                ind=false;
            }
            break;
        case Modificar:
            String sql2="SELECT NOMBRE FROM UNIDAD_APRENDIZAJE WHERE CLAVE_UA='"+clv+"' AND ID_UAPRENDIZAJE="+index;
            ResultSet res2=conexion.getConsulta(sql2);
            try{
                res2.next();
                res2.getString("NOMBRE");
                ind=false;
            }catch(SQLException e){
                ind=true;
                if(compExistencia2(1)==false){
                    ind=false;
                }
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

class DocumentoJTF extends PlainDocument{
    
    public static final int Mayusculas=0;
    public static final int Minusculas=1;
    public static final int Limitador=2;
    public static final int NoDigitos=3;
    
    public int Tipo=0;
    public int Limite=0;
    public int Num=0;
    public String Str2="";
    private JTextField editor;
    
    public DocumentoJTF(int tipo){
        this.Tipo=tipo;
    }
    
    public DocumentoJTF(JTextField editor,int tipo,int limite){
        this.Tipo=tipo;
        this.editor=editor;
        this.Limite=limite;
    }   
    
    public DocumentoJTF(JTextField editor,int tipo){
        this.Tipo=tipo;
        this.editor=editor;
    }   
    
    
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        switch(this.Tipo){
        case Mayusculas:
            if(this.Limite==0){
                super.insertString(offset, str.toUpperCase(), attr);
            }
            else{
                if(editor.getText().length()+str.length()>this.Limite){
                    return ;
                }
                else{
                        super.insertString(offset,str.toUpperCase(), attr);
                    }
                }
            break;
        case Minusculas:
            break;
        case Limitador:
            if(editor.getText().length()+str.length()>this.Limite){
                return ;
            }
            else{
                super.insertString(offset,str, attr);
            }
            break;
        case NoDigitos:
            for(int i=0;i<str.length();i++){
                if(Character.isDigit(str.charAt(i))==false){
                    super.insertString(offset,str, attr);
                }
                else{
                    return;
                }
            }
            break;
        
        }
                 
       }
}

