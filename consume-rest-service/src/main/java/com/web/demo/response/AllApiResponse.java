package com.web.demo.response;

import java.util.List;

public class AllApiResponse {
    List<Photos> photosList;
    List<Posts> postsList;
    List<Product> productList;
    List<Book> bookList;
    List<Authors> authorsList;
    List<Comments> commentsList;
    List<Todos> todosList;

    public List<Photos> getPhotosList() {
        return photosList;
    }

    public void setPhotosList(List<Photos> photosList) {
        this.photosList = photosList;
    }

    public List<Posts> getPostsList() {
        return postsList;
    }

    public void setPostsList(List<Posts> postsList) {
        this.postsList = postsList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<Authors> getAuthorsList() {
        return authorsList;
    }

    public void setAuthorsList(List<Authors> authorsList) {
        this.authorsList = authorsList;
    }

    public List<Comments> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }

    public List<Todos> getTodosList() {
        return todosList;
    }

    public void setTodosList(List<Todos> todosList) {
        this.todosList = todosList;
    }
}
