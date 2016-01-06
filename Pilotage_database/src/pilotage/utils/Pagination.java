package pilotage.utils;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;

@SuppressWarnings("unchecked")
public class Pagination<T> {
	
	private List<T> results;
	private int pageSize;
	private int page;
	private ScrollableResults scrollableResults;
	private int totalResults = 0;
	private int nrPages;
	private int totalElements;
	private String linksNav;

	/**
	 * Constructeur pour la pagination
	 */
	public Pagination(int page, int pageSize){
		this.page = page;
		this.pageSize = pageSize;
	}

	/**
	 * Realisation de la page. 
	 * 
	 * @param query
	 *            the Hibernate Query
	 * @param page
	 *            the page number (premier page est 1)
	 * @param pageSize
	 *            the number of results to display on the page
	 */
	public List<T> setQueryPagination(Query query) {
		try {
			scrollableResults = query.scroll();
			results = query.setFirstResult((page - 1) * pageSize).setMaxResults(
					pageSize + 1).list();
			totalElements = getTotalResults();
			nrPages = getLastPageNumber();
			linksNav = getLinksNav();
		} catch (HibernateException e) {
			throw e;
		} finally {
			scrollableResults.close();
		}
		return hasNextPage() ? results.subList(0, pageSize) : results;
	}
	
	/**
	 * Realisation de la page. 
	 * 
	 * @param criteria
	 *            the Hibernate criteria
	 * @param page
	 *            the page number (premier page est 1)
	 * @param pageSize
	 *            the number of results to display on the page
	 */
	public List<T> setCriteriaPagination(Criteria criteria) {
		try {
			scrollableResults = criteria.scroll();
			results = criteria.setFirstResult((page - 1) * pageSize).setMaxResults(
					pageSize + 1).list();
			totalElements = getTotalResults();
			if (totalElements == 0) {
				nrPages = 1;
			} else {
				nrPages = getLastPageNumber();
			}
			linksNav = getLinksNav();
		} catch (HibernateException e) {
			throw e;
		} finally {
			if(scrollableResults != null)
				scrollableResults.close();
		}
		return hasNextPage() ? results.subList(0, pageSize) : results;
	}

	public boolean hasNextPage() {
		return results.size() > pageSize;
	}

	public int getLastPageNumber() {
		/*
		 * On utilise la methode Math.ceil() parce que 
		 * on debutte la numerotation des pages a 1
		 */
		double totalRes = new Integer(getTotalResults() + 1).doubleValue();
		return new Double(Math.ceil(totalRes / pageSize)).intValue();
	}

	public int getTotalResults() {
		try {
			getScrollableResults().last();
			totalResults = getScrollableResults().getRowNumber();
		} catch (HibernateException e) {
			throw e;
		}
		return totalResults;
	}

	/**
	 * generation liens pagination
	 * 
	 * @param page
	 *            int
	 * @param nr_pages
	 *            int
	 * @return String
	 */
	public String getLinksNav() {
		StringBuffer links = new StringBuffer("");
		
		links.append((page > 1) ? " <a href='#' onclick=\"changePage(" + (page - 1) + ")\" ></a> " : "");

		links.append((page > 7) ? " <a href='#' onclick=\"changePage(" + 1 + ")\" >" + 1 + "</a> ... " : "");
		links.append((page == 7) ? " <a href='#' onclick=\"changePage(" + 1 + ")\" >" + 1 + "</a> " : "");
		for (int i = (page - 5); i <= (page + 5); i++) {
			if ((page != i) && (i > 0) && (i <= nrPages)) {
				links.append(" <a href='#' onclick=\"changePage(" + i + ")\" >" + i + "</a> ");
			} else if ((i > 0) && (i <= nrPages)) {
				links.append("<span class='pageSelected'>" + i + "</span>");
			}
		}
		links.append((page == (nrPages - 6)) ? " <a href='#' onclick=\"changePage(" + nrPages + ")\" >" + nrPages + "</a> " : "");
		links.append((page < (nrPages - 6)) ? " ... <a href='#' onclick=\"changePage(" + nrPages + ")\" >" + nrPages + "</a> " : "");

		links.append((page < nrPages) ? " <a href='#' onclick=\"changePage(" + (page) + ")\" ></a> " : "");
		linksNav = links.toString();
		return linksNav;
	}

	public ScrollableResults getScrollableResults() {
		return scrollableResults;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public List<T> getResults() {
		return results;
	}
	
	public void setResults(List<T> results) {
		this.results = results;
	}
	
	public void setScrollableResults(ScrollableResults scrollableResults) {
		this.scrollableResults = scrollableResults;
	}
	
	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public int getNrPages() {
		return nrPages;
	}

	public void setNrPages(int nrPages) {
		this.nrPages = nrPages;
		if (nrPages == 0 ) this.nrPages = 1;
	}

	public int getTotalElements() {
		return totalElements + 1;
	}

	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}

	public void setLinksNav(String linksNav) {
		this.linksNav = linksNav;
	}
	
	public int getPageSuivante(){
		return page + 1;
	}
	
	public int getPagePrecedente(){
		return page - 1;
	}
}
