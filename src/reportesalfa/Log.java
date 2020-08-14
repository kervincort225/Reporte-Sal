/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reportesalfa;
import java.io.*;
import java.util.Date;
import java.util.Calendar;
/**
 *
 * @author Gabriel Valenzuela
 */
public class Log {
    public static void log(String mensaje)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(System.currentTimeMillis()));
        int mes = c.get(Calendar.MONTH)+1;
        FileOutputStream fo;
    	try
    	{
            PrintWriter escribir = new PrintWriter(new FileWriter("ReporteSalfa_"+c.get(Calendar.YEAR)+mes+c.get(Calendar.DAY_OF_MONTH) +".txt",true));
            escribir.println(new Date(System.currentTimeMillis())+" - "+mensaje);    			
            escribir.close();
    	}
    	catch(IOException e)
    	{
    		
    	}
    }
}