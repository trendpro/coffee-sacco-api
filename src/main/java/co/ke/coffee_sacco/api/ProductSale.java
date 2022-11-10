package co.ke.coffee_sacco.api;

import javax.persistence.*;

@Entity(name = "ProductSale")
@Table(name = "product_sale")
public class ProductSale {
    @Id
    @SequenceGenerator(
            name = "product_sale_sequence",
            sequenceName = "product_sale_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sale_sequence"
    )
    @Column(updatable = false, nullable = false)
    private Long id;
    private double sellingPricePerKg = 0;
    private double quantity = 0.0;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    public ProductSale(double sellingPricePerKg, double quantity, Branch branch) {
        this.sellingPricePerKg = sellingPricePerKg;
        this.quantity = quantity;
        this.branch = branch;
    }

    public ProductSale() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getSellingPricePerKg() {
        return sellingPricePerKg;
    }

    public void setSellingPricePerKg(double sellingPricePerKg) {
        this.sellingPricePerKg = sellingPricePerKg;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }


    @Override
    public String toString() {
        return "ProductSale{" +
                "id=" + id +
                ", sellingPricePerKg=" + sellingPricePerKg +
                ", quantity=" + quantity +
                ", branch=" + branch +
                '}';
    }
}
