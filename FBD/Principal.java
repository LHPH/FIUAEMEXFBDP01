package FBD;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.Rectangle;

import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class Principal extends JFrame {
    
    //Paneles
    Acceso acc = new Acceso();
    Menu menu = new Menu();
    AgregarReunion agregarR = new AgregarReunion();
    ConsultaP consultaP = new ConsultaP();
    AgregarProfesor agregarP = new AgregarProfesor();
    ConsultaAcd consultaA = new ConsultaAcd();
    AgregarUnidad agregarU = new AgregarUnidad();
    ConsultarUA consultaU = new ConsultarUA();
    Asistencia asistencia= new Asistencia();
    ConsultaR consultaR = new ConsultaR();

    //Dialogos
    
    JButton boton1;
    JScrollPane scroll,scroll2,scroll3,scroll4,scroll5;
    Conexion conexion=Acceso.conexion;
    JPanel cartas;
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel(new ImageIcon("C:\\Users\\JDK7\\Desktop\\proyectFBD\\pics_proyecto\\UNI.jpg"));
    private JLabel jLabel4 = new JLabel(new ImageIcon("C:\\Users\\JDK7\\Desktop\\proyectFBD\\pics_proyecto\\fiuaemex.jpg"));


    public Principal() {
        
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(400,200);
        this.setResizable(false);
        this.setTitle("Sistema de Administración de Actas de Academia");
        
      /*  scroll= new JScrollPane(p.jbInit());
        scroll.setBounds(new Rectangle(639,480));
        
       // boton1=p.getButton1();
       /* boton1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    ingresarReunion(e);
                }
            });*/
        
        /////////////////////////////////
        
        cartas = new JPanel();
        cartas.setLayout(new CardLayout());
        cartas.setBounds(new Rectangle(50, 55, 540, 355));

        jLabel1.setText("Universidad Autonoma Del Estado De Mexico");
        jLabel1.setForeground(Color.WHITE);
        jLabel1.setFont(new Font("Arial",1,16));
        jLabel1.setBounds(new Rectangle(155, 5, 365, 30));
        jLabel2.setText("Facultad de Ingenieria");
        jLabel2.setForeground(Color.WHITE);
        jLabel2.setFont(new Font("Arial",1,15));
        jLabel2.setBounds(new Rectangle(240, 30, 180, 20));
       // jLabel3.setText("jLabel3");
        jLabel3.setBounds(new Rectangle(60, 5, 70, 50));
       // jLabel4.setText("jLabel4");
        jLabel4.setBounds(new Rectangle(510, 0, 60, 50));
        this.setLayout( null );
        this.setSize(new Dimension(631, 467)); //645 485

        this.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    this_windowClosing(e);
                }
            });
        acc.setCard(cartas);
        menu.setCard(cartas);
        menu.setProf(agregarP,consultaP);
        menu.setUA(agregarU,consultaU);
        menu.setAcd(consultaA);
        menu.setReun(agregarR,asistencia,consultaR);
        
        agregarP.setCard(cartas);
        consultaP.setCard(cartas);
        agregarR.setCart(cartas);
        consultaA.setCard(cartas);
        agregarU.setCard(cartas);
        consultaU.setCard(cartas);
        asistencia.setCard(cartas);
        consultaR.setCard(cartas);
        
        acc.setFrame(this);
        menu.setFrame(this);

        cartas.add("Acceso",acc.jbInit());

        this.getContentPane().add(jLabel4, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(jLabel1, null);
        this.getContentPane().add(cartas);
        //this.getContentPane().add(scroll,null);
        
    }
    
    public void initPaneles(){
        //Iniciar la conexion en los paneles
        menu.initConexion();
        agregarP.initConexion();
        consultaP.initConexion();
        agregarR.initConexion();
        consultaA.initConexion();
        agregarU.initConexion();
        consultaU.initConexion();
        asistencia.initConexion();
        consultaR.initConexion();
        
        scroll = new JScrollPane(agregarP.jbInit());
        agregarP.setScroll(scroll);
        scroll2=new JScrollPane(consultaP.jbInit());
        consultaP.setScroll(scroll2);
        scroll3=new JScrollPane(consultaA.jbInit());
        scroll4=new JScrollPane(agregarR.jbInit());
        agregarR.setScroll(scroll4);
        scroll5=new JScrollPane(consultaR.jbInit());
        consultaR.setScroll(scroll5);
        
        
        
        
        cartas.add("Menu",menu.jbInit());
        cartas.add("Consultar P",scroll2);
        cartas.add("Agregar R",scroll4);
        cartas.add("Agregar Profesor",scroll);
        cartas.add("Consulta A",scroll3);
        cartas.add("Agregar U",agregarU.jbInit());
        cartas.add("Consulta U",consultaU.jbInit());
        cartas.add("Asistencia",asistencia.jbInit());
        cartas.add("Consulta R",scroll5);
        
    }
    
  
    
    public static void main(String [] arg){
        try
        {
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
           e.printStackTrace();
        }
        Principal pr = new Principal();
        pr.setLayout(new BorderLayout());
        pr.add(new GradientPane(),BorderLayout.CENTER);
        pr.setVisible(true);
    }

    private void this_windowClosing(WindowEvent e) {
            
    }
}
