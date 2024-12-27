package com.example.appbook;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.Nullable;


import com.example.appbook.model.TaiKhoan;


public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME = "BookLibrary.db";
    public static final int DATABASE_VERSION = 1;


    //Bảng tài khoản
    public static final String TABLE_TAIKHOAN = "tbTaiKhoan";
    public static final String TAIKHOAN_ID = "taikhoan_id";
    public static final String TAIKHOAN_TENDANGNHAP = "taikhoan_tendangnhap";
    public static final String TAIKHOAN_MATKHAU = "taikhoan_matkhau";
    public static final String TAIKHOAN_SDT = "taikhoan_sdt";
    public static final String TAIKHOAN_PHANQUYEN = "taikhoan_phanquyen";


    //Bảng truyện
    public static final String TABLE_TRUYEN = "tbTruyen";
    public static final String TRUYEN_ID = "truyen_id";
    public static final String TRUYEN_TENTRUYEN = "truyen_tentruyen";
    public static final String TRUYEN_TENTACGIA = "truyen_tentacgia";
    public static final String TRUYEN_MOTA = "truyen_mota";
    public static final String TRUYEN_IMAGE = "truyen_image";
    public static final String TRUYEN_LINK = "truyen_link";
    public static final String TRUYEN_THELOAI_ID = "theloai_id";
    public static final String TRUYEN_TRANGTHAI = "truyen_trangthai";
    public static final String TRUYEN_LUOTXEM = "truyen_luotxem";
    public static final String TRUYEN_NGAYDANG = "truyen_ngaydang";
    public static final String TRUYEN_NGAYCAPNHATCUOI = "truyen_ngaycapnhatcuoi";


    //Bảng thể loại truyện
    public static final String TABLE_THELOAI = "tbTheLoai";
    public static final String THELOAI_ID = "theloai_id";
    public static final String THELOAI_TEN = "theloai_ten";


    //Bảng truyện yêu thích
    public static final String TABLE_YEUTHICH = "tbYeuThich";
    public static final String YEUTHICH_ID = "yeuthich_id";
    public static final String YEUTHICH_TAIKHOAN_ID = "taikhoan_id";
    public static final String YEUTHICH_TRUYEN_ID = "truyen_id";
    public static final String YEUTHICH_NGAYTHEM = "yeuthich_ngaythem";


    //Bảng truyện đang đọc
    public static final String TABLE_DANGDOC = "tbDangDoc";
    public static final String DANGDOC_ID = "dangdoc_id";
    public static final String DANGDOC_TAIKHOAN_ID = "taikhoan_id";
    public static final String DANGDOC_TRUYEN_ID = "truyen_id";
    public static final String DANGDOC_NGAYTHEM = "dangdoc_ngaythem";


    // Tạo bảng tại phương thức này
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        this.context = context;
    }


    // Tạo bảng table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbTaiKhoan = "CREATE TABLE " + TABLE_TAIKHOAN + " ( " + TAIKHOAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TAIKHOAN_TENDANGNHAP + " TEXT, " + TAIKHOAN_MATKHAU + " TEXT, " + TAIKHOAN_SDT + " TEXT, "
                + TAIKHOAN_PHANQUYEN + " INTEGER )";

        String tbTruyen = "CREATE TABLE " + TABLE_TRUYEN + " ( " + TRUYEN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TRUYEN_TENTRUYEN + " TEXT, " + TRUYEN_TENTACGIA + " TEXT, " + TRUYEN_MOTA + " TEXT, "
                + TRUYEN_IMAGE + " TEXT, " + TRUYEN_LINK + " TEXT, " + TRUYEN_THELOAI_ID + " INTEGER, " + TRUYEN_TRANGTHAI + " TEXT, " + TRUYEN_LUOTXEM + " INTEGER, "
                + TRUYEN_NGAYDANG + " DATETIME, " + TRUYEN_NGAYCAPNHATCUOI + " DATETIME )";

        String tbTheLoai = "CREATE TABLE " + TABLE_THELOAI + " ( " + THELOAI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + THELOAI_TEN + " TEXT )";

        String tbYeuThich = "CREATE TABLE " + TABLE_YEUTHICH + " ( " + YEUTHICH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + YEUTHICH_TAIKHOAN_ID + " INTEGER, " + YEUTHICH_TRUYEN_ID + " INTEGER, " + YEUTHICH_NGAYTHEM + " DATETIME )";

        String tbDangDoc = "CREATE TABLE " + TABLE_DANGDOC + " ( " + DANGDOC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DANGDOC_TAIKHOAN_ID + " INTEGER, " + DANGDOC_TRUYEN_ID + " INTEGER, " + DANGDOC_NGAYTHEM + " DATETIME )";


        // Thêm tên thể loại
        String theloai_tieuthuyet = "INSERT INTO tbTheLoai VAlUES (null,'Tiểu thuyết')";
        String theloai_vanhoc = "INSERT INTO tbTheLoai VAlUES (null,'Văn học')";
        String theloai_truyendangian = "INSERT INTO tbTheLoai VAlUES (null,'Truyện dân gian')";
        String theloai_giaoduc = "INSERT INTO tbTheLoai VAlUES (null,'Giáo dục')";
        String theloai_truyenngan = "INSERT INTO tbTheLoai VAlUES (null,'Truyện ngắn')";

        // Thêm tên thể loại
        String sach_tieuthuyet_matbiec = "INSERT INTO tbTruyen VALUES (null,'Mắt biếc','Nguyễn Nhật Ánh','Mắt biếc là một tiểu thuyết lãng mạn nổi tiếng của nhà văn Nguyễn Nhật Ánh. Câu chuyện xoay quanh tình yêu đơn phương của Ngạn, một chàng trai quê, dành cho Hà Lan, cô bạn có đôi mắt biếc cuốn hút từ thuở nhỏ. Tuy nhiên, Hà Lan lại yêu một người khác và rời xa làng quê, để lại Ngạn với nỗi nhớ và tình cảm không bao giờ được đáp lại. Mắt biếc không chỉ là câu chuyện về tình yêu mà còn là nỗi tiếc nuối của những ký ức tuổi thơ, sự thay đổi của con người qua thời gian.','matbiec','https://truyenfull.vanhocviet.com/mat-biec/chuong-1.html',1,null,0,'2024-11-20','2024-11-20')";
        String sach_tieuthuyet_ongnoiphieuluyki = "INSERT INTO tbTruyen VALUES (null, 'Ông Nội Phiêu Lưu Ký', 'Dương Khuê', '“Ông Nội Phiêu Lưu Ký” là một câu chuyện thú vị và hấp dẫn về những cuộc phiêu lưu kỳ thú của một ông lão và những đứa trẻ trong gia đình. Qua đó, tác phẩm truyền tải những bài học về tình yêu thương, sự kiên trì và lòng dũng cảm.', 'ongnoiphieuluuky','https://truyenfull.vanhocviet.com/mat-biec/chuong-1.html', 1, null, 0, '2024-11-21', '2024-11-21')";
        String sach_tieuthuyet_chipheo = "INSERT INTO tbTruyen VALUES (null,'Chí Phèo','Nam Cao','Chí Phèo là một tác phẩm nổi tiếng của Nam Cao, phản ánh cuộc sống của người nông dân nghèo khổ dưới chế độ phong kiến. Câu chuyện xoay quanh bi kịch của Chí Phèo, một người đàn ông bị xã hội xua đuổi, đã đánh mất nhân phẩm và trở thành kẻ hung dữ. Tuy nhiên, trong con người anh vẫn tiềm ẩn những yếu tố của tình yêu và lòng nhân ái, tạo nên một câu chuyện đầy đau đớn về xã hội và số phận con người.','chipheo','https://bigone.vn/chi-pheo-truyen-ngan-nam-cao-a667.html',1,null,0,'2024-11-21','2024-11-21')";
        String sach_tieuthuyet_doithua = "INSERT INTO tbTruyen VALUES (null,'Đời thừa','Nam Cao','Đời thừa là một tiểu thuyết kinh điển của Nam Cao về cuộc sống của những con người nghèo khổ trong xã hội cũ. Qua những nhân vật chính là Hộ và bà vợ, Nam Cao đã khắc họa một cách sâu sắc về số phận và sự chán nản, tuyệt vọng của con người trong một xã hội thiếu công bằng và nhân đạo.','doithua','https://bigone.vn/doi-thua-truyen-ngan-nam-cao-a689.html',1,null,0,'2024-11-22','2024-11-22')";
        String sach_tieuthuyet_hoangtu = "INSERT INTO tbTruyen VALUES (null,'Hoàng tử bé','Antoine de Saint-Exupéry','Hoàng tử bé là một trong những cuốn tiểu thuyết nổi tiếng nhất trên thế giới. Tác phẩm kể về cuộc hành trình của một hoàng tử đến thăm các hành tinh khác nhau và gặp gỡ những con người kỳ lạ. Qua đó, tác giả khắc họa những bài học sâu sắc về cuộc sống, tình yêu và sự trưởng thành.','hoangtube','https://truyenfull.vanhocviet.com/hoang-tu-be/chuong-2.html?t=1734160954715','1',null,0,'2024-11-21','2024-11-21')";
        String sach_tieuthuyet_dacnhantam = "INSERT INTO tbTruyen VALUES (null, 'Đắc nhân tâm', 'Dale Carnegie', 'Đắc nhân tâm là cuốn sách kinh điển về nghệ thuật giao tiếp và tạo dựng mối quan hệ. Cuốn sách đã giúp hàng triệu người thay đổi cuộc sống của họ thông qua việc cải thiện kỹ năng giao tiếp.', 'dacnhantam', 'https://nhasachmienphi.com/doc-online/dac-nhan-tam-322410',2, null, 0, '2024-11-25', '2024-11-25')";
        String sach_tieuthuyet_toithayhoavangtrencoxanh = "INSERT INTO tbTruyen VALUES (null,'Tôi thấy hoa vàng trên cỏ xanh','Nguyễn Nhật Ánh','Tôi thấy hoa vàng trên cỏ xanh là một tiểu thuyết về tuổi thơ của Ngạn và những người bạn của anh tại một làng quê Việt Nam. Câu chuyện đậm chất thơ và giàu tính nhân văn, mang đến cho độc giả những cảm xúc chân thật về tình bạn, tình yêu và những kỷ niệm khó quên. Cuốn sách là sự hoài niệm về những ngày tháng trong sáng, tươi đẹp của tuổi trẻ.','hoavangtrencoxanh','https://truyenfull.tv/toi-thay-hoa-vang-tren-co-xanh/chuong-1.html',1,null,0,'2024-11-24','2024-11-24')";
        String sach_vanhoc_harrypotter = "INSERT INTO tbTruyen VALUES (null,'Harry Potter và Hòn đá Phù thủy','J.K. Rowling','Harry Potter và Hòn đá Phù thủy là phần đầu tiên trong loạt tiểu thuyết nổi tiếng của J.K. Rowling. Câu chuyện kể về hành trình của Harry Potter từ một cậu bé mồ côi bình thường đến việc phát hiện mình là phù thủy và bước vào thế giới phép thuật tại trường Hogwarts. Tại đây, Harry gặp gỡ những người bạn thân thiết và khám phá nhiều bí mật về quá khứ của mình.','harrypotter','https://truyenfull.tv/harry-potter-va-hon-da-phu-thuy-quyen-1/chuong-1.html\n',2,null,0,'2024-11-25','2024-11-25')";
        String sach_vanhoc_tatden = "INSERT INTO tbTruyen VALUES (null,'Tắt đèn','Ngô Tất Tố','Tắt đèn là một tác phẩm phản ánh sâu sắc đời sống khốn khổ của những người nông dân trong xã hội phong kiến Việt Nam. Câu chuyện kể về cuộc sống của nhân vật chính, chị Dậu, trong những điều kiện sống nghèo khó và đầy áp bức. Từ đó, tác phẩm phê phán sự bất công trong xã hội và khắc họa tinh thần đấu tranh của con người.','tatden','https://truyenhdt.com/truyen/tat-den/chap/4361939-chuong-1/',2,null,0,'2024-11-26','2024-11-26')";
        String sach_vanhoc_sodo = "INSERT INTO tbTruyen VALUES (null,'Số đỏ','Vũ Trọng Phụng','Số đỏ là một tiểu thuyết mang tính trào phúng, phản ánh hiện thực xã hội Việt Nam trong giai đoạn xã hội cũ. Câu chuyện xoay quanh nhân vật chính Xuân, người trải qua những biến cố và nghịch cảnh trong đời sống, thể hiện những quan điểm, góc nhìn châm biếm về xã hội thời bấy giờ. Tác phẩm phản ánh sự vô lý, bất công và mâu thuẫn của xã hội.','sodo','https://truyenhdt.com/truyen/so-do/chap/4361882-chap-1/\n',2,null,0,'2024-11-27','2024-11-27')";
        String sach_vanhoc_nhatky = "INSERT INTO tbTruyen VALUES (null,'Nhật ký trong tù','Hồ Chí Minh','Nhật ký trong tù là tập hợp những bài thơ của Hồ Chí Minh trong thời gian người bị giam cầm tại Trung Quốc. Tập thơ phản ánh một cách sinh động cuộc sống khổ cực trong tù và tinh thần kiên cường, bất khuất của Người. Các bài thơ trong sách là những suy tư, những lời tâm sự về cuộc sống, tình yêu quê hương đất nước.','nhatky','https://truyenhdt.com/truyen/muoi-sau-bai-tho-hay-nhat-trong-tap-tho-nhat-ky-trong-tu/chap/6473097-chuong-3/\n',2,null,0,'2024-11-28','2024-11-28')";
        String sach_vanhoc_sudai = "INSERT INTO tbTruyen VALUES (null, 'Sự Dại', 'Tô Hoài', 'Sự Dại là cuốn tiểu thuyết nổi tiếng của nhà văn Tô Hoài, với phong cách kể chuyện mộc mạc, gần gũi nhưng lại rất sâu sắc trong việc phản ánh tâm lý con người và những biến động của xã hội trong một thời kỳ lịch sử đặc biệt.', 'sudai','https://vietmessenger.com/books/?title=co%20dai%20to%20hoai\n', 2, null, 0, '2024-11-21', '2024-11-21')";
        String sach_truyendangian_traucham = "INSERT INTO tbTruyen VALUES (null,'Trâu chậm uống nước đục','Tác giả dân gian','Trâu chậm uống nước đục là một câu chuyện ngụ ngôn nổi tiếng, dạy bài học về sự khôn ngoan và cẩn trọng. Trong câu chuyện, nhân vật trâu chậm biết chọn lựa thời điểm thích hợp để tìm nước, và đã tránh được những nguy hiểm bất ngờ. Câu chuyện này mang đến thông điệp về sự kiên nhẫn và khéo léo trong cuộc sống.','traucham','https://vietmessenger.com/books/?title=co%20dai%20to%20hoai\n',3,null,0,'2024-12-01','2024-12-01')";
        String sach_truyendangian_concoc = "INSERT INTO tbTruyen VALUES (null,'Con cóc là cậu ông trời','Tác giả dân gian','Câu chuyện về con cóc là một trong những truyện dân gian nổi tiếng, kể về hành trình của con cóc nhỏ bé nhưng dũng cảm. Con cóc đã dạy cho mọi người bài học về sự khiêm tốn và lòng trung thực trong cuộc sống.','concoc','https://heritagevietnamairlines.com/con-coc-la-cau-ong-troi-2/',3,null,0,'2024-12-02','2024-12-02')";
        String sach_truyendangian_chucuoi = "INSERT INTO tbTruyen VALUES (null,'Sự tích chú Cuội ngồi gốc cây đa','Tác giả dân gian','Sự tích chú Cuội ngồi gốc cây đa là một trong những câu chuyện cổ tích Việt Nam nổi tiếng, giải thích về sự ra đời của cây đa và truyền thuyết chú Cuội. Đây là câu chuyện về lòng nhân ái và sự lừa dối, tạo nên một bài học về đạo đức trong xã hội.','chucuoi','https://eva.vn/nuoi-con-iq-eq/co-tich-su-tich-chu-cuoi-cay-da-c429a591347.html\n',3,null,0,'2024-12-03','2024-12-03')";
        String sach_truyendangian_tamcam = "INSERT INTO tbTruyen VALUES (null, 'Tấm Cám', 'Truyền thuyết dân gian', 'Tấm Cám là câu chuyện cổ tích nổi tiếng trong kho tàng văn học dân gian Việt Nam. Câu chuyện kể về Tấm, một cô gái hiền lành, nhân hậu nhưng phải chịu nhiều sự bất công từ người mẹ kế và cô em gái Cám độc ác. Tấm đã trải qua bao khó khăn và thử thách, từ việc bị mẹ kế hãm hại đến việc phải nhờ đến sự trợ giúp của các thế lực thần bí để chiến thắng và tìm lại hạnh phúc. Truyện mang thông điệp về sự kiên nhẫn, lòng tốt và công lý.', 'tamcam','https://truyenfull.tv/tam-cam-dang-sau-mot-co-tich/chuong-1.html\n', 3, null, 0, '2024-11-25', '2024-11-25')";
        String sach_truyendangian_caytre = "INSERT INTO tbTruyen VALUES (null, 'Cây tre trăm đốt', 'Truyền thuyết dân gian', 'Cây tre trăm đốt là một câu chuyện dân gian nổi tiếng, nói về một người nghèo khó sống trong một ngôi làng. Trong một lần đi qua rừng, anh đã gặp một cây tre kỳ lạ có thể mọc lên tới trăm đốt. Nhờ vào cây tre này, anh đã giúp đỡ được dân làng và thay đổi cuộc đời mình. Câu chuyện mang thông điệp về sự kiên nhẫn và lòng dũng cảm khi đối mặt với khó khăn trong cuộc sống.', 'caytre','https://mntranduong.haiphong.edu.vn/3-tuyen-tap-tro-choi-bai-hat-cau-chuyen/truyen-co-tich-cay-tre-tram-dot/ctmb/23614/207558\n', 3, null, 0, '2024-11-25', '2024-11-25')";
        String sach_truyendangian_cobebandiem = "INSERT INTO tbTruyen VALUES (null, 'Cô bé bán diêm', 'Hans Christian Andersen', 'Cô bé bán diêm là một câu chuyện nổi tiếng của Hans Christian Andersen kể về một cô bé nghèo khó, phải bán diêm trong đêm giao thừa lạnh giá. Dù không bán được diêm nào, cô bé vẫn có những ước mơ đẹp và cuối cùng tìm thấy sự bình yên trong giấc mơ của mình. Câu chuyện mang thông điệp về sự hi sinh, lòng trắc ẩn và hy vọng trong cuộc sống.', 'cobebandiem','https://happyhouse.edu.vn/goc-phu-huynh/truyen-ke-co-be-ban-diem-54.html\n', 3, null, 0, '2024-11-25', '2024-11-25')";
        String sach_anhmeovanvacochimen = "INSERT INTO tbTruyen VALUES (null, 'Anh Mèo Vằn Và Cô Chim Én', 'Nguyễn Nhật Ánh', '“Anh Mèo Vằn và Cô Chim Én” là một câu chuyện dễ thương về tình bạn và sự đồng cảm giữa các loài vật. Mèo Vằn và Chim Én, dù có những khác biệt về bản tính và cuộc sống, vẫn tìm thấy điểm chung và kết nối nhau qua những cuộc phiêu lưu thú vị. Cuốn sách mang đến cho độc giả những bài học quý giá về tình bạn, sự hiểu biết và tôn trọng lẫn nhau, từ đó khuyến khích tình yêu thương và sự hòa hợp trong cộng đồng.', 'anhmeovanvacochimen','https://happyhouse.edu.vn/goc-phu-huynh/truyen-ke-co-be-ban-diem-54.html\n', 3, null, 0, '2024-11-22', '2024-11-22')";
        String sach_giaoduc_nguoivietnam = "INSERT INTO tbTruyen VALUES (null, 'Người Việt Nam', 'Nguyễn Trường Tộ', 'Nguyễn Trường Tộ là một trí thức kiệt xuất, người đã có những đóng góp quan trọng vào việc thúc đẩy cải cách đất nước trong thời kỳ cuối thế kỷ XIX. Ông nổi bật với tầm nhìn và trí tuệ vượt thời đại, luôn kêu gọi cải cách đất nước để nâng cao sức mạnh quốc gia. Những đề xuất của ông về giáo dục, quân sự và phát triển công nghiệp thương mại vẫn còn giá trị cho đến ngày nay.', 'nguoivietnam','https://sachhiem.net/BUIKHA/BuiKha22_NTT.php', 4, null, 0, '2024-12-01', '2024-12-01')";
        String sach_giaoduc_suynghialamgiau = "INSERT INTO tbTruyen VALUES (null, 'Suy nghĩ, làm giàu', 'Napoleon Hill', 'Suy nghĩ, làm giàu là cuốn sách nổi tiếng giúp người đọc hiểu được những nguyên lý và bí quyết để đạt được thành công trong công việc và cuộc sống. Cuốn sách chia sẻ những nguyên lý cơ bản của sự giàu có và hạnh phúc mà mọi người có thể áp dụng để đạt được mục tiêu.', 'suynghialamgiau','https://file.nhasachmienphi.com/pdf/nhasachmienphi-suy-nghi-va-lam-giau.pdf\n', 4, null, 0, '2024-11-25', '2024-11-25')";
        String sach_giaoduc_chagiouchangheo = "INSERT INTO tbTruyen VALUES (null, 'Cha giàu, cha nghèo', 'Robert T. Kiyosaki', 'Cha giàu, cha nghèo là một cuốn sách giúp người đọc nhận thức rõ về sự khác biệt giữa cách tư duy của những người giàu và nghèo. Cuốn sách hướng dẫn về cách quản lý tài chính và đầu tư để đạt được sự tự do tài chính trong tương lai.', 'chagiouchangheo','https://truyenfull.tv/cha-giau-cha-ngheo/quyen-1-chuong-1.html\n', 4, null, 0, '2024-11-25', '2024-11-25')";
        String sach_giaoduc_tuduynhanhvacham = "INSERT INTO tbTruyen VALUES (null, 'Tư duy nhanh và chậm', 'Daniel Kahneman', 'Tư duy nhanh và chậm của Daniel Kahneman giải thích cách mà con người ra quyết định trong các tình huống khác nhau, với sự phân chia rõ ràng giữa hai loại tư duy: tư duy nhanh (instinctive) và tư duy chậm (logical). Cuốn sách này giúp người đọc hiểu rõ hơn về các quyết định trong cuộc sống và công việc.', 'tuduynhanhvacham','https://nhasachmienphi.com/doc-online/tu-duy-nhanh-va-cham-323937#google_vignette\n', 4, null, 0, '2024-11-25', '2024-11-25')";
        String sach_giaoduc_nguoibanhang = "INSERT INTO tbTruyen VALUES (null, 'Người bán hàng vĩ đại nhất thế giới', 'Og Mandino', 'Người bán hàng vĩ đại nhất thế giới là một cuốn sách dạy về nghệ thuật bán hàng và tự cải thiện bản thân. Cuốn sách mang lại những bài học quý giá về cách thức giao tiếp, thuyết phục và quản lý bán hàng hiệu quả.', 'nguoibanhang','https://file.nhasachmienphi.com/pdf/nhasachmienphi-nguoi-ban-hang-vi-dai-nhat-the-gioi.pdf\n', 4, null, 0, '2024-11-25', '2024-11-25')";
        String sach_giaoduc_bimatcuathanhcong = "INSERT INTO tbTruyen VALUES (null, 'Bí mật của thành công', 'Og Mandino', 'Bí mật của thành công là cuốn sách cổ điển về việc đạt được thành công trong cuộc sống thông qua một quy trình tư duy và hành động có phương pháp. Cuốn sách này sẽ giúp bạn tìm ra các chìa khóa để thành công trong công việc và cuộc sống cá nhân.', 'bimatcuathanhcong','https://vanhoctre.com/lao-hac-nam-cao.html', 4, null, 0, '2024-11-25', '2024-11-25')";
        String sach_truyenngan_laohac = "INSERT INTO tbTruyen VALUES (null, 'Lão Hạc', 'Nam Cao', 'Lão Hạc là một tác phẩm nổi tiếng của nhà văn Nam Cao, kể về cuộc sống khốn khổ của một lão nông nghèo trong xã hội phong kiến. Lão Hạc sống một mình trong căn nhà nghèo khó và chăm sóc con chó cưng. Mặc dù sống trong cảnh nghèo đói, lão vẫn luôn yêu thương và chăm sóc con vật như người bạn tri kỷ. Tuy nhiên, sau khi con chó chết, Lão Hạc đã tìm đến cái chết để giải thoát cho chính mình và con chó. Câu chuyện phản ánh sự bất công của xã hội phong kiến và là nỗi khổ của những con người nghèo trong cuộc sống đầy bi kịch.', 'laohac','https://vanhoctre.com/lao-hac-nam-cao.html', 5, null, 0, '2024-11-26', '2024-11-26')";
        String sach_truyenngan_langlesapa = "INSERT INTO tbTruyen VALUES (null, 'Lặng Lẽ Sapa', 'Nguyễn Thành Long', 'Lặng Lẽ Sapa là một tác phẩm nổi tiếng của nhà văn Nguyễn Thành Long, kể về câu chuyện của những con người sống trong những hoàn cảnh đặc biệt tại Sapa, một vùng núi cao, hẻo lánh. Trong tác phẩm, nhân vật chính là một bác sĩ trẻ làm việc tại Sapa, nơi anh gặp những người dân và những du khách qua lại. Câu chuyện thể hiện lòng kiên trì, sự hy sinh của những người làm công tác cứu chữa bệnh cho dân tộc, đồng thời phác họa những giá trị nhân văn về cuộc sống con người trong xã hội. Tác phẩm mang lại những suy ngẫm sâu sắc về cuộc sống con người và thiên nhiên.', 'langlesapa','https://book.sachgiai.com/truyen/lang-le-sapa.html', 5, null, 0, '2024-11-26', '2024-11-26')";
        String sach_truyenngan_rungxanu = "INSERT INTO tbTruyen VALUES (null, 'Rừng Xà Nu', 'Nguyễn Trung Thành', 'Rừng Xà Nu là một tác phẩm nổi tiếng của Nguyễn Trung Thành, kể về cuộc sống và những hy sinh của người dân Tây Nguyên trong cuộc kháng chiến chống Pháp. Câu chuyện phản ánh sức mạnh của tình đoàn kết và lòng dũng cảm của các thế hệ người dân Tây Nguyên trong cuộc chiến đấu vì độc lập, tự do của dân tộc. Tác phẩm không chỉ phản ánh những khó khăn, gian khổ mà còn thể hiện lòng yêu nước, tinh thần quật cường của con người Việt Nam. Đặc biệt, rừng xà nu, với sức sống mãnh liệt của những cây cổ thụ, trở thành biểu tượng của sự trường tồn của dân tộc.', 'rungxanu','https://book.sachgiai.com/truyen/rung-xa-nu.html\n', 5, null, 0, '2024-11-26', '2024-11-26')";
        String sach_truyenngan_haiduatre = "INSERT INTO tbTruyen VALUES (null, 'Hai Đứa Trẻ', 'Thạch Lam', 'Hai Đứa Trẻ là một tác phẩm nổi tiếng của nhà văn Thạch Lam, kể về hai đứa trẻ sống trong một gia đình nghèo. Câu chuyện phản ánh những khó khăn mà họ phải đối mặt trong cuộc sống, cũng như sự yêu thương, sự quan tâm mà gia đình dành cho nhau trong hoàn cảnh khó khăn. Tác phẩm mang đậm tính nhân văn, thể hiện lòng nhân ái, sự sẻ chia và tình yêu thương giữa những con người trong cuộc sống thường nhật. Đặc biệt, hình ảnh hai đứa trẻ gợi lên những suy ngẫm về cuộc sống và sự trưởng thành.', 'haiduatre','https://bigone.vn/hai-dua-tre-truyen-ngan-thach-lam-a688.html\n', 5, null, 0, '2024-11-26', '2024-11-26')";
        String sach_truyenngan_leuchong = "INSERT INTO tbTruyen VALUES (null, 'Lều Chõng', 'Nguyễn Công Hoan', 'Lều Chõng là một tác phẩm nổi bật của Nguyễn Công Hoan, phản ánh những mâu thuẫn trong xã hội phong kiến qua hình ảnh nhân vật lều chõng - nơi mà các học trò nghèo phải ngồi học, nhưng cũng là biểu tượng cho sự nghèo khổ và khốn khó. Câu chuyện kể về cuộc sống khổ cực của những người học trò nghèo nhưng luôn khát khao học hỏi và vươn lên trong xã hội. Tuy nhiên, tác phẩm cũng không thiếu sự trào phúng, mỉa mai những bất công trong xã hội phong kiến.', 'leuchong','https://www.sachhayonline.com/tua-sach/leu-chong/chuong-1/805\n', 5, null, 0, '2024-11-26', '2024-11-26')";
        String sach_truyenngan_banthanhpho = "INSERT INTO tbTruyen VALUES (null, 'Bạn Thành Phố', 'Nguyễn Nhật Ánh', '“Bạn Thành Phố” là một câu chuyện đầy cảm xúc về tình bạn, tình yêu và sự trưởng thành của những đứa trẻ trong môi trường đô thị hiện đại. Câu chuyện xoay quanh nhóm bạn nhỏ, khi những biến cố trong cuộc sống khiến họ phải đối mặt với những thay đổi lớn, từ việc chuyển trường cho đến việc tìm kiếm con đường riêng cho mình. Cuốn sách không chỉ là những dòng chữ về tình bạn thuần khiết mà còn phản ánh những giá trị nhân văn sâu sắc về tình yêu, sự hy sinh và lòng kiên cường trong cuộc sống.', 'banthanhpho','https://truyenhdt.com/truyen/tat-den/chap/4361939-chuong-1/\n', 5, null, 0, '2024-11-22', '2024-11-22')";
        String sach_truyenngan_noikhurungchamtoithienvisao = "INSERT INTO tbTruyen VALUES (null, 'Nơi Khu Rừng Chạm Tới Những Vì Sao', 'Lê Thị Thanh Hương', '“Nơi Khu Rừng Chạm Tới Những Vì Sao” là một câu chuyện kỳ diệu, nơi những phép màu và bí ẩn thiên nhiên kết hợp với những giấc mơ của những đứa trẻ. Trong khu rừng huyền bí, các nhân vật chính – một nhóm trẻ em tò mò – khám phá những điều kỳ diệu xảy ra khi họ tìm cách chạm đến các vì sao. Cuốn sách không chỉ là một cuộc phiêu lưu đầy màu sắc mà còn chứa đựng thông điệp về sự hiếu kỳ, khám phá và niềm tin vào những điều tưởng chừng như không thể.', 'noikhurungchamtoinhungvisao','https://spiderum.com/bai-dang/Review-Noi-khu-rung-cham-toi-nhung-vi-sao_Glendy-Vanderah-uus\n', 5, null, 0, '2024-11-22', '2024-11-22')";


        String sach_tieuthuyet_vochongaphu = "INSERT INTO tbTruyen VALUES (null, 'Vợ chồng A Phủ', 'Tô Hoài', 'Vợ chồng A Phủ là tác phẩm nổi tiếng của Tô Hoài, kể về cuộc sống nghèo khổ của người dân miền núi. Trong đó, nhân vật A Phủ và Mị đại diện cho những người dân nghèo khổ bị xã hội áp bức, nhưng tình yêu giữa họ là điểm sáng trong cuộc sống đầy gian khổ. Câu chuyện không chỉ về tình yêu mà còn về sự đấu tranh cho tự do.', 'vochongaphu', 'https://sachgiaibaitap.com/sach_giao_khoa/sach-giao-khoa-ngu-van-lop-12-nang-cao-tap-2-vo-chong-a-phu-trich-to-hoai/', 1, null, 0, '2024-11-20', '2024-11-20')";
        String sach_tieuthuyet_demenphieuluki = "INSERT INTO tbTruyen VALUES (null, 'Dế Mèn Phiêu Lưu Ký', 'Tô Hoài', 'Dế Mèn Phiêu Lưu Ký kể về cuộc hành trình của chú dế mèn, từ lúc tự tin, kiêu hãnh cho đến những bài học quý giá về sự trưởng thành. Qua những chuyến phiêu lưu, Dế Mèn đã học được cách sống có trách nhiệm và yêu thương. Đây là một tác phẩm không chỉ dành cho thiếu nhi mà còn mang giá trị nhân văn sâu sắc.', 'demenphieuluki', 'https://dtruyen.net/de-men-phieu-luu-ky/toi-song-doc-lap-tu-thua-be--mot-su-ngo-nghich-dang-an-han-suot-doi_1692415.html', 1, null, 0, '2024-11-20', '2024-11-20')";
        String sach_vanhoc_datrungphuongnam = "INSERT INTO tbTruyen VALUES (null, 'Đất rừng phương Nam', 'Đoàn Giỏi', 'Đất rừng phương Nam là tác phẩm nổi bật của Đoàn Giỏi, kể về cuộc sống của những con người sinh sống ở vùng đất phương Nam. Câu chuyện tái hiện lại những biến động xã hội trong giai đoạn lịch sử đầy khó khăn và tình cảm giữa những con người ở miền Tây Nam Bộ, vừa oai hùng, vừa đậm chất nhân văn.', 'datrungphuongnam', 'https://dtruyen.net/dat-rung-phuong-nam/chuong-1_448400.html', 2, null, 0, '2024-11-20', '2024-11-20')";
        String sach_vanhoc_doanthuyendanhtca = "INSERT INTO tbTruyen VALUES (null,'Đoàn thuyền đánh cá','Huy Cận','Bài thơ “Đoàn thuyền đánh cá” là một trong những tác phẩm nổi tiếng của Huy Cận, thể hiện vẻ đẹp của thiên nhiên đất nước và con người lao động vất vả nhưng tràn đầy niềm tin và hy vọng.', 'doanthuyendanhtca', 'https://truyenfull.vanhocviet.com/doan-thuyen-danh-ca/chuong-1.html', 2, null, 0, '2024-12-25', '2024-12-25')";
        String sach_truyendangian_thachsanh = "INSERT INTO tbTruyen VALUES (null,'Truyện Thạch Sanh','Truyền thuyết dân gian','Truyện Thạch Sanh là một trong những câu chuyện dân gian nổi tiếng, kể về chàng trai nghèo Thạch Sanh, người đã vượt qua nhiều thử thách và chiến thắng được những thế lực tà ác. Đây là một câu chuyện về lòng dũng cảm, chính nghĩa và tình yêu thương gia đình.', 'thachsanh', 'https://eva.vn/day-con/truyen-co-tich-thach-sanh-c14a421192.html', 3, null, 0, '2024-12-25', '2024-12-25')";
        String sach_truyendangian_conrongchautien = "INSERT INTO tbTruyen VALUES (null,'Truyện Con Rồng, Cháu Tiên','Truyền thuyết dân gian','Truyện Con Rồng, Cháu Tiên là một truyền thuyết nổi tiếng của dân tộc Việt Nam, kể về nguồn gốc của người Việt từ cuộc hôn nhân giữa Lạc Long Quân và Âu Cơ. Câu chuyện này thể hiện ý nghĩa về sự đoàn kết dân tộc, nguồn cội của người Việt.', 'conrongchautien', 'https://mnbaccau.longbien.edu.vn/tho-truyen-cho-be/truyen-truyen-thuyet-con-rong-chau-tien-lop-mgl-a1/ctmb/13292/481546', 3, null, 0, '2024-12-25', '2024-12-25')";
        String sach_giaoduc_khuyenhoc = "INSERT INTO tbTruyen VALUES (null,'Khuyến Học','Fukuzawa Yukichi','Khuyến Học là một tác phẩm nổi tiếng của Fukuzawa Yukichi, một nhà tư tưởng và giáo dục người Nhật. Tác phẩm này là lời khuyên về tầm quan trọng của việc học và tri thức trong việc phát triển bản thân và xã hội. Fukuzawa đã nhấn mạnh rằng học vấn là nền tảng vững chắc để cải cách xã hội và xây dựng quốc gia. Bằng cách khuyến khích người dân Nhật Bản học hỏi và phát triển, ông đã góp phần vào sự thay đổi mạnh mẽ của Nhật Bản trong thời kỳ Minh Trị.', 'khuyenhoc', 'https://nhatban.net.vn/wp-content/uploads/Khuyen-hoc-Fukuzawa-Yukichi.pdf', 4, null, 0, '2024-12-25', '2024-12-25')";
        String sach_giaoduc_phuongphapgiaoduc = "INSERT INTO tbTruyen VALUES (null,'Phương Pháp Giáo Dục Con Của Người Do Thái','Do Thái','Phương Pháp Giáo Dục Con Của Người Do Thái là một tác phẩm nổi bật về phương pháp giáo dục của người Do Thái, nhấn mạnh tầm quan trọng của việc nuôi dạy trẻ em một cách toàn diện, từ kiến thức đến kỹ năng sống, và đặc biệt là khả năng suy nghĩ độc lập. Cuốn sách này đưa ra các nguyên tắc về giáo dục con cái như sự tôn trọng, sự tự lập, sự sáng tạo và trách nhiệm, giúp trẻ em phát triển mạnh mẽ và có một cuộc sống thành công.', 'giaoduc', 'https://khoahoctamlinh.vn/img/news/2021/12/larger/5443-phuong-phap-giao-duc-con-cua-nguoi-do-thai-pdf-khoahoctamlinh.vn.pdf', 4, null, 0, '2024-12-25', '2024-12-25')";
        String sach_truyenngan_vonhat = "INSERT INTO tbTruyen VALUES (null,'Vợ Nhặt','Kim Lân','Vợ Nhặt của Kim Lân là một tác phẩm đặc sắc của văn học Việt Nam, phản ánh tình cảnh khốn khó của người dân trong nạn đói năm 1945. Truyện xoay quanh nhân vật Tràng và câu chuyện tình đầy bi kịch của anh với người vợ nhặt trong hoàn cảnh đói kém.', 'vonhat', 'https://truyenhdt.com/truyen/vo-nhat-2/', 5, null, 0, '2024-12-25', '2024-12-25')";
        String sach_truyenngan_bocuatoi = "INSERT INTO tbTruyen VALUES (null,'Bố Của Tôi','Nguyễn Nhật Ánh','Truyện ngắn Bố Của Tôi của Nguyễn Nhật Ánh là câu chuyện cảm động về tình cha con. Tác phẩm mang đến cái nhìn sâu sắc về sự hi sinh, tình yêu thương vô bờ bến của người cha dành cho con, trong khi bản thân người con lại không nhận ra điều đó cho đến khi trưởng thành.', 'bocuatoi', 'http://vietnamthuquan.eu/truyen/truyen.aspx?tid=2qtqv3m3237nvnvn4n0ntn31n343tq83a3q3m3237nvn&AspxAutoDetectCookieSupport=1', 5, null, 0, '2024-12-25', '2024-12-25')";



        db.execSQL(tbTaiKhoan);
        db.execSQL(tbTruyen);
        db.execSQL(tbTheLoai);
        db.execSQL(tbYeuThich);
        db.execSQL(tbDangDoc);


        // Thêm tên thể loại vào Database
        db.execSQL(theloai_tieuthuyet);
        db.execSQL(theloai_vanhoc);
        db.execSQL(theloai_truyendangian);
        db.execSQL(theloai_giaoduc);
        db.execSQL(theloai_truyenngan);


        // Thêm tên sách vào Database
        db.execSQL(sach_tieuthuyet_matbiec);
        db.execSQL(sach_tieuthuyet_ongnoiphieuluyki);
        db.execSQL(sach_tieuthuyet_chipheo);
        db.execSQL(sach_tieuthuyet_doithua);
        db.execSQL(sach_tieuthuyet_hoangtu);
        db.execSQL(sach_tieuthuyet_dacnhantam);
        db.execSQL(sach_tieuthuyet_toithayhoavangtrencoxanh);
        db.execSQL(sach_vanhoc_harrypotter);
        db.execSQL(sach_vanhoc_tatden);
        db.execSQL(sach_vanhoc_sodo);
        db.execSQL(sach_vanhoc_nhatky);
        db.execSQL(sach_vanhoc_sudai);
        db.execSQL(sach_truyendangian_traucham);
        db.execSQL(sach_truyendangian_concoc);
        db.execSQL(sach_truyendangian_chucuoi);
        db.execSQL(sach_truyendangian_tamcam);
        db.execSQL(sach_truyendangian_caytre);
        db.execSQL(sach_truyendangian_cobebandiem);
        db.execSQL(sach_anhmeovanvacochimen);
        db.execSQL(sach_giaoduc_nguoivietnam);
        db.execSQL(sach_giaoduc_suynghialamgiau);
        db.execSQL(sach_giaoduc_chagiouchangheo);
        db.execSQL(sach_giaoduc_tuduynhanhvacham);
        db.execSQL(sach_giaoduc_nguoibanhang);
        db.execSQL(sach_giaoduc_bimatcuathanhcong);
        db.execSQL(sach_truyenngan_laohac);
        db.execSQL(sach_truyenngan_langlesapa);
        db.execSQL(sach_truyenngan_rungxanu);
        db.execSQL(sach_truyenngan_haiduatre);
        db.execSQL(sach_truyenngan_leuchong);
        db.execSQL(sach_truyenngan_banthanhpho);
        db.execSQL(sach_truyenngan_noikhurungchamtoithienvisao);

        db.execSQL(sach_tieuthuyet_vochongaphu);
        db.execSQL(sach_tieuthuyet_demenphieuluki);
        db.execSQL(sach_vanhoc_datrungphuongnam);
        db.execSQL(sach_vanhoc_doanthuyendanhtca);
        db.execSQL(sach_truyendangian_thachsanh);
        db.execSQL(sach_truyendangian_conrongchautien);
        db.execSQL(sach_giaoduc_khuyenhoc);
        db.execSQL(sach_giaoduc_phuongphapgiaoduc);
        db.execSQL(sach_truyenngan_vonhat);
        db.execSQL(sach_truyenngan_bocuatoi);

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public SQLiteDatabase open() {
        return this.getWritableDatabase();
    }


    //Phương thức lấy tất cả tài khoản
    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_TAIKHOAN, null);
        return res;
    }


    //Phương thức add tài khoản vào database
    public void AddTaiKhoan(TaiKhoan taikhoan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Thực hiện insert thông qua ContentValues
        values.put(TAIKHOAN_TENDANGNHAP, taikhoan.getmTenDangNhap());
        values.put(TAIKHOAN_MATKHAU, taikhoan.getmMatKhau());
        values.put(TAIKHOAN_SDT, taikhoan.getmSDT());
        values.put(TAIKHOAN_PHANQUYEN, taikhoan.getmPhanQuyen());

        db.insert(TABLE_TAIKHOAN, null, values);

        Log.e("ADD TK", "TC");
    }


    public Cursor getAccountByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM tbTaiKhoan WHERE taikhoan_tendangnhap = ?", new String[]{username});
    }


    // Phương thức lấy tất cả thể loại truyện
    public Cursor getAllTheLoai() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_THELOAI, null);
    }


    // Phương thức Lấy truyện theo tên thể loại (dùng JOIN với tbTheLoai)
    public Cursor getBooksByCategory(String categoryName) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT t.* FROM " + TABLE_TRUYEN + " t " +
                        "JOIN " + TABLE_THELOAI + " tl ON t." + TRUYEN_THELOAI_ID + " = tl." + THELOAI_ID + " " +
                        "WHERE tl." + THELOAI_TEN + " = ?",
                new String[]{categoryName}
        );
    }

    // Phương thức Lấy top 5 truyện theo tên thể loại mới nhất ừ dưới lên
    public Cursor GetTop5SachTheoTheLoai(String categoryName) {
        SQLiteDatabase db = this.getReadableDatabase();
        // Truy vấn top 5 truyện theo thể loại
        return db.rawQuery(
                "SELECT t.* FROM " + TABLE_TRUYEN + " t " +
                        "JOIN " + TABLE_THELOAI + " tl ON t." + TRUYEN_THELOAI_ID + " = tl." + THELOAI_ID + " " +
                        "WHERE tl." + THELOAI_TEN + " = ?" +
                        "LIMIT 5",  // Lấy tối đa 5 kết quả
                new String[]{categoryName}
        );
    }

    public Cursor GetTop10SachMoiXuatBan() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT t." + TRUYEN_ID + ", t." + TRUYEN_TENTRUYEN + ", t." + TRUYEN_TENTACGIA + ", t." + TRUYEN_MOTA + ", t." + TRUYEN_IMAGE + ", tl." + THELOAI_TEN + " " +
                        "FROM " + TABLE_TRUYEN + " t " +
                        "JOIN " + TABLE_THELOAI + " tl ON t." + THELOAI_ID + " = tl." + THELOAI_ID + " " +
                        "ORDER BY t." + TRUYEN_NGAYDANG + " DESC " +
                        "LIMIT 10",
                null
        );
    }


    public Cursor GetTop10SachNoiBat() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT * " +
                        "FROM " + TABLE_TRUYEN + " t " +
                        "JOIN " + TABLE_THELOAI + " tl ON t." + THELOAI_ID + " = tl." + THELOAI_ID + " " +
                        "ORDER BY t." + TRUYEN_LUOTXEM + " DESC " +
                        "LIMIT 10",
                null
        );
    }



    public Cursor TimKiemSachTheoTheLoai(String query, String tentheloai) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT t." + TRUYEN_ID + ", t." + TRUYEN_TENTRUYEN + ", t." + TRUYEN_TENTACGIA + ", t." + TRUYEN_MOTA + ", t." + TRUYEN_IMAGE + ", tl." + THELOAI_TEN + " " +
                        "FROM " + TABLE_TRUYEN + " t " +
                        "JOIN " + TABLE_THELOAI + " tl ON t." + THELOAI_ID + " = tl." + THELOAI_ID + " " +
                        "WHERE t." + TRUYEN_TENTRUYEN + " LIKE ? AND tl." + THELOAI_TEN + " = ?",
                new String[]{"%" + query + "%", tentheloai}
        );
    }


    // Thêm truyện vào bảng yêu thích
    public void addToFavorites(int taikhoan_id, int truyen_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(YEUTHICH_TAIKHOAN_ID, taikhoan_id);
        values.put(YEUTHICH_TRUYEN_ID, truyen_id);
        values.put(YEUTHICH_NGAYTHEM, "");
        db.insert(TABLE_YEUTHICH, null, values);
        db.close();
    }

    // Thêm truyện vào bảng yêu thích
    public void addToReadingList(int taikhoan_id, int truyen_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DANGDOC_TAIKHOAN_ID, taikhoan_id);
        values.put(DANGDOC_TRUYEN_ID, truyen_id);
        values.put(DANGDOC_NGAYTHEM, "");
        db.insert(TABLE_DANGDOC, null, values);
        db.close();
    }

    // Xóa truyện khỏi bảng yêu thích
    public void removeFromFavorites(int taikhoan_id, int truyen_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_YEUTHICH, YEUTHICH_TAIKHOAN_ID + " = ? AND " + YEUTHICH_TRUYEN_ID + " = ?",
                new String[]{String.valueOf(taikhoan_id), String.valueOf(truyen_id)});
    }

    // Lấy danh sách yêu thích của người dùng theo taikhoan_id và truyen_id
    public Cursor getFavorites(int taikhoan_id, int truyen_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        // Truy vấn bảng yêu thích để lấy tất cả truyện yêu thích của người dùng theo taikhoan_id và truyen_id
        String query = "SELECT * FROM " + TABLE_YEUTHICH +
                " WHERE " + YEUTHICH_TAIKHOAN_ID + " = ? AND " + YEUTHICH_TRUYEN_ID + " = ?";
        return db.rawQuery(query, new String[]{String.valueOf(taikhoan_id), String.valueOf(truyen_id)});
    }

    public Cursor getFavoritesByTaiKhoan(int taikhoan_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_YEUTHICH +
                " INNER JOIN " + TABLE_TRUYEN +
                " ON " + TABLE_YEUTHICH + "." + YEUTHICH_TRUYEN_ID + " = " + TABLE_TRUYEN + "." + TRUYEN_ID +
                " INNER JOIN " + TABLE_THELOAI +
                " ON " + TABLE_TRUYEN + "." + TRUYEN_THELOAI_ID + " = " + TABLE_THELOAI + "." + THELOAI_ID +
                " WHERE " + TABLE_YEUTHICH + "." + YEUTHICH_TAIKHOAN_ID + " = ?";
        return db.rawQuery(query, new String[]{String.valueOf(taikhoan_id)});
    }

    public Cursor getReadingByTaiKhoan(int taikhoan_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_DANGDOC +
                " INNER JOIN " + TABLE_TRUYEN +
                " ON " + TABLE_DANGDOC + "." + DANGDOC_TRUYEN_ID + " = " + TABLE_TRUYEN + "." + TRUYEN_ID +
                " INNER JOIN " + TABLE_THELOAI +
                " ON " + TABLE_TRUYEN + "." + TRUYEN_THELOAI_ID + " = " + TABLE_THELOAI + "." + THELOAI_ID +
                " WHERE " + TABLE_DANGDOC + "." + DANGDOC_TAIKHOAN_ID + " = ?";
        return db.rawQuery(query, new String[]{String.valueOf(taikhoan_id)});
    }


    // Lấy link sách theo truyen_id
    public Cursor getLinkSach(int truyen_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + TRUYEN_LINK + " FROM " + TABLE_TRUYEN +
                " WHERE " + TRUYEN_ID + " = ?";
        return db.rawQuery(query, new String[]{String.valueOf(truyen_id)});
    }

    // Lấy danh sách đang đọc của người dùng theo taikhoan_id và truyen_id
    public Cursor getReadingList(int taikhoan_id, int truyen_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_DANGDOC +
                " WHERE " + DANGDOC_TAIKHOAN_ID + " = ? AND " + DANGDOC_TRUYEN_ID + " = ?";
        return db.rawQuery(query, new String[]{String.valueOf(taikhoan_id), String.valueOf(truyen_id)});
    }

    // Xóa truyện khỏi bảng đang đọc
    public void removeFromReading(int taikhoan_id, int truyen_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DANGDOC, DANGDOC_TAIKHOAN_ID + " = ? AND " + DANGDOC_TRUYEN_ID + " = ?",
                new String[]{String.valueOf(taikhoan_id), String.valueOf(truyen_id)});
    }


    // Tăng lượt xem
    public void incrementViewCount(int truyen_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            // Tăng giá trị truyen_luotxem thêm 1
            db.execSQL("UPDATE " + TABLE_TRUYEN + " SET " + TRUYEN_LUOTXEM + " = " + TRUYEN_LUOTXEM + " + 1 WHERE " + TRUYEN_ID + " = ?",
                    new Object[]{truyen_id});
            Log.d("Database", "Tăng lượt xem thành công cho truyện ID: " + truyen_id);
        } catch (Exception e) {
            Log.e("Database", "Lỗi khi tăng lượt xem: " + e.getMessage());
        } finally {
            db.close();
        }
    }




}
