package com.wind.exception;

public class RollBackException extends Exception{
    private static final long serialVersionUID = 1L;
    
    static String message = "事务异常，回滚";
    
    public RollBackException(String message) {
        super(message);
    }
    
    public RollBackException() {
        super(message);
    }
    public static void main(String[] args) {
        RollBackException e = new RollBackException("操作异常，事务回滚");
        RollBackException e1 = new RollBackException();
        System.out.println(e);
        System.out.println(e1);
    }
}    