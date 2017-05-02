/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scitools.metrics.control;

import java.util.ArrayList;
import org.scitools.metrics.model.beans.IdentifyNamesNewPattern;
import org.scitools.metrics.model.bisness.GeneratePercentisPerMetric;
import org.scitools.metrics.model.bisness.ReadCSV;
import org.scitools.metrics.model.util.ManipulaDir;
import org.scitools.metrics.model.util.QuicSortString;
import org.scitools.metrics.model.util.RWArquivoNew;

/**
 *
 * @author fernando
 */
public class CGAllMetricsPercentiles {

    public String getDefinedMetricsOfNewPattern(String metric) {
        
    	String saida = "";
        saida = IdentifyNamesNewPattern.getMnemonicOfMetric(metric);
        return saida;
    }

    public ArrayList<String> getMetricasEmArquivos(String path, ArrayList<String> nomesArquivos, boolean newPattern) {
        ArrayList<String> todas = new ArrayList<String>();
        //ter todas os nomes de métricas dos arquivos
        ReadCSV rcsv = new ReadCSV();
        for (String aux : nomesArquivos) {
            rcsv.setUrlArquivo(path + "//" + aux);
            if (!newPattern) {
                todas.addAll(rcsv.getNomesMetricas(ReadCSV.SEPARADOR_VIGULA));
            } else {//padrão dos 103 sistemas
                todas.addAll(getNamesFilter("entityName,superEntityName", getNamesFilter("inheritedMethods", rcsv.getNomesMetricas(ReadCSV.SEPARADOR_PONTOVIGULA))));
            }
        }
        //ordena métricas
        QuicSortString.quick_sort(todas, 0, todas.size() - 1, QuicSortString.TYPE_STRING);
        //agrupa métricas
        ArrayList<String> auxTodas = new ArrayList<String>();
        String nomeAux = "";
        for (String nome : todas) {
            if (!nomeAux.equalsIgnoreCase(nome)) {
                auxTodas.add(nome);
            }
            nomeAux = nome;
        }
        todas = auxTodas;
        return todas;
    }

    public ArrayList<String> getNamesFilter(String subStringOfEliminate, ArrayList<String> listFilesNames) {
       // System.out.println("Lista de desprezados");
        ArrayList<String> saida = new ArrayList<String>();
        for (String x : listFilesNames) {
            if (!x.contains(subStringOfEliminate)) {
                saida.add(x);
            } else {
              //  System.out.println(x);
            }
        }
        //this.listFilesNames = saida;
        //System.out.println("num arquivos filtrados:" + saida.size() + ", N total de aruqivos:" + listFilesNames.size());
        return saida;
    }

    public void geraPercentis(String metrica, String pathOrigem, String pathDestino, ArrayList<String> nomesArquivos, boolean newPattern) {
        GeneratePercentisPerMetric gppm = new GeneratePercentisPerMetric();
        RWArquivoNew f = new RWArquivoNew();
        ManipulaDir md = new ManipulaDir();
        md.criaDiretorio(pathDestino, "//data-percentiles");
        if (!newPattern) {
            f.setUrl(pathDestino + "//data-percentiles" + "//metric_" + metrica + ".csv");
            try {
                f.criaArq();
                f.escreveLinha(gppm.getTabelaPercentil(pathOrigem, nomesArquivos, metrica));
                f.fechaArq();
            } catch (Exception x) {
                x.printStackTrace();
            }
        } else {// se for do novo padrão
            f.setUrl(pathDestino + "//data-percentiles" + "//metric_" + getDefinedMetricsOfNewPattern(metrica) + ".csv");
            try {
                f.criaArq();
                f.escreveLinha(gppm.getTabelaPercentilNewPatter(pathOrigem, nomesArquivos, metrica));
                f.fechaArq();
            } catch (Exception x) {
                x.printStackTrace();
            }
        }
    }
}
