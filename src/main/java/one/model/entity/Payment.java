package one.model.entity;


import java.time.LocalDate;

public class Payment {
    private int id;
    private LocalDate date;
    private int amount;
    private int price;
    private int clientId;
    private int utilityId;

    public Payment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getUtilityId() {
        return utilityId;
    }

    public void setUtilityId(int utilityId) {
        this.utilityId = utilityId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public static class Builder{
        Payment payment = new Payment();

        public Builder id(int id){
            payment.id = id;
            return this;
        }
        public Builder date(LocalDate date){
            payment.date = date;
            return this;
        }
        public Builder amount(int amount){
            payment.amount = amount;
            return this;
        }
        public Builder price(int price){
            payment.price = price;
            return this;
        }
        public Builder clientId(int clientId){
            payment.clientId = clientId;
            return this;
        }
        public Builder utilityId(int utilityId){
            payment.utilityId = utilityId;
            return this;
        }
        public Payment build(){
            return payment;
        }
    }
}
