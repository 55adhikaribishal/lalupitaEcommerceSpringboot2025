package com.example.SpringbootEcommerce2025.dto;

import com.example.SpringbootEcommerce2025.entity.Product;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

public class FAQDto {
    private Long id;
    private String question;
    private String answer;

    private Long productId;

    public FAQDto() {
    }

    public FAQDto(Long id, String question, String answer, Long productId) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.productId = productId;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
