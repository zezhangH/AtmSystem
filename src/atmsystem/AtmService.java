package atmsystem;

import java.util.Scanner;

public class AtmService {
    private AtmDao dao = new AtmDao();

    public String login(String name, String password) {
//        try {
//            FileReader fileReader = new FileReader("src\\atmsystem\\User.txt");
//            BufferedReader br = new BufferedReader(fileReader);
//            String value = br.readLine();
//            while (value != null) {
//                String[] user = value.split("-");
//                if (user[0].equals(name)) {
//                    if (user[1].equals(password)) {
//                        return "登录成功";
//                    }
//                }
//                value = br.readLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        User user = dao.selectOne(name);
        if (user != null && user.getPassword().equals(password)) {
            return "登录成功";
        }
        return "帐号或密码错误";
    }

    public Float queryBalance(String name) {
        User user = dao.selectOne(name);
        Float ablance = user.getAblance();
        return ablance;
    }

    //存款
    public void deposit(String name, Float depositMoney) {
        User user = dao.selectOne(name);
        user.setAblance(user.getAblance() + depositMoney);
        dao.update(user);
//        d.commit();
    }

    //取款
    public void withdrawal(String name, Float withdrawalMoney) {
        User user = dao.selectOne(name);
        if (user.getAblance() > withdrawalMoney) {
            user.setAblance(user.getAblance() - withdrawalMoney);
            dao.update(user);
//            this.commit();
        } else {
            System.out.println("对不起，" + name + "您的账户余额不足");
        }
    }

    //转账
    public void transfer(String outName, String inName, Float transferMoney) {
        User outUser = dao.selectOne(outName);
        if (outUser.getAblance() > transferMoney) {
            User inUser = dao.selectOne(inName);
            if (inUser != null) {
                outUser.setAblance(outUser.getAblance() - transferMoney);
                inUser.setAblance(inUser.getAblance() + transferMoney);
                dao.update(outUser);
                dao.update(inUser);
//                this.commit();
            } else {
                System.out.println("您输入的转入帐号不正确");
            }
        } else {
            System.out.println("对不起，" + outName + "您的账户余额不足");
        }
    }

    //设计方法将集合的所有内容推到文件中
    public static void main(String[] args) {
        AtmService service = new AtmService();
        Scanner input = new Scanner(System.in);
        System.out.println("欢迎使用银行自助服务系统\n请输入帐号");
        String name = input.nextLine();
        System.out.println("请输入密码");
        String password = input.nextLine();
        String result = service.login(name, password);
        if (result.equals("登录成功")) {
            System.out.println("恭喜您登录成功，请您输入操作项");
            System.out.println("查询请输入1\n存款请输入2\n取款请输入3\n转账请输入4\n退出请输入5\n如需帮助请拨打1234566770");
            String option = input.nextLine();
            switch (option) {
                case "1":
                    Float balance = service.queryBalance(name);
                    System.out.println("尊敬的用户你的可用余额为" + balance);
                    break;
                case "2":
                case "3":
                case "4":
                case "5":
            }
        } else {
            System.out.println("对不起，" + result);
        }
    }
}
