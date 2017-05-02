/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scitools.metrics.model.util;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Fernando
 */
public class ManipulaDir {

    public String criaDiretorio(String url, String nomeDiretorio) {
        String resp = "";
        File dir = new File(url + "/" + nomeDiretorio);
        if (!dir.isDirectory()) {
            if (dir.mkdir()) {
                resp = ("Diretorio " + nomeDiretorio + " criado com sucesso!");
            } else {
                resp = ("Erro ao criar diretorio! " + nomeDiretorio + " ");
            }
        }
        return resp;
    }
    public String criaDiretorio(String url) {
        String resp = "";
        File dir = new File(url );
        if (!dir.isDirectory()) {
            if (dir.mkdir()) {
                resp = ("Diretorio  criado com sucesso!");
            } else {
                resp = ("Erro ao criar diretorio! ");
            }
        }
        return resp;
    }
    public boolean isDiretorio(String urlCompleta) {
        boolean resp = false;
        File dir = new File(urlCompleta);

        if (dir.isDirectory()) {
            resp = true;
        } else {
            resp = false;
        }

        return resp;
    }
    
    public static void listar(File file, int nivel, ArrayList<String> x) {

        // Exibe a identação necessária 

        if (file.isDirectory()) {
            // Exibe o nome do diretório 

            File[] lista = file.listFiles();
            // Faz uma chamada recursiva para exibir os arquivos e subdiretórios 
            for (int i = 0; i < lista.length; i++) {
                listar(lista[i], nivel + 1,x);
            }
        } else {
            // Exibe o nome do arquivo 

        	x.add(file.getName());
        }
    }
}
