/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scitools.metrics.model.bisness;



import java.util.ArrayList;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.scitools.metrics.model.beans.Percentil;

/**
 *
 * @author fernando
 */
public class GeneratePercentisPerMetricNewPattern {

    /*
     * Gera percentis
     */
    public StringBuffer getTabelaPercentil(String path, ArrayList<String> nomesArquivos, String nomeMetrica) {
        //o nomeMetrica não pode ser mnemônico.
        //System.out.println(nomeMetrica);
        ReadCSV rcsv = new ReadCSV();
        String nomeProjeto = "";
        ArrayList<Percentil> percentis = new ArrayList<Percentil>();// lista de percentils indicando um arquivo de saida(uma métrica)
        Percentil p = null;
        for (String aux : nomesArquivos) {//arquivo por arquivo de todos projetos
            rcsv.setUrlArquivo(path + "//" + aux);
            ArrayList<Float> valores = rcsv.getValueColunaNewPattern(nomeMetrica, ReadCSV.SEPARADOR_PONTOVIGULA);
            if (!valores.isEmpty()) { // se não achou a métrica no arquivo
                DescriptiveStatistics ds = new DescriptiveStatistics();
                for (float v : valores) {
                    try {
                        ds.addValue(v);
                    } catch (NumberFormatException x) {
                       // System.err.println("Formato diferente de número: " + v);
                    } catch (Exception x) {
                        //x.printStackTrace();
                    }
                }
                float[] agrupadoPercentis = new float[20];                      //vetor que armazena os percentisn do projeto
                for (int i = 5, j = 0; i <= 100; j++, i += 5) {
                    agrupadoPercentis[j] = (float) ds.getPercentile(i);
                }

                p = new Percentil();
                nomeProjeto = aux.substring(0, aux.length() - 4);               //nome do projeto sem o mnemonico final _mtt
                p.setNomeProjeto(nomeProjeto);
                p.setPercentis(agrupadoPercentis);
                percentis.add(p);
                //System.out.print(":");
            } else {
                //System.out.print(".");
            }

        }
        //gera a string de saida
        StringBuffer saida = new StringBuffer();
        saida.append("Projetos,5%,10%,15%,20%,25%,30%,35%,40%,45%,50%,55%,60%,65%,70%,75%,80%,85%,90%,95%,100%;\n");
        for (Percentil x : percentis) {
            saida.append(x.getNomeProjeto());
            float[] auxP = x.getPercentis();
            for (int i = 0; i < auxP.length; i++) {
                saida.append("," + auxP[i]);
            }
            saida.append("\n");
        }

        //System.out.println(saida);
        return saida;
    }
}
