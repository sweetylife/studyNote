public class CustomerList {
    private Customer[] customers;
    private int total;// 已经有的数量即指向null

    public CustomerList(int size) {
        customers = new Customer[size];
    }

    public Customer[] getCustomerList() {
        return customers;
    }

    public int getTotal() {
        return total;
    }

    // 添加客户
    public boolean addCustomer(Customer customer) {
        if (total >= customers.length) {
            return false;
        } else {
            customers[total++] = customer;
            return true;
        }
    }

    // 删除客户
    public boolean deleteCustomer(int code) {
        int id = code - 1;
        if (id < 0 || id >= total) {
            return false;
        } else {
            for (int i = id; i < total - 1; i++) {
                customers[i] = customers[i + 1];
            }
            customers[total - 1] = null;
            total--;
            return true;
        }
    }
    public boolean updateCustomer(int code,String name,char sex,int age,String tel,String email){
        Customer item= findCustomer(code);
        item.setName(name);
        item.setSex(sex);
        item.setAge(age);
        item.setTel(tel);
        item.setEmail(email);
        return true;
    }
    // 查看客户
    public Customer[] checkCustomer() {
        Customer[] checkList = new Customer[total];
        for (int i = 0; i < total; i++) {
            checkList[i] = customers[i];
        }
        return checkList;
    }

    // 查询某一个客户
    public Customer findCustomer(int code) {
        int id = code - 1;
        if (id < 0 || id >= customers.length) {
            return null;
        } else {
            return customers[id];
        }
    }
}
