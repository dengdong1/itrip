package beans.vo.hotel;

import org.apache.solr.client.solrj.beans.Field;

public class Hotel {

    @Field("id")
    private Long id;

    @Field("address")
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
