package FBD;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class P {
    public P() {
        super();
    }
    
    private String setSemestre(){
        Calendar cal = new GregorianCalendar();
        String sem="";
        int a�o=cal.get(Calendar.YEAR);
        int mes=cal.get(Calendar.MONTH);
        if(mes<=6){
            sem=a�o+"A";
        }
        else{
            sem=a�o+"B";
        }
        return sem;
    }

    public static void main(String[] args) {
        P p = new P();
        System.out.println(p.setSemestre());
    }
}
