package ReadData.ProcessContainers.ForXMLBuilders;

import ReadData.ProcessContainers.Customer;

import java.util.Optional;

public
    class CustomerBuilder {

    private Integer id;
    private String name;
    private String surname;
    private Integer age = null;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Customer buildCustomer() {
        return Customer
            .newInstance(id, name, surname, age);
    }
}
