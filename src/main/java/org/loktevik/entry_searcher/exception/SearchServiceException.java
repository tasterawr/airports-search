package org.loktevik.entry_searcher.exception;

public class SearchServiceException extends Exception {
    public SearchServiceException(){
        super();
    }

    public SearchServiceException(String message, Exception cause){
        super(message, cause);
    }
}
