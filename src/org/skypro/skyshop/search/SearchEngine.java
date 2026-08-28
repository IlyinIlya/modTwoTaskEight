package org.skypro.skyshop.search;

import org.skypro.skyshop.exceptions.BestResultNotFound;

import java.util.*;

public class SearchEngine {
    private final Set<Searchable> searchables;

    public SearchEngine(int iSize) {
        searchables = new HashSet<>();
    }

    public void add(Searchable searchable) {
        searchables.add(searchable);
    }

    public Set<Searchable> search(String searchTerm) {
        Comparator<Searchable> iComp = (o1, o2) -> {
            int iRes = Integer.compare(o2.getName().length(), o1.getName().length());
            if (iRes == 0) {
                iRes = o1.getName().compareTo(o2.getName());
            }
            return iRes;
        };

        Set<Searchable> elements = new TreeSet<>(iComp);
        for (Searchable searchable : searchables) {
            if (searchable != null && searchable.getSearchTerm().toLowerCase().contains(searchTerm.toLowerCase())) {
                elements.add(searchable);
            }
        }
        return elements;
    }

    public Searchable getMostEqualElement(String search) throws BestResultNotFound {
        Searchable mostEqualElement = null;
        int iMaxCount = 0;

        for (Searchable searchable : searchables) {
            if (searchable == null) {
                throw new BestResultNotFound(search);
            }

            String iSearch = searchable.getSearchTerm();

            int iCount = 0;
            int index = 0;
            int indexSubstring = iSearch.indexOf(search, index);

            while (indexSubstring != -1) {
                iCount++;
                index = indexSubstring + search.length();
                indexSubstring = iSearch.indexOf(search, index);
            }

            if (iCount > iMaxCount) {
                iMaxCount = iCount;
                mostEqualElement = searchable;
            }
        }
        if (mostEqualElement == null) {
            throw new BestResultNotFound(search);
        }
        return mostEqualElement;
    }

}
