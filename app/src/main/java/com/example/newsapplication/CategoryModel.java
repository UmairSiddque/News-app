package com.example.newsapplication;

public class CategoryModel {
    private String category;
    private String categoryUrlImage;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryUrlImage() {
        return categoryUrlImage;
    }

    public void setCategoryUrlImage(String categoryUrlImage) {
        this.categoryUrlImage = categoryUrlImage;
    }

    public CategoryModel(String category, String categoryUrlImage) {
        this.category = category;
        this.categoryUrlImage = categoryUrlImage;
    }
}
