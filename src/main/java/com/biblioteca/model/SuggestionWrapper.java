package com.biblioteca.model;


import java.util.List;

/**
 * Created by jonaspfeifer on 07.05.17.
 */
public class SuggestionWrapper {

  List<DocenteModel> suggestions;

  public List<DocenteModel> getSuggestions() {
    return suggestions;
  }

  public void setSuggestions(List<DocenteModel> suggestions) {
    this.suggestions = suggestions;
  }
}
