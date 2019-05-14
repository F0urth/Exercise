package ReadData.ProcessContainers;

public
    class Customer {

    private Integer id;
    private String name;
    private String surname;
    private Integer age;



    public static Customer newInstance() {
        return new Customer();
    }

    private Customer() {

    }
}
