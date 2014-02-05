package pathmaker;
import java.util.*;
import java.io.*;

public class PathMaker 
{
    public static void main(String[] args) throws IOException
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Insert the path: ");
        String dir=sc.nextLine();
        try
        {
            BufferedReader br=new BufferedReader(new FileReader(dir+File.separator+"config.cfg"));
            int nPrec=0;
            String lettore;
            while((lettore=br.readLine())!=null)
            {
                String[] tab=lettore.split("\t");
                int x=tab.length;
                if(x>nPrec)
                    dir=makeDir(dir,tab[x-1]);
                if(x==nPrec)
                    dir=makeDir(backToTop(dir),tab[x-1]);
                if(x<nPrec)
                {
                    for (int i=0;i<nPrec-x+1;i++)
                        dir=backToTop(dir);
                    dir=makeDir(dir,tab[x-1]);
                }
                nPrec=x;
            }
            br.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found: config.cfg");
        }
    }
    public static String backToTop(String s)
    {
        String[] x=s.split(isWin());
        s="";
        for(int i=0;i<x.length-1;i++)
            s+=x[i]+File.separator;
        return s;
    }
    public static String makeDir(String s,String l)
    {
        File f1=new File(s+File.separator+l);
        f1.mkdirs();
        return s+File.separator+l;
    }
    public static String isWin()
    {
        return System.getProperty("os.name").contains("Windows")?"\\\\":"/";
    }
}