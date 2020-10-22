package com.zaumal.download.xmla.bean.albuminfo;

public class Crumbs {

	private int categoryId;
	private String categoryPinyin;
	private String categoryTitle;
	private int subcategoryId;
	private String subcategoryName;
	private String subcategoryDisplayName;
	private String subcategoryCode;

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryPinyin(String categoryPinyin) {
		this.categoryPinyin = categoryPinyin;
	}

	public String getCategoryPinyin() {
		return categoryPinyin;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setSubcategoryId(int subcategoryId) {
		this.subcategoryId = subcategoryId;
	}

	public int getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}

	public String getSubcategoryName() {
		return subcategoryName;
	}

	public void setSubcategoryDisplayName(String subcategoryDisplayName) {
		this.subcategoryDisplayName = subcategoryDisplayName;
	}

	public String getSubcategoryDisplayName() {
		return subcategoryDisplayName;
	}

	public void setSubcategoryCode(String subcategoryCode) {
		this.subcategoryCode = subcategoryCode;
	}

	public String getSubcategoryCode() {
		return subcategoryCode;
	}

}