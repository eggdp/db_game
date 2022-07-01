import Table.Enemy;
import Table.Item;
import Table.Tchar;
import dao.EnemyDao;
import dao.ItemDao;
import dao.T_charDao;

import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        System.out.println("게임을 시작합니다.");
        for(;;) {
            System.out.print("1. 게임 설정 2.게임 시작 3. 저장된 정보 보기.");
            Scanner nc = new Scanner(System.in);
            
            ItemDao db = new ItemDao(); //아이템 db
            T_charDao cb = new T_charDao(); // 캐릭터 db
            EnemyDao eb = new EnemyDao(); //몬스터 db

            int num = nc.nextInt();
            if (num == 1) {

                Scanner sc = new Scanner(System.in); //입력
                //캐릭터 정보ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
                System.out.println("캐릭터 정보 설정");
                System.out.print("캐릭터 이름:");
                String name = sc.next();
                System.out.print("캐릭터 체력:");
                int hp = sc.nextInt();
                sc.nextLine();
                System.out.print("캐릭터 직업");
                String job = sc.next();
                Tchar tchar = new Tchar();
                tchar.setName(name);
                tchar.setHp(hp);
                tchar.setJob(job);

                cb.insertT_char(tchar);
                cb.selectChar();

                //몬스터 정보ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
                System.out.print("몬스터 정보 입력.");
                System.out.print("몬스터 이름:");
                String name2 = sc.next();
                System.out.print("몬스터 체력:");
                int hp1 = sc.nextInt();
                sc.nextLine();
                Enemy enemy = new Enemy();
                enemy.setName(name2);
                enemy.setHp(hp1);

                eb.insertEnemy(enemy);
                eb.selectEnemy();

                //아이템 정보ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
                System.out.println("아이템 정보 설정.");
                System.out.print("아이템 이름:");
                String name1 = sc.next();
                System.out.print("아이템 속성:");
                String att = sc.next();
                System.out.print("아이템 데미지:");
                int dem = sc.nextInt();
                sc.nextLine();
                System.out.print("아이템 효과:");
                String hyo = sc.next();
                Item item = new Item();
                item.setName(name1);
                item.setAtt(att);
                item.setDem(dem);
                item.setHyo(hyo);

                db.insertItem(item);
                db.selectItem();
            } else if (num == 2) {
                if (eb.CheckEnemy() && db.CheckItem() && cb.CheckT_char()) {
                    System.out.println("게임을 시작합니다.");
                } else {
                    System.out.println("게임 설정을 해야 시작가능합니다.");
                }
            }
        }
    }
    //게임시작
    // 1.게임 설정 2.게임 시작 3.저장된 정보 보기.

    //1번이면
    //캐릭터 정보 설정
    //캐릭터 정보가 db에 저장.
    //몬스터 정보 입력
    //몬스터 정보 db에 저장
    //아이템 정보 설정
    //아이템 정보 db에 저장.

    //2번이면
    //시작 db에 저장된 정보가있으면 게임 시작(시나리오로 진행)
    //db에 저장된 정보가 없으면 게임설정으로 가라고 안내 메시지.

    //3번이면
    //1.캐릭터 정보보기 2.몬스터 정보보기 3. 아이템 정보보기
    //각 db에 저장되있는 정보 출력력


}
