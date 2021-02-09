import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;


public class TestObject implements Serializable {

    private static final long serialVersionUID = 2880696969847438999L;

    public TestObject() {
    }

    public TestObject(String a, String b, String c, String d, String e, String f, LocalDate d1, LocalDate d2, LocalDate d3, BigDecimal b1, BigDecimal b2, int i) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.d1 = d1;
        this.d2 = d2;
        this.d3 = d3;
        this.b1 = b1;
        this.b2 = b2;
        this.i = i;
    }

    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private LocalDate d1;
    private LocalDate d2;
    private LocalDate d3;
    private BigDecimal b1;
    private BigDecimal b2;
    private int i;

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

    public LocalDate getD1() {
        return d1;
    }

    public void setD1(LocalDate d1) {
        this.d1 = d1;
    }

    public LocalDate getD2() {
        return d2;
    }

    public void setD2(LocalDate d2) {
        this.d2 = d2;
    }

    public LocalDate getD3() {
        return d3;
    }

    public void setD3(LocalDate d3) {
        this.d3 = d3;
    }

    public BigDecimal getB1() {
        return b1;
    }

    public void setB1(BigDecimal b1) {
        this.b1 = b1;
    }

    public BigDecimal getB2() {
        return b2;
    }

    public void setB2(BigDecimal b2) {
        this.b2 = b2;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestObject that = (TestObject) o;
        return i == that.i &&
                Objects.equals(a, that.a) &&
                Objects.equals(b, that.b) &&
                Objects.equals(c, that.c) &&
                Objects.equals(d, that.d) &&
                Objects.equals(e, that.e) &&
                Objects.equals(f, that.f) &&
                Objects.equals(d1, that.d1) &&
                Objects.equals(d2, that.d2) &&
                Objects.equals(d3, that.d3) &&
                Objects.equals(b1, that.b1) &&
                Objects.equals(b2, that.b2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c, d, e, f, d1, d2, d3, b1, b2, i);
    }
}
