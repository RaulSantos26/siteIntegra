package br.com.bb.siteIntegra.services.exceptions;

public class ResourcesNotFoudException extends RuntimeException {

    public ResourcesNotFoudException(String msg){
        super(msg);
    }
}
