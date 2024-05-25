package upload;

public class Table {
	
	String a;
	String b;
	String c;
	String d;
	String e;
	String f;
	String g;
	String h;
	
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}
	public String getE() {
		return e;
	}
	public void setE(String e) {
		this.e = e;
	}
	public String getF() {
		return f;
	}
	public void setF(String f) {
		this.f = f;
	}
	public String getG() {
		return g;
	}
	public void setG(String g) {
		this.g = g;
	}
	public String getH() {
		return h;
	}
	public void setH(String h) {
		this.h = h;
	}

	public void carregaDados(String[] dados) {
		
		setA(dados[1].toString());
		setB(dados[2].toString());
		setC(dados[3].toString());
		setD(dados[4].toString());
		setE(dados[5].toString());
		setF(dados[6].toString());
		setG(dados[7].toString());
		setH(dados[8].toString());
	
		
	}
	
}
