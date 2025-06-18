package ra.web.utils.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;

    @Autowired // Inject đối tượng Cloudinary từ cấu hình (CloudinaryConfig)
    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    //Dùng để xử lý upload một file (MultipartFile) lên Cloudinary.
    public String uploadFile(MultipartFile file) throws IOException {
        // Tạo file tạm trên hệ thống (tên file bắt đầu bằng "upload" và giữ nguyên tên gốc)
        File temp = File.createTempFile("upload", file.getOriginalFilename());

        // Ghi nội dung từ MultipartFile vào file tạm
        file.transferTo(temp);

        // Upload file tạm lên Cloudinary, không truyền thêm tuỳ chọn nào (dùng ObjectUtils.emptyMap())
        Map uploadResult = cloudinary.uploader().upload(temp, ObjectUtils.emptyMap());
        String url = (String) uploadResult.get("secure_url");
        // Lấy và trả về đường dẫn HTTPS của file đã upload
        return (String) uploadResult.get("secure_url");
    }
}