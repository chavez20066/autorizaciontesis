package com.biblioteca.model;

public class DocenteModel {
	String value;
	String data;

	public DocenteModel(String name) {
		this.value = name;
		this.data = "";
	}

	public DocenteModel() {
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
