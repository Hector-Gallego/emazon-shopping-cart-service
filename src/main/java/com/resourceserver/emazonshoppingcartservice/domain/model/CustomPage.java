package com.resourceserver.emazonshoppingcartservice.domain.model;


import java.util.List;
import java.util.Objects;

public class CustomPage<T> {

    private List<T> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private Boolean first;
    private Boolean last;

    public CustomPage() {
    }

    private CustomPage(Builder<T> builder) {
        this.content = builder.content;
        this.pageNumber = builder.pageNumber;
        this.pageSize = builder.pageSize;
        this.totalElements = builder.totalElements;
        this.totalPages = builder.totalPages;
        this.first = builder.first;
        this.last = builder.last;
    }


    public List<T> getContent() {
        return content;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Boolean isFirst() {
        return first;
    }

    public Boolean isLast() {
        return last;
    }

    public static class Builder<T> {
        private List<T> content;
        private Integer pageNumber;
        private Integer pageSize;
        private Long totalElements;
        private Integer totalPages;
        private Boolean first;
        private Boolean last;

        public Builder<T> content(List<T> content) {
            this.content = content;
            return this;
        }

        public Builder<T> pageNumber(Integer pageNumber) {
            this.pageNumber = pageNumber;
            return this;
        }

        public Builder<T> pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder<T> totalElements(Long totalElements) {
            this.totalElements = totalElements;
            return this;
        }

        public Builder<T> totalPages(Integer totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public Builder<T> first(Boolean first) {
            this.first = first;
            return this;
        }

        public Builder<T> last(Boolean last) {
            this.last = last;
            return this;
        }

        public CustomPage<T> build() {
            return new CustomPage<>(this);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomPage<?> that = (CustomPage<?>) o;
        return Objects.equals(content, that.content) && Objects.equals(pageNumber, that.pageNumber) && Objects.equals(pageSize, that.pageSize) && Objects.equals(totalElements, that.totalElements) && Objects.equals(totalPages, that.totalPages) && Objects.equals(first, that.first) && Objects.equals(last, that.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, pageNumber, pageSize, totalElements, totalPages, first, last);
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }
}