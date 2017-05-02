// Class for the final lists of each metric.
package tdt.methods.vale;

import java.util.ArrayList;
import java.util.List;

/* 
 * Universidade Federal De Minas Gerais
 * Departamento da Ci�ncia da Computa��o
 * 
 * Created by:
 * Lucas Furtini Veado
 * furtini@dcc.ufmg.br
 *
 */

public class ValeFinalResult {

	private static List<Integer> resultEntitiesVale = new ArrayList<Integer>();

	public List<Integer> getResultEntitiesVale() {
		return resultEntitiesVale;
	}

	public void setResultEntitiesVale(List<Integer> resultEntities) {
		ValeFinalResult.resultEntitiesVale = resultEntities;
	}
}

