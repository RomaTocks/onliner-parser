package com.onliner.tocks.response;

import com.onliner.tocks.response.exception.RequestException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Response
{
    public static ResponseEntity<Map<String,Object>> configureResponse(Page<?> page, String URL) {
        Integer totalPages = page.getTotalPages();
        Integer items = page.getNumberOfElements();
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String,String> links = new HashMap<>();
        links.put("first",URL + "?page=1");
        if(page.hasNext()) {
            links.put("next",URL + "?page=" + (page.nextPageable().getPageNumber()+1));
        }
        if(page.hasPrevious() && (page.getNumber() <= totalPages)) {
            links.put("prev",URL + "?page=" + (page.previousPageable().getPageNumber()+1));
        }
        links.put("last",URL + "?page=" + totalPages);
        if (page.getNumberOfElements() == 0) {
            map.put("error", new RequestException("Данная страница не существует!"));
            map.put("pages", totalPages);
            map.put("items", items);
            map.put("links",links);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        else {
            map.put("products", page.getContent());
            map.put("pages", totalPages);
            map.put("items", items);
            map.put("links",links);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }
}
