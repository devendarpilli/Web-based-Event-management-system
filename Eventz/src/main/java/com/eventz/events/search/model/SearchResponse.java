package com.eventz.events.search.model;

import net.sf.json.JSONArray;

public class SearchResponse {

	private JSONArray results;
	
	private int page;
	
	private int totalItemsCount;
	
	private int totalPagesCount;
	
	private int pageSize;
	
	private SearchRequest searchRequest;
	
	public JSONArray getResults() {
		return results;
	}

	public void setResults(JSONArray results) {
		this.results = results;
	}

	public int getPage() {
		return page;
	}

	public void setPage(String string) {
		this.page = Integer.parseInt(string);
	}

	public int getTotalItemsCount() {
		return totalItemsCount;
	}

	public void setTotalItemsCount(String string) {
		this.totalItemsCount = Integer.parseInt(string);
	}

	public int getTotalPagesCount() {
		return totalPagesCount;
	}

	public void setTotalPagesCount(int totalPagesCount) {
		this.totalPagesCount = totalPagesCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(String string) {
		this.pageSize = Integer.parseInt(string);
	}

	public SearchRequest getSearchRequest() {
		return searchRequest;
	}

	public void setSearchRequest(SearchRequest searchRequest) {
		this.searchRequest = searchRequest;
	}

}
