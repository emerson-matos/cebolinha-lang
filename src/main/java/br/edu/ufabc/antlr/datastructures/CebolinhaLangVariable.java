package br.edu.ufabc.antlr.datastructures;

public class CebolinhaLangVariable extends CebolinhaLangSymbol {

    public static final int NUMBER = 0;
    public static final int TEXT = 1;

    private int type;
    private String value;
    private Boolean isUsed;

    public CebolinhaLangVariable(String name, int type, String value) {
        super(name);
        this.type = type;
        this.value = value;
        this.isUsed = false;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setUsed() {
        this.isUsed = true;
    }

    public Boolean isUsed() {
        return isUsed;
    }

    @Override
    public String generetaCode() {
        String str = "";
        if (type == NUMBER) {
            str = "Double ";
        } else if (type == TEXT) {
            str = "String ";
        }
        return str + super.name + " = " + value + ";\n";
    }

    @Override
    public String toString() {
        return "CebolinhaLangValiable{" +
                "name=" + name +
                ", type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
