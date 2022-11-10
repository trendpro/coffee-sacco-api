package co.ke.coffee_sacco.api;

import javax.persistence.*;
//import java.time.LocalDate;
import java.util.Date;

@Entity(name = "ProductDelivery")
@Table(name = "product_delivery")
public class ProductDelivery {
    @Id
    @SequenceGenerator(
            name = "product_delivery_sequence",
            sequenceName = "product_delivery_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_delivery_sequence"
    )
    @Column(updatable = false, nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
    private Date deliveryDate;
    private double quantity;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }


    public ProductDelivery(double quantity, Member member) {
        this.branch = member.getBranch();
        this.deliveryDate = new Date();
        this.quantity = quantity;
        this.member = member;
    }

    public ProductDelivery() {
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductDelivery{" +
                "id=" + id +
                ", branch=" + branch +
                ", deliveryDate=" + deliveryDate +
                ", quantity=" + quantity +
                '}';
    }
}
