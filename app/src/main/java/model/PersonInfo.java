package model;

import java.io.Serializable;

/**
 * Created by tao on 2015/12/15.
 */
public class PersonInfo implements Serializable {
    private String nickName;
    private String age;
    private String sex;
    private String level;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
