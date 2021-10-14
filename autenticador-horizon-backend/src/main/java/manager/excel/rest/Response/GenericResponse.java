package manager.excel.rest.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
public class GenericResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String menssage;
    private String error;

    public GenericResponse(String menssage) {
        this.menssage=menssage;
    }
    public GenericResponse(String menssage,String error) {
        this.menssage=menssage;
        this.error=error;
    }
}
