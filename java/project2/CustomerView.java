public class CustomerView {
    // 属性显式赋值
    private CustomerList customerList = new CustomerList(2);

    public static void main(String[] args) {
        CustomerView customerView = new CustomerView();
        customerView.showMenu();
    }

    // 显示菜单
    public void showMenu() {
        for (;;) {
            System.out.println("-----------------客户信息管理软件-----------------");
            System.out.println("1 添 加 客 户");
            System.out.println("2 修 改 客 户");
            System.out.println("3 删 除 客 户");
            System.out.println("4 客 户 列 表");
            System.out.println("5 退           出");
            System.out.print("请选择(1-5)：_");
            char choice = CMUtility.readMenuSelection();
            switch (choice) {
                case '1':
                    addCustomer();
                    break;
                case '2':
                    updateCustomer();
                    break;
                case '3':
                    deleteCustomer();
                    break;
                case '4':
                    showList();
                    break;
                case '5':
                    break;
            }
        }
    }

    // 添加客户
    private void addCustomer() {
        System.out.println("--------------------------- 添 加 客 户---------------------------");
        System.out.print("输入姓名：");
        String name = CMUtility.readString(4);
        System.out.print("输入性别：");
        char sex = CMUtility.readChar();
        System.out.print("输入年龄：");
        int age = CMUtility.readInt();
        System.out.print("输入电话：");
        String tel = CMUtility.readString(11);
        System.out.print("输入邮箱：");
        String email = CMUtility.readString(20);
        Customer customer = new Customer(name, sex, age, tel, email);
        boolean isAdd = customerList.addCustomer(customer);
        if (isAdd) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败，库存已满");
        }
    }

    // 删除客户列表
    private void deleteCustomer() {
        System.out.println("---------------------------删除客户---------------------------");
        System.out.print("请选择要删除的编号(-1退出)：");
        for (;;) {
            int code = CMUtility.readInt();
            if (code == -1) {
                return;
            }
            // 查看是否有这个人
            Customer item = customerList.findCustomer(code);
            if (item == null) {
                System.out.print("查无此人，重新选择编号：");
            } else {
                boolean isDelete = customerList.deleteCustomer(code);
                if (isDelete) {
                    System.out.println("删除成功");
                    break;
                }
            }
        }
    }

    // 修改客户
    private void updateCustomer() {
        System.out.println("---------------------------修改客户---------------------------");
        System.out.print("请选择要修改的编号(-1退出)：");
        for (;;) {
            int code = CMUtility.readInt();
            if (code == -1) {
                return;
            }
            // 查看是否有这个人
            Customer item = customerList.findCustomer(code);
            if (item == null) {
                System.out.print("查无此人，重新选择编号：");
            } else {
                System.out.print("修改姓名（"+item.getName()+"）：");
                String name = CMUtility.readString(4,item.getName());
                System.out.print("修改性别（"+item.getSex()+"）：");
                char sex = CMUtility.readChar(item.getSex());
                System.out.print("修改年龄（"+item.getAge()+"）：");
                int age = CMUtility.readInt(item.getAge());
                System.out.print("修改电话（"+item.getTel()+"）：");
                String tel = CMUtility.readString(11,item.getTel());
                System.out.print("修改邮箱（"+item.getEmail()+"）：");
                String email = CMUtility.readString(20,item.getEmail());
                customerList.updateCustomer(code,name,sex,age,tel,email);
                System.out.println("修改成功");
                break;
            }
        }
    }

    // 显示客户列表
    private void showList() {
        System.out.println("---------------------------客户列表---------------------------");
        Customer[] list = customerList.checkCustomer();
        if (list.length == 0) {
            System.out.println("暂无客户");
        } else {
            System.out.println("编号\t姓名\t性别\t年龄\t\t电话\t\t邮箱");
            for (int i = 0; i < list.length; i++) {
                System.out.println(i + 1 + "\t" + list[i].getName() + "\t" + list[i].getSex() + "\t" + list[i].getAge()
                        + "\t\t" + list[i].getTel() + "\t" + list[i].getEmail());
            }
        }
    }
}
