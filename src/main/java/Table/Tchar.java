package Table;

public class Tchar {
    private int id;
    private String name;
    private int hp;
    private String job;


    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id; //정보를 받고 get 으로 출력?
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
