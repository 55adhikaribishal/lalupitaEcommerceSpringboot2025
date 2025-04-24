package com.example.SpringbootEcommerce2025.entity;

import com.example.SpringbootEcommerce2025.dto.FAQDto;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class FAQ {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String question;
    private String answer;
    @ManyToOne(fetch=FetchType.LAZY,optional=false)
    @JoinColumn(name="product_Id",nullable=false)
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Product product;

    public FAQ() {
    }

    public FAQ(Long id, String question, String answer, Product product) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.product = product;
    }

    public FAQDto getFAQDto() {
        FAQDto faqDto=new FAQDto();
        faqDto.setId(id);
        faqDto.setQuestion(question);
        faqDto.setAnswer(answer);
        faqDto.setProductId(product.getId());

        return faqDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
