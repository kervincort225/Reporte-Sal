/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportesalfa;
import java.io.InputStream;
import java.lang.Object;
import java.net.URL;
import java.net.URLConnection;
import javax.crypto.Mac;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.MarkedObject;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLDocument.HTMLReader;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author pcontador
 */
public class ReporteSalfa extends Thread {

    /**
     * @param args the command line arguments
     */
    static String key= "pek-e9423d0cd736c81e767a3d9dceaef1a613100ad7e6799a44e56f9c3e7414ea92ad94927891aaf6b43e73393edb7ed4208d99f0f740131cb5098c8b8af312dd2a";
    static String token="pt-0Wvx",slug = "ralenti";
    static String URLG="";
    static boolean existe=false;
    static boolean Flag=false;
    static boolean Flag2=false;
    static String minKM="0",maxKM="0", minH="0",maxH="0",KmL="0",LHr="0";
    //String raw_sig;

    
    private URL    url      = null;
    private String location = null;
    private FileOutputStream fileOutputStream = null;
    private InputStream      inputStream      = null;
    static int len = 0;
    static int off = 0;
    
    
    public static String Encripta(String json,String Grafico)
    {    
        String valor="";
        try{
            
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret = new SecretKeySpec(key.getBytes(),"HmacSHA256");
            mac.init(secret);
            byte[] digest = mac.doFinal(json.getBytes());        
            //String enc = new String(digest);
            System.out.println("256_1"); 
            System.out.println(digest); 
            String SignatureA = new String(Base64.encodeBase64(digest));
            System.out.println("BinarioA"); 
            System.out.println(SignatureA);
            valor="https://charturl.com/i/"+token+"/"+Grafico+"?d="+URLEncoder.encode(json,"UTF-8")+"&s="+URLEncoder.encode(SignatureA, "UTF-8");
            System.out.println("https://charturl.com/i/"+token+"/"+slug+"?d="+URLEncoder.encode(json,"UTF-8")+"&s="+URLEncoder.encode(SignatureA, "UTF-8"));        
       
            }catch(Exception e) {
                System.out.println(e.getMessage());            
            }    
     return valor;        
    }
    
    
 
private static void showLinks(String content) throws Exception {
    if(content.contains("https://charturl.com/i/pt-0Wvx")){
    String url=content;
    URLConnection con = new URL(url).openConnection();
    System.out.println("Orignal URL: " + con.getURL());
    con.connect();
    System.out.println("Connected URL: " + con.getURL());
    InputStream is = con.getInputStream();
    System.out.println("Redirected URL: " + con.getURL());
    URLG=con.getURL().toString();
    is.close();
    }else URLG="";
}

public static void moverpdf(String Arch){
      File f;
        
          String files="";
//          String ruta="C:\\\\Reportes1";
          String ruta="E:\\\\NvaPlataforma\\ReporteSalfa\\Reportes1";

        String Archivo=Arch;
        File folder = new File(ruta+"\\");
        
        if (!folder.isDirectory()) { 
        folder.mkdir();
        }
   
        
        File[] listOfFiles = folder.listFiles(); 
        System.out.println("RUTA Origen:"+ruta);
        System.out.println("BUSCANDO ARCHIVO:"+Archivo);
        for (int i = 0; i < listOfFiles.length; i++) 
        {
            if (listOfFiles[i].isFile()) 
            {
                files = listOfFiles[i].getName();
                if (files.endsWith(Archivo))
                {
                    if(!files.equals("")){
                    System.out.println("Archivos encontrados: "+files);
                    existe=true;
                    break;
                    }
                }
            }
        }
        
         if(existe==false) {
        
                System.out.println("NO SE A ENCONTRADO ARCHIVO");  
                     
        } 
    else 
        { 
        
      
//      Path origenPath = FileSystems.getDefault().getPath("C:\\\\Reportes1\\"+Archivo);
//      Path destinoPath = FileSystems.getDefault().getPath("E:\\\\NvaPlataforma\\ReporteSalfa\\Reportes1\\"+Archivo);
  
      
      Path origenPath = FileSystems.getDefault().getPath("E:\\\\NvaPlataforma\\ReporteSalfa\\Reportes1\\"+Archivo);
      Path destinoPath = FileSystems.getDefault().getPath("Z:\\\\"+Archivo);
        try {
            Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println(e);
        }
	}
}







      public static void GeneraPDF(String Codhtml, String nomPDF) {
    System.out.println("--------------------");
      System.out.println("--------------------");
        System.out.println("GeneraPDF");
          System.out.println("--------------------");
            System.out.println("--------------------");
    System.out.println("-------Codhtml: "+Codhtml.replace("'", ""));
    System.out.println("-------nomPDF: "+nomPDF);
        
           try {

      
com.itextpdf.text.Document document = new com.itextpdf.text.Document();
//PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("C:\\\\Reportes1\\\\"+nomPDF+".pdf"));

PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("E:\\\\NvaPlataforma\\ReporteSalfa\\Reportes1\\"+nomPDF+".pdf"));


 Rectangle one = new Rectangle(70,140);
 Rectangle two = new Rectangle(850,5000);

 document.setPageSize(one);
 document.setMargins(2, 2, 2, 2);
document.open();
document.setPageSize(two);
document.setMargins(20, 20, 20, 20);
   document.newPage();
//document.addAuthor("Author of the Doc");
//document.addCreator("Creator of the Doc");
//document.addSubject("Subject of the Doc");
document.addCreationDate();
//document.addTitle("This is the title");


HTMLWorker htmlWorker = new HTMLWorker(document);
System.out.println(htmlWorker.toString());
String str = Codhtml.replace("'", "");





htmlWorker.parse(new StringReader(str));
 

//Environment.defaultConfig (). ConnectTimeout = 50000;
//   Environment.defaultConfig (). ReadTimeout = 80000;

  System.out.println(document.toString());

document.close();
String nom=nomPDF+".pdf";
moverpdf(nom);

} catch(DocumentException e) {
    e.toString();
      Log.log(e.toString());
e.printStackTrace();
} catch (FileNotFoundException e) {
    e.toString();
      Log.log(e.toString());
e.printStackTrace();
} catch (UnsupportedEncodingException e) {
    e.toString();
      Log.log(e.toString());
e.printStackTrace();
} catch (IOException e) {
    e.toString();
      Log.log(e.toString());
e.printStackTrace();
}

    }

   
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        int cont=0;
        Statement st,st1,st2,st4,st3,st32,st5;
        ResultSet rs,rs1,rs2,rs4,rs3,rs32,rs5;              
        
        //String json="{\"options\":{\"data\":{\"columns\":[[\"This Week\",10,12,41,9,14,15,15],[\"Last Week\",9,14,21,21,20,3,5]]}}}";

        

        try{
            System.out.println("Inicio App");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = null;
            boolean _exit=false;
            while(!_exit)
            {
                
                System.out.println("NO CUMPLE CONDICION DE EJECUCION");
                Thread.sleep(10000);
                try
                {
//                    con = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=SalfaLink","sa","pcontador");
//                    con = DriverManager.getConnection("jdbc:sqlserver://;servername=localhost\\SQLEXPRESS;databaseName=SalfaLink","sa","Micrologica2014");
                    con = DriverManager.getConnection("jdbc:sqlserver://150.0.20.202;databaseName=SalfaLink","sa","Micrologica2014");                    
                      
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                    Log.log(e.toString());
                    if (con!=null)
                        con.close();
                    int c = 0;
                    while (c==0)
                    {
                        try
                        {
//                          con = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=SalfaLink","sa","pcontador");
//                           con = DriverManager.getConnection("jdbc:sqlserver://;servername=localhost\\SQLEXPRESS;databaseName=SalfaLink","sa","Micrologica2014");
                          con = DriverManager.getConnection("jdbc:sqlserver://150.0.20.202;databaseName=SalfaLink","sa","Micrologica2014");                                  
                            Log.log("Se recupera conexion");
                            c=1;
                        }
                        catch (SQLException s)
                        {
                            System.out.println("Err: "+s.toString());
                        }
                        Thread.sleep(15000);
                    }
                }
                try{
                    Calendar c = Calendar.getInstance();
                    c.setTime(new Date(System.currentTimeMillis()));
                    int diaD = c.get(Calendar.DATE);
                    int mesD = c.get(Calendar.MONTH)+1;
                    int anoD = c.get(Calendar.YEAR);
                    int hora = c.get(Calendar.HOUR_OF_DAY);
                    int minu = c.get(Calendar.MINUTE);
                    int diase=c.get(Calendar.DAY_OF_WEEK);
                    System.out.println("--------------------");
                    System.out.println("dia: "+diase);
                    System.out.println("--------------------");
                   //Dia Domingo = 1
                   //Dia Lunes  =2 
                   //Dia Martes =3
                   //Dia Miercoles =4 
                   //Dia Jueves=5
                   //Dia Viernes =6
                   //Dia Sabado = 7
                   
                    if ((diase==2)){
                            //&&(minu==51)&&(diase==6)
                          
                    if (Flag==false){                      
                    Flag=true;
                    String Correo,CodU,NomEmp,NomUsuario,SQueryCuenta="";   
                    String VIN,VEH,ULREGIS="";
                    String fechaIni,fechaFin,fechaIni1,fechaFin1,fechaIni2,fechaFin2="";
                    
                    System.out.println("Inicio Proceso");
                    Log.log("Inicio proceso"); 
                    st=con.createStatement();                    
                    String SQuery1="select distinct  u.CodUsuario,u.correo,e.NomEmpresa,u.Nombre,getdate(), GETDATE()-7\n" +
                                    " from Usuario u inner join Empresa e on u.Empresa_CodEmpresa=e.CodEmpresa\n" +
                                    " left join Vehiculo v on u.Empresa_CodEmpresa = v.Empresa_CodEmpresa \n"+
                                    " where perfil_CodPerfil=2 and u.Empresa_CodEmpresa<>1 and v.Estadoservicio=0 order by u.CodUsuario";
//" where perfil_CodPerfil=2 and u.Empresa_CodEmpresa<>1 and u.CodUsuario in('10968','10969') order by u.CodUsuario";
                    System.out.println(SQuery1);
                    Log.log(SQuery1);
                    rs1=st.executeQuery(SQuery1);
                    while (rs1.next()){
                        cont++;
                    System.out.println("While cont++: "+cont);
                    
                    Correo=rs1.getObject(2).toString();
                    CodU=rs1.getObject(1).toString();
                    NomEmp=rs1.getObject(3).toString();
                    NomUsuario=rs1.getObject(4).toString();
                     
                    fechaIni2=rs1.getObject(5).toString().substring(0, 4)+rs1.getObject(5).toString().substring(5, 7)+rs1.getObject(5).toString().substring(8, 10);
                    fechaIni1=rs1.getObject(5).toString();
                    fechaIni=rs1.getObject(5).toString().substring(8, 10)
                            +"/"+rs1.getObject(5).toString().substring(5, 7)
                            +"/"+rs1.getObject(5).toString().substring(0, 4);
                    fechaFin2=rs1.getObject(6).toString().substring(0, 4)+rs1.getObject(6).toString().substring(5, 7)+rs1.getObject(6).toString().substring(8, 10);
                    fechaFin1=rs1.getObject(6).toString();
                    fechaFin=rs1.getObject(6).toString().substring(8, 10)
                            +"/"+rs1.getObject(6).toString().substring(5, 7)
                            +"/"+rs1.getObject(6).toString().substring(0, 4);
                    
                    System.out.println("fechaIni2 :"+ fechaIni2+ " fechaFin2 :"+fechaFin2);
                    
                    st3=con.createStatement();                    
                    String SQueryR3="select v.VIN, m.NomEmpresa as 'Empresa', case when v.Patente='' then v.VIN else v.Patente end as 'Vehiculo',\n" +
"l.fecha_hora as 'Fecha último registro' , case when e.EstadoSQ=1 then 'Conectado' else 'Desconectado' end as 'Squarell',\n" +
"case when e.EstadoGPS=1 then 'Con cobertura' else 'Sin cobertura' end as 'GPS',case when e.EstadoBat=1 then 'Energizado' else 'Desenergizado' end as 'Energia'\n" +
"from LastRegRx l inner join EstadoVehiculo e on e.Vehiculo_Vin=l.Vehiculo_VIN inner join Vehiculo v on v.VIN=l.Vehiculo_VIN \n" +
"inner join Empresa m on m.CodEmpresa=v.Empresa_CodEmpresa inner join Usuario u on  u.Empresa_CodEmpresa=m.CodEmpresa\n" +
"where v.Estadoservicio=0 and u.CodUsuario="+CodU+" order by l.fecha_hora desc";
                           
//                    System.out.println("SQueryR3: "+SQueryR3);
                    Log.log(SQueryR3);
                    rs3=st3.executeQuery(SQueryR3);
                  
                    System.out.println("Camiones Activos");
                   // rs3.next();
                   //VIN=rs3.getObject(1).toString();
                   // VEH=rs3.getObject(3).toString();
                   //ULREGIS=rs3.getObject(5).toString();
                   String ColorAzul="#2E64FE",ColorGris="#A4A4A4";
                   String inicio="", Datos="",Datos1 = "<tr align=\"center\">",Datos2 = "</tr>",Datos3="";
                  // int contA,contPerio,contNo=0;
                    inicio= "<tr>"
                            + "<td bgcolor=\"#F3F3F3\">"                            
                            + "<table bgcolor=\"#F3F3F3\" align=\"center\" width=\"700\" border=\"1\">\n" 
                            + "<tr width=\"100%\" align=\"center\">\n" 
                            + "<th colspan=\"9\"><font face=\"calibri\" size=\"4\" color=\""+ColorAzul+"\" >Vehiculos pertenecientes a <b>"+NomEmp+"</b></font></th>"
                     
                            + "</tr>\n" 
                            + "<tr width=\"100%\" align=\"center\">\n" 

                            
                            + "<th><font size=\"1\"face=\"calibri\" color=\""+ColorGris+"\"><b> VIN            </b></font><font size=\"2\"face=\"calibri\" color=\""+ColorGris+"\"></font></th>\n" 
                            + "<th><font size=\"1\"face=\"calibri\" color=\""+ColorGris+"\"><b> Vehiculo       </b></font><font size=\"2\"face=\"calibri\" color=\""+ColorGris+"\"></font></th>\n" 
                            + "<th width=\"140px\"><font size=\"1\"face=\"calibri\" color=\""+ColorGris+"\"><b> Ultimo Registro</b></font><font size=\"2\"face=\"calibri\" color=\""+ColorGris+"\"></font></th>\n" 
                            
                            + "<th><font size=\"1\"face=\"calibri\" color=\""+ColorGris+"\"><b> Kilometraje recorrido</b></font><font size=\"2\"face=\"calibri\" color=\""+ColorGris+"\"></font></th>\n" 
                            + "<th><font size=\"1\"face=\"calibri\" color=\""+ColorGris+"\"><b> Kilometraje total</b></font><font size=\"2\"face=\"calibri\" color=\""+ColorGris+"\"></font></th>\n" 
                            + "<th><font size=\"1\"face=\"calibri\" color=\""+ColorGris+"\"><b> Hora Total Periodo</b></font><font size=\"2\"face=\"calibri\" color=\""+ColorGris+"\"></font></th>\n" 
                            + "<th><font size=\"1\"face=\"calibri\" color=\""+ColorGris+"\"><b> Hora Total Motor</b></font><font size=\"2\"face=\"calibri\" color=\""+ColorGris+"\"></font></th>\n" 
                            + "<th><font size=\"1\"face=\"calibri\" color=\""+ColorGris+"\"><b> Consumo km/lts</b></font><font size=\"2\"face=\"calibri\" color=\""+ColorGris+"\"></font></th>\n" 
                            + "<th><font size=\"1\"face=\"calibri\" color=\""+ColorGris+"\"><b> Consumo lts/hr</b></font><font size=\"2\"face=\"calibri\" color=\""+ColorGris+"\"></font></th>\n" 
                            + "</tr>\n" 
                                                        
                            + "<tr width=\"100%\" align=\"left\">\n" ;
                   //  
                    while (rs3.next()) //Esta es la forma correcta de recorrer los valores obtenidos de una consulta
                    {
System.out.println("+rs3.getObject(1).toString()+ "+rs3.getObject(1).toString() );   
                    st32=con.createStatement();                    
                    
                    String SQueryR32="select \n" +
"min(convert(float,m.Generico13)) as 'min Kilometraje',\n" +
"max(convert(float,m.generico13)) as 'Kilometraje total',\n" +
"min(convert(float,m.generico14)) as 'min Hora Motor',\n" +
"max(convert(float,m.generico14)) as 'Hora Total Motor',\n" +
"avg(convert(float,m.generico15)) as 'Consumo km/lts',\n" +
"avg(convert(float,m.generico16)) as 'Consumo lts/hrs'\n" +
"from DiasMotorRegRx m \n" +
"inner join Vehiculo v on m.Vehiculo_VIN=v.VIN \n" +
"where generico13<>'' \n" +
"and Generico_CodGenerico=54 \n" +
"and codfecha>='" +fechaFin2+ "' and codfecha<='" +fechaIni2+ "' \n" +
"and m.Vehiculo_VIN = ('"+rs3.getObject(1).toString()+"') \n" +
"group by m.Vehiculo_VIN,v.Patente\n" +
"order by m.Vehiculo_VIN ";
                    rs32=st32.executeQuery(SQueryR32);
                        rs32.next();
                   
                    if (rs32.getRow()>0){
                    DecimalFormat df = new DecimalFormat("0.0");
//           System.out.println("ORIGINAL: "+rs32.getObject(2).toString()+"-"+rs32.getObject(1).toString());
                    
        minKM=String.valueOf(df.format(Float.parseFloat(rs32.getObject(2).toString())-Float.parseFloat(rs32.getObject(1).toString())));                        
//        System.out.println("minKM RES: "+minKM);
        
        
        maxKM=String.valueOf(df.format(Float.parseFloat(rs32.getObject(2).toString())));
        
       // minH=String.valueOf(Math.round(Float.parseFloat(rs32.getObject(3).toString())));
        minH=String.valueOf(df.format(Float.parseFloat(rs32.getObject(4).toString())-Float.parseFloat(rs32.getObject(3).toString())));                        
        maxH=String.valueOf(df.format(Float.parseFloat(rs32.getObject(4).toString())));
        KmL=String.valueOf(df.format(Float.parseFloat(rs32.getObject(5).toString())));                        
        LHr=String.valueOf(df.format(Float.parseFloat(rs32.getObject(6).toString())));  

//                        System.out.println("Camzxcvbn"+minKM+"-"+maxKM+"-"+minH+"-"+maxH+"-"+KmL+"-"+LHr);

                    }else{
                           
//                            System.out.println("Camzxcvbn: NO ENTRO");
                       
                        minKM="-";                        
                        maxKM="-";
                        minH="-";
                        maxH="-";
                        KmL="-";                        
                        LHr="-";    
                       }
                      /*  contA=+1;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date1=sdf.parse(fechaFin1);
                        Date date2=sdf.parse(rs3.getString(4));
                        if (date1.before(date2)){
                            contPerio=+1;
                        }else{contNo=+1;}*/
                   Datos=Datos+ Datos1+"<td ><font size=\"1\"face=\"calibri\" color=\""+ColorGris+"\"><b>"+rs3.getObject(1).toString()+"</b></font></td>"
                              + "<td><font size=\"1\"face=\"calibri\" color=\""+ColorGris+"\"><b>"+rs3.getObject(3).toString()+"</b></font></td>"   
                              + "<td><font size=\"1\"face=\"calibri\" color=\""+ColorGris+"\"><b>"+rs3.getObject(4).toString()+"</b></font></td>"    

                              + "<td><font size=\"1\"face=\"calibri\" color=\""+ColorGris+"\"><b>"+minKM+"</b></font></td>"    
                              + "<td><font size=\"1\"face=\"calibri\" color=\""+ColorGris+"\"><b>"+maxKM+"</b></font></td>"    
                              + "<td><font size=\"1\"face=\"calibri\" color=\""+ColorGris+"\"><b>"+minH+"</b></font></td>"    
                              + "<td><font size=\"1\"face=\"calibri\" color=\""+ColorGris+"\"><b>"+maxH+"</b></font></td>"    
                              + "<td><font size=\"1\"face=\"calibri\" color=\""+ColorGris+"\"><b>"+KmL+"</b></font></td>"    
                              + "<td><font size=\"1\"face=\"calibri\" color=\""+ColorGris+"\"><b>"+LHr+"</b></font></td>"    
                             
                                +Datos2;
                    }
                    Datos3=inicio+Datos + "</tr>\n" 
                            + "</table>\n" 
                            + "</td>"
                            + "</tr>";
                   
                    
                    st5=con.createStatement();                    
                    String SQueryR5="select v.VIN, m.NomEmpresa as 'Empresa', case when v.Patente='' then v.VIN else v.Patente end as 'Vehiculo',\n" +
"l.fecha_hora as 'Fecha último registro' , case when e.EstadoSQ=1 then 'Conectado' else 'Desconectado' end as 'Squarell',\n" +
"case when e.EstadoGPS=1 then 'Con cobertura' else 'Sin cobertura' end as 'GPS',case when e.EstadoBat=1 then 'Energizado' else 'Desenergizado' end as 'Energia'\n" +
"from LastRegRx l inner join EstadoVehiculo e on e.Vehiculo_Vin=l.Vehiculo_VIN inner join Vehiculo v on v.VIN=l.Vehiculo_VIN \n" +
"inner join Empresa m on m.CodEmpresa=v.Empresa_CodEmpresa inner join Usuario u on  u.Empresa_CodEmpresa=m.CodEmpresa\n" +
"where v.Estadoservicio=0 and u.CodUsuario="+CodU+" order by l.fecha_hora desc  ";
                    rs5=st5.executeQuery(SQueryR5);
                
                    Log.log(SQueryR5);
                System.out.println("Camiones Activos2");
                  
                   String inicio5="", Datos5="",Datos15 = "<tr align=\"center\">",Datos25 = "</tr>",Datos35="";
                   int contA5=0;
                   int contPerio5=0;
                   int contNo5=0;
                    inicio5= "<tr>"
                            + "<td bgcolor=\"#F3F3F3\">"                            
                            + "<table bgcolor=\"#F3F3F3\" align=\"center\" width=\"700\" border=\"1\">\n" 
                            + "<tr width=\"100%\" align=\"center\">\n" 
                            + "<th colspan=\"3\"><font face=\"calibri\" size=\"4\" color=\""+ColorAzul+"\" >RESUMEN Vehiculos de <b>"+NomEmp+"</b></font></th>"
                           + "</tr>\n" 
                            + "<tr width=\"100%\" align=\"center\">\n" 
                            
                            + "<th><font size=\"1\"face=\"calibri\" color=\""+ColorGris+"\"><b> Total Camiones Activos            </b></font><font size=\"2\"face=\"calibri\" color=\""+ColorGris+"\"></font></th>\n"
                            + "<th><font size=\"1\"face=\"calibri\" color=\""+ColorGris+"\"><b> Vehiculos con registros en el periodo       </b></font><font size=\"2\"face=\"calibri\" color=\""+ColorGris+"\"></font></th>\n" 
                            + "<th><font size=\"1\"face=\"calibri\" color=\""+ColorGris+"\"><b> Vehiculos sin registros en el periodo </b></font><font size=\"2\"face=\"calibri\" color=\""+ColorGris+"\"></font></th>\n" 
                          
                            + "</tr>\n" 
                                                        
                            + "<tr width=\"100%\" align=\"left\">\n" ;
                    while (rs5.next()) //Esta es la forma correcta de recorrer los valores obtenidos de una consulta
                    {
                        contA5=contA5+1;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date15=sdf.parse(fechaFin1);
                        Date date25=sdf.parse(rs5.getString(4));
                        if (date15.before(date25)){
                            contPerio5=contPerio5+1;
                        }else{contNo5=contNo5+1;}
                
                    }
                Datos5= Datos15
                       +"<td ><font size=\"4\"face=\"calibri\" color=\""+ColorGris+"\"><b>"+contA5+"</b></font></td>"
                       +"<td ><font size=\"4\"face=\"calibri\" color=\""+ColorGris+"\"><b>"+contPerio5+"</b></font></td>"
                       +"<td ><font size=\"4\"face=\"calibri\" color=\""+ColorGris+"\"><b>"+contNo5+"</b></font></td>"
                       
                       +Datos25;
                
                    Datos35=inicio5+Datos5 + "</tr>\n" 
                            + "</table>\n" 
                            + "</td>"
                            + "</tr>";
                    
                    
                    
                    
                    
                    st1 = con.createStatement();
                    st1.executeUpdate("delete from TotalesDiarios");                    
                    st2 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    String SQuery2="insert into TotalesDiarios select substring(b.patente,1,10), c.Vehiculo_VIN,convert(date,c.Fecha_Hora)\n" +
                                    ",round((sum(CONVERT(float,c.Generico19))/60),1)\n" +
                                    ",round(min(convert(float,c.Generico32)),1)\n" +
                                    ",round(max(convert(float,c.Generico32)),1)\n" +
                                    ",round(max(convert(float,c.Generico32))-min(convert(float,c.Generico32)) ,1)\n" +
                                    ",round(min(convert(float,c.Generico13)),1)\n" +
                                    ",round(max(convert(float,c.Generico13)),1)\n" +
                                    ",round(max(convert(float,c.Generico13))-min(convert(float,c.Generico13)),1)" +
                                    "from Empresa a inner join Vehiculo b on a.CodEmpresa=b.Empresa_CodEmpresa \n" +
                                    "right join DiasMotorRegRx c on b.VIN=c.Vehiculo_VIN \n" +
                                    "where a.CodEmpresa=(select Empresa_CodEmpresa from Usuario where CodUsuario="+CodU+") \n" +
                                    //"where a.CodEmpresa=(select Empresa_CodEmpresa from Usuario where CodUsuario=41)\n" +
                                    "and c.Fecha_Hora>=convert(datetime,convert(varchar,dateadd(dd,-7,getdate()),111),102) and c.Fecha_Hora<convert(datetime,convert(varchar,getdate(),111),102) \n" +
                                  //  "--and c.Fecha_Hora>=convert(datetime,'2018-03-12',102) and c.Fecha_Hora<convert(datetime,'2018-03-19',102) \n" +
                                    "and CONVERT(float,c.generico14)>0 and CONVERT(float,c.generico13)>0 and b.EstadoServicio=0 \n" +
                                    "group by b.Patente, c.Vehiculo_VIN,convert(date,c.Fecha_Hora) \n" +
                                    "order by b.Patente,convert(date,c.Fecha_Hora)";                    
//                    System.out.println("-------------Insert totales diarios-------------");
//                    System.out.println(SQuery2);
//                    System.out.println("-----------------------------------------------");
                    st2.executeUpdate(SQuery2);
//                    System.out.println(SQuery2);
                    
                    st2 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    SQueryCuenta="select count(*) from TotalesDiarios where c7<>0 and c5<>c6 and c8<>c9 and c10<>0";
                    st4 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    rs4=st4.executeQuery(SQueryCuenta);
                    rs4.next();
                    String Val="";
                    Val=rs4.getObject(1).toString();
                         
                    
                    
                    
                    
                    
                    
                    if (Float.parseFloat(Val)!=0){ 
                        
                        
                        
                    SQuery1="select b.patente, c.Vehiculo_VIN\n" +
                                    ",round(min(convert(float,c.Generico13)),1) --Odo inicial\n" +
                                    ",round(max(convert(float,c.Generico13)),1) --Odo final\n" +
                                    ",round(max(convert(float,c.Generico13)),1)- min(convert(float,c.Generico13))-- KM recorridos\n" +
                                    ",round(min(convert(float,c.Generico14 )),1) --Horo inicial\n" +
                                    ",round(max(convert(float,c.Generico14 )),1) --Horo final\n" +
                                    ",round(max(convert(float,c.Generico14 )),1)-min(convert(float,c.Generico14 ))--Horometro total\n" +
                                    ",round(min(convert(float,c.Generico32 )),1) --Lts inicial\n" +
                                    ",round(max(convert(float,c.Generico32 )),1) --Lts final\n" +
                                    ",round(max(convert(float,c.Generico32 ))-min(convert(float,c.Generico32 )),1)--Lts total\n" +
                                    ",round(sum(convert(float,c.Generico19))/60,1) --Horas Ralenti\n" +
                                    ",round((max(convert(float,c.Generico14 ))-min(convert(float,c.Generico14 )))-(sum(convert(float,c.Generico19))/60),1) --Horas mov\n" +
                                    "from Empresa a inner join Vehiculo b on a.CodEmpresa=b.Empresa_CodEmpresa\n" +
                                    "right join DiasMotorRegRx c on b.VIN=c.Vehiculo_VIN \n" +
                                    "where a.CodEmpresa=(select Empresa_CodEmpresa from Usuario where CodUsuario="+CodU+") \n" +
                                   // "--and c.Fecha_Hora>=convert(datetime,'2018-01-12',102) and c.Fecha_Hora<convert(datetime,'2018-01-19',102) \n" +                                    
                                    "and c.Fecha_Hora>=convert(datetime,convert(varchar,dateadd(dd,-7,getdate()),111),102) and c.Fecha_Hora<convert(datetime,convert(varchar,getdate(),111),102) \n" +
                                    "and CONVERT(float,c.generico14)>0 and CONVERT(float,c.generico13)>0  and b.EstadoServicio=0 \n" +
                                    "group by b.Patente, c.Vehiculo_VIN";                    
//                    System.out.println(SQuery1);
                    Log.log(SQuery1);
                    rs=st2.executeQuery(SQuery1);
                    String KM="0",Rendimiento="0",Litros="0",Horas="0",Alertas="0",TpoMovimiento="0",Ralenti="0";
                    
                     String RalentiAn="";
                     String RendimientoAn="";
                     String LitrosAn="";
                    
                    
                    rs.last();
                      DecimalFormat df1 = new DecimalFormat("0.0");
                    
                    if (rs.getRow()>0){
                        rs.beforeFirst();                    
                    while (rs.next()){
                        KM=String.valueOf(Math.round(Float.parseFloat(rs.getObject(5).toString())+Float.parseFloat(KM)));                        
                        Horas=String.valueOf(Math.round(Float.parseFloat(rs.getObject(8).toString())+Float.parseFloat(Horas)));
                        Litros=String.valueOf(Math.round(Float.parseFloat(rs.getObject(11).toString())+Float.parseFloat(Litros)));
                        Ralenti=String.valueOf(Math.round(Float.parseFloat(rs.getObject(12).toString())+Float.parseFloat(Ralenti)));
                        TpoMovimiento=String.valueOf(Math.round(Float.parseFloat(rs.getObject(13).toString())+Float.parseFloat(TpoMovimiento)));                        
                    }
                    if (Float.parseFloat(Litros)==0){
                        Rendimiento=String.valueOf(df1.format(Float.parseFloat(KM)/0.01));
                    }
                    else{
                        Rendimiento=String.valueOf(df1.format(Float.parseFloat(KM)/Float.parseFloat(Litros)));    
                    }
                    ResultSet Rsaux;                    
                    st=con.createStatement();  
                    SQuery2="select top 1 case DATEPART(dw,a.c3)when 1 then 'Domingo' when 2 then 'Lunes' when 3 then 'Martes' when 4 then 'Miercoles' ";
                    SQuery2=SQuery2+ "when 5 then 'Jueves' when 6 then 'Viernes' when 7 then 'Sabado'end,replace(convert(varchar,a.c3,111),'/','-'),round(SUM(a.c4),1),MAX(a.c4) " ;
                    SQuery2=SQuery2+ "from TotalesDiarios a group by a.c3 order by SUM(a.c4) desc";
                    
//                    System.out.println(SQuery2);
                    Log.log(SQuery2);
                    Rsaux=st.executeQuery(SQuery2);                    
                    
                   //**************************************
                //*****MAYOR RALENTI
                  //**************************************
                  
                 //   String ColorAzul="#2E64FE",ColorGris="#A4A4A4";
                    String Cuerpo,Cuerpo2,Dia,Fecha,RalDia,Vehiculo,TotalRalVeh,RalMaxVeh,Patente;
                    String ResumenRRL="";
                    String ResumenMRD="";
                    Rsaux.next();                    
                    Dia=Rsaux.getObject(1).toString();
                    Fecha=Rsaux.getObject(2).toString();
                    RalDia=Rsaux.getObject(3).toString();
                    RalMaxVeh=Rsaux.getObject(4).toString();
                   // System.out.println(Dia);
                   // System.out.println(Fecha);
                   // System.out.println(RalMaxVeh);
                    SQuery2="select isnull(c1,c2), c2 from TotalesDiarios where c3='"+Fecha+"' and convert(varchar,convert(float,round(c4,2)))=convert(varchar,convert(float,round("+RalMaxVeh+",2))) group by c1,c2";
//                    System.out.println(SQuery2);
                    Log.log(SQuery2);
                    rs=st.executeQuery(SQuery2);
                    rs.next();
                    Patente=rs.getObject(1).toString();
                    Vehiculo=rs.getObject(2).toString();
                    
                    SQuery2="select round(convert(float,sum(c4)),1) from TotalesDiarios where c2='"+Vehiculo+"' and c3='"+Fecha+"' group by c2";
//                    System.out.println(SQuery2);
                    Log.log(SQuery2);
                    rs=st.executeQuery(SQuery2);
                    rs.next();                    
                    TotalRalVeh=rs.getObject(1).toString();
                    
                    SQuery2="select case DATEPART(dw,a.c3)when 1 then 'Domingo' when 2 then 'Lunes' when 3 then 'Martes' when 4 then 'Miercoles' \n" +
                            "when 5 then 'Jueves' when 6 then 'Viernes' when 7 then 'Sabado'end,replace(convert(varchar,a.c3,111),'/','-'),round(SUM(a.c4),1)\n" +
                            "from TotalesDiarios a   group by a.c3 having round(SUM(a.c4),1)>0 order by a.c3 asc";
//                    System.out.println(SQuery2);
                    Log.log(SQuery2);
                    rs=st.executeQuery(SQuery2);
//                    String datosG1="";
//                    while (rs.next()){
//                        datosG1=datosG1+rs.getObject(3).toString()+",";
//                    }      
//                    datosG1=datosG1.substring(0,datosG1.length()-1);      
                    String datosG1="";
                    String LunesG1="0";
                    String MartesG1="0";
                    String MiercolesG1="0";
                    String JuevesG1="0";
                    String ViernesG1="0";
                    String SabadoG1="0";
                    String DomingoG1="0";
                    rs=st.executeQuery(SQuery2); 
                  
                    while (rs.next()){
                        
                        //  Lunes
                        if(rs.getObject(1).toString().equals("Lunes")){
                            if (Float.parseFloat(rs.getObject(3).toString())>0){
                            LunesG1=rs.getObject(3).toString(); 
                            }
                        }
                        //Martes
                        if(rs.getObject(1).toString().equals("Martes")){
                            if (Float.parseFloat(rs.getObject(3).toString())>0){
                            MartesG1=rs.getObject(3).toString();                                  
                            }
                        }
                        //Miercoles
                        if(rs.getObject(1).toString().equals("Miercoles")){
                            if (Float.parseFloat(rs.getObject(3).toString())>0){
                            MiercolesG1=rs.getObject(3).toString();                                  
                            }
                        }
                        //Jueves
                           if(rs.getObject(1).toString().equals("Jueves")){
                            if (Float.parseFloat(rs.getObject(3).toString())>0){
                            JuevesG1=rs.getObject(3).toString();                                  
                            }
                        }
                        //Viernes
                           if(rs.getObject(1).toString().equals("Viernes")){
                            if (Float.parseFloat(rs.getObject(3).toString())>0){
                            ViernesG1=rs.getObject(3).toString();                                  
                            }
                        }
                        //Sabado
                           if(rs.getObject(1).toString().equals("Sabado")){
                            if (Float.parseFloat(rs.getObject(3).toString())>0){
                            SabadoG1=rs.getObject(3).toString();                                  
                            }
                        }
                        //Domingo
                           if(rs.getObject(1).toString().equals("Domingo")){
                            if (Float.parseFloat(rs.getObject(3).toString())>0){
                            DomingoG1=rs.getObject(3).toString();                                  
                            }
                        }
                       // datosG1=datosG1+rs.getObject(3).toString()+",";
                    }   
                     datosG1=LunesG1+","+MartesG1+","+MiercolesG1+","+JuevesG1+","+ViernesG1+","+SabadoG1+","+DomingoG1;

                    String UrlGrafico1="";
                    UrlGrafico1=Encripta("{\"options\":{\"data\":{\"labels\":[\"L\",\"M\",\"M\",\"J\",\"V\",\"S\",\"D\"],\"datasets\" :[{\"label\":\"Ralenti\",\"backgroundColor\" : \"rgba(50,50,50,0.5)\",\"data\" :["+datosG1+"]}]}}}","ralenti");
                      
                    //**************************************
                    //*****MAYOR CONSUMO COMBUSTIBLE
                    //**************************************
                    
                    st=con.createStatement();  
                    SQuery2="select top 1 case DATEPART(dw,a.c3)when 1 then 'Domingo' when 2 then 'Lunes' when 3 then 'Martes' when 4 then 'Miercoles' \n" +
                            "when 5 then 'Jueves' when 6 then 'Viernes' when 7 then 'Sabado'end,replace(convert(varchar,a.c3,111),'/','-'), "
                            + "round(convert(float,SUM(a.c7)),1),round(convert(float,MAX(a.c7)),1)\n" +
                            "from TotalesDiarios a   group by a.c3  order by SUM(a.c7) desc";
//                    System.out.println(SQuery2);
                    Log.log(SQuery2);
                    rs=st.executeQuery(SQuery2);                    

                    String Dia2,Fecha2,LtsDia,Vehiculo2,TotalLtsVeh,LtsMaxVeh,Patente2;
                    rs.next();
                    Dia2=rs.getObject(1).toString();
                    Fecha2=rs.getObject(2).toString();
                    LtsDia=rs.getObject(3).toString();
                    LtsMaxVeh=rs.getObject(4).toString();
                    
                    SQuery2="select isnull(c1,c2),c2 from TotalesDiarios where c3='"+Fecha2+"' and round(convert(float,c7),1)=round(convert(float,"+LtsMaxVeh +"),1) group by c1,c2";
//                    System.out.println(SQuery2);
                    Log.log(SQuery2);
                    rs=st.executeQuery(SQuery2);
                    rs.next();
                    Patente2=rs.getObject(1).toString();
                    Vehiculo2=rs.getObject(2).toString();
                    
                    SQuery2="select round(convert(float,sum(c7)),1) from TotalesDiarios where c2='"+Vehiculo2+"' and c3='"+Fecha2+"' group by c2";
//                    System.out.println(SQuery2);
                    Log.log(SQuery2);
                    rs=st.executeQuery(SQuery2);
                    rs.next();                    
                    TotalLtsVeh=rs.getObject(1).toString();
                    
                    SQuery2="select  case DATEPART(dw,a.c3)when 1 then 'Domingo' when 2 then 'Lunes' when 3 then 'Martes' when 4 then 'Miercoles' \n" +
                            "when 5 then 'Jueves' when 6 then 'Viernes' when 7 then 'Sabado'end,replace(convert(varchar,a.c3,111),'/','-'), "
                            + "round(convert(float,SUM(a.c7)),1) \n" +
                            "from TotalesDiarios a  group by a.c3   order by a.c3 asc";
//                    System.out.println(SQuery2);
                    Log.log(SQuery2);
                    rs=st.executeQuery(SQuery2);
//                    String datosG2="";
//                    while (rs.next()){
//                        datosG2=datosG2+rs.getObject(3).toString()+",";
//                    }                 
//                    datosG2=datosG2.substring(0,datosG2.length()-1);                    
                    String datosG2="";
                    String LunesG2="0";
                    String MartesG2="0";
                    String MiercolesG2="0";
                    String JuevesG2="0";
                    String ViernesG2="0";
                    String SabadoG2="0";
                    String DomingoG2="0";
                    rs=st.executeQuery(SQuery2); 
                  
                    while (rs.next()){
                        
                        //  Lunes
                        if(rs.getObject(1).toString().equals("Lunes")){
                            if (Float.parseFloat(rs.getObject(3).toString())>0){
                            LunesG2=rs.getObject(3).toString(); 
                            }
                        }
                        //Martes
                        if(rs.getObject(1).toString().equals("Martes")){
                            if (Float.parseFloat(rs.getObject(3).toString())>0){
                            MartesG2=rs.getObject(3).toString();                                  
                            }
                        }
                        //Miercoles
                        if(rs.getObject(1).toString().equals("Miercoles")){
                            if (Float.parseFloat(rs.getObject(3).toString())>0){
                            MiercolesG2=rs.getObject(3).toString();                                  
                            }
                        }
                        //Jueves
                           if(rs.getObject(1).toString().equals("Jueves")){
                            if (Float.parseFloat(rs.getObject(3).toString())>0){
                            JuevesG2=rs.getObject(3).toString();                                  
                            }
                        }
                        //Viernes
                           if(rs.getObject(1).toString().equals("Viernes")){
                            if (Float.parseFloat(rs.getObject(3).toString())>0){
                            ViernesG2=rs.getObject(3).toString();                                  
                            }
                        }
                        //Sabado
                           if(rs.getObject(1).toString().equals("Sabado")){
                            if (Float.parseFloat(rs.getObject(3).toString())>0){
                            SabadoG2=rs.getObject(3).toString();                                  
                            }
                        }
                        //Domingo
                           if(rs.getObject(1).toString().equals("Domingo")){
                            if (Float.parseFloat(rs.getObject(3).toString())>0){
                            DomingoG2=rs.getObject(3).toString();                                  
                            }
                        }
                       //  datosG2=datosG2+rs.getObject(3).toString()+",";
                    }   
                    datosG2=LunesG2+","+MartesG2+","+MiercolesG2+","+JuevesG2+","+ViernesG2+","+SabadoG2+","+DomingoG2;
                    String UrlGrafico2="";
                    
                    UrlGrafico2=Encripta("{\"options\":{\"data\":{\"labels\":[\"L\",\"M\",\"M\",\"J\",\"V\",\"S\",\"D\"],\"datasets\" :[{\"label\":\"Consumos \",\"backgroundColor\" : \"rgba(50,50,50,0.5)\",\"data\" :["+datosG2+"]}]}}}","consumos");                    
                    
                     
                    
                    
                    
                    //**************************************
                    //*****MENOR RENDIMIENTO DE COMBUSTIBLE
                    //**************************************
                    System.out.println("---------------COMPARACION RENDIMIENTO DE COMBUSTIBLE----------------\n");
                    System.out.println("---------------Mejor rendimiento----------------\n");
                    String UrlGrafico3="";
                    String PatenteM3, VinM3, FechaM3, MR3,DiaM3="";
                    SQuery2="select top 1"
                            // + " isnull(a.c1,a.c2)"
                            + " case when a.c1='' then a.c2 else a.c1 end "
                            + ",a.c2,a.c3, round(convert(float,case SUM(a.c7) when 0 then SUM(a.c10)*0.000000000001 else SUM(a.c10)/SUM(a.c7) end) ,1)\n" +
                    ",case DATEPART(dw,a.c3)when 1 then 'Domingo' when 2 then 'Lunes' when 3 then 'Martes' when 4 then 'Miercoles' \n" +
                    "when 5 then 'Jueves' when 6 then 'Viernes' when 7 then 'Sabado'end \n" +
                    "from TotalesDiarios a  group by a.c1,a.c2,a.c3 having round(convert(float,case SUM(a.c7) when 0 then SUM(a.c10)*0.000000000001 else SUM(a.c10)/SUM(a.c7) end),3)>0 "
                    + "order by round(convert(float,case SUM(a.c7) when 0 then SUM(a.c10)*0.000000000001 else SUM(a.c10)/SUM(a.c7) end) ,1) desc";
                    Log.log(SQuery2);
                    rs=st.executeQuery(SQuery2);    
                                                          
                    rs.next();
                    PatenteM3=rs.getObject(1).toString();
                    VinM3=rs.getObject(2).toString();
                    FechaM3=rs.getObject(3).toString();
                    MR3=rs.getObject(4).toString();
                    DiaM3=rs.getObject(5).toString();
                    System.out.println("PatenteM3, VinM3, FechaM3, MR3,DiaM3 :"+PatenteM3+","+VinM3+","+FechaM3+","+MR3+","+DiaM3);
                    
                    
                    System.out.println("---------------Peor rendimiento----------------\n");
                    String PatenteP3, VinP3, FechaP3, PR3,DiaP3="";
                    SQuery2="select top 1"
                            // + " isnull(a.c1,a.c2)"
                            + " case when a.c1='' then a.c2 else a.c1 end "
                            + ",a.c2,a.c3, round(convert(float,case SUM(a.c7) when 0 then SUM(a.c10)*0.000000000001 else SUM(a.c10)/SUM(a.c7) end) ,1)\n" +
                    ",case DATEPART(dw,a.c3)when 1 then 'Domingo' when 2 then 'Lunes' when 3 then 'Martes' when 4 then 'Miercoles' \n" +
                    "when 5 then 'Jueves' when 6 then 'Viernes' when 7 then 'Sabado'end \n" +
                    "from TotalesDiarios a  group by a.c1,a.c2,a.c3 having round(convert(float,case SUM(a.c7) when 0 then SUM(a.c10)*0.000000000001 else SUM(a.c10)/SUM(a.c7) end),3)>0 "
                     + "order by round(convert(float,case SUM(a.c7) when 0 then SUM(a.c10)*0.000000000001 else SUM(a.c10)/SUM(a.c7) end) ,1) asc";
                    Log.log(SQuery2);
                    rs=st.executeQuery(SQuery2);    
                                                          
                    rs.next();
                    PatenteP3=rs.getObject(1).toString();
                    VinP3=rs.getObject(2).toString();
                    FechaP3=rs.getObject(3).toString();
                    PR3=rs.getObject(4).toString();
                    DiaP3=rs.getObject(5).toString();
                    System.out.println("PatenteP3, VinP3, FechaP3, PR3,DiaP3 :"+PatenteP3+","+VinP3+","+FechaP3+","+PR3+","+DiaP3);
                    
                    
            ResumenMRD= " <tr>"
            + "<td align=\"center\">"                                                      
            + "<table width=\"60%\" border=\"1\">"
            + "<tr align=\"center\">"
            + "<th></th>"
            + "<th><font face=\"calibri\" size=\"4\" color=\""+ColorGris+"\">Vehiculo mejor rendimiento semanal</font></th>"
            + "<th><font face=\"calibri\" size=\"4\" color=\""+ColorGris+"\">Vehiculo peor rendimiento semanal</font></th>"
            + "</tr>"
            + "<tr align=\"center\">"
            + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">Patente o VIN</font></td>"
            + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+PatenteM3+"</font></td>"
            + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+PatenteP3+"</font></td>"
           
            + "</tr>"
            + "<tr align=\"center\">"
            //String.valueOf(Float.parseFloat(TotalConVeh1.toString())-Float.parseFloat(ConDia))
            + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">Km/Lts</font></td>"
            + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+String.valueOf(Float.parseFloat(MR3.toString()))+"</font></td>"
            + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+String.valueOf(Float.parseFloat(PR3.toString()))+"</font></td>"
            + "</tr>"
            + "</tr>"
            + "<tr align=\"center\">"
            + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">Dia</font></td>"
                    
            + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+DiaM3+" "+FechaM3.substring(8,10)+"/"+FechaM3.substring(5,7)+"/"+FechaM3.substring(0,4)+"</font></td>"
            + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+DiaP3+" "+FechaP3.substring(8,10)+"/"+FechaP3.substring(5,7)+"/"+FechaP3.substring(0,4)+"</font></td>"

            + "</tr>"                        

            + "</table>"
            + "</td>"
            + "</tr> "  ;
                    
                    
              
                    SQuery2="select top 1 isnull(a.c1,a.c2),a.c2,replace(convert(varchar,a.c3,111),'/','-'), "
                    + "round(convert(float,case SUM(a.c7) when 0 then SUM(a.c10)*0.000000000001 else SUM(a.c10)/SUM(a.c7) end) ,1)\n" +
                    ",case DATEPART(dw,a.c3)when 1 then 'Domingo' when 2 then 'Lunes' when 3 then 'Martes' when 4 then 'Miercoles' \n" +
                    "when 5 then 'Jueves' when 6 then 'Viernes' when 7 then 'Sabado'end,ROW_NUMBER() OVER(ORDER BY a.c2 asc) AS RoW\n" +
                    "from TotalesDiarios a  group by a.c1,a.c2,a.c3 having"
                    + " round(convert(float,case SUM(a.c7) when 0 then SUM(a.c10)*0.000000000001 else SUM(a.c10)/SUM(a.c7) end),3)>0\n" +
                    " order by ROW_NUMBER() OVER(ORDER BY a.c2 asc) desc";
                     Log.log(SQuery2);
                    rs=st.executeQuery(SQuery2);    
                    rs.next();
                    
                    
                     System.out.println("------------------------------------------------\n");
                     System.out.println("EMPRESA: "+NomEmp);
                    System.out.println("NUMERO DE VEICULOS: "+contA5);
                     System.out.println("------------------------------------------------\n");
                   // if (Float.parseFloat(rs.getObject(6).toString())>3){
                   
                   
                   
                    if (contA5>3){   
                        Flag2=true;
                    System.out.println("------------------------------------------------\n");
                    System.out.println("---------------  > 3 vehiculos  ----------------\n");
                  
                    System.out.println("------------------------------------------------\n");
                    System.out.println("--------------SEMANA MEJOR CAMION---------------\n");

                
                    SQuery2="select round(convert(float,case SUM(a.c7) when 0 then SUM(a.c10)*0.000000000001 else SUM(a.c10)/SUM(a.c7) end) ,1)\n" +
                    ",case DATEPART(dw,a.c3)when 1 then 'Domingo' when 2 then 'Lunes' when 3 then 'Martes' when 4 then 'Miercoles' \n" +
                    "when 5 then 'Jueves' when 6 then 'Viernes' when 7 then 'Sabado'end \n" +
                    "from TotalesDiarios a where a.c2='"+VinM3+"' group by a.c1,a.c2,a.c3 having "
                    + "round(convert(float,case SUM(a.c7) when 0 then SUM(a.c10)*0.000000000001 else SUM(a.c10)/SUM(a.c7) end),3)>0 \n" +
                    "order by case DATEPART(dw,a.c3)when 1 then 'Domingo' when 2 then 'Lunes' when 3 then 'Martes' when 4 then 'Miercoles' \n" +
                    "when 5 then 'Jueves' when 6 then 'Viernes' when 7 then 'Sabado'end  asc";
                    Log.log(SQuery2);
                    rs=st.executeQuery(SQuery2); //System.out.println(SQuery2);
           
                    // datosG3 (Mejor rendimiento) 
                    String datosMG3="";
                    String LunesM="0";
                    String MartesM="0";
                    String MiercolesM="0";
                    String JuevesM="0";
                    String ViernesM="0";
                    String SabadoM="0";
                    String DomingoM="0";
                   
                    while (rs.next()){
                        //Lunes
                           if(rs.getObject(2).toString().equals("Lunes")){
                            if (Float.parseFloat(rs.getObject(1).toString())>0){
                            LunesM=String.valueOf(Float.parseFloat(rs.getObject(1).toString()));                                  
                            }
                        }
                        //Martes
                          if(rs.getObject(2).toString().equals("Martes")){
                           if (Float.parseFloat(rs.getObject(1).toString())>0){
                           MartesM=String.valueOf(Float.parseFloat(rs.getObject(1).toString()));                                  
                           }
                        }
                        //Miercoles
                          if(rs.getObject(2).toString().equals("Miercoles")){
                           if (Float.parseFloat(rs.getObject(1).toString())>0){
                           MiercolesM=String.valueOf(Float.parseFloat(rs.getObject(1).toString()));                                  
                           }
                        }
                        //Jueves
                          if(rs.getObject(2).toString().equals("Jueves")){
                           if (Float.parseFloat(rs.getObject(1).toString())>0){
                           JuevesM=String.valueOf(Float.parseFloat(rs.getObject(1).toString()));                                  
                           }
                        }
                        //Viernes
                        if(rs.getObject(2).toString().equals("Viernes")){
                           if (Float.parseFloat(rs.getObject(1).toString())>0){
                           ViernesM=String.valueOf(Float.parseFloat(rs.getObject(1).toString()));                                  
                           }
                        }
                        //Sabado
                        if(rs.getObject(2).toString().equals("Sabado")){
                           if (Float.parseFloat(rs.getObject(1).toString())>0){
                           SabadoM=String.valueOf(Float.parseFloat(rs.getObject(1).toString()));                                  
                           }
                        }
                        //Domingo
                        if(rs.getObject(2).toString().equals("Domingo")){
                           if (Float.parseFloat(rs.getObject(1).toString())>0){
                           DomingoM=String.valueOf(Float.parseFloat(rs.getObject(1).toString()));                                  
                           }
                        }
                    }                 
                    datosMG3=LunesM+","+MartesM+","+MiercolesM+","+JuevesM+","+ViernesM+","+SabadoM+","+DomingoM;
                
                    
                    
                    
                    
                    
                    
                     System.out.println("--------------SEMANA PEOR CAMION---------------\n");

                
                    SQuery2="select round(convert(float,case SUM(a.c7) when 0 then SUM(a.c10)*0.000000000001 else SUM(a.c10)/SUM(a.c7) end) ,1)\n" +
                    ",case DATEPART(dw,a.c3)when 1 then 'Domingo' when 2 then 'Lunes' when 3 then 'Martes' when 4 then 'Miercoles' \n" +
                    "when 5 then 'Jueves' when 6 then 'Viernes' when 7 then 'Sabado'end \n" +
                    "from TotalesDiarios a where a.c2='"+VinP3+"' group by a.c1,a.c2,a.c3 having "
                    + " round(convert(float,case SUM(a.c7) when 0 then SUM(a.c10)*0.000000000001 else SUM(a.c10)/SUM(a.c7) end),3)>0 \n" +
                    "order by case DATEPART(dw,a.c3)when 1 then 'Domingo' when 2 then 'Lunes' when 3 then 'Martes' when 4 then 'Miercoles' \n" +
                    "when 5 then 'Jueves' when 6 then 'Viernes' when 7 then 'Sabado'end  asc";
                     Log.log(SQuery2);
                    rs=st.executeQuery(SQuery2); //System.out.println(SQuery2);
           
                    // datosG3 (Mejor rendimiento) 
                    String datosPG3="";
                    String LunesP="0";
                    String MartesP="0";
                    String MiercolesP="0";
                    String JuevesP="0";
                    String ViernesP="0";
                    String SabadoP="0";
                    String DomingoP="0";
                   
                    while (rs.next()){
                        //Lunes
                           if(rs.getObject(2).toString().equals("Lunes")){
                            if (Float.parseFloat(rs.getObject(1).toString())>0){
                            LunesP=String.valueOf(Float.parseFloat(rs.getObject(1).toString()));                                  
                            }
                        }
                        //Martes
                          if(rs.getObject(2).toString().equals("Martes")){
                           if (Float.parseFloat(rs.getObject(1).toString())>0){
                           MartesP=String.valueOf(Float.parseFloat(rs.getObject(1).toString()));                                  
                           }
                        }
                        //Miercoles
                          if(rs.getObject(2).toString().equals("Miercoles")){
                           if (Float.parseFloat(rs.getObject(1).toString())>0){
                           MiercolesP=String.valueOf(Float.parseFloat(rs.getObject(1).toString()));                                  
                           }
                        }
                        //Jueves
                          if(rs.getObject(2).toString().equals("Jueves")){
                           if (Float.parseFloat(rs.getObject(1).toString())>0){
                           JuevesP=String.valueOf(Float.parseFloat(rs.getObject(1).toString()));                                  
                           }
                        }
                        //Viernes
                        if(rs.getObject(2).toString().equals("Viernes")){
                           if (Float.parseFloat(rs.getObject(1).toString())>0){
                           ViernesP=String.valueOf(Float.parseFloat(rs.getObject(1).toString()));                                  
                           }
                        }
                        //Sabado
                        if(rs.getObject(2).toString().equals("Sabado")){
                           if (Float.parseFloat(rs.getObject(1).toString())>0){
                           SabadoP=String.valueOf(Float.parseFloat(rs.getObject(1).toString()));                                  
                           }
                        }
                        //Domingo
                        if(rs.getObject(2).toString().equals("Domingo")){
                           if (Float.parseFloat(rs.getObject(1).toString())>0){
                           DomingoP=String.valueOf(Float.parseFloat(rs.getObject(1).toString()));                                  
                           }
                        }
                    }                 
                    datosPG3=LunesP+","+MartesP+","+MiercolesP+","+JuevesP+","+ViernesP+","+SabadoP+","+DomingoP;
                    
                    
                    
                    UrlGrafico3=Encripta(                  
                        "{\n"+
                        "\"options\" : {\n"+
                        "\"data\" : {\n"+
                        "\"labels\" : [\n"+
                        "        \"L\",\n" +
                        "        \"M\",\n" +
                        "        \"M\",\n" +
                        "        \"J\",\n" +
                        "        \"V\",\n" +
                        "        \"S\",\n" +
                        "        \"D\"\n" +
                        "],\n"+
                        "\"datasets\" : [\n"+
                        "{\n"+
                        "\"label\" : \"Vehiculo Mejor Rendimiento\",\n"+
                        "\"backgroundColor\" : \"rgba(90,90,90,0.5)\",\n"+
                        "\"data\" : [\n"+datosMG3+
                        "]\n"+
                        "},\n"+
                        "{\n"+
                        "\"label\" : \"Vehiculo Peor Rendimiento\",\n"+
                        "\"backgroundColor\" : \"rgba(220,220,220,0.5)\",\n"+
                        "\"data\" : [\n"+datosPG3+
                        "]\n"+
                        "}\n"+                    
                        "]\n"+
                        "}\n"+
                        "}\n"+
                        "}","semana");
                    
                    
                    }
         

                    
                    


                String SQuery3="select count(c.CodNotificacion) from Empresa a inner join Vehiculo b on a.CodEmpresa=b.Empresa_CodEmpresa\n" +
                            "right join Notificacion c on b.VIN=c.Vehiculo_VIN\n" +
                            "where a.CodEmpresa=(select Empresa_CodEmpresa from Usuario where CodUsuario="+CodU+") \n"
                        + " --and c.Fecha_Hora>=convert(datetime,'2018-03-12',102) and c.Fecha_Hora<convert(datetime,'2018-03-19',102)\n" 
                        +  " and c.Fecha_Hora>=convert(varchar,convert(date,dateadd(dd,-8,getdate()))) and c.Fecha_Hora<=convert(varchar,convert(date,dateadd(dd,-1,getdate()))) "
                        + " and c.Generico_CodGenerico in (select CodGenerico from Generico where Gravedad_CodGravedad=5)";
                    
//                 System.out.println(SQuery3);
                         Log.log(SQuery3);
                        rs2=st.executeQuery(SQuery3);
                        rs2.next();
                        String ACrit="";
                        ACrit=rs2.getObject(1).toString();

             
                        
                    
                        String SQuery4="select isnull(KM,0),isnull(Ralenti,0),isnull(Litros,0),isnull(Rendimiento,0) from SemanaAnterior where CodUsuario="+CodU;                        
                        st4 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                         Log.log(SQuery4);
                        rs4=st4.executeQuery(SQuery4);
                        rs4.last();
                        String UrlGrafico4="";
                        if (rs4.getRow()>0){
                        rs4.beforeFirst();                    
                        rs4.next();
                        String data1,data2;
                       // String data12=rs4.getObject(2).toString()+","+rs4.getObject(4).toString()+","+rs4.getObject(3).toString();
                          data1=100+","+100+","+100;
                        String RalentiSAc="0";
                        String RendimientoSAc="0";
                        String LitrosSAc="0";
                   
//                        System.out.println("----------------------------------------------");
//                        System.out.println("SemanaAnterior: "+data1);
//                        System.out.println("----------------------------------------------");
//                          System.out.println("----------------------------------------------");
//                        System.out.println("SemanaAcatual: "+Ralenti+","+Rendimiento+","+Litros);
//                          System.out.println("----------------------------------------------");
                //***********************************
                //Ralenti
                //***********************************
                if (Float.parseFloat(rs4.getObject(2).toString())>0){
RalentiSAc=String.valueOf(Math.round((Float.parseFloat(Ralenti.toString().replace(",", "."))*100)/Float.parseFloat(rs4.getObject(2).toString())));    
System.out.println("----------------------------------------------");
System.out.println("Ralenti SemanaAnterior): "+Float.parseFloat(rs4.getObject(2).toString()) );
System.out.println("Ralenti Actual: "+Ralenti);
System.out.println("Math.round((Float.parseFloat(Ralenti)*100): "+Math.round((Float.parseFloat(Ralenti)*100)/Float.parseFloat(rs4.getObject(2).toString()))  );
System.out.println("RalentiSAc: "+RalentiSAc);
System.out.println("----------------------------------------------");
                        }
                 else
                 {
                     RalentiSAc="0";
                 }                 
                          
                //***********************************
                //Rendimiento
                //***********************************
                if (Float.parseFloat(rs4.getObject(4).toString())>0){
RendimientoSAc=String.valueOf(Math.round((Float.parseFloat(Rendimiento.toString().replace(",", "."))*100)/Float.parseFloat(rs4.getObject(4).toString())));                    
System.out.println("----------------------------------------------");
System.out.println("Rendimiento SemanaAnterior: "+Float.parseFloat(rs4.getObject(4).toString()) );
System.out.println("Rendimiento Actual: "+Rendimiento.toString().replace(",", "."));
System.out.println("Math.round((Float.parseFloat(Rendimiento)*100): "+Math.round((Float.parseFloat(Rendimiento.toString().replace(",", "."))*100)/Float.parseFloat(rs4.getObject(4).toString()))  );
System.out.println("RendimientoSAc: "+RendimientoSAc);
System.out.println("----------------------------------------------");     
                }
                 else
                 {
                     RendimientoSAc="0";
                 }                 
                      
                //***********************************
                //Litros
                //***********************************
                if (Float.parseFloat(rs4.getObject(3).toString())>0){
LitrosSAc=String.valueOf(Math.round((Float.parseFloat(Litros.toString().replace(",", "."))*100)/Float.parseFloat(rs4.getObject(3).toString())));
System.out.println("----------------------------------------------");
System.out.println("Litros SemanaAnterior: "+Float.parseFloat(rs4.getObject(3).toString()) );
System.out.println("Litros Actual: "+Litros.toString().replace(",", "."));
System.out.println("Math.round((Float.parseFloat(Litros)*100): "+Math.round((Float.parseFloat(Litros.toString().replace(",", "."))*100)/Float.parseFloat(rs4.getObject(3).toString()))  );
System.out.println("LitrosSAc: "+LitrosSAc);
System.out.println("----------------------------------------------");   
                        }
                 else
                 {
                     LitrosSAc="0";
                 }                               
                      //  String RalentiAn, RendimientoAn,LitrosAn="";
                        RalentiAn=rs4.getObject(2).toString();
                        LitrosAn=rs4.getObject(3).toString();
                        RendimientoAn=rs4.getObject(4).toString();
                     
                        //data2=Ralenti+","+Rendimiento+","+Litros;
    ResumenRRL= " <tr>"
    + "<td align=\"center\">"                                                      
        + "<table width=\"60%\" border=\"1\">"
        + "<tr align=\"center\">"
        + "<th></th>"
        + "<th><font face=\"calibri\" size=\"4\" color=\""+ColorGris+"\">Ralenti</font></th>"
        + "<th><font face=\"calibri\" size=\"4\" color=\""+ColorGris+"\">Litros</font></th>"
        + "<th><font face=\"calibri\" size=\"4\" color=\""+ColorGris+"\">Rendimiento</font></th>"
        + "</tr>"
        + "<tr align=\"center\">"
        + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">Semana Anterior</font></td>"
        + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+RalentiAn+"</font></td>"
        + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+LitrosAn+"</font></td>"
        + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+RendimientoAn+"</font></td>"
        + "</tr>"
        + "<tr align=\"center\">"
        + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">Semana Actual</font></td>"
        + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+Ralenti+"</font></td>"
        + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+Litros+"</font></td>"
        + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+Rendimiento+"</font></td>"
        + "</tr>"
        + "</table>"
    + "</td>"
+ "</tr> "  ;

                        
                        
                        
                        
                        data2=RalentiSAc+","+LitrosSAc+","+RendimientoSAc ;
//                         System.out.println("----------------------------------------------");
//                        System.out.println("SemanaAcatual: "+data2);
//                          System.out.println("----------------------------------------------");
                        String Grafico="{\n"+
                                    "\"options\" : {\n"+
                                    "\"data\" : {\n"+
                                    "\"labels\" : [\n"+
                                    "\"Ralenti\",\n"+                                
                                    "\"Litros\",\n"+
                                    "\"Rendimiento\"\n"+
                                    "],\n"+
                                    "\"datasets\" : [\n"+
                                    "{\n"+
                                    "\"label\" : \"% Semana Anterior\",\n"+
                                    "\"backgroundColor\" : \"rgba(255,236,0,1)\",\n"+
                                    "\"data\" : [\n"+data1+
                                
                                    "]\n"+
                                    "},\n"+
                                    "{\n"+
                                    "\"label\" : \"% Semana Actual\",\n"+
                                    "\"backgroundColor\" : \"rgba(151,187,205,1)\",\n"+
                                    "\"data\" : [\n"+data2+
                                
                                    "]\n"+
                                    "}\n"+                    
                                    "]\n"+
                                    "}\n"+
                                    "}\n"+
                                    "}";
                        UrlGrafico4=Encripta(Grafico,"semana"); 
//                        System.out.println("----------------------------------------------");
//                        System.out.println("Ralenti: "+Ralenti+" Rendimiento: "+Rendimiento+"Litros: "+Litros);
//                        System.out.println("----------------------------------------------");
//                        System.out.println(Grafico);
                                }
                        else
                            {
                                UrlGrafico4="SIN DATOS";
                            }
                    

                    if (Patente.length()==0)
                    {
                        Patente="VIN..."+ Vehiculo ;
                    }
                    if (Patente2.length()==0)
                    {

                        Patente2="VIN..."+ Vehiculo2 ;
                    }

                    String CuerpoAlertas="";

                    if (Float.parseFloat(ACrit)==0){

                        CuerpoAlertas="<font size=\"4\" face=\"calibri\" color=\""+ColorAzul+"\"> No se detectaron fallas que requieran revisión durante la semana.</font>\n" ;
                                                
                    }else{

                        //CuerpoAlertas="<font size=\"4\" face=\"calibri\" color=\""+ColorAzul+"\">"+ACrit+" Las alertas criticas que requieran revision las puede consultar en <a href=\"https://www.salfalink.cl\">SalfaLink</a></font>\n" ;
                          CuerpoAlertas="<font size=\"4\" face=\"calibri\" color=\""+ColorAzul+"\">"+ACrit+" Las alertas criticas que requieran revision las puede consultar en SalfaLink</font>\n" ;
                        } 

                    Cuerpo2="'";        
                    Cuerpo= "'"
                            + "<body bgcolor=\"#FCFBFB\">"
                            + "<center>"
                            + "<table bgcolor=\"#FCFBFB\" align=\"center\" width=\"801\" border=\"0\">\n" 
                            + "<tr>"
                            + "<td align=\"center\"><font face=\"calibri\" size=\"2\"color=\""+ColorGris+"\">"
                            + "<b>"+NomUsuario+"</b>, SALFALINK realiza seguimiento semanal a tu flota de camiones, indicando las<br>"
                            + "variables más importantes de tu operación</font><br>"
                            + "</td>"
                            + "</tr>"
                            + "<tr>"
                            + "<td align=\"left\" bgcolor=\"#1F4E79\">"
                            + "<img src=\"https://www.salfalink.cl/Mail/logo2.png\" width=\"253\" height=\"46\"><br>\n" 
                          //  + "<img src=\"http://image.ibb.co/fpc9Jy/logo2.png\" width=\"253\" height=\"46\"><br>\n" 
//                             + "<img src=\"http://150.0.0.169:52322/Mail/logo2.png\" width=\"253\" height=\"46\"><br>\n" 
                            + "</td>"
                            + "</tr>"
                            + "<tr>"
                            + "<td align=\"center\" bgcolor=\"#F3F3F3\">"
                            + "<font face=\"calibri\" size=\"5\" color=\""+ColorAzul+"\">Salud de su flota <u>"+NomEmp+"</u></font>"
                            + "<font face=\"calibri\" size=\"4\" color=\""+ColorGris+"\">"
                          /*+ "semana '+ substring(convert(varchar,convert(date,dateadd(dd,-7,getdate())),112),7,2)+'/'+" 
                            + "substring(convert(varchar,convert(date,dateadd(dd,-7,getdate())),112),5,2)+'/'+"
                            + "substring(convert(varchar,convert(date,dateadd(dd,-7,getdate())),112),1,4)+' a ' +  substring(convert(varchar,convert(date,dateadd(dd,-1,getdate())),112),7,2)+'/'+" 
                            + "substring(convert(varchar,convert(date,dateadd(dd,-1,getdate())),112),5,2)+'/'+" 
                            + "substring(convert(varchar,convert(date,dateadd(dd,-1,getdate())),112),1,4) +'</font>"*/
                            + "<br>"
                            + "Semana "+fechaFin+" al "+fechaIni
                            + "</td>"
                            + "</tr>"
                           
                           
                            
                            +Datos35
                            +Datos3
                          
                            + "<td bgcolor=\"#F3F3F3\">"
                            + "<table bgcolor=\"#F3F3F3\" align=\"center\" width=\"700\" border=\"0\">\n" 
                            + "<tr width=\"100%\" align=\"left\">\n" 
//                       
                            + "<td width=\"100\"><img src=\"https://www.salfalink.cl/Mail/Odom.png\" width=\"40\" height=\"40\"></td>\n" 
//                             + "<td width=\"100\"><img src=\"http://image.ibb.co/kEEBWJ/Odom.png\" width=\"40\" height=\"40\"></td>\n" 
//                            + "<td width=\"100\"><img src=\"http://150.0.0.169:52322/Mail/Odom.png\" width=\"40\" height=\"40\"></td>\n" 
                            + "<td width=\"167\"><table border=\"0\"><tr><td><font size=\"3\"face=\"calibri\" color=\""+ColorGris+"\"><b>"+KM+" Kmt.</b></font></td></tr><tr><td><font size=\"2\"face=\"calibri\" color=\""+ColorGris+"\">Total recorrido semanal</font></td></tr></table></td>\n" 
                            + "<td width=\"100\"><img src=\"https://www.salfalink.cl/Mail/Rsem.png\" width=\"40\" height=\"40\"></td>\n" 
//                             + "<td width=\"100\"><img src=\"http://image.ibb.co/bOb6gJ/Rsem.png\" width=\"40\" height=\"40\"></td>\n" 
//                            + "<td width=\"100\"><img src=\"http://150.0.0.169:52322/Mail/Rsem.png\" width=\"40\" height=\"40\"></td>\n" 
                            + "<td width=\"167\"><table border=\"0\"><tr><td><font size=\"3\"face=\"calibri\" color=\""+ColorGris+"\"><b>"+Rendimiento+" Kmt/Lts</b></font></td></tr><tr><td><font size=\"2\"face=\"calibri\" color=\""+ColorGris+"\">Rendimiento semanal</font></td></tr></table></td>\n" 
                            + "<td width=\"100\"><img src=\"https://www.salfalink.cl/Mail/CCom.png\" width=\"40\" height=\"40\"></td>\n" 
//                            + "<td width=\"100\"><img src=\"http://image.ibb.co/kmgEkd/CCom.png\" width=\"40\" height=\"40\"></td>\n" 
//                            + "<td width=\"100\"><img src=\"http://150.0.0.169:52322/Mail/CCom.png\" width=\"40\" height=\"40\"></td>\n" 
                            + "<td width=\"167\"><table border=\"0\"><tr><td><font size=\"3\"face=\"calibri\" color=\""+ColorGris+"\"><b>"+Litros+" Litros</b></font></td></tr><tr><td><font size=\"2\"face=\"calibri\" color=\""+ColorGris+"\">Consumo de combustible semanal</font></td></tr></table></td>\n" 
                            + "</tr>\n" 
                            + "<tr width=\"100%\" align=\"left\">\n" 
                            + "<td width=\"100\"><img src=\"https://www.salfalink.cl/Mail/TRal.png\" width=\"40\" height=\"40\"></td>\n" 
//                            + "<td width=\"100\"><img src=\"http://image.ibb.co/iNaz1J/TRal.png\" width=\"40\" height=\"40\"></td>\n" 
//                            + "<td width=\"100\"><img src=\"http://150.0.0.169:52322/Mail/TRal.png\" width=\"40\" height=\"40\"></td>\n" 
                            + "<td width=\"167\"><table border=\"0\"><tr><td><font size=\"3\"face=\"calibri\" color=\""+ColorGris+"\"><b>"+Ralenti+ " Horas</b></font></td></tr><tr><td><font size=\"2\"face=\"calibri\" color=\""+ColorGris+"\">Tiempo total en ralentí</font></td></tr></table></td>\n" 
                            + "<td width=\"100\"><img src=\"https://www.salfalink.cl/Mail/AAvi.png\" width=\"40\" height=\"40\"></td>\n" 
//                            + "<td width=\"100\"><img src=\"http://image.ibb.co/dqnWWJ/AAvi.png\" width=\"40\" height=\"40\"></td>\n" 
//                            + "<td width=\"100\"><img src=\"http://150.0.0.169:52322/Mail/AAvi.png\" width=\"40\" height=\"40\"></td>\n" 
                            + "<td width=\"167\"><table border=\"0\"><tr><td><font size=\"3\"face=\"calibri\" color=\""+ColorGris+"\"><b>"+ACrit+" Alertas</b></font></td></tr><tr><td><font size=\"2\"face=\"calibri\" color=\""+ColorGris+"\">Alertas de falla y avisos totales</font></td></tr></table></td>\n" 
                            + "<td width=\"100\"><img src=\"https://www.salfalink.cl/Mail/TMov.png\" width=\"40\" height=\"40\"></td>\n" 
//                            + "<td width=\"100\"><img src=\"http://image.ibb.co/dfX1Fd/TMov.png\" width=\"40\" height=\"40\"></td>\n" 
//                            + "<td width=\"100\"><img src=\"http://150.0.0.169:52322/Mail/TMov.png\" width=\"40\" height=\"40\"></td>\n" 
                            + "<td width=\"167\"><table border=\"0\"><tr><td><font size=\"3\"face=\"calibri\" color=\""+ColorGris+"\"><b>"+Horas+" Horas</b></font></td></tr><tr><td><font size=\"2\"face=\"calibri\" color=\""+ColorGris+"\">Tiempo en movimiento</font></td></tr></table></td>\n" 
                            + "</tr>\n" 
                            + "</table>\n" 
                            + "</td>"
                            + "</tr>"
                            
                            + "<tr>"
                            + "<td bgcolor=\"#F3F3F3\">"
                            + "<table bgcolor=\"#F3F3F3\" align=\"center\" width=\"801\" border=\"0\">"
                            + "<tr>"
                            + "<td align=\"center\">"
                            + CuerpoAlertas
                            + "</td>"
                            + "</tr>"
                            + "<tr>"
                            + "<td align=\"center\">"                            
                            + "<img src=\"https://www.salfalink.cl/Mail/FallaR.png\" width=\"39\" height=\"34\">\n" 
//                            + "<img src=\"http://image.ibb.co/mSXLQd/FallaR.png\" width=\"39\" height=\"34\">\n" 
//                             + "<img src=\"http://150.0.0.169:52322/Mail/FallaR.png\" width=\"39\" height=\"34\">\n" 
                            + "</td>"                                                        
                            + "</tr>"                            
                            + "<tr>"
                            + "<td align=\"center\">"
//                            + "<font size=\"4\" face=\"calibri\" color=\""+ColorAzul+"\"><a href=\"https://www.salfalink.cl/InformeUltimosDias.asp?IDU='+convert(varchar,"+CodU+"*1024-128)+'&TP='+'2'+'\">Aqui</a> podra ver el comportamiento semanal de cada camion</font><br>\n" 
                            + "</td>"
                            + "</tr>"
                            + "</table>"                            
                            + "</td>"
                            + "</tr>'";
                            
                            String FG1=Fecha.substring(8,10)+"/"+Fecha.substring(5,7)+"/"+Fecha.substring(0,4);
                            String FG2=Fecha2.substring(8,10)+"/"+Fecha2.substring(5,7)+"/"+Fecha2.substring(0,4);
                           Cuerpo2=Cuerpo2    + "<tr><td bgcolor=\"#F3F3F3\" align=\"center\">"
                            + "<table bgcolor=\"#FCFBFB\" width=\"700\" border=\"0\">"
                            
                            //***********************************
                            //PRIMER GRAFICO
                            //***********************************                        
                            + "<tr><td  align=\"center\">"
                            + "<font face=\"calibri\" size=\"4\" color=\""+ColorGris+"\">Mayor ralentí de la semana</font><br>\n" 
                            + "</td></tr>"
                            
                            + "<tr><td align=\"center\">"                                                        
                            +"<table width=\"80%\">"
                            + "<tr align=\"left\">"
                            + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+RalDia+" Horas ralenti flota<br>Dia mayor ralenti "+Dia+" "+Fecha.substring(8,10)+"/"+Fecha.substring(5,7)+"/"+Fecha.substring(0,4)+"</font></td>"
                            + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+Patente+" , "+TotalRalVeh+" Horas<br>Unidad mayor ralenti dia "+Dia+" "+Fecha.substring(8,10)+"/"+Fecha.substring(5,7)+"/"+Fecha.substring(0,4)+"</font></td>"
                            + "</tr>"
                            + "</table>"
                            + "</td></tr>"                            
                            
                            + "<tr>"
                            + "<td align=\"center\">"
                            + "<div style=\"position: relative;\">"
                            +   "<div style=\"position: absolute; left: 150px; top: 20px; z-index: 3;\"><font face=\"calibri\" size=\"4\" color=\""+ColorGris+"\"> Hrs </font> </div>"
                            +   "<img src=\""+UrlGrafico1+"\" width=\"400\" height=\"250\"> "
                            +   "<div style=\"position: absolute; left: 550px; top: 230px; z-index: 3;\"><font face=\"calibri\" size=\"4\" color=\""+ColorGris+"\"> Dias </font></div>"
                            +"</div>"
                    
                          //  + "<img src=\""+UrlGrafico1+"\" width=\"400\" height=\"250\">" 
                            + "</td>"
                            + "</tr>"
                            
                            + "<tr><td align=\"center\">"
                            + "<font size=\"2\" face=\"calibri\" color=\""+ColorAzul+"\">RECOMENDACION: Evite el exceso de tiempo en ralenti ya que puede provocar daños graves en el motor disminuyendo la vida util de su camion</font><br><br>"
                            + "</td>"
                            + "</tr>"     
                            
                            //***********************************
                            //SEGUNDO GRAFICO
                            //***********************************                                                         
                            + "<tr><td  align=\"center\">"
                            + "<font face=\"calibri\" size=\"4\" color=\""+ColorGris+"\">Mayor consumo de combustible de la semana</font><br>" 
                            + "</td></tr>"                            

                            + "<tr><td align=\"center\">"                                                        
                            +"<table width=\"80%\">"
                            + "<tr align=\"left\">"
                            + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+LtsDia+" litros mayor consumo<br> de flota dia  "+Dia2+" "+Fecha2.substring(8,10)+"/"+Fecha2.substring(5,7)+"/"+Fecha2.substring(0,4)+"</font></td>"
                            + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+Patente2+" , "+TotalLtsVeh+" Litros<br>Unidad mayor consumo dia "+Dia2+" "+Fecha2.substring(8,10)+"/"+Fecha2.substring(5,7)+"/"+Fecha2.substring(0,4)+"</font></td>"
                            + "</tr>"
                            + "</table>" 
                            + "</td></tr>"
                            
                            + "<tr>"
                            + "<td align=\"center\">"
                            + "<tr>"
                            + "<td align=\"center\">"
                            + "<div style=\"position: relative;\">"
                            +   "<div style=\"position: absolute; left: 153px; top: 20px; z-index: 3;\"><font face=\"calibri\" size=\"4\" color=\""+ColorGris+"\"> Lts </font> </div>"
                            +   "<img src=\""+UrlGrafico2+"\" width=\"400\" height=\"250\"> "
                            +   "<div style=\"position: absolute; left: 550px; top: 230px; z-index: 3;\"><font face=\"calibri\" size=\"4\" color=\""+ColorGris+"\"> Dias </font></div>"
                            +"</div>"       
                          //  + "<img src=\""+UrlGrafico2+"\" width=\"400\" height=\"250\">\n" 
                            + "</td>"
                            + "</tr>"
                            + "<tr>"
                            + "<td align=\"center\">"
                            + "<font size=\"2\" face=\"calibri\" color=\""+ColorAzul+"\">RECOMENDACION:Revisa periodicamente el rendimiento de tus camiones, puedes generar importantes ahorros en los costos de operacion de tu flota</font><br><br>"                             
                            + "</td>"
                            + "</tr>"                            
                            
                            
                            //***********************************
                            //TERCER GRAFICO
                            //***********************************   
                           
                            + "<tr><td  align=\"center\">"
                            + "<font face=\"calibri\" size=\"4\" color=\""+ColorGris+"\">Mejor y peor rendimiento de combustible</font><br>\n" 
                            + "</td></tr>"; 
                                    
                            
                            
                            Cuerpo2=Cuerpo2+ ResumenMRD;
                         
                                           
                        if (Flag2==true){
                         
                            if (UrlGrafico3.equalsIgnoreCase("")){
                            
                            Cuerpo2=Cuerpo2+"<tr>"
                            + "<td align=\"center\">"
                            + "<div style=\"position: relative;\">"
                            + "<div style=\"position: absolute; left: 112px; top: 20px; z-index: 3;\"><font face=\"calibri\" size=\"4\" color=\""+ColorGris+"\"> Km/Lts </font> </div>"
                            + "<div style=\"position: absolute; left: 550px; top: 230px; z-index: 3;\"><font face=\"calibri\" size=\"4\" color=\""+ColorGris+"\"> Dias </font></div>"
                            + "</div>"        
                            + "</td>"
                            + "</tr>";
                                
                            }else{
                            
                           Cuerpo2=Cuerpo2+"<tr>"
                            + "<td align=\"center\">"
                            + "<div style=\"position: relative;\">"
                            + "<div style=\"position: absolute; left: 112px; top: 20px; z-index: 3;\"><font face=\"calibri\" size=\"4\" color=\""+ColorGris+"\"> Km/Lts </font> </div>"
                            + "<img src=\""+UrlGrafico3+"\" width=\"400\" height=\"250\"> "
                            + "<div style=\"position: absolute; left: 550px; top: 230px; z-index: 3;\"><font face=\"calibri\" size=\"4\" color=\""+ColorGris+"\"> Dias </font></div>"
                            + "</div>"        
                            + "</td>"
                            + "</tr>";
                            }
                       }
                            Cuerpo2=Cuerpo2+ "<tr>"
                            + "<td align=\"center\">"
                            + "<font size=\"2\" face=\"calibri\" color=\""+ColorAzul+"\">RECOMENDACION:Manten el inflado de tus neumaticos con la presion adecuada, puedes reducir el consumo de combustible hasta en un 3%.</font><br><br>"                             
                            + "</td>"
                            + "</tr>" ; 
                            
                            
                            //***********************************
                            //CUARTO GRAFICO
                            //***********************************   
                            Cuerpo2=Cuerpo2+  "<tr><td  align=\"center\">"
                            +"<font face=\"calibri\" size=\"4\" color=\""+ColorGris+"\">Comparado a la semana anterior</font><br>\n" 
                            + "</td></tr>"+ResumenRRL+ "" ;
                    
                        
                    
                            if (UrlGrafico4!="SIN DATOS"){
                            
                            Cuerpo2=Cuerpo2+  "<tr><td align=\"center\"> <div style=\"position: relative;\">"
                            +   "<div style=\"position: absolute; left: 153px; top: 20px; z-index: 3;\"><font face=\"calibri\" size=\"4\" color=\""+ColorGris+"\"> Pct.% </font> </div>"
                            +   "<img src=\""+UrlGrafico4+"\" width=\"400\" height=\"250\"> "
                            +   "<div style=\"position: absolute; left: 550px; top: 230px; z-index: 3;\"><font face=\"calibri\" size=\"4\" color=\""+ColorGris+"\"> Vs. </font></div>"
                            +"</div>"       
                            + "</td>"
                            + "</tr>";
                            }
                            else
                                {                                 
                                       
                            Cuerpo2=Cuerpo2+ "<tr><td  align=\"center\">"
                            + "<font face=\"calibri\"  size=\"2\" color=\""+ColorGris+"\">SIN INFORMACION SEMANA ANTERIOR</font><br>"                             
                            + "</td></tr>";
                            
                             }
                            String Cuerpo3=Cuerpo2;
                             Cuerpo3=Cuerpo3+ "</table>"
                            + "</td></tr>"
                            + "<tr><td><br>"
                            + "<center><font face=\"calibri\"  size=\"2\" color=\""+ColorGris+"\">SALFA-Salinas Y Fabres S.A.| Post-Venta | Servicio Pesado | 600 360 6200</font></center>"
                            + "</td></tr>"
                            + "</table>"
                            + "</body>'" ;
                            
                             String UrlG1,UrlG2,UrlG3,UrlG4,MR3a,PR3a,Fm3,Fp3="";
                             
//                             showLinks(UrlGrafico1);
//                             UrlG1=URLG;
//                             showLinks(UrlGrafico2);
//                             UrlG2=URLG;
//                             showLinks(UrlGrafico3);
//                             UrlG3=URLG;
//                             showLinks(UrlGrafico4);
//                             UrlG4=URLG;
                             
                             
                             MR3a=String.valueOf(Float.parseFloat(MR3.toString()));
                             PR3a=String.valueOf(Float.parseFloat(PR3.toString()));
                             Fm3= FechaM3.substring(8,10)+"/"+FechaM3.substring(5,7)+"/"+FechaM3.substring(0,4);
                             Fp3= FechaP3.substring(8,10)+"/"+FechaP3.substring(5,7)+"/"+FechaP3.substring(0,4);
                             
                       // Encriptar(Datos3);
                             
                  
                   //   System.out.println("Cuerpo: "+Cuerpo);
                  //  System.out.println("Cuerpo4: "+Cuerpo4);
//                    Log.log(Cuerpo);
//                    Log.log(Cuerpo2);
                    
                    st2 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    SQuery2="update  ReporteSaludTXMail set Cuerpo="+Cuerpo; 
                    System.out.println(SQuery2);
                    st2.executeUpdate(SQuery2); 
                    
                    
                               
                    String DEF;      
                    SQuery2="select cuerpo from ReporteSaludTXMail";
                    rs=st.executeQuery(SQuery2);
                    rs.next();
                    DEF=rs.getObject(1).toString();
                    

                    
                             
//       String   PDF= "<tr>"
//       +"<td valign=\"top\" colspan =\"6\" style=\"background-color:#BEC0C1;\" align=\"right\">"
//
//                +"<a href=\"https://www.salfalink.cl/Pdf_ReporteSalud.asp?param="
//               +""+NomUsuario+"@"+NomEmp+"@"+KM+"@"+Rendimiento+"@"+Litros+"@"+Ralenti+"@"+ACrit+"@"+Horas+""
//               +"@"+fechaFin+"@"+fechaIni+"@"+RalDia+"@"+Dia+"@"+Patente+"@"+TotalRalVeh+"@"+FG1+"@"+UrlG1+""
//               +"@"+LtsDia+"@"+Dia2+"@"+FG2+"@"+Patente2+"@"+TotalLtsVeh+"@"+UrlG2+"@"+PatenteM3+"@"+PatenteP3+""
//               +"@"+MR3a+"@"+PR3a+"@"+DiaM3+"@"+DiaP3+"@"+Fm3+"@"+Fp3+"@"+Flag2+"@"+UrlG3+"@"+RalentiAn+"@"+LitrosAn+"@"+RendimientoAn+"@"+UrlG4+""
//               +"@"+contA5+"@"+contPerio5+"@"+contNo5+"@<!--"+Datos3+"-->\">"
//       +"<img src=\"https://www.salfalink.cl/Mail/pdf.png\" width=35 height=35 title=\"Exportar a PDF\"> </a>"
//       +"</td>"
//    +"</tr>";      
       
      
       
           String Cuerpo4=Cuerpo2;
                             Cuerpo4=Cuerpo4+ "</table>"
                            + "</td></tr>"
                            
                            + "<tr><td><br>"
                            + "<center><font face=\"calibri\"  size=\"2\" color=\""+ColorGris+"\">SALFA-Salinas Y Fabres S.A.| Post-Venta | Servicio Pesado | 600 360 6200</font></center>"
                            + "</td></tr>"
                          //  +PDF
                            + "</table>"
                            + "</body>'" ;
                             
                             
                             
                             
                              String Cx=DEF.replace("https://www.salfalink.cl", "http://150.0.0.169:52322")+Cuerpo4;   
//                                String Cx=DEF.replace("http://150.0.0.169:52322", "https://www.salfalink.cl")+Cuerpo4;   
                              String nombreArc=NomUsuario+NomEmp+anoD+mesD+diaD;
                              
                              GeneraPDF(Cx, nombreArc);
                            
                              
                              String CodRa;
                    SQuery2="select CodRA from ReporteAutomatico where ListaDistribucion like '%"+Correo+"%' and TipoReporte_CodTipoRep='8'";
                    
                    rs=st.executeQuery(SQuery2);
                    rs.next();
                    
                if (rs.getRow()>0){
                    
                    CodRa=rs.getObject(1).toString();
                System.out.println("AQUI COMENZAMOS ");
                System.out.println("CodRa: "+CodRa);
                System.out.println("AQUI TERMINAMOS ");
//                System.out.println("AQUI COMENZAMOS ");
//                System.out.println("insert into TxMail(FechaInsercion,Asunto,Cuerpo,Destinatario,Estado) values(getdate(),'Reporte estado de salud flota',concat((select cuerpo from ReporteSaludTXMail),"+Cuerpo4+"),'"+Correo+"',0");
//                System.out.println("AQUI TERMINAMOS ");                             
//                   CodReporte	ReporteAutomatico_CodRA	Asunto          Cuerpo                          NomArchivo                      Estado
//                   153569	4614                    ReporteDiario	Se adjunta reporte diario	ReporteExcon20180502.csv	0

          SQuery2="insert into reporte1 values("+CodRa+",'Reporte estado de salud flota',concat((select cuerpo from ReporteSaludTXMail),"+Cuerpo4+"),'"+nombreArc+".pdf"+"',0) " ;
//          SQuery2="insert into reporte1 values('4741','Reporte estado de salud flota',concat((select cuerpo from ReporteSaludTXMail),"+Cuerpo4+"),'"+nombreArc+".pdf"+"',0) " ;
                    System.out.println(SQuery2);
                    st2.executeUpdate(SQuery2);           
                    
     
//          SQuery2="insert into TxMail(FechaInsercion,Asunto,Cuerpo,Destinatario,Estado) values(getdate(),'Reporte estado de salud flota',concat((select cuerpo from ReporteSaludTXMail),"+Cuerpo4+"),'"+Correo+"',0) " 
//                +" insert into TxMail(FechaInsercion,Asunto,Cuerpo,Destinatario,Estado) values(getdate(),'Reporte estado de salud flota',concat((select cuerpo from ReporteSaludTXMail),"+Cuerpo4+"),'ppalaciosf@salfa.cl',0) "
//                +" insert into TxMail(FechaInsercion,Asunto,Cuerpo,Destinatario,Estado) values(getdate(),'Reporte estado de salud flota',concat((select cuerpo from ReporteSaludTXMail),"+Cuerpo4+"),'jrojass@salfa.cl',0) "
//                +" insert into TxMail(FechaInsercion,Asunto,Cuerpo,Destinatario,Estado) values(getdate(),'Reporte estado de salud flota',concat((select cuerpo from ReporteSaludTXMail),"+Cuerpo4+"),'ldiaz@salfa.cl',0) " 
//                +" insert into TxMail(FechaInsercion,Asunto,Cuerpo,Destinatario,Estado) values(getdate(),'Reporte estado de salud flota',concat((select cuerpo from ReporteSaludTXMail),"+Cuerpo4+"),'gvalenzuela@micrologica.cl',0) " ;      

                    
//                    System.out.println(SQuery2);
//                    st2.executeUpdate(SQuery2); 
                    
                    st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    SQuery2="delete from SemanaAnterior where CodUsuario="+CodU+""; 
                    st.executeUpdate(SQuery2);                     
                    System.out.println("Borro Usuario"+CodU);
                    st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    String SQuery="insert into SemanaAnterior values("+CodU+","+KM+","+Rendimiento.replace(',', '.')+","+Litros+","+Ralenti+","+Horas+")"; 
                    System.out.println(SQuery);
                    st.executeUpdate(SQuery); 
                    System.out.println("Inserto Usuario"+CodU);
                    Flag2=false;
                     nombreArc="";
                     Cx=""; 
                    }
                     else{
                     SQuery2="insert into TxMail(FechaInsercion,Asunto,Cuerpo,Destinatario,Estado) values(getdate(),'Error Reporte estado de salud flota ','El Correo,"+Correo+" de la empresa "+NomEmp+" no se encuentra en los reporte automaticos, favor de ingresarlo','gvalenzuela@micrologica.cl',0) " ;
                    
                     Log.log(SQuery2);
                    System.out.println(SQuery2);
                    st2.executeUpdate(SQuery2);   
                             }
                     
                     
                    }
                }
                    
                    
                    
                    
        else{
                        
                        
                        
                        
                        
                        
                        
                        
                        
                          SQuery1="select b.patente, c.Vehiculo_VIN\n" +
                                    ",round(min(convert(float,c.Generico13)),1) --Odo inicial\n" +
                                    ",round(max(convert(float,c.Generico13)),1) --Odo final\n" +
                                    ",round(max(convert(float,c.Generico13)),1)- min(convert(float,c.Generico13))-- KM recorridos\n" +
                                    ",round(min(convert(float,c.Generico14 )),1) --Horo inicial\n" +
                                    ",round(max(convert(float,c.Generico14 )),1) --Horo final\n" +
                                    ",round(max(convert(float,c.Generico14 )),1)-min(convert(float,c.Generico14 ))--Horometro total\n" +
                                    ",round(min(convert(float,c.Generico32 )),1) --Lts inicial\n" +
                                    ",round(max(convert(float,c.Generico32 )),1) --Lts final\n" +
                                    ",round(max(convert(float,c.Generico32 ))-min(convert(float,c.Generico32 )),1)--Lts total\n" +
                                    ",round(sum(convert(float,c.Generico19))/60,1) --Horas Ralenti\n" +
                                    ",round((max(convert(float,c.Generico14 ))-min(convert(float,c.Generico14 )))-(sum(convert(float,c.Generico19))/60),1) --Horas mov\n" +
                                    "from Empresa a inner join Vehiculo b on a.CodEmpresa=b.Empresa_CodEmpresa\n" +
                                    "right join DiasMotorRegRx c on b.VIN=c.Vehiculo_VIN \n" +
                                    "where a.CodEmpresa=(select Empresa_CodEmpresa from Usuario where CodUsuario="+CodU+") \n" +
                                    "--and c.Fecha_Hora>=convert(datetime,'2018-01-15',102) and c.Fecha_Hora<convert(datetime,'2018-01-22',102) \n" +                                    
                                    "and c.Fecha_Hora>=convert(datetime,convert(varchar,dateadd(dd,-7,getdate()),111),102) and c.Fecha_Hora<convert(datetime,convert(varchar,getdate(),111),102) \n" +
                                    "and CONVERT(float,c.generico14)>0 and CONVERT(float,c.generico13)>0 \n" +
                                    "group by b.Patente, c.Vehiculo_VIN";                    
//                    System.out.println(SQuery1);
                    rs=st2.executeQuery(SQuery1);
                    String KM="0",Rendimiento="0",Litros="0",Horas="0",Alertas="0",TpoMovimiento="0",Ralenti="0";
                    
                    
                    rs.last();
                      DecimalFormat df1 = new DecimalFormat("0.0");
                    
               //     if (rs.getRow()>0){
                        rs.beforeFirst();                    
                    while (rs.next()){
                        KM=String.valueOf(Math.round(Float.parseFloat(rs.getObject(5).toString())+Float.parseFloat(KM)));                        
                        Horas=String.valueOf(Math.round(Float.parseFloat(rs.getObject(8).toString())+Float.parseFloat(Horas)));
                        Litros=String.valueOf(Math.round(Float.parseFloat(rs.getObject(11).toString())+Float.parseFloat(Litros)));
                        Ralenti=String.valueOf(Math.round(Float.parseFloat(rs.getObject(12).toString())+Float.parseFloat(Ralenti)));
                        TpoMovimiento=String.valueOf(Math.round(Float.parseFloat(rs.getObject(13).toString())+Float.parseFloat(TpoMovimiento)));                        
                    }
                    if (Float.parseFloat(Litros)==0){
                        Rendimiento=String.valueOf(df1.format(Float.parseFloat(KM)/0.01));
                    }
                    else{
                        Rendimiento=String.valueOf(df1.format(Float.parseFloat(KM)/Float.parseFloat(Litros)));    
                    }
                    ResultSet Rsaux;                    
                    st=con.createStatement();  
                    SQuery2="select top 1 case DATEPART(dw,a.c3)when 1 then 'Domingo' when 2 then 'Lunes' when 3 then 'Martes' when 4 then 'Miercoles' ";
                    SQuery2=SQuery2+ "when 5 then 'Jueves' when 6 then 'Viernes' when 7 then 'Sabado'end,replace(convert(varchar,a.c3,111),'/','-'),round(SUM(a.c4),1),MAX(a.c4) " ;
                    SQuery2=SQuery2+ "from TotalesDiarios a group by a.c3 order by SUM(a.c4) desc";
                    
//                    System.out.println(SQuery2);
                    Log.log(SQuery2);
                    Rsaux=st.executeQuery(SQuery2);                    
                    
                   //**************************************
                //*****MAYOR RALENTI
                  //**************************************
                  
          
                    String Cuerpo,Cuerpo2,Dia,Fecha,RalDia,Vehiculo,TotalRalVeh,RalMaxVeh,Patente;
                    String ResumenRRL="";
                    String ResumenMRD="";
       
                    
                    SQuery2="select case DATEPART(dw,a.c3)when 1 then 'Domingo' when 2 then 'Lunes' when 3 then 'Martes' when 4 then 'Miercoles' \n" +
                            "when 5 then 'Jueves' when 6 then 'Viernes' when 7 then 'Sabado'end,replace(convert(varchar,a.c3,111),'/','-'),round(SUM(a.c4),1)\n" +
                            "from TotalesDiarios a   group by a.c3 having round(SUM(a.c4),1)>0 order by a.c3 asc";
                    Log.log(SQuery2);
                    rs=st.executeQuery(SQuery2);
                    String datosG1="";
                    while (rs.next()){
                        datosG1=datosG1+rs.getObject(3).toString()+",";
                    }                 
                 
                    String UrlGrafico1="";
                    UrlGrafico1=Encripta("{\"options\":{\"data\":{\"labels\":[\"L\",\"M\",\"M\",\"J\",\"V\",\"S\",\"D\"],\"datasets\" :[{\"label\":\"Ralenti\",\"backgroundColor\" : \"rgba(50,50,50,0.5)\",\"data\" :["+datosG1+"]}]}}}","ralenti");
                      
                    //**************************************
                    //*****MAYOR CONSUMO COMBUSTIBLE
                    //**************************************
                    
                    st=con.createStatement();  
                    SQuery2="select top 1 case DATEPART(dw,a.c3)when 1 then 'Domingo' when 2 then 'Lunes' when 3 then 'Martes' when 4 then 'Miercoles' \n" +
                            "when 5 then 'Jueves' when 6 then 'Viernes' when 7 then 'Sabado'end,replace(convert(varchar,a.c3,111),'/','-'),round(convert(float,SUM(a.c7)),1),round(convert(float,MAX(a.c7)),1)\n" +
                            "from TotalesDiarios a   group by a.c3  order by SUM(a.c7) desc";
                    Log.log(SQuery2);
                    rs=st.executeQuery(SQuery2);                    

                    String Dia2,Fecha2,LtsDia,Vehiculo2,TotalLtsVeh,LtsMaxVeh,Patente2;

                    SQuery2="select  case DATEPART(dw,a.c3)when 1 then 'Domingo' when 2 then 'Lunes' when 3 then 'Martes' when 4 then 'Miercoles' \n" +
                            "when 5 then 'Jueves' when 6 then 'Viernes' when 7 then 'Sabado'end,replace(convert(varchar,a.c3,111),'/','-'),round(convert(float,SUM(a.c7)),1) \n" +
                            "from TotalesDiarios a  group by a.c3   order by a.c3 asc";
                    Log.log(SQuery2);
                    rs=st.executeQuery(SQuery2);
                    String datosG2="";
                    while (rs.next()){
                        datosG2=datosG2+rs.getObject(3).toString()+",";
                    }                 
               
                    
                    String UrlGrafico2="";
                    
                    UrlGrafico2=Encripta("{\"options\":{\"data\":{\"labels\":[\"L\",\"M\",\"M\",\"J\",\"V\",\"S\",\"D\"],\"datasets\" :[{\"label\":\"Consumos \",\"backgroundColor\" : \"rgba(50,50,50,0.5)\",\"data\" :["+datosG2+"]}]}}}","consumos");                    
                    
                     
                    //**************************************
                    //*****MENOR RENDIMIENTO DE COMBUSTIBLE
                    //**************************************
                    System.out.println("---------------MENOR RENDIMIENTO DE COMBUSTIBLE----------------");
                    st=con.createStatement();  
                    SQuery2="select  case DATEPART(dw,a.c3)when 1 then 'Domingo' when 2 then 'Lunes' when 3 then 'Martes' when 4 then 'Miercoles' \n" +
                            "when 5 then 'Jueves' when 6 then 'Viernes' when 7 then 'Sabado'end,replace(convert(varchar,a.c3,111),'/','-'),round(convert(float,SUM(a.c10)/case SUM(a.c7) when 0 then 0.01 else SUM(a.c7) end),1)\n" +
                            "from TotalesDiarios a  group by a.c3 having round(SUM(a.c10)/case SUM(a.c7) when 0 then 0.01 else SUM(a.c7) end,3)>0 \n" +
                            "order by a.c3 asc "+
                            "--order by round(convert(float,SUM(a.c10)/case SUM(a.c7) when 0 then 0.01 else SUM(a.c7) end),1) asc";
                    Log.log(SQuery2);
                    String datosG3A="";
                    String ds="";
                    String Lunes="0";
                    String Martes="0";
                    String Miercoles="0";
                    String Jueves="0";
                    String Viernes="0";
                    String Sabado="0";
                    String Domingo="0";
                    String Semana="0";
                    rs=st.executeQuery(SQuery2); 
                    while (rs.next()){
                      //  Lunes
                        if(rs.getObject(1).toString().equals("Lunes")){
                            if (Float.parseFloat(rs.getObject(3).toString())>0){
                            Lunes=rs.getObject(3).toString(); 
                            }
                        }
                        //Martes
                        if(rs.getObject(1).toString().equals("Martes")){
                            if (Float.parseFloat(rs.getObject(3).toString())>0){
                            Martes=rs.getObject(3).toString();                                  
                            }
                        }
                        //Miercoles
                        if(rs.getObject(1).toString().equals("Miercoles")){
                            if (Float.parseFloat(rs.getObject(3).toString())>0){
                            Miercoles=rs.getObject(3).toString();                                  
                            }
                        }
                        //Jueves
                           if(rs.getObject(1).toString().equals("Jueves")){
                            if (Float.parseFloat(rs.getObject(3).toString())>0){
                            Jueves=rs.getObject(3).toString();                                  
                            }
                        }
                        //Viernes
                           if(rs.getObject(1).toString().equals("Viernes")){
                            if (Float.parseFloat(rs.getObject(3).toString())>0){
                            Viernes=rs.getObject(3).toString();                                  
                            }
                        }
                        //Sabado
                           if(rs.getObject(1).toString().equals("Sabado")){
                            if (Float.parseFloat(rs.getObject(3).toString())>0){
                            Sabado=rs.getObject(3).toString();                                  
                            }
                        }
                        //Domingo
                           if(rs.getObject(1).toString().equals("Domingo")){
                            if (Float.parseFloat(rs.getObject(3).toString())>0){
                            Domingo=rs.getObject(3).toString();                                  
                            }
                        }

                    }
                    Semana=Lunes+","+Martes+","+Miercoles+","+Jueves+","+Viernes+","+Sabado+","+Domingo;
                    datosG3A=Semana;
                    System.out.println("---------------Semana----------------");
                    System.out.println(" Semana: Lunes,Martes,Miercoles,Jueves,Viernes,Sabado,Domingo ");
                    System.out.println(" Semana: "+Semana);
                    System.out.println(" datosG3A: "+datosG3A);
                    System.out.println(" Dias de las semanas: "+ds);
                    System.out.println("-------------------------------------");
                    

                    SQuery2="select top 1 case DATEPART(dw,a.c3)when 1 then 'Domingo' when 2 then 'Lunes' when 3 then 'Martes' when 4 then 'Miercoles' \n" +
                            "when 5 then 'Jueves' when 6 then 'Viernes' when 7 then 'Sabado'end, "+
                            "replace(convert(varchar,a.c3,111),'/','-'), \n"+
                            "round(convert(float,SUM(a.c10)/case SUM(a.c7) when 0 then 0.01 else SUM(a.c7) end),1)\n" +
                            "from TotalesDiarios a  group by a.c3 having round(SUM(a.c10)/case SUM(a.c7) when 0 then 0.01 else SUM(a.c7) end,3)>0 \n" +
                            "order by round(convert(float,SUM(a.c10)/case SUM(a.c7) when 0 then 0.01 else SUM(a.c7) end),1) asc";
                    Log.log(SQuery2);
                        rs=st.executeQuery(SQuery2); 
                        
                    String Dia3,Fecha3,ConDia,Vehiculo3,TotalConVeh,ConMinVeh,Patente3,Dia3a,Fecha3a;

                  SQuery2="select top 1 isnull(a.c1,a.c2),a.c2,a.c3, round(convert(float,SUM(a.c10)/case SUM(a.c7) when 0 then 0.01 else SUM(a.c7) end) ,1)\n" 
                    + ",case DATEPART(dw,a.c3)when 1 then 'Domingo' when 2 then 'Lunes' when 3 then 'Martes' when 4 then 'Miercoles' \n" 
                    + "when 5 then 'Jueves' when 6 then 'Viernes' when 7 then 'Sabado'end \n"
                    + "from TotalesDiarios a  group by a.c1,a.c2,a.c3 having round(convert(float,SUM(a.c10)/case SUM(a.c7) when 0 then 0.01 else SUM(a.c7) end),3)>0 order by round(SUM(a.c10)/case SUM(a.c7) when 0 then 0.01 else SUM(a.c7) end,1) desc";
                    Log.log(SQuery2);
                    rs=st.executeQuery(SQuery2);                                        
     
                    
                    String Patente31, Vehiculo31,  Fecha3a1,   TotalConVeh1,   Dia3a1;
                    SQuery2="select top 1 isnull(a.c1,a.c2),a.c2,a.c3, round(convert(float,SUM(a.c10)/case SUM(a.c7) when 0 then 0.01 else SUM(a.c7) end) ,1)\n" 
                    + ",case DATEPART(dw,a.c3)when 1 then 'Domingo' when 2 then 'Lunes' when 3 then 'Martes' when 4 then 'Miercoles' \n" 
                    + "when 5 then 'Jueves' when 6 then 'Viernes' when 7 then 'Sabado'end \n"
                    + "from TotalesDiarios a  group by a.c1,a.c2,a.c3 having round(convert(float,SUM(a.c10)/case SUM(a.c7) when 0 then 0.01 else SUM(a.c7) end),3)>0 order by round(SUM(a.c10)/case SUM(a.c7) when 0 then 0.01 else SUM(a.c7) end,1) asc";
                    Log.log(SQuery2);
                    rs=st.executeQuery(SQuery2);                                        

            
                    
                    SQuery2="select case DATEPART(dw,a.c3)when 1 then 'Domingo' when 2 then 'Lunes' when 3 then 'Martes' when 4 then 'Miercoles' \n" +
                            "when 5 then 'Jueves' when 6 then 'Viernes' when 7 then 'Sabado'end, "+
                            "replace(convert(varchar,a.c3,111),'/','-'), "+
                            "round(convert(float,SUM(a.c10)/case SUM(a.c7) when 0 then 0.01 else SUM(a.c7) end),3)\n" +
                            "from TotalesDiarios  a group by a.c3  order by a.c3 asc";
                    Log.log(SQuery2);

                    rs=st.executeQuery(SQuery2); 

                    String datosG3="";
                    String datG3="";
                    String ds2="";
                    String Lunes2="0";
                    String Martes2="0";
                    String Miercoles2="0";
                    String Jueves2="0";
                    String Viernes2="0";
                    String Sabado2="0";
                    String Domingo2="0";
                    String Semana2="0";
                    while (rs.next()){
                        //Lunes
                           if(rs.getObject(1).toString().equals("Lunes")){
                            if (Float.parseFloat(rs.getObject(3).toString())>0){
//                            Lunes2=String.valueOf(Float.parseFloat(rs.getObject(3).toString())-Float.parseFloat(ConDia));                                  
                            }
                        }
                        //Martes
                          if(rs.getObject(1).toString().equals("Martes")){
                           if (Float.parseFloat(rs.getObject(3).toString())>0){
//                           Martes2=String.valueOf(Float.parseFloat(rs.getObject(3).toString())-Float.parseFloat(ConDia));                                  
                           }
                        }
                        //Miercoles
                          if(rs.getObject(1).toString().equals("Miercoles")){
                           if (Float.parseFloat(rs.getObject(3).toString())>0){
//                           Miercoles2=String.valueOf(Float.parseFloat(rs.getObject(3).toString())-Float.parseFloat(ConDia));                                  
                           }
                        }
                        //Jueves
                          if(rs.getObject(1).toString().equals("Jueves")){
                           if (Float.parseFloat(rs.getObject(3).toString())>0){
//                           Jueves2=String.valueOf(Float.parseFloat(rs.getObject(3).toString())-Float.parseFloat(ConDia));                                  
                           }
                        }
                        //Viernes
                        if(rs.getObject(1).toString().equals("Viernes")){
                           if (Float.parseFloat(rs.getObject(3).toString())>0){
//                           Viernes2=String.valueOf(Float.parseFloat(rs.getObject(3).toString())-Float.parseFloat(ConDia));                                  
                           }
                        }
                        //Sabado
                        if(rs.getObject(1).toString().equals("Sabado")){
                           if (Float.parseFloat(rs.getObject(3).toString())>0){
//                           Sabado2=String.valueOf(Float.parseFloat(rs.getObject(3).toString())-Float.parseFloat(ConDia));                                  
                           }
                        }
                        //Domingo
                        if(rs.getObject(1).toString().equals("Domingo")){
                           if (Float.parseFloat(rs.getObject(3).toString())>0){
//                           Domingo2=String.valueOf(Float.parseFloat(rs.getObject(3).toString())-Float.parseFloat(ConDia));                                  
                           }
                        }
                    }                 
                    
                        
                    Semana2=Lunes2+","+Martes2+","+Miercoles2+","+Jueves2+","+Viernes2+","+Sabado2+","+Domingo2;
                    datosG3=Semana2;
                    System.out.println("---------------Semana2----------------");
                    System.out.println(" Semana: Lunes,Martes,Miercoles,Jueves,Viernes,Sabado,Domingo ");
                    System.out.println(" Semana: "+Semana2);
                    System.out.println(" datosG3:"+datosG3);
                    System.out.println(" Dias de las semanas: "+ds2);
                    System.out.println("datG3: "+datG3);
//                    System.out.println("ConDia: "+ConDia);
                    System.out.println("-------------------------------------");
                    
                    
                    String SQuery3="select count(c.CodNotificacion) from Empresa a inner join Vehiculo b on a.CodEmpresa=b.Empresa_CodEmpresa\n" +
                            "right join Notificacion c on b.VIN=c.Vehiculo_VIN\n" +
                            "where a.CodEmpresa=(select Empresa_CodEmpresa from Usuario where CodUsuario="+CodU+")\n" +
                            "and c.Fecha_Hora>=convert(varchar,convert(date,dateadd(dd,-8,getdate()))) and c.Fecha_Hora<=convert(varchar,convert(date,dateadd(dd,-1,getdate()))) and c.Generico_CodGenerico in (select CodGenerico from Generico where Gravedad_CodGravedad=5)";
                    
//                 System.out.println(SQuery3);
                    Log.log(SQuery3);
                    rs2=st.executeQuery(SQuery3);
                    rs2.next();
                    String ACrit="";
                    ACrit=rs2.getObject(1).toString();
                    
                    System.out.println("---------------Inicio----------------");
                    System.out.println(" datosG3A: "+datosG3A);
                    System.out.println(" Dias de las semanas: "+ds);
                    System.out.println("-------------------------------------");
                    System.out.println("---------------Inicio2----------------");
                    System.out.println(" datosG3:"+datosG3);
                    System.out.println(" Dias de las semanas: "+ds2);
                    System.out.println("datG3: "+datG3);
//                    System.out.println("ConDia: "+ConDia);
                    System.out.println("-------------------------------------");
                    
                    System.out.println("----------------------------------------------");
                    System.out.println("DATOS GRAFICO 3");
//                    System.out.println("CON DIA: "+ConDia);
                    System.out.println("DATOSG3 (mayor rendimiento): "+datosG3A);
                    System.out.println("DATOSG3A (menor rendimiento): "+datosG3);
                    System.out.println("----------------------------------------------");
                    
                    

                    
    ResumenMRD= " <tr>"
    + "<td align=\"center\">"                                                      
        + "<table width=\"60%\" border=\"1\">"
        + "<tr align=\"center\">"
        + "<th></th>"
        + "<th><font face=\"calibri\" size=\"4\" color=\""+ColorGris+"\">Menor rendimiento semanal flota</font></th>"
        + "<th><font face=\"calibri\" size=\"4\" color=\""+ColorGris+"\">Mayor rendimiento semanal flota</font></th>"
        + "</tr>"
        + "<tr align=\"center\">"
        + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">Patente o VIN</font></td>"
//        + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+Patente31+"</font></td>"
//        + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+Patente3+"</font></td>"
        + "</tr>"
        + "<tr align=\"center\">"
        + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">Km/Lts</font></td>"
//        + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+String.valueOf(Float.parseFloat(TotalConVeh1.toString()))+"</font></td>"
//        + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+String.valueOf(Float.parseFloat(TotalConVeh.toString()))+"</font></td>"
        + "</tr>"
        + "</tr>"
        + "<tr align=\"center\">"
        + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">Dia</font></td>"
//        + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+Dia3a1+" "+Fecha3a1.substring(8,10)+"/"+Fecha3a1.substring(5,7)+"/"+Fecha3a1.substring(0,4)+"</font></td>"
//        + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+Dia3a+" "+Fecha3a.substring(8,10)+"/"+Fecha3a.substring(5,7)+"/"+Fecha3a.substring(0,4)+"</font></td>"
        + "</tr>"                        
                                
        + "</table>"
    + "</td>"
+ "</tr> "  ;
                    
                    
                    
                                        
                    String UrlGrafico3="";
                    UrlGrafico3=Encripta(                  
                        "{\n"+
                        "\"options\" : {\n"+
                        "\"data\" : {\n"+
                        "\"labels\" : [\n"+
                        "        \"L\",\n" +
                        "        \"M\",\n" +
                        "        \"M\",\n" +
                        "        \"J\",\n" +
                        "        \"V\",\n" +
                        "        \"S\",\n" +
                        "        \"D\"\n" +
                        "],\n"+
                        "\"datasets\" : [\n"+
                        "{\n"+
                        "\"label\" : \"Menor Rendimiento Promedio flota\",\n"+
                        "\"backgroundColor\" : \"rgba(220,220,220,0.5)\",\n"+
                        "\"data\" : [\n"+datosG3+
                        "]\n"+
                        "},\n"+
                        "{\n"+
                        "\"label\" : \"Mayor Rendimiento Promedio flota\",\n"+
                        "\"backgroundColor\" : \"rgba(90,90,90,0.5)\",\n"+
                        "\"data\" : [\n"+datosG3A+
                        "]\n"+
                        "}\n"+                    
                        "]\n"+
                        "}\n"+
                        "}\n"+
                        "}","semana");

                    
                        String SQuery4="select isnull(KM,0),isnull(Ralenti,0),isnull(Litros,0),isnull(Rendimiento,0) from SemanaAnterior where CodUsuario="+CodU;                        
                        st4 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        rs4=st4.executeQuery(SQuery4);
                        rs4.last();
                        String UrlGrafico4="";
                        if (rs4.getRow()>0){
                        rs4.beforeFirst();                    
                        rs4.next();
                        String data1,data2;
                        data1=100+","+100+","+100;
                        String RalentiSAc="0";
                        String RendimientoSAc="0";
                        String LitrosSAc="0";
                   
                        System.out.println("----------------------------------------------");
                        System.out.println("SemanaAnterior: "+data1);
                        System.out.println("----------------------------------------------");
                          System.out.println("----------------------------------------------");
                        System.out.println("SemanaAcatual: "+Ralenti+","+Rendimiento+","+Litros);
                          System.out.println("----------------------------------------------");
                //***********************************
                //Ralenti
                //***********************************
                if (Float.parseFloat(rs4.getObject(2).toString())>0){
RalentiSAc=String.valueOf(Math.round((Float.parseFloat(Ralenti.toString().replace(",", "."))*100)/Float.parseFloat(rs4.getObject(2).toString())));    
System.out.println("----------------------------------------------");
System.out.println("Ralenti SemanaAnterior): "+Float.parseFloat(rs4.getObject(2).toString()) );
System.out.println("Ralenti Actual: "+Ralenti);
System.out.println("Math.round((Float.parseFloat(Ralenti)*100): "+Math.round((Float.parseFloat(Ralenti)*100)/Float.parseFloat(rs4.getObject(2).toString()))  );
System.out.println("RalentiSAc: "+RalentiSAc);
System.out.println("----------------------------------------------");
                        }
                 else
                 {
                     RalentiSAc="0";
                 }                 
                          
                //***********************************
                //Rendimiento
                //***********************************
                if (Float.parseFloat(rs4.getObject(4).toString())>0){
RendimientoSAc=String.valueOf(Math.round((Float.parseFloat(Rendimiento.toString().replace(",", "."))*100)/Float.parseFloat(rs4.getObject(4).toString())));                    
System.out.println("----------------------------------------------");
System.out.println("Rendimiento SemanaAnterior: "+Float.parseFloat(rs4.getObject(4).toString()) );
System.out.println("Rendimiento Actual: "+Rendimiento.toString().replace(",", "."));
System.out.println("Math.round((Float.parseFloat(Rendimiento)*100): "+Math.round((Float.parseFloat(Rendimiento.toString().replace(",", "."))*100)/Float.parseFloat(rs4.getObject(4).toString()))  );
System.out.println("RendimientoSAc: "+RendimientoSAc);
System.out.println("----------------------------------------------");     

                }
                 else
                 {
                     RendimientoSAc="0";
                 }                 
                      
                //***********************************
                //Litros
                //***********************************
                if (Float.parseFloat(rs4.getObject(3).toString())>0){
LitrosSAc=String.valueOf(Math.round((Float.parseFloat(Litros.toString().replace(",", "."))*100)/Float.parseFloat(rs4.getObject(3).toString())));
System.out.println("----------------------------------------------");
System.out.println("Litros SemanaAnterior: "+Float.parseFloat(rs4.getObject(3).toString()) );
System.out.println("Litros Actual: "+Litros.toString().replace(",", "."));
System.out.println("Math.round((Float.parseFloat(Litros)*100): "+Math.round((Float.parseFloat(Litros.toString().replace(",", "."))*100)/Float.parseFloat(rs4.getObject(3).toString()))  );
System.out.println("LitrosSAc: "+LitrosSAc);
System.out.println("----------------------------------------------");   
                        }
                 else
                 {
                     LitrosSAc="0";
                 }                               
                        String RalentiAn, RendimientoAn,LitrosAn="";
                        RalentiAn=rs4.getObject(2).toString();
                        LitrosAn=rs4.getObject(3).toString();
                        RendimientoAn=rs4.getObject(4).toString();

    ResumenRRL= " <tr>"
    + "<td align=\"center\">"                                                      
        + "<table width=\"60%\" border=\"1\">"
        + "<tr align=\"center\">"
        + "<th></th>"
        + "<th><font face=\"calibri\" size=\"4\" color=\""+ColorGris+"\">Ralenti</font></th>"
        + "<th><font face=\"calibri\" size=\"4\" color=\""+ColorGris+"\">Litros</font></th>"
        + "<th><font face=\"calibri\" size=\"4\" color=\""+ColorGris+"\">Rendimiento</font></th>"
        + "</tr>"
        + "<tr align=\"center\">"
        + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">Semana Anterior</font></td>"
        + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+RalentiAn+"</font></td>"
        + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+LitrosAn+"</font></td>"
        + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+RendimientoAn+"</font></td>"
        + "</tr>"
        + "<tr align=\"center\">"
        + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">Semana Actual</font></td>"
        + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+Ralenti+"</font></td>"
        + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+Litros+"</font></td>"
        + "<td><font size=\"2\" face=\"calibri\" color=\""+ColorGris+"\">"+Rendimiento+"</font></td>"
        + "</tr>"
        + "</table>"
    + "</td>"
+ "</tr> "  ;

                        data2=RalentiSAc+","+LitrosSAc+","+RendimientoSAc ;
                         System.out.println("----------------------------------------------");
                        System.out.println("SemanaAcatual: "+data2);
                          System.out.println("----------------------------------------------");
                        String Grafico="{\n"+
                                    "\"options\" : {\n"+
                                    "\"data\" : {\n"+
                                    "\"labels\" : [\n"+
                                    "\"Ralenti\",\n"+                                
                                    "\"Litros\",\n"+
                                    "\"Rendimiento\"\n"+
                                    "],\n"+
                                    "\"datasets\" : [\n"+
                                    "{\n"+
                                    "\"label\" : \"% Semana Anterior\",\n"+
                                    "\"backgroundColor\" : \"rgba(255,236,0,1)\",\n"+
                                    "\"data\" : [\n"+data1+
                                
                                    "]\n"+
                                    "},\n"+
                                    "{\n"+
                                    "\"label\" : \"% Semana Actual\",\n"+
                                    "\"backgroundColor\" : \"rgba(151,187,205,1)\",\n"+
                                    "\"data\" : [\n"+data2+
                                
                                    "]\n"+
                                    "}\n"+                    
                                    "]\n"+
                                    "}\n"+
                                    "}\n"+
                                    "}";
                        UrlGrafico4=Encripta(Grafico,"semana"); 
                        System.out.println("----------------------------------------------");
                        System.out.println("Ralenti: "+Ralenti+" Rendimiento: "+Rendimiento+"Litros: "+Litros);
                        System.out.println("----------------------------------------------");
                        System.out.println(Grafico);
                                }
                        else
                            {
                                UrlGrafico4="SIN DATOS";
                            }
                                   
                    System.out.println(UrlGrafico4);

                    System.out.println("PC "+ACrit);
                    String CuerpoAlertas="";

                        CuerpoAlertas="<font size=\"4\" face=\"calibri\" color=\""+ColorAzul+"\">NO SE PRESENTAN DATOS EN LA FLOTA DENTRO DE ESTA SEMANA, FAVOR DE CONTACTAR CON SU PROVEEDOR PARA REGULARIZAR SU SITUACI&Oacute;N.</font>\n" ;
                                

                    Cuerpo2="'";        
                    Cuerpo= "'"
                            + "<body bgcolor=\"#FCFBFB\">"
                            + "<center>"
                            + "<table bgcolor=\"#FCFBFB\" align=\"center\" width=\"801\" border=\"0\">\n" 
                            + "<tr>"
                            + "<td align=\"center\"><font face=\"calibri\" size=\"2\"color=\""+ColorGris+"\">"
                            + "<b>"+NomUsuario+"</b>, SALFALINK realiza seguimiento semanal a tu flota de camiones, indicando las<br>"
                            + "variables más importantes de tu operación</font><br>"
                            + "</td>"
                            + "</tr>"
                            + "<tr>"
                            + "<td align=\"left\" bgcolor=\"#1F4E79\">"
                            + "<img src=\"https://www.salfalink.cl/Mail/logo2.png\" width=\"253\" height=\"46\"><br>\n" 
//                            + "<img src=\"http://image.ibb.co/fpc9Jy/logo2.png\" width=\"253\" height=\"46\"><br>\n" 
//                             + "<img src=\"http://150.0.0.169:52322/Mail/logo2.png\" width=\"253\" height=\"46\"><br>\n" 
                            + "</td>"
                            + "</tr>"
                            + "<tr>"
                            + "<td align=\"center\" bgcolor=\"#F3F3F3\">"
                            + "<font face=\"calibri\" size=\"5\" color=\""+ColorAzul+"\">Salud de su flota <u>"+NomEmp+"</u></font>"
                            + "<font face=\"calibri\" size=\"4\" color=\""+ColorGris+"\">"
                            + "<br>"
                            + "Semana "+fechaFin+" al "+fechaIni
                            + "</td>"
                            + "</tr>"

                            +Datos35
                            +Datos3
                          
                            + "<td bgcolor=\"#F3F3F3\">"
                            + "<table bgcolor=\"#F3F3F3\" align=\"center\" width=\"700\" border=\"0\">\n" 
                            + "<tr width=\"100%\" align=\"left\">\n" 
                            + "<td width=\"100\"><img src=\"https://www.salfalink.cl/Mail/Odom.png\" width=\"40\" height=\"40\"></td>\n" 
//                              + "<td width=\"100\"><img src=\"http://image.ibb.co/kEEBWJ/Odom.png\" width=\"40\" height=\"40\"></td>\n" 
//                                 + "<td width=\"100\"><img src=\"http://150.0.0.169:52322/Mail/Odom.png\" width=\"40\" height=\"40\"></td>\n" 
                            + "<td width=\"167\"><table border=\"0\"><tr><td><font size=\"3\"face=\"calibri\" color=\""+ColorGris+"\"><b>"+KM+" Kmt.</b></font></td></tr><tr><td><font size=\"2\"face=\"calibri\" color=\""+ColorGris+"\">Total recorrido semanal</font></td></tr></table></td>\n" 
                            + "<td width=\"100\"><img src=\"https://www.salfalink.cl/Mail/Rsem.png\" width=\"40\" height=\"40\"></td>\n" 
//                             + "<td width=\"100\"><img src=\"http://image.ibb.co/bOb6gJ/Rsem.png\" width=\"40\" height=\"40\"></td>\n" 
//                            + "<td width=\"100\"><img src=\"http://150.0.0.169:52322/Mail/Rsem.png\" width=\"40\" height=\"40\"></td>\n" 
                            
                            + "<td width=\"167\"><table border=\"0\"><tr><td><font size=\"3\"face=\"calibri\" color=\""+ColorGris+"\"><b>"+Rendimiento+" Kmt/Lts</b></font></td></tr><tr><td><font size=\"2\"face=\"calibri\" color=\""+ColorGris+"\">Rendimiento semanal</font></td></tr></table></td>\n" 
                            + "<td width=\"100\"><img src=\"https://www.salfalink.cl/Mail/CCom.png\" width=\"40\" height=\"40\"></td>\n" 
//                            + "<td width=\"100\"><img src=\"https://image.ibb.co/kmgEkd/CCom.png\" width=\"40\" height=\"40\"></td>\n" 
//                             + "<td width=\"100\"><img src=\"http://150.0.0.169:52322/Mail/CCom.png\" width=\"40\" height=\"40\"></td>\n" 
                            + "<td width=\"167\"><table border=\"0\"><tr><td><font size=\"3\"face=\"calibri\" color=\""+ColorGris+"\"><b>"+Litros+" Litros</b></font></td></tr><tr><td><font size=\"2\"face=\"calibri\" color=\""+ColorGris+"\">Consumo de combustible semanal</font></td></tr></table></td>\n" 
                            + "</tr>\n" 
                            + "<tr width=\"100%\" align=\"left\">\n" 
                            + "<td width=\"100\"><img src=\"https://www.salfalink.cl/Mail/TRal.png\" width=\"40\" height=\"40\"></td>\n" 
//                            + "<td width=\"100\"><img src=\"https://image.ibb.co/iNaz1J/TRal.png\" width=\"40\" height=\"40\"></td>\n" 
//                             + "<td width=\"100\"><img src=\"http://150.0.0.169:52322/Mail/TRal.png\" width=\"40\" height=\"40\"></td>\n" 
                            
                            + "<td width=\"167\"><table border=\"0\"><tr><td><font size=\"3\"face=\"calibri\" color=\""+ColorGris+"\"><b>"+Ralenti+ " Horas</b></font></td></tr><tr><td><font size=\"2\"face=\"calibri\" color=\""+ColorGris+"\">Tiempo total en ralentí</font></td></tr></table></td>\n" 
                            + "<td width=\"100\"><img src=\"https://www.salfalink.cl/Mail/AAvi.png\" width=\"40\" height=\"40\"></td>\n" 
//                              + "<td width=\"100\"><img src=\"https://image.ibb.co/dqnWWJ/AAvi.png\" width=\"40\" height=\"40\"></td>\n" 
//                             + "<td width=\"100\"><img src=\"http://150.0.0.169:52322/Mail/AAvi.png\" width=\"40\" height=\"40\"></td>\n" 
                            + "<td width=\"167\"><table border=\"0\"><tr><td><font size=\"3\"face=\"calibri\" color=\""+ColorGris+"\"><b>"+ACrit+" Alertas</b></font></td></tr><tr><td><font size=\"2\"face=\"calibri\" color=\""+ColorGris+"\">Alertas de falla y avisos totales</font></td></tr></table></td>\n" 
                            + "<td width=\"100\"><img src=\"https://www.salfalink.cl/Mail/TMov.png\" width=\"40\" height=\"40\"></td>\n" 
//                             + "<td width=\"100\"><img src=\"https://image.ibb.co/dfX1Fd/TMov.png\" width=\"40\" height=\"40\"></td>\n" 
//                            + "<td width=\"100\"><img src=\"http://150.0.0.169:52322/Mail/TMov.png\" width=\"40\" height=\"40\"></td>\n" 
                            + "<td width=\"167\"><table border=\"0\"><tr><td><font size=\"3\"face=\"calibri\" color=\""+ColorGris+"\"><b>"+Horas+" Horas</b></font></td></tr><tr><td><font size=\"2\"face=\"calibri\" color=\""+ColorGris+"\">Tiempo en movimiento</font></td></tr></table></td>\n" 
                            + "</tr>\n" 
                            + "</table>\n" 
                            + "</td>"
                            + "</tr>"
                            
                            + "<tr>"
                            + "<td bgcolor=\"#F3F3F3\">"
                            + "<table bgcolor=\"#F3F3F3\" align=\"center\" width=\"801\" border=\"0\">"
                            + "<tr>"
                            + "<td align=\"center\">"
                            + "</td>"
                            + "</tr>"
                            + "<tr>"
                            + "<td align=\"center\">"                            
                            + "<img src=\"https://www.salfalink.cl/Mail/FallaR.png\" width=\"39\" height=\"34\">\n" 
//                            + "<img src=\"https://image.ibb.co/mSXLQd/FallaR.png\" width=\"39\" height=\"34\">\n" 
//                            + "<img src=\"http://150.0.0.169:52322/Mail/FallaR.png\" width=\"39\" height=\"34\">\n" 
                            + CuerpoAlertas
                            + "</td>"                                                        
                            + "</tr>"                            
                            + "<tr>"
                            + "<td align=\"center\">"
                        
                            + "</td>"
                            + "</tr>"
                            + "</td>"
                            + "</tr>'";
                            

                            Cuerpo2=Cuerpo2+
                            "</table>"
                            + "</td></tr>"
                            
                            + "<tr><td align=\"center\"><br>"
                            + "<font face=\"calibri\"  size=\"2\" color=\""+ColorGris+"\">SALFA-Salinas Y Fabres S.A.| Post-Venta | Servicio Pesado | 600 360 6200</font>"
                            + "</td></tr>"
                            
                            + "</table>"
                            + "</body>'" ;
                            
                            
                            
               
//                   System.out.println("Cuerpo: "+Cuerpo);
//                    System.out.println("Cuerpo4: "+Cuerpo4);
//                    System.out.println("Cuerpo: "+Cuerpo);
//                    System.out.println("Cuerpo2: "+Cuerpo2);
//                    Log.log(Cuerpo);
//                    Log.log(Cuerpo2);
                    st2 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    SQuery2="update  ReporteSaludTXMail set Cuerpo="+Cuerpo; 
                    System.out.println(SQuery2);
                    st2.executeUpdate(SQuery2); 
                    
                    
                                 
                    String DEF;      
                    SQuery2="select cuerpo from ReporteSaludTXMail";
                    rs=st.executeQuery(SQuery2);
                    rs.next();
                    DEF=rs.getObject(1).toString();
  

                             
                              String Cx="";
                              String nombreArc="";
                              Cx=DEF.replace("https://www.salfalink.cl", "http://150.0.0.169:52322")+Cuerpo2;   
//                              Cx=DEF.replace("http://150.0.0.169:52322", "https://www.salfalink.cl")+Cuerpo2;  
                              nombreArc=NomUsuario+NomEmp+anoD+mesD+diaD;
                              GeneraPDF(Cx, nombreArc);
                          
                    
                    String CodRa;
                    SQuery2="select CodRA from ReporteAutomatico where ListaDistribucion like '%"+Correo+"%' and TipoReporte_CodTipoRep='8'";
                    
                    rs=st.executeQuery(SQuery2);
                    rs.next();
                    
                    if (rs.getRow()>0){
                    
                    
                    CodRa=rs.getObject(1).toString();
                System.out.println("AQUI COMENZAMOS ");
                System.out.println("CodRa: "+CodRa);
                System.out.println("AQUI TERMINAMOS ");

                   SQuery2="insert into reporte1 values("+CodRa+",'Reporte estado de salud flota',concat((select cuerpo from ReporteSaludTXMail),"+Cuerpo2+"),'"+nombreArc+".pdf"+"',0) " ;
//                         SQuery2="insert into reporte1 values('4741','Reporte estado de salud flota',concat((select cuerpo from ReporteSaludTXMail),"+Cuerpo2+"),'"+nombreArc+".pdf"+"',0) " ;
                   
                     st2.executeUpdate(SQuery2); 
//                    SQuery2="insert into TxMail(FechaInsercion,Asunto,Cuerpo,Destinatario,Estado) values(getdate(),'Reporte estado de salud flota',concat((select cuerpo from ReporteSaludTXMail),"+Cuerpo2+"),'"+Correo+"',0) " 
//                          +" insert into TxMail(FechaInsercion,Asunto,Cuerpo,Destinatario,Estado) values(getdate(),'Reporte estado de salud flota',concat((select cuerpo from ReporteSaludTXMail),"+Cuerpo2+"),'ppalaciosf@salfa.cl',0) "
//                          +" insert into TxMail(FechaInsercion,Asunto,Cuerpo,Destinatario,Estado) values(getdate(),'Reporte estado de salud flota',concat((select cuerpo from ReporteSaludTXMail),"+Cuerpo2+"),'jrojass@salfa.cl',0) "
//                          +" insert into TxMail(FechaInsercion,Asunto,Cuerpo,Destinatario,Estado) values(getdate(),'Reporte estado de salud flota',concat((select cuerpo from ReporteSaludTXMail),"+Cuerpo2+"),'ldiaz@salfa.cl',0) "
//                          +" insert into TxMail(FechaInsercion,Asunto,Cuerpo,Destinatario,Estado) values(getdate(),'Reporte estado de salud flota',concat((select cuerpo from ReporteSaludTXMail),"+Cuerpo2+"),'gvalenzuela@micrologica.cl',0) "; 
//                    st2.executeUpdate(SQuery2); 
                    
                    st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    SQuery2="delete from SemanaAnterior where CodUsuario="+CodU+""; 
                    st.executeUpdate(SQuery2);                     
                    System.out.println("Borro Usuario"+CodU);
                    st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                    String SQuery="insert into SemanaAnterior values("+CodU+","+KM+","+Rendimiento.replace(',', '.')+","+Litros+","+Ralenti+","+Horas+")"; 
                    System.out.println(SQuery);
                    st.executeUpdate(SQuery); 
                    System.out.println("Inserto Usuario"+CodU);
                    nombreArc="";
                    Cx=""; 

                    
                    }else{
                     SQuery2="insert into TxMail(FechaInsercion,Asunto,Cuerpo,Destinatario,Estado) values(getdate(),'Error Reporte estado de salud flota ','El Correo,"+Correo+" de la empresa "+NomEmp+" no se encuentra en los reporte automaticos, favor de ingresarlo','gvalenzuela@micrologica.cl',0) " ;
                     Log.log(SQuery2);
                    System.out.println(SQuery2);
                    st2.executeUpdate(SQuery2);   
                             }
                    
                    
                    
                    
                    }
                    
                    
                    
                    
                    }
                    cont=0;
                    }    
                 }
                    else{
                        Flag=false;  
                        System.out.println("No Entro: ERROR ");
                    }
                        
                }catch(Exception e){
                    e.printStackTrace();
                }
             
            }            
        }catch(Exception e)
        {
         e.printStackTrace();   
        }        
    }
    
}
