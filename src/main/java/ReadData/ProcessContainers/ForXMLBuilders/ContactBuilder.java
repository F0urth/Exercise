package ReadData.ProcessContainers.ForXMLBuilders;

import ReadData.ProcessContainers.Contact;


/**
 * Situational Creator for class
 * @see ReadData.ProcessContainers.Contact
 * @author F0urth
 */
public
    class ContactBuilder {

    private Integer id;
    private Integer id_customer;
    private Integer type;
    private String contact;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setId_customer(Integer id_customer) {
        this.id_customer = id_customer;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @return new of instance Contact
     * @see ReadData.ProcessContainers.Contact
     */
    public Contact buildContact() {
        return Contact
            .newInstance(id, id_customer, type, contact);
    }
}
