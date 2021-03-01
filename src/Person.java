import java.sql.Date;

/**
 *  ORM编程思想
 *      一个数据表对应一个Java类
 *      表中的一条记录对应Java类的一个属性
 *      表中的一个字段对应Java类的一个属性
 */

public class Person {
    private int ID;
    private String NAME;
    private String EMAIL;
    private Date BIRTH;

    public Person() {
    }

    public Person(int ID, String NAME, String EMAIL, Date BIRTH) {
        this.ID = ID;
        this.NAME = NAME;
        this.EMAIL = EMAIL;
        this.BIRTH = BIRTH;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public Date getBIRTH() {
        return BIRTH;
    }

    public void setBIRTH(Date BIRTH) {
        this.BIRTH = BIRTH;
    }

    @Override
    public String toString() {
        return "Person{" +
                "ID=" + ID +
                ", NAME='" + NAME + '\'' +
                ", EMAIL='" + EMAIL + '\'' +
                ", BIRTH=" + BIRTH +
                '}';
    }
}

