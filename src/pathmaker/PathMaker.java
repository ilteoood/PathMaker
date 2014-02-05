/*

PathMaker

Copyright (c) 2013, Matteo Pietro Dazzi <---> ilteoood
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided
that the following conditions are met:

* Redistributions of source code must retain the above copyright notice, this list of conditions and the
  following disclaimer.

* Redistributions in binary form must reproduce the above copyright notice, this list of conditions and
  the following disclaimer in the documentation and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF
THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

========================================================================================================================

*/

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