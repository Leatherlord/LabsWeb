package msg.ejb;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "points")
public class PointsEntity {
    private double x;
    private double y;
    private double r;
    private boolean result;
    private Timestamp date;
    private int id;

    @Basic
    @Column(name = "x", nullable = false, precision = 0)
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @Basic
    @Column(name = "y", nullable = false, precision = 0)
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Basic
    @Column(name = "r", nullable = false, precision = 0)
    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    @Basic
    @Column(name = "result", nullable = false)
    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointsEntity that = (PointsEntity) o;
        return Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0 && Double.compare(that.r, r) == 0 && result == that.result && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, r, result, date);
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, precision = 0)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
