package ra.web.dto.page;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDto<T> {
    private List<T> content; //Cacs phan tu trong trang
    private int currentPage; // Trang hien tai
    private long totalPages; // Tong so trang
    private int size; // So phan tu tren moi trang
    private String keyword;
    private String sortBy; // Cot sap xep
    private String direction; // Huong sap xep (asc, desc)
}
