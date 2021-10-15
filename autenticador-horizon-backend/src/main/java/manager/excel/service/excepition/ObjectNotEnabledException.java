package manager.excel.service.excepition;

import java.io.Serializable;

public class ObjectNotEnabledException extends  RuntimeException implements Serializable {
    public ObjectNotEnabledException(String message){super(message);}
}
