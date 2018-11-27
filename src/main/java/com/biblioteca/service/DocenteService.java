package com.biblioteca.service;

import java.util.Map;

public interface DocenteService {
	public abstract Map<String, String> listDocentes(String searchDocente);
	public abstract Map<String, String> lisAllDocentes();
}
