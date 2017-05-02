//package org.mathrider.maximaplugin;
package org.scitools.metrics.model.util.externos;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author tk
 */
public class GnuPlotComandos {

    private String url = "C:\\Program Files\\gnuplot\\bin\\pgnuplot.exe";

    public GnuPlotComandos(String urlGnuplot) {
        url = urlGnuplot;
    }

    public void setUrlGnuPlot(String url) {
        this.url = url;
    }

    public String getUrlGnuPlot() {
        return url;
    }

    public void exec(ArrayList<String> comandos) {
        try {
            Process p = Runtime.getRuntime().exec(url);

            OutputStream outputStream = p.getOutputStream(); //process p
            PrintWriter gp = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
            for (String comando : comandos) {
                gp.println(comando);
                gp.flush();

            }
            gp.close();
        } catch (Exception x) {
            System.out.println(x.getMessage());
        }
    }

    public static void main(String[] args) {
    	
        String[] teste = new String[7];

        teste[0] = "reset\n";
        teste[1] = "set term png \n";
        teste[2] = "set output \"/home/fernando/testando.png\"\n";
        teste[3] = "set multiplot\n";
        teste[4] = "plot sin(x)\n";
        teste[5] = "unset multiplot\n";
        teste[6] = "exit";
    }
}//end class.

