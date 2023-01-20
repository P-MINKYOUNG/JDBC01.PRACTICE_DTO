package com.greedy.model.dto;

public class CategoryDTO {
	
	private int categoryCode;
	private String categoryName;
	private int refCategoryCode;
	private String refCategoryName;
	
	public CategoryDTO() {}

	public CategoryDTO(int categoryCode, String categoryName, int refCategoryCode , String refCategoryName) {
		super();
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.refCategoryCode = refCategoryCode;
		this.refCategoryName = refCategoryName;
	}
	
	public String getRefCategoryName() {
		return refCategoryName;
	}
	
	public void setRefCategoryName(String refCategoryName) {
		this.refCategoryName = refCategoryName;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getRefCategoryCode() {
		return refCategoryCode;
	}

	public void setRefCategoryCode(int refCategoryCode) {
		this.refCategoryCode = refCategoryCode;
	}

	@Override
	public String toString() {
		return "카테고리 코드 : " + categoryCode + "\n" 
	         + "카테고리 이름 : " + categoryName + "\n" 
	         + "상위 카테고리 코드 : " + refCategoryCode + "\n" 
	         + "상위 카테고리 이름 : " + refCategoryName + "\n";
	};
	
	
}
