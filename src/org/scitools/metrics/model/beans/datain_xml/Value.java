/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scitools.metrics.model.beans.datain_xml;

/**
 *
 * @author fernando
 */
public class Value {
    private String name;
    private String source;
    private String packagi;
    private String value;
    private String aux;
    
    public Value() {
        name="";
        source="";
        packagi="";
        value="";
    }

    public String getAux() {
        return aux;
    }

    public void setAux(String aux) {
        this.aux = aux;
    }

    public Value(String name, String source, String packagi, String value) {
        this.name = name;
        this.source = source;
        this.packagi = packagi;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getSource() {
        return source;
    }

    public String getPackagi() {
        return packagi;
    }

    public String getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setPackagi(String packagi) {
        this.packagi = packagi;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
